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
     * @param usuario Usuario a consultar
     * @param contraseña Verificar que concuerda con la contraseña
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta correctamente
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
    public HistorialHabitosDTO buscarPorFechaYIdHabito(Date dia, int idHabito) throws ControllerException;

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

}
