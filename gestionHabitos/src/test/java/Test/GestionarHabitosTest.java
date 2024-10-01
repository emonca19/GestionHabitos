/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author elimo
 */
public class GestionarHabitosTest {

    private IConexion conexionMock;
    private EntityManager entityManagerMock;
    private EntityTransaction transactionMock;
    private GestionarHabitosDAO gestionarHabitos;

    @BeforeEach
    public void setUp() {
        // Mocking the dependencies
        conexionMock = Mockito.mock(IConexion.class);
        entityManagerMock = Mockito.mock(EntityManager.class);
        transactionMock = Mockito.mock(EntityTransaction.class);

        // Configuración del comportamiento del mock
        when(conexionMock.crearConexion()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(transactionMock);

        // Inicializa la clase que estamos probando
        gestionarHabitos = new GestionarHabitosDAO(conexionMock);
    }

    @Test
    public void testCrearHabito() throws ModelException {
        Habito nuevoHabito = new Habito();
        nuevoHabito.setId(1L);
        nuevoHabito.setNombre("Ejercicio");
        nuevoHabito.setFrecuencia("Diario");
        nuevoHabito.setRealizado(false);
        nuevoHabito.setFecha(new Date());

        // Realiza la operación de creación
        Habito resultado = gestionarHabitos.crearHabito(nuevoHabito);

        // Verifica que el resultado sea el esperado
        assertNotNull(resultado);
        assertEquals(nuevoHabito.getNombre(), resultado.getNombre());

        // Verifica que el método persist se llame una vez
        verify(entityManagerMock, times(1)).persist(nuevoHabito);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testActualizarHabito() throws ModelException {
        Habito habitoExistente = new Habito();
        habitoExistente.setId(1L);
        habitoExistente.setNombre("Ejercicio");
        habitoExistente.setFrecuencia("Diario");
        habitoExistente.setRealizado(false);
        habitoExistente.setFecha(new Date());

        // Mockea la búsqueda del hábito existente
        when(entityManagerMock.find(Habito.class, 1L)).thenReturn(habitoExistente);

        // Crea un nuevo objeto de hábito para actualizar
        Habito habitoActualizar = new Habito();
        habitoActualizar.setId(1L);
        habitoActualizar.setNombre("Correr");
        habitoActualizar.setFrecuencia("Diario");
        habitoActualizar.setRealizado(true);
        habitoActualizar.setFecha(new Date());

        // Realiza la operación de actualización
        Habito resultado = gestionarHabitos.actualizarHabito(habitoActualizar);

        // Verifica que el resultado sea el esperado
        assertNotNull(resultado);
        assertEquals("Correr", resultado.getNombre());

        // Verifica que se actualicen las propiedades
        verify(entityManagerMock, times(1)).find(Habito.class, 1L);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testEliminarHabito() throws ModelException {
        Habito habitoAEliminar = new Habito();
        habitoAEliminar.setId(1L);

        // Mockea la búsqueda del hábito existente
        when(entityManagerMock.find(Habito.class, 1L)).thenReturn(habitoAEliminar);

        // Realiza la operación de eliminación
        boolean resultado = gestionarHabitos.eliminarHabito(habitoAEliminar);

        // Verifica que el resultado sea verdadero
        assertTrue(resultado);

        // Verifica que el método remove se llame una vez
        verify(entityManagerMock, times(1)).remove(habitoAEliminar);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testVerHabitos() throws ModelException {
        // Prepara los hábitos
        List<Habito> listaHabitos = new ArrayList<>();
        listaHabitos.add(new Habito());
        listaHabitos.add(new Habito());

        // Mockea el comportamiento del query
        when(entityManagerMock.createQuery("SELECT h FROM Habito h", Habito.class)).thenReturn(Mockito.mock(TypedQuery.class));
        when(entityManagerMock.createQuery("SELECT h FROM Habito h", Habito.class).getResultList()).thenReturn(listaHabitos);

        // Realiza la operación de ver hábitos
        List<Habito> resultado = (List<Habito>) gestionarHabitos.verHabitos();

        // Verifica que el resultado no sea nulo y tenga el tamaño esperado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }
}