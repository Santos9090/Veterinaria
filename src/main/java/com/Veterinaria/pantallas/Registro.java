package com.Veterinaria.pantallas;

import com.Veterinaria.CRUD.DuenosCRUD;
import com.Veterinaria.CRUD.MascotasCRUD;
import com.Veterinaria.CRUD.VisitasCRUD;
import com.Veterinaria.bd.Duenos;
import com.Veterinaria.bd.Mascotas;
import com.Veterinaria.bd.Visitas;
import com.Veterinaria.principal.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Registro extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JSeparator separator_1;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JComboBox<String> Combo;
    private List<Long> IdsDelCombo;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JLabel lblNewLabel_8;
    private JLabel ErrorLBL;
    private List<Long> IdsDelCombo1;

    /**
     * Create the frame.
     */
    public Registro() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Calcular la posición para centrar la ventana
        int x = (screenSize.width - 1135) / 2;
        int y = (screenSize.height - 710) / 2;
        setAlwaysOnTop(true);
        // Establecer la ubicación de la ventana y el tamaño
        setBounds(x, y, 1135, 710);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("Registro Dueños");
        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(13, 11, 355, 96);
        contentPane.add(lblNewLabel);

        JLabel lblRegistroUsuarios = new JLabel("Registro Mascotas");
        lblRegistroUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistroUsuarios.setFont(new Font("Arial Black", Font.PLAIN, 30));
        lblRegistroUsuarios.setBounds(381, 7, 355, 104);
        contentPane.add(lblRegistroUsuarios);

        JLabel lblRegistroVisitas = new JLabel("Registro Visitas");
        lblRegistroVisitas.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistroVisitas.setFont(new Font("Arial Black", Font.PLAIN, 30));
        lblRegistroVisitas.setBounds(749, 9, 355, 101);
        contentPane.add(lblRegistroVisitas);

        textField = new JTextField();
        textField.setBounds(102, 152, 205, 31);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(102, 292, 205, 31);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(102, 432, 205, 31);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(477, 115, 205, -3);
        contentPane.add(textField_3);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(new Color(0, 0, 0));
        separator.setBackground(new Color(0, 0, 0));
        separator.setBounds(363, 0, 15, 671);
        contentPane.add(separator);

        separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setForeground(Color.BLACK);
        separator_1.setBackground(Color.BLACK);
        separator_1.setBounds(741, 0, 15, 671);
        contentPane.add(separator_1);

        lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setBounds(117, 127, 46, 14);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Direccion");
        lblNewLabel_2.setBounds(117, 271, 100, 14);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("Telefono");
        lblNewLabel_3.setBounds(117, 407, 70, 14);
        contentPane.add(lblNewLabel_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(453, 115, 205, 31);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(453, 250, 205, 31);
        contentPane.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(453, 385, 205, 31);
        contentPane.add(textField_6);

        Combo = new JComboBox<String>();
        Combo.setBounds(453, 520, 205, 31);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        DuenosCRUD crud = new DuenosCRUD(Main.BD);
        List<String> nombres = crud.ListNombres();
        IdsDelCombo = crud.ListId();
        model.addAll(nombres);
        Combo.setModel(model);
        contentPane.add(Combo);

        lblNewLabel_4 = new JLabel("Tipo");
        lblNewLabel_4.setBounds(397, 258, 46, 14);
        contentPane.add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("Nombre");
        lblNewLabel_5.setBounds(397, 122, 46, 14);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("Raza");
        lblNewLabel_6.setBounds(397, 393, 46, 14);
        contentPane.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("Dueño");
        lblNewLabel_7.setBounds(397, 528, 46, 14);
        contentPane.add(lblNewLabel_7);

        JLabel lblNewLabel_5_1 = new JLabel("Fecha");
        lblNewLabel_5_1.setBounds(783, 121, 46, 14);
        contentPane.add(lblNewLabel_5_1);

        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(871, 115, 205, 31);
        contentPane.add(textField_7);

        JLabel lblNewLabel_4_1 = new JLabel("Motivo");
        lblNewLabel_4_1.setBounds(783, 257, 46, 14);
        contentPane.add(lblNewLabel_4_1);

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(871, 250, 205, 31);
        contentPane.add(textField_8);

        JLabel lblNewLabel_6_1 = new JLabel("Diagnostico");
        lblNewLabel_6_1.setBounds(783, 392, 90, 14);
        contentPane.add(lblNewLabel_6_1);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(871, 385, 205, 31);
        contentPane.add(textField_9);

        JLabel lblNewLabel_7_1 = new JLabel("Mascota");
        lblNewLabel_7_1.setBounds(783, 527, 70, 14);
        contentPane.add(lblNewLabel_7_1);

        JComboBox<String> Combo_1 = new JComboBox<String>();
        Combo_1.setBounds(871, 520, 205, 31);
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>();
        MascotasCRUD crud1 = new MascotasCRUD(Main.BD);
        List<String> nombres1 = crud1.ListNombres();
        IdsDelCombo1 = crud1.ListId();
        model1.addAll(nombres1);
        Combo_1.setModel(model1);
        contentPane.add(Combo_1);

        JButton btnNewButton = new JButton("Registrar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DuenosCRUD aux = new DuenosCRUD(Main.BD);
                aux.crearDueno(new Duenos(textField.getText(), textField_1.getText(), Long.parseLong(textField_2.getText())));
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                setVisible(false);
            }
        });

        btnNewButton.setBounds(102, 520, 205, 37);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Registrar");
        btnNewButton_1.setBounds(453, 590, 205, 37);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MascotasCRUD aux = new MascotasCRUD(Main.BD);
                aux.crearMascota(new Mascotas(textField_4.getText(), textField_5.getText(), textField_6.getText(), IdsDelCombo.get(Combo.getSelectedIndex())));
                textField_4.setText("");
                textField_5.setText("");
                textField_6.setText("");
                setVisible(false);
            }
        });
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Registrar");
        btnNewButton_2.setBounds(871, 590, 205, 37);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisitasCRUD aux = new VisitasCRUD(Main.BD);
                String partes[] = textField_7.getText().split("/");
                aux.crearVisita(new Visitas(new java.sql.Date(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0])), textField_8.getText(), textField_9.getText(), IdsDelCombo1.get(Combo_1.getSelectedIndex())));
                textField_7.setText("");
                textField_8.setText("");
                textField_9.setText("");
                setVisible(false);
            }
        });
        contentPane.add(btnNewButton_2);
        lblNewLabel_8 = new JLabel("Formato: DD/MM/AAAA");
        lblNewLabel_8.setBounds(871, 93, 151, 14);
        contentPane.add(lblNewLabel_8);

        ErrorLBL = new JLabel();
        ErrorLBL.setForeground(new Color(255, 0, 0));
        ErrorLBL.setBounds(871, 160, 151, 14);
        contentPane.add(ErrorLBL);
    }


}
