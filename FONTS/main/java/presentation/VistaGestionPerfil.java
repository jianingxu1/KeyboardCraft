package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase VistaGestionPerfil
 * Representa la interfaz gráfica de la vista de bienvenida.
 * 
 * @author Ruben Catalán Rua (ruben.catalan@estudiantat.upc.edu)
 */
public class VistaGestionPerfil extends JFrame {
    private CtrlPresentacion iCtrlPresentacion;
    private JPanel panelBotones;
    private JButton eliminarCuentaButton;
    private JButton cambiarContrasenaButton;
    private JButton atrasButton;
    private JLabel labelGestionPerfil;
    private JPanel panelContenidos;

    /**
     * Constructora de la vista.
     * @param pCtrlPresentacion Controlador de presentacion.
     */
    public VistaGestionPerfil(CtrlPresentacion pCtrlPresentacion) {
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
        this.setTitle("KeyboardCraft - Gestión de perfil");
        this.setMinimumSize(new Dimension(600, 450));
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
        atrasButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_atrasButton(event);
                    }
                });
        eliminarCuentaButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        try {
                            actionPerformed_eliminarCuentaButton(event);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        cambiarContrasenaButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        try {
                            actionPerformed_cambiarContrasenaButton(event);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    /**
     * Transiciona de Vista Gestion Perfil a Vista Cambiar Contraseña cuando se
     * pulsa el botón de cambiar contraseña.
     * @param evt El ActionEvent desencadenado por el clic en el botón.
     */
    void actionPerformed_cambiarContrasenaButton(ActionEvent event) {
        iCtrlPresentacion.syncVistaGestionPerfil_a_CambiarContrasena();
    }


    /**
     * Transiciona de Vista Gestion Perfil a Vista Bienvenida cuando se
     * pulsa el botón de eliminar cuenta.
     * @param evt El ActionEvent desencadenado por el clic en el botón.
     */
    void actionPerformed_eliminarCuentaButton(ActionEvent event) throws Exception {
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro? El borrado de la cuenta es irreversible.", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                iCtrlPresentacion.borrarCuenta();
                JOptionPane.showMessageDialog(this, "Cuenta borrada con éxito.");
                iCtrlPresentacion.syncVistaGestionPerfil_a_Bienvenida();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    /**
     * Transiciona de Vista Gestion Perfil a Vista Menu Principal cuando se
     * pulsa el botón de atras.
     * @param event El ActionEvent desencadenado por el clic en el botón.
     */
    void actionPerformed_atrasButton(ActionEvent event) {
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
        atrasButton = new JButton();
        atrasButton.setHorizontalTextPosition(0);
        atrasButton.setPreferredSize(new Dimension(70, 30));
        atrasButton.setText("Atrás");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelBotones.add(atrasButton, gbc);
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
        cambiarContrasenaButton = new JButton();
        cambiarContrasenaButton.setPreferredSize(new Dimension(250, 60));
        cambiarContrasenaButton.setText("Cambiar contraseña");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(cambiarContrasenaButton, gbc);
        labelGestionPerfil = new JLabel();
        labelGestionPerfil.setHorizontalAlignment(0);
        labelGestionPerfil.setHorizontalTextPosition(0);
        labelGestionPerfil.setText("Gestión de perfil");
        labelGestionPerfil.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelBotones.add(labelGestionPerfil, gbc);
    }
}
