import com.Veterinaria.bd.Visitas;
import com.Veterinaria.CRUD.VisitasCRUD;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Date;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestVisitasCRUD {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeClass
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void test1Crear() {
        VisitasCRUD visitasCrud = new VisitasCRUD(entityManager);
        Visitas visita = new Visitas();
        visita.setMotivo("Prueba");
        visita.setMascota(1);
        visita.setFecha(new Date(2000, 12, 12));
        visitasCrud.crearVisita(visita);
        assertNotNull(visita.getId());
        assertTrue(visita.getId() > 0);
    }

    @Test
    public void test2Buscar() {
        VisitasCRUD visitasCrud = new VisitasCRUD(entityManager);
        // Obtener el último ID utilizado
        long ultimoId = visitasCrud.obtenerUltimoId();
        // Buscar la visita por el último ID
        Visitas visitaObtenida = visitasCrud.obtenerVisitaPorId(ultimoId);
        assertNotNull(visitaObtenida);
        // Realizar aserciones adicionales según los atributos de la visita
    }

    @Test
    public void test3Actualizar() {
        // Agrega lógica para actualizar
    }

    public void test4EliminarVisita() {
        VisitasCRUD visitasCrud = new VisitasCRUD(entityManager);
        // Obtener el último ID utilizado
        long ultimoId = visitasCrud.obtenerUltimoId();
        // Eliminar la visita por el último ID
        visitasCrud.eliminarVisita(ultimoId);
        // Verificar que la visita ya no exista en la base de datos
        Visitas visitaEliminada = visitasCrud.obtenerVisitaPorId(ultimoId);
        assertNull(visitaEliminada);
    }
}
