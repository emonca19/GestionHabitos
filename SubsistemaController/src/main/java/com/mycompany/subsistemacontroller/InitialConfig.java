/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsistemacontroller;

import com.mycompany.subsistemamodelo.Conexion;
import com.mycompany.subsistemamodelo.IConexion;

/**
 *
 * @author crist
 */
public class InitialConfig {
      public static void iniciarConexion(){
        IConexion conexion = new Conexion();
        conexion.crearConexion();
    }
}
