package org.itson.pruebas.gestionhabitos.modelo;

import javax.persistence.EntityManager;

/**
 * Interfaz para crear conexiones a la base de datos utilizando JPA.
 * 
 * <p>Define un método para crear y obtener una instancia de {@link EntityManager} que gestiona la conexión a la base de datos.</p>
 * 
 * @author Eliana Monge
 * @author Roberto García
 * @version 1.0
 */
public interface IConexion {

    /**
     * Crea y devuelve una nueva conexión a la base de datos utilizando JPA.
     * 
     * @return Una instancia de {@link EntityManager} que representa la conexión a la base de datos.
     */
    public EntityManager crearConexion();
}