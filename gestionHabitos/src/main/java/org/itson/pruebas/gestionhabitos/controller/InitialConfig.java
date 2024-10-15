package org.itson.pruebas.gestionhabitos.controller;

import org.itson.pruebas.gestionhabitos.model.Conexion;
import org.itson.pruebas.gestionhabitos.model.IConexion;

/**
 *
 * @author rover
 */
public class InitialConfig {
    public static void iniciarConexion(){
        IConexion conexion = new Conexion();
        conexion.crearConexion();
    }
    
    
}
