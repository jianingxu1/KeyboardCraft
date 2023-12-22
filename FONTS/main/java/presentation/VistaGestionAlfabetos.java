package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 *
 * @author jianingxu
 */
public class VistaGestionAlfabetos extends javax.swing.JFrame {
    private CtrlPresentacion iCtrlPresentacion;
    private boolean userSelection = true;
    
    public VistaGestionAlfabetos(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        initComponents();
        inicializar_frameVista();
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }
    
    public void hacerVisible() {
        pack();
        setVisible(true);
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }

    public void hacerInvisible() {
        setVisible(false);
    }

    public void activar() {
        setEnabled(true);
    }

    public void desactivar() {
        setEnabled(false);
    }
    
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelCard = new javax.swing.JPanel();
        panelModificar = new javax.swing.JPanel();
        btnEliminarAlfabeto = new javax.swing.JButton();
        btnModificarAlfabeto = new javax.swing.JButton();
        labelAlfabeto = new javax.swing.JLabel();
        comboboxAlfabeto = new javax.swing.JComboBox<>();
        labelTituloCrear1 = new javax.swing.JLabel();
        labelCaracteresModificar = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaCaracteresModificar = new javax.swing.JTextArea();
        labelInfoInputModificar = new javax.swing.JLabel();
        panelCrear = new javax.swing.JPanel();
        labelNombreAlfabeto = new javax.swing.JLabel();
        fieldNombreAlfabeto = new javax.swing.JTextField();
        labelCaracteres = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaCaracteresCrear = new javax.swing.JTextArea();
        btnCrearAlfabeto = new javax.swing.JButton();
        labelTituloCrear = new javax.swing.JLabel();
        labelInfoInputCrear = new javax.swing.JLabel();
        menubarAcciones = new javax.swing.JMenuBar();
        menuVolver = new javax.swing.JMenu();
        menuCrear = new javax.swing.JMenu();
        menuModificar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KeyboardCraft - Gestión de alfabetos");
        setMinimumSize(new java.awt.Dimension(500, 400));

        panelCard.setLayout(new java.awt.CardLayout());

