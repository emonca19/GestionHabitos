/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author crist
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.HabitoDTO;
import org.itson.pruebas.gestionhabitos.controller.HistorialHabitosDTO;
import org.itson.pruebas.gestionhabitos.controller.IGestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.ModelException;

public class GestionarHabitosNegocioTest {

    private IGestionarHabitosNegocio gestionarHabitosNegocio;
    private HabitoDTO habitoDTO;
    private CuentaDTO cuentaDTO;

    @BeforeEach
    public void setUp() {
        gestionarHabitosNegocio = new GestionarHabitosNegocio();
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUser");
        cuentaDTO.setNombre("Test User");
        cuentaDTO.setContraseña("password123");

        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
    }

    @Test
    public void testCrearCuenta() {
        assertDoesNotThrow(() -> gestionarHabitosNegocio.crearCuenta(cuentaDTO));
    }

    @Test
    public void testCrearHabito() {
        assertDoesNotThrow(() -> gestionarHabitosNegocio.crearHabito(habitoDTO));
    }

    @Test
    public void testObtenerHabitosNegocio() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testObtenerHabitosNegocio");
        cuentaDTO.setNombre("TestObtenerHabitosNegocio");
        cuentaDTO.setContraseña("passwordObtenerHabitosNegocio");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("TestObtenerHabitosNegocio");
        habitoDTO.setFrecuencia("DiarioObtenerHabitosNegocio");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertDoesNotThrow(() -> {
            List<HabitoDTO> habitos = gestionarHabitosNegocio.obtenerHabitos(cuentaDTO);
            assertNotNull(habitos);
        });
    }

    @Test
    public void testEliminarHabito() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testEliminarHabito");
        cuentaDTO.setNombre("TestEliminarHabito");
        cuentaDTO.setContraseña("passwordEliminarHabito");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("TestEliminarHabito");
        habitoDTO.setFrecuencia("DiarioEliminarHabito");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
        
        assertDoesNotThrow(() -> {
            boolean eliminado = gestionarHabitosNegocio.eliminarHabito(habitoDTO.getId());
            assertTrue(eliminado);
        });
        
    }

    @Test
    public void testActualizarHabitoNegocio() throws ControllerException {

        cuentaDTO.setUsuario("usuarioPruebaActualizarHabito");
        cuentaDTO.setContraseña("contrasenaPruebaActualizarHabito");
        cuentaDTO.setNombre("BrendaPruebaActualizarHabito");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO.setNombre("HábitoPruebaActualizarHabito");
        habitoDTO.setDiasSemana("1001001");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertNotNull(habitoDTO.getId(), "El identificador del hábito no debería ser nulo después de la creación.");

        // Actualizar el hábito
        assertDoesNotThrow(() -> {
            habitoDTO.setFrecuencia("DiariaActualizarHabitoNegocio");
            HabitoDTO actualizado = gestionarHabitosNegocio.actualizarHabito(habitoDTO);
            assertNotNull(actualizado);
            assertEquals(habitoDTO.getFrecuencia(), actualizado.getFrecuencia());
        });
    }

    @Test
    public void testConsultarCuenta() {
        assertDoesNotThrow(() -> {
            CuentaDTO cuentaConsultada = gestionarHabitosNegocio.consultarCuenta("testUser", "password123");
            assertNotNull(cuentaConsultada);
            assertEquals(cuentaDTO.getUsuario(), cuentaConsultada.getUsuario());
        });
    }

    @Test
    public void testConsultarHistorialHabitos() {
        assertDoesNotThrow(() -> {
            List<HistorialHabitosDTO> historial = gestionarHabitosNegocio.consultarHistorialHabitos(new Date(), cuentaDTO);
            assertNotNull(historial);
        });
    }

    @Test
    public void testCuentaExiste() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUser2");
        cuentaDTO.setNombre("Test User2");
        cuentaDTO.setContraseña("password123");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        assertDoesNotThrow(() -> {
            boolean existe = gestionarHabitosNegocio.cuentaExiste("testUser2");
            assertTrue(existe);
        });
    }

    @Test
    public void testIdentificarDias() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUser5");
        cuentaDTO.setNombre("Test User5");
        cuentaDTO.setContraseña("password123");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito5");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertDoesNotThrow(() -> {
            List<HabitoDTO> habitosPorDia = gestionarHabitosNegocio.identificarDias(cuentaDTO, "lunes");
            assertNotNull(habitosPorDia);
        });
    }

    @Test
    public void testObtenerSemana() {
        assertDoesNotThrow(() -> {
            Date[] semana = gestionarHabitosNegocio.obtenerSemana(new Date());
            assertEquals(7, semana.length);
        });
    }
    
    @Test
public void testBuscarPorFechaYIdHabito() throws ControllerException {
    // Crear cuenta y hábito
    cuentaDTO = new CuentaDTO();
    cuentaDTO.setUsuario("testUserBuscar");
    cuentaDTO.setNombre("Test User Buscar");
    cuentaDTO.setContraseña("passwordBuscar");
    cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);
