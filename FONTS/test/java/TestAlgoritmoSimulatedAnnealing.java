import domain.Alfabeto;
import domain.AlgoritmoSimulatedAnnealing;
import domain.PalabrasConFrecuencia;
import domain.Texto;
import org.junit.Test;

/**
 * Clase TestAlgoritmoSA
 * Representa los test de un algoritmo SA en concreto con sus caracteres.
 * 
 * @author Rubén Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class TestAlgoritmoSimulatedAnnealing {

    @Test
    public void testAlgoritmoSimulatedAnnealing() {
        String input = "phone 1 keyboard 40 home 1000";
        PalabrasConFrecuencia palabras = new PalabrasConFrecuencia(input);

        String input2 = "abcdedfghijklmnopqrstuvwxyz";
        Texto texto = new Texto(input2);

        String a = "ingles";
        String alf = "abcdefghijklmnopqrstuvwxyz";

        Alfabeto alfabeto = new Alfabeto(a, alf);

        AlgoritmoSimulatedAnnealing alg = new AlgoritmoSimulatedAnnealing();

        char[][] dist = alg.generarDistribucion(alfabeto, palabras, texto);

        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist[0].length; ++j) {
                System.out.print(dist[i][j]);
            }
            System.out.println();
        }
    }
}
