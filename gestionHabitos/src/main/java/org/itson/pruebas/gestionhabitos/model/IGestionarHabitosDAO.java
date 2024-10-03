/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author elimo
 */
public interface IGestionarHabitosDAO {

    /**
     * Crea un habito
     *
     * @param nuevoHabito Habito a crear
     * @return Habito creado
     * @throws ModelException Si no se puede crear el habito
     */
    public Habito crearHabito(Habito nuevoHabito) throws ModelException;

    /**
     * Actualizar un habito
     *
     * @param habito Habito a actualizar
     * @return Habito actualizado
     * @throws ModelException Si no se puede actualizar
     */
    public Habito actualizarHabito(Habito habito) throws ModelException;

    /**
     * Habito a eliminar
     *
     * @param id id del habito a eliminar
     * @return true si se elimino false en caso contrario
     * @throws ModelException
     */
    public boolean eliminarHabito(Long id) throws ModelException;

    /**
     * Obtener los habitos de una cuenta
     *
     * @param cuenta Cuenta a aobtener los habitos
     * @return Lista de habitos de la cuenta
     * @throws ModelException Si hubo un error al obtener los habitos
     */
    public List<Habito> obtenerHabitos(Cuenta cuenta) throws ModelException;

    /**
     * Crea una cuenta
     *
     * @param cuenta Cuenta a crear
     * @return Cuenta creada
     * @throws ModelException Si no se puede crear la cuenta
     */
    public Cuenta crearCuenta(Cuenta cuenta) throws ModelException;

    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a encontrar
     * @param contraseña Contraseña que coincida con la cuenta
     * @return Cuenta consultada
     * @throws ModelException Si no se puede consultar la cuenta
     */
    public Cuenta consultarCuenta(String usuario, String contraseña) throws ModelException;

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Lista de registros de historial de hábitos que coinciden con la
     * fecha y el ID de hábito.
     * @throws ModelException Si ocurre un error al buscar
     */
    public HistorialHabitos buscarPorFechaYIdHabito(Date dia, Long idHabito) throws ModelException;

    public HistorialHabitos guardarHistorial(HistorialHabitos historial) throws ModelException;

    /**
     * Devuelve si un usuario ya existe
     *
     * @param usuario Cadena del nombre del usuario para verificar su existencia
     * @return True si existe, false en caso contrario
     * @throws ModelException Si ocurre un error al consultar la cuenta
     */
    public boolean cuentaExiste(String usuario) throws ModelException;

    /**
     * Devuelve la cuenta de un usuario si ese usuario ya existe
     *
     * @param usuario
     * @return
     * @throws ModelException
     */
    public Cuenta consultarCuentaPorUsuario(String usuario) throws ModelException;

    /**
     * Busca un hábito por su ID.
     *
     * @param id el ID del hábito a buscar.
     * @return el hábito encontrado o null si no se encuentra.
     * @throws ModelException Si hay un error al buscar el hábito.
     */
    public Habito buscarHabitoPorId(Long id) throws ModelException;

}
