/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.itson.pruebas.gestionhabitos.main;

import org.itson.pruebas.gestionhabitos.controller.InitialConfig;
import org.itson.pruebas.gestionhabitos.view.FrameContenedor;

/**
 *
 * @author rover
 */
public class Main {

    public static void main(String[] args) {
        FrameContenedor frameContenedor = new FrameContenedor();
        frameContenedor.mostrarInicioSesion();
        frameContenedor.setVisible(true);
        InitialConfig.iniciarConexion();
    }
}
