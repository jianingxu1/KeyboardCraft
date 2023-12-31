package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VistaMenuPrincipal
 * Representa la vista del menu principal de la aplicacion.
 * 
 * @author Ruben Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class VistaMenuPrincipal extends JFrame {
    /**
     * Controlador de presentación asociado a esta vista.
     */
    CtrlPresentacion iCtrlPresentacion;

    /**
     * Panel que contiene los elementos visuales del menú principal.
     */
    private JPanel panelContenidos;

    /**
     * Botón para transicionar a gestionar los teclados en el menú principal.
     */
    private JButton btnGestionarTeclados;

    /**
     * Botón para transicionar a gestionar los alfabetos en el menú principal.
     */
    private JButton btnGestionarAlfabetos;

    /**
     * Botón para transicionar a gestionar el perfil en el menú principal.
     */
    private JButton btnGestionarPerfil;

    /**
     * Botón para cerrar sesión en el menú principal.
     */
    private JButton btnLogOut;

    /**
     * Panel que contiene los botones del menú principal.
     */
    private JPanel panelBotones;

    /**
     * Etiqueta de título del menú principal.
     */
    private JLabel labelTitulo;

    /**
     * Constructor de la vista del menu principal.
     * 
     * @param pCtrlPresentacion El controlador de presentacion asociado a esta
     *                          vista.
     */
    public VistaMenuPrincipal(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    /**
     * Hace visible el menu principal.
     */
    public void hacerVisible() {
        this.pack();
        this.setVisible(true);
    }

    /**
     * Oculta el menu principal.
     */
    public void hacerInvisible() {
        this.setVisible(false);
    }

    /**
     * Activa el menu principal.
     */
    public void activar() {
        this.setEnabled(true);
    }

    /**
     * Desactiva el menu principal.
     */
    public void desactivar() {
        this.setEnabled(false);
    }

    /**
     * Inicializa los componentes del menu principal.
     */
    private void inicializarComponentes() {
        inicializar_frameVista();
        asignar_listenersComponentes();
    }

    /**
     * Inicializa el frame de la vista.
     */
    private void inicializar_frameVista() {
        this.setTitle("KeyboardCraft - Menú Principal");
        this.setMinimumSize(new Dimension(600, 450));
        this.setPreferredSize(this.getMinimumSize());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    performOperationBeforeExit();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.setContentPane(panelContenidos);
    }

    /**
     * Realiza las operaciones de confirmación y guardado de datos antes de cerrar
     * la aplicacion.
     * 
     * @throws Exception
     */
    private void performOperationBeforeExit() throws Exception {
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                iCtrlPresentacion.guardarDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            dispose(); // Close the frame
            iCtrlPresentacion.cerrarPrograma();
        }
    }

    /**
     * Asigna los listeners a los componentes de la vista.
     */
    private void asignar_listenersComponentes() {
        btnGestionarAlfabetos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_btnGestionarAlfabetos(event);
                    }
                });
        btnGestionarTeclados.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_btnGestionarTeclados(event);
                    }
                });
        btnGestionarPerfil.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_btnGestionarPerfil(event);
                    }
                });
        btnLogOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_btnLogout(event);
                    }
                });

    }

    /**
     * Transiciona de la vista del menú principal a la gestión de alfabetos.
     * 
     * @param event El evento de acción que desencadena esta operación.
     */
    private void actionPerformed_btnGestionarAlfabetos(ActionEvent event) {
        iCtrlPresentacion.syncVistaMenuPrincipal_a_GestionAlfabetos();
    }

    /**
     * Transiciona de la vista del menú principal a la gestión de teclados.
     * 
     * @param event El evento de acción que desencadena esta operación.
     */
    private void actionPerformed_btnGestionarTeclados(ActionEvent event) {
        iCtrlPresentacion.syncVistaMenuPrincipal_a_GestionTeclados();
    }

    /**
     * Transiciona de la vista del menú principal a la gestión de perfil.
     * 
     * @param event El evento de acción que desencadena esta operación.
     */
    private void actionPerformed_btnGestionarPerfil(ActionEvent event) {
        iCtrlPresentacion.syncVistaMenuPrincipal_a_GestionPerfil();
    }

    /**
     * Cierra la sesión y transiciona de la vista del menú principal a la gestión de
     * perfil.
     * 
     * @param event El evento de acción que desencadena esta operación.
     */
    private void actionPerformed_btnLogout(ActionEvent event) {
        try {
            iCtrlPresentacion.cerrarSesion();
            iCtrlPresentacion.syncVistaMenuPrincipal_a_Bienvenida();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panelContenidos = new JPanel();
        panelContenidos.setLayout(new GridBagLayout());
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelContenidos.add(panelBotones, gbc);
        btnGestionarTeclados = new JButton();
        btnGestionarTeclados.setPreferredSize(new Dimension(250, 60));
        btnGestionarTeclados.setText("Gestionar teclados");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelBotones.add(btnGestionarTeclados, gbc);
        btnGestionarAlfabetos = new JButton();
        btnGestionarAlfabetos.setPreferredSize(new Dimension(250, 60));
        btnGestionarAlfabetos.setText("Gestionar alfabetos");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelBotones.add(btnGestionarAlfabetos, gbc);
        btnGestionarPerfil = new JButton();
        btnGestionarPerfil.setHorizontalAlignment(0);
        btnGestionarPerfil.setPreferredSize(new Dimension(250, 60));
        btnGestionarPerfil.setText("Gestionar perfil");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelBotones.add(btnGestionarPerfil, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelContenidos.add(spacer3, gbc);
        labelTitulo = new JLabel();
        labelTitulo.setHorizontalAlignment(0);
        labelTitulo.setHorizontalTextPosition(0);
        labelTitulo.setText("Menú Principal");
        labelTitulo.setFont(new java.awt.Font("Lucida Grande", 1, 16));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelContenidos.add(labelTitulo, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 30;
        panelContenidos.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelContenidos.add(spacer5, gbc);
        btnLogOut = new JButton();
        btnLogOut.setPreferredSize(new Dimension(82, 30));
        btnLogOut.setText("Log out");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        panelContenidos.add(btnLogOut, gbc);
    }

    /**
     * Devuelve el panel de contenidos de la vista.
     * @return panelContenidos
     */
    public JComponent $$$getRootComponent$$$() {
        return panelContenidos;
    }

}
