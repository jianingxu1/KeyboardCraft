package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.util.*;

/**
 * Representa la interfaz gráfica para gestionar teclados. Permite crear,
 * modificar y visualizar diferentes disposiciones de teclado.
 * 
 * @autor Jianing Xu (jianing.xu@estudiantat.upc.edu)
 */
public class VistaGestionTeclados extends javax.swing.JFrame {
        /**
         * Controlador de presentación asociado a esta vista.
         */
        private CtrlPresentacion iCtrlPresentacion;

        /**
         * Booleano que indica si el usuario puede seleccionar un elemento del combobox.
         */
        private boolean userSelection = true;

        /**
         * Lista de botones que contiene los botones que representan las teclas del
         * teclado seleccionado.
         */
        private ArrayList<JButton> teclasBtn = new ArrayList<JButton>();

        /**
         * Constructor para inicializar la vista de gestion de teclados.
         * Establece la conexion con la capa de control y configura los componentes
         * iniciales de la interfaz grafica.
         * 
         * @param pCtrlPresentacion Controlador de la presentacion para la gestion de
         *                          teclados.
         */
        public VistaGestionTeclados(CtrlPresentacion pCtrlPresentacion) {
                iCtrlPresentacion = pCtrlPresentacion;
                initComponents();
                inicializar_frameVista();
                resetCardCrearTeclado();
                mostrarCardCrearTeclado();
        }

        /**
         * Hace visible la vista de gestion de teclados.
         */
        public void hacerVisible() {
                pack();
                setVisible(true);
                resetCardCrearTeclado();
                mostrarCardCrearTeclado();
        }

        /**
         * Oculta la vista de gestion de teclados.
         */
        public void hacerInvisible() {
                setVisible(false);
        }

        /**
         * Activa la vista de gestion de teclados.
         */
        public void activar() {
                setEnabled(true);
        }

        /**
         * Desactiva la vista de gestion de teclados.
         */
        public void desactivar() {
                setEnabled(false);
        }

