package org.itson.pruebas.gestionhabitos.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase que gestiona los hábitos y cuentas. Esta clase implementa las operaciones CRUD para la gestión de hábitos y cuentas en la base de datos.
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
public class GestionarHabitosDAO implements IGestionarHabitosDAO {

    EntityManager entityManager;

    /**
     * Constructor para inicializar la clase `GestionarHabitosDAO`.
     *
     * @param conexion La conexión a la base de datos utilizada para inicializar el `EntityManager`.
     */
    public GestionarHabitosDAO(IConexion conexion) {
        this.entityManager = conexion.crearConexion();
    }

    /**
     * Crea una nueva cuenta en la base de datos.
     *
     * @param cuenta La cuenta a crear.
     * @return La cuenta creada.
     * @throws ModelException Si ocurre algún error durante la creación de la cuenta.
     */
    @Override
    public Cuenta crearCuenta(Cuenta cuenta) throws ModelException {
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(cuenta);
            entityManager.getTransaction().commit();

            return cuenta;
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
                throw new ModelException("Transaccion revertida debido a un error al crear la cuenta", ex);
            }

            throw new ModelException("Error al crear la cuenta", ex);

        }
    }

    /**
     * Consulta una cuenta en la base de datos basada en el nombre de usuario y la contraseña.
     *
     * @param usuario El nombre de usuario de la cuenta.
     * @param contraseña La contraseña de la cuenta.
     * @return La cuenta consultada.
     * @throws ModelException Si la cuenta no existe o si ocurre algún error durante la consulta.
     */
    @Override
    public Cuenta consultarCuenta(String usuario, String contraseña) throws ModelException {

        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Cuenta> criteriaQuery = cb.createQuery(Cuenta.class);

            Root<Cuenta> cuentaRoot = criteriaQuery.from(Cuenta.class);

            Predicate usuarioPredicate = cb.equal(cuentaRoot.get("usuario"), usuario);
            Predicate contrasenaPredicate = cb.equal(cuentaRoot.get("contrasena"), contraseña);

            criteriaQuery.select(cuentaRoot).where(cb.and(usuarioPredicate, contrasenaPredicate));

            List<Cuenta> cuentas = entityManager.createQuery(criteriaQuery).getResultList();

            if (!cuentas.isEmpty()) {
                return cuentas.get(0);
            } else {
                throw new ModelException("La cuenta no existe.");
            }

        } catch (ModelException e) {
            throw new ModelException("Error al consultar la cuenta: " + e.getMessage(), e);
        }
    }

    /**
     * Devuelve la cuenta del usuario si el usuario ya existe
     *
     * @param usuario Cadena del nombre del usuario para verificar su existencia
     * @return True si existe, false en caso contrario
     * @throws ModelException Si ocurre un error al consultar la cuenta
     */
    @Override
    public Cuenta consultarCuentaPorUsuario(String usuario) throws ModelException {
        try {

            return entityManager.createQuery("SELECT c FROM Cuenta c WHERE c.usuario = :usuario", Cuenta.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Si no encuentra la cuenta, retorna null
        } catch (Exception e) {
            throw new ModelException("Error al consultar la cuenta por usuario", e);
        }
    }

    /**
     * Devuelve si un usuario ya existe
     *
     * @param usuario Cadena del nombre del usuario para verificar su existencia
     * @return True si existe, false en caso contrario
     * @throws ModelException Si ocurre un error al consultar la cuenta
     */
    @Override
    public boolean cuentaExiste(String usuario) throws ModelException {
        try {

            TypedQuery<Cuenta> query = entityManager.createQuery(
                    "SELECT c FROM Cuenta c WHERE c.usuario = :usuario", Cuenta.class
            );
            query.setParameter("usuario", usuario);

            List<Cuenta> cuentas = query.getResultList();

            return !cuentas.isEmpty();
        } catch (Exception e) {
            throw new ModelException("Error al consultar si la cuenta existe: " + e.getMessage(), e);
        }
    }

    /**
     * Crea un nuevo hábito en la base de datos.
     *
     * @param nuevoHabito El hábito a crear.
     * @return El hábito creado.
     * @throws ModelException Si ocurre algún error al intentar crear el hábito o si los datos del hábito no son válidos.
     */
    @Override
    public Habito crearHabito(Habito nuevoHabito) throws ModelException {
        EntityTransaction transaction = null;

        if (nuevoHabito == null
                || nuevoHabito.getFrecuencia() == null || nuevoHabito.getFrecuencia().isEmpty()
                || nuevoHabito.getNombre() == null || nuevoHabito.getNombre().isEmpty()
                || nuevoHabito.getDiasSemana() == null || nuevoHabito.getDiasSemana().isEmpty()
                || nuevoHabito.getFechaCreacion() == null) {
            throw new ModelException("Todos los campos son obligatorios y deben estar llenos");
        }
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(nuevoHabito);
            transaction.commit();
            return nuevoHabito;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                throw new ModelException("Transaccion revertida debido a un error al crear el habito");
            }
            throw new ModelException("Error al crear habito ");
        }
    }

    /**
     * Actualiza un hábito existente en la base de datos.
     *
     * @param habito El hábito con los datos actualizados.
     * @return El hábito actualizado.
     * @throws ModelException Si el hábito no existe o si ocurre un error durante la actualización.
     */
    @Override
    public Habito actualizarHabito(Habito habito) throws ModelException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoExistente = entityManager.find(Habito.class, habito.getId());
            if (habitoExistente != null) {
                habitoExistente.setFrecuencia(habito.getFrecuencia());
                habitoExistente.setFechaCreacion(habito.getFechaCreacion());
                habitoExistente.setDiasSemana(habito.getDiasSemana());
                habitoExistente.setCuenta(habito.getCuenta());
                transaction.commit();
                return habitoExistente; // Retorna el hábito actualizado
            } else {
                transaction.rollback();
                throw new ModelException("Habito no encontrado");
            }

        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                throw new ModelException("Transaccion revertida debido a un error al actualizar", e);
            }
            throw new ModelException("Error al actualizar habito", e);
        }
    }

    /**
     * Elimina un hábito de la base de datos.
     *
     * @param id El ID del hábito a eliminar.
     * @return `true` si el hábito se eliminó correctamente, `false` en caso contrario.
     * @throws ModelException Si ocurre algún error al intentar eliminar el hábito o si el hábito no se encuentra.
     */
    @Override
    public boolean eliminarHabito(Long id) throws ModelException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Deleting records from HistorialHabitos associated with the habit
            entityManager.createQuery("DELETE FROM HistorialHabitos h WHERE h.habito.id = :idHabito")
                    .setParameter("idHabito", id)
                    .executeUpdate();

            // Now find the Habito to delete it
            Habito habitoEncontrado = entityManager.find(Habito.class, id);
            if (habitoEncontrado != null) {
                entityManager.remove(habitoEncontrado); // Remove the habit
                transaction.commit(); // Commit the transaction
                return true;
            } else {
                transaction.rollback(); // Rollback if the habit isn't found
                throw new ModelException("Hábito no encontrado con ID: " + id);
            }

        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback on any error
                throw new ModelException("Transacción revertida debido a un error al eliminar el hábito", e);
            }
            throw new ModelException("Error al eliminar hábito con ID: " + id, e);
        }
    }

    /**
     * Obtiene una lista de hábitos asociados a una cuenta.
     *
     * @param cuenta La cuenta para la cual se quieren obtener los hábitos.
     * @return Una lista de hábitos pertenecientes a la cuenta.
     * @throws NoSuchElementException Si no se encuentran hábitos asociados a la cuenta.
     * @throws ModelException Si ocurre algún error al intentar obtener los hábitos.
     */
    @Override
    public List<Habito> obtenerHabitos(Cuenta cuenta) throws NoSuchElementException, ModelException {
        try {
            TypedQuery<Habito> query = entityManager.createQuery(
                    "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class
            );
            query.setParameter("usuario", cuenta.getUsuario());
            List<Habito> habitos = query.getResultList();
            if (habitos == null) {
                throw new NoSuchElementException("No se encontro habitos en la cuenta");
            }
            return habitos;

        } catch (Exception e) {
            throw new ModelException("Error al obtener la lista de hábitos", e);
        }
    }

    /**
     * Verifica si un hábito está completado.
     *
     * @param habito El hábito a verificar.
     * @return True si el hábito está completado, false en caso contrario.
     * @throws ModelException Si hay un error al verificar el estado del hábito.
     */
    @Override
    public boolean habitoCompletado(Habito habito) throws ModelException {
        // Validación inicial del hábito
        if (habito == null || habito.getId() == null) {
            throw new ModelException("El hábito no puede ser nulo y debe tener un ID válido.");
        }

        try {
            // Consultar el historial de hábitos para este hábito y verificar si alguno está completado
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
            Root<HistorialHabitos> historialRoot = criteriaQuery.from(HistorialHabitos.class);

            // Filtrar por el ID del hábito y completado = 1
            Predicate habitoPredicate = cb.equal(historialRoot.get("habito").get("id"), habito.getId());
            Predicate completadoPredicate = cb.equal(historialRoot.get("completado"), 1); // 1 representa completado

            // Contar cuántos registros hay con el hábito completado
            criteriaQuery.select(cb.count(historialRoot)).where(cb.and(habitoPredicate, completadoPredicate));

            // Ejecutar la consulta
            Long count = entityManager.createQuery(criteriaQuery).getSingleResult();

            // Si hay al menos un registro, el hábito está completado
            return count > 0;

        } catch (NoResultException e) {
            // No hay resultados, el hábito no está completado
            return false; // O lanzar una excepción dependiendo de tu lógica
        } catch (Exception e) {
            // Manejo de excepción
            throw new ModelException("Error al verificar si el hábito está completado: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un hábito por su ID.
     *
     * @param id el ID del hábito a buscar.
     * @return el hábito encontrado o null si no se encuentra.
     * @throws ModelException Si hay un error al buscar el hábito.
     */
    @Override
    public Habito buscarHabitoPorId(Long id) throws ModelException {
        Habito habito;

        if (id == null) {
            throw new ModelException("El ID no puede ser nulo");
        }

        try {

            habito = entityManager.find(Habito.class, id);

            if (habito == null) {
                throw new ModelException("No se encontró el hábito con ID: " + id);
            }

            return habito;

        } catch (ModelException e) {
            throw new ModelException("Error al buscar el hábito: " + e.getMessage());
        }
    }

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Registro de historial de hábitos que coincide con la fecha y el ID de hábito.
     * @throws ModelException Si ocurre un error al buscar o si no se encuentra el registro.
     */
    @Override
    public HistorialHabitos buscarPorFechaYIdHabito(Date dia, Long idHabito) throws ModelException {
        HistorialHabitos resultado = null;

        try {
            // Asegúrate de que las horas en la fecha son cero
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dia);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date diaSinHora = calendar.getTime();

            // Verifica que el EntityManager esté abierto antes de la consulta
            if (!entityManager.isOpen()) {
                throw new ModelException("El EntityManager está cerrado.");
            }

            // Usa una consulta para comparar la fecha sin hora
            resultado = entityManager.createQuery(
                    "SELECT h FROM HistorialHabitos h WHERE h.dia = :diaSinHora AND h.habito.id = :idHabito", HistorialHabitos.class)
                    .setParameter("diaSinHora", diaSinHora)
                    .setParameter("idHabito", idHabito)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new ModelException("No se encontró el registro del historial de hábitos", e);
        } catch (NonUniqueResultException e) {
            throw new ModelException("Se encontraron múltiples registros, se esperaba uno solo", e);
        }

        return resultado; // Retorna el resultado
    }

    /**
     * Crear o actualizar un historial de hábitos. Si el historial ya existe, se actualiza; si no, se crea.
     *
     * @param historial El objeto HistorialHabitos a persistir.
     * @return El objeto HistorialHabitos persistido (creado o actualizado).
     * @throws ModelException Si ocurre un error durante la operación.
     */
    @Override
    public HistorialHabitos guardarYActualizarHistorial(HistorialHabitos historial) throws ModelException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Intentamos buscar un historial existente
            HistorialHabitos historialExistente;
            historialExistente = buscarPorFechaYIdHabito(historial.getDia(), historial.getHabito().getId());

            // Si se encuentra el historial, actualizamos el campo 'completado'
            historialExistente.setCompletado(historial.isCompletado());
            entityManager.merge(historialExistente);
            transaction.commit();
            return historialExistente;

        } catch (ModelException e) {
            // Si no se encuentra ningún historial, persistimos uno nuevo
            if (transaction.isActive()) {
                transaction.rollback();
            }

            // Iniciamos una nueva transacción para crear un nuevo historial
            transaction.begin();
            entityManager.persist(historial);
            transaction.commit();
            return historial;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Error al guardar o actualizar el historial de hábitos", e);

        } finally {
            // Aquí no deberías cerrar el EntityManager manualmente
            // Sólo aseguramos que no haya transacciones abiertas
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    /**
     * Consultar historial de hábitos para una cuenta en una fecha específica utilizando Criteria API.
     *
     * @param date La fecha en la que se desea consultar el historial.
     * @param cuenta La cuenta asociada al historial a través del hábito.
     * @return Una lista de objetos HistorialHabitos que coinciden con los criterios de búsqueda.
     * @throws ModelException Si ocurre un error durante la consulta.
     */
    @Override
    public List<HistorialHabitos> consultarHistorialHabitos(Date date, Cuenta cuenta) throws ModelException {

        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<HistorialHabitos> criteriaQuery = cb.createQuery(HistorialHabitos.class);

            // Root de la consulta
            Root<HistorialHabitos> historialRoot = criteriaQuery.from(HistorialHabitos.class);

            // Predicado para filtrar por fecha
            Predicate datePredicate = cb.equal(historialRoot.get("dia"), date);
            // Predicado para filtrar por la cuenta relacionada a través del hábito
            Predicate cuentaPredicate = cb.equal(historialRoot.get("habito").get("cuenta"), cuenta);

            // Construcción de la consulta
            criteriaQuery.select(historialRoot).where(cb.and(datePredicate, cuentaPredicate));

            // Ejecución de la consulta
            List<HistorialHabitos> historialList = entityManager.createQuery(criteriaQuery).getResultList();

            return historialList;

        } catch (Exception e) {
            throw new ModelException("Error al consultar el historial de hábitos: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene el progreso de los hábitos de una cuenta en un rango de fechas determinado.
     *
     * @param cuenta La cuenta para la cual se desea obtener el progreso de los hábitos.
     * @param fechaInicio La fecha de inicio del rango de fechas.
     * @param fechaFin La fecha de fin del rango de fechas.
     * @return Una lista de objetos {@link ProgresoHabito} que representan el progreso de los hábitos de la cuenta en el rango de fechas especificado.
     * @throws ModelException Si ocurre un error al obtener el progreso de los hábitos.
     */
    @Override
    public List<ProgresoHabito> obtenerProgresoHabitos(Cuenta cuenta, Date fechaInicio, Date fechaFin) throws ModelException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

        Root<HistorialHabitos> hh = query.from(HistorialHabitos.class);
        Join<HistorialHabitos, Habito> h = hh.join("habito", JoinType.LEFT);

        // Crear las expresiones para el conteo y la longitud
        Expression<Long> diasRealizados = cb.count(
                cb.selectCase()
                        .when(cb.and(cb.between(hh.get("dia"), fechaInicio, fechaFin), cb.isTrue(hh.get("completado"))), 1)
                        .otherwise((Long) null)
        );
        //TODO cuando sea mensual cambiar la lógica
        Expression<Integer> diasTotal = cb.length(cb.function("REPLACE", String.class, h.get("diasSemana"), cb.literal("0"), cb.literal("")));

        // Definir la consulta
        query.multiselect(h.get("nombre"), diasRealizados, diasTotal)
                .where(cb.equal(h.get("cuenta").get("usuario"), cuenta.getUsuario()))
                .groupBy(h.get("id"), h.get("nombre"), h.get("diasSemana"));

        // Ejecutar la consulta y transformar resultados
        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(result -> new ProgresoHabito(
                (String) result[0],
                ((Long) result[1]).intValue(), // Cambia Long a Integer para diasRealizados
                ((Integer) result[2])) // Asegúrate de que esto sea Integer
                )
                .collect(Collectors.toList());
    }

}
