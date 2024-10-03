/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.itson.pruebas.gestionhabitos.model.Conexion;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.IGestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 * Clase que implementa la lógica de negocio para gestionar los hábitos.
 */
public class GestionarHabitosNegocio implements IGestionarHabitosNegocio {

    private final IGestionarHabitosDAO habitoDAO;

    /**
     * Constructor de la clase.
     *
     */
    public GestionarHabitosNegocio() {
        IConexion conexion = new Conexion();
        this.habitoDAO = new GestionarHabitosDAO(conexion);
    }

    /**
     * Crea un nuevo hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a crear
     * @throws ControllerException si ocurre un error al crear el hábito
     */
    @Override
    public void crearHabito(HabitoDTO habitoDTO) throws ControllerException {
        if (habitoDTO.sonCamposValidos()) {
            try {
                habitoDAO.crearHabito(HabitoDTOConvertirAEntidad(habitoDTO));
            } catch (ModelException ex) {
                throw new ControllerException(ex);
            }
        } else {
            throw new ControllerException("No se puede crear cuenta porque no cumple con los datos necesarios");
        }
    }

    /**
     * Crea una cuenta
     *
     * @param cuentaDTO Cuenta a crear
     * @throws ControllerException si algo sale mal al crear la cuenta
     */
    @Override
    public void crearCuenta(CuentaDTO cuentaDTO) throws ControllerException {
        if (cuentaDTO.sonCamposValidos()) {
            try {
                habitoDAO.crearCuenta(cuentaDTOAEntidad(cuentaDTO));
            } catch (ModelException ex) {
                throw new ControllerException(ex);
            }
        } else {
            throw new ControllerException("No se puede crear cuenta porque no cumple con los datos necesarios");
        }
    }

