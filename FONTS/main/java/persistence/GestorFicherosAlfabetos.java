package persistence;

import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona la lectura y escritura de archivos relacionados con alfabetos para un usuario específico.
 * Implementa la interfaz GestorEstrategia.
 * @author Momin Miah Begum
 * @author Muhammad Yasin Khokhar
 */
public class GestorFicherosAlfabetos implements GestorEstrategia<ArrayList<Character>, String> {

    /**
     * Constructor por defecto para el gestor de archivos de alfabetos.
     */
    public GestorFicherosAlfabetos() {
    }

    /**
     * Guarda los alfabetos para un usuario dado.
     *
     * @param username          El nombre de usuario para asociar los alfabetos.
     * @param conjuntoAlfabetos La colección de alfabetos a guardar.
     * @throws EscrituraIncorrectaFicheroExcepcion Si hay un error al escribir en el archivo de alfabetos.
     */
    @Override
    public void guardar(String username, HashMap<String, ArrayList<Character>> conjuntoAlfabetos)
            throws EscrituraIncorrectaFicheroExcepcion {
        if (username.trim().isEmpty()) {
            return;
        }

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
        } catch (IOException e) {
            throw new EscrituraIncorrectaFicheroExcepcion(
                    "Error al escribir en el fichero de alfabetos: " + e.getMessage());
        }
    }

    /**
     * Carga los alfabetos para un usuario específico.
     *
     * @param username El nombre de usuario.
     * @return Un HashMap con los alfabetos cargados.
     * @throws LecturaIncorrectaFicheroExcepcion Si hay un error al leer el archivo de alfabetos.
     */
    @Override
    public HashMap<String, String> cargar(String username) throws LecturaIncorrectaFicheroExcepcion {
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
        } catch (IOException e) {
            File file = new File(path);

            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new LecturaIncorrectaFicheroExcepcion(
                        "Error al crear el fichero de alfabetos: " + ex.getMessage());
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
        StringBuilder s = new StringBuilder();
        for (Character c : caracteres) {
            s.append(c).append("\n");
        }
        return s.toString();
    }
    
}
