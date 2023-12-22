package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VistaCrearCuenta
 * Representa la interfaz gráfica de la vista de crear cuenta.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class VistaCrearCuenta extends JFrame {
    /**
     * Controlador de presentación asociado a esta vista.
     */
    private CtrlPresentacion iCtrlPresentacion;

    /**
     * Constructor para inicializar la vista crear cuenta.
     * 
     * @param pCtrlPresentacion Controlador de la presentacion.
     */
    public VistaCrearCuenta(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        initComponents();
        inicializar_frameVista();
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
    private void inicializar_frameVista() {
        setResizable(false);

        // Posicion y operaciones por defecto
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

    /**
     * Inicializa los componentes de la interfaz.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panelContenidos = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelInput = new javax.swing.JPanel();
        fieldUsuario = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelContrasena = new javax.swing.JLabel();
        fieldContrasena = new javax.swing.JPasswordField();
        btnCrearCuenta = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KeyboardCraft - Creación de cuenta");
        setMinimumSize(new java.awt.Dimension(500, 350));

        labelTitulo.setFont(new java.awt.Font("Lucida Grande", 1, 22)); // NOI18N
        labelTitulo.setText("Creación de cuenta");

        fieldUsuario.setColumns(8);
        fieldUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        labelUsuario.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelUsuario.setText("Usuario:");

        labelContrasena.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelContrasena.setText("Contraseña:");

        fieldContrasena.setColumns(8);
        fieldContrasena.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N

        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelInputLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelInputLayout.createSequentialGroup()
                                                        .addComponent(labelContrasena)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(fieldContrasena,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInputLayout
                                                .createSequentialGroup()
                                                .addComponent(labelUsuario)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(panelInputLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnCrearCuenta, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVolver, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 125,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)));
        panelInputLayout.setVerticalGroup(
                panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelInputLayout.createSequentialGroup()
                                .addGroup(
                                        panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelUsuario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(labelContrasena)
                                                .addComponent(fieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE)));

        javax.swing.GroupLayout panelContenidosLayout = new javax.swing.GroupLayout(panelContenidos);
        panelContenidos.setLayout(panelContenidosLayout);
        panelContenidosLayout.setHorizontalGroup(
                panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContenidosLayout.createSequentialGroup()
                                .addContainerGap(181, Short.MAX_VALUE)
                                .addGroup(panelContenidosLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenidosLayout
                                                .createSequentialGroup()
                                                .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(182, 182, 182))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelContenidosLayout.createSequentialGroup()
                                                        .addComponent(labelTitulo)
                                                        .addGap(167, 167, 167)))));
        panelContenidosLayout.setVerticalGroup(
                panelContenidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContenidosLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(labelTitulo)
                                .addGap(40, 40, 40)
                                .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE)));

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
     * Transiciona de la vista crear cuenta a la vista de bienvenida.
     * 
     * @param evt
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCamposInput();
        iCtrlPresentacion.syncVistaCrearCuenta_a_Bienvenida();
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

    /**
     * Crea una nueva cuenta con los datos introducidos en los campos de texto.
     * 
     * @param evt El ActionEvent desencadenado por el clic en el botón
     */
    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {
        String user = fieldUsuario.getText();
        char[] passwordChars = fieldContrasena.getPassword();
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

    // Variables declaration - do not modify
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPasswordField fieldContrasena;
    private javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel labelContrasena;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelContenidos;
    private javax.swing.JPanel panelInput;
    // End of variables declaration

}