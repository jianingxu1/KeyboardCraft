package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class VistaInterfazTitulo {
    private CtrlPresentacion iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Login");
    private JPanel panelContenidos = new JPanel();
    private JPanel panelBotones = new JPanel();

    private JPanel panelTitulo = new JPanel();
    private JLabel labelTitulo = new JLabel("Generador de teclados");
    private JButton buttonIniciarSesion = new JButton("Iniciar sesión");
    private JButton buttonCrearCuenta = new JButton("Crear cuenta");


    public VistaInterfazTitulo (CtrlPresentacion pCtrlPresentacion) {
        System.out.println
                ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    public void hacerVisible() {
        System.out.println
                ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
        frameVista.pack();
        frameVista.setVisible(true);
    }

    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_panelContenidos();
        inicializar_panelBotones();
        inicializar_panelTitulo();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(700,400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        // Posicion y operaciones por defecto
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Se agrega panelContenidos al contentPane (el panelContenidos se
        // podria ahorrar y trabajar directamente sobre el contentPane)
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panelContenidos);
    }

    private void inicializar_panelTitulo() {
        // Layout
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER)); // Usamos FlowLayout para centrar el JLabel        labelTitulo.setFont(new Font("Serif", Font.PLAIN, 30));
        // Componentes
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        panelTitulo.add(labelTitulo);
    }

    private void inicializar_panelContenidos() {
        // Layout
        panelContenidos.setLayout(new BorderLayout());
        // Paneles
        panelContenidos.add(panelTitulo,BorderLayout.NORTH);
        panelContenidos.add(panelBotones,BorderLayout.SOUTH);
    }


    private void inicializar_panelBotones() {
        // Layout
        panelBotones.setLayout(new FlowLayout());
        // Componentes
        buttonIniciarSesion.setBackground(Color.green);
        buttonCrearCuenta.setBackground(Color.red);
        panelBotones.add(buttonIniciarSesion);
        panelBotones.add(buttonCrearCuenta);
        // Tooltips
        buttonIniciarSesion.setToolTipText("Inicia sesión con una cuenta ya creada");
        buttonCrearCuenta.setToolTipText("Crea una nueva cuenta vacía");
    }

    private void asignar_listenersComponentes() {

        // Listeners para los botones

        buttonIniciarSesion.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_buttonIniciarSesion(event);
                    }
                });

        buttonCrearCuenta.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_buttonCrearCuenta(event);
                    }
                });
    }

    public void actionPerformed_buttonIniciarSesion(ActionEvent event) {
        // llamar a vista iniciarSesion
    }

    public void actionPerformed_buttonCrearCuenta(ActionEvent event) {
        // llamar a vista CrearCuenta
    }

}