        /**
         * Inicializa el frame de la vista.
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

                jLabel2 = new javax.swing.JLabel();
                panelCard = new javax.swing.JPanel();
                panelCrear = new javax.swing.JPanel();
                btnCrearTeclado = new javax.swing.JButton();
                labelTituloCrear = new javax.swing.JLabel();
                panelInputCrearLista = new javax.swing.JPanel();
                btnCargarLista = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                areaPalabrasConFrecuencia = new javax.swing.JTextArea();
                labelPalabrasConFrecuencia = new javax.swing.JLabel();
                labelEjemploLista = new javax.swing.JLabel();
                labelInfoInputLista = new javax.swing.JLabel();
                panelInputCrearTexto = new javax.swing.JPanel();
                jScrollPane2 = new javax.swing.JScrollPane();
                areaTexto = new javax.swing.JTextArea();
                labelTexto = new javax.swing.JLabel();
                labelEjemploTexto = new javax.swing.JLabel();
                btnCargarTexto = new javax.swing.JButton();
                labelInfoInputTexto = new javax.swing.JLabel();
                panelInputCrearTop = new javax.swing.JPanel();
                labelNombreTeclado = new javax.swing.JLabel();
                labelAlfabeto = new javax.swing.JLabel();
                labelAlgoritmo = new javax.swing.JLabel();
                comboboxAlgoritmo = new javax.swing.JComboBox<>();
                comboboxAlfabeto = new javax.swing.JComboBox<>();
                fieldNombreTeclado = new javax.swing.JTextField();
                panelModificar = new javax.swing.JPanel();
                panelInputModificarTop = new javax.swing.JPanel();
                labelTituloModificar = new javax.swing.JLabel();
                panelContainerTeclas = new javax.swing.JPanel();
                panelTeclas = new javax.swing.JPanel();
                comboboxTeclado = new javax.swing.JComboBox<>();
                labelTeclado = new javax.swing.JLabel();
                panelInputModificarBot = new javax.swing.JPanel();
                btnEliminarTeclado = new javax.swing.JButton();
                btnIntercambiarTeclas = new javax.swing.JButton();
                fieldCaracter2 = new javax.swing.JTextField();
                fieldCaracter1 = new javax.swing.JTextField();
                labelCaracter1 = new javax.swing.JLabel();
                labelCaracter2 = new javax.swing.JLabel();
                menubarAcciones = new javax.swing.JMenuBar();
                menuVolver = new javax.swing.JMenu();
                menuCrear = new javax.swing.JMenu();
                menuModificar = new javax.swing.JMenu();

                jLabel2.setText("Intercambiar teclas:");

                setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                setTitle("KeyboardCraft - Gestión de teclados");
                setMinimumSize(new java.awt.Dimension(500, 400));

                panelCard.setLayout(new java.awt.CardLayout());

                panelCrear.setPreferredSize(new java.awt.Dimension(540, 440));

                btnCrearTeclado.setText("Crear teclado");
                btnCrearTeclado.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCrearTecladoActionPerformed(evt);
                        }
                });

                labelTituloCrear.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
                labelTituloCrear.setText("Creación de teclado");

                btnCargarLista.setText("Cargar lista");
                btnCargarLista.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCargarListaActionPerformed(evt);
                        }
                });

                areaPalabrasConFrecuencia.setColumns(20);
                areaPalabrasConFrecuencia.setRows(5);
                jScrollPane1.setViewportView(areaPalabrasConFrecuencia);

                labelPalabrasConFrecuencia.setText("Lista de palabras con frecuencia:");

                labelEjemploLista.setForeground(new java.awt.Color(100, 100, 100));
                labelEjemploLista.setText("Ejemplo: \"mesa 15 cama 100 reloj 3 ...\"");

                labelInfoInputLista.setText("Puedes introducir manualmente o importar la lista mediante un fichero.");

                javax.swing.GroupLayout panelInputCrearListaLayout = new javax.swing.GroupLayout(panelInputCrearLista);
                panelInputCrearLista.setLayout(panelInputCrearListaLayout);
                panelInputCrearListaLayout.setHorizontalGroup(
                                panelInputCrearListaLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelInfoInputLista)
                                                .addGroup(panelInputCrearListaLayout.createSequentialGroup()
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                330,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnCargarLista))
                                                .addGroup(panelInputCrearListaLayout.createSequentialGroup()
                                                                .addComponent(labelPalabrasConFrecuencia)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelEjemploLista)));
                panelInputCrearListaLayout.setVerticalGroup(
                                panelInputCrearListaLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputCrearListaLayout.createSequentialGroup()
                                                                .addGroup(panelInputCrearListaLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelPalabrasConFrecuencia)
                                                                                .addComponent(labelEjemploLista,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                24,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelInputCrearListaLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                0,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(btnCargarLista,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                38,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelInfoInputLista)
                                                                .addContainerGap()));

                areaTexto.setColumns(20);
                areaTexto.setRows(5);
                jScrollPane2.setViewportView(areaTexto);

                labelTexto.setText("Texto:");

                labelEjemploTexto.setForeground(new java.awt.Color(100, 100, 100));
                labelEjemploTexto.setText("Ejemplo: \"erase una vez ...\"");

                btnCargarTexto.setText("Cargar texto");
                btnCargarTexto.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCargarTextoActionPerformed(evt);
                        }
                });

                labelInfoInputTexto.setText("Puedes introducir manualmente o importar el texto mediante un fichero.");

                javax.swing.GroupLayout panelInputCrearTextoLayout = new javax.swing.GroupLayout(panelInputCrearTexto);
                panelInputCrearTexto.setLayout(panelInputCrearTextoLayout);
                panelInputCrearTextoLayout.setHorizontalGroup(
                                panelInputCrearTextoLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelInfoInputTexto)
                                                .addGroup(panelInputCrearTextoLayout.createSequentialGroup()
                                                                .addGroup(panelInputCrearTextoLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelInputCrearTextoLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(labelTexto)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(labelEjemploTexto)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(jScrollPane2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                322,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnCargarTexto)));
                panelInputCrearTextoLayout.setVerticalGroup(
                                panelInputCrearTextoLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputCrearTextoLayout.createSequentialGroup()
                                                                .addGroup(panelInputCrearTextoLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelTexto)
                                                                                .addComponent(labelEjemploTexto,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                24,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(panelInputCrearTextoLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelInputCrearTextoLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jScrollPane2,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                66,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                panelInputCrearTextoLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(btnCargarTexto,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(20, 20, 20)))
                                                                .addComponent(labelInfoInputTexto)));

                labelNombreTeclado.setText("Nombre del teclado:");

                labelAlfabeto.setText("Alfabeto a utilizar:");

                labelAlgoritmo.setText("Algoritmo a utilizar:");

                comboboxAlgoritmo.setMaximumRowCount(10);

                comboboxAlfabeto.setMaximumRowCount(10);

                fieldNombreTeclado.setColumns(10);

                javax.swing.GroupLayout panelInputCrearTopLayout = new javax.swing.GroupLayout(panelInputCrearTop);
                panelInputCrearTop.setLayout(panelInputCrearTopLayout);
                panelInputCrearTopLayout.setHorizontalGroup(
                                panelInputCrearTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelInputCrearTopLayout.createSequentialGroup()
                                                                                .addGroup(panelInputCrearTopLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(labelNombreTeclado)
                                                                                                .addComponent(labelAlfabeto))
                                                                                .addGroup(panelInputCrearTopLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addGroup(panelInputCrearTopLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(11, 11, 11)
                                                                                                                .addComponent(comboboxAlfabeto,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                131,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(panelInputCrearTopLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(fieldNombreTeclado))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelInputCrearTopLayout.createSequentialGroup()
                                                                                .addComponent(labelAlgoritmo)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(comboboxAlgoritmo,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                131,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                panelInputCrearTopLayout.setVerticalGroup(
                                panelInputCrearTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputCrearTopLayout.createSequentialGroup()
                                                                .addGroup(panelInputCrearTopLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelNombreTeclado)
                                                                                .addComponent(fieldNombreTeclado,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelInputCrearTopLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelAlfabeto)
                                                                                .addComponent(comboboxAlfabeto,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelInputCrearTopLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(labelAlgoritmo)
                                                                                .addComponent(comboboxAlgoritmo,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));

                javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
                panelCrear.setLayout(panelCrearLayout);
                panelCrearLayout.setHorizontalGroup(
                                panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelCrearLayout.createSequentialGroup()
                                                                .addGroup(panelCrearLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(panelCrearLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(69, 69, 69)
                                                                                                .addGroup(panelCrearLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(panelCrearLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(150, 150,
                                                                                                                                                150)
                                                                                                                                .addComponent(labelTituloCrear))
                                                                                                                .addGroup(panelCrearLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                panelCrearLayout.createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addGroup(panelCrearLayout
                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                panelInputCrearTexto,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                2,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addComponent(panelInputCrearLista,
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addGroup(panelCrearLayout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addGap(103, 103,
                                                                                                                                                                103)
                                                                                                                                                .addComponent(panelInputCrearTop,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                86,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                .addGroup(panelCrearLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(229, 229, 229)
                                                                                                .addComponent(btnCrearTeclado)))
                                                                .addContainerGap(73, Short.MAX_VALUE)));
                panelCrearLayout.setVerticalGroup(
                                panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelCrearLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(labelTituloCrear)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(panelInputCrearTop,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(panelInputCrearTexto,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(panelInputCrearLista,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnCrearTeclado,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                41,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(19, Short.MAX_VALUE)));

                panelCard.add(panelCrear, "cardCrear");

                labelTituloModificar.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
                labelTituloModificar.setText("Consulta de teclado");

                panelTeclas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                panelTeclas.setPreferredSize(new java.awt.Dimension(320, 150));
                panelTeclas.setLayout(new java.awt.GridLayout(1, 0));

                comboboxTeclado.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                comboboxTecladoActionPerformed(evt);
                        }
                });

                labelTeclado.setText("Teclado:");

                javax.swing.GroupLayout panelContainerTeclasLayout = new javax.swing.GroupLayout(panelContainerTeclas);
                panelContainerTeclas.setLayout(panelContainerTeclasLayout);
                panelContainerTeclasLayout.setHorizontalGroup(
                                panelContainerTeclasLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelContainerTeclasLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap(62, Short.MAX_VALUE)
                                                                                .addGroup(panelContainerTeclasLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(panelTeclas,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                330,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(panelContainerTeclasLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(62, 62, 62)
                                                                                                                .addComponent(labelTeclado)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(comboboxTeclado,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                149,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGap(54, 54, 54)));
                panelContainerTeclasLayout.setVerticalGroup(
                                panelContainerTeclasLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelContainerTeclasLayout.createSequentialGroup()
                                                                                .addGroup(panelContainerTeclasLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(labelTeclado)
                                                                                                .addComponent(comboboxTeclado,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                10,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(panelTeclas,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                javax.swing.GroupLayout panelInputModificarTopLayout = new javax.swing.GroupLayout(
                                panelInputModificarTop);
                panelInputModificarTop.setLayout(panelInputModificarTopLayout);
                panelInputModificarTopLayout.setHorizontalGroup(
                                panelInputModificarTopLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputModificarTopLayout.createSequentialGroup()
                                                                .addContainerGap(80, Short.MAX_VALUE)
                                                                .addGroup(panelInputModificarTopLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                panelInputModificarTopLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(panelContainerTeclas,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(74, 74, 74))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                panelInputModificarTopLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(labelTituloModificar)
                                                                                                                .addGap(211, 211,
                                                                                                                                211)))));
                panelInputModificarTopLayout.setVerticalGroup(
                                panelInputModificarTopLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputModificarTopLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(labelTituloModificar)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                34,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(panelContainerTeclas,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                btnEliminarTeclado.setText("Eliminar teclado");
                btnEliminarTeclado.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarTecladoActionPerformed(evt);
                        }
                });

                btnIntercambiarTeclas.setText("Intercambiar posicion");
                btnIntercambiarTeclas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnIntercambiarTeclasActionPerformed(evt);
                        }
                });

                fieldCaracter2.setColumns(2);

                fieldCaracter1.setColumns(2);

                labelCaracter1.setText("Caracter 1:");

                labelCaracter2.setText("Caracter 2:");

                javax.swing.GroupLayout panelInputModificarBotLayout = new javax.swing.GroupLayout(
                                panelInputModificarBot);
                panelInputModificarBot.setLayout(panelInputModificarBotLayout);
                panelInputModificarBotLayout.setHorizontalGroup(
                                panelInputModificarBotLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelInputModificarBotLayout.createSequentialGroup()
                                                                .addComponent(btnIntercambiarTeclas)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnEliminarTeclado))
                                                .addGroup(panelInputModificarBotLayout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addGroup(panelInputModificarBotLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(labelCaracter2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                68,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(labelCaracter1,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelInputModificarBotLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(fieldCaracter2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(fieldCaracter1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))));
                panelInputModificarBotLayout.setVerticalGroup(
                                panelInputModificarBotLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelInputModificarBotLayout.createSequentialGroup()
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(panelInputModificarBotLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(fieldCaracter1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(labelCaracter1))
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(panelInputModificarBotLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(labelCaracter2)
                                                                                                .addComponent(fieldCaracter2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(panelInputModificarBotLayout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(btnEliminarTeclado,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                51,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btnIntercambiarTeclas,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                51,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addContainerGap()));

                javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
                panelModificar.setLayout(panelModificarLayout);
                panelModificarLayout.setHorizontalGroup(
                                panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelInputModificarTop,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                panelModificarLayout
                                                                                .createSequentialGroup()
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(panelInputModificarBot,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(128, 128, 128)));
                panelModificarLayout.setVerticalGroup(
                                panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelModificarLayout.createSequentialGroup()
                                                                .addComponent(panelInputModificarTop,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(panelInputModificarBot,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(36, Short.MAX_VALUE)));

                panelCard.add(panelModificar, "cardModificar");
                panelModificar.getAccessibleContext().setAccessibleName("");
                panelModificar.getAccessibleContext().setAccessibleDescription("");

                menuVolver.setText("<- Ir a menú principal");
                menuVolver.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                menuVolverMouseClicked(evt);
                        }
                });
                menubarAcciones.add(menuVolver);

                menuCrear.setText("Crear teclado");
                menuCrear.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                menuCrearMouseClicked(evt);
                        }
                });
                menubarAcciones.add(menuCrear);

                menuModificar.setText("Consultar o modificar teclado");
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
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelCard, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 428,
                                                                Short.MAX_VALUE));

                pack();
        }// </editor-fold>

        /**
         * Acción ejecutada al hacer clic en el botón "Cargar Lista". Abre un cuadro de
         * diálogo para seleccionar un archivo y carga una lista de palabras.
         */
        private void btnCargarListaActionPerformed(java.awt.event.ActionEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(this);
                if (option == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        try {
                                String texto = iCtrlPresentacion.importarListaPalabras(filePath);
                                areaPalabrasConFrecuencia.setText(texto);
                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                }
        }

        /**
         * Acción ejecutada al hacer clic en el botón "Cargar Texto". Abre un cuadro de
         * diálogo para seleccionar un archivo y carga un texto.
         */
        private void btnCargarTextoActionPerformed(java.awt.event.ActionEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(this);
                if (option == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String filePath = selectedFile.getAbsolutePath();
                        try {
                                String texto = iCtrlPresentacion.importarTexto(filePath);
                                areaTexto.setText(texto);
                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                }
        }

        /**
         * Acción ejecutada al hacer clic en el botón "Intercambiar Teclas". Realiza el
         * intercambio de dos teclas en del teclado seleccionado.
         */
        private void btnIntercambiarTeclasActionPerformed(java.awt.event.ActionEvent evt) {
                String input1 = fieldCaracter1.getText();
                String input2 = fieldCaracter2.getText();

                if (input1 == null || input2 == null || input1.trim().isEmpty() || input2.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                        "Debes seleccionar o introducir dos teclas para intercambiar.");
                        return;
                }
                if (input1.length() > 1 || input2.length() > 1) {
                        JOptionPane.showMessageDialog(this, "Introduce solo un carácter.");
                        return;
                }
                char caracter1 = input1.charAt(0);
                char caracter2 = input2.charAt(0);
                try {
                        iCtrlPresentacion.intercambiarTeclasTeclado((String) comboboxTeclado.getSelectedItem(),
                                        caracter1,
                                        caracter2);
                        // Intercambiar las teclas en el panel
                        for (JButton tecla : teclasBtn) {
                                if (tecla.getText().equals(input1)) {
                                        tecla.setText(input2);
                                } else if (tecla.getText().equals(input2)) {
                                        tecla.setText(input1);
                                }
                        }
                        // panelTeclas.revalidate();
                        // panelTeclas.repaint();
                        // Limpiar los campos de texto
                        fieldCaracter1.setText("");
                        fieldCaracter2.setText("");
                        JOptionPane.showMessageDialog(this,
                                        "¡Se ha intercambiado la posición de las teclas " + caracter1 + " y "
                                                        + caracter2 + " con éxito!");
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                }
        }

        /**
         * Acción ejecutada al hacer clic en el botón "Eliminar Teclado". Elimina el
         * teclado seleccionado.
         */
        private void btnEliminarTecladoActionPerformed(java.awt.event.ActionEvent evt) {
                String nombreTeclado = (String) comboboxTeclado.getSelectedItem();
                if (nombreTeclado == null) {
                        JOptionPane.showMessageDialog(this, "No hay ningún teclado seleccionado.");
                        return;
                }
                int result = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar el teclado?",
                                "Confirmación",
                                JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                        try {
                                iCtrlPresentacion.eliminarTeclado(nombreTeclado);
                                actualizarNombreTecladosComboBox();
                                panelTeclas.removeAll();
                                fieldCaracter1.setText("");
                                fieldCaracter2.setText("");
                                panelTeclas.revalidate();
                                panelTeclas.repaint();
                                JOptionPane.showMessageDialog(this,
                                                "¡Se ha eliminado el teclado " + nombreTeclado + " con éxito!");
                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                }
        }

        /**
         * Acción ejecutada al hacer clic en el botón "Crear Teclado". Crea un nuevo
         * teclado con los datos proporcionados.
         */
        private void btnCrearTecladoActionPerformed(java.awt.event.ActionEvent evt) {
                String nombreTeclado = fieldNombreTeclado.getText();
                String nombreAlfabeto = (String) comboboxAlfabeto.getSelectedItem();
                String nombreAlgoritmo = (String) comboboxAlgoritmo.getSelectedItem();
                String texto = areaTexto.getText();
                String palabrasConFrecuencia = areaPalabrasConFrecuencia.getText();
                try {
                        iCtrlPresentacion.crearTeclado(nombreTeclado, nombreAlfabeto, texto, palabrasConFrecuencia,
                                        nombreAlgoritmo);
                        resetCardCrearTeclado();
                        JOptionPane.showMessageDialog(this,
                                        "¡Se ha creado el teclado " + nombreTeclado + " con éxito!");
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                }
        }

        /**
         * Acción ejecutada al hacer clic en el menú "Modificar". Muestra el panel de
         * "Modificar Teclado".
         */
        private void menuModificarMouseClicked(java.awt.event.MouseEvent evt) {
                resetCardModificarTeclado();
                mostrarCardModificarTeclado();
        }

        /**
         * Acción ejecutada al hacer clic en el menú "Crear". Muestra el panel de "Crear
         * Teclado".
         */
        private void menuCrearMouseClicked(java.awt.event.MouseEvent evt) {
                resetCardCrearTeclado();
                mostrarCardCrearTeclado();
        }

        /**
         * Acción ejecutada al hacer clic en el menú "Volver". Regresa al menú principal
         * desde la vista de gestión de teclados.
         */
        private void menuVolverMouseClicked(java.awt.event.MouseEvent evt) {
                iCtrlPresentacion.syncVistaGestionTeclados_a_MenuPrincipal();
        }

        /**
         * Actualiza la lista de teclados disponibles en el combobox.
         */
        private void comboboxTecladoActionPerformed(java.awt.event.ActionEvent evt) {
                if (!userSelection)
                        return;
                String nombreTeclado = (String) comboboxTeclado.getSelectedItem();
                try {
                        Character[][] distribucion = iCtrlPresentacion.getDistribucionTeclado(nombreTeclado);

                        // Quitar las teclas del teclado anterior del panel
                        panelTeclas.removeAll();
                        teclasBtn.clear();

                        // Poner las teclas del teclado actual en el panel como botones
                        int rows = distribucion.length;
                        int cols = distribucion[0].length;
                        panelTeclas.setLayout(new GridLayout(rows, cols));
                        for (int i = 0; i < rows; ++i) {
                                for (int j = 0; j < cols; ++j) {
                                        JButton button = new JButton();
                                        teclasBtn.add(button);

                                        if (distribucion[i][j] == null || distribucion[i][j] == ' ') {
                                                button.setEnabled(false);
                                        } else
                                                button.setText(distribucion[i][j].toString());

                                        // Establecer el estilo de los botones
                                        Dimension buttonSize = button.getPreferredSize();
                                        buttonSize.width = 30;
                                        buttonSize.height = 30;
                                        button.setPreferredSize(buttonSize);
                                        button.setMaximumSize(buttonSize);
                                        button.setMargin(new Insets(5, 5, 5, 5));
                                        // Añadir la acción de que cuando se clique el botón, se
                                        // ponga el caracter del botón en el campo de los caracteres
                                        // a intercambiar
                                        button.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                        if (fieldCaracter1.getText().isEmpty()) {
                                                                fieldCaracter1.setText(button.getText());
                                                        } else if (fieldCaracter2.getText().isEmpty()) {
                                                                fieldCaracter2.setText(button.getText());
                                                        } else {
                                                                fieldCaracter1.setText(button.getText());
                                                                fieldCaracter2.setText("");
                                                        }
                                                }
                                        });
                                        panelTeclas.add(button);
                                }
                        }
                        panelTeclas.revalidate();
                        panelTeclas.repaint();
                        fieldCaracter1.setText("");
                        fieldCaracter2.setText("");
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                }
        }

        /**
         * Muestra el panel "Crear Teclado" en la vista.
         */
        private void mostrarCardCrearTeclado() {
                CardLayout cardLayout = (CardLayout) (panelCard.getLayout());
                cardLayout.show(panelCard, "cardCrear");
        }

        /**
         * Muestra el panel "Modificar Teclado" en la vista.
         */
        private void mostrarCardModificarTeclado() {
                CardLayout cardLayout = (CardLayout) (panelCard.getLayout());
                cardLayout.show(panelCard, "cardModificar");
        }

        /**
         * Actualiza el combo box con los nombres de los alfabetos disponibles.
         */
        private void actualizarNombreAlfabetosComboBox() {
                comboboxAlfabeto.removeAllItems();
                ArrayList<String> nombreAlfabetos = iCtrlPresentacion.getNombreAlfabetos();
                for (String nombreAlfabeto : nombreAlfabetos) {
                        comboboxAlfabeto.addItem(nombreAlfabeto);
                }
                comboboxAlfabeto.setSelectedIndex(-1);
        }

        /**
         * Actualiza el combo box con los nombres de los teclados disponibles.
         */
        private void actualizarNombreTecladosComboBox() {
                userSelection = false;
                comboboxTeclado.removeAllItems();
                ArrayList<String> nombreTeclados = iCtrlPresentacion.getNombreTeclados();
                for (String nombreTeclado : nombreTeclados) {
                        comboboxTeclado.addItem(nombreTeclado);
                }
                comboboxTeclado.setSelectedIndex(-1);
                userSelection = true;
        }

        /**
         * Actualiza el combo box con los nombres de los algoritmos disponibles.
         */
        private void actualizarNombreAlgoritmosComboBox() {
                comboboxAlgoritmo.removeAllItems();
                ArrayList<String> nombreAlgoritmos = iCtrlPresentacion.getNombreAlgoritmos();
                for (String nombreAlgoritmo : nombreAlgoritmos) {
                        comboboxAlgoritmo.addItem(nombreAlgoritmo);
                }
                comboboxAlgoritmo.setSelectedIndex(-1);
        }

        /**
         * Restablece el panel "Crear Teclado" a su estado inicial.
         */
        private void resetCardCrearTeclado() {
                actualizarNombreAlfabetosComboBox();
                actualizarNombreAlgoritmosComboBox();
                fieldNombreTeclado.setText("");
                areaTexto.setText("");
                areaPalabrasConFrecuencia.setText("");
        }

        /**
         * Restablece el panel "Modificar Teclado" a su estado inicial.
         */
        private void resetCardModificarTeclado() {
                actualizarNombreTecladosComboBox();
                panelTeclas.removeAll();
                fieldCaracter1.setText("");
                fieldCaracter2.setText("");
        }

        // Variables declaration - do not modify
        /**
         * Area de texto para ingresar o mostrar el contenido de un archivo de texto.
         */
        private JTextArea areaTexto;

        /**
         * Area de texto para mostrar palabras con sus frecuencias.
         */
        private JTextArea areaPalabrasConFrecuencia;

        /**
         * Boton para cargar una lista de palabras desde un archivo.
         */
        private JButton btnCargarLista;

        /**
         * Boton para cargar un archivo de texto.
         */
        private JButton btnCargarTexto;

        /**
         * Boton para crear un nuevo teclado.
         */
        private JButton btnCrearTeclado;

        /**
         * Boton para eliminar un teclado existente.
         */
        private JButton btnEliminarTeclado;

        /**
         * Boton para intercambiar dos teclas en un teclado.
         */
        private JButton btnIntercambiarTeclas;

        /**
         * Cuadro desplegable para seleccionar un alfabeto.
         */
        private JComboBox<String> comboboxAlfabeto;

        /**
         * Cuadro desplegable para seleccionar un algoritmo.
         */
        private JComboBox<String> comboboxAlgoritmo;

        /**
         * Cuadro desplegable para seleccionar un teclado.
         */
        private JComboBox<String> comboboxTeclado;

        /**
         * Campo de texto para introducir el primer caracter a intercambiar.
         */
        private JTextField fieldCaracter1;

        /**
         * Campo de texto para introducir el segundo caracter a intercambiar.
         */
        private JTextField fieldCaracter2;

        /**
         * Campo de texto para ingresar el nombre de un nuevo teclado.
         */
        private JTextField fieldNombreTeclado;

        /**
         * Etiqueta de texto.
         */
        private JLabel jLabel2;

        /**
         * Panel de desplazamiento para el area de texto de entrada.
         */
        private JScrollPane jScrollPane1;

        /**
         * Panel de desplazamiento para el area de palabras con frecuencias.
         */
        private JScrollPane jScrollPane2;

        /**
         * Etiqueta para el campo de seleccion del alfabeto.
         */
        private JLabel labelAlfabeto;

        /**
         * Etiqueta para el campo de seleccion del algoritmo.
         */
        private JLabel labelAlgoritmo;

        /**
         * Etiqueta para el campo del primer caracter a intercambiar.
         */
        private JLabel labelCaracter1;

        /**
         * Etiqueta para el campo del segundo caracter a intercambiar.
         */
        private JLabel labelCaracter2;

        /**
         * Etiqueta de ejemplo para la lista de palabras.
         */
        private JLabel labelEjemploLista;

        /**
         * Etiqueta de ejemplo para el texto.
         */
        private JLabel labelEjemploTexto;

        /**
         * Etiqueta de informacion para la entrada de la lista de palabras.
         */
        private JLabel labelInfoInputLista;

        /**
         * Etiqueta de informacion para la entrada de texto.
         */
        private JLabel labelInfoInputTexto;

        /**
         * Etiqueta para el campo del nombre del teclado.
         */
        private JLabel labelNombreTeclado;

        /**
         * Etiqueta para el area de palabras con frecuencias.
         */
        private JLabel labelPalabrasConFrecuencia;

        /**
         * Etiqueta para el campo de seleccion del teclado.
         */
        private JLabel labelTeclado;

        /**
         * Etiqueta para el area de texto.
         */
        private JLabel labelTexto;

        /**
         * Etiqueta de titulo para la creacion.
         */
        private JLabel labelTituloCrear;

        /**
         * Etiqueta de titulo para la modificacion.
         */
        private JLabel labelTituloModificar;

        /**
         * Menu para la accion de crear.
         */
        private JMenu menuCrear;

        /**
         * Menu para la accion de modificar.
         */
        private JMenu menuModificar;

        /**
         * Menu para la accion de volver.
         */
        private JMenu menuVolver;

        /**
         * Barra de menu que contiene los menus de acciones.
         */
        private JMenuBar menubarAcciones;

        /**
         * Panel que contiene las tarjetas (paneles) para mostrar diferentes vistas.
         */
        private JPanel panelCard;

        /**
         * Panel que contiene los botones para las teclas.
         */
        private JPanel panelContainerTeclas;

        /**
         * Panel para la creacion de teclados.
         */
        private JPanel panelCrear;

        /**
         * Panel de entrada para crear una lista de palabras.
         */
        private JPanel panelInputCrearLista;

        /**
         * Panel de entrada para crear un texto.
         */
        private JPanel panelInputCrearTexto;

        /**
         * Panel de entrada superior para la creacion.
         */
        private JPanel panelInputCrearTop;

        /**
         * Panel de entrada inferior para la modificacion.
         */
        private JPanel panelInputModificarBot;

        /**
         * Panel de entrada superior para la modificacion.
         */
        private JPanel panelInputModificarTop;

        /**
         * Panel para la modificacion de teclados.
         */
        private JPanel panelModificar;

        /**
         * Panel que contiene los botones de las teclas.
         */
        private JPanel panelTeclas;

        // End of variables declaration
}
