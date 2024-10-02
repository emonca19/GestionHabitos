/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.List;

/**
 *
 * @author crist
 */
public interface IGestionarHabitosNegocio {

    public void crearHabito(HabitoDTO habito) throws ControllerException;

    public void crearCuenta(CuentaDTO cuenta) throws ControllerException;

    public HabitoDTO actualizarHabito(HabitoDTO habito) throws ControllerException;

    public boolean eliminarHabito(Long id) throws ControllerException;

    public List<HabitoDTO> obtenerHabitos(CuentaDTO cuenta) throws ControllerException;

}
