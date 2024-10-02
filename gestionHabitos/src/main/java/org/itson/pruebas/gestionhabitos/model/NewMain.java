/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.pruebas.gestionhabitos.main.Main;

/**
 *
 * @author elimo
 */
public class NewMain {

    static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     * @throws org.itson.pruebas.gestionhabitos.model.ModelException
     */
    public static void main(String[] args) throws ModelException {
        IConexion conexion = new Conexion();
        IGestionarHabitosDAO gestionarHabitosDAO = new GestionarHabitosDAO(conexion);

        Cuenta cuenta = new Cuenta();

        cuenta.setUsuario("elianita");
        cuenta.setNombre("eliana");
        cuenta.setContrasena("auuuuuuu");

//        gestionarHabitosDAO.crearCuenta(cuenta);
        Habito hab = new Habito();
        hab.setCuenta(cuenta);
        hab.setFrecuencia("Diario");
        hab.setNombre("ejercitarme");
        hab.setRealizado(false);

        gestionarHabitosDAO.eliminarHabito(51L);
//        System.out.println(gestionarHabitosDAO.obtenerHabitos(cuenta).getFirst().getId());

    }
}
