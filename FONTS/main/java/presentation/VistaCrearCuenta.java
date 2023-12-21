package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class VistaCrearCuenta extends JFrame {
    private CtrlPresentacion iCtrlPresentacion;
    private JPanel panelContenidos;
    private JLabel labelLogo;
    private JLabel labelUsuario;
    private JTextField fieldUsuario;
    private JLabel labelContra;
    private JPasswordField fieldContra;
    private JPanel panelBotones;
    private JButton btnCrearCuenta;
    private JButton btnVolver;
    private JPanel panelInput;

    public VistaCrearCuenta(CtrlPresentacion pCtrlPresentacion) {
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
        this.setTitle("Generador de teclados");
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
        iCtrlPresentacion.syncVistaCrearCuenta_a_Bienvenida();
        dispose();
    }

    private void asignar_listenersComponentes() {
        btnCrearCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_btnCrearCuenta(event);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_btnVolver(event);
            }
        });

    }

    private void actionPerformed_btnCrearCuenta(ActionEvent event) {
        String user = fieldUsuario.getText();
        char[] passwordChars = fieldContra.getPassword();
        String password = new String(passwordChars);
        try {
            iCtrlPresentacion.crearUsuario(user, password);
            limpiarCamposInput();
            iCtrlPresentacion.syncVistaCrearCuenta_a_Bienvenida();
            JOptionPane.showMessageDialog(this, "¡Has creado una cuenta nueva!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actionPerformed_btnVolver(ActionEvent event) {
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaCrearCuenta_a_Bienvenida();
    }

    private void limpiarCamposInput() {
        fieldUsuario.setText("");
        fieldContra.setText("");
        fieldUsuario.revalidate();
        fieldUsuario.repaint();
        fieldContra.revalidate();
        fieldContra.repaint();
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
        labelLogo.setText("Logo");
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
        btnCrearCuenta = new JButton();
        btnCrearCuenta.setText("Crear Cuenta");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(btnCrearCuenta, gbc);
        btnVolver = new JButton();
        btnVolver.setText("Volver");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(btnVolver, gbc);
        panelInput = new JPanel();
        panelInput.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelContenidos.add(panelInput, gbc);
        labelContra = new JLabel();
        labelContra.setText("Contraseña:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(labelContra, gbc);
        fieldContra = new JPasswordField();
        fieldContra.setColumns(15);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(fieldContra, gbc);
        fieldUsuario = new JTextField();
        fieldUsuario.setColumns(15);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(fieldUsuario, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 60;
        panelInput.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 60;
        panelInput.add(spacer2, gbc);
        labelUsuario = new JLabel();
        labelUsuario.setText("Usuario:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelInput.add(labelUsuario, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelContenidos;
    }

}