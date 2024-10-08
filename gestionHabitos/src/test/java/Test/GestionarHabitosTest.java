/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import com.mycompany.subsistemamodelo.Conexion;
import com.mycompany.subsistemamodelo.Cuenta;
import com.mycompany.subsistemamodelo.GestionarHabitosDAO;
import com.mycompany.subsistemamodelo.Habito;
import com.mycompany.subsistemamodelo.HistorialHabitos;
import com.mycompany.subsistemamodelo.IConexion;
import com.mycompany.subsistemamodelo.IGestionarHabitosDAO;
import com.mycompany.subsistemamodelo.ProgresoHabito;
import com.mycompany.subsistemamodelo.ModelException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    @Test
    public void testCrearCuenta() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaCrearCuenta");
        nuevaCuenta.setContrasena("contrasenaCrearCuenta");

        nuevaCuenta.setNombre("nombreCrearCuenta");

        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
        assertNotNull(creada);
        assertEquals(nuevaCuenta.getUsuario(), creada.getUsuario());
    }
    
    @Test
    public void testConsultarCuenta() throws ModelException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioPruebaConsultarCuenta");
        cuenta.setContrasena("contrasenaPruebaConsultarCuenta");
        cuenta.setNombre("BrendaPruebaConsultarCuenta");
        gestionarHabitosDAO.crearCuenta(cuenta);

        Cuenta consultada = gestionarHabitosDAO.consultarCuenta(cuenta.getUsuario(), cuenta.getContrasena());
        assertNotNull(consultada);
        assertEquals(cuenta.getUsuario(), consultada.getUsuario());
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
    public void testCuentaExiste() throws ModelException {
//         Crear y persistir la cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioPruebaCuentaExiste");
        cuenta.setContrasena("contrasenaPruebaCuentaExiste");
        cuenta.setNombre("brendaPruebaCuentaExiste");
        gestionarHabitosDAO.crearCuenta(cuenta);

//         Verificar existencia
        boolean existe = gestionarHabitosDAO.cuentaExiste(cuenta.getUsuario());
        assertTrue(existe);
    }
    
    
    @Test
    public void testCrearHabito() throws ModelException {
        // Crea un nuevo hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaCrearHabito");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1111111"); // Suponiendo que se asigna todos los días
        nuevoHabito.setFechaCreacion(new Date());

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaCrearHabito");
        nuevaCuenta.setContrasena("contrasenaCrearHabito");
        nuevaCuenta.setNombre("nombreCrearHabito");

        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);
        nuevoHabito.setCuenta(creada);

        Habito creadoHabito = gestionarHabitosDAO.crearHabito(nuevoHabito);
        assertNotNull(creadoHabito);
        assertEquals(nuevoHabito.getNombre(), creadoHabito.getNombre());
    }
    
    
    
    
    @Test
    public void testActualizarHabito() throws ModelException {
        
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaActualizarHabito");
        nuevaCuenta.setContrasena("contrasenaPruebaActualizarHabito");
        nuevaCuenta.setNombre("BrendaPruebaActualizarHabito");
        gestionarHabitosDAO.crearCuenta(nuevaCuenta);

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaActualizarHabito");
        nuevoHabito.setFrecuencia("DiariaPruebaActualizarHabito");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("diarioPruebaActualizarHabito");
        nuevoHabito.setCuenta(nuevaCuenta);
        gestionarHabitosDAO.crearHabito(nuevoHabito);
       
        nuevoHabito.setFrecuencia("DiariaActualizarHabito");
        Habito actualizado = gestionarHabitosDAO.actualizarHabito(nuevoHabito);

        assertEquals("DiariaActualizarHabito", actualizado.getFrecuencia());
    }

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

    @Test
    public void testObtenerHabitos() throws ModelException {
        
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaObtenerHabitos");
        nuevaCuenta.setContrasena("contrasenaPruebaObtenerHabitos");
        nuevaCuenta.setNombre("BrendaPruebaObtenerHabitos");

        gestionarHabitosDAO.crearCuenta(nuevaCuenta);  

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaObtenerHabitos");
        nuevoHabito.setFrecuencia("DiariaPruebaObtenerHabitos");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("diarioPruebaObtenerHabitos");

       
        nuevoHabito.setCuenta(nuevaCuenta);

        
        gestionarHabitosDAO.crearHabito(nuevoHabito);

        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(nuevaCuenta);
        assertEquals(1, habitos.size()); 
    }
    
    @Test
    public void testHabitoCompletado() throws ModelException {
        
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaObtenerHabitos");
        nuevaCuenta.setContrasena("contrasenaPruebaObtenerHabitos");
        nuevaCuenta.setNombre("BrendaPruebaObtenerHabitos");

        nuevaCuenta = gestionarHabitosDAO.crearCuenta(nuevaCuenta);  

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaObtenerHabitos");
        nuevoHabito.setFrecuencia("DiariaPruebaObtenerHabitos");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setCuenta(nuevaCuenta);
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("diarioPruebaObtenerHabitos");
        
        nuevoHabito = gestionarHabitosDAO.crearHabito(nuevoHabito);
        
        HistorialHabitos historial = new HistorialHabitos();
        historial.setCompletado(true);
        historial.setDia(new Date());
        historial.setHabito(nuevoHabito);
        
        gestionarHabitosDAO.guardarYActualizarHistorial(historial);
        
        boolean completado = gestionarHabitosDAO.habitoCompletado(nuevoHabito);
        assertEquals(true , completado); 
    }

     @Test
    public void testBuscarHabitoPorId() throws ModelException {
        
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaBuscarHabitoPorId");
        nuevaCuenta.setContrasena("contrasenaPruebaBuscarHabitoPorId");
        nuevaCuenta.setNombre("BrendaPruebaBuscarHabitoPorId");

        nuevaCuenta = gestionarHabitosDAO.crearCuenta(nuevaCuenta);  

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaBuscarHabitoPorId");
        nuevoHabito.setFrecuencia("DiariaPruebaBuscarHabitoPorId");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setCuenta(nuevaCuenta);
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFrecuencia("DiarioPruebaBuscarHabitoPorId");
        
        nuevoHabito = gestionarHabitosDAO.crearHabito(nuevoHabito);
        
        Habito habitoEncontrado = gestionarHabitosDAO.buscarHabitoPorId(nuevoHabito.getId());

        // Verificar que el hábito no sea null y tiene los valores esperados
        assertNotNull(habitoEncontrado);
        assertEquals("HábitoPruebaBuscarHabitoPorId", habitoEncontrado.getNombre());
        assertEquals("DiarioPruebaBuscarHabitoPorId", habitoEncontrado.getFrecuencia());
    }

    @Test
    public void testBuscarPorFechaYIdHabito() throws ModelException {
        // Crear y persistir la nueva cuenta
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaBuscarPorFechaYIdHabito");
        nuevaCuenta.setContrasena("contrasenaPruebaBuscarPorFechaYIdHabito");
        nuevaCuenta.setNombre("BrendaPruebaBuscarPorFechaYIdHabito");

        gestionarHabitosDAO.crearCuenta(nuevaCuenta); // Persistir la cuenta

        // Crear y persistir el nuevo hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaBuscarPorFechaYIdHabito");
        nuevoHabito.setFrecuencia("DiariaPruebaBuscarPorFechaYIdHabito");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setCuenta(nuevaCuenta);
        gestionarHabitosDAO.crearHabito(nuevoHabito); // Persistir el hábito

        // Crear historial
        HistorialHabitos historial = new HistorialHabitos();
        Date fechaHistorial = new Date(); // Guarda la fecha actual para la búsqueda
        historial.setHabito(nuevoHabito);
        historial.setDia(fechaHistorial); // Usa la misma fecha
        historial.setCompletado(false);
        gestionarHabitosDAO.guardarYActualizarHistorial(historial); // Persistir el historial

        // Buscar por fecha y ID
        HistorialHabitos encontrado = gestionarHabitosDAO.buscarPorFechaYIdHabito(fechaHistorial, nuevoHabito.getId());
        assertNotNull(encontrado);
        assertEquals(nuevoHabito.getId(), encontrado.getHabito().getId());
    }


    @Test
    public void testGuardarHistorial() throws ModelException {
        // Crear un nuevo objeto Cuenta
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("UsuarioPruebaCrearHistorial");
        cuenta.setContrasena("contrasenaPruebaCrearHistorial");
        cuenta.setNombre("BrendaPruebaCrearHistorial");

        // Persistir la cuenta
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Crear un nuevo objeto Habito
        Habito habito = new Habito();
        habito.setNombre("HábitoPruebaCrearHistorial");
        habito.setFrecuencia("DiariaPruebaCrearHistorial");
        habito.setDiasSemana("1001001");
        habito.setFechaCreacion(new Date());
        habito.setCuenta(cuenta);

        // Persistir el hábito
        gestionarHabitosDAO.crearHabito(habito);

        // Crear el objeto HistorialHabitos
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        historial.setDia(new Date());
        historial.setCompletado(false);

        // Llamar al método crearHistorial
        HistorialHabitos creado = gestionarHabitosDAO.guardarYActualizarHistorial(historial);

        // Verificar que el objeto creado no sea nulo y que tenga el mismo id
        assertNotNull(creado);
        assertNotNull(creado.getId()); // Asegúrate de que el ID fue asignado
        assertEquals(historial.getHabito().getId(), creado.getHabito().getId());
        assertEquals(historial.getDia(), creado.getDia());
        assertEquals(historial.isCompletado(), creado.isCompletado());
    }


    @Test
    public void testActualizarHistorial() throws ModelException {
        // Crear un nuevo hábito
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("UsuarioPruebaActualizarHistorial");
        cuenta.setContrasena("contrasenaPruebaActualizarHistorial");
        cuenta.setNombre("BrendaPruebaActualizarHistorial");

        // Persistir la cuenta
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Crear un nuevo objeto Habito
        Habito habito = new Habito();
        habito.setNombre("HábitoPruebaActualizarHistorial");
        habito.setFrecuencia("DiariaPruebaActualizarHistorial");
        habito.setDiasSemana("1001001");
        habito.setFechaCreacion(new Date());
        habito.setCuenta(cuenta);

        // Persistir el hábito
        gestionarHabitosDAO.crearHabito(habito);

        // Crear un nuevo historial asociado al hábito
        HistorialHabitos historial = new HistorialHabitos();
        historial.setHabito(habito);
        historial.setDia(new Date()); // Fecha actual
        historial.setCompletado(false); // Inicialmente no completado

        // Persistir el historial
        gestionarHabitosDAO.guardarYActualizarHistorial(historial);

        // Actualizar el historial para marcarlo como completado
        historial.setCompletado(true);
        HistorialHabitos actualizado = gestionarHabitosDAO.guardarYActualizarHistorial(historial);

        // Verificar que el historial ha sido actualizado correctamente
        assertNotNull(actualizado);
        assertTrue(actualizado.isCompletado());
    }
    @Test
    public void testConsultarHistorialHabitos() throws ModelException {
        Date fecha = new Date();
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaHistorial");
        nuevaCuenta.setContrasena("contrasenaPruebaHistorial");
        nuevaCuenta.setNombre("BrendaPruebaHistorial");
        gestionarHabitosDAO.crearCuenta(nuevaCuenta);

        // Crear y persistir un hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("HábitoPruebaHistorial");
        nuevoHabito.setFrecuencia("DiariaPruebaHistorial");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setCuenta(nuevaCuenta);
        nuevoHabito.setFechaCreacion(fecha);
        gestionarHabitosDAO.crearHabito(nuevoHabito);

        // Crear y persistir el historial de hábitos
        HistorialHabitos historial = new HistorialHabitos();
        historial.setCompletado(true);
        historial.setDia(fecha); // Usar la fecha de hoy
        historial.setHabito(nuevoHabito);
        historial = gestionarHabitosDAO.guardarYActualizarHistorial(historial);

        // Usar la fecha de hoy para consultar
        // Ejecutar el método
        List<HistorialHabitos> historialList = gestionarHabitosDAO.consultarHistorialHabitos(fecha, nuevaCuenta);

        // Verificar que la lista no está vacía y contiene los datos correctos
        assertNotNull(historialList);
        assertEquals(1, historialList.size()); // Debería haber solo un registro
        assertEquals(historial.getId(), historialList.get(0).getId());
        assertEquals(fecha, historialList.get(0).getDia());
        assertEquals(nuevaCuenta, historialList.get(0).getHabito().getCuenta());
    }

    @Test
    public void testObtenerProgresoHabitos() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("testUserProgresoHabitos");
        nuevaCuenta.setContrasena("passwordProgresoHabitos");
        nuevaCuenta.setNombre("Test User Progreso Habitos");
        gestionarHabitosDAO.crearCuenta(nuevaCuenta);

        // Crear y persistir un hábito diario
        Habito habitoDiario = new Habito();
        habitoDiario.setNombre("Habito Diario");
        habitoDiario.setFrecuencia("Diario");
        habitoDiario.setDiasSemana("1111111"); // Todos los días
        habitoDiario.setFechaCreacion(new Date());
        habitoDiario.setCuenta(nuevaCuenta);
        gestionarHabitosDAO.crearHabito(habitoDiario);

        // Crear y persistir un hábito semanal (lunes y jueves)
        Habito habitoSemanal = new Habito();
        habitoSemanal.setNombre("Habito Semanal");
        habitoSemanal.setFrecuencia("Semanal");
        habitoSemanal.setDiasSemana("1001000"); // Lunes y jueves
        habitoSemanal.setFechaCreacion(new Date());
        habitoSemanal.setCuenta(nuevaCuenta);
        gestionarHabitosDAO.crearHabito(habitoSemanal);

        // Crear historial de hábitos completados para el hábito diario
        HistorialHabitos historial1 = new HistorialHabitos();
        historial1.setHabito(habitoDiario);
        historial1.setCompletado(true);
        historial1.setDia(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        gestionarHabitosDAO.guardarYActualizarHistorial(historial1);

        // Crear historial de hábitos no completados para el hábito semanal
        HistorialHabitos historial2 = new HistorialHabitos();
        historial2.setHabito(habitoSemanal);
        historial2.setCompletado(false);
        historial2.setDia(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        gestionarHabitosDAO.guardarYActualizarHistorial(historial2);
        // Definir rango de fechas (última semana)
        Date fechaInicio = Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = new Date(); // Hoy

        // Ejecutar el método
        List<ProgresoHabito> progresoHabitos = gestionarHabitosDAO.obtenerProgresoHabitos(nuevaCuenta, fechaInicio, fechaFin);

        // Verificar que se obtuvieron resultados
        assertNotNull(progresoHabitos);
        assertEquals(2, progresoHabitos.size()); // Hay dos hábitos

        // Verificar los detalles de cada progreso de hábito
        for (ProgresoHabito progreso : progresoHabitos) {
            if ("Habito Diario".equals(progreso.getNombre())) {
                assertEquals(1, progreso.getDiasRealizados()); // Debe haber sido completado 1 vez
                assertEquals(7, progreso.getTotalDias()); // 7 días en total (para hábitos diarios)
            } else if ("Habito Semanal".equals(progreso.getNombre())) {
                assertEquals(0, progreso.getDiasRealizados()); // No completado
                assertEquals(2, progreso.getTotalDias()); // Solo lunes y jueves
            }
        }
    }

}
