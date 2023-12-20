package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VistaBienvenida extends javax.swing.JFrame {
    private CtrlPresentacion iCtrlPresentacion;

    public VistaBienvenida(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        initComponents();
        initOthers();
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

    // Method to perform an operation before exiting
    private void performOperationBeforeExit() throws Exception {
        // Your operation logic goes here
        int result = JOptionPane.showConfirmDialog(this, "Seguro que quieres salir?", "Confirmation", JOptionPane.YES_NO_OPTION);
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenidos = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        fieldUsuario = new javax.swing.JTextField();
        labelContrasena = new javax.swing.JLabel();
        fieldContrasena = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        labelRegistrar = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KeyboardCraft");
        setMinimumSize(new java.awt.Dimension(500, 350));

        labelTitulo.setFont(new java.awt.Font("Lucida Grande", 1, 22)); // NOI18N
        labelTitulo.setText("KeyboardCraft");

        labelUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelUsuario.setText("Usuario:");

        fieldUsuario.setColumns(8);
        fieldUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        labelContrasena.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout panelContenidosLayout = new javax.swing.GroupLayout(panelContenidos);
        panelContenidos.setLayout(panelContenidosLayout);
        panelContenidosLayout.setHorizontalGroup(
            panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidosLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContenidosLayout.createSequentialGroup()
                        .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenidosLayout.createSequentialGroup()
                                .addComponent(btnIniciarSesion)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenidosLayout.createSequentialGroup()
                                    .addComponent(labelContrasena)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenidosLayout.createSequentialGroup()
                                    .addComponent(labelUsuario)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52))
                    .addGroup(panelContenidosLayout.createSequentialGroup()
                        .addComponent(labelTitulo)
                        .addGap(155, 155, 155))))
        );
        panelContenidosLayout.setVerticalGroup(
            panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelTitulo)
                .addGroup(panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenidosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContenidosLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelContrasena)
                            .addComponent(fieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIniciarSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>  

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
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

    private void labelRegistrarMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaBienvenida_a_CrearCuenta();
    }                                           

    private void labelRegistrarMouseEntered(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        labelRegistrar.setForeground(Color.BLUE); // Change text color on hover
    }                                           

    private void labelRegistrarMouseExited(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        labelRegistrar.setForeground(Color.BLACK); // Restore initial text color on exit
    } 

    private void initOthers() {
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
    }

    private void limpiarCamposInput() {
        fieldUsuario.setText("");
        fieldContrasena.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JPasswordField fieldContrasena;
    private javax.swing.JLabel labelContrasena;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelRegistrar;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelContenidos;
    // End of variables declaration//GEN-END:variables
    
}