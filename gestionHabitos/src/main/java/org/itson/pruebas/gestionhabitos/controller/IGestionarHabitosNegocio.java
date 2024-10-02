/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.Date;
import java.util.List;

/**
 *
 * @author crist
 */
public interface IGestionarHabitosNegocio {

    /**
     * Crea un nuevo hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a crear
     * @throws ControllerException si ocurre un error al crear el hábito
     */
    public void crearHabito(HabitoDTO habitoDTO) throws ControllerException;

    /**
     * Crea una cuenta
     *
     * @param cuentaDTO Cuenta a crear
     * @throws ControllerException si algo sale mal al crear la cuenta
     */
    public void crearCuenta(CuentaDTO cuentaDTO) throws ControllerException;

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

    /**
     * Actualizar un registro de historial de hábitos utilizando la entidad.
     *
     * @param historial Habito a actualizar.
     * @return El registro actualizado de historial de hábitos.
     * @throws ControllerException Si ocurre un error al actualizar.
     */
    public HistorialHabitosDTO actualizarHistorial(HistorialHabitosDTO historial) throws ControllerException;

    /**
     * Crear un nuevo historial de hábitos.
     *
     * @param historial El objeto HistorialHabitos a crear.
     * @return El objeto HistorialHabitos creado.
     * @throws ControllerException Si ocurre un error al crear el historial.
     */
    public HistorialHabitosDTO crearHistorial(HistorialHabitosDTO historial) throws ControllerException;

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
     * Metodo para obtener la semana de un determinado dia
     * @param fecha Fecha a obtener la semana
     * @return Aregglo de los dias de la semana del dia 
     */
    public Date[] obtenerSemana(Date fecha);
    
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

}
