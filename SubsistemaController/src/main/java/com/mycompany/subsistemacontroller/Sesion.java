/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsistemacontroller;

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