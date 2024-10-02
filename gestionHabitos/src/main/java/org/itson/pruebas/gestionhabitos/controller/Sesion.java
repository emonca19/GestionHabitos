package org.itson.pruebas.gestionhabitos.controller;

/**
 *
 * @author user
 */
public class Sesion {

    private static CuentaDTO cuenta;

    private Sesion() {
    }

    public static void iniciarSesion(CuentaDTO cuenta) {
        Sesion.cuenta = cuenta;
    }

    public static CuentaDTO getCuenta() {
        return cuenta;
    }

    public static void cerrarSesion() {
        cuenta = null;
    }
}
