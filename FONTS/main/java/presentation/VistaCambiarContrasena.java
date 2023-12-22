package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VistaCambiarContrasena
 * Representa la interfaz gráfica de la vista para cambiar la contraseña de un usuario.
 * 
 * @author Ruben Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class VistaCambiarContrasena extends JFrame {
    private CtrlPresentacion iCtrlPresentacion;
    private JPanel panelContenidos;
    private JLabel labelLogo;
    private JLabel labelContraOld;
    private JPasswordField fieldContraOld;
    private JLabel labelContraNew;
    private JPasswordField fieldContraNew;
    private JPanel panelBotones;
    private JButton btnCambiarContrasena;
    private JButton btnVolver;
    private JPanel panelInput;

    /**
     * Constructor para inicializar la vista cambiar contraseña.
     * 
     * @param pCtrlPresentacion Controlador de la presentacion.
     */
    public VistaCambiarContrasena(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    /**
     * Hace visible la vista.
     */
    public void hacerVisible() {
        this.pack();
        this.setVisible(true);
    }

    /**
     * Oculta la vista.
     */
    public void hacerInvisible() {
        this.setVisible(false);
    }

    /**
     * Activa la vista.
     */
    public void activar() {
        this.setEnabled(true);
    }

    /**
     * Desactiva la vista.
     */
    public void desactivar() {
        this.setEnabled(false);
    }

    /**
     * Inicializa los componentes de la vista.
     */
    private void inicializarComponentes() {
        inicializar_frameVista();
        asignar_listenersComponentes();
    }

    /**
     * Inicializa el frame de la vista.
     */
    private void inicializar_frameVista() {
        this.setTitle("KeyboardCraft - Cambiar contraseña");
        this.setMinimumSize(new Dimension(600, 450));
        this.setPreferredSize(this.getMinimumSize());
        this.setResizable(false);

        // Posicion y operaciones por defecto
        this.setLocationRelativeTo(null);

        // Set default close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a window listener to intercept the window-closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                performOperationBeforeExit();
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
    private void performOperationBeforeExit() {
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Perform cleanup or other necessary operations before exiting
            try {
                iCtrlPresentacion.guardarDatos();
                this.dispose(); // Close the frame
                iCtrlPresentacion.cerrarPrograma();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    /**
     * Asigna los listeners para los componentes de la vista.
     */
    private void asignar_listenersComponentes() {
        btnCambiarContrasena.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_btnCambiarContraseña(event);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_btnVolver(event);
            }
        });

    }

    /**
     * Cambia la contraseña por la nueva cuando se pulsa el botón de cambiar contraseña.
     * @param event Evento de pulsación del botón.
     */
    private void actionPerformed_btnCambiarContraseña(ActionEvent event) {

        char[] passwordOldChars = fieldContraOld.getPassword();
        String oldPass = new String(passwordOldChars);

        char[] passwordChars = fieldContraNew.getPassword();
        String newPass = new String(passwordChars);
        try {
            iCtrlPresentacion.cambiarContrasena(oldPass, newPass);
            limpiarCamposInput();
            iCtrlPresentacion.syncVistaCambiarContrasena_a_GestionPerfil();
            JOptionPane.showMessageDialog(this, "Contraseña modificada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Vuelve a la vista de gestión de perfil cuando se pulsa el botón de volver.
     * @param event Evento de pulsación del botón.
     */
    private void actionPerformed_btnVolver(ActionEvent event) {
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaCambiarContrasena_a_GestionPerfil();
    }

    /**
     * Limpia los campos de input de la vista.
     */
    private void limpiarCamposInput() {
        fieldContraOld.setText("");
        fieldContraNew.setText("");
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelContenidos = new JPanel();
        panelContenidos.setLayout(new GridBagLayout());
        labelLogo = new JLabel();
        labelLogo.setText("Modificación de contraseña");
        labelLogo.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelContenidos.add(labelLogo, gbc);
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenidos.add(panelBotones, gbc);
        btnCambiarContrasena = new JButton();
        btnCambiarContrasena.setText("Cambiar contraseña");
        btnCambiarContrasena.setPreferredSize(new Dimension(btnCambiarContrasena.getPreferredSize().width, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(btnCambiarContrasena, gbc);
        btnVolver = new JButton();
        btnVolver.setText("Volver");
        btnVolver.setPreferredSize(new Dimension(btnVolver.getPreferredSize().width, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(btnVolver, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer1, gbc);
        panelInput = new JPanel();
        panelInput.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenidos.add(panelInput, gbc);
        labelContraNew = new JLabel();
        labelContraNew.setText("Contraseña nueva:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(labelContraNew, gbc);
        fieldContraNew = new JPasswordField();
        fieldContraNew.setColumns(15);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelInput.add(fieldContraNew, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 60;
        panelInput.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 60;
        panelInput.add(spacer3, gbc);
        labelContraOld = new JLabel();
        labelContraOld.setText("Contraseña antigua:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(labelContraOld, gbc);
        fieldContraOld = new JPasswordField();
        fieldContraOld.setColumns(15);
        fieldContraOld.setPreferredSize(new Dimension(126, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelInput.add(fieldContraOld, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelInput.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelInput.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelInput.add(spacer6, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelContenidos;
    }

}