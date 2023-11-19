package edu.upc.prop.cluster23.domain;

import java.util.*;
import edu.upc.prop.cluster23.domain.AlgoritmoHungarian;

/**
 * Clase AlgoritmoQAP
 * Representa un algoritmo Branch and Bound con cota Gilmore-Lawler para
 * crear la distribucion mas optima para un teclado.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class AlgoritmoQAP implements Algoritmo {
    /**
     * Numero de instalaciones o caracteres.
     */
    private int n;

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
     * @param alf      Alfabeto a utilizar.
     * @param palabras Palabras con sus frecuencias.
     * @param texto    Texto para analizar.
     * @return Matriz de caracteres que representa la distribucion optima.
     */
    @Override
    public char[][] generarDistribucion(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        this.n = alf.getCaracteres().size();

        if (n > 1) {

            HashMap<String, Integer> frecuenciasCjts = obtenerFrecuenciasCjts(alf, palabras, texto);
            char[] caracteres = obtenerCaracteres(alf);
            inicializarFrecuencias(frecuenciasCjts, caracteres);

            int[] filas = new int[1];
            int[] cols = new int[1];
            Posicion[] posiciones = obtenerPosiciones(filas, cols);
            inicializarDistancias(posiciones);

            calcularAsignacionOptima(this.frecuencias, this.distancias);

            distribucion = convertirMejorDistribucion(caracteres, posiciones, filas, cols);
        }

        else {
            distribucion = new char[1][10];
            distribucion[0][0] = alf.getCaracteres().get(0);
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
     * @param alf      Alfabeto a utilizar.
     * @param palabras Palabras con sus frecuencias.
     * @param texto    Texto para analizar.
     * @return Mapa con las frecuencias de los bigramas.
     */
    private HashMap<String, Integer> obtenerFrecuenciasCjts(Alfabeto alf, PalabrasConFrecuencia palabras, Texto texto) {
        HashMap<String, Integer> frecuenciasCjts = new HashMap<>();

        int total = 0; // total de caractéres

        ArrayList<Character> alfChar = new ArrayList<Character>();
        alfChar = alf.getCaracteres();

        for (int i = 0; i < alfChar.size(); ++i) {
            for (int j = i + 1; j < alfChar.size(); ++j) {
                frecuenciasCjts.put("" + alfChar.get(i) + alfChar.get(j), 0);
            }
        }
        String Texto = texto.getTexto();
        String TextoN = Texto.replaceAll("\\s", "");

        for (int i = 0; i < Texto.length() - 1; ++i) {
            String a = "" + Texto.charAt(i) + Texto.charAt(i + 1);
            if (frecuenciasCjts.containsKey(a))
                frecuenciasCjts.put(a, frecuenciasCjts.get(a) + 1);
            else {
                StringBuffer b = new StringBuffer(a);
                b.reverse();
                String c = b.toString();
                if (frecuenciasCjts.containsKey(c))
                    frecuenciasCjts.put(c, frecuenciasCjts.get(c) + 1);
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
                if (frecuenciasCjts.containsKey(a))
                    frecuenciasCjts.put(a, frecuenciasCjts.get(a) + num);
                else {
                    StringBuffer b = new StringBuffer(a);
                    b.reverse();
                    String c = b.toString();
                    if (frecuenciasCjts.containsKey(c))
                        frecuenciasCjts.put(c, frecuenciasCjts.get(c) + num);
                }
            }
            total += (pal.length() - 1) * num;
        }
        return frecuenciasCjts;
    }

    /**
     * Obtiene los caracteres del alfabeto.
     * 
     * @param alf Alfabeto a utilizar.
     * @return Array de caracteres del alfabeto.
     */
    private char[] obtenerCaracteres(Alfabeto alf) {
        char[] caracteres = new char[n];
        ArrayList<Character> car = alf.getCaracteres();
        for (int i = 0; i < n; ++i) {
            caracteres[i] = car.get(i);
        }
        return caracteres;
    }

    /**
     * Calcula la clave para la frecuencia de bigramas.
     * 
     * @param frecuenciasCjts Mapa de frecuencias de bigramas.
     * @param c1              Caracter 1.
     * @param c2              Caracter 2.
     * @return Clave para la frecuencia de bigramas.
     */
    private String calcularKey(Map<String, Integer> frecuenciasCjts, char c1, char c2) {
        if (frecuenciasCjts.containsKey("" + c1 + c2))
            return "" + c1 + c2;
        return "" + c2 + c1;
    }

    /**
     * Inicializa la matriz de frecuencias de bigramas.
     * 
     * @param frecuenciasCjts Mapa de frecuencias de bigramas.
     * @param caracteres      Array de caracteres distintos.
     */
    private void inicializarFrecuencias(Map<String, Integer> frecuenciasCjts, char[] caracteres) {
        this.frecuencias = new int[n][n];
        for (int i = 0; i < n; ++i) {
            char c1 = caracteres[i];
            for (int j = 0; j < n; ++j) {
                char c2 = caracteres[j];
                if (i == j)
                    this.frecuencias[i][j] = 0;
                else {
                    String key = calcularKey(frecuenciasCjts, c1, c2);
                    this.frecuencias[i][j] = frecuenciasCjts.get(key);
                }
            }
        }
    }

    /**
     * Obtiene las posiciones de los caracteres en la distribución.
     * 
     * @param filas Arreglo de filas.
     * @param cols  Arreglo de columnas.
     * @return Arreglo de posiciones.
     */
    public Posicion[] obtenerPosiciones(int[] filas, int[] cols) {
        Posicion[] posiciones = new Posicion[n];
        if (n <= 2) {
            filas[0] = 1;
            cols[0] = n;
        } else if (n <= 6) {
            filas[0] = 2;
            cols[0] = (int) Math.ceil((double) n / (double) filas[0]);
        } else {
            filas[0] = 3;
            cols[0] = (int) Math.ceil((double) n / (double) filas[0]);
        }
        Posicion p = new Posicion(0, 0);
        for (int i = 0; i < n; ++i) {
            posiciones[i] = new Posicion(p);
            if (p.col < cols[0] - 1)
                ++p.col;
            else {
                ++p.fila;
                p.col = 0;
            }
        }
        return posiciones;
    }

    /**
     * Inicializa la matriz de distancias entre posiciones.
     * 
     * @param posiciones Arreglo de posiciones.
     */
    private void inicializarDistancias(Posicion[] posiciones) {
        this.distancias = new double[n][n];
        for (int i = 0; i < n; ++i) {
            Posicion p1 = posiciones[i];
            for (int j = 0; j < n; ++j) {
                Posicion p2 = posiciones[j];
                this.distancias[i][j] = p1.euclidianDistanceTo(p2);
            }
        }
    }

    private void calcularAsignacionOptimaRecursivo(HashMap<Integer, Integer> mapAsignacionActual, double costeActual,
            int ubicacionActual) {
        if (ubicacionActual == this.n) {
            if (costeActual < this.mejorCosteTotal) {
                this.mapMejorAsignacion = new HashMap<Integer, Integer>(mapAsignacionActual);
                this.mejorCosteTotal = costeActual;
            }
            return;
        }
        for (int instalacion = 0; instalacion < this.n; ++instalacion) {
            if (mapAsignacionActual.containsKey(instalacion))
                continue;

            // Calcular coste actual poniendo la instalacion en la ubicacionActual
            double newCosteActual = (costeActual
                    + calcularCosteDeAsignar(mapAsignacionActual, instalacion, ubicacionActual));
            mapAsignacionActual.put(instalacion, ubicacionActual);

            // Calcular el bound para decidir si hacer branch
            double costeTotalAproximado = newCosteActual +
                    calcularCosteNoEmplazados(mapAsignacionActual, ubicacionActual + 1);
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
        this.n = frecuencias.length;
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
     * @param instalacion1 Instalación 1.
     * @param ubicacion1   Ubicación 1.
     * @param instalacion2 Instalación 2.
     * @param ubicacion2   Ubicación 2.
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
     * Calcula el coste de las instalaciones no asignadas.
     * 
     * @param mapAsignacionActual Mapa con la asignacion actual.
     * @param k                   La primera ubicacion no asignada. De 0 a k-1
     *                            estan asignadas.
     * @return El coste de las instalaciones no asignadas.
     */
    private double calcularCosteNoEmplazados(HashMap<Integer, Integer> mapAsignacionActual,
            int k) {
        int m = mapAsignacionActual.size();
        int numNoAsignados = n - m;

        // Inicializar instalacionesNoAsignadas
        int[] instalacionesNoAsignadas = new int[numNoAsignados];
        for (int instalacion = 0, i = 0; instalacion < n; ++instalacion) {
            if (mapAsignacionActual.containsKey(instalacion))
                continue;
            instalacionesNoAsignadas[i] = instalacion;
            ++i;
        }

        // Calcula C1
        double[][] C1 = new double[numNoAsignados][numNoAsignados];
        for (int i = 0; i < numNoAsignados; ++i) {
            int instalacion = instalacionesNoAsignadas[i];
            for (int j = 0; j < numNoAsignados; ++j) {
                int ubicacion = k + j;
                C1[i][j] = calcularCosteDeAsignar(mapAsignacionActual, instalacion, ubicacion);
            }
        }

        // Calcula C2
        double[][] C2 = new double[numNoAsignados][numNoAsignados];
        for (int i = 0; i < numNoAsignados; ++i) {
            int instalacion1 = instalacionesNoAsignadas[i];
            for (int j = 0; j < numNoAsignados; ++j) {
                int ubicacion1 = k + j;
                Double[] T = new Double[numNoAsignados - 1];

                // Calcula vector de traficos
                for (int t = 0, index = 0; t < numNoAsignados; ++t) {
                    int instalacion2 = instalacionesNoAsignadas[t];
                    if (instalacion1 == instalacion2)
                        continue;
                    T[index] = (double) this.frecuencias[instalacion1][instalacion2];
                    ++index;
                }

                // Calcula vector de distancias
                Double[] D = new Double[numNoAsignados - 1];
                for (int d = 0, index = 0; d < numNoAsignados; ++d) {
                    int ubicacion2 = k + d;
                    if (ubicacion1 == ubicacion2)
                        continue;
                    D[index] = this.distancias[ubicacion1][ubicacion2];
                    ++index;
                }
                Arrays.sort(T);
                Arrays.sort(D, Collections.reverseOrder());
                C2[i][j] = productoEscalar(T, D);
            }
        }

        // Calcula C1+C2
        double[][] C1C2 = new double[numNoAsignados][numNoAsignados];
        for (int i = 0; i < numNoAsignados; ++i) {
            for (int j = 0; j < numNoAsignados; ++j) {
                C1C2[i][j] = C1[i][j] + C2[i][j];
            }
        }

        // Algoritmo Hungarian
        AlgoritmoHungarian alg = new AlgoritmoHungarian();
        double coste = alg.ejecutar(C1C2);
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
            char c = caracteres[instalacion];
            Posicion p = posiciones[ubicacion];
            distribucion[p.fila][p.col] = c;
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
        double res = 0;
        int n = X.length;
        for (int i = 0; i < n; ++i) {
            res += X[i] * Y[i];
        }
        return res;
    }
}
