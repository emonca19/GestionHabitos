/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsistemacontroller;

import java.util.Date;
import java.util.List;

/**
 * Clase DTO que representa un registro de historial de hábitos. Se utiliza para transferir datos sobre un historial de hábitos entre capas de la aplicación.
 */
public class HistorialHabitosDTO {

    // Identificador único del registro de historial
    private Long id;

    // Fecha en la que se realizó el hábito
    private Date dia;

    // Indica si el hábito ha sido completado
    private boolean completado;

    // Identificador del hábito asociado
    private HabitoDTO habito;

    private static List<HistorialHabitosDTO> historialHabitosDTOs;
    /**
     * Constructor vacío
     */
    public HistorialHabitosDTO() {
    }

    public HistorialHabitosDTO(Date dia, boolean completado, HabitoDTO habito) {
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
    public HistorialHabitosDTO(Long id, Date dia, boolean completado, HabitoDTO habito) {
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
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del registro de historial.
     *
     * @param id El identificador del registro a establecer.
     */
    public void setId(Long id) {
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
    public HabitoDTO getHabito() {
        return habito;
    }

    /**
     * Establece el identificador del hábito asociado.
     *
     * @param habito El identificador del hábito a establecer.
     */
    public void setHabito(HabitoDTO habito) {
        this.habito = habito;
    }

    public static List<HistorialHabitosDTO> getHistorialHabitosDTOs() {
        return historialHabitosDTOs;
    }

    public static void setHistorialHabitosDTOs(List<HistorialHabitosDTO> historialHabitosDTOs) {
        HistorialHabitosDTO.historialHabitosDTOs = historialHabitosDTOs;
    }
    
    
}

