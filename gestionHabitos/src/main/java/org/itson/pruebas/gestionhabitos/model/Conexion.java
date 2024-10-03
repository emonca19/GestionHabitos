package org.itson.pruebas.gestionhabitos.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementación de la interfaz {@link IConexion} para crear conexiones a la
 * base de datos utilizando JPA.
 *
 * Utiliza la configuración definida en el archivo {@code persistence.xml} para
 * establecer la conexión con la base de datos.
 *
 * @author Eliana Monge
 * @author Roberto García
 * @version 1.1
 */
public class Conexion implements IConexion {

    private static EntityManagerFactory emFactory;

    
    static {
        try {
            emFactory = Persistence.createEntityManagerFactory("gestionHabitos");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inicializar EntityManagerFactory");
        }
    }

    /**
     * Crea y devuelve una nueva conexión a la base de datos utilizando JPA.
     *
     * @return Una instancia de {@link EntityManager} que representa la conexión
     * a la base de datos.
     */
    @Override
    public EntityManager crearConexion() {
        return emFactory.createEntityManager();
    }

    /**
     * Método para cerrar el EntityManagerFactory al finalizar la aplicación
     */
    @Override
    public  void cerrarConexion() {
//        if (emFactory != null && emFactory.isOpen()) {
//            emFactory.close();
//        }
    }
}
