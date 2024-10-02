/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.mockito.*;
import java.util.*;

public class GestionarHabitosTest {

    private GestionarHabitosDAO gestionarHabitosDAO;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta();
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        // Supongamos que tienes una clase de conexión que devuelve el EntityManager
        IConexion conexionMock = mock(IConexion.class);
        when(conexionMock.crearConexion()).thenReturn(entityManager);

        gestionarHabitosDAO = new GestionarHabitosDAO(conexionMock);
    }

    @Test
    public void testCrearCuenta() throws ModelException {

        cuenta.setUsuario("Test User");
        cuenta.setContrasena("testPass");
        cuenta.setNombre("Test User");
        // Invocar el método para crear la cuenta
        gestionarHabitosDAO.crearCuenta(cuenta);

        // Verificar que se invocaron los métodos correctos
        verify(transaction).begin();
        verify(entityManager).persist(cuenta);
        verify(transaction).commit();
    }

    @Test
    public void testCrearHabito() throws ModelException {
        // Crear un nuevo hábito
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Test Habit");
        nuevoHabito.setId(1L);
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setRealizado(false);
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFechaRealizacion(null);
        nuevoHabito.setDiasSemana(1001001L);
        nuevoHabito.setCuenta(cuenta);

        // Simular la persistencia del hábito
        doNothing().when(transaction).begin();
        doNothing().when(entityManager).persist(nuevoHabito);
        doNothing().when(transaction).commit();

        // Actuar - crear el hábito
        Habito creado = gestionarHabitosDAO.crearHabito(nuevoHabito);

        // Aserciones
        assertNotNull(creado);
        assertEquals(nuevoHabito.getNombre(), creado.getNombre());

        // Verificación de transacciones
        verify(transaction, times(1)).begin(); // Se inicia la transacción
        verify(transaction, times(1)).commit(); // Se confirma la transacción
        verify(entityManager, times(1)).persist(nuevoHabito); // Se persiste el hábito
    }

    @Test
    public void testActualizarHabito() throws ModelException {
        Habito nuevoHabito = new Habito();
        nuevoHabito.setNombre("Test Habit");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setRealizado(false);
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFechaRealizacion(null);
        nuevoHabito.setDiasSemana(1001001L);
        nuevoHabito.setId(1L);
        nuevoHabito.setCuenta(cuenta);

        when(entityManager.find(Habito.class, 1L)).thenReturn(nuevoHabito);
        doNothing().when(transaction).begin();
        doNothing().when(transaction).commit();

        nuevoHabito.setFrecuencia("Semanal");
        Habito actualizado = gestionarHabitosDAO.actualizarHabito(nuevoHabito);
        assertEquals("Semanal", actualizado.getFrecuencia());
    }

    @Test
    public void testEliminarHabito() throws ModelException {
        Habito nuevoHabito = new Habito();
        nuevoHabito.setId(1L);
        nuevoHabito.setNombre("Test Habit");
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setRealizado(false);
        nuevoHabito.setFechaCreacion(new Date());
        nuevoHabito.setFechaRealizacion(null);
        nuevoHabito.setDiasSemana(1001001L);
        nuevoHabito.setCuenta(cuenta);

        when(entityManager.find(Habito.class, nuevoHabito.getId())).thenReturn(nuevoHabito);
        doNothing().when(transaction).begin();
        doNothing().when(entityManager).remove(nuevoHabito);
        doNothing().when(transaction).commit();

        boolean eliminado = gestionarHabitosDAO.eliminarHabito(nuevoHabito.getId());
        assertTrue(eliminado);
    }

    @Test
    public void testObtenerHabitos() throws ModelException {
        // Crear nuevos hábitos
        Habito nuevoHabito1 = new Habito();
        nuevoHabito1.setNombre("Test Habit 1");
        nuevoHabito1.setFrecuencia("Diaria");
        nuevoHabito1.setRealizado(false);
        nuevoHabito1.setFechaCreacion(new Date());
        nuevoHabito1.setFechaRealizacion(null);
        nuevoHabito1.setDiasSemana(1001001L);
        nuevoHabito1.setCuenta(cuenta);

        Habito nuevoHabito2 = new Habito();
        nuevoHabito2.setNombre("Test Habit 2");
        nuevoHabito2.setFrecuencia("Semanal");
        nuevoHabito2.setRealizado(true);
        nuevoHabito2.setFechaCreacion(new Date());
        nuevoHabito2.setFechaRealizacion(null);
        nuevoHabito2.setDiasSemana(1001001L);
        nuevoHabito2.setCuenta(cuenta);

        List<Habito> listaHabitos = Arrays.asList(nuevoHabito1, nuevoHabito2);

        // Configurar el TypedQuery
        TypedQuery<Habito> mockTypedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Habito.class))).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(listaHabitos); // Este debe ser el método que se llama en la prueba

        // Ejecutar el método que estás probando
        List<Habito> habitos = gestionarHabitosDAO.obtenerHabitos(cuenta);

        // Verificaciones
        assertNotNull(habitos);
        assertEquals(2, habitos.size());
        assertEquals("Test Habit 1", habitos.get(0).getNombre());
        assertEquals("Test Habit 2", habitos.get(1).getNombre());
    }

}
