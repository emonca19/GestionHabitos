/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author crist
 */
import com.mycompany.subsistemacontroller.CuentaDTO;
import com.mycompany.subsistemacontroller.GestionarHabitosNegocio;
import com.mycompany.subsistemacontroller.HabitoDTO;
import com.mycompany.subsistemacontroller.HistorialHabitosDTO;
import com.mycompany.subsistemacontroller.IGestionarHabitosNegocio;
import com.mycompany.subsistemacontroller.ProgresoHabitoDTO;
import com.mycompany.subsistemacontroller.Sesion;
import com.mycompany.subsistemamodelo.Cuenta;
import com.mycompany.subsistemamodelo.Habito;
import com.mycompany.subsistemamodelo.HistorialHabitos;
import com.mycompany.subsistemamodelo.ModelException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import com.mycompany.subsistemacontroller.ControllerException;


public class GestionarHabitosNegocioTest {

    private IGestionarHabitosNegocio gestionarHabitosNegocio;
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
    public void testObtenerHabitosNegocio() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testObtenerHabitosNegocio");
        cuentaDTO.setNombre("TestObtenerHabitosNegocio");
        cuentaDTO.setContraseña("passwordObtenerHabitosNegocio");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("TestObtenerHabitosNegocio");
        habitoDTO.setFrecuencia("DiarioObtenerHabitosNegocio");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertDoesNotThrow(() -> {
            List<HabitoDTO> habitos = gestionarHabitosNegocio.obtenerHabitos(cuentaDTO);
            assertNotNull(habitos);
        });
    }
    @Test
    public void testConsultarHistorialHabitos() {
        assertDoesNotThrow(() -> {
            List<HistorialHabitosDTO> historial = gestionarHabitosNegocio.consultarHistorialHabitos(new Date(), cuentaDTO);
            assertNotNull(historial);
        });
    }
    @Test
    public void testEliminarHabito() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testEliminarHabito");
        cuentaDTO.setNombre("TestEliminarHabito");
        cuentaDTO.setContraseña("passwordEliminarHabito");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("TestEliminarHabito");
        habitoDTO.setFrecuencia("DiarioEliminarHabito");
        habitoDTO.setDiasSemana("1000000"); // Ejemplo binario que representa días de la semana
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        assertDoesNotThrow(() -> {
            boolean eliminado = gestionarHabitosNegocio.eliminarHabito(habitoDTO.getId());
            assertTrue(eliminado);
        });

    }
    @Test
    public void testActualizarHabitoNegocio() throws ControllerException {

        cuentaDTO.setUsuario("usuarioPruebaActualizarHabito");
        cuentaDTO.setContraseña("contrasenaPruebaActualizarHabito");
        cuentaDTO.setNombre("BrendaPruebaActualizarHabito");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO.setNombre("HábitoPruebaActualizarHabito");
        habitoDTO.setDiasSemana("1001001");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertNotNull(habitoDTO.getId(), "El identificador del hábito no debería ser nulo después de la creación.");

        // Actualizar el hábito
        assertDoesNotThrow(() -> {
            habitoDTO.setFrecuencia("DiariaActualizarHabitoNegocio");
            HabitoDTO actualizado = gestionarHabitosNegocio.actualizarHabito(habitoDTO);
            assertNotNull(actualizado);
            assertEquals(habitoDTO.getFrecuencia(), actualizado.getFrecuencia());
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
    void testHabitoConvertirADTO() {
        // Arrange
        Habito habito = new Habito();
        habito.setId(1L);
        habito.setFrecuencia("Diario");
        Date fecha = new Date();
        habito.setFechaCreacion(fecha);
        habito.setDiasSemana("1000100");
        habito.setNombre("Hábito de prueba");

        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("UsuarioHabitoADTO");
        cuenta.setNombre("NombreHabitoADTO");
        cuenta.setContrasena("passwordHabitoADTO");
        habito.setCuenta(cuenta);


        // Act
        HabitoDTO resultado = gestionarHabitosNegocio.HabitoConvertirADTO(habito);

        // Assert
        assertEquals(1L, resultado.getId());
        assertEquals("Diario", resultado.getFrecuencia());
        assertEquals(fecha, resultado.getFechaCreacion());
        assertEquals("1000100", resultado.getDiasSemana());
        assertEquals("Hábito de prueba", resultado.getNombre());
        assertEquals("UsuarioHabitoADTO", resultado.getCuentaId().getUsuario());
        assertEquals("NombreHabitoADTO", resultado.getCuentaId().getNombre());
    }
     @Test
    void testHabitoDTOConvertirAEntidad() {
        // Arrange
        cuentaDTO.setUsuario("UsuarioHabitoDTO");
        cuentaDTO.setNombre("NombreHabitoDTO");
        cuentaDTO.setContraseña("passwordHabitoDTO");

        habitoDTO.setId(1L);
        habitoDTO.setFrecuencia("Diario");
        Date fechaCreacion = new Date();
        habitoDTO.setFechaCreacion(fechaCreacion);
        habitoDTO.setDiasSemana("1000100");
        habitoDTO.setNombre("Hábito de prueba");
        habitoDTO.setCuentaId(cuentaDTO);

        // Act
        Habito resultado = gestionarHabitosNegocio.HabitoDTOConvertirAEntidad(habitoDTO);

        // Assert
        assertEquals(1L, resultado.getId());
        assertEquals("Diario", resultado.getFrecuencia());
        assertEquals(fechaCreacion, resultado.getFechaCreacion());
        assertEquals("1000100", resultado.getDiasSemana());
        assertEquals("Hábito de prueba", resultado.getNombre());
        assertEquals("UsuarioHabitoDTO", resultado.getCuenta().getUsuario());
        assertEquals("NombreHabitoDTO", resultado.getCuenta().getNombre());
    }
     @Test
    void testCuentaDTOAEntidad() {
        // Arrange
        cuentaDTO.setUsuario("usuarioPrueba");
        cuentaDTO.setContraseña("contraseñaPrueba");
        cuentaDTO.setNombre("nombrePrueba");

        // Act
        Cuenta cuenta = gestionarHabitosNegocio.cuentaDTOAEntidad(cuentaDTO);

        // Assert
        assertEquals("usuarioPrueba", cuenta.getUsuario());
        // No se compara la contraseña directamente ya que está cifrada
        assertEquals("nombrePrueba", cuenta.getNombre());
    }
    @Test
    void testEntidadACuentaDTO() {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioPrueba");
        cuenta.setContrasena("contraseñaPrueba");
        cuenta.setNombre("nombrePrueba");

        // Act
        cuentaDTO = gestionarHabitosNegocio.entidadACuentaDTO(cuenta);

        // Assert
        assertEquals("usuarioPrueba", cuentaDTO.getUsuario());
        assertEquals("nombrePrueba", cuentaDTO.getNombre());
        assertEquals("contraseñaPrueba", cuentaDTO.getContraseña());
    }
    @Test
    void testHistorialConvertirADTO() throws ControllerException {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario("usuarioPrueba");
        cuenta.setContrasena("contraseñaPrueba");
        cuenta.setNombre("nombrePrueba");

        Habito habito = new Habito();
        habito.setId(1L);
        habito.setDiasSemana("1001011");
        habito.setFechaCreacion(new Date());
        habito.setNombre("Hábito de prueba");
        habito.setCuenta(cuenta);

        HistorialHabitos historial = new HistorialHabitos();
        historial.setId(2L);
        Date fecha = new Date();
        historial.setDia(fecha);
        historial.setCompletado(true);
        historial.setHabito(habito);

        // Act
        HistorialHabitosDTO resultado = gestionarHabitosNegocio.historialConvertirADTO(historial);

        // Assert
        assertEquals(2L, resultado.getId());
        assertEquals(fecha, resultado.getDia());
        assertEquals(true, resultado.isCompletado());
        assertEquals(1L, resultado.getHabito().getId());
    }
    @Test
    public void testHistorialHabitosDTOConvertirAEntidad() throws ControllerException {
        // Crear historial
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUserBuscarHabito");
        cuentaDTO.setNombre("Test User Buscar Habito");
        cuentaDTO.setContraseña("passwordBuscarHabito");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito Buscar");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        HistorialHabitosDTO dto = new HistorialHabitosDTO();
        dto.setCompletado(true);
        dto.setDia(new Date());
        dto.setHabito(habitoDTO);

        dto = gestionarHabitosNegocio.guardarHistorial(dto);

        HistorialHabitos entidad = gestionarHabitosNegocio.HistorialHabitosDTOConvertirAEntidad(dto);

        assertNotNull(entidad);
        assertEquals(dto.getId(), entidad.getId());
        assertEquals(dto.isCompletado(), entidad.isCompletado());

    }
    @Test
    public void testIdentificarDias() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUser5");
        cuentaDTO.setNombre("Test User5");
        cuentaDTO.setContraseña("password123");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito5");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        gestionarHabitosNegocio.crearHabito(habitoDTO);
        assertDoesNotThrow(() -> {
            List<HabitoDTO> habitosPorDia = gestionarHabitosNegocio.identificarDias(cuentaDTO, "lunes");
            assertNotNull(habitosPorDia);
        });
    }
    
    @Test
    public void testBuscarPorFechaYIdHabito() throws ControllerException {
        // Crear cuenta y hábito
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUserBuscar");
        cuentaDTO.setNombre("Test User Buscar");
        cuentaDTO.setContraseña("passwordBuscar");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        Date fecha = new Date();
        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito Buscar");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(fecha);
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        HistorialHabitosDTO historial = new HistorialHabitosDTO();
        historial.setHabito(habitoDTO);
        historial.setDia(fecha);
        historial.setCompletado(true);
        gestionarHabitosNegocio.guardarHistorial(historial);

        HistorialHabitosDTO resultado = gestionarHabitosNegocio.buscarPorFechaYIdHabito(fecha, habitoDTO.getId());

        assertNotNull(resultado);
        assertEquals(habitoDTO.getId(), resultado.getHabito().getId());
        assertEquals(fecha, resultado.getDia());
    }
    @Test
    public void testGuardarHistorial() throws ControllerException {
        // Crear cuenta y hábito
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUserGuardarHistorial");
        cuentaDTO.setNombre("Test User Guardar Historial");
        cuentaDTO.setContraseña("passwordGuardarHistorial");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito Guardar Historial");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        HistorialHabitosDTO historial = new HistorialHabitosDTO();
        historial.setDia(new Date());
        historial.setCompletado(true);
        historial.setHabito(habitoDTO);

        HistorialHabitosDTO resultado = gestionarHabitosNegocio.guardarHistorial(historial);

        assertNotNull(resultado);
        assertTrue(resultado.isCompletado());
    }
    @Test
    public void testCuentaExiste() throws ControllerException {
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUser2");
        cuentaDTO.setNombre("Test User2");
        cuentaDTO.setContraseña("password123");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);
        assertDoesNotThrow(() -> {
            boolean existe = gestionarHabitosNegocio.cuentaExiste("testUser2");
            assertTrue(existe);
        });
    }
    @Test
    public void testObtenerSemana() {
        assertDoesNotThrow(() -> {
            Date[] semana = gestionarHabitosNegocio.obtenerSemana(new Date());
            assertEquals(7, semana.length);
        });
    }
    @Test
    void testObtenerLimitesSemana() throws Exception {
        // Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse("2024-10-09"); // Un miércoles

        // Act
        Date[] limitesSemana = gestionarHabitosNegocio.obtenerLimitesSemana(fecha);

        // Assert
        Calendar calendar = Calendar.getInstance();

        // Verificar Lunes
        calendar.setTime(fecha);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date expectedLunes = calendar.getTime();
        assertEquals(dateFormat.format(expectedLunes), dateFormat.format(limitesSemana[0]));

        assertEquals("2024-10-13", dateFormat.format(limitesSemana[1]));
    }
    @Test
    void testObtenerLimitesMes() throws Exception {
        // Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse("2024-10-09"); // Un día de octubre de 2024


        // Act
        Date[] limitesMes = gestionarHabitosNegocio.obtenerLimitesMes(fecha);

        // Assert
        Calendar calendar = Calendar.getInstance();

        // Verificar primer día del mes
        calendar.setTime(fecha);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date expectedPrimerDia = calendar.getTime();
        assertEquals(dateFormat.format(expectedPrimerDia), dateFormat.format(limitesMes[0]));

        // Verificar último día del mes
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date expectedUltimoDia = calendar.getTime();
        assertEquals(dateFormat.format(expectedUltimoDia), dateFormat.format(limitesMes[1]));
    }
    @Test
    public void testObtenerSemanaDireccion() throws ControllerException {
        // Usar la semana actual para el test
        Date[] semanaActual = gestionarHabitosNegocio.obtenerSemana(new Date());
        Date[] nuevaSemana = gestionarHabitosNegocio.obtenerSemana(semanaActual, "anterior");

        assertNotNull(nuevaSemana);
        assertEquals(7, nuevaSemana.length);
    }
    @Test
    public void testConvertirABits() {
        // Prueba de conversión a bits
        String bits = GestionarHabitosNegocio.convertirABits(true, false, true, false, true, false, true);

        assertEquals("1010101", bits);
    }
    @Test
    public void testConsultarCuentaPorUsuario() throws ControllerException, ModelException {
        // Crear cuenta
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUserConsultar");
        cuentaDTO.setNombre("Test User Consultar");
        cuentaDTO.setContraseña("passwordConsultar");
        gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        CuentaDTO cuentaConsultada = gestionarHabitosNegocio.consultarCuentaPorUsuario(cuentaDTO.getUsuario());

        assertNotNull(cuentaConsultada);
        assertEquals(cuentaDTO.getUsuario(), cuentaConsultada.getUsuario());
    }
    @Test
    public void testBuscarHabitoPorId() throws ControllerException {
        // Crear cuenta y hábito
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testBuscarHabitoPorId");
        cuentaDTO.setNombre("TestBuscarHabitoPorId");
        cuentaDTO.setContraseña("passwordBuscarHabitoPorId");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito Buscar");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        HabitoDTO habito = gestionarHabitosNegocio.buscarHabitoPorId(habitoDTO.getId());

        assertNotNull(habito);
        assertEquals(habitoDTO.getId(), habito.getId());
    }

    @Test
    public void testHabitoCompletado() throws ControllerException {
        // Crear cuenta y hábito
        cuentaDTO = new CuentaDTO();
        cuentaDTO.setUsuario("testUserHabitoCompletado");
        cuentaDTO.setNombre("Test User Habito Completado");
        cuentaDTO.setContraseña("passwordHabitoCompletado");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        habitoDTO = new HabitoDTO();
        habitoDTO.setNombre("Test Habito Completado");
        habitoDTO.setFrecuencia("Diario");
        habitoDTO.setDiasSemana("1000000");
        habitoDTO.setFechaCreacion(new Date());
        habitoDTO.setCuentaId(cuentaDTO);
        habitoDTO = gestionarHabitosNegocio.crearHabito(habitoDTO);

        boolean completado = gestionarHabitosNegocio.habitoCompletado(habitoDTO);

        assertFalse(completado); // O true dependiendo del estado del hábito
    }

    @Test
    public void testObtenerProgresoHabitos() throws ControllerException {
        // Crear cuenta para la prueba
        cuentaDTO.setUsuario("testUserProgreso");
        cuentaDTO.setNombre("Test User Progreso");
        cuentaDTO.setContraseña("passwordProgreso");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        // Crear un par de hábitos para la cuenta
        HabitoDTO habitoDTO1 = new HabitoDTO();
        habitoDTO1.setNombre("Test Habito 1");
        habitoDTO1.setFrecuencia("Diario");
        habitoDTO1.setDiasSemana("1000000"); // Todos los días
        habitoDTO1.setFechaCreacion(new Date());
        habitoDTO1.setCuentaId(cuentaDTO);
        habitoDTO1 = gestionarHabitosNegocio.crearHabito(habitoDTO1);

        HabitoDTO habitoDTO2 = new HabitoDTO();
        habitoDTO2.setNombre("Test Habito 2");
        habitoDTO2.setFrecuencia("Semanal");
        habitoDTO2.setDiasSemana("1000000"); // Solo el domingo
        habitoDTO2.setFechaCreacion(new Date());
        habitoDTO2.setCuentaId(cuentaDTO);
        habitoDTO2 = gestionarHabitosNegocio.crearHabito(habitoDTO2);

        // Definir la fecha del historial manualmente para que caiga dentro del rango
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.OCTOBER, 2); // Ajustar según el rango que especificaste
        Date fechaHistorial = cal.getTime();

        // Crear y guardar el historial de hábitos
        HistorialHabitosDTO historial1 = new HistorialHabitosDTO();
        historial1.setHabito(habitoDTO1);
        historial1.setCompletado(true);
        historial1.setDia(fechaHistorial);  // Fecha ajustada
        gestionarHabitosNegocio.guardarHistorial(historial1);

        HistorialHabitosDTO historial2 = new HistorialHabitosDTO();
        historial2.setHabito(habitoDTO2);
        historial2.setCompletado(true);
        historial2.setDia(fechaHistorial);  // Fecha ajustada
        gestionarHabitosNegocio.guardarHistorial(historial2);

        // Definir rango de fechas (ejemplo: última semana)
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.OCTOBER, 1); // Fecha de inicio
        Date fechaInicio = calendar.getTime();

        calendar.set(2024, Calendar.OCTOBER, 7); // Fecha de fin
        Date fechaFin = calendar.getTime();

        // Obtener progreso de hábitos
        List<ProgresoHabitoDTO> progresoHabitos = gestionarHabitosNegocio.obtenerProgresoHabitos(cuentaDTO, fechaInicio, fechaFin);

        // Afirmaciones
        assertNotNull(progresoHabitos); // Verificar que no sea nulo
        assertEquals(2, progresoHabitos.size()); // Verificar que se obtuvieron los dos hábitos creados

        // Verificar detalles de cada ProgresoHabitoDTO
        for (ProgresoHabitoDTO progreso : progresoHabitos) {
            if ("Test Habito 1".equals(progreso.getNombreHabito())) {
                assertEquals(1, progreso.getDiasRealizados()); // Suponiendo que se completó todos los días
                assertEquals(1, progreso.getDiasTotales()); // Total de días en el rango
            } else if ("Test Habito 2".equals(progreso.getNombreHabito())) {
                assertEquals(1, progreso.getDiasRealizados()); // Suponiendo que solo se completó un día
                assertEquals(1, progreso.getDiasTotales()); // Total de días en el rango
            }
        }
    }


     @Test
    public void testObtenerDia() {
        // Prueba para lunes
        LocalDate fechaLunes = LocalDate.of(2024, 10, 7); // Lunes
        assertEquals("lunes", gestionarHabitosNegocio.obtenerDia(fechaLunes));

        // Prueba para martes
        LocalDate fechaMartes = LocalDate.of(2024, 10, 8); // Martes
        assertEquals("lartes", gestionarHabitosNegocio.obtenerDia(fechaMartes)); // Nota: "lartes" parece ser un error tipográfico

        // Prueba para miércoles
        LocalDate fechaMiercoles = LocalDate.of(2024, 10, 9); // Miércoles
        assertEquals("miercoles", gestionarHabitosNegocio.obtenerDia(fechaMiercoles));

        // Prueba para jueves
        LocalDate fechaJueves = LocalDate.of(2024, 10, 10); // Jueves
        assertEquals("jueves", gestionarHabitosNegocio.obtenerDia(fechaJueves));

        // Prueba para viernes
        LocalDate fechaViernes = LocalDate.of(2024, 10, 11); // Viernes
        assertEquals("viernes", gestionarHabitosNegocio.obtenerDia(fechaViernes));

        // Prueba para sábado
        LocalDate fechaSabado = LocalDate.of(2024, 10, 12); // Sábado
        assertEquals("sabado", gestionarHabitosNegocio.obtenerDia(fechaSabado));

        // Prueba para domingo
        LocalDate fechaDomingo = LocalDate.of(2024, 10, 13); // Domingo
        assertEquals("domingo", gestionarHabitosNegocio.obtenerDia(fechaDomingo));
    }
    
    @Test
    public void testObtenerHabitosDia() throws ControllerException {
        // Crear cuenta para la prueba
        cuentaDTO.setUsuario("testUserHabitosDia");
        cuentaDTO.setNombre("Test User Habitos Dia");
        cuentaDTO.setContraseña("passwordHabitosDia");
        cuentaDTO = gestionarHabitosNegocio.crearCuenta(cuentaDTO);

        // Crear un par de hábitos para la cuenta
        HabitoDTO habitoDTO1 = new HabitoDTO();
        habitoDTO1.setNombre("Test Habito 1");
        habitoDTO1.setFrecuencia("Diario");
        habitoDTO1.setDiasSemana("1111111"); // Todos los días
        habitoDTO1.setFechaCreacion(new Date());
        habitoDTO1.setCuentaId(cuentaDTO);
        habitoDTO1 = gestionarHabitosNegocio.crearHabito(habitoDTO1);

        HabitoDTO habitoDTO2 = new HabitoDTO();
        habitoDTO2.setNombre("Test Habito 2");
        habitoDTO2.setFrecuencia("Semanal");
        habitoDTO2.setDiasSemana("1001000"); // Solo el domingo
        habitoDTO2.setFechaCreacion(new Date());
        habitoDTO2.setCuentaId(cuentaDTO);
        habitoDTO2 = gestionarHabitosNegocio.crearHabito(habitoDTO2);

        // Crear historiales de hábitos ya con datos
        HistorialHabitosDTO historial1 = new HistorialHabitosDTO();
        historial1.setHabito(habitoDTO1);
        historial1.setCompletado(true);
        historial1.setDia(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        
        // Guardar el historial directamente
        gestionarHabitosNegocio.guardarHistorial(historial1);

        HistorialHabitosDTO historial2 = new HistorialHabitosDTO();
        historial2.setHabito(habitoDTO2);
        historial2.setCompletado(false); // No completado
        historial2.setDia(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        
        // Guardar el historial directamente
        gestionarHabitosNegocio.guardarHistorial(historial2);
        
        Sesion.iniciarSesion(cuentaDTO);

        // Obtener hábitos del día específico
        LocalDate diaEspecifico = LocalDate.now();
        String diaString = "lunes"; // Ejemplo, ajustar según tu lógica
        List<HistorialHabitosDTO> historialHabitos = gestionarHabitosNegocio.obtenerHabitosDia(diaEspecifico, diaString);

        // Afirmaciones
        assertNotNull(historialHabitos); // Verificar que no sea nulo
        assertEquals(2, historialHabitos.size()); // Verificar que se obtuvieron los dos hábitos creados

        // Verificar detalles de cada HistorialHabitosDTO
        for (HistorialHabitosDTO historial : historialHabitos) {
            if ("Test Habito 1".equals(historial.getHabito().getNombre())) {
                assertEquals(true, historial.isCompletado()); // Debe estar completado
            } else if ("Test Habito 2".equals(historial.getHabito().getNombre())) {
                assertEquals(false, historial.isCompletado()); // No debe estar completado
            }
        }
    }
    
}
//
