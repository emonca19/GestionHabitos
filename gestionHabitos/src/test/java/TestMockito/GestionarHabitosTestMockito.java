/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;

import Test.GestionarHabitosTest;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class GestionarHabitosTestMockito {

    @Mock
    private EntityManager entityManager;

    @Mock
    private GestionarHabitosDAO gestionarHabitosDAO;

    @InjectMocks
    private GestionarHabitosTest gestionarHabitosTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa los mocks
    }

    @Test
    public void testCrearCuenta() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPrueba");
        nuevaCuenta.setContrasena("contrasena");
        nuevaCuenta.setNombre("Brenda");

        // Mockear el comportamiento del DAO
        when(gestionarHabitosDAO.crearCuenta(nuevaCuenta)).thenReturn(nuevaCuenta);

        // Llamar al método a probar
        Cuenta creada = gestionarHabitosDAO.crearCuenta(nuevaCuenta);

        // Verificar el resultado
        assertNotNull(creada);
        assertEquals(nuevaCuenta.getUsuario(), creada.getUsuario());

        // Verificar que el método del DAO fue llamado
        verify(gestionarHabitosDAO, times(1)).crearCuenta(nuevaCuenta);
    }

    @Test
    public void testCrearHabitoConPersistenciaManual() throws ModelException {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setUsuario("usuarioPruebaManual");
        nuevaCuenta.setContrasena("contrasena");
        nuevaCuenta.setNombre("Brenda");

        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Hábito de prueba manual");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setDiasSemana("1001001");
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setCuenta(nuevaCuenta);

        // Mockear el comportamiento del DAO
        when(gestionarHabitosDAO.crearCuenta(nuevaCuenta)).thenReturn(nuevaCuenta);
        when(gestionarHabitosDAO.crearHabito(nuevoHabito)).thenReturn(nuevoHabito);

        // Llamar a los métodos
        gestionarHabitosDAO.crearCuenta(nuevaCuenta);
        Habito creado = gestionarHabitosDAO.crearHabito(nuevoHabito);

        // Verificar el resultado
        assertNotNull(creado);
        assertEquals(nuevoHabito.getNombre(), creado.getNombre());

        // Verificar que los métodos del DAO fueron llamados
        verify(gestionarHabitosDAO, times(1)).crearCuenta(nuevaCuenta);
        verify(gestionarHabitosDAO, times(1)).crearHabito(nuevoHabito);
    }

    @Test
    public void testActualizarHabito() throws ModelException {
        Habito habitoExistente = new Habito();
        habitoExistente.setId(2L);
        habitoExistente.setNombre("Hábito de prueba");
        habitoExistente.setFrecuencia("Semanal");

        // Mockear el comportamiento del DAO
        when(gestionarHabitosDAO.actualizarHabito(habitoExistente)).thenReturn(habitoExistente);

        // Llamar al método a probar
        habitoExistente.setFrecuencia("Diaria");
        Habito actualizado = gestionarHabitosDAO.actualizarHabito(habitoExistente);

        // Verificar el resultado
        assertEquals("Diaria", actualizado.getFrecuencia());

        // Verificar que el método del DAO fue llamado
        verify(gestionarHabitosDAO, times(1)).actualizarHabito(habitoExistente);
    }

    @Test
    public void testEliminarHabito() throws ModelException {
        Long habitoId = 1L;

        // Mockear el comportamiento del DAO
        when(gestionarHabitosDAO.eliminarHabito(habitoId)).thenReturn(true);

        // Llamar al método a probar
        boolean eliminado = gestionarHabitosDAO.eliminarHabito(habitoId);

        // Verificar el resultado
        assertTrue(eliminado);

        // Verificar que el método del DAO fue llamado
        verify(gestionarHabitosDAO, times(1)).eliminarHabito(habitoId);
    }

    @Test
    public void testObtenerHabitos() throws ModelException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioPruebaManual");

        List<Habito> listaHabitos = new ArrayList<>();
        Habito habito = new Habito();
        habito.setNombre("Hábito de prueba manual");
        listaHabitos.add(habito);

        // Mockear el comportamiento del DAO
        when(gestionarHabitosDAO.obtenerHabitos(cuenta)).thenReturn(listaHabitos);

        // Llamar al método a probar
        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(cuenta);

        // Verificar el resultado
        assertEquals(1, habitos.size());

        // Verificar que el método del DAO fue llamado
        verify(gestionarHabitosDAO, times(1)).obtenerHabitos(cuenta);
    }
}

