package persistence;

import domain.CjtAlfabetos;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;

public class GestorFicherosAlfabetos {

    /*
     * Constructora de la clase GestorFicherosAlfabetos
    */
    public GestorFicherosAlfabetos() {
    }

    /*
     * Guarda los alfabetos en el fichero de alfabetos
     * @throws EscrituraIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido escribir correctamente
     * @param cjtAlfabetos El conjunto de alfabetos que se quiere guardar
     * @param userName El nombre del usuario que quiere guardar los alfabetos
     */
    public void guardarAlfabetos(CjtAlfabetos cjtAlfabetos, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        if (userName.trim().isEmpty()) return;
        try {
            String path = "../DATA/" + userName + "Alfabetos" + ".prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (String idAlfabeto : cjtAlfabetos.getNombreAlfabetos()) {
                writer.write(idAlfabeto + 'º' + cjtAlfabetos.getAlfabetoCaracteresEnString(idAlfabeto).replaceAll("\\s", "◘") + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de alfabetos " + e.getMessage());
        }
    }

    /*
     * Carga los alfabetos del fichero de alfabetos
     * @return El conjunto de alfabetos que se ha cargado
     * @throws LecturaIncorrectaFicheroExcepcion Si el fichero de alfabetos no se ha podido leer correctamente
     * @param userName El nombre del usuario que quiere cargar los alfabetos
     */
    public CjtAlfabetos cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        CjtAlfabetos cjtAlfabetos = CjtAlfabetos.getInstance();
        String path = "../DATA/" + userName + "Alfabetos" + ".prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String elementos[] = linea.split("º");
                String idAlfabeto = elementos[0];
                String caracteres = elementos[1];
                caracteres = caracteres.replace("◘", "\n");
                cjtAlfabetos.añadirAlfabeto(idAlfabeto, caracteres);
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
        return cjtAlfabetos;
    }

    /*
     * Elimina los alfabetos del usuario
     * @param userName El nombre del usuario que quiere eliminar los alfabetos
     */
    public void eliminarAlfabetosDeUsuario(String userName) {
        File fileAlfabetos = new File("../DATA/" + userName + "Alfabetos" + ".prop");
        fileAlfabetos.delete();
    }

    /*
     * Convierte un string en una matriz de caracteres
     * @param distribucionString El string que contiene la distribucion
     * @return La matriz de caracteres que contiene la distribucion
     */
	private static char[][] convertirStringADistribucion(String distribucionString){
		char[][] distribucion = new char[3][10];
		int i = 0;
		int j = 0;
		for (int k = 0; k < distribucionString.length(); ++k) {
			if (distribucionString.charAt(k) == '◘') {
				i++;
				j = 0;
			} else {
				distribucion[i][j] = distribucionString.charAt(k);
				j++;
			}
		}
		return distribucion;
	}  
}
