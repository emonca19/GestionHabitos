package org.itson.pruebas.gestionhabitos.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class GestionarHabitosDAO implements IGestionarHabitosDAO {

    private final IConexion conexion;
    static final Logger logger = Logger.getLogger(GestionarHabitosDAO.class.getName());

    // Constructor
    public GestionarHabitosDAO() {
        this.conexion = new Conexion();
    }

    @Override
    public Habito crearHabito(Habito nuevoHabito) throws ModelException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            logger.info("Iniciando la creacion de un nuevo habito");
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(nuevoHabito); // Persiste el nuevo hábito
            transaction.commit();
            logger.log(Level.INFO, "Habito creado con exito: {0}", nuevoHabito.getId());
            return nuevoHabito;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                logger.log(Level.WARNING, "Transaccion revertida debido a un error al crear el habito: {0}", e.getMessage());
            }
            logger.log(Level.SEVERE, "Error al crear habito con id: " + nuevoHabito.getId(), e);
            throw new ModelException("Error al crear habito con id: " + nuevoHabito.getId(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Habito actualizarHabito(Habito habito) throws ModelException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            logger.log(Level.INFO, "Iniciando la actualizacion del habito con id: {0}", habito.getId());
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoExistente = entityManager.find(Habito.class, habito.getId());
            if (habitoExistente != null) {
                // Actualiza las propiedades del hábito existente
                habitoExistente.setNombre(habito.getNombre());
                habitoExistente.setFrecuencia(habito.getFrecuencia());
                habitoExistente.setRealizado(habito.isRealizado());
                habitoExistente.setFecha(habito.getFecha());
                transaction.commit();
                logger.log(Level.INFO, "Habito actualizado con exito: {0}", habito.getId());
                return habitoExistente; // Retorna el hábito actualizado
            } else {
                transaction.rollback();
                logger.log(Level.WARNING, "No se encontro el habito con id: {0}", habito.getId());
                throw new ModelException("Habito no encontrado con ID: " + habito.getId());
            }

        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                logger.log(Level.WARNING, "Transaccion revertida debido a un error al actualizar el habito: {0}", e.getMessage());
            }
            logger.log(Level.SEVERE, "Error al actualizar habito con id: " + habito.getId(), e);
            throw new ModelException("Error al actualizar habito con id: " + habito.getId(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean eliminarHabito(Long id) throws ModelException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            logger.log(Level.INFO, "Iniciando la eliminacion del habito con id: {0}", id);
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoEncontrado = entityManager.find(Habito.class, id);
            if (habitoEncontrado != null) {
                entityManager.remove(habitoEncontrado); // Elimina el hábito
                transaction.commit();
                logger.log(Level.INFO, "Habito eliminado con exito: {0}", id);
                return true;
            } else {
                transaction.rollback();
                logger.log(Level.WARNING, "No se encontr\u00f3 el habito con id: {0}", id);
                throw new ModelException("Habito no encontrado con ID: " + id);
            }

        } catch (ModelException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                logger.log(Level.WARNING, "Transacci\u00f3n revertida debido a un error al eliminar el habito: {0}", e.getMessage());
            }
            logger.log(Level.SEVERE, "Error al eliminar habito con id: " + id, e);
            throw new ModelException("Error al eliminar habito con id: " + id, e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Habito> obtenerHabitos(Cuenta cuenta) throws ModelException {
        EntityManager entityManager = null;

        try {
            logger.log(Level.INFO, "Obteniendo habitos asociados a la cuenta: {0}", cuenta.getUsuario());
            entityManager = this.conexion.crearConexion();
            TypedQuery<Habito> query = entityManager.createQuery(
                    "SELECT h FROM Habito h WHERE h.usuario = :usuario", Habito.class
            );
            query.setParameter("usuario", cuenta.getUsuario());
            List<Habito> habitos = query.getResultList();
            logger.log(Level.INFO, "Habitos obtenidos: {0} asociados a la cuenta: {1}", new Object[]{habitos.size(), cuenta.getUsuario()});
            return habitos; // Obtiene la lista de hábitos asociados al usuario de la cuenta
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener la lista de habitos para el usuario: " + cuenta.getUsuario(), e);
            throw new ModelException("Error al obtener la lista de hábitos", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) throws ModelException {
        EntityManager entityManager = null;
        try {
            logger.log(Level.INFO, "Iniciando la creacion de una nueva cuenta: {0}", cuenta.getUsuario());
            entityManager = this.conexion.crearConexion();
            entityManager.getTransaction().begin();

            // Persistir la cuenta en la base de datos
            entityManager.persist(cuenta);

            // Confirmar la transacción
            entityManager.getTransaction().commit();
            logger.log(Level.INFO, "Cuenta creada exitosamente: {0}", cuenta.getUsuario());
            return cuenta;  // Devolver la cuenta persistida
        } catch (Exception ex) {
            if (entityManager != null) {
                // Si ocurre un error, revertir la transacción
                entityManager.getTransaction().rollback();
                logger.log(Level.WARNING, "Transaccion revertida debido a un error al crear la cuenta: {0}", ex.getMessage());
            }
            logger.log(Level.SEVERE, "Error al crear la cuenta: " + cuenta.getUsuario(), ex);
            throw new ModelException("Error al crear la cuenta", ex);
        } finally {
            if (entityManager != null) {
                // Cerrar el EntityManager
                entityManager.close();
            }
        }
    }

    @Override
    public Cuenta consultarCuenta(String usuario, String contraseña) throws ModelException {
        EntityManager entityManager = null;

        try {
            logger.log(Level.INFO, "Consultando la cuenta para el usuario: {0}", usuario);
            entityManager = this.conexion.crearConexion();

            // Realizar la consulta para encontrar una cuenta con el usuario y la contraseña proporcionados
            TypedQuery<Cuenta> query = entityManager.createQuery(
                    "SELECT c FROM Cuenta c WHERE c.usuario = :usuario AND c.contrasena = :contrasena", Cuenta.class
            );
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", contraseña);

            // Obtener la lista de cuentas encontradas
            List<Cuenta> cuentas = query.getResultList();

            // Si se encontró al menos una cuenta, retornar la primera cuenta encontrada
            if (!cuentas.isEmpty()) {
                logger.log(Level.INFO, "Cuenta encontrada para el usuario: {0}", usuario);
                return cuentas.get(0); // Retorna la primera cuenta encontrada
            } else {
                logger.log(Level.WARNING, "No se encontró una cuenta para el usuario: {0}", usuario);
                return null; // Retorna null si no se encontró ninguna cuenta
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al consultar la cuenta para el usuario: " + usuario, e);
            throw new ModelException("Error al consultar la cuenta para el usuario: " + usuario, e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