        btnEliminarAlfabeto.setText("Eliminar alfabeto");
        btnEliminarAlfabeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlfabetoActionPerformed(evt);
            }
        });

        btnModificarAlfabeto.setText("Modificar alfabeto");
        btnModificarAlfabeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAlfabetoActionPerformed(evt);
            }
        });

        labelAlfabeto.setText("Alfabeto:");

        comboboxAlfabeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxAlfabetoActionPerformed(evt);
            }
        });

        labelTituloCrear1.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelTituloCrear1.setText("Modificación de alfabeto");

        labelCaracteresModificar.setText("Caracteres del alfabeto seleccionado:");

        areaCaracteresModificar.setColumns(20);
        areaCaracteresModificar.setRows(5);
        jScrollPane3.setViewportView(areaCaracteresModificar);

        labelInfoInputModificar.setForeground(new java.awt.Color(100, 100, 100));
        labelInfoInputModificar.setText("<html>Si quieres modificar el alfabeto, reemplaza los caracteres que desees. Máx 30 caractéres distintos (si escribes más, se descartarán).</html>");

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                        .addComponent(btnModificarAlfabeto)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                        .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCaracteresModificar)
                            .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelInfoInputModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout.createSequentialGroup()
                        .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTituloCrear1)
                            .addGroup(panelModificarLayout.createSequentialGroup()
                                .addComponent(labelAlfabeto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65)))
                .addGap(129, 129, 129))
        );
        panelModificarLayout.setVerticalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelTituloCrear1)
                .addGap(34, 34, 34)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAlfabeto)
                    .addComponent(comboboxAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelCaracteresModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfoInputModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarAlfabeto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        panelCard.add(panelModificar, "cardModificar");
        panelModificar.getAccessibleContext().setAccessibleName("");
        panelModificar.getAccessibleContext().setAccessibleDescription("");

        labelNombreAlfabeto.setText("Nombre del alfabeto:");

        fieldNombreAlfabeto.setColumns(10);

        labelCaracteres.setText("Caracteres del alfabeto:");

        areaCaracteresCrear.setColumns(20);
        areaCaracteresCrear.setRows(5);
        jScrollPane2.setViewportView(areaCaracteresCrear);

        btnCrearAlfabeto.setText("Crear alfabeto");
        btnCrearAlfabeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAlfabetoActionPerformed(evt);
            }
        });

        labelTituloCrear.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        labelTituloCrear.setText("Creación de alfabeto");

        labelInfoInputCrear.setForeground(new java.awt.Color(100, 100, 100));
        labelInfoInputCrear.setText("<html>Introduce los caracteres. Los repetidos y espacios no se considerarán. Máx 30 caractéres distintos (si escribes más, se descartarán).</html>");

        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2)
                                .addGroup(panelCrearLayout.createSequentialGroup()
                                    .addComponent(labelCaracteres)
                                    .addGap(130, 130, 130))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCrearLayout.createSequentialGroup()
                                    .addComponent(labelNombreAlfabeto)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(fieldNombreAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelInfoInputCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(btnCrearAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelCrearLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(labelTituloCrear)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        panelCrearLayout.setVerticalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrearLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(labelTituloCrear)
                .addGap(36, 36, 36)
                .addGroup(panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreAlfabeto)
                    .addComponent(fieldNombreAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelCaracteres)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInfoInputCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCrearAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        panelCard.add(panelCrear, "cardCrear");

        menuVolver.setText("<- Ir a menú principal");
        menuVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuVolverMouseClicked(evt);
            }
        });
        menubarAcciones.add(menuVolver);

        menuCrear.setText("Crear alfabeto");
        menuCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCrearMouseClicked(evt);
            }
        });
        menubarAcciones.add(menuCrear);

        menuModificar.setText("Modificar alfabeto");
        menuModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuModificarMouseClicked(evt);
            }
        });
        menubarAcciones.add(menuModificar);

        setJMenuBar(menubarAcciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>   

    private void btnEliminarAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        String nombreAlfabeto = (String) comboboxAlfabeto.getSelectedItem();
        if (nombreAlfabeto == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún alfabeto seleccionado.");
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar el alfabeto?", "Confirmación",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                iCtrlPresentacion.eliminarAlfabeto(nombreAlfabeto);
                resetCardModificarAlfabeto();
                JOptionPane.showMessageDialog(this, "¡Se ha eliminado el alfabeto " + nombreAlfabeto + " con éxito!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }                                                   

    private void btnModificarAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        String nombreAlfabeto = (String) comboboxAlfabeto.getSelectedItem();
        String caracteres = areaCaracteresModificar.getText();
        try {
            iCtrlPresentacion.modificarCaracteresAlfabeto(nombreAlfabeto, caracteres);
            resetCardModificarAlfabeto();
            JOptionPane.showMessageDialog(this, "¡Se ha modificado el alfabeto " + nombreAlfabeto + " con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }                                                    

    private void btnCrearAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        String nombreAlfabeto = fieldNombreAlfabeto.getText();
        String caracteres = areaCaracteresCrear.getText();
        try {
            iCtrlPresentacion.crearAlfabeto(nombreAlfabeto, caracteres);
            resetCardCrearAlfabeto();
            JOptionPane.showMessageDialog(this, "¡Se ha creado el alfabeto " + nombreAlfabeto + " con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }                                                

    private void menuModificarMouseClicked(java.awt.event.MouseEvent evt) {                                           
        resetCardModificarAlfabeto();
        mostrarCardModificarAlfabeto();
    }                                          

    private void menuCrearMouseClicked(java.awt.event.MouseEvent evt) {                                       
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }                                      

    private void comboboxAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        if (!userSelection) return;
        String nombreAlfabeto = (String) comboboxAlfabeto.getSelectedItem();
        try {
            ArrayList<Character> listaCaracteres = iCtrlPresentacion.getCaracteresDeAlfabeto(nombreAlfabeto);
            StringBuilder sb = new StringBuilder();
            for (Character c : listaCaracteres) {
                sb.append(c);
            }
            String caracteres = sb.toString();
            areaCaracteresModificar.setText(caracteres);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }                                                

    private void menuVolverMouseClicked(java.awt.event.MouseEvent evt) {                                        
        iCtrlPresentacion.syncVistaGestionAlfabetos_a_MenuPrincipal();
    }                                       

    private void mostrarCardCrearAlfabeto() {
        CardLayout cardLayout = (CardLayout)(panelCard.getLayout());
        cardLayout.show(panelCard, "cardCrear");
    }
    
    private void mostrarCardModificarAlfabeto() {
        CardLayout cardLayout = (CardLayout)(panelCard.getLayout());
        cardLayout.show(panelCard, "cardModificar");
    }
    
    private void actualizarNombreAlfabetosComboBox() {
        userSelection = false;
        comboboxAlfabeto.removeAllItems();
        ArrayList<String> nombreAlfabetos = iCtrlPresentacion.getNombreAlfabetos();
        for (String nombreAlfabeto : nombreAlfabetos) {
            comboboxAlfabeto.addItem(nombreAlfabeto);
        }
        comboboxAlfabeto.setSelectedIndex(-1);
        userSelection = true;
    }
    
    private void resetCardCrearAlfabeto() {
        fieldNombreAlfabeto.setText("");
        areaCaracteresCrear.setText("");
    }

    private void resetCardModificarAlfabeto() {
        actualizarNombreAlfabetosComboBox();
        areaCaracteresModificar.setText("");
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea areaCaracteresCrear;
    private javax.swing.JTextArea areaCaracteresModificar;
    private javax.swing.JButton btnCrearAlfabeto;
    private javax.swing.JButton btnEliminarAlfabeto;
    private javax.swing.JButton btnModificarAlfabeto;
    private javax.swing.JComboBox<String> comboboxAlfabeto;
    private javax.swing.JTextField fieldNombreAlfabeto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAlfabeto;
    private javax.swing.JLabel labelCaracteres;
    private javax.swing.JLabel labelCaracteresModificar;
    private javax.swing.JLabel labelInfoInputCrear;
    private javax.swing.JLabel labelInfoInputModificar;
    private javax.swing.JLabel labelNombreAlfabeto;
    private javax.swing.JLabel labelTituloCrear;
    private javax.swing.JLabel labelTituloCrear1;
    private javax.swing.JMenu menuCrear;
    private javax.swing.JMenu menuModificar;
    private javax.swing.JMenu menuVolver;
    private javax.swing.JMenuBar menubarAcciones;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelCrear;
    private javax.swing.JPanel panelModificar;
    // End of variables declaration                   
}
