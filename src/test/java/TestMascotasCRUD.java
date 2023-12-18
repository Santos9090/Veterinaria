import com.Veterinaria.bd.Mascotas;
import com.Veterinaria.CRUD.MascotasCRUD;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class TestMascotasCRUD {

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
        MascotasCRUD mascotasCrud = new MascotasCRUD(entityManager);
        Mascotas mascota = new Mascotas();
        mascota.setNombre("Mascota");
        mascota.setTipo("Tipo de prueba");
        mascota.setRaza("Raza de prueba");
        mascota.setDueno(1);
        mascotasCrud.crearMascota(mascota);
        assertNotNull(mascota.getId());
        assertTrue(mascota.getId() > 0);
    }

    @Test
    public void test2Buscar() {
        MascotasCRUD mascotasCrud = new MascotasCRUD(entityManager);
        Mascotas mascotaObtenida = mascotasCrud.obtenerMascotaPorId(mascotasCrud.obtenerUltimoId());
        assertNotNull(mascotaObtenida);
        assertEquals("Mascota", mascotaObtenida.getNombre());
    }

    @Test
    public void test3EliminarMascota() {
        MascotasCRUD mascotasCrud = new MascotasCRUD(entityManager);
        // Obtener el último ID utilizado
        long ultimoId = mascotasCrud.obtenerUltimoId();
        // Eliminar la mascota por el último ID
        mascotasCrud.eliminarMascota(ultimoId);
        // Verificar que la mascota ya no exista en la base de datos
        Mascotas mascotaEliminada = mascotasCrud.obtenerMascotaPorId(ultimoId);
        assertNull(mascotaEliminada);
    }
}

