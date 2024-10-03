/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.itson.pruebas.gestionhabitos.model.Conexion;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestionarHabitosTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private GestionarHabitosDAO gestionarHabitosDAO;

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
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    public void testCrearCuenta() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPrueba");
        nuevaCuenta.setContrasena("contrasena");

        nuevaCuenta.setNombre("Brenda");

        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
        assertNotNull(creada);
        assertEquals(nuevaCuenta.getUsuario(), creada.getUsuario());
    }

    @Test
    public void testCrearHabitoConPersistenciaManual() throws ModelException {
        // 1. Crear una nueva cuenta
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaManual");
        nuevaCuenta.setContrasena("contrasena");
        nuevaCuenta.setNombre("Brenda");

        // 2. Persistir manualmente la cuenta
        gestionarHabitosDAO.crearCuenta(nuevaCuenta);  // Este método debe persistir la cuenta

        // 3. Crear un nuevo hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba manual");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("diario");

        // 4. Asociar la cuenta ya persistida al hábito
        nuevoHabito.setCuenta(nuevaCuenta);

        // 5. Persistir el hábito asociado a la cuenta
        Habito creado = gestionarHabitosDAO.crearHabito(nuevoHabito);

        // 6. Verificar que el hábito se haya creado correctamente
        assertNotNull(creado);
        assertEquals(nuevoHabito.getNombre(), creado.getNombre());
    }

    @Test
    public void testActualizarHabito() throws ModelException {
        // Crear un habito y persistirlo
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPrueba");
        nuevaCuenta.setContrasena("contrasena");

        nuevaCuenta.setNombre("Brenda");

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setId(2L);
        nuevoHabito.setFrecuencia("diario");
        nuevoHabito.setCuenta(nuevaCuenta);

        // Actualizar el hábito
        nuevoHabito.setFrecuencia("Diaria");
        Habito actualizado = gestionarHabitosDAO.actualizarHabito(nuevoHabito);

        assertEquals("Diaria", actualizado.getFrecuencia());
    }

    @Test
    public void testEliminarHabito() throws ModelException {

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPrueba");
        nuevaCuenta.setContrasena("contrasena");

        nuevaCuenta.setNombre("Brenda");

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setId(1L);
        nuevoHabito.setFrecuencia("diario");
        nuevoHabito.setCuenta(nuevaCuenta);

        boolean eliminado = gestionarHabitosDAO.eliminarHabito(nuevoHabito.getId());
        assertTrue(eliminado);
    }

    @Test
    public void testObtenerHabitos() throws ModelException {
        // Crear una cuenta de prueba
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("Juana");
        cuenta.setContrasena("contrasena4");
        cuenta.setNombre("Juana");

        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(cuenta);
        assertEquals(1, habitos.size()); // Verificar que hay dos hábitos
    }

    @Test
    public void testConsultarCuenta() throws ModelException {
//         Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioConsulta");
        cuenta.setContrasena("contrasenaConsulta");
        cuenta.setNombre("GelatinPower");
        gestionarHabitosDAO.crearCuenta(cuenta);

//         Consultar la cuenta
        Cuenta consultada = gestionarHabitosDAO.consultarCuenta(cuenta.getUsuario(), cuenta.getContrasena());
        assertNotNull(consultada);
        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
    }

    @Test
    public void testBuscarPorFechaYIdHabito() throws ModelException {
        // Crear y persistir la nueva cuenta
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruEbonae");
        nuevaCuenta.setContrasena("contrasena");
        nuevaCuenta.setNombre("Brenda");
        gestionarHabitosDAO.crearCuenta(nuevaCuenta); // Asegúrate de que esta llamada persiste la cuenta

        // Crear y persistir el nuevo hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setId(42L);
        nuevoHabito.setFrecuencia("diario");
        nuevoHabito.setCuenta(nuevaCuenta);
        gestionarHabitosDAO.crearHabito(nuevoHabito); // Asegúrate de que esta llamada persiste el hábito

        // Crear historial
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(nuevoHabito);
        historial.setDia(new Date());
        historial.setCompletado(false);
        gestionarHabitosDAO.guardarHistorial(historial); // Persistir el historial

        // Buscar por fecha y ID
        HistorialHabitos encontrado = gestionarHabitosDAO.buscarPorFechaYIdHabito(historial.getDia(), nuevoHabito.getId());
        assertNotNull(encontrado);
        assertEquals(nuevoHabito.getId(), encontrado.getHabito().getId());
    }

    @Test
    public void testCrearHistorial() throws ModelException {
        // Crear un nuevo objeto Cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("erferfer");
        cuenta.setContrasena("contrasena");
        cuenta.setNombre("Prueba Usuario");

        // Persistir la cuenta
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Crear un nuevo objeto Habito
        Habito habito = new Habito();
        habito.setNombre("Hábito de prueba");
        habito.setFrecuencia("Diaria");
        habito.setDiasSemana("1001001");
        habito.setFechaCreacion(new Date());
        habito.setId(455L);
        habito.setCuenta(cuenta);

        // Persistir el hábito
        gestionarHabitosDAO.crearHabito(habito);

        // Crear el objeto HistorialHabitos
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        historial.setDia(new Date());
        historial.setCompletado(false);

        // Llamar al método crearHistorial
        HistorialHabitos creado = gestionarHabitosDAO.guardarHistorial(historial);

        // Verificar que el objeto creado no sea nulo y que tenga el mismo id
        assertNotNull(creado);
        assertNotNull(creado.getId()); // Asegúrate de que el ID fue asignado
        assertEquals(historial.getHabito().getId(), creado.getHabito().getId());
        assertEquals(historial.getDia(), creado.getDia());
        assertEquals(historial.isCompletado(), creado.isCompletado());
    }
