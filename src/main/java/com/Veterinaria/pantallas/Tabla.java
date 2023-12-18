package com.Veterinaria.pantallas;

import com.Veterinaria.CRUD.DuenosCRUD;
import com.Veterinaria.CRUD.MascotasCRUD;
import com.Veterinaria.CRUD.VisitasCRUD;
import com.Veterinaria.principal.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Tabla extends JFrame {

    private JPanel contentPane, superior;
    private JTable tabla;

    public String getTablaDB() {
        return TablaDB;
    }

    private String TablaDB;
    private String[] Orders;
    private int punteroOrders = 0;
    private JTextField buscador;

    public Tabla(String TablaDB) {
        this.TablaDB = TablaDB;
        setUndecorated(true);
        contentPane = new JPanel(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int ancho_pantalla = screenSize.width;
        int alto_pantalla = screenSize.height;
        setSize(ancho_pantalla, alto_pantalla);
        setExtendedState(MAXIMIZED_BOTH);
        superior = new JPanel(new GridLayout(1, 5, 20, 0));
        JButton[] btnSup = new JButton[5];
        btnSup[0] = crearBoton("Ordenar: Id", "Naranja");
        btnSup[1] = null;
        btnSup[4] = crearBoton("Visitas", "Azul");
        btnSup[3] = crearBoton("Registrar", "Azul");
        btnSup[2] = crearBoton("Salir", "Rojo");
        btnSup[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.VistaPrincipal.setVisible(true);
            }
        });
        btnSup[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.VistaCache = new Registro();
                Main.VistaCache.setVisible(true);
            }
        });
        btnSup[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.VistaCache = new Tabla("Visitas");
                Main.VistaCache.setVisible(true);
                setVisible(false);
            }
        });
        btnSup[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                punteroOrders++;
                if (punteroOrders >= tabla.getModel().getColumnCount()) {
                    punteroOrders = 0;
                }
                btnSup[0].setText("Ordenar: " + Orders[punteroOrders]);
                cambiarOrden();

            }
        });
        for (JButton jButton : btnSup) {
            if (jButton != null) {
                superior.add(jButton);
            } else {
                if (superior.getComponentCount() == 1) {
                    buscador = new JTextField();
                    buscador.setFont(new Font("Arial", Font.PLAIN, 18));
                    buscador.addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            if (!buscador.getText().equalsIgnoreCase("")) {
                                tabla.setModel(plantilla("SELECT m FROM " + TablaDB + " m WHERE m."+Orders[punteroOrders]+" LIKE "+buscador.getText()));
                            }else{
                                tabla.setModel(plantilla("Select m from " + TablaDB + " m"));
                            }
                        }
                    });
                    superior.add(buscador);
                }
            }
        }
        superior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.add(superior, BorderLayout.NORTH);
        setContentPane(contentPane);
        tabla = generarTabla("Select m from " + TablaDB + " m");
        JScrollPane scrollPane = new JScrollPane(tabla);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void cambiarOrden() {
        String jpqlQuery = "SELECT t FROM " + TablaDB + " t ORDER BY t." + Orders[punteroOrders];
        tabla.setModel(plantilla(jpqlQuery));
    }

    private JTable generarTabla(String C) {
        DefaultTableModel tableModel = plantilla(C);
        JTable table = new JTable(tableModel);
        table.setGridColor(Color.WHITE);
        table.setSelectionForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 25));
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(true);
        table.setDefaultEditor(Object.class, new Renderceldas(table, this, TablaDB));
        Orders = new String[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++) {
            Orders[i] = table.getModel().getColumnName(i);
            System.out.println(table.getModel().getColumnName(i));
        }
        return table;
    }

    private DefaultTableModel plantilla(String C) {
        DefaultTableModel a = new DefaultTableModel();
        if (TablaDB.equalsIgnoreCase("Duenos")) {
            DuenosCRUD crud = new DuenosCRUD(Main.BD);
            a = crud.obtenerTableModel(C);
        } else if (TablaDB.equalsIgnoreCase("Visitas")) {
            VisitasCRUD crud = new VisitasCRUD(Main.BD);
            a = crud.obtenerTableModel(C);
        } else if (TablaDB.equalsIgnoreCase("Mascotas")) {
            MascotasCRUD crud = new MascotasCRUD(Main.BD);
            a = crud.obtenerTableModel(C);
        }
        Main.recargarEntityManager();
        return a;
    }

    private JButton crearBoton(String Nombre, String color) {
        JButton boton = new JButton(Nombre);
        boton.setPreferredSize(new Dimension(getWidth() / 3, 70));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        if (color.equalsIgnoreCase("Azul")) {
            boton.setBackground(new Color(27, 50, 68));
        } else if (color.equalsIgnoreCase("Rojo")) {
            boton.setBackground(new Color(227, 2, 2));
        } else if (color.equalsIgnoreCase("Naranja")) {
            boton.setBackground(new Color(245, 142, 7));
        }
        return boton;
    }

    public void change(int row, int colum) {
        int consulta = 0;
        TableModel aux = tabla.getModel();
        String cambio = (String) aux.getValueAt(row, colum);
        String columna = tabla.getColumnName(colum);
        cambio = quitarTildes(cambio);
        columna = quitarTildes(columna);
        long id = Long.parseLong((String) aux.getValueAt(row, 0));
        if (TablaDB.equalsIgnoreCase("Duenos")) {
            DuenosCRUD crud = new DuenosCRUD(Main.BD);
            crud.actualizarCampo(id, columna, cambio);
        } else if (TablaDB.equalsIgnoreCase("Visitas")) {
            VisitasCRUD crud = new VisitasCRUD(Main.BD);
            crud.actualizarCampo(id, columna, cambio);
        } else if (TablaDB.equalsIgnoreCase("Mascotas")) {
            MascotasCRUD crud = new MascotasCRUD(Main.BD);
            crud.actualizarCampo(id, columna, cambio);
        }
        Main.recargarEntityManager();
    }

    public static String quitarTildes(String cadena) {
        String cadenaNormalizada = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("[^\\p{ASCII}]");
        return patron.matcher(cadenaNormalizada).replaceAll("");
    }

}

