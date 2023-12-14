package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaGestionPerfil extends JFrame {

    private CtrlPresentacion iCtrlPresentacion;
    private JPanel panelBotones;
    private JButton eliminarCuentaButton;
    private JButton cambiarContraseñaButton;
    private JButton atrásButton;
    private JLabel labelGestionPerfil;
    private JPanel panelContenidos;

    public VistaGestionPerfil(CtrlPresentacion pCtrlPresentacion) {
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
        this.setTitle("Gestión de perfil");
        this.setMinimumSize(new Dimension(700, 500));
        this.setPreferredSize(this.getMinimumSize());
        this.setResizable(false);

        // Posicion y operaciones por defecto
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add WindowListener to the frame
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

    private void asignar_listenersComponentes() {
        atrásButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_atrásButton(event);
                    }
                });
    }

    void actionPerformed_atrásButton(ActionEvent event) {
        iCtrlPresentacion.syncVistaGestionPerfil_a_MenuPrincipal();
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panelContenidos = new JPanel();
        panelContenidos.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panelContenidos, gbc);
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelContenidos.add(panelBotones, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer1, gbc);
        atrásButton = new JButton();
        atrásButton.setHorizontalTextPosition(0);
        atrásButton.setPreferredSize(new Dimension(70, 30));
        atrásButton.setText("Atrás");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelBotones.add(atrásButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 30;
        panelBotones.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelBotones.add(spacer4, gbc);
        eliminarCuentaButton = new JButton();
        eliminarCuentaButton.setPreferredSize(new Dimension(250, 60));
        eliminarCuentaButton.setText("Eliminar cuenta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(eliminarCuentaButton, gbc);
        cambiarContraseñaButton = new JButton();
        cambiarContraseñaButton.setPreferredSize(new Dimension(250, 60));
        cambiarContraseñaButton.setText("Cambiar contraseña");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(cambiarContraseñaButton, gbc);
        labelGestionPerfil = new JLabel();
        labelGestionPerfil.setHorizontalAlignment(0);
        labelGestionPerfil.setHorizontalTextPosition(0);
        labelGestionPerfil.setText("Gestión de perfil");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(labelGestionPerfil, gbc);
    }
}