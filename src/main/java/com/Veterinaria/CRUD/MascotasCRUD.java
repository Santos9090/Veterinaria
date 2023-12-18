package com.Veterinaria.CRUD;

import com.Veterinaria.bd.Mascotas;
import com.Veterinaria.principal.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class MascotasCRUD {

    private EntityManager entityManager;

    public MascotasCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crearMascota(Mascotas mascota) {
        entityManager.getTransaction().begin();
        entityManager.persist(mascota);
        entityManager.getTransaction().commit();
    }

    public Mascotas obtenerMascotaPorId(long id) {
        System.out.println(entityManager.find(Mascotas.class, id));
        return entityManager.find(Mascotas.class, id);
    }

    public void actualizarMascota(Mascotas mascota) {
        entityManager.getTransaction().begin();
        entityManager.merge(mascota);
        entityManager.getTransaction().commit();
    }

    public void eliminarMascota(long id) {
        Mascotas mascota = obtenerMascotaPorId(id);
        if (mascota != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(mascota);
            entityManager.getTransaction().commit();
        }
    }

    public long obtenerUltimoId() {
        // La consulta JPQL para obtener el último ID
        String jpql = "SELECT MAX(d.Id) FROM Mascotas d";

        // Ejecutar la consulta
        Query query = entityManager.createQuery(jpql);
        Long ultimoId = (Long) query.getSingleResult();

        // Si no hay registros, devuelve 0 o un valor predeterminado según tus necesidades
        return ultimoId != null ? ultimoId : 0L;
    }

    public DefaultTableModel obtenerTableModel(String jpql) {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>();

        // Execute the query
        Query query = entityManager.createQuery(jpql);
        try {
            DuenosCRUD aux = new DuenosCRUD(Main.BD);
            @SuppressWarnings("unchecked")
            List<Mascotas> result = query.getResultList();
            for (Mascotas mascota : result) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(mascota.getId()));
                row.add(mascota.getNombre());
                row.add(mascota.getTipo());
                row.add(mascota.getRaza());
                row.add(aux.obtenerDuenoPorId(mascota.getDueno()).getNombre());
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add column names
        columns.add("Id");
        columns.add("Nombre");
        columns.add("Tipo");
        columns.add("Raza");
        columns.add("Dueño");

        // Build and return the table model
        return new DefaultTableModel(data, columns);
    }

    public int actualizarCampo(long id, String nombreCampo, String nuevoValor) {
        EntityTransaction transaction = null;
        int consulta = 0;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            String jpql = "UPDATE Mascotas d SET d." + nombreCampo + " = :nuevoValor WHERE d.Id = :id";
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
        String jpql = "SELECT m FROM Mascotas m";
        Query query = entityManager.createQuery(jpql);
        List<String> salida = new LinkedList<>();
        List<Mascotas> result =(List<Mascotas>) query.getResultList();
        for (Mascotas mascota : result) {
            salida.add(mascota.getNombre());
        }
        return salida;
    }

    public List<Long> ListId() {
        String jpql = "SELECT m FROM Mascotas m";
        Query query = entityManager.createQuery(jpql);
        List<Long> salida = new LinkedList<>();
        List<Mascotas> result =(List<Mascotas>) query.getResultList();
        for (Mascotas mascota : result) {
            salida.add(mascota.getId());
        }
        return salida;
    }
}
