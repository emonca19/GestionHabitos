/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase DTO que representa un hábito. Se utiliza para transferir datos sobre un
 * hábito entre capas de la aplicación.
 */
public class HabitoDTO implements Serializable {

    // Identificador único del hábito
    private Long id;

    // Frecuencia con la que se debe realizar el hábito
    private String frecuencia;

    // Fecha en la que se creó el hábito
    private Date fechaCreacion;

    // Días de la semana en los que se puede realizar el hábito, representados como un valor Long
    private Long diasSemana;
    

    // Nombre del hábito
    private String nombre;

    // Identificador de la cuenta asociada al hábito
    private CuentaDTO cuentaId;
    

    /**
     * Constructor vacío
     */
    public HabitoDTO() {
    }

    /**
     * Constructor para crear un objeto HabitoDTO.
     *
     * @param id el identificador único del hábito.
     * @param frecuencia la frecuencia con la que se debe realizar el hábito.
     * @param fechaCreacion la fecha en que se creó el hábito.
     * @param diasSemana un valor Long que representa los días de la semana en
     * los que se puede realizar el hábito, almacenado como un patrón de bits.
     * @param nombre el nombre del hábito.
     * @param cuentaId el identificador de la cuenta asociada al hábito.
     */
    public HabitoDTO(Long id, String frecuencia, Date fechaCreacion, Long diasSemana, String nombre, CuentaDTO cuentaId) {
        this.id = id;
        this.frecuencia = frecuencia;
        this.fechaCreacion = fechaCreacion;
        this.diasSemana = diasSemana;
        this.nombre = nombre;
        this.cuentaId = cuentaId;
    }

    
    /**
     * Obtiene el identificador del hábito.
     *
     * @return el identificador del hábito.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del hábito.
     *
     * @param id el identificador del hábito.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la frecuencia del hábito.
     *
     * @return la frecuencia del hábito.
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * Establece la frecuencia del hábito.
     *
     * @param frecuencia la frecuencia del hábito.
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Obtiene la fecha de creación del hábito.
     *
     * @return la fecha en la que se creó el hábito.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del hábito.
     *
     * @param fechaCreacion la fecha en la que se creó el hábito.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene los días de la semana en los que se puede realizar el hábito.
     *
     * @return un valor Long que representa los días de la semana en los que se
     * puede realizar el hábito.
     */
    public Long getDiasSemana() {
        return diasSemana;
    }

    /**
     * Establece los días de la semana en los que se puede realizar el hábito.
     *
     * @param diasSemana el valor Long que representa los días de la semana en
     * los que se puede realizar el hábito.
     */
    public void setDiasSemana(Long diasSemana) {
        this.diasSemana = diasSemana;
    }

    /**
     * Obtiene el nombre del hábito.
     *
     * @return el nombre del hábito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hábito.
     *
     * @param nombre el nombre del hábito.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador de la cuenta asociada al hábito.
     *
     * @return el identificador de la cuenta asociada.
     */
    public CuentaDTO getCuentaId() {
        return cuentaId;
    }

    /**
     * Establece el identificador de la cuenta asociada al hábito.
     *
     * @param cuentaId el identificador de la cuenta asociada.
     */
    public void setCuentaId(CuentaDTO cuentaId) {
        this.cuentaId = cuentaId;
    }

    /**
     * Método para validar si todos los campos están completos.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    public boolean sonCamposValidos() {
        return frecuencia != null && !frecuencia.trim().isEmpty()
                && nombre != null && !nombre.trim().isEmpty()
                && diasSemana != null
                && cuentaId != null; // Asegúrate de que la cuenta no sea nula.
    }
}
