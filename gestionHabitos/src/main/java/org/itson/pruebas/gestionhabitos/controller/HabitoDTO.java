/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.Date;

/**
 *
 * @author crist
 */
public class HabitoDTO {
    
     private Long id;
    private String frecuencia;
    private boolean realizado;
    private Date fecha;
    private String nombre;
    private String usuarioCuenta;
    
    public HabitoDTO() {
    }

    public HabitoDTO(Long id, String frecuencia, boolean realizado, Date fecha, String nombre, String usuarioCuenta) {
        this.id = id;
        this.frecuencia = frecuencia;
        this.realizado = realizado;
        this.fecha = fecha;
        this.nombre = nombre;
        this.usuarioCuenta = usuarioCuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioCuenta() {
        return usuarioCuenta;
    }

    public void setUsuarioCuenta(String usuarioCuenta) {
        this.usuarioCuenta = usuarioCuenta;
    }
}
