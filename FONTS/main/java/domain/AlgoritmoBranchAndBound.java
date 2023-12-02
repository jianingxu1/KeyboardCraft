package domain;

import java.util.*;

/**
 * Clase AlgoritmoBranchAndBound
 * Representa un algoritmo Branch and Bound con cota Gilmore-Lawler para
 * crear la distribucion mas optima para un teclado.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class AlgoritmoBranchAndBound implements Algoritmo {
    /**
     * Numero de instalaciones o caracteres.
     */
    private int numInstancias;

    /**
     * Matriz donde cada elemento representa la frecuencia entre dos caracteres.
     */
    private int[][] frecuencias;

    /**
     * Matriz donde cada elemento representa la distancia entre dos posiciones.
     */
    private double[][] distancias;

    /**
     * Mejor coste total obtenido.
     */
    private double mejorCosteTotal;

    /**
     * Mapa que contiene pares indiceCaracter, indicePosicion representando la
     * mejor asignacion de caracteres a posiciones.
     */
    private HashMap<Integer, Integer> mapMejorAsignacion;

    private char[][] distribucion;

    /**
     * Genera la distribucion optima para el teclado dada una entrada.
     * 
     * @param alfabeto Alfabeto a utilizar.
     * @param palabras Palabras con sus frecuencias.
     * @param texto    Texto para analizar.
     * @return Matriz de caracteres que representa la distribucion optima.
     */
    @Override
    public char[][] generarDistribucion(Alfabeto alfabeto, PalabrasConFrecuencia palabras, Texto texto) {
        this.numInstancias = alfabeto.getCaracteres().size();

        if (numInstancias > 1) {
            HashMap<String, Integer> frecuenciasBigrama = obtenerFrecuenciasBigramas(alfabeto, palabras, texto);
            char[] caracteres = obtenerCaracteres(alfabeto);
            inicializarMatrizFrecuencias(frecuenciasBigrama, caracteres);

            int[] filas = new int[1];
            int[] cols = new int[1];
            Posicion[] posiciones = obtenerPosiciones(filas, cols);
            inicializarMatrizDistancias(posiciones);

            calcularAsignacionOptima(this.frecuencias, this.distancias);

            distribucion = convertirMejorDistribucion(caracteres, posiciones, filas, cols);
        } else {
            distribucion = new char[1][10];
            distribucion[0][0] = alfabeto.getCaracteres().get(0);
        }

        return distribucion;
    }

    /**
     * Retorna el coste total de la asignacion optima.
     * 
     * @return El coste total de la asignacion optima.
     */
    public double getMejorCosteTotal() {
        return this.mejorCosteTotal;
    }

    /**
     * Obtiene las frecuencias de los bigramas en el texto.
     * 
     * @param alfabeto Alfabeto a utilizar.
     * @param palabras Palabras con sus frecuencias.
     * @param texto    Texto para analizar.
     * @return Mapa con las frecuencias de los bigramas.
     */
    private HashMap<String, Integer> obtenerFrecuenciasBigramas(Alfabeto alfabeto, PalabrasConFrecuencia palabras,
            Texto texto) {
        HashMap<String, Integer> frecuenciasBigrama = new HashMap<>();

        int total = 0; // total de caractéres

        ArrayList<Character> alfChar = new ArrayList<Character>();
        alfChar = alfabeto.getCaracteres();

        for (int i = 0; i < alfChar.size(); ++i) {
            for (int j = i + 1; j < alfChar.size(); ++j) {
                frecuenciasBigrama.put("" + alfChar.get(i) + alfChar.get(j), 0);
            }
        }
        String Texto = texto.getTexto();
        String TextoN = Texto.replaceAll("\\s", "");

        for (int i = 0; i < Texto.length() - 1; ++i) {
            String a = "" + Texto.charAt(i) + Texto.charAt(i + 1);
            if (frecuenciasBigrama.containsKey(a))
                frecuenciasBigrama.put(a, frecuenciasBigrama.get(a) + 1);
            else {
                StringBuffer b = new StringBuffer(a);
                b.reverse();
                String c = b.toString();
                if (frecuenciasBigrama.containsKey(c))
                    frecuenciasBigrama.put(c, frecuenciasBigrama.get(c) + 1);
            }
        }

        total = TextoN.length() - 1 - (Texto.length() - TextoN.length());

        Map<String, Integer> ListaPal = new LinkedHashMap<String, Integer>();

        ListaPal = palabras.getMap();

        for (Map.Entry<String, Integer> entry : ListaPal.entrySet()) {
            String pal = entry.getKey();
            Integer num = entry.getValue();
            for (int j = 0; j < pal.length() - 1; ++j) {
                String a = "" + pal.charAt(j) + pal.charAt(j + 1);
                if (frecuenciasBigrama.containsKey(a))
                    frecuenciasBigrama.put(a, frecuenciasBigrama.get(a) + num);
                else {
                    StringBuffer b = new StringBuffer(a);
                    b.reverse();
                    String c = b.toString();
                    if (frecuenciasBigrama.containsKey(c))
                        frecuenciasBigrama.put(c, frecuenciasBigrama.get(c) + num);
                }
            }
            total += (pal.length() - 1) * num;
        }
        return frecuenciasBigrama;
    }

    /**
     * Obtiene los caracteres del alfabeto.
     * 
     * @param alfabeto Alfabeto a utilizar.
     * @return Array de caracteres del alfabeto.
     */
    private char[] obtenerCaracteres(Alfabeto alfabeto) {
        char[] caracteres = new char[numInstancias];
        ArrayList<Character> caracteresAux = alfabeto.getCaracteres();
        for (int i = 0; i < numInstancias; ++i) {
            caracteres[i] = caracteresAux.get(i);
        }
        return caracteres;
    }

    /**
     * Calcula la clave para la frecuencia de bigramas.
     * 
     * @param frecuenciasBigrama Mapa de frecuencias de bigramas.
     * @param caracter1          Caracter 1.
     * @param caracter2          Caracter 2.
     * @return Clave para la frecuencia de bigramas.
     */
    private String calcularKey(Map<String, Integer> frecuenciasBigrama, char caracter1, char caracter2) {
        if (frecuenciasBigrama.containsKey("" + caracter1 + caracter2))
            return "" + caracter1 + caracter2;
        return "" + caracter2 + caracter1;
    }

    /**
     * Inicializa la matriz de frecuencias de bigramas.
     *
     * @param frecuenciasBigrama Mapa de frecuencias de bigramas.
     * @param caracteres         Array de caracteres distintos.
     */
    private void inicializarMatrizFrecuencias(Map<String, Integer> frecuenciasBigrama, char[] caracteres) {
        this.frecuencias = new int[numInstancias][numInstancias];
        for (int i = 0; i < numInstancias; ++i) {
            char caracter1 = caracteres[i];
            for (int j = 0; j < numInstancias; ++j) {
                char caracter2 = caracteres[j];
                if (i == j) {
                    // Inicializamos la diagonal principal a 0, ya que los bigramas no pueden ser
                    // iguales.
                    this.frecuencias[i][j] = 0;
                } else {
                    String key = calcularKey(frecuenciasBigrama, caracter1, caracter2);
                    // Obtenemos la frecuencia del bigrama y la asignamos a la matriz.
                    this.frecuencias[i][j] = frecuenciasBigrama.get(key);
                }
            }
        }
    }

    /**
     * Obtiene el numero de filas y columnas para la distribucion del teclado.
     *
     * @param filas Arreglo para almacenar el numero de filas.
     * @param cols  Arreglo para almacenar el numero de columnas.
     */
    private void obtenerNumFilasYColsParaDistribucionTeclado(int[] filas, int[] cols) {
        final int MAX_INSTANCIAS_PARA_UNA_FILA = 2;
        final int MAX_INSTANCIAS_PARA_DOS_FILAS = 6;
        if (numInstancias <= MAX_INSTANCIAS_PARA_UNA_FILA) {
            filas[0] = 1;
            cols[0] = numInstancias;
        } else if (numInstancias <= MAX_INSTANCIAS_PARA_DOS_FILAS) {
            filas[0] = 2;
            cols[0] = (int) Math.ceil((double) numInstancias / (double) filas[0]);
        } else {
            filas[0] = 3;
            cols[0] = (int) Math.ceil((double) numInstancias / (double) filas[0]);
        }
    }

    /**
     * Obtiene un arreglo de posiciones a partir de la matriz de filas y columnas
     * especificadas.
     *
     * @param filas Numero de filas.
     * @param cols  Numero de columnas.
     * @return Arreglo de posiciones.
     */
    private Posicion[] obtenerPosicionesAPartirDeMatriz(int filas, int cols) {
        Posicion[] posiciones = new Posicion[numInstancias];
        Posicion posicion = new Posicion(0, 0);
        for (int i = 0; i < numInstancias; ++i) {
            posiciones[i] = new Posicion(posicion);
            if (posicion.col < cols - 1) {
                ++posicion.col;
            } else {
                ++posicion.fila;
                posicion.col = 0;
            }
        }
        return posiciones;
    }

    /**
     * Obtiene las posiciones de los caracteres en la distribución.
     * 
     * @param filas Arreglo de filas.
     * @param cols  Arreglo de columnas.
     * @return Arreglo de posiciones.
     */
    private Posicion[] obtenerPosiciones(int[] filas, int[] cols) {
        obtenerNumFilasYColsParaDistribucionTeclado(filas, cols);
        Posicion[] posiciones = obtenerPosicionesAPartirDeMatriz(filas[0], cols[0]);
        return posiciones;
    }

    /**
     * Inicializa la matriz de distancias entre posiciones.
     *
     * @param posiciones Arreglo de posiciones.
     */
    private void inicializarMatrizDistancias(Posicion[] posiciones) {
        this.distancias = new double[numInstancias][numInstancias];
        for (int i = 0; i < numInstancias; ++i) {
            Posicion pos1 = posiciones[i];
            for (int j = 0; j < numInstancias; ++j) {
                Posicion pos2 = posiciones[j];
                this.distancias[i][j] = pos1.euclidianDistanceTo(pos2);
            }
        }
    }

    /**
     * Calcula la asignacion optima recursivamente.
     *
     * @param mapAsignacionActual Mapa de asignacion actual.
     * @param costeActual         Coste actual.
     * @param ubicacionActual     Ubicacion actual.
     */
    private void calcularAsignacionOptimaRecursivo(HashMap<Integer, Integer> mapAsignacionActual, double costeActual,
            int ubicacionActual) {
        if (ubicacionActual == this.numInstancias) {
            if (costeActual < this.mejorCosteTotal) {
                this.mapMejorAsignacion = new HashMap<Integer, Integer>(mapAsignacionActual);
                this.mejorCosteTotal = costeActual;
            }
            return;
        }
        for (int instalacion = 0; instalacion < this.numInstancias; ++instalacion) {
            if (mapAsignacionActual.containsKey(instalacion))
                continue;

            // Calcular coste actual poniendo la instalacion en la ubicacionActual
            double newCosteActual = (costeActual
                    + calcularCosteDeAsignar(mapAsignacionActual, instalacion, ubicacionActual));
            mapAsignacionActual.put(instalacion, ubicacionActual);

            // Calcular el bound para decidir si hacer branch
            double costeTotalAproximado = newCosteActual +
                    calcularCosteAproximadoNoEmplazados(mapAsignacionActual, ubicacionActual + 1);
            if (costeTotalAproximado >= this.mejorCosteTotal) {
                mapAsignacionActual.remove(instalacion);
                continue;
            }
            // Actualizar posicion y hacer branch
            calcularAsignacionOptimaRecursivo(mapAsignacionActual, newCosteActual, ubicacionActual + 1);

            mapAsignacionActual.remove(instalacion);
        }
    }

    /**
     * Calcula la asignacion optima de caracteres a posiciones y su coste.
     *
     * @param frecuencias Matriz de frecuencias entre caracteres.
     * @param distancias  Matriz de distancias entre posiciones.
     * @return El coste total de la asignacion optima.
     */
    public double calcularAsignacionOptima(int[][] frecuencias, double[][] distancias) {
        this.numInstancias = frecuencias.length;
        this.frecuencias = frecuencias;
        this.distancias = distancias;
        this.mapMejorAsignacion = new HashMap<Integer, Integer>();
        this.mejorCosteTotal = Double.MAX_VALUE;

        HashMap<Integer, Integer> mapAsignacionActual = new HashMap<>(); // instalacion a ubicacion
        double costeActual = 0.0;
        int indexActual = 0;
        calcularAsignacionOptimaRecursivo(mapAsignacionActual, costeActual, indexActual);
        return this.mejorCosteTotal;
    }

    /**
     * Calcula el coste entre dos asignaciones.
     *
     * @param instalacion1 Instalacion 1.
     * @param ubicacion1   Ubicacion 1.
     * @param instalacion2 Instalacion 2.
     * @param ubicacion2   Ubicacion 2.
     * @return El coste entre dos asignaciones.
     */
    private double calcularCosteEntreDosAsignaciones(int instalacion1, int ubicacion1,
            int instalacion2, int ubicacion2) {
        double distancia = this.distancias[ubicacion1][ubicacion2];
        double frecuencia = (double) this.frecuencias[instalacion1][instalacion2];
        return distancia * frecuencia;
    }

    /**
     * Calcula el coste de asignar un simbolo en una posicion respecto a los
     * simbolos ya emplazados.
     * 
     * @param mapAsignacionActual Mapa con la asignacion actual.
     * @param instalacion         Instalacion a asignar.
     * @param ubicacion           Ubicacion para asignar la instalacion.
     * @return El coste de asignar un simbolo en una posicion.
     */
    private double calcularCosteDeAsignar(HashMap<Integer, Integer> mapAsignacionActual,
            int instalacion, int ubicacion) {
        double coste = 0.0;
        for (HashMap.Entry<Integer, Integer> entry : mapAsignacionActual.entrySet()) {
            int instalacion1 = entry.getKey();
            int ubicacion1 = entry.getValue();
            coste += calcularCosteEntreDosAsignaciones(instalacion, ubicacion,
                    instalacion1, ubicacion1);
        }
        return coste * 2.0;
    }

    /**
     * Calcula y ordena el vector de traficos desde una instalacion dada hasta todas
     * las otras instalaciones no asignadas.
     * Este vector se utiliza para estimar el coste de emplazamiento en el problema
     * de asignacion cuadratica.
     * 
     * @param instalacionesNoAsignadas Array de instalaciones que aun no han sido
     *                                 asignadas.
     * @param instalacion1             La instalacion para la cual se calcula el
     *                                 vector de traficos.
     * @return Un array de Double que contiene los traficos calculados, ordenados de
     *         menor a mayor.
     */
    private Double[] calculaVectorTraficos(int[] instalacionesNoAsignadas, int instalacion1) {
        int numNoAsignados = instalacionesNoAsignadas.length;
        Double[] T = new Double[numNoAsignados - 1];

        // Calcula vector de traficos
        for (int t = 0, index = 0; t < numNoAsignados; ++t) {
            int instalacion2 = instalacionesNoAsignadas[t];
            if (instalacion1 == instalacion2)
                continue;
            T[index] = (double) this.frecuencias[instalacion1][instalacion2];
            ++index;
        }
        Arrays.sort(T);
        return T;
    }

    /**
     * Calcula y ordena el vector de distancias desde una ubicacion dada hasta todas
     * las otras ubicaciones no asignadas.
     * Este vector se utiliza para estimar el coste de emplazamiento en el problema
     * de asignacion cuadratica.
     * 
     * @param numNoAsignados             El numero de ubicaciones no asignadas.
     * @param primeraUbicacionNoAsignada El indice de la primera ubicacion no
     *                                   asignada.
     * @param ubicacion1                 La ubicacion desde la cual se calculan las
     *                                   distancias.
     * @return Un array de Double que contiene las distancias calculadas, ordenadas
     *         de mayor a menor.
     */
    private Double[] calculaVectorDistancias(int numNoAsignados, int primeraUbicacionNoAsignada, int ubicacion1) {
        Double[] D = new Double[numNoAsignados - 1];
        for (int d = 0, index = 0; d < numNoAsignados; ++d) {
            int ubicacion2 = primeraUbicacionNoAsignada + d;
            if (ubicacion1 == ubicacion2)
                continue;
            D[index] = this.distancias[ubicacion1][ubicacion2];
            ++index;
        }
        Arrays.sort(D, Collections.reverseOrder());
        return D;
    }

    /**
     * Calcula la matriz de costes C1 para el problema de asignacion cuadratica.
     * Cada elemento de la matriz
     * representa el coste de asignar una instalacion no emplazada a una ubicacion
     * especifica, considerando
     * las instalaciones ya emplazadas.
     * 
     * @param mapAsignacionActual        Un mapa que representa la asignacion actual
     *                                   de instalaciones a ubicaciones.
     * @param instalacionesNoAsignadas   Un array de instalaciones que aun no han
     *                                   sido asignadas.
     * @param primeraUbicacionNoAsignada El indice de la primera ubicacion no
     *                                   asignada.
     * @return Una matriz 2D de double representando los costes de asignacion.
     */
    private double[][] calculaC1(HashMap<Integer, Integer> mapAsignacionActual, int[] instalacionesNoAsignadas,
            int primeraUbicacionNoAsignada) {
        int numNoAsignados = instalacionesNoAsignadas.length;
        double[][] C1 = new double[numNoAsignados][numNoAsignados];
        for (int i = 0; i < numNoAsignados; ++i) {
            int instalacion = instalacionesNoAsignadas[i];
            for (int j = 0; j < numNoAsignados; ++j) {
                int ubicacion = primeraUbicacionNoAsignada + j;
                C1[i][j] = calcularCosteDeAsignar(mapAsignacionActual, instalacion, ubicacion);
            }
        }
        return C1;
    }

    /**
     * Calcula la matriz de costes C2 para el problema de asignacion cuadratica.
     * Cada elemento de la matriz
     * representa el coste estimado de asignar una instalacion no emplazada a una
     * ubicacion especifica,
     * considerando las instalaciones y ubicaciones aun no emplazadas.
     * 
     * @param instalacionesNoAsignadas   Un array de instalaciones que aun no han
     *                                   sido asignadas.
     * @param primeraUbicacionNoAsignada El indice de la primera ubicacion no
     *                                   asignada.
     * @return Una matriz 2D de double representando los costes de asignacion
     *         estimados.
     */
    private double[][] calculaC2(int[] instalacionesNoAsignadas, int primeraUbicacionNoAsignada) {
        int numNoAsignados = instalacionesNoAsignadas.length;
        double[][] C2 = new double[numNoAsignados][numNoAsignados];
        for (int i = 0; i < numNoAsignados; ++i) {
            int instalacion = instalacionesNoAsignadas[i];
            Double[] T = calculaVectorTraficos(instalacionesNoAsignadas, instalacion);

            for (int j = 0; j < numNoAsignados; ++j) {
                int ubicacion = primeraUbicacionNoAsignada + j;
                // Calcula vector de distancias
                Double[] D = calculaVectorDistancias(numNoAsignados, primeraUbicacionNoAsignada, ubicacion);
                C2[i][j] = productoEscalar(T, D);
            }
        }
        return C2;
    }

    /**
     * Suma dos matrices de igual dimension elemento a elemento. Utilizada para
     * combinar las matrices de coste
     * C1 y C2 en el proceso de resolucion del problema de asignacion cuadratica.
     * 
     * @param X La primera matriz de double.
     * @param Y La segunda matriz de double.
     * @return Una matriz que es la suma elemento a elemento de X e Y.
     */
    private double[][] sumaMatrices(double[][] X, double[][] Y) {
        int length = X.length;
        double[][] Z = new double[length][length];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                Z[i][j] = X[i][j] + Y[i][j];
            }
        }
        return Z;
    }

    /**
     * Calcula el coste aproximado para las instalaciones no emplazadas utilizando
     * el Algoritmo Hungaro.
     * Esta funcion considera la asignacion actual, calcula las matrices de coste C1
     * y C2, y utiliza
     * el Algoritmo Hungaro para encontrar el coste de la asignacion optima.
     * 
     * @param mapAsignacionActual        Un mapa con la asignacion actual de
     *                                   instalaciones a ubicaciones.
     * @param primeraUbicacionNoAsignada El indice de la primera ubicacion no
     *                                   asignada.
     * @return El coste aproximado de las instalaciones no emplazadas.
     */
    private double calcularCosteAproximadoNoEmplazados(HashMap<Integer, Integer> mapAsignacionActual,
            int primeraUbicacionNoAsignada) {
        int m = mapAsignacionActual.size();
        int numNoAsignados = numInstancias - m;

        // Inicializar instalacionesNoAsignadas
        int[] instalacionesNoAsignadas = new int[numNoAsignados];
        for (int instalacion = 0, i = 0; instalacion < numInstancias; ++instalacion) {
            if (mapAsignacionActual.containsKey(instalacion))
                continue;
            instalacionesNoAsignadas[i] = instalacion;
            ++i;
        }

        double[][] C1 = calculaC1(mapAsignacionActual, instalacionesNoAsignadas, primeraUbicacionNoAsignada);
        double[][] C2 = calculaC2(instalacionesNoAsignadas, primeraUbicacionNoAsignada);
        double[][] C1C2 = sumaMatrices(C1, C2);

        // Algoritmo Hungarian
        AlgoritmoHungarian algoritmo = new AlgoritmoHungarian();
        double coste = algoritmo.ejecutar(C1C2);
        return coste;
    }

    /**
     * Convierte la mejor distribucion en una matriz de caracteres.
     * 
     * @param caracteres Arreglo de caracteres del alfabeto.
     * @param posiciones Arreglo de posiciones.
     * @param filas      Arreglo de filas.
     * @param cols       Arreglo de columnas.
     * @return Matriz de caracteres que representa la mejor distribucion.
     */
    private char[][] convertirMejorDistribucion(char[] caracteres, Posicion[] posiciones, int[] filas, int[] cols) {
        char[][] distribucion = new char[filas[0]][cols[0]];
        for (Map.Entry<Integer, Integer> entry : mapMejorAsignacion.entrySet()) {
            int instalacion = entry.getKey();
            int ubicacion = entry.getValue();
            char caracter = caracteres[instalacion];
            Posicion posicion = posiciones[ubicacion];
            distribucion[posicion.fila][posicion.col] = caracter;
        }
        return distribucion;
    }

    /**
     * Calcula el producto escalar entre dos vectores.
     * 
     * @param X Vector X.
     * @param Y Vector Y.
     * @return El producto escalar entre los vectores X e Y.
     */
    private double productoEscalar(Double[] X, Double[] Y) {
        double resultado = 0;
        int numElementos = X.length;
        for (int i = 0; i < numElementos; ++i) {
            resultado += X[i] * Y[i];
        }
        return resultado;
    }
}
