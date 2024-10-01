/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author elimo
 */
public class GestionarHabitosDAO implements IGestionarHabitosDAO {

    private IConexion conexion;

    // Constructor
    public GestionarHabitosDAO(IConexion conexion) {
        this.conexion=conexion;
    }

    /**
     * Crea un nuevo hábito y lo persiste en la base de datos.
     *
     * @param nuevoHabito el hábito a crear
     * @return el nuevo hábito creado
     * @throws org.itson.pruebas.gestionhabitos.model.ModelException
     */
    @Override
    public Habito crearHabito(Habito nuevoHabito) throws ModelException{
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(nuevoHabito); // Persiste el nuevo hábito
        entityManager.getTransaction().commit();
        entityManager.close();

        return nuevoHabito;
        
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Error al crear habito con id: "+nuevoHabito.getId(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Actualiza un hábito existente en la base de datos.
     *
     * @param habito el hábito a actualizar
     * @return el hábito actualizado
     * @throws ModelException si ocurre un error durante la actualización
     */
    @Override
    public Habito actualizarHabito(Habito habito) throws ModelException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoExistente = entityManager.find(Habito.class, habito.getId()); // Busca el hábito por ID
            if (habitoExistente != null) {
                // Actualiza las propiedades del hábito existente
                habitoExistente.setNombre(habito.getNombre());
                habitoExistente.setFrecuencia(habito.getFrecuencia());
                habitoExistente.setRealizado(habito.isRealizado());
                habitoExistente.setFecha(habito.getFecha());
                transaction.commit();
                return habitoExistente; // Retorna el hábito actualizado
            } else {
                transaction.rollback();
                throw new ModelException("Hábito no encontrado con ID: " + habito.getId());
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Error al actualizar hábito con id: " + habito.getId(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Elimina un hábito de la base de datos.
     *
     * @param habito el hábito a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws ModelException si ocurre un error durante la eliminación
     */
    @Override
    public boolean eliminarHabito(Habito habito) throws ModelException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = this.conexion.crearConexion();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Habito habitoEncontrado = entityManager.find(Habito.class, habito.getId());
            if (habitoEncontrado != null) {
                entityManager.remove(habitoEncontrado); // Elimina el hábito
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                throw new ModelException("Hábito no encontrado con ID: " + habito.getId());
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ModelException("Error al eliminar hábito con id: " + habito.getId(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Devuelve la lista de hábitos desde la base de datos.
     *
     * @return la lista de hábitos
     * @throws ModelException si ocurre un error al obtener la lista de hábitos
     */
    @Override
    public List <Habito> verHabitos() throws ModelException {
        EntityManager entityManager = null;

        try {
            entityManager = this.conexion.crearConexion();
            TypedQuery<Habito> query = entityManager.createQuery("SELECT h FROM Habito h", Habito.class);
            return (List) query.getResultList(); // Obtiene la lista de hábitos
        } catch (Exception e) {
            throw new ModelException("Error al obtener la lista de hábitos", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}