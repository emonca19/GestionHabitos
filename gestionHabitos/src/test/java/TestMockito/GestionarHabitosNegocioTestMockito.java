/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;

import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.HabitoDTO;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.ModelException;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;
import org.itson.pruebas.gestionhabitos.model.IGestionarHabitosDAO;

@ExtendWith(MockitoExtension.class)
public class GestionarHabitosNegocioTestMockito {

    @Mock
    private IGestionarHabitosDAO habitoDAO; // Mock del DAO

    @InjectMocks
    private GestionarHabitosNegocio gestionarHabitosNegocio; // Clase a probar

    @Mock
    private CuentaDTO cuentaDTO;

    @Mock
    private HabitoDTO habitoDTO;

    @Mock
    private List<Habito> habitosMockList; // Mock de la lista de hábitos

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testObtenerHabitos() throws ControllerException, ModelException {
        // Simula el comportamiento esperado
        when(habitoDAO.obtenerHabitos(any())).thenReturn(habitosMockList);
        when(habitosMockList.isEmpty()).thenReturn(false); // Para evitar NullPointerException en el stream

        // Crea un objeto Habito mock y simula la conversión a DTO
        Habito mockHabito = new Habito();
        when(habitosMockList.stream()).thenReturn(Stream.of(mockHabito));
        when(gestionarHabitosNegocio.HabitoConvertirADTO(mockHabito)).thenReturn(habitoDTO);

        // Llama al método a probar
        List<HabitoDTO> habitos = gestionarHabitosNegocio.obtenerHabitos(cuentaDTO);

        // Afirmaciones
        assertNotNull(habitos);
        assertFalse(habitos.isEmpty()); // Verifica que la lista no esté vacía
        verify(habitoDAO, times(1)).obtenerHabitos(any()); // Verifica que se llamó al método en el DAO
    }

    @Test
    public void testCrearCuenta_CamposValidos() throws ControllerException, ModelException {
        // Simulamos que el DTO tiene campos válidos
        when(cuentaDTO.sonCamposValidos()).thenReturn(true);

        // Simula el comportamiento del DAO
        when(habitoDAO.crearCuenta(any())).thenReturn(new Cuenta()); // Suponiendo que el DAO devuelve una nueva cuenta

        // Llama al método a probar
        CuentaDTO nuevaCuenta = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        // Afirmaciones
        assertNotNull(nuevaCuenta);
        verify(habitoDAO, times(1)).crearCuenta(any()); // Verifica que se llamó al método en el DAO
    }

    @Test
    public void testCrearCuenta_CamposInvalidos() throws ModelException {
        // Simulamos que el DTO tiene campos inválidos
        when(cuentaDTO.sonCamposValidos()).thenReturn(false);

        // Llama al método a probar y verifica que se lanza una excepción
        ControllerException thrown = assertThrows(ControllerException.class, () -> {
            gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        });

        assertEquals("No se puede crear cuenta porque no cumple con los datos necesarios", thrown.getMessage());
        verify(habitoDAO, never()).crearCuenta(any()); // Verifica que no se llamó al DAO
    }
}