Date fecha = new Date();
    habitoDTO = new HabitoDTO();
    habitoDTO.setNombre("Test Habito Buscar");
    habitoDTO.setFrecuencia("Diario");
    habitoDTO.setDiasSemana("1000000");
    habitoDTO.setFechaCreacion(fecha);
    habitoDTO.setCuentaId(cuentaDTO);
    habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

   

    HistorialHabitosDTO resultado = gestionarHabitosNegocio.buscarPorFechaYIdHabito(fecha, habitoDTO.getId());
    
    assertNotNull(resultado);
    assertEquals(habitoDTO.getId(), resultado.getHabito().getId());
    assertEquals(fecha, resultado.getDia());
}


@Test
public void testGuardarHistorial() throws ControllerException {
    // Crear cuenta y hábito
    cuentaDTO = new CuentaDTO();
    cuentaDTO.setUsuario("testUserGuardarHistorial");
    cuentaDTO.setNombre("Test User Guardar Historial");
    cuentaDTO.setContraseña("passwordGuardarHistorial");
    cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

    habitoDTO = new HabitoDTO();
    habitoDTO.setNombre("Test Habito Guardar Historial");
    habitoDTO.setFrecuencia("Diario");
    habitoDTO.setDiasSemana("1000000");
    habitoDTO.setFechaCreacion(new Date());
    habitoDTO.setCuentaId(cuentaDTO);
    habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

    HistorialHabitosDTO historial = new HistorialHabitosDTO();
    historial.setDia(new Date());
    historial.setCompletado(true);
    historial.setHabito(habitoDTO);

    HistorialHabitosDTO resultado = gestionarHabitosNegocio.guardarHistorial(historial);
    
    assertNotNull(resultado);
    assertTrue(resultado.isCompletado());
}

@Test
public void testObtenerSemanaDireccion() throws ControllerException {
    // Usar la semana actual para el test
    Date[] semanaActual = gestionarHabitosNegocio.obtenerSemana(new Date());
    Date[] nuevaSemana = gestionarHabitosNegocio.obtenerSemana(semanaActual, "anterior");
    
    assertNotNull(nuevaSemana);
    assertEquals(7, nuevaSemana.length);
}

@Test
public void testConvertirABits() {
    // Prueba de conversión a bits
    String bits = GestionarHabitosNegocio.convertirABits(true, false, true, false, true, false, true);
    
    assertEquals("1010101", bits);
}

@Test
public void testConsultarCuentaPorUsuario() throws ControllerException, ModelException {
    // Crear cuenta
    cuentaDTO = new CuentaDTO();
    cuentaDTO.setUsuario("testUserConsultar");
    cuentaDTO.setNombre("Test User Consultar");
    cuentaDTO.setContraseña("passwordConsultar");
    gestionarHabitosNegocio.crearCuenta(cuentaDTO);
    
    CuentaDTO cuentaConsultada = gestionarHabitosNegocio.consultarCuentaPorUsuario(cuentaDTO.getUsuario());
    
    assertNotNull(cuentaConsultada);
    assertEquals(cuentaDTO.getUsuario(), cuentaConsultada.getUsuario());
}


@Test
public void testBuscarHabitoPorId() throws ControllerException {
    // Crear cuenta y hábito
    cuentaDTO = new CuentaDTO();
    cuentaDTO.setUsuario("testUserBuscarHabito");
    cuentaDTO.setNombre("Test User Buscar Habito");
    cuentaDTO.setContraseña("passwordBuscarHabito");
    cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

    habitoDTO = new HabitoDTO();
    habitoDTO.setNombre("Test Habito Buscar");
    habitoDTO.setFrecuencia("Diario");
    habitoDTO.setDiasSemana("1000000");
    habitoDTO.setFechaCreacion(new Date());
    habitoDTO.setCuentaId(cuentaDTO);
    habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

    HabitoDTO habito = gestionarHabitosNegocio.buscarHabitoPorId(habitoDTO.getId());
    
    assertNotNull(habito);
    assertEquals(habitoDTO.getId(), habito.getId());
}


//@Test
//public void testHistorialHabitosDTOConvertirAEntidad() {
//    // Crear historial
//    HistorialHabitosDTO dto = new HistorialHabitosDTO();
//    dto.setId(1L);
//    dto.setCompletado(true);
//    
//    HistorialHabitos entidad = gestionarHabitosNegocio.HistorialHabitosDTOConvertirAEntidad(dto);
//    
//    assertNotNull(entidad);
//    assertEquals(dto.getId(), entidad.getId());
//    assertEquals(dto.isCompletado(), entidad.isCompletado());
//}

@Test
public void testHabitoCompletado() throws ControllerException {
    // Crear cuenta y hábito
    cuentaDTO = new CuentaDTO();
    cuentaDTO.setUsuario("testUserHabitoCompletado");
    cuentaDTO.setNombre("Test User Habito Completado");
    cuentaDTO.setContraseña("passwordHabitoCompletado");
    cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

    habitoDTO = new HabitoDTO();
    habitoDTO.setNombre("Test Habito Completado");
    habitoDTO.setFrecuencia("Diario");
    habitoDTO.setDiasSemana("1000000");
    habitoDTO.setFechaCreacion(new Date());
    habitoDTO.setCuentaId(cuentaDTO);
    habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
    
    boolean completado = gestionarHabitosNegocio.habitoCompletado(habitoDTO);
    
    assertFalse(completado); // O true dependiendo del estado del hábito
}

}
//
