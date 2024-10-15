package org.itson.pruebas.gestionhabitos.model;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interfaz que define las operaciones para gestionar hábitos y cuentas en el
 * sistema.
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
public interface IGestionarHabitosDAO {

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
     * Devuelve la cuenta de un usuario si ese usuario ya existe
     *
     * @param usuario
     * @return
     * @throws ModelException
     */
    public Cuenta consultarCuentaPorUsuario(String usuario) throws ModelException;

    /**
     * Devuelve si un usuario ya existe
     *
     * @param usuario Cadena del nombre del usuario para verificar su existencia
     * @return True si existe, false en caso contrario
     * @throws ModelException Si ocurre un error al consultar la cuenta
     */
    public boolean cuentaExiste(String usuario) throws ModelException;

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
    public List<Habito> obtenerHabitos(Cuenta cuenta) throws NoSuchElementException, ModelException;

    /**
     * Verifica si un hábito ha sido completado.
     *
     * @param habito Hábito a verificar
     * @return true si el hábito está completado, false en caso contrario
     * @throws ModelException Si ocurre un error al verificar el estado del
     * hábito
     */
    public boolean habitoCompletado(Habito habito) throws ModelException;

    /**
     * Busca un hábito por su ID.
     *
     * @param id el ID del hábito a buscar.
     * @return el hábito encontrado o null si no se encuentra.
     * @throws ModelException Si hay un error al buscar el hábito.
     */
    public Habito buscarHabitoPorId(Long id) throws ModelException;

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

    /**
     * Guarda y actualiza un historial de hábitos.
     *
     * @param historial Historial de hábitos a guardar o actualizar
     * @return Historial de hábitos guardado o actualizado
     * @throws ModelException Si ocurre un error al guardar o actualizar
     */
    public HistorialHabitos guardarYActualizarHistorial(HistorialHabitos historial) throws ModelException;

    /**
     * Consulta el historial de hábitos de una cuenta en una fecha específica.
     *
     * @param date Fecha para la consulta
     * @param cuenta Cuenta a consultar
     * @return Lista de historial de hábitos
     * @throws ModelException Si ocurre un error al consultar el historial
     */
    public List<HistorialHabitos> consultarHistorialHabitos(Date date, Cuenta cuenta) throws ModelException;

    /**
     * Obtiene el progreso de los hábitos de una cuenta en un rango de fechas
     * determinado.
     *
     * @param cuenta La cuenta para la cual se desea obtener el progreso de los
     * hábitos.
     * @param fechaInicio La fecha de inicio del rango de fechas.
     * @param fechaFin La fecha de fin del rango de fechas.
     * @return Una lista de objetos {@link ProgresoHabito} que representan el
     * progreso de los hábitos de la cuenta en el rango de fechas especificado.
     * @throws ModelException Si ocurre un error al obtener el progreso de los
     * hábitos.
     */
    public List<ProgresoHabito> obtenerProgresoHabitos(Cuenta cuenta, Date fechaInicio, Date fechaFin) throws ModelException;

}
