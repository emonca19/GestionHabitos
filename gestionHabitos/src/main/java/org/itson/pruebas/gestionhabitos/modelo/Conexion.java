package org.itson.pruebas.gestionhabitos.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Implementación de la interfaz {@link IConexion} para crear conexiones a la base de datos utilizando JPA.
 * 
 * <p>Utiliza la configuración definida en el archivo {@code persistence.xml} para establecer la conexión
 * con la base de datos.</p>
 * 
 * @author Eliana Monge
 * @author Roberto García
 * @version 1.0
 */
public class Conexion implements IConexion {

    /**
     * Crea y devuelve una nueva conexión a la base de datos utilizando JPA.
     * 
     * @return Una instancia de {@link EntityManager} que representa la conexión a la base de datos.
     */
    @Override
    public EntityManager crearConexion() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("gestionHabitos");
        EntityManager eManager = emFactory.createEntityManager();
        
        return eManager;
    }
}
