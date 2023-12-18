package com.Veterinaria.CRUD;

import com.Veterinaria.bd.Duenos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class DuenosCRUD {

    private EntityManager entityManager;

    public DuenosCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crearDueno(Duenos dueno) {
        entityManager.getTransaction().begin();
        entityManager.persist(dueno);
        entityManager.getTransaction().commit();
    }

    public Duenos obtenerDuenoPorId(long id) {
        System.out.println(entityManager.find(Duenos.class, id));
        return entityManager.find(Duenos.class, id);
    }


    public void actualizarDueno(Duenos dueno) {
        entityManager.getTransaction().begin();
        entityManager.merge(dueno);
        entityManager.getTransaction().commit();
    }

    public void eliminarDueno(long id) {
        Duenos dueno = obtenerDuenoPorId(id);
        if (dueno != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(dueno);
            entityManager.getTransaction().commit();
        }
    }

    public long obtenerUltimoId() {
        // La consulta JPQL para obtener el último ID
        String jpql = "SELECT MAX(d.Id) FROM Duenos d";

        // Ejecutar la consulta
        Query query = entityManager.createQuery(jpql);
        Long ultimoId = (Long) query.getSingleResult();

        // Si no hay registros, devuelve 0 o un valor predeterminado según tus necesidades
        return ultimoId != null ? ultimoId : 0L;
    }

    public DefaultTableModel obtenerTableModel(String jpql) {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>();
        Query query = entityManager.createQuery(jpql);
        try {
            // Obtener los resultados y construir el modelo de tabla
            @SuppressWarnings("unchecked")
            List<Duenos> result = query.getResultList();
            for (Duenos dueno : result) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(dueno.getId()));
                row.add(dueno.getNombre());
                row.add(dueno.getDireccion());
                row.add(String.valueOf(dueno.getTelefono()));
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Agregar nombres de columnas
        columns.add("Id");
        columns.add("Nombre");
        columns.add("Dirección");
        columns.add("Teléfono");

        // Construir y devolver el modelo de tabla
        return new DefaultTableModel(data, columns);
    }

    public int actualizarCampo(long id, String nombreCampo, String nuevoValor) {
        EntityTransaction transaction = null;
        int consulta = 0;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            String jpql = "UPDATE Duenos d SET d." + nombreCampo + " = :nuevoValor WHERE d.Id = :id";
            System.out.println(jpql);
            consulta = entityManager.createQuery(jpql)
                    .setParameter("nuevoValor", nuevoValor)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return consulta;
    }

    public List<String> ListNombres() {
        String jpql = "SELECT m FROM Duenos m";
        Query query = entityManager.createQuery(jpql);
        List<String> salida = new LinkedList<>();
        List<Duenos> result =(List<Duenos>) query.getResultList();
        for (Duenos Duenos : result) {
            salida.add(Duenos.getNombre());
        }
        return salida;
    }

    public List<Long> ListId() {
        String jpql = "SELECT m FROM Duenos m";
        Query query = entityManager.createQuery(jpql);
        List<Long> salida = new LinkedList<>();
        List<Duenos> result =(List<Duenos>) query.getResultList();
        for (Duenos Duenos : result) {
            salida.add(Duenos.getId());
        }
        return salida;
    }
}


