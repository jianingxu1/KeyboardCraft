package persistence;

import domain.CjtTeclados;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.LecturaIncorrectaFicheroExcepcion;

import java.io.*;

public class CtrlFicherosTeclados {

    public void guardarTeclados(CjtTeclados cjtTeclados, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        if (userName.trim().isEmpty()) return;
        try {
            String path = "../DATA/" + userName + "Teclados" + ".prop";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
            for (String nombreTeclado : cjtTeclados.getNombreTeclados()) {
                writer.write(nombreTeclado + 'º' + cjtTeclados.getDistribucioStringSimplificado(nombreTeclado) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion("Error al escribir en el fichero de teclados " + e.getMessage());
        }
    }

    public CjtTeclados cargarTeclados(String userName) throws LecturaIncorrectaFicheroExcepcion {
        CjtTeclados cjtTeclados = CjtTeclados.getInstance();
        String path = "../DATA/" + userName + "Teclados" + ".prop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String elementos[] = linea.split("º");
                String nombreTeclado = elementos[0];
                char[][] distribucion = convertirStringADistribucion(elementos[1]);
                cjtTeclados.crearTeclado(nombreTeclado, distribucion);
            }
            reader.close();
        } catch (Exception e) {
            File file = new File(path);
            try {
                file.createNewFile();
            } catch (Exception ex) {
                throw new LecturaIncorrectaFicheroExcepcion("Error al crear el fichero de teclados " + ex.getMessage());
            }
        }
        return cjtTeclados;
    }

    private static char[][] convertirStringADistribucion(String distribucionString) {
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
