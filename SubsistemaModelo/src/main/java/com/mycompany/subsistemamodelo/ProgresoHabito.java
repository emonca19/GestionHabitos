package com.mycompany.subsistemamodelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase que representa el progreso de un hábito, incluyendo su nombre y la
 * cantidad de días realizados y totales.
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
public class ProgresoHabito {

    private String nombre;
    private int diasRealizados;
    private int diasTotales;

    /**
     * Constructor para crear un objeto {@link ProgresoHabito}.
     *
     * @param nombre El nombre del hábito.
     * @param diasRealizados La cantidad de días en que se ha realizado el
     * hábito.
     * @param diasTotales La cantidad total de días en que el hábito se puede
     * realizar.
     */
    public ProgresoHabito(String nombre, int diasRealizados, int diasTotales) {
        this.nombre = nombre;
        this.diasRealizados = diasRealizados;
        this.diasTotales = diasTotales;
    }

    /**
     * Obtiene el nombre del hábito.
     *
     * @return El nombre del hábito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hábito.
     *
     * @param nombre El nombre del hábito a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public int getTotalDias() {
        return diasTotales;
    }

    /**
     * Establece la cantidad total de días.
     *
     * @param diasTotales La cantidad total de días a establecer.
     */
    public void setTotalDias(int diasTotales) {
        this.diasTotales = diasTotales;
    }

    /**
     * Devuelve una representación en cadena del progreso del hábito.
     *
     * @return Una cadena que representa el progreso del hábito.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProgresoHabito{");
        sb.append("nombre=").append(nombre);
        sb.append(", diasRealizados=").append(diasRealizados);
        sb.append(", diasTotales=").append(diasTotales);
        sb.append('}');
        return sb.toString();
    }
}

