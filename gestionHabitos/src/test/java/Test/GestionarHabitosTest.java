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
import org.itson.pruebas.gestionhabitos.model.IGestionarHabitosDAO;
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
    private IGestionarHabitosDAO gestionarHabitosDAO;

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

//    @Test
//    public void testCrearCuenta() throws ModelException {
//        Cuenta nuevaCuenta = new Cuenta();
//        nuevaCuenta.setUsuario("usuarioPruebaCrearCuenta");
//        nuevaCuenta.setContrasena("contrasenaCrearCuenta");
//
//        nuevaCuenta.setNombre("nombreCrearCuenta");
//
//        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
//        assertNotNull(creada);
//        assertEquals(nuevaCuenta.getUsuario(), creada.getUsuario());
//    }
//
//    @Test
//    public void testCrearHabitoSuccess() throws ModelException {
//        // Crea un nuevo hábito
//        Habito nuevoHabito = new Habito();
//        nuevoHabito.setNombre("HábitoPruebaCrearHabito");
//        nuevoHabito.setFrecuencia("Diaria");
//        nuevoHabito.setDiasSemana("1111111"); // Suponiendo que se asigna todos los días
//        nuevoHabito.setFechaCreacion(new Date());
//
//        Cuenta nuevaCuenta = new Cuenta();
//        nuevaCuenta.setUsuario("usuarioPruebaCrearHabito");
//        nuevaCuenta.setContrasena("contrasenaCrearHabito");
//        nuevaCuenta.setNombre("nombreCrearHabito");
//
//        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
//        nuevoHabito.setCuenta(creada);
//
//        Habito creadoHabito = gestionarHabitosDAO.crearHabito(nuevoHabito);
//        assertNotNull(creadoHabito);
//        assertEquals(nuevoHabito.getNombre(), creadoHabito.getNombre());
//    }
//    @Test
//    public void testActualizarHabito() throws ModelException {
//        
//        Cuenta nuevaCuenta = new Cuenta();
//        nuevaCuenta.setUsuario("usuarioPruebaActualizarHabito");
//        nuevaCuenta.setContrasena("contrasenaPruebaActualizarHabito");
//        nuevaCuenta.setNombre("BrendaPruebaActualizarHabito");
//        gestionarHabitosDAO.crearCuenta(nuevaCuenta);
//
//        Habito nuevoHabito = new Habito();
//        nuevoHabito.setNombre("HábitoPruebaActualizarHabito");
//        nuevoHabito.setFrecuencia("DiariaPruebaActualizarHabito");
//        nuevoHabito.setDiasSemana("1001001");
//        nuevoHabito.setFechaCreacion(new Date());
//        nuevoHabito.setFrecuencia("diarioPruebaActualizarHabito");
//        nuevoHabito.setCuenta(nuevaCuenta);
//        gestionarHabitosDAO.crearHabito(nuevoHabito);
//       
//        nuevoHabito.setFrecuencia("DiariaActualizarHabito");
//        Habito actualizado = gestionarHabitosDAO.actualizarHabito(nuevoHabito);
//
//        assertEquals("DiariaActualizarHabito", actualizado.getFrecuencia());
//    }

    @Test
    public void testEliminarHabito() throws ModelException {

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaEliminarHabito");
        nuevaCuenta.setContrasena("contrasenaPruebaEliminarHabito");
        nuevaCuenta.setNombre("BrendaPruebaEliminarHabito");

        gestionarHabitosDAO.crearCuenta(nuevaCuenta);  

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaEliminarHabito");
        nuevoHabito.setFrecuencia("DiariaPruebaEliminarHabito");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("diarioPruebaEliminarHabito");

        nuevoHabito.setCuenta(nuevaCuenta);

         gestionarHabitosDAO.crearHabito(nuevoHabito);

        boolean eliminado = gestionarHabitosDAO.eliminarHabito(nuevoHabito.getId());
        assertTrue(eliminado);
    }
