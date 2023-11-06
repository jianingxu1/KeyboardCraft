package edu.upc.prop.cluster23;

import edu.upc.prop.cluster23.presentation.CtrlPresentacion;

public class Main {
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CtrlPresentacion mainWindow = new CtrlPresentacion();
				mainWindow.inicializarPresentacion();
			}
		});

	}
}