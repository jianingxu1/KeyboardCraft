package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VistaBienvenida
 * Representa la interfaz gráfica de la vista de bienvenida.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class VistaBienvenida extends javax.swing.JFrame {
    /**
     * Controlador de presentación asociado a esta vista.
     */
    private CtrlPresentacion iCtrlPresentacion;

    /**
     * Constructor para inicializar la vista bienvenida.
     * 
     * @param pCtrlPresentacion Controlador de la presentacion.
     */
    public VistaBienvenida(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        initComponents();
        initOthers();
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
     * Realiza las operaciones de confirmación y guardado de datos antes de cerrar
     * la aplicacion.
     * 
     * @throws Exception
     */
    private void performOperationBeforeExit() throws Exception {
        // Your operation logic goes here
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Perform cleanup or other necessary operations before exiting
            try {
                iCtrlPresentacion.guardarDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            this.dispose(); // Close the frame
            iCtrlPresentacion.cerrarPrograma();
        }
    }

    /**
     * Inicializa los componentes de la interfaz.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenidos = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        panelInput = new javax.swing.JPanel();
        fieldUsuario = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelContrasena = new javax.swing.JLabel();
        fieldContrasena = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        labelRegistrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KeyboardCraft");
        setMinimumSize(new java.awt.Dimension(500, 350));

        labelTitulo.setFont(new java.awt.Font("Lucida Grande", 1, 22)); // NOI18N
        labelTitulo.setText("KeyboardCraft");

        fieldUsuario.setColumns(8);
        fieldUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        labelUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelUsuario.setText("Usuario:");

        labelContrasena.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelContrasena.setText("Contraseña:");

        fieldContrasena.setColumns(8);
        fieldContrasena.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        labelRegistrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRegistrar.setText("<html><center>¿Aún no tienes cuenta?<br />Regístrate</center></html>");
        labelRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRegistrarMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelRegistrarMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelRegistrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInputLayout
                                .createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING,
                                        false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        panelInputLayout.createSequentialGroup()
                                                .addComponent(labelContrasena)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fieldContrasena,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                        panelInputLayout.createSequentialGroup()
                                                .addComponent(labelUsuario)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(fieldUsuario,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGroup(panelInputLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelInputLayout
                                                .createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(btnIniciarSesion))
                                        .addGroup(panelInputLayout
                                                .createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(labelRegistrar,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        173,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        panelInputLayout.setVerticalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGroup(
                                        panelInputLayout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(fieldUsuario,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelUsuario))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        panelInputLayout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(labelContrasena)
                                                .addComponent(fieldContrasena,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIniciarSesion,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(labelRegistrar,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        39,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        javax.swing.GroupLayout panelContenidosLayout = new javax.swing.GroupLayout(panelContenidos);
        panelContenidos.setLayout(panelContenidosLayout);
        panelContenidosLayout.setHorizontalGroup(
                panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContenidosLayout.createSequentialGroup()
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addGroup(panelContenidosLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelContenidosLayout
                                                        .createSequentialGroup()
                                                        .addComponent(labelLogo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                163,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(50, 50, 50)
                                                        .addComponent(panelInput,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(76, 76, 76))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelContenidosLayout
                                                        .createSequentialGroup()
                                                        .addComponent(labelTitulo)
                                                        .addGap(190, 190,
                                                                190)))));
        panelContenidosLayout.setVerticalGroup(
                panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContenidosLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(labelTitulo)
                                .addGap(39, 39, 39)
                                .addGroup(panelContenidosLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelLogo,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                156,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(95, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelContenidos, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelContenidos, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    /**
     * Inicia sesión con los datos introducidos en los campos de texto.
     * 
     * @param evt El ActionEvent desencadenado por el clic en el botón
     */
    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        String user = fieldUsuario.getText();
        char[] passwordChars = fieldContrasena.getPassword();
        String password = new String(passwordChars);
        try {
            iCtrlPresentacion.iniciarSesion(user, password);
            limpiarCamposInput();
            iCtrlPresentacion.syncVistaBienvenida_a_MenuPrincipal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Realiza la transición de Vista Bienvenida a Crear Cuenta.
     * 
     * @param evt El ActionEvent desencadenado por el clic en el botón
     */
    private void labelRegistrarMouseClicked(java.awt.event.MouseEvent evt) {
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaBienvenida_a_CrearCuenta();
    }

    /**
     * Cambia el color del texto del label al entrar con el ratón.
     * 
     * @param evt El ActionEvent desencadenado por el clic en el botón
     */
    private void labelRegistrarMouseEntered(java.awt.event.MouseEvent evt) {
        labelRegistrar.setForeground(Color.BLUE); // Change text color on hover
    }

    /**
     * Cambia el color del texto del label al salir con el ratón.
     * 
     * @param evt El ActionEvent desencadenado por el clic en el botón
     */
    private void labelRegistrarMouseExited(java.awt.event.MouseEvent evt) {
        labelRegistrar.setForeground(Color.BLACK); // Restore initial text color on exit
    }

    /**
     * Inicializa otros componentes de la interfaz.
     */
    private void initOthers() {
        setResizable(false);
        setLocationRelativeTo(null); // Center the window on the screen

        // Add a window listener to intercept the window-closing event
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

        // Replace "path/to/your/image.png" with the path to your image file
        ImageIcon originalIcon = new ImageIcon("../FONTS/main/java/presentation/Logo.png");
        Image originalImage = originalIcon.getImage();

        // Define maximum width and height
        int maxWidth = 150;
        int maxHeight = 90;

        // Resize the image while maintaining aspect ratio
        Image scaledImage = originalImage.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        labelLogo.setIcon(scaledIcon);
    }

    /*
     * Limpia los campos de texto de la interfaz.
     */
    private void limpiarCamposInput() {
        fieldUsuario.setText("");
        fieldContrasena.setText("");
        fieldUsuario.revalidate();
        fieldUsuario.repaint();
        fieldContrasena.revalidate();
        fieldContrasena.repaint();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JPasswordField fieldContrasena;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel labelContrasena;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelRegistrar;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelContenidos;
    private javax.swing.JPanel panelInput;
    // End of variables declaration

}
