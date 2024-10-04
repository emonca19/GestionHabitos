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
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.HabitoDTO;
import org.itson.pruebas.gestionhabitos.controller.HistorialHabitosDTO;

public class GestionarHabitosNegocioTest {

    private GestionarHabitosNegocio gestionarHabitosNegocio;
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
        habitoDTO.setId(1L);
        habitoDTO.setNombre("Test Habito");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
    }

    @Test
    public void testCrearHabito() {
        assertDoesNotThrow(() -> gestionarHabitosNegocio.crearHabito(habitoDTO));
    }

    @Test
    public void testCrearCuenta() {
        assertDoesNotThrow(() -> gestionarHabitosNegocio.crearCuenta(cuentaDTO));
    }

    @Test
    public void testObtenerHabitos() {
        assertDoesNotThrow(() -> {
            List<HabitoDTO> habitos = gestionarHabitosNegocio.obtenerHabitos(cuentaDTO);
            assertNotNull(habitos);
        });
    }

    @Test
    public void testEliminarHabito() {
        assertDoesNotThrow(() -> {
            boolean eliminado = gestionarHabitosNegocio.eliminarHabito(1L);
            assertTrue(eliminado);
        });
    }

    @Test
    public void testActualizarHabito() {
        assertDoesNotThrow(() -> {
            HabitoDTO actualizado = gestionarHabitosNegocio.actualizarHabito(habitoDTO);
            assertNotNull(actualizado);
            assertEquals(habitoDTO.getNombre(), actualizado.getNombre());
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
            List<HistorialHabitosDTO> historial = gestionarHabitosNegocio.consultarHisorialHabitos(new Date(), cuentaDTO);
            assertNotNull(historial);
        });
    }

    @Test
    public void testCuentaExiste() {
        assertDoesNotThrow(() -> {
            boolean existe = gestionarHabitosNegocio.cuentaExiste("testUser");
            assertTrue(existe);
        });
    }

    @Test
    public void testIdentificarDias() {
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
}

