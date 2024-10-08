package org.itson.pruebas.gestionhabitos.main;

import com.mycompany.subsistemacontroller.InitialConfig;
import com.mycompany.subsistemamodelo.ModelException;

import org.itson.pruebas.gestionhabitos.view.FrameContenedor;

/**
 *
 * @author rover
 */
public class Main {

    public static void main(String[] args) throws ModelException {
        FrameContenedor frameContenedor = new FrameContenedor();
        frameContenedor.mostrarInicioSesion();
        frameContenedor.setVisible(true);
        InitialConfig.iniciarConexion();
    }
}
