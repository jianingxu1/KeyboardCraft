package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VistaCambiarContraseña extends JFrame {
    private CtrlPresentacion iCtrlPresentacion;
    private JPanel panelContenidos;
    private JLabel labelLogo;
    private JLabel labelContraOld;
    private JPasswordField fieldContraOld;
    private JLabel labelContraNew;
    private JPasswordField fieldContraNew;
    private JPanel panelBotones;
    private JButton btnCambiarContraseña;
    private JButton btnVolver;
    private JPanel panelInput;

    public VistaCambiarContraseña(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    public void hacerVisible() {
        this.pack();
        this.setVisible(true);
    }

    public void hacerInvisible() {
        this.setVisible(false);
    }

    public void activar() {
        this.setEnabled(true);
    }

    public void desactivar() {
        this.setEnabled(false);
    }

    private void inicializarComponentes() {
        inicializar_frameVista();
        asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
        this.setTitle("Cambiar contraseña");
        this.setMinimumSize(new Dimension(500, 350));
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

    private void performOperationBeforeExit() {
        iCtrlPresentacion.syncVistaCambiarContraseña_a_GestionPerfil();
        dispose();
    }

    private void asignar_listenersComponentes() {
        btnCambiarContraseña.addActionListener(new ActionListener() {
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

    private void actionPerformed_btnCambiarContraseña(ActionEvent event) {

        char[] passwordOldChars = fieldContraOld.getPassword();
        String oldPass = new String(passwordOldChars);

        char[] passwordChars = fieldContraNew.getPassword();
        String newPass = new String(passwordChars);
        try {
            iCtrlPresentacion.cambiarContraseña(oldPass, newPass);
            limpiarCamposInput();
            iCtrlPresentacion.syncVistaCambiarContraseña_a_GestionPerfil();
            JOptionPane.showMessageDialog(this, "Contraseña modificada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actionPerformed_btnVolver(ActionEvent event) {
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaCambiarContraseña_a_GestionPerfil();
    }

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
        labelLogo.setText("Gestión de perfil");
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
        btnCambiarContraseña = new JButton();
        btnCambiarContraseña.setText("Cambiar contraseña");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(btnCambiarContraseña, gbc);
        btnVolver = new JButton();
        btnVolver.setText("Volver");
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
        labelContraNew.setText("Contraseña nueva");
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
        labelContraOld.setText("Contraseña antigua");
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