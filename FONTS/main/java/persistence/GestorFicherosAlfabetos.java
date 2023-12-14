package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.*;

/**
 * Gestiona la lectura y escritura de archivos relacionados con alfabetos para un usuario específico.
 */
public class GestorFicherosAlfabetos {

    /**
     * Constructor por defecto para el gestor de archivos de alfabetos.
     */
    public GestorFicherosAlfabetos() {
    }

    /**
     * Guarda los alfabetos para un usuario dado.
     *
     * @param username           El nombre de usuario para asociar los alfabetos.
     * @param conjuntoAlfabetos  La colección de alfabetos a guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si hay un error al escribir en el archivo de alfabetos.
     */
    public void guardarAlfabetos(String username, HashMap<String, ArrayList<Character> > conjuntoAlfabetos) throws EscrituraIncorrectaFicheroExcepcion {
        if (username.trim().isEmpty()) return;
        try {
            String path = "../DATA/" + username + "Alfabetos" + ".prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));

            for (Map.Entry<String, ArrayList<Character>> entry : conjuntoAlfabetos.entrySet()) {
                String nombreAlfabeto = entry.getKey();
                ArrayList<Character> caracteresAlfabeto = entry.getValue();
                String caracteresString = caracteresAString(caracteresAlfabeto).replaceAll("\\s", "◘");
                writer.write(nombreAlfabeto + 'º' + caracteresString + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de alfabetos " + e.getMessage());
        }
    }

    /**
     * Carga los alfabetos para un usuario específico.
     *
     * @param username El nombre de usuario.
     * @return Un HashMap con los alfabetos cargados.
     * @throws LecturaIncorrectaFicheroExcepcion Si hay un error al leer el archivo de alfabetos.
     */
    public HashMap<String, String> cargarAlfabetos(String username) throws LecturaIncorrectaFicheroExcepcion {
        HashMap<String, String> conjuntoAlfabetos = new HashMap<>();
        String path = "../DATA/" + username + "Alfabetos" + ".prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String elementos[] = linea.split("º");
                String nombreAlfabeto = elementos[0];
                String caracteres = elementos[1].replace("◘", "");
                conjuntoAlfabetos.put(nombreAlfabeto, caracteres);
            }
            reader.close();
        } catch (Exception e) {
            File file = new File(path);
            try {
                file.createNewFile();
            } catch (Exception ex) {
                throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de alfabetos " + ex.getMessage());
            }
        }
        return conjuntoAlfabetos;
    }

    /**
     * Elimina los alfabetos de un usuario.
     *
     * @param username El nombre de usuario.
     */
    public void eliminarAlfabetosDeUsuario(String username) {
        File fileAlfabetos = new File("../DATA/" + username + "Alfabetos" + ".prop");
        fileAlfabetos.delete();
    }

    /**
     * Convierte una lista de caracteres en una cadena de texto.
     *
     * @param caracteres La lista de caracteres a convertir.
     * @return La cadena resultante.
     */
    private String caracteresAString(ArrayList<Character> caracteres) {
        String s = "";
        for (int i = 0; i < caracteres.size(); ++i) {
            s += caracteres.get(i);
            s += "\n";
        }
        return s;
    }

    /**
     * Convierte una cadena de texto en una lista de caracteres.
     *
     * @param caracteresString La cadena de caracteres a convertir.
     * @return La lista de caracteres resultante.
     */
    private ArrayList<Character> stringAListaCaracteres(String caracteresString) {
        ArrayList<Character> listaCaracteres = new ArrayList<>();
            for (char c : caracteresString.toCharArray()) {
            listaCaracteres.add(c);
        }
        return listaCaracteres;
    }
}