//
//    @Test
//    public void testObtenerHabitos() throws ModelException {
//        
//        Cuenta nuevaCuenta = new Cuenta();
//        nuevaCuenta.setUsuario("usuarioPruebaObtenerHabitos");
//        nuevaCuenta.setContrasena("contrasenaPruebaObtenerHabitos");
//        nuevaCuenta.setNombre("BrendaPruebaObtenerHabitos");
//
//        gestionarHabitosDAO.crearCuenta(nuevaCuenta);  
//
//        Habito nuevoHabito = new Habito();
//        nuevoHabito.setNombre("HábitoPruebaObtenerHabitos");
//        nuevoHabito.setFrecuencia("DiariaPruebaObtenerHabitos");
//        nuevoHabito.setDiasSemana("1001001");
//        nuevoHabito.setFechaCreacion(new Date());
//        nuevoHabito.setFrecuencia("diarioPruebaObtenerHabitos");
//
//       
//        nuevoHabito.setCuenta(nuevaCuenta);
//
//        
//        gestionarHabitosDAO.crearHabito(nuevoHabito);
//
//        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(nuevaCuenta);
//        assertEquals(1, habitos.size()); 
//    }
//
//    @Test
//    public void testConsultarCuenta() throws ModelException {
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("usuarioPruebaConsultarCuenta");
//        cuenta.setContrasena("contrasenaPruebaConsultarCuenta");
//        cuenta.setNombre("BrendaPruebaConsultarCuenta");
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
//        Cuenta consultada = gestionarHabitosDAO.consultarCuenta(cuenta.getUsuario(), cuenta.getContrasena());
//        assertNotNull(consultada);
//        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
//    }
//
//    @Test
//    public void testBuscarPorFechaYIdHabito() throws ModelException {
//        // Crear y persistir la nueva cuenta
//        Cuenta nuevaCuenta = new Cuenta();
//        nuevaCuenta.setUsuario("usuarioPruebaBuscarPorFechaYIdHabito");
//        nuevaCuenta.setContrasena("contrasenaPruebaBuscarPorFechaYIdHabito");
//        nuevaCuenta.setNombre("BrendaPruebaBuscarPorFechaYIdHabito");
//
//        gestionarHabitosDAO.crearCuenta(nuevaCuenta); // Persistir la cuenta
//
//        // Crear y persistir el nuevo hábito
//        Habito nuevoHabito = new Habito();
//        nuevoHabito.setNombre("HábitoPruebaBuscarPorFechaYIdHabito");
//        nuevoHabito.setFrecuencia("DiariaPruebaBuscarPorFechaYIdHabito");
//        nuevoHabito.setDiasSemana("1001001");
//        nuevoHabito.setFechaCreacion(new Date());
//        nuevoHabito.setCuenta(nuevaCuenta);
//        gestionarHabitosDAO.crearHabito(nuevoHabito); // Persistir el hábito
//
//        // Crear historial
//        HistorialHabitos historial = new HistorialHabitos();
//        Date fechaHistorial = new Date(); // Guarda la fecha actual para la búsqueda
//        historial.setHabito(nuevoHabito);
//        historial.setDia(fechaHistorial); // Usa la misma fecha
//        historial.setCompletado(false);
//        gestionarHabitosDAO.guardarYActualizarHistorial(historial); // Persistir el historial
//
//        // Buscar por fecha y ID
//        HistorialHabitos encontrado = gestionarHabitosDAO.buscarPorFechaYIdHabito(fechaHistorial, nuevoHabito.getId());
//        assertNotNull(encontrado);
//        assertEquals(nuevoHabito.getId(), encontrado.getHabito().getId());
//    }
//
//
//    @Test
//    public void testCrearHistorial() throws ModelException {
//        // Crear un nuevo objeto Cuenta
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("UsuarioPruebaCrearHistorial");
//        cuenta.setContrasena("contrasenaPruebaCrearHistorial");
//        cuenta.setNombre("BrendaPruebaCrearHistorial");
//
//        // Persistir la cuenta
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
//        // Crear un nuevo objeto Habito
//        Habito habito = new Habito();
//        habito.setNombre("HábitoPruebaCrearHistorial");
//        habito.setFrecuencia("DiariaPruebaCrearHistorial");
//        habito.setDiasSemana("1001001");
//        habito.setFechaCreacion(new Date());
//        habito.setCuenta(cuenta);
//
//        // Persistir el hábito
//        gestionarHabitosDAO.crearHabito(habito);
//
//        // Crear el objeto HistorialHabitos
//        HistorialHabitos historial = new HistorialHabitos();
//        historial.setHabito(habito);
//        historial.setDia(new Date());
//        historial.setCompletado(false);
//
//        // Llamar al método crearHistorial
//        HistorialHabitos creado = gestionarHabitosDAO.guardarYActualizarHistorial(historial);
//
//        // Verificar que el objeto creado no sea nulo y que tenga el mismo id
//        assertNotNull(creado);
//        assertNotNull(creado.getId()); // Asegúrate de que el ID fue asignado
//        assertEquals(historial.getHabito().getId(), creado.getHabito().getId());
//        assertEquals(historial.getDia(), creado.getDia());
//        assertEquals(historial.isCompletado(), creado.isCompletado());
//    }
////
//
//    @Test
//    public void testActualizarHistorial() throws ModelException {
//        // Crear un nuevo hábito
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("UsuarioPruebaActualizarHistorial");
//        cuenta.setContrasena("contrasenaPruebaActualizarHistorial");
//        cuenta.setNombre("BrendaPruebaActualizarHistorial");
//
//        // Persistir la cuenta
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
//        // Crear un nuevo objeto Habito
//        Habito habito = new Habito();
//        habito.setNombre("HábitoPruebaActualizarHistorial");
//        habito.setFrecuencia("DiariaPruebaActualizarHistorial");
//        habito.setDiasSemana("1001001");
//        habito.setFechaCreacion(new Date());
//        habito.setCuenta(cuenta);
//
//        // Persistir el hábito
//        gestionarHabitosDAO.crearHabito(habito);
//
//        // Crear un nuevo historial asociado al hábito
//        HistorialHabitos historial = new HistorialHabitos();
//        historial.setHabito(habito);
//        historial.setDia(new Date()); // Fecha actual
//        historial.setCompletado(false); // Inicialmente no completado
//
//        // Persistir el historial
//        gestionarHabitosDAO.guardarYActualizarHistorial(historial);
//
//        // Actualizar el historial para marcarlo como completado
//        historial.setCompletado(true);
//        HistorialHabitos actualizado = gestionarHabitosDAO.guardarYActualizarHistorial(historial);
//
//        // Verificar que el historial ha sido actualizado correctamente
//        assertNotNull(actualizado);
//        assertTrue(actualizado.isCompletado());
//    }
//
//    @Test
//    public void testCuentaExiste() throws ModelException {
////         Crear y persistir la cuenta
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("usuarioPruebaCuentaExiste");
//        cuenta.setContrasena("contrasenaPruebaCuentaExiste");
//        cuenta.setNombre("brendaPruebaCuentaExiste");
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
////         Verificar existencia
//        boolean existe = gestionarHabitosDAO.cuentaExiste(cuenta.getUsuario());
//        assertTrue(existe);
//    }
//
//    @Test
//    public void testConsultarCuentaPorUsuario() throws ModelException {
////         Crear y persistir la cuenta
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("usuarioConsultaPorUsuario");
//        cuenta.setContrasena("contrasenaConsultaPorUsuario");
//        cuenta.setNombre("pulunchitow");
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
////         Consultar cuenta
//        Cuenta consultada = gestionarHabitosDAO.consultarCuentaPorUsuario(cuenta.getUsuario());
//        assertNotNull(consultada);
//        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
//    }
//
//    @Test
//    public void testBuscarHabitoPorId() throws ModelException {
////         Crear un hábito y persistirlo
//        Cuenta cuenta = new Cuenta();
//        cuenta.setUsuario("UsuarioPruebaBuscarHabitoPorId");
//        cuenta.setContrasena("contrasenaPruebaBuscarHabitoPorId");
//        cuenta.setNombre("BrendaPruebaBuscarHabitoPorId");
//
//        // Persistir la cuenta
//        gestionarHabitosDAO.crearCuenta(cuenta);
//
//        // Crear un nuevo objeto Habito
//        Habito habito = new Habito();
//        habito.setNombre("HábitoPruebaBuscarHabitoPorId");
//        habito.setFrecuencia("DiariaPruebaBuscarHabitoPorId");
//        habito.setDiasSemana("1001001");
//        habito.setFechaCreacion(new Date());
//        habito.setCuenta(cuenta);
//
//        // Persistir el hábito
//        gestionarHabitosDAO.crearHabito(habito);
//
////         Buscar por ID
//        Habito encontrado = gestionarHabitosDAO.buscarHabitoPorId(habito.getId());
//        assertNotNull(encontrado);
//        assertEquals(habito.getId(), encontrado.getId());
//    }
}
