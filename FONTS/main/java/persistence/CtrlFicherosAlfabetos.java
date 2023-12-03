package persistence;

import domain.CjtAlfabetos;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;

public class CtrlFicherosAlfabetos {

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
