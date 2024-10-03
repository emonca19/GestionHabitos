/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.Date;
import org.itson.pruebas.gestionhabitos.model.Habito;

/**
 * Clase DTO que representa un registro de historial de hábitos. Se utiliza para transferir datos sobre un historial de hábitos entre capas de la aplicación.
 */
public class HistorialHabitosDTO {

    // Identificador único del registro de historial
    private int id;

    // Fecha en la que se realizó el hábito
    private Date dia;

    // Indica si el hábito ha sido completado
    private boolean completado;

    // Identificador del hábito asociado
    private Habito habito;

    /**
     * Constructor vacío
     */
    public HistorialHabitosDTO() {
    }

    public HistorialHabitosDTO(Date dia, boolean completado, Habito habito) {
        this.dia = dia;
        this.completado = completado;
        this.habito = habito;
    }

    /**
     * Constructor para crear un nuevo objeto HistorialHabitosDTO.
     *
     * @param id El identificador del registro de historial.
     * @param dia La fecha del hábito.
     * @param completado Indica si el hábito ha sido completado.
     * @param habito El identificador del hábito asociado.
     */
    public HistorialHabitosDTO(int id, Date dia, boolean completado, Habito habito) {
        this.id = id;
        this.dia = dia;
        this.completado = completado;
        this.habito = habito;
    }

    /**
     * Obtiene el identificador del registro de historial.
     *
     * @return El identificador del registro.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del registro de historial.
     *
     * @param id El identificador del registro a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha en la que se realizó el hábito.
     *
     * @return La fecha del hábito.
     */
    public Date getDia() {
        return dia;
    }

    /**
     * Establece la fecha en la que se realizó el hábito.
     *
     * @param dia La fecha a establecer.
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * Indica si el hábito ha sido completado.
     *
     * @return true si el hábito ha sido completado, false en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Establece el estado de completado del hábito.
     *
     * @param completado El nuevo estado de completado.
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    /**
     * Obtiene el identificador del hábito asociado.
     *
     * @return El identificador del hábito.
     */
    public Habito getHabito() {
        return habito;
    }

    /**
     * Establece el identificador del hábito asociado.
     *
     * @param habito El identificador del hábito a establecer.
     */
    public void setHabito(Habito habito) {
        this.habito = habito;
    }
}
