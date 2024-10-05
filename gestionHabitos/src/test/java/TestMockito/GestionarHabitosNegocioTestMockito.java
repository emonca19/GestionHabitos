/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;
import java.util.Date;
import java.util.List;
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.HabitoDTO;
import org.itson.pruebas.gestionhabitos.controller.HistorialHabitosDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.model.ModelException;

public class GestionarHabitosNegocioTestMockito {

   
    @Mock
    private GestionarHabitosNegocio gestionarHabitosNegocio;
    
    @Mock
    private CuentaDTO cuentaDTO;

    @Mock
    private HabitoDTO habitoDTO;

    @Mock
    private List<HabitoDTO> habitoDTOMockList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearHabito() throws ModelException, ControllerException {
        when(gestionarHabitosNegocio.crearHabito(any(HabitoDTO.class))).thenReturn(habitoDTO);

        HabitoDTO nuevoHabito = gestionarHabitosNegocio.crearHabito(habitoDTO);

        assertNotNull(nuevoHabito);
        verify(gestionarHabitosNegocio).crearHabito(habitoDTO);
    }

    @Test
    public void testActualizarHabito() throws ModelException, ControllerException {
        when(gestionarHabitosNegocio.actualizarHabito(any(HabitoDTO.class))).thenReturn(habitoDTO);

        HabitoDTO actualizado = gestionarHabitosNegocio.actualizarHabito(habitoDTO);

        assertNotNull(actualizado);
        verify(gestionarHabitosNegocio).actualizarHabito(habitoDTO);
    }


    @Test
    public void testConsultarCuenta() throws ModelException {
        when(gestionarHabitosNegocio.consultarCuentaPorUsuario("testUser")).thenReturn(cuentaDTO);

        CuentaDTO cuenta = gestionarHabitosNegocio.consultarCuentaPorUsuario("testUser");

        assertNotNull(cuenta);
        verify(gestionarHabitosNegocio).consultarCuentaPorUsuario("testUser");
    }

    @Test
    public void testObtenerHabitos() throws ModelException, ControllerException {
        when(gestionarHabitosNegocio.obtenerHabitos(any(CuentaDTO.class))).thenReturn(habitoDTOMockList);

        List<HabitoDTO> habitos = gestionarHabitosNegocio.obtenerHabitos(cuentaDTO);

        assertNotNull(habitos);
        verify(gestionarHabitosNegocio).obtenerHabitos(any(CuentaDTO.class));
    }

    @Test
    public void testCuentaExiste() throws ModelException, ControllerException {
        when(gestionarHabitosNegocio.cuentaExiste("testUser2")).thenReturn(true);

        boolean existe = gestionarHabitosNegocio.cuentaExiste("testUser2");

        assertTrue(existe);
        verify(gestionarHabitosNegocio).cuentaExiste("testUser2");
    }

@Test
public void testEliminarHabito() throws ModelException, ControllerException {
    // Simula el comportamiento del método para que devuelva true
    when(gestionarHabitosNegocio.eliminarHabito(1L)).thenReturn(true);

    // Llama al método y verifica que no se lance ninguna excepción
    assertDoesNotThrow(() -> {
        boolean eliminado = gestionarHabitosNegocio.eliminarHabito(1L);
        assertTrue(eliminado); // Verifica que se haya eliminado
    });

    verify(gestionarHabitosNegocio).eliminarHabito(1L); // Verifica que se haya llamado al método
}

}
