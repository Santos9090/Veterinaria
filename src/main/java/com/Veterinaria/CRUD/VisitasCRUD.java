package com.Veterinaria.CRUD;

import com.Veterinaria.bd.Visitas;
import com.Veterinaria.principal.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class VisitasCRUD {

    private EntityManager entityManager;

    public VisitasCRUD(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void crearVisita(Visitas visita) {
        entityManager.getTransaction().begin();
        entityManager.persist(visita);
        entityManager.getTransaction().commit();
    }

    public Visitas obtenerVisitaPorId(long id) {
        System.out.println(entityManager.find(Visitas.class, id));
        return entityManager.find(Visitas.class, id);
    }

    public void actualizarVisita(Visitas visita) {
        entityManager.getTransaction().begin();
        entityManager.merge(visita);
        entityManager.getTransaction().commit();
    }

    public void eliminarVisita(long id) {
        Visitas visita = obtenerVisitaPorId(id);
        if (visita != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(visita);
            entityManager.getTransaction().commit();
        }
    }

    public long obtenerUltimoId() {
        // La consulta JPQL para obtener el último ID
        String jpql = "SELECT MAX(v.Id) FROM Visitas v";

        // Ejecutar la consulta
        Query query = entityManager.createQuery(jpql);
        Long ultimoId = (Long) query.getSingleResult();

        // Si no hay registros, devuelve 0 o un valor predeterminado según tus necesidades
        return ultimoId != null ? ultimoId : 0L;
    }
    public DefaultTableModel obtenerTableModel(String jpql) {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>();
        MascotasCRUD aux= new MascotasCRUD(Main.BD);
        // Ejecutar la consulta
        Query query = entityManager.createQuery(jpql);
        try {
            // Obtener los resultados y construir el modelo de tabla
            @SuppressWarnings("unchecked")
            List<Visitas> result = query.getResultList();
            for (Visitas visita : result) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(visita.getId()));
                row.add(visita.getFecha().toString());
                row.add(visita.getMotivo());
                row.add(visita.getDiagnostico());
                row.add(aux.obtenerMascotaPorId(visita.getMascota()).getNombre());
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Agregar nombres de columnas
        columns.add("Id");
        columns.add("Fecha");
        columns.add("Motivo");
        columns.add("Diagnóstico");
        columns.add("Mascota");

        // Construir y devolver el modelo de tabla
        return new DefaultTableModel(data, columns);
    }
    public int actualizarCampo(long id, String nombreCampo, String nuevoValor) {
        EntityTransaction transaction = null;
        int consulta = 0;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            String jpql = "UPDATE Visitas d SET d." + nombreCampo + " = :nuevoValor WHERE d.Id = :id";
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
}

