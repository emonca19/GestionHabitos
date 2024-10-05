package org.itson.pruebas.gestionhabitos.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase que gestiona habitos y cuentas
 *
 * @author
 */
public class GestionarHabitosDAO implements IGestionarHabitosDAO {

    EntityManager entityManager;

    // Constructor
    /**
     *
     * @param conexion
     */
    public GestionarHabitosDAO(IConexion conexion) {
        this.entityManager = conexion.crearConexion();
    }

//    /**
//     * Cierra el `EntityManager` cuando el DAO ya no se necesite.
//     */
//    public void cerrar() {
//        if (entityManager != null && entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
    /**
     * Crea un habito
     *
     * @param nuevoHabito Habito a crear
     * @return Habito creado
     * @throws ModelException Si no se puede crear el habito
     */
    @Override
    public Habito crearHabito(Habito nuevoHabito) throws ModelException {
        EntityTransaction transaction = null;

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
     * Actualizar un habito
     *
     * @param habito Habito a actualizar
     * @return Habito actualizado
     * @throws ModelException Si no se puede actualizar
     */
    @Override
    public Habito actualizarHabito(Habito habito) throws ModelException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoExistente = entityManager.find(Habito.class, habito.getId());
            if (habitoExistente != null) {
                habitoExistente.setId(habito.getId());
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
     * Habito a eliminar
     *
     * @param id id del habito a eliminar
     * @return true si se elimino false en caso contrario
     * @throws ModelException
     */
    @Override
    public boolean eliminarHabito(Long id) throws ModelException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoEncontrado = entityManager.find(Habito.class, id);
            if (habitoEncontrado != null) {
                entityManager.remove(habitoEncontrado); // Elimina el hábito
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                throw new ModelException("Habito no encontrado con ID: " + id);
            }

        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                throw new ModelException("Transaccion revertida debido a un error al eliminar el habito", e);
            }
            throw new ModelException("Error al eliminar habito con id: " + id, e);
        }
    }

    /**
     * Obtener los habitos de una cuenta
     *
     * @param cuenta Cuenta a aobtener los habitos
     * @return Lista de habitos de la cuenta
     * @throws ModelException Si hubo un error al obtener los habitos
     */
    @Override
    public List<Habito> obtenerHabitos(Cuenta cuenta) throws ModelException {
        try {
            TypedQuery<Habito> query = entityManager.createQuery(
                    "SELECT h FROM Habito h WHERE h.cuenta.usuario = :usuario", Habito.class
            );
            query.setParameter("usuario", cuenta.getUsuario());
            List<Habito> habitos = query.getResultList();
            if (habitos.isEmpty()) {
                throw new ModelException("No se encontro habitos en la cuenta");
            }
            return habitos;

        } catch (ModelException e) {
            throw new ModelException("Error al obtener la lista de hábitos", e);
        }
    }

    /**
     * Crea una cuenta
     *
     * @param cuenta Cuenta a crear
     * @return Cuenta creada
     * @throws ModelException Si no se puede crear la cuenta
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
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a encontrar
     * @param contraseña Contraseña que coincida con la cuenta
     * @return Cuenta consultada
     * @throws ModelException Si no se puede consultar la cuenta
     */
    /**
     * Consulta la existencia de una cuenta utilizando Criteria API
     *
     * @param usuario Usuario a encontrar
     * @param contraseña Contraseña que coincida con la cuenta
     * @return Cuenta consultada
     * @throws ModelException Si no se puede consultar la cuenta
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

        } catch (Exception e) {
            throw new ModelException("Error al consultar la cuenta: " + e.getMessage(), e);
        }
    }

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Registro de historial de hábitos que coincide con la fecha y el
     * ID de hábito.
     * @throws ModelException Si ocurre un error al buscar o si no se encuentra
     * el registro
     */
    @Override
    public HistorialHabitos buscarPorFechaYIdHabito(Date dia, Long idHabito) throws ModelException {
        EntityTransaction transaction = null;
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

            // Usa una consulta para comparar la fecha sin hora
            resultado = entityManager.createQuery(
                    "SELECT h FROM HistorialHabitos h WHERE h.dia = :diaSinHora AND h.habito.id = :idHabito", HistorialHabitos.class)
                    .setParameter("diaSinHora", diaSinHora)
                    .setParameter("idHabito", idHabito)
                    .getSingleResult();

        } catch (NoResultException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("No se encontró ningún historial de hábitos para la fecha y el ID proporcionados", e);
        } catch (NonUniqueResultException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Se encontraron múltiples registros, se esperaba uno solo", e);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
             e.printStackTrace();
//            throw new ModelException("Error al buscar historial de hábitos", e);
        }

        return resultado; // Retorna el resultado
    }

    /**
     * Crear o actualizar un historial de hábitos. Si el historial ya existe, se
     * actualiza; si no, se crea.
     *
     * @param historial El objeto HistorialHabitos a persistir.
     * @return El objeto HistorialHabitos persistido (creado o actualizado).
     * @throws ModelException Si ocurre un error durante la operación.
     */
    @Override
    public HistorialHabitos guardarHistorial(HistorialHabitos historial) throws ModelException {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            HistorialHabitos historialExistente = buscarPorFechaYIdHabito(historial.getDia(), historial.getHabito().getId());

            historialExistente.setCompletado(historial.isCompletado());
            entityManager.merge(historialExistente);
            transaction.commit();
            return historialExistente;

        } catch (ModelException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertimos la transacción fallida
            }

            // Iniciamos una nueva transacción para persistir el nuevo historial
            transaction.begin();
            entityManager.persist(historial);
            transaction.commit();
            return historial;

        } finally {
            if (transaction.isActive()) {
                transaction.rollback(); // Aseguramos que cualquier transacción pendiente se deshaga
            }
            entityManager.close();
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
     * Busca un hábito por su ID.
     *
     * @param id el ID del hábito a buscar.
     * @return el hábito encontrado o null si no se encuentra.
     * @throws ModelException Si hay un error al buscar el hábito.
     */
    @Override
    public Habito buscarHabitoPorId(Long id) throws ModelException {
        Habito habito = null;

        if (id == null) {
            throw new ModelException("El ID no puede ser nulo");
        }

        try {

            habito = entityManager.find(Habito.class, id);

            if (habito == null) {
                throw new ModelException("No se encontró el hábito con ID: " + id);
            }

            return habito;

        } catch (Exception e) {
            throw new ModelException("Error al buscar el hábito: " + e.getMessage());
        }
    }

    /**
     * Consultar historial de hábitos para una cuenta en una fecha específica
     * utilizando Criteria API.
     *
     * @param date La fecha en la que se desea consultar el historial.
     * @param cuenta La cuenta asociada al historial a través del hábito.
     * @return Una lista de objetos HistorialHabitos que coinciden con los
     * criterios de búsqueda.
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

}
