/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.util.List;

/**
 *
 * @author elimo
 */
public interface IGestionarHabitosDAO {

    public Habito crearHabito(Habito habito) throws ModelException;

    public Habito actualizarHabito(Habito habito) throws ModelException;

    public boolean eliminarHabito(Long id) throws ModelException;

    public List<Habito> obtenerHabitos(Cuenta cuenta) throws ModelException;

    public Cuenta crearCuenta(Cuenta cuenta) throws ModelException;

    public Cuenta consultarCuenta(String usuario, String contraseña) throws ModelException;

}
