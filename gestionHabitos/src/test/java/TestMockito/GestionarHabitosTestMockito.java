/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;

import Test.GestionarHabitosTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.IGestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

public class GestionarHabitosTestMockito {

    @Mock
    private IConexion mockConexion;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    @InjectMocks
    private GestionarHabitosDAO gestionarHabitosDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockConexion.crearConexion()).thenReturn(mockEntityManager);
        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        gestionarHabitosDAO = new GestionarHabitosDAO(mockConexion);
    }

    /**
     * Prueba para verificar la creación exitosa de una cuenta. Se asegura de
     * que el método {@link CuentaDAO#crearCuenta(Cuenta)} persista la cuenta,
     * inicie y confirme la transacción.
     */
    @Test
    void testCrearCuenta() throws ModelException {
        Cuenta cuenta = new Cuenta("usuario123", "password123", "usuario1237");

        doNothing().when(mockTransaction).begin();
        doNothing().when(mockEntityManager).persist(cuenta);
        doNothing().when(mockTransaction).commit();

        Cuenta cuentaCreada = gestionarHabitosDAO.crearCuenta(cuenta);

        verify(mockEntityManager, times(1)).persist(cuenta);
        verify(mockTransaction, times(1)).begin();
        verify(mockTransaction, times(1)).commit();

        assertEquals(cuenta, cuentaCreada);
    }

    @Test
    void testConsultarCuenta_CuentaExistente() throws ModelException {
        // Arrange
        String usuario = "testUser";
        String contraseña = "testPassword";

        Cuenta expectedCuenta = new Cuenta();
        expectedCuenta.setUsuario(usuario);
        expectedCuenta.setContrasena(contraseña);

        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Cuenta> criteriaQuery = mock(CriteriaQuery.class);
        Root<Cuenta> cuentaRoot = mock(Root.class);

        when(mockEntityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Cuenta.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Cuenta.class)).thenReturn(cuentaRoot);
        when(cuentaRoot.get("usuario")).thenReturn(mock(Path.class));
        when(cuentaRoot.get("contrasena")).thenReturn(mock(Path.class));

        // Configurando los predicados
        Predicate usuarioPredicate = mock(Predicate.class);
        Predicate contrasenaPredicate = mock(Predicate.class);
        when(cb.equal(cuentaRoot.get("usuario"), usuario)).thenReturn(usuarioPredicate);
        when(cb.equal(cuentaRoot.get("contrasena"), contraseña)).thenReturn(contrasenaPredicate);

        // Configurando el query
        when(criteriaQuery.select(cuentaRoot)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(cb.and(usuarioPredicate, contrasenaPredicate))).thenReturn(criteriaQuery);

        // Simulando el resultado de la consulta
        when(mockEntityManager.createQuery(criteriaQuery)).thenReturn(mock(TypedQuery.class));
        when(mockEntityManager.createQuery(criteriaQuery).getResultList()).thenReturn(Collections.singletonList(expectedCuenta));

        // Act
        Cuenta cuentaConsultada = gestionarHabitosDAO.consultarCuenta(usuario, contraseña);

        // Assert
        assertEquals(expectedCuenta, cuentaConsultada);
    }

    @Test
    void testConsultarCuentaPorUsuario_CuentaExistente() throws ModelException {
        // Arrange
        String usuario = "usuarioPrueba";
        Cuenta cuentaEsperada = new Cuenta();
        cuentaEsperada.setUsuario(usuario);
        cuentaEsperada.setContrasena("contrasenaPrueba");
        cuentaEsperada.setNombre("Nombre Prueba");

        TypedQuery<Cuenta> mockQuery = mock(TypedQuery.class);

        when(mockEntityManager.createQuery("SELECT c FROM Cuenta c WHERE c.usuario = :usuario", Cuenta.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter("usuario", usuario)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(cuentaEsperada);

        // Act
        Cuenta cuentaConsultada = gestionarHabitosDAO.consultarCuentaPorUsuario(usuario);

        // Assert
        assertNotNull(cuentaConsultada);
        assertEquals(cuentaEsperada.getUsuario(), cuentaConsultada.getUsuario());
    }

    @Test
    void testCuentaExiste_CuentaExistente() throws ModelException {
        // Arrange
        String usuario = "usuarioExistente";
        Cuenta cuentaExistente = new Cuenta();
        cuentaExistente.setUsuario(usuario);

        TypedQuery<Cuenta> mockQuery = mock(TypedQuery.class);

        when(mockEntityManager.createQuery("SELECT c FROM Cuenta c WHERE c.usuario = :usuario", Cuenta.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter("usuario", usuario)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(Collections.singletonList(cuentaExistente));

        // Act
        boolean existe = gestionarHabitosDAO.cuentaExiste(usuario);

        // Assert
        assertTrue(existe);
    }

    @Test
    void testCrearHabito_Exito() throws ModelException {
        // Arrange
        Habito nuevoHabito = new Habito();
        nuevoHabito.setFrecuencia("Diaria");
        nuevoHabito.setNombre("Ejercicio");
        nuevoHabito.setDiasSemana("1111110"); // Asumiendo que representa todos los días menos el domingo
        nuevoHabito.setFechaCreacion(new Date());

        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockTransaction.isActive()).thenReturn(false);

        // Act
        Habito habitoCreado = gestionarHabitosDAO.crearHabito(nuevoHabito);

        // Assert
        assertNotNull(habitoCreado);
        assertEquals(nuevoHabito.getNombre(), habitoCreado.getNombre());
        verify(mockEntityManager).persist(nuevoHabito);
        verify(mockTransaction).commit();
    }

    @Test
    void testActualizarHabito_Exito() throws ModelException {
        // Arrange
        Long idHabito = 1L;
        Habito habitoExistente = new Habito();
        habitoExistente.setId(idHabito);
        habitoExistente.setFrecuencia("Diaria");
        habitoExistente.setNombre("Ejercicio");
        habitoExistente.setDiasSemana("1111110");
        habitoExistente.setFechaCreacion(new Date());

        Habito habitoActualizado = new Habito();
        habitoActualizado.setId(idHabito);
        habitoActualizado.setFrecuencia("Semanal");
        habitoActualizado.setFechaCreacion(new Date());
        habitoActualizado.setDiasSemana("0000010"); // Solo un día a la semana
        habitoActualizado.setCuenta(habitoExistente.getCuenta());

        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockEntityManager.find(Habito.class, idHabito)).thenReturn(habitoExistente);

        // Act
        Habito resultado = gestionarHabitosDAO.actualizarHabito(habitoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Semanal", resultado.getFrecuencia());
        assertEquals(habitoExistente.getId(), resultado.getId());
        verify(mockEntityManager).find(Habito.class, idHabito);
        verify(mockTransaction).commit();
    }

    @Test
    void testEliminarHabito_Exito() throws ModelException {
        // Arrange
        Long idHabito = 1L;
        Habito habitoEncontrado = new Habito();
        habitoEncontrado.setId(idHabito);
        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockTransaction.isActive()).thenReturn(false);

        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockEntityManager.find(Habito.class, idHabito)).thenReturn(habitoEncontrado);

        // Act
        boolean resultado = gestionarHabitosDAO.eliminarHabito(idHabito);

        // Assert
        assertTrue(resultado);
        verify(mockEntityManager).createQuery("DELETE FROM HistorialHabitos h WHERE h.habito.id = :idHabito");
        verify(mockEntityManager).remove(habitoEncontrado);
        verify(mockTransaction).commit();
    }

    @Test
    void testObtenerHabitosExito() throws ModelException {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuario1");

        Habito habito1 = new Habito();
        habito1.setNombre("Ejercicio");

        Habito habito2 = new Habito();
        habito2.setNombre("Leer");

        List<Habito> listaHabitos = Arrays.asList(habito1, habito2);

        when(mockEntityManager.createQuery(
                "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class))
                .thenReturn(mock(TypedQuery.class));

        when(mockEntityManager.createQuery(
                "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class)
                .setParameter("usuario", cuenta.getUsuario()))
                .thenReturn(mock(TypedQuery.class));

        when(mockEntityManager.createQuery(
                "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class)
                .setParameter("usuario", cuenta.getUsuario()).getResultList())
                .thenReturn(listaHabitos);

        // Act
        List<Habito> resultado = gestionarHabitosDAO.obtenerHabitos(cuenta);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ejercicio", resultado.get(0).getNombre());
        assertEquals("Leer", resultado.get(1).getNombre());
    }

}
