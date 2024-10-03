/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.itson.pruebas.gestionhabitos.model.Conexion;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author elimo
 */
public class GestionarHabitoDAOTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private GestionarHabitosDAO gestionarHabitosDAO;

    @BeforeAll
    public static void setUpClass() {
        // Configuración de la base de datos en memoria (H2)
        entityManagerFactory = Persistence.createEntityManagerFactory("testPU");
    }

    @AfterAll
    public static void tearDownClass() {
        // Cierre de la fábrica de administradores de entidad
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @BeforeEach
    public void setUp() {
        // Creación de una nueva conexión a la base de datos
        IConexion conexion = new Conexion();
        entityManager = conexion.crearConexion();

        // Inicializa el DAO con la conexión
        gestionarHabitosDAO = new GestionarHabitosDAO(conexion);

        // Inicia la transacción
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        // Si la transacción está activa, se hace un rollback para limpiar los datos
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        // Cierra el EntityManager
        if (entityManager != null) {
            entityManager.close();
        }
    }

    @Test
    public void testCrearHabito() throws ModelException {
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba");
        nuevoHabito.setFrecuencia("Diaria");

        Habito creado = gestionarHabitosDAO.crearHabito(nuevoHabito);
        assertNotNull(creado);
        assertEquals(nuevoHabito.getNombre(), creado.getNombre());
    }

    @Test
    public void testActualizarHabito() throws ModelException {
        // Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito inicial");
        habito.setFrecuencia("Semanal");
        gestionarHabitosDAO.crearHabito(habito);

        // Actualizar el hábito
        habito.setFrecuencia("Diaria");
        Habito actualizado = gestionarHabitosDAO.actualizarHabito(habito);

        assertEquals("Diaria", actualizado.getFrecuencia());
    }

    @Test
    public void testEliminarHabito() throws ModelException {
        // Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito para eliminar");
        gestionarHabitosDAO.crearHabito(habito);

        // Eliminar el hábito
        boolean eliminado = gestionarHabitosDAO.eliminarHabito(habito.getId());
        assertTrue(eliminado);
    }

    @Test
    public void testObtenerHabitos() throws ModelException {
        // Crear cuenta y hábitos
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("testUser");
        cuenta.setContrasena("testPass");
        gestionarHabitosDAO.crearCuenta(cuenta);

        Habito habito1 = new Habito();
        habito1.setNombre("Hábito 1");
        habito1.setCuenta(cuenta);
        gestionarHabitosDAO.crearHabito(habito1);

        Habito habito2 = new Habito();
        habito2.setNombre("Hábito 2");
        habito2.setCuenta(cuenta);
        gestionarHabitosDAO.crearHabito(habito2);

        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(cuenta);
        assertEquals(2, habitos.size());
    }

    @Test
    public void testCrearCuenta() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPrueba");
        nuevaCuenta.setContrasena("contrasena");

        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
        assertNotNull(creada);
        assertEquals(nuevaCuenta.getUsuario(), creada.getUsuario());
    }

    @Test
    public void testConsultarCuenta() throws ModelException {
        // Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioConsulta");
        cuenta.setContrasena("contrasenaConsulta");
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Consultar la cuenta
        Cuenta consultada = gestionarHabitosDAO.consultarCuenta(cuenta.getUsuario(), cuenta.getContrasena());
        assertNotNull(consultada);
        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
    }

    @Test
    public void testBuscarPorFechaYIdHabito() throws ModelException {
        // Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito para historial");
        gestionarHabitosDAO.crearHabito(habito);

        // Crear historial
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        historial.setDia(new Date());
        gestionarHabitosDAO.crearHistorial(historial);

        // Buscar por fecha y ID
        HistorialHabitos encontrado = gestionarHabitosDAO.buscarPorFechaYIdHabito(historial.getDia(), habito.getId());
        assertNotNull(encontrado);
        assertEquals(habito.getId(), encontrado.getHabito().getId());
    }

    @Test
    public void testActualizarHistorial() throws ModelException {
        // Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito para actualizar historial");
        gestionarHabitosDAO.crearHabito(habito);

        // Crear historial
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        gestionarHabitosDAO.crearHistorial(historial);

        // Actualizar historial
        historial.setCompletado(true);
        HistorialHabitos actualizado = gestionarHabitosDAO.actualizarHistorial(historial);
        assertTrue(actualizado.isCompletado());
    }

    @Test
    public void testCuentaExiste() throws ModelException {
        // Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioExistente");
        cuenta.setContrasena("contrasenaExistente");
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Verificar existencia
        boolean existe = gestionarHabitosDAO.cuentaExiste(cuenta.getUsuario());
        assertTrue(existe);
    }

    @Test
    public void testConsultarCuentaPorUsuario() throws ModelException {
        // Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioConsultaPorUsuario");
        cuenta.setContrasena("contrasenaConsultaPorUsuario");
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Consultar cuenta
        Cuenta consultada = gestionarHabitosDAO.consultarCuentaPorUsuario(cuenta.getUsuario());
        assertNotNull(consultada);
        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
    }

    @Test
    public void testBuscarHabitoPorId() throws ModelException {
        // Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito por ID");
        gestionarHabitosDAO.crearHabito(habito);

        // Buscar por ID
        Habito encontrado = gestionarHabitosDAO.buscarHabitoPorId(habito.getId());
        assertNotNull(encontrado);
        assertEquals(habito.getId(), encontrado.getId());
    }
}
