import exceptions.InputNoEsEnteroExcepcion;
import presentation.CtrlPresentacion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
				ctrlPresentacion.inicializarPresentacion();
			}
		});

	}

	static int escoger() {
		Scanner input;
		input = new Scanner(System.in);

		int opt = -1;

		boolean valido = false;

		System.out.println("Escoge una opción para usar el generador de teclados:\n" +
				"1. Vista por terminal. 2. Vista por interfaz");

		while (!valido) {
			try {
				opt = input.nextInt();
				if (opt == 1 || opt == 2) valido = true;

				else System.out.println("Opción inválida. Debe ser un valor entre 1 y 2.");
			}
			catch (InputMismatchException ime) {
				System.out.println("Opción inválida. Debe ser un valor entre 1 y 2.");
				input.nextLine();
			}
		}
		return opt;
	}
}