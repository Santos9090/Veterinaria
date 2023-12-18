package com.Veterinaria.pantallas;

import com.Veterinaria.principal.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Inicio extends JFrame {
    private JPanel contentPane, superior, centro;

    public Inicio() {
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
        btnSup[0] = crearBoton("Mascotas", "Azul");
        btnSup[1] = crearBoton("Dueños", "Azul");
        btnSup[2] = crearBoton("Salir", "Rojo");
        btnSup[3] = crearBoton("Registrar", "Azul");
        btnSup[4] = crearBoton("Visitas", "Azul");

        btnSup[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.VistaCache = new Tabla("Mascotas");
                Main.VistaCache.setVisible(true);
                setVisible(false);
            }
        });
        btnSup[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.VistaCache = new Tabla("Duenos");
                Main.VistaCache.setVisible(true);
                setVisible(false);
            }
        });
        btnSup[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
        for (JButton jButton : btnSup) {
            superior.add(jButton);
        }
        superior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.add(superior, BorderLayout.NORTH);
        centro = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(new ImageIcon(logo()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        centro.add(label, gbc);
        contentPane.add(centro, BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    private BufferedImage logo() {
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File("src/main/resources/img/logoVet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
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
        }
        return boton;
    }

}