    /**
     * Obtiene la lista de hábitos asociados a una cuenta específica.
     *
     * @param cuentaDTO el DTO de la cuenta cuyos hábitos se desean obtener
     * @return la lista de hábitos convertidos a DTO
     * @throws ControllerException si ocurre un error al obtener los hábitos
     */
    @Override
    public List<HabitoDTO> obtenerHabitos(CuentaDTO cuentaDTO) throws ControllerException {
        List<Habito> habitos = null;
        try {
            habitos = habitoDAO.obtenerHabitos(cuentaDTOAEntidad(cuentaDTO));
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
        return habitos.stream().map(this::HabitoConvertirADTO).collect(Collectors.toList());
    }

    /**
     * Elimina un hábito por su ID.
     *
     * @param id el ID del hábito a eliminar
     * @return true si el hábito fue eliminado, false en caso contrario
     * @throws ControllerException si ocurre un error al eliminar el hábito
     */
    @Override
    public boolean eliminarHabito(Long id) throws ControllerException {
        boolean eliminado = false;
        try {
            eliminado = habitoDAO.eliminarHabito(id);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
        return eliminado;
    }

    /**
     * Actualiza un hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a actualizar
     * @return el hábito actualizado convertido a DTO
     * @throws ControllerException si ocurre un error al actualizar el hábito
     */
    @Override
    public HabitoDTO actualizarHabito(HabitoDTO habitoDTO) throws ControllerException {
        try {
            Habito habito = habitoDAO.actualizarHabito(HabitoDTOConvertirAEntidad(habitoDTO));
            return HabitoConvertirADTO(habito);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @param contraseña Verificar que concuerda con la contraseña
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta
     * correctamente
     */
    @Override
    public CuentaDTO consultarCuenta(String usuario, String contraseña) throws ControllerException {
        try {
            return entidadACuentaDTO(habitoDAO.consultarCuenta(usuario, contraseña));
        } catch (ModelException e) {
            throw new ControllerException(e);
        }
    }

    /**
     * Convierte una entidad Habito en un DTO HabitoDTO.
     *
     * @param habito la entidad Habito a convertir
     * @return el DTO HabitoDTO que representa el hábito
     */
    private HabitoDTO HabitoConvertirADTO(Habito habito) {
        return new HabitoDTO(
                habito.getId(),
                habito.getFrecuencia(),
                habito.getFechaCreacion(),
                habito.getDiasSemana(),
                habito.getNombre(),
                entidadACuentaDTO(habito.getCuenta())
        );
    }

    /**
     * Convierte un DTO HabitoDTO en una entidad Habito.
     *
     * @param habitoDTO el DTO HabitoDTO a convertir
     * @return la entidad Habito que representa el hábito
     */
    private Habito HabitoDTOConvertirAEntidad(HabitoDTO habitoDTO) {
        Habito habito = new Habito();
        habito.setId(habitoDTO.getId());
        habito.setDiasSemana(habitoDTO.getDiasSemana());
        habito.setFechaCreacion(habitoDTO.getFechaCreacion());
        habito.setFrecuencia(habitoDTO.getFrecuencia());
        habito.setNombre(habitoDTO.getNombre());
        habito.setCuenta(cuentaDTOAEntidad(habitoDTO.getCuentaId()));
        return habito;
    }

    /**
     * Convierte un DTO CuentaDTO en una entidad Cuenta.
     *
     * @param cuentaDTO el DTO CuentaDTO a convertir
     * @return la entidad Cuenta
     */
    public Cuenta cuentaDTOAEntidad(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta(cuentaDTO.getUsuario(), cuentaDTO.getContraseña(), cuentaDTO.getNombre());

        return cuenta;
    }

    /**
     * Convierte una entidad de cuenta a una cuentaDTO
     *
     * @param cuenta Cuenta a convertir a DTO
     * @return CuenataDTO convertida
     */
    public CuentaDTO entidadACuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO(cuenta.getNombre(), cuenta.getUsuario(), cuenta.getContrasena());
        return cuentaDTO;
    }

    /**
     * Convierte una entidad HistorialHabitos en un DTO HistorialHabitosDTO.
     *
     * @param historial el objeto HistorialHabitos a convertir
     * @return el DTO HistorialHabitosDTO que representa el historial de hábitos
     */
    private HistorialHabitosDTO historialConvertirADTO(HistorialHabitos historial) {
        return new HistorialHabitosDTO(
                historial.getId(),
                historial.getDia(),
                historial.isCompletado(),
                historial.getHabito() // Asumiendo que 'idHabito' no es null
        );
    }

    /**
     * Convierte un DTO HistorialHabitosDTO en una entidad HistorialHabitos.
     *
     * @param historialDTO el DTO HistorialHabitosDTO a convertir
     * @return la entidad HistorialHabitos que representa el historial de
     * hábitos
     */
    private HistorialHabitos historialDTOConvertirAEntidad(HistorialHabitosDTO historialDTO) {
        HistorialHabitos historial = new HistorialHabitos();
        historial.setId(historialDTO.getId());
        historial.setDia(historialDTO.getDia());
        historial.setCompletado(historialDTO.isCompletado());
        historial.setHabito(historialDTO.getHabito()); // Asumiendo que el idHabito es un entero
        return historial;
    }

    /**
     * Metodo que devuelve los habitos que concuerden con el usuario y dia de la
     * semana
     *
     * @param cuenta Cuenta a buscar los habitos
     * @param diaSemana Dia de la semana que concuerde con los habitos
     * @return Lista de habitosDTO que concuerden con las especificaciones
     * @throws ControllerException Si no se encuentra infromacion
     */
    public List<HabitoDTO> identificarDias(CuentaDTO cuenta, String diaSemana) throws ControllerException {
        List<HabitoDTO> habitos = obtenerHabitos(cuenta);
        List<HabitoDTO> habitosPorDia = new ArrayList<>();
        for (int i = 0; i < habitos.size(); i++) {
            String dias = String.valueOf(habitos.get(i).getDiasSemana());
            if (dias.charAt(0) == 1 && diaSemana.equalsIgnoreCase("lunes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(1) == 1 && diaSemana.equalsIgnoreCase("martes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(2) == 1 && diaSemana.equalsIgnoreCase("miercoles")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(3) == 1 && diaSemana.equalsIgnoreCase("jueves")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(4) == 1 && diaSemana.equalsIgnoreCase("viernes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(5) == 1 && diaSemana.equalsIgnoreCase("sabado")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(6) == 1 && diaSemana.equalsIgnoreCase("domingo")) {
                habitosPorDia.add(habitos.get(i));
            } else {
                throw new ControllerException("No se ha encontrado información");
            }

        }
        return habitosPorDia;
    }

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Lista de registros de historial de hábitos que coinciden con la
     * fecha y el ID de hábito.
     * @throws ControllerException Si ocurre un error al buscar
     */
    @Override
    public HistorialHabitosDTO buscarPorFechaYIdHabito(Date dia, Long idHabito) throws ControllerException {
        try {
            return historialConvertirADTO(habitoDAO.buscarPorFechaYIdHabito(dia, idHabito));
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Actualizar un registro de historial de hábitos utilizando la entidad.
     *
     * @param historial Habito a actualizar.
     * @return El registro actualizado de historial de hábitos.
     * @throws ControllerException Si ocurre un error al actualizar.
     */
    @Override
    public HistorialHabitosDTO actualizarHistorial(HistorialHabitosDTO historial) throws ControllerException {
        try {
            HistorialHabitosDTO habito = historialConvertirADTO(habitoDAO.actualizarHistorial(historialDTOConvertirAEntidad(historial)));
            return habito;
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta
     * correctamente
     */
    @Override
    public boolean cuentaExiste(String usuario) throws ControllerException {
        try {
            return habitoDAO.cuentaExiste(usuario);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public HistorialHabitosDTO crearHistorial(HistorialHabitosDTO historial) throws ControllerException {
        try {
            HistorialHabitosDTO habito = historialConvertirADTO(habitoDAO.crearHistorial(historialDTOConvertirAEntidad(historial)));
            return habito;
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Metodo para obtener la semana de un determinado dia
     *
     * @param fecha Fecha a obtener la semana
     * @return Aregglo de los dias de la semana del dia
     */
    @Override
    public Date[] obtenerSemana(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        Date[] semana = new Date[7];
        for (int i = 0; i < 7; i++) {
            semana[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return semana;
    }

    /**
     * Método que toma un array de fechas que representa los días de una semana
     * (del lunes al domingo) y devuelve un nuevo array con los días de la
     * semana anterior o posterior.
     *
     * @param semanaActual Un array de `Date` que contiene exactamente 7
     * elementos, representando una semana completa desde el lunes hasta el
     * domingo.
     * @param direccion Un `String` que indica la dirección a calcular:
     * `"anterior"` o `"posterior"`.
     * @return Un array de `Date` que contiene los días de la semana anterior o
     * posterior, comenzando desde el lunes y terminando el domingo.
     * @throws IllegalArgumentException Si el array `semanaActual` es nulo, no
     * contiene exactamente 7 elementos, o si el valor de `direccion` no es
     * `"anterior"` o `"posterior"`.
     */
    @Override
    public Date[] obtenerSemana(Date[] semanaActual, String direccion) throws ControllerException {
        if (semanaActual == null || semanaActual.length != 7) {
            throw new ControllerException("El array debe contener exactamente 7 días.");
        }

        if (!"anterior".equalsIgnoreCase(direccion) && !"posterior".equalsIgnoreCase(direccion)) {
            throw new ControllerException("La dirección debe ser 'anterior' o 'posterior'.");
        }

        Date[] nuevaSemana = new Date[7];
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(semanaActual[0]);

        int diasAjuste = "anterior".equalsIgnoreCase(direccion) ? -7 : 7;
        calendar.add(Calendar.DAY_OF_MONTH, diasAjuste);
        for (int i = 0; i < 7; i++) {
            nuevaSemana[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return nuevaSemana;
    }

    /**
     * Convierte siete valores booleanos en una cadena de bits.
     *
     * @param b1 Primer valor booleano.
     * @param b2 Segundo valor booleano.
     * @param b3 Tercer valor booleano.
     * @param b4 Cuarto valor booleano.
     * @param b5 Quinto valor booleano.
     * @param b6 Sexto valor booleano.
     * @param b7 Séptimo valor booleano.
     * @return Una cadena de siete caracteres representando los valores
     * booleanos como bits (1 o 0).
     */
    public static String convertirABits(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7) {
        StringBuilder bits = new StringBuilder();

        boolean[] booleanos = {b1, b2, b3, b4, b5, b6, b7};

        for (boolean b : booleanos) {
            bits.append(b ? "1" : "0");
        }

        return bits.toString();
    }

}
