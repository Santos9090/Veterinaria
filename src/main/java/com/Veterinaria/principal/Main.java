package com.Veterinaria.principal;

import com.Veterinaria.pantallas.Inicio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;


public class Main {
    public static final JFrame VistaPrincipal = new Inicio();
    public static EntityManager BD;
    public static JFrame VistaCache = null;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
        BD = entityManagerFactory.createEntityManager();
        VistaPrincipal.setVisible(true);
    }

    public static void recargarEntityManager() {
        // Cierra la instancia actual del EntityManager
        if (BD != null && BD.isOpen()) {
            BD.close();
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
        // Crea una nueva instancia del EntityManager
        BD = entityManagerFactory.createEntityManager();
    }
}
