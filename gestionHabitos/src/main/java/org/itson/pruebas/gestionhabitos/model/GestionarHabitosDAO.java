package org.itson.pruebas.gestionhabitos.model;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 * Clase que gestiona habitos y cuentas
 *
 * @author
 */
public class GestionarHabitosDAO implements IGestionarHabitosDAO {

    private final IConexion conexion;
    static final Logger logger = Logger.getLogger(GestionarHabitosDAO.class.getName());
    EntityManager entityManager = null;

    // Constructor
    public GestionarHabitosDAO(IConexion conexion) {
        this.conexion = conexion;
    }

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
            entityManager = this.conexion.crearConexion();
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoExistente = entityManager.find(Habito.class, habito.getId());
            if (habitoExistente != null) {
                habitoExistente.setId(habito.getId());
                habitoExistente.setFrecuencia(habito.getFrecuencia());
                habitoExistente.setFechaCreacion(habito.getFechaCreacion());
                habitoExistente.setDiasSemanaRealizado(habito.getDiasSemanaRealizado());
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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
            entityManager = this.conexion.crearConexion();
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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
            entityManager = this.conexion.crearConexion();
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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

            entityManager = this.conexion.crearConexion();
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
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
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
    @Override
    public Cuenta consultarCuenta(String usuario, String contraseña) throws ModelException {
        try {
            entityManager = this.conexion.crearConexion();

            TypedQuery<Cuenta> query = entityManager.createQuery(
                    "SELECT c FROM Cuenta c WHERE c.usuario = :usuario AND c.contrasena = :contrasena", Cuenta.class
            );
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", contraseña);

            List<Cuenta> cuentas = query.getResultList();

            if (!cuentas.isEmpty()) {
                return cuentas.get(0);
            } else {
                throw new ModelException("La cuenta no existe.");
            }

        } catch (ModelException e) {
            throw new ModelException("Error al consultar la cuenta: " + e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Registro de historial de hábitos que coincide con la fecha y el ID de hábito.
     * @throws ModelException Si ocurre un error al buscar o si no se encuentra el registro
     */
    @Override
    public HistorialHabitos buscarPorFechaYIdHabito(Date dia, int idHabito) throws ModelException {
        EntityTransaction transaction = null;
        HistorialHabitos resultado = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Consulta para buscar por fecha y idHabito
            resultado = entityManager.createQuery(
                    "SELECT h FROM HistorialHabitos h WHERE h.dia = :dia AND h.habito.id = :idHabito", HistorialHabitos.class)
                    .setParameter("dia", dia)
                    .setParameter("idHabito", idHabito)
                    .getSingleResult(); // Cambiado a getSingleResult()

            transaction.commit();
        } catch (NoResultException e) {
            transaction.rollback();
            throw new ModelException("No se encontró ningún historial de hábitos para la fecha y el ID proporcionados", e);
        } catch (NonUniqueResultException e) {
            transaction.rollback();
            throw new ModelException("Se encontraron múltiples registros, se esperaba uno solo", e);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                throw new ModelException("Transacción revertida debido a un error al buscar", e);
            }
            throw new ModelException("Error al buscar historial de hábitos", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return resultado; // Retorna el resultado
    }

    /**
     * Actualizar un registro de historial de hábitos utilizando la entidad.
     *
     * @param historial Habito a actualizar.
     * @return El registro actualizado de historial de hábitos.
     * @throws ModelException Si ocurre un error al actualizar.
     */
    @Override
    public HistorialHabitos actualizarHistorial(HistorialHabitos historial) throws ModelException {
        EntityTransaction transaction = null;
        HistorialHabitos historialExistente = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            historialExistente = entityManager.find(HistorialHabitos.class, historial.getId());
            if (historialExistente != null) {
                historialExistente.setDia(historial.getDia());
                historialExistente.setCompletado(historial.isCompletado());
                historialExistente.setHabito(historial.getHabito());

                transaction.commit();
            } else {
                transaction.rollback();
                throw new ModelException("Registro de historial de hábitos no encontrado con el ID proporcionado");
            }
        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                throw new ModelException("Transacción revertida debido a un error al actualizar", e);
            }
            throw new ModelException("Error al actualizar historial de hábitos", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return historialExistente;
    }

    /**
     * Crear un nuevo historial de hábitos.
     *
     * @param historial El objeto HistorialHabitos a crear.
     * @return El objeto HistorialHabitos creado.
     * @throws ModelException Si ocurre un error al crear el historial.
     */
    public HistorialHabitos crearHistorial(HistorialHabitos historial) throws ModelException {
        EntityTransaction transaction = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(historial);

            transaction.commit();
            return historial;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Error al crear el historial de hábitos", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean cuentaExiste(String usuario) throws ModelException {
        try {
            entityManager = this.conexion.crearConexion();
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
}
