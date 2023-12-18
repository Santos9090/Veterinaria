import com.Veterinaria.bd.Duenos;
import com.Veterinaria.CRUD.DuenosCRUD;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class TestDuenosCRUD {

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
        DuenosCRUD duenosCrud = new DuenosCRUD(entityManager);
        Duenos dueno = new Duenos();
        dueno.setNombre("Dueño de prueba");
        dueno.setDireccion("Dirección de prueba");
        dueno.setTelefono(123456789);
        duenosCrud.crearDueno(dueno);
        assertNotNull(dueno.getId());
        assertTrue(dueno.getId() > 0);

    }

    @Test
    public void test2Buscar() {
        DuenosCRUD duenosCrud = new DuenosCRUD(entityManager);
        Duenos duenoObtenido = duenosCrud.obtenerDuenoPorId(duenosCrud.obtenerUltimoId());
        assertNotNull(duenoObtenido);
        assertEquals("Dueño de prueba", duenoObtenido.getNombre());
    }



    @Test
    public void test3EliminarDueño(){
        DuenosCRUD duenosCrud = new DuenosCRUD(entityManager);
        // Obtener el último ID utilizado
        long ultimoId = duenosCrud.obtenerUltimoId();
        // Eliminar el dueño por el último ID
        duenosCrud.eliminarDueno(ultimoId);
        // Verificar que el dueño ya no exista en la base de datos
        Duenos duenoEliminado = duenosCrud.obtenerDuenoPorId(ultimoId);
        assertNull(duenoEliminado);
    }

}

