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
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
public class Conexion implements IConexion {

    private static EntityManagerFactory emFactory;

    static {
        try {
            emFactory = Persistence.createEntityManagerFactory("gestionHabitosPU");
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException("No se pudo inicializar EntityManagerFactory");
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
     * Cierra el EntityManagerFactory si está abierto, lo que permite finalizar
     * la conexión a la base de datos.
     */
    @Override
    public void cerrarConexion() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
