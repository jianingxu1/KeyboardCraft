package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Clase VistaGestionAlfabetos
 * Representa la interfaz de usuario para la gestión de alfabetos.
 * Permite consultar, crear, modificar y eliminar alfabetos.
 * 
 * @author Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class VistaGestionAlfabetos extends javax.swing.JFrame {
    /**
     * Controlador de presentación asociado a esta vista.
     */
    private CtrlPresentacion iCtrlPresentacion;

    /**
     * Booleano que indica si el usuario puede seleccionar un elemento del combobox.
     */
    private boolean userSelection = true;

    /**
     * Constructor para inicializar la vista de gestion de alfabetos.
     * 
     * @param pCtrlPresentacion Controlador de la presentacion para la gestion de
     *                          alfabetos.
     */
    public VistaGestionAlfabetos(CtrlPresentacion pCtrlPresentacion) {
        iCtrlPresentacion = pCtrlPresentacion;
        initComponents();
        inicializar_frameVista();
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }

    /**
     * Hace visible la vista de gestion de alfabetos.
     */
    public void hacerVisible() {
        pack();
        setVisible(true);
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }

    /**
     * Oculta la vista de gestion de alfabetos.
     */
    public void hacerInvisible() {
        setVisible(false);
    }

    /**
     * Activa la vista de gestion de alfabetos.
     */
    public void activar() {
        setEnabled(true);
    }

    /**
     * Desactiva la vista de gestion de alfabetos.
     */
    public void desactivar() {
        setEnabled(false);
    }

    /**
     * Inicializa el frame de la vista.
     */
    private void inicializar_frameVista() {
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
     * Inicializa los componentes de la interfaz.
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

        labelTituloCrear1.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        labelTituloCrear1.setText("Consulta de alfabeto");

        labelCaracteresModificar.setText("Caracteres del alfabeto seleccionado:");

        areaCaracteresModificar.setColumns(20);
        areaCaracteresModificar.setRows(5);
        jScrollPane3.setViewportView(areaCaracteresModificar);

        labelInfoInputModificar.setForeground(new java.awt.Color(100, 100, 100));
        labelInfoInputModificar.setText(
                "<html>Si quieres modificar el alfabeto, reemplaza los caracteres que desees. Máx 30 caractéres distintos (si escribes más, se descartarán).</html>");

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
                panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout
                                .createSequentialGroup()
                                .addContainerGap(137, Short.MAX_VALUE)
                                .addGroup(panelModificarLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelModificarLayout.createSequentialGroup()
                                                        .addComponent(btnModificarAlfabeto)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnEliminarAlfabeto,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 158,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout
                                                .createSequentialGroup()
                                                .addGroup(panelModificarLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(labelCaracteresModificar)
                                                        .addGroup(panelModificarLayout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addComponent(jScrollPane3,
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelInfoInputModificar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 286,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(25, 25, 25))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarLayout
                                                .createSequentialGroup()
                                                .addComponent(labelAlfabeto)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboboxAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65))
                                        .addGroup(panelModificarLayout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addComponent(labelTituloCrear1)))
                                .addGap(129, 129, 129)));
        panelModificarLayout.setVerticalGroup(
                panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelModificarLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(labelTituloCrear1)
                                .addGap(34, 34, 34)
                                .addGroup(panelModificarLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelAlfabeto)
                                        .addComponent(comboboxAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(labelCaracteresModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelInfoInputModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelModificarLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnModificarAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEliminarAlfabeto, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(62, Short.MAX_VALUE)));

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

        labelTituloCrear.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        labelTituloCrear.setText("Creación de alfabeto");

        labelInfoInputCrear.setForeground(new java.awt.Color(100, 100, 100));
        labelInfoInputCrear.setText(
                "<html>Introduce los caracteres. Los repetidos y espacios no se considerarán. Máx 30 caractéres distintos (si escribes más, se descartarán).</html>");

        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(
                panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGroup(panelCrearLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCrearLayout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addGroup(panelCrearLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addComponent(jScrollPane2)
                                                        .addGroup(panelCrearLayout.createSequentialGroup()
                                                                .addComponent(labelCaracteres)
                                                                .addGap(130, 130, 130))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                panelCrearLayout.createSequentialGroup()
                                                                        .addComponent(labelNombreAlfabeto)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(fieldNombreAlfabeto,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(labelInfoInputCrear,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 274,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelCrearLayout.createSequentialGroup()
                                                .addGap(218, 218, 218)
                                                .addComponent(labelTituloCrear))
                                        .addGroup(panelCrearLayout.createSequentialGroup()
                                                .addGap(221, 221, 221)
                                                .addComponent(btnCrearAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(163, Short.MAX_VALUE)));
        panelCrearLayout.setVerticalGroup(
                panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCrearLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(labelTituloCrear)
                                .addGap(36, 36, 36)
                                .addGroup(panelCrearLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelNombreAlfabeto)
                                        .addComponent(fieldNombreAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(labelCaracteres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelInfoInputCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 77,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCrearAlfabeto, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE)));

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

        menuModificar.setText("Consultar o modificar alfabeto");
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
                        .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    /**
     * Acción ejecutada al hacer clic en el botón "Eliminar Alfabeto". Elimina el
     * alfabeto seleccionado.
     */
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

    /**
     * Acción ejecutada al hacer clic en el botón "Modificar Alfabeto". Modifica
     * el alfabeto seleccionado con los datos proporcionados.
     */
    private void btnModificarAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreAlfabeto = (String) comboboxAlfabeto.getSelectedItem();
        String caracteres = areaCaracteresModificar.getText();
        if (nombreAlfabeto == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún alfabeto seleccionado.");
            return;
        }
        try {
            iCtrlPresentacion.modificarCaracteresAlfabeto(nombreAlfabeto, caracteres);
            resetCardModificarAlfabeto();
            JOptionPane.showMessageDialog(this, "¡Se ha modificado el alfabeto " + nombreAlfabeto + " con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Acción ejecutada al hacer clic en el botón "Crear Alfabeto". Crea un nuevo
     * alfabeto con los datos proporcionados.
     */
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

    /**
     * Acción ejecutada al hacer clic en el menú "Modificar". Muestra el panel de
     * "Modificar
     * Alfabeto".
     */
    private void menuModificarMouseClicked(java.awt.event.MouseEvent evt) {
        resetCardModificarAlfabeto();
        mostrarCardModificarAlfabeto();
    }

    /**
     * Acción ejecutada al hacer clic en el menú "Crear". Muestra el panel de "Crear
     * Teclado".
     */
    private void menuCrearMouseClicked(java.awt.event.MouseEvent evt) {
        resetCardCrearAlfabeto();
        mostrarCardCrearAlfabeto();
    }

    /**
     * Acción ejecutada al seleccionar un elemento del combo box. Muestra los
     * caracteres del alfabeto seleccionado.
     * 
     * @param evt Evento de selección de un elemento del combo box.
     */
    private void comboboxAlfabetoActionPerformed(java.awt.event.ActionEvent evt) {
        if (!userSelection)
            return;
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

    /**
     * Acción ejecutada al hacer clic en el menú "Volver". Regresa al menú principal
     * desde la vista de gestión de alfabetos.
     */
    private void menuVolverMouseClicked(java.awt.event.MouseEvent evt) {
        iCtrlPresentacion.syncVistaGestionAlfabetos_a_MenuPrincipal();
    }

    /**
     * Muestra el panel "Crear Alfabeto" en la vista.
     */
    private void mostrarCardCrearAlfabeto() {
        CardLayout cardLayout = (CardLayout) (panelCard.getLayout());
        cardLayout.show(panelCard, "cardCrear");
    }

    /**
     * Muestra el panel "Consultar o modificar Alfabeto" en la vista.
     */
    private void mostrarCardModificarAlfabeto() {
        CardLayout cardLayout = (CardLayout) (panelCard.getLayout());
        cardLayout.show(panelCard, "cardModificar");
    }

    /**
     * Actualiza el combo box con los nombres de los alfabetos disponibles.
     */
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

    /**
     * Restablece los inputs del panel "Crear Alfabeto" a su estado inicial.
     */
    private void resetCardCrearAlfabeto() {
        fieldNombreAlfabeto.setText("");
        areaCaracteresCrear.setText("");
    }

    /**
     * Restablece los inputs del panel "Modificar Alfabeto" a su estado inicial.
     */
    private void resetCardModificarAlfabeto() {
        actualizarNombreAlfabetosComboBox();
        areaCaracteresModificar.setText("");
    }

    // Variables declaration - do not modify
    /**
     * Área de texto para ingresar caracteres al crear un alfabeto.
     */
    private JTextArea areaCaracteresCrear;

    /**
     * Área de texto para ingresar caracteres al modificar un alfabeto.
     */
    private JTextArea areaCaracteresModificar;

    /**
     * Botón para crear un alfabeto.
     */
    private JButton btnCrearAlfabeto;

    /**
     * Botón para eliminar un alfabeto.
     */
    private JButton btnEliminarAlfabeto;

    /**
     * Botón para modificar un alfabeto.
     */
    private JButton btnModificarAlfabeto;

    /**
     * Cuadro desplegable para seleccionar un alfabeto.
     */
    private JComboBox<String> comboboxAlfabeto;

    /**
     * Campo de texto para ingresar el nombre de un alfabeto.
     */
    private JTextField fieldNombreAlfabeto;

    /**
     * Barra de desplazamiento para el área de caracteres al crear un alfabeto.
     */
    private JScrollPane jScrollPane2;

    /**
     * Barra de desplazamiento para el área de caracteres al modificar un alfabeto.
     */
    private JScrollPane jScrollPane3;

    /**
     * Etiqueta que representa el texto "Alfabeto".
     */
    private JLabel labelAlfabeto;

    /**
     * Etiqueta que representa el texto "Caracteres".
     */
    private JLabel labelCaracteres;

    /**
     * Etiqueta que representa el texto "Caracteres del alfabeto seleccionado".
     */
    private JLabel labelCaracteresModificar;

    /**
     * Etiqueta de información para la entrada de caracteres al crear un alfabeto.
     */
    private JLabel labelInfoInputCrear;

    /**
     * Etiqueta de información para la entrada de caracteres al modificar un
     * alfabeto.
     */
    private JLabel labelInfoInputModificar;

    /**
     * Etiqueta que representa el texto "Nombre del alfabeto".
     */
    private JLabel labelNombreAlfabeto;

    /**
     * Etiqueta que representa el título "Creación de alfabeto".
     */
    private JLabel labelTituloCrear;

    /**
     * Etiqueta que representa el título "Consulta de alfabeto".
     */
    private JLabel labelTituloCrear1;

    /**
     * Menú de opciones para crear un alfabeto.
     */
    private JMenu menuCrear;

    /**
     * Menú de opciones para consultar o modificar un alfabeto.
     */
    private JMenu menuModificar;

    /**
     * Menú de opciones para volver al menú principal.
     */
    private JMenu menuVolver;

    /**
     * Barra de menú para las acciones de la ventana.
     */
    private JMenuBar menubarAcciones;

    /**
     * Panel principal que contiene las tarjetas de creación y modificación.
     */
    private JPanel panelCard;

    /**
     * Panel para la creación de alfabetos.
     */
    private JPanel panelCrear;

    /**
     * Panel para la modificación de alfabetos.
     */
    private JPanel panelModificar;

    // End of variables declaration
}
