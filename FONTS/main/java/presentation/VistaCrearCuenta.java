package presentation;

import exceptions.ContrasenaNoValidaExcepcion;
import exceptions.EscrituraIncorrectaFicheroExcepcion;
import exceptions.NombreUsuarioNoValidoExcepcion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class VistaCrearCuenta {
    private CtrlPresentacion iCtrlPresentacion;
    private JFrame frameVista = new JFrame("Crear cuenta");
    private JPanel panelContenidos = new JPanel();
    private JButton buttonCerrar = new JButton("Atrás");
    private JPanel panelUser = new JPanel();
    private JTextArea Username = new JTextArea(2,25);
    private JPanel panelPass = new JPanel();
    private JTextArea Password = new JTextArea(2,25);


    private JPanel panelBotones = new JPanel();
    private JButton buttonRegistro = new JButton("Registrarse");

    public VistaCrearCuenta(CtrlPresentacion pCtrlPresentacion) {

        iCtrlPresentacion = pCtrlPresentacion;
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

    public void hacerInvisible() {
        frameVista.setVisible(false);
    }


    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_panelUser();
        inicializar_panelPass();
        inicializar_panelBotones();
        inicializar_panelContenidos();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(300, 200));
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

    private void inicializar_panelContenidos() {
        // Layout
        panelContenidos.setLayout(new FlowLayout());
        // Paneles
        panelContenidos.add(panelUser);
        panelContenidos.add(panelPass);
        panelContenidos.add(panelBotones);
    }

    private void inicializar_panelUser() {
        Username.setText("Nombre de usuario");
        panelUser.add(new JScrollPane(Username));
    }

    private void inicializar_panelPass() {
        Password.setText("Contraseña");
        panelPass.add(new JScrollPane(Password));
    }


    private void inicializar_panelBotones() {
        // Layout
        panelBotones.setLayout(new FlowLayout());
        // Componentes
        buttonRegistro.setBackground(Color.green);
        buttonCerrar.setBackground(Color.red);
        panelBotones.add(buttonRegistro);
        panelBotones.add(buttonCerrar);
        // Tooltips
        buttonRegistro.setToolTipText("Crear cuenta");
        buttonCerrar.setToolTipText("Cierra la pestaña");
    }

    private void asignar_listenersComponentes() {

        // Listeners para los botones

        buttonRegistro.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            actionPerformed_buttonCrearCuenta(event);
                        } catch (EscrituraIncorrectaFicheroExcepcion e) {
                            throw new RuntimeException(e);
                        } catch (NombreUsuarioNoValidoExcepcion e) {
                            throw new RuntimeException(e);
                        } catch (ContrasenaNoValidaExcepcion e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        buttonCerrar.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_buttonCerrar(event);
                    }
                });

    }

    public void actionPerformed_buttonCrearCuenta(ActionEvent event) throws EscrituraIncorrectaFicheroExcepcion, NombreUsuarioNoValidoExcepcion, ContrasenaNoValidaExcepcion {
        String user = Username.getText();
        String pass = Password.getText();

        if (pass.length() < 8) {
            JOptionPane.showMessageDialog(frameVista, "Error: la contraseña ha de tener\n" +
                    "8 o más caractéres.");
        }

        else if (iCtrlPresentacion.existeUsuario(user)) {
            JOptionPane.showMessageDialog(frameVista, "Error: el usuario ya existe.");
        }

        else iCtrlPresentacion.crearUsuario(user,pass);
    }

    public void actionPerformed_buttonCerrar(ActionEvent event) {
        iCtrlPresentacion.cerrarPestañaCrearCuenta();
    }

    public void activar() {
        frameVista.setEnabled(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
    }

}