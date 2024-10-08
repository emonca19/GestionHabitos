package com.mycompany.subsistemacontroller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase que representa el progreso de un hábito, incluyendo su nombre y la
 * cantidad de días realizados y totales.
 *
 * @author Roberto García
 */
public class ProgresoHabitoDTO {

    private String nombreHabito;
    private int diasRealizados;
    private int diasTotales;

    /**
     * Constructor para crear un objeto {@link ProgresoHabitoDTO}.
     *
     * @param nombreHabito El nombre del hábito.
     * @param diasRealizados La cantidad de días en que se ha realizado el
     * hábito.
     * @param diasTotales La cantidad total de días en que el hábito se puede
     * realizar.
     */
    public ProgresoHabitoDTO(String nombreHabito, int diasRealizados, int diasTotales) {
        this.nombreHabito = nombreHabito;
        this.diasRealizados = diasRealizados;
        this.diasTotales = diasTotales;
    }

    /**
     * Obtiene el nombre del hábito.
     *
     * @return El nombre del hábito.
     */
    public String getNombreHabito() {
        return nombreHabito;
    }

    /**
     * Establece el nombre del hábito.
     *
     * @param nombreHabito El nombre del hábito a establecer.
     */
    public void setNombreHabito(String nombreHabito) {
        this.nombreHabito = nombreHabito;
    }

    /**
     * Obtiene la cantidad de días realizados.
     *
     * @return La cantidad de días en que se ha realizado el hábito.
     */
    public int getDiasRealizados() {
        return diasRealizados;
    }

    /**
     * Establece la cantidad de días realizados.
     *
     * @param diasRealizados La cantidad de días a establecer.
     */
    public void setDiasRealizados(int diasRealizados) {
        this.diasRealizados = diasRealizados;
    }

    /**
     * Obtiene la cantidad total de días.
     *
     * @return La cantidad total de días en que el hábito se puede realizar.
     */
    public int getDiasTotales() {
        return diasTotales;
    }

    /**
     * Establece la cantidad total de días.
     *
     * @param diasTotales La cantidad total de días a establecer.
     */
    public void setDiasTotales(int diasTotales) {
        this.diasTotales = diasTotales;
    }

}

