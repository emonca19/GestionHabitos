package org.itson.pruebas.gestionhabitos.model;

/**
 * Excepción personalizada para errores del modelo.
 *
 * <p>
 * Extiende {@link Exception} para permitir manejo específico de errores en la
 * capa de modelo.</p>
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
public class ModelException extends Exception {

    /**
     * Crea una nueva {@code ModelException} sin mensaje.
     */
    public ModelException() {
        super();
    }

    /**
     * Crea una {@code ModelException} con el mensaje especificado.
     *
     * @param message Mensaje que describe el error.
     */
    public ModelException(String message) {
        super(message);
    }

    /**
     * Crea una {@code ModelException} con el mensaje y causa especificados.
     *
     * @param message Mensaje que describe el error.
     * @param cause La causa de la excepción.
     */
    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una {@code ModelException} con la causa especificada.
     *
     * @param cause La causa de la excepción.
     */
    public ModelException(Throwable cause) {
        super(cause);
    }
}
