/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 *
 * @author crist
 */
public interface IGestionarHabitosNegocio {

    /**
     * Crea un nuevo hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a crear
     * @return
     * @throws ControllerException si ocurre un error al crear el hábito
     */
    public HabitoDTO crearHabito(HabitoDTO habitoDTO) throws ControllerException;

    /**
     * Crea una cuenta
     *
     * @param cuentaDTO Cuenta a crear
     * @return CuentaDTO creada
     * @throws ControllerException si algo sale mal al crear la cuenta
     */
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) throws ControllerException;

    /**
     * Actualiza un hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a actualizar
     * @return el hábito actualizado convertido a DTO
     * @throws ControllerException si ocurre un error al actualizar el hábito
     */
    public HabitoDTO actualizarHabito(HabitoDTO habitoDTO) throws ControllerException;

    /**
     * Elimina un hábito por su ID.
     *
     * @param id el ID del hábito a eliminar
     * @return true si el hábito fue eliminado, false en caso contrario
     * @throws ControllerException si ocurre un error al eliminar el hábito
     */
    public boolean eliminarHabito(Long id) throws ControllerException;

    /**
     * Obtiene la lista de hábitos asociados a una cuenta específica.
     *
     * @param cuentaDTO el DTO de la cuenta cuyos hábitos se desean obtener
     * @return la lista de hábitos convertidos a DTO
     * @throws ControllerException si ocurre un error al obtener los hábitos
     */
    public List<HabitoDTO> obtenerHabitos(CuentaDTO cuentaDTO) throws ControllerException;

    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @param contraseña Verificar que concuerda con la contraseña
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta
     * correctamente
     */
    public CuentaDTO consultarCuenta(String usuario, String contraseña) throws ControllerException;

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Lista de registros de historial de hábitos que coinciden con la
     * fecha y el ID de hábito.
     * @throws ControllerException Si ocurre un error al buscar
     */
    public HistorialHabitosDTO buscarPorFechaYIdHabito(Date dia, Long idHabito) throws ControllerException;

    public HistorialHabitosDTO guardarHistorial(HistorialHabitosDTO historial) throws ControllerException;

    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta
     * correctamente
     */
    public boolean cuentaExiste(String usuario) throws ControllerException;

    /**
     * Consulta una cuenta por el nombre de usuario proporcionado.
     *
     * Este método busca en la base de datos una cuenta asociada con el nombre
     * de usuario especificado. Si se encuentra la cuenta, se convierte a un
     * objeto CuentaDTO y se retorna. Si no se encuentra ninguna cuenta, el
     * método retornará null.
     *
     * @param usuario el nombre de usuario de la cuenta a consultar. Este valor
     * no debe ser nulo.
     * @return un objeto CuentaDTO que representa la cuenta encontrada, o null
     * si no se encuentra ninguna cuenta asociada con el nombre de usuario
     * proporcionado.
     * @throws ModelException si hay un error al consultar la cuenta, ya sea por
     * problemas en la base de datos o en la lógica de negocio.
     */
    public CuentaDTO consultarCuentaPorUsuario(String usuario) throws ModelException;

    /**
     * Metodo para obtener la semana de un determinado dia
     *
     * @param fecha Fecha a obtener la semana
     * @return Aregglo de los dias de la semana del dia
     */
    public Date[] obtenerSemana(Date fecha);

    /**
     * Metodo para obtener la fecha del Lunes y Domingo de la semana de un
     * determinado día
     *
     * @param fecha Fecha a obtener la semana
     * @return Array con las fechas del Lunes y Domingo de la semana
     */
    public Date[] obtenerLimitesSemana(Date fecha);

    /**
     * Metodo para obtener la fecha del primer y último día del mes de un
     * determinado día
     *
     * @param fecha Fecha a obtener los límites del mes
     * @return Array con las fechas del primer y último día del mes
     */
    public Date[] obtenerLimitesMes(Date fecha);

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
     * @throws org.itson.pruebas.gestionhabitos.controller.ControllerException
     * @throws IllegalArgumentException Si el array `semanaActual` es nulo, no
     * contiene exactamente 7 elementos, o si el valor de `direccion` no es
     * `"anterior"` o `"posterior"`.
     */
    public Date[] obtenerSemana(Date[] semanaActual, String direccion) throws ControllerException;

    /**
     * Busca un hábito por su ID.
     *
     * @param id el ID del hábito a buscar.
     * @return el hábito encontrado o null si no se encuentra.
     * @throws ModelException Si hay un error al buscar el hábito.
     */
    public HabitoDTO buscarHabitoPorId(Long id) throws ControllerException;

    public List<HistorialHabitosDTO> consultarHistorialHabitos(Date date, CuentaDTO cuentaDTO) throws ControllerException;

    public boolean habitoCompletado(HabitoDTO habitoDTO) throws ControllerException;

    /**
     * Obtiene el progreso de los hábitos de una cuenta en un rango de fechas
     * determinado.
     *
     * @param cuenta La cuenta para la cual se desea obtener el progreso de los
     * hábitos en forma de {@link CuentaDTO}.
     * @param fechaInicio La fecha de inicio del rango de fechas.
     * @param fechaFin La fecha de fin del rango de fechas.
     * @return Una lista de objetos {@link ProgresoHabitoDTO} que representan el
     * progreso de los hábitos de la cuenta en el rango de fechas especificado.
     * @throws ControllerException Si ocurre un error al obtener el progreso de
     * los hábitos.
     */
    public List<ProgresoHabitoDTO> obtenerProgresoHabitos(CuentaDTO cuenta, Date fechaInicio, Date fechaFin) throws ControllerException;

     /** Método que devuelve los hábitos que concuerden con el usuario y el día de
     * la semana.
     *
     * @param cuenta Cuenta a buscar los hábitos.
     * @param diaSemana Día de la semana que concuerde con los hábitos.
     * @return Lista de HabitoDTO que concuerden con las especificaciones.
     * @throws ControllerException Si no se encuentra información.
     */
    public List<HabitoDTO> identificarDias(CuentaDTO cuenta, String diaSemana) throws ControllerException, NullPointerException;
    
    public String obtenerDia(LocalDate hoy);
    
     public List<HistorialHabitosDTO> obtenerHabitosDia(LocalDate dia, String diaString) throws ControllerException, NoSuchElementException;
}
