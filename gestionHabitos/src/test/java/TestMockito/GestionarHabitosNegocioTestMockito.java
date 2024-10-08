/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;

import com.mycompany.subsistemacontroller.ControllerException;
import com.mycompany.subsistemacontroller.CuentaDTO;
import com.mycompany.subsistemacontroller.GestionarHabitosNegocio;
import com.mycompany.subsistemacontroller.HabitoDTO;
import com.mycompany.subsistemamodelo.ModelException;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


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


@ExtendWith(MockitoExtension.class)
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





@Test
public void testObtenerHabitosConCuentaInvalida() throws ControllerException {
    // Simula que el método lanza una excepción al intentar obtener hábitos con una cuenta inválida
    when(gestionarHabitosNegocio.obtenerHabitos(any(CuentaDTO.class))).thenThrow(new ControllerException("Cuenta inválida"));

    // Verifica que se lance la excepción al intentar obtener los hábitos
    assertThrows(ControllerException.class, () -> {
        gestionarHabitosNegocio.obtenerHabitos(new CuentaDTO()); // Pasa una cuenta inválida
    });

    // Verifica que el método fue llamado
    verify(gestionarHabitosNegocio).obtenerHabitos(any(CuentaDTO.class));
}

@Test
public void testCuentaNoExiste() throws ControllerException {
    // Simula que el método lanza una excepción al verificar una cuenta no existente
    when(gestionarHabitosNegocio.cuentaExiste("usuarioNoExistente")).thenReturn(false);

    // Llama al método y verifica el resultado
    boolean existe = gestionarHabitosNegocio.cuentaExiste("usuarioNoExistente");

    assertFalse(existe); // Verifica que la cuenta no existe
    verify(gestionarHabitosNegocio).cuentaExiste("usuarioNoExistente");
}

@Test
public void testCrearHabitoConCamposInvalidos() throws ControllerException {
    // Prepara un HabitoDTO inválido
    HabitoDTO habitoInvalido = new HabitoDTO(); // Asigna propiedades inválidas según las reglas de negocio

    // Simula que el método lanza una RuntimeException al intentar crear un hábito con datos inválidos
    when(gestionarHabitosNegocio.crearHabito(any(HabitoDTO.class))).thenThrow(new RuntimeException("Datos inválidos"));

    // Verifica que se lance la excepción al intentar crear un hábito
    assertThrows(RuntimeException.class, () -> {
        gestionarHabitosNegocio.crearHabito(habitoInvalido);
    });

    // Verifica que el método fue llamado
    verify(gestionarHabitosNegocio).crearHabito(habitoInvalido);
}

@Test
public void testActualizarHabitoConCamposInvalidos() throws ControllerException {
    // Prepara un HabitoDTO inválido
    HabitoDTO habitoInvalido = new HabitoDTO(); // Asigna propiedades inválidas según las reglas de negocio

    // Simula que el método lanza una RuntimeException al intentar actualizar un hábito con datos inválidos
    when(gestionarHabitosNegocio.actualizarHabito(any(HabitoDTO.class))).thenThrow(new RuntimeException("Datos inválidos"));

    // Verifica que se lance la excepción al intentar actualizar un hábito
    assertThrows(RuntimeException.class, () -> {
        gestionarHabitosNegocio.actualizarHabito(habitoInvalido);
    });

    // Verifica que el método fue llamado
    verify(gestionarHabitosNegocio).actualizarHabito(habitoInvalido);
}

@Test
public void testEliminarHabitoConIdInvalido() throws ControllerException {
    // Simula que el método lanza una RuntimeException al intentar eliminar un hábito con un ID inválido
    when(gestionarHabitosNegocio.eliminarHabito(-1L)).thenThrow(new RuntimeException("ID inválido"));

    // Verifica que se lance la excepción al intentar eliminar un hábito
    assertThrows(RuntimeException.class, () -> {
        gestionarHabitosNegocio.eliminarHabito(-1L); // Usar un ID negativo como caso inválido
    });

    // Verifica que el método fue llamado
    verify(gestionarHabitosNegocio).eliminarHabito(-1L);
}



}
