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
import org.itson.pruebas.gestionhabitos.model.Cuenta;
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

        conexionMock = Mockito.mock(IConexion.class);
        entityManagerMock = Mockito.mock(EntityManager.class);
        transactionMock = Mockito.mock(EntityTransaction.class);

        when(conexionMock.crearConexion()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(transactionMock);

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

        Habito resultado = gestionarHabitos.crearHabito(nuevoHabito);

        assertNotNull(resultado);
        assertEquals(nuevoHabito.getNombre(), resultado.getNombre());

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

        when(entityManagerMock.find(Habito.class, 1L)).thenReturn(habitoExistente);

        Habito habitoActualizar = new Habito();
        habitoActualizar.setId(1L);
        habitoActualizar.setNombre("Correr");
        habitoActualizar.setFrecuencia("Diario");
        habitoActualizar.setRealizado(true);
        habitoActualizar.setFecha(new Date());

        Habito resultado = gestionarHabitos.actualizarHabito(habitoActualizar);

        assertNotNull(resultado);
        assertEquals("Correr", resultado.getNombre());

        verify(entityManagerMock, times(1)).find(Habito.class, 1L);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testEliminarHabito() throws ModelException {
        Habito habitoAEliminar = new Habito();
        habitoAEliminar.setId(1L);

        when(entityManagerMock.find(Habito.class, 1L)).thenReturn(habitoAEliminar);

        boolean resultado = gestionarHabitos.eliminarHabito(1L);

        assertTrue(resultado);

        verify(entityManagerMock, times(1)).remove(habitoAEliminar);
        verify(transactionMock, times(1)).commit();
    }

    @Test
    public void testVerHabitos() throws ModelException {
        // Preparar los datos de prueba
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioTest"); // Establecer el usuario de la cuenta de prueba

        List<Habito> listaHabitos = new ArrayList<>();
        listaHabitos.add(new Habito());
        listaHabitos.add(new Habito());

        // Crear mocks
        TypedQuery<Habito> queryMock = Mockito.mock(TypedQuery.class);

        // Configurar el comportamiento de los mocks
        when(entityManagerMock.createQuery("SELECT h FROM Habito h WHERE h.usuario = :usuario", Habito.class)).thenReturn(queryMock);
        when(queryMock.setParameter("usuario", "usuarioTest")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(listaHabitos);

        // Llamar al método bajo prueba
        List<Habito> resultado = gestionarHabitos.obtenerHabitos(cuenta);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

}
