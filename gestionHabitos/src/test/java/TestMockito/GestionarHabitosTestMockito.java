/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestMockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;
import org.itson.pruebas.gestionhabitos.model.ProgresoHabito;

public class GestionarHabitosTestMockito {

    @Mock
    private IConexion mockConexion;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    @Mock
    private TypedQuery<HistorialHabitos> mockTypedQueryHistorialHabitos;

    @Mock
    private Query mockQuery;

    @Mock
    private CriteriaBuilder mockCriteriaBuilder;

    @Mock
    private CriteriaQuery<Long> mockCriteriaQuery;

    @Mock
    private Root<HistorialHabitos> mockRootHistorial;

    @Mock
    private TypedQuery<Long> mockTypedQuery;

    @InjectMocks
    private GestionarHabitosDAO gestionarHabitosDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockConexion.crearConexion()).thenReturn(mockEntityManager);
        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery); // Agrega esto
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery); // Asegúrate de que setParameter también funcione
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

        // Simulación del comportamiento del EntityManager y la transacción
        when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        when(mockTransaction.isActive()).thenReturn(false);
        when(mockEntityManager.find(Habito.class, idHabito)).thenReturn(habitoEncontrado);
        when(mockEntityManager.createQuery("DELETE FROM HistorialHabitos h WHERE h.habito.id = :idHabito")).thenReturn(mockQuery);

        // Simula el comportamiento de setParameter y su retorno
        when(mockQuery.setParameter("idHabito", idHabito)).thenReturn(mockQuery);

        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery); // Para setParameter
        when(mockQuery.executeUpdate()).thenReturn(1); // Simula una ejecución exitosa de la consulta

        // Act
        boolean resultado = gestionarHabitosDAO.eliminarHabito(idHabito);

        // Assert
        assertTrue(resultado);
        verify(mockQuery).setParameter("idHabito", idHabito); // Verificar que se estableció el parámetro
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

        // Mock para TypedQuery
        TypedQuery<Habito> mockTypedQuery = mock(TypedQuery.class);

        // Configura el comportamiento de createQuery para devolver el TypedQuery mock
        when(mockEntityManager.createQuery(
                "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class))
                .thenReturn(mockTypedQuery);

        // Simula el comportamiento de setParameter y getResultList
        when(mockTypedQuery.setParameter("usuario", cuenta.getUsuario())).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(listaHabitos);

        // Act
        List<Habito> resultado = gestionarHabitosDAO.obtenerHabitos(cuenta);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ejercicio", resultado.get(0).getNombre());
        assertEquals("Leer", resultado.get(1).getNombre());
    }

    @Test
    void testObtenerHabitos_Exito() throws ModelException {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuario1");

        Habito habito1 = new Habito();
        habito1.setNombre("Ejercicio");

        Habito habito2 = new Habito();
        habito2.setNombre("Leer");

        List<Habito> listaHabitos = Arrays.asList(habito1, habito2);
        TypedQuery<Habito> mockQuery = mock(TypedQuery.class);

        when(mockEntityManager.createQuery(
                "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter("usuario", cuenta.getUsuario())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(listaHabitos);

        // Act
        List<Habito> resultado = gestionarHabitosDAO.obtenerHabitos(cuenta);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ejercicio", resultado.get(0).getNombre());
        assertEquals("Leer", resultado.get(1).getNombre());
    }

    @Test
    void testBuscarHabitoPorId_Exito() throws ModelException {
        // Arrange
        Long idHabito = 1L;
        Habito habitoEsperado = new Habito();
        habitoEsperado.setId(idHabito);

        when(mockEntityManager.find(Habito.class, idHabito)).thenReturn(habitoEsperado);

        // Act
        Habito resultado = gestionarHabitosDAO.buscarHabitoPorId(idHabito);

        // Assert
        assertNotNull(resultado);
        assertEquals(habitoEsperado.getId(), resultado.getId());
    }

    @Test
    void testGuardarYActualizarHistorial_ActualizarExistente() throws ModelException {
        // Arrange
        Date dia = new Date(); // Simula una fecha
        Long idHabito = 1L;

        // Crea un historial existente
        HistorialHabitos historialExistente = new HistorialHabitos();
        historialExistente.setDia(dia);
        historialExistente.setCompletado(false);
        historialExistente.setHabito(new Habito());
        historialExistente.getHabito().setId(idHabito);

        // Crea un nuevo historial que debería actualizar el existente
        HistorialHabitos historialNuevo = new HistorialHabitos();
        historialNuevo.setDia(dia);
        historialNuevo.setCompletado(true);
        historialNuevo.setHabito(historialExistente.getHabito());

        // Mockear el EntityManager para asegurarse de que esté abierto
        when(mockEntityManager.isOpen()).thenReturn(true);

        // Mockear el createQuery para devolver el TypedQuery mockeado
        mockTypedQueryHistorialHabitos = mock(TypedQuery.class);
        when(mockEntityManager.createQuery(anyString(), eq(HistorialHabitos.class)))
                .thenReturn(mockTypedQueryHistorialHabitos);

        // Mockear los parámetros de la consulta
        when(mockTypedQueryHistorialHabitos.setParameter(eq("diaSinHora"), any(Date.class)))
                .thenReturn(mockTypedQueryHistorialHabitos);
        when(mockTypedQueryHistorialHabitos.setParameter(eq("idHabito"), anyLong()))
                .thenReturn(mockTypedQueryHistorialHabitos);

        // Mockear el resultado de la consulta para devolver el historial existente
        when(mockTypedQueryHistorialHabitos.getSingleResult()).thenReturn(historialExistente);

        // Mockear el comportamiento del EntityManager para el merge
        when(mockEntityManager.merge(any(HistorialHabitos.class))).thenReturn(historialExistente);

        // Act
        HistorialHabitos resultado = gestionarHabitosDAO.guardarYActualizarHistorial(historialNuevo);

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isCompletado());
        verify(mockEntityManager).merge(historialExistente); // Verificar que se llamó a merge
    }

    @Test
    void testHabitoCompletado_HabitoCompletado() throws ModelException {
        // Arrange
        Habito habito = new Habito();
        habito.setId(1L);

        Long count = 1L; // Simula que hay 1 registro completado

        // Mockear el comportamiento de la consulta
        when(mockEntityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
        when(mockCriteriaBuilder.createQuery(Long.class)).thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.from(HistorialHabitos.class)).thenReturn(mockRootHistorial);

        Predicate mockHabitoPredicate = mock(Predicate.class);
        Predicate mockCompletadoPredicate = mock(Predicate.class);

        // Mockear los paths intermedios con casting a Path<Object>
        Path<Object> mockPathHabito = mock(Path.class);
        Path<Object> mockPathId = mock(Path.class);

        when(mockRootHistorial.get("habito")).thenReturn(mockPathHabito);
        when(mockPathHabito.get("id")).thenReturn(mockPathId);

        // Simular la comparación de ID y completado
        when(mockCriteriaBuilder.equal(mockPathId, habito.getId()))
                .thenReturn(mockHabitoPredicate);
        when(mockCriteriaBuilder.equal(mockRootHistorial.get("completado"), 1))
                .thenReturn(mockCompletadoPredicate);

        // Mockear el conteo
        when(mockCriteriaQuery.select(mockCriteriaBuilder.count(mockRootHistorial)))
                .thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.where(mockCriteriaBuilder.and(mockHabitoPredicate, mockCompletadoPredicate)))
                .thenReturn(mockCriteriaQuery);

        // Mockear la ejecución de la query
        when(mockEntityManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getSingleResult()).thenReturn(count);

        // Act
        boolean resultado = gestionarHabitosDAO.habitoCompletado(habito);

        // Assert
        assertTrue(resultado);
        verify(mockEntityManager).createQuery(mockCriteriaQuery);
    }

    @Test
    void testHabitoCompletado_HabitoNoCompletado() throws ModelException {
        // Arrange
        Habito habito = new Habito();
        habito.setId(1L);

        Long count = 0L; // Simula que no hay registros completados

        // Mockear el comportamiento de la consulta
        when(mockEntityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
        when(mockCriteriaBuilder.createQuery(Long.class)).thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.from(HistorialHabitos.class)).thenReturn(mockRootHistorial);

        Predicate mockHabitoPredicate = mock(Predicate.class);
        Predicate mockCompletadoPredicate = mock(Predicate.class);

        // Mockear los paths intermedios con casting a Path<Object>
        Path<Object> mockPathHabito = mock(Path.class);
        Path<Object> mockPathId = mock(Path.class);

        when(mockRootHistorial.get("habito")).thenReturn(mockPathHabito);
        when(mockPathHabito.get("id")).thenReturn(mockPathId);

        // Simular la comparación de ID y completado
        when(mockCriteriaBuilder.equal(mockPathId, habito.getId()))
                .thenReturn(mockHabitoPredicate);
        when(mockCriteriaBuilder.equal(mockRootHistorial.get("completado"), 1))
                .thenReturn(mockCompletadoPredicate);

        // Mockear el conteo
        when(mockCriteriaQuery.select(mockCriteriaBuilder.count(mockRootHistorial)))
                .thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.where(mockCriteriaBuilder.and(mockHabitoPredicate, mockCompletadoPredicate)))
                .thenReturn(mockCriteriaQuery);

        // Mockear la ejecución de la query
        when(mockEntityManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getSingleResult()).thenReturn(count);

        // Act
        boolean resultado = gestionarHabitosDAO.habitoCompletado(habito);

        // Assert
        assertFalse(resultado); // Aquí verificamos que el hábito no esté completado
        verify(mockEntityManager).createQuery(mockCriteriaQuery); // Verificamos que se ejecutó la consulta
    }

    @Test
    void testBuscarPorFechaYIdHabito_RegistroEncontrado() throws ModelException {
        // Arrange
        Date dia = new Date(); // Simula una fecha
        Long idHabito = 1L;

        // Crea un historial de hábitos simulado que debería ser devuelto
        HistorialHabitos historialSimulado = new HistorialHabitos();
        historialSimulado.setDia(dia);
        historialSimulado.setCompletado(true);
        historialSimulado.setHabito(new Habito());
        historialSimulado.getHabito().setId(idHabito);

        // Asegúrate de que las horas en la fecha son cero
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date diaSinHora = calendar.getTime();

        // Mockear el EntityManager para asegurarse de que esté abierto
        when(mockEntityManager.isOpen()).thenReturn(true);

        // Mockear la consulta
        TypedQuery<HistorialHabitos> mockTypedQueryHistorial = mock(TypedQuery.class);
        when(mockEntityManager.createQuery(anyString(), eq(HistorialHabitos.class)))
                .thenReturn(mockTypedQueryHistorial);

        // Mockear los parámetros de la consulta
        when(mockTypedQueryHistorial.setParameter("diaSinHora", diaSinHora)).thenReturn(mockTypedQueryHistorial);
        when(mockTypedQueryHistorial.setParameter("idHabito", idHabito)).thenReturn(mockTypedQueryHistorial);

        // Mockear el resultado de la consulta
        when(mockTypedQueryHistorial.getSingleResult()).thenReturn(historialSimulado);

        // Act
        HistorialHabitos resultado = gestionarHabitosDAO.buscarPorFechaYIdHabito(dia, idHabito);

        // Assert
        assertNotNull(resultado);
        assertEquals(historialSimulado, resultado); // Asegúrate de que el resultado es el esperado
        verify(mockEntityManager).createQuery(anyString(), eq(HistorialHabitos.class)); // Verifica que se llamó a createQuery
    }

    @Test
    void testConsultarHistorialHabitos_RegistroEncontrado() throws ModelException {
        // Arrange
        Date date = new Date(); // Simula una fecha
        Cuenta cuenta = new Cuenta(); // Simula una cuenta
        cuenta.setUsuario("rob"); // Establecer un ID para la cuenta

        // Crea una lista simulada de HistorialHabitos que debería ser devuelta
        HistorialHabitos historialSimulado = new HistorialHabitos();
        historialSimulado.setDia(date);
        historialSimulado.setHabito(new Habito());
        historialSimulado.getHabito().setCuenta(cuenta);
        List<HistorialHabitos> historialListSimulado = Collections.singletonList(historialSimulado);

        // Mockear el EntityManager y la Criteria API
        when(mockEntityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);

        // Asegúrate de que mockCriteriaQuery es del tipo correcto
        CriteriaQuery<HistorialHabitos> mockCriteriaQuery = mock(CriteriaQuery.class);
        when(mockCriteriaBuilder.createQuery(HistorialHabitos.class)).thenReturn(mockCriteriaQuery);

        when(mockCriteriaQuery.from(HistorialHabitos.class)).thenReturn(mockRootHistorial);

        // Mockear el objeto que se espera de "habito"
        Root<Object> mockRootHabito = mock(Root.class);
        when(mockRootHistorial.get("habito")).thenReturn(mockRootHabito);

        // Mockear los predicados de la consulta
        Predicate mockDatePredicate = mock(Predicate.class);
        Predicate mockCuentaPredicate = mock(Predicate.class);

        when(mockCriteriaBuilder.equal(mockRootHistorial.get("dia"), date)).thenReturn(mockDatePredicate);
        when(mockCriteriaBuilder.equal(mockRootHabito.get("cuenta"), cuenta)).thenReturn(mockCuentaPredicate);

        // Mockear la construcción de la consulta
        when(mockCriteriaQuery.select(mockRootHistorial)).thenReturn(mockCriteriaQuery);
        when(mockCriteriaQuery.where(mockCriteriaBuilder.and(mockDatePredicate, mockCuentaPredicate))).thenReturn(mockCriteriaQuery);

        // Asegúrate de que mockTypedQuery es del tipo correcto
        TypedQuery<HistorialHabitos> mockTypedQuery = mock(TypedQuery.class);
        when(mockEntityManager.createQuery(mockCriteriaQuery)).thenReturn(mockTypedQuery);

        // Mockear la ejecución de la consulta
        when(mockTypedQuery.getResultList()).thenReturn(historialListSimulado);

        // Act
        List<HistorialHabitos> resultado = gestionarHabitosDAO.consultarHistorialHabitos(date, cuenta);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(historialSimulado, resultado.get(0)); // Asegúrate de que el resultado es el esperado
        verify(mockEntityManager).createQuery(mockCriteriaQuery); // Verifica que se llamó a createQuery
    }

    @Test
    void testObtenerProgresoHabitos_ProgresoEncontrado() throws ModelException {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("rob");// Asigna un usuario a la cuenta

        Date fechaInicio = new Date(); // Simula la fecha de inicio
        Date fechaFin = new Date(); // Simula la fecha de fin (puedes ajustar esto)

        // Crear un mock de HistorialHabitos
        HistorialHabitos historialSimulado = new HistorialHabitos();
        historialSimulado.setDia(fechaInicio);
        historialSimulado.setCompletado(true); // Simula que el hábito fue completado

        // Crear un mock de Habito
        Habito habitoSimulado = new Habito();
        habitoSimulado.setId(1L);
        habitoSimulado.setNombre("Hábito 1");
        habitoSimulado.setDiasSemana("1111111"); // Simula días completados

        // Establecer la relación entre HistorialHabitos y Habito
        historialSimulado.setHabito(habitoSimulado);

        // Crear una lista simulada de resultados
        List<Object[]> resultadoSimulado = new ArrayList<>();
        resultadoSimulado.add(new Object[]{"Hábito 1", 1L, 7});

        // Mockear el comportamiento del EntityManager y Criteria API
        when(mockEntityManager.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);

        // Mockear la consulta
        CriteriaQuery<Object[]> mockQuery = mock(CriteriaQuery.class);
        when(mockCriteriaBuilder.createQuery(Object[].class)).thenReturn(mockQuery);
        Root<HistorialHabitos> mockRootHistorial = mock(Root.class);
        when(mockQuery.from(HistorialHabitos.class)).thenReturn(mockRootHistorial);

        // Mockear el Join
        Join<Object, Object> mockJoinHabito = mock(Join.class);
        when(mockRootHistorial.join("habito", JoinType.LEFT)).thenReturn(mockJoinHabito);

        // Mockear las expresiones
        Expression<Object> mockDiasRealizados = mock(Expression.class);
        Expression<Object> mockDiasTotal = mock(Expression.class);

        // Mockear el selectCase
        CriteriaBuilder.Case<Object> mockSelectCase = mock(CriteriaBuilder.Case.class);
        when(mockCriteriaBuilder.selectCase()).thenReturn(mockSelectCase);

        // Mockear el comportamiento de when y otherwise
        when(mockSelectCase.when(any(), any())).thenReturn(mockSelectCase);
        when(mockSelectCase.otherwise(any())).thenReturn(mockDiasRealizados);

        Expression<Long> mockDiasRealizadosL = mock(Expression.class);
        // Mockear el conteo
        when(mockCriteriaBuilder.count(mockSelectCase)).thenReturn(mockDiasRealizadosL);

        Expression<Integer> mockDiasTotalI = mock(Expression.class);

        // Mockear la longitud
        when(mockCriteriaBuilder.length(any())).thenReturn(mockDiasTotalI);

        // Mockear la construcción de la consulta
        when(mockQuery.multiselect(any(), any(), any())).thenReturn(mockQuery);

        // Cambia la llamada a groupBy para evitar la ambigüedad
        when(mockQuery.groupBy(any(Expression[].class))).thenReturn(mockQuery);

        // Mockear la cláusula where
        Predicate mockPredicate = mock(Predicate.class);
        when(mockQuery.where(any(Predicate[].class))).thenReturn(mockQuery);

        // Mockear la ejecución de la consulta
        TypedQuery<Object[]> mockTypedQuery = mock(TypedQuery.class);
        when(mockEntityManager.createQuery(mockQuery)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(resultadoSimulado);

        // Act
        List<ProgresoHabito> resultado = gestionarHabitosDAO.obtenerProgresoHabitos(cuenta, fechaInicio, fechaFin);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Hábito 1", resultado.get(0).getNombre());
        assertEquals(1, resultado.get(0).getDiasRealizados());
        assertEquals(7, resultado.get(0).getTotalDias());

        // Verificaciones adicionales
        verify(mockEntityManager).getCriteriaBuilder();
        verify(mockEntityManager).createQuery(mockQuery);
    }

}