//

    @Test
    public void testActualizarHistorial() throws ModelException {
        // Crear un nuevo hábito
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("GTHTH");
        cuenta.setContrasena("contrasena");
        cuenta.setNombre("Prueba Usuario");

        // Persistir la cuenta
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Crear un nuevo objeto Habito
        Habito habito = new Habito();
        habito.setNombre("Hábito de prueba");
        habito.setFrecuencia("Diaria");
        habito.setDiasSemana("1001001");
        habito.setFechaCreacion(new Date());
        habito.setId(5L);
        habito.setCuenta(cuenta);

        // Persistir el hábito
        gestionarHabitosDAO.crearHabito(habito);

        // Crear un nuevo historial asociado al hábito
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        historial.setDia(new Date()); // Fecha actual
        historial.setCompletado(false); // Inicialmente no completado

        // Persistir el historial
        gestionarHabitosDAO.guardarHistorial(historial);

        // Actualizar el historial para marcarlo como completado
        historial.setCompletado(true);
        HistorialHabitos actualizado = gestionarHabitosDAO.guardarHistorial(historial);

        // Verificar que el historial ha sido actualizado correctamente
        assertNotNull(actualizado);
        assertTrue(actualizado.isCompletado());
    }

    @Test
    public void testCuentaExiste() throws ModelException {
//         Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioExistente");
        cuenta.setContrasena("contrasenaExistente");
        cuenta.setNombre("pulunchito");
        gestionarHabitosDAO.crearCuenta(cuenta);

//         Verificar existencia
        boolean existe = gestionarHabitosDAO.cuentaExiste(cuenta.getUsuario());
        assertTrue(existe);
    }

    @Test
    public void testConsultarCuentaPorUsuario() throws ModelException {
//         Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioConsultaPorUsuario");
        cuenta.setContrasena("contrasenaConsultaPorUsuario");
        cuenta.setNombre("pulunchitow");
        gestionarHabitosDAO.crearCuenta(cuenta);

//         Consultar cuenta
        Cuenta consultada = gestionarHabitosDAO.consultarCuentaPorUsuario(cuenta.getUsuario());
        assertNotNull(consultada);
        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
    }

    @Test
    public void testBuscarHabitoPorId() throws ModelException {
//         Crear un hábito y persistirlo
        Habito habito = new Habito();
        habito.setNombre("Hábito por ID");
        habito.setId(4L);

//         Buscar por ID
        Habito encontrado = gestionarHabitosDAO.buscarHabitoPorId(habito.getId());
        assertNotNull(encontrado);
        assertEquals(habito.getId(), encontrado.getId());
    }
}
