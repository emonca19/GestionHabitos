package org.itson.pruebas.gestionhabitos.controller;

/**
 *
 * @author user
 */
public class Sesion {

    private static String usuario;
    private static String nombre;

    Sesion() {
    }

    public static void iniciarSesion(String usuario, String nombre) {
        Sesion.usuario = usuario;
        Sesion.nombre = nombre;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setUsuario(String usuarioNuevo) {
        usuario = usuarioNuevo;
    }

    public static void setNombre(String nombreNuevo) {
        nombre = nombreNuevo;
    }

    public static void cerrarSesion() {
        usuario = null;
        nombre = null;
    }
}
