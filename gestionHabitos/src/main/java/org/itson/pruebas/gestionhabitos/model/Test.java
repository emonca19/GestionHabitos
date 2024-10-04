/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rover
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GestionarHabitosDAO ghdao = new GestionarHabitosDAO(new Conexion());
            List<HistorialHabitos> his = ghdao.consultarHistorialHabitos(new Date(), new Cuenta("rob", "wbTeVT4j8rIVFxJofihK045hEjY3z6296FZGNLmqtuzSlTikIe/47rk/Zrr4a/i9", "Roberto"));
            System.out.println(his);
        } catch (ModelException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
