import static org.junit.Assert.*;

import domain.Alfabeto;
import domain.AlgoritmoSimulatedAnnealing;
import domain.PalabrasConFrecuencia;
import domain.Texto;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase TestAlgoritmoSA
 * Representa los test de un algoritmo SA en concreto con sus caracteres.
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class TestAlgoritmoSimulatedAnnealing {


    @Test
    public void testFrecuenciaBigramas() {
        String alf = "ingles";
        String a = "abcd";

        Alfabeto alfabeto = new Alfabeto(alf,a);

        String inputList = "baba 2 aced 2 adab 2 hola 5 esternon 10";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(inputList);

        String inputText = "buenos dias bdb";
        Texto texto = new Texto(inputText);

        AlgoritmoSimulatedAnnealing alg = new AlgoritmoSimulatedAnnealing();

        char[][] dist = alg.generarDistribucion(alfabeto,palabras,texto);

        Map<String,Integer> freqs = new HashMap<>();

        freqs.put("ab",8);
        freqs.put("bc",0);
        freqs.put("cd",0);
        freqs.put("ac",2);
        freqs.put("ad",4);
        freqs.put("bd",2);

        // comprobamos que las frecuencias de los bigramas son las correctas


        System.out.println(freqs);
        System.out.println(alg.getFrecuenciasCjts());

        assertEquals(freqs, alg.getFrecuenciasCjts());
    }

    @Test
    public void testAlgoritmoSimulatedAnnealing() {
        String input = "phone 1 keyboard 40 home 1000";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);

        String input2 = "abcdedfghijklmnopqr";
        Texto texto = new Texto(input2);

        String a = "ingles";
        String alf = "abcdefghijklmnopqr";

        Alfabeto alfabeto = new Alfabeto(a, alf);

        AlgoritmoSimulatedAnnealing alg = new AlgoritmoSimulatedAnnealing(123);

        char[][] dist = alg.generarDistribucion(alfabeto, palabras, texto);

        String keyboard = MatrixToString(dist);

        String seededKeyboard = "lnhpqc\njmoaif\nkebrdg\n";

        // Comprobamos con una seed que genera siempre el mismo teclado

        assertEquals(keyboard, seededKeyboard);

        // Ahora vemos que nos saca una solución optimizada

        assertTrue(alg.getMejorCosteTotal() < alg.getCosteInicial());

        // realizamos una segunda prueba (algoritmo estocástico)

        AlgoritmoSimulatedAnnealing alg2 = new AlgoritmoSimulatedAnnealing();
        
        char[][] dist2 = alg2.generarDistribucion(alfabeto,palabras,texto);

        assertTrue(alg2.getMejorCosteTotal() < alg2.getCosteInicial());
    }

    String MatrixToString(char[][] dist) {

        StringBuilder keyboard = new StringBuilder();

        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist[0].length; ++j) {
                if(dist[i][j] != ' ') keyboard.append(dist[i][j]);
            }
            keyboard.append("\n");
        }

        return keyboard.toString();
    }
}
