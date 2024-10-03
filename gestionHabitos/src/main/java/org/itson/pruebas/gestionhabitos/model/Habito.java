/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author elimo
 */
@Entity
public class Habito implements Serializable {

    private static final long serialVersionUID = 1L;

    // Identificador único de la actividad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

// Frecuencia de la actividad (no nulo)
    @Column(name = "frecuencia", nullable = false, length = 50)
    private String frecuencia;

// Fecha de creación de la actividad (no nulo)
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

// Días de la semana en bits
    @Column(name = "dias_semana", nullable = false)
    private String diasSemana;

// Nombre del hábito
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

// Relación con la entidad Cuenta (clave foránea)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "usuario", referencedColumnName = "usuario", nullable = false)
    private Cuenta cuenta;

    /**
     * Constructor para crear un hábito
     */
    public Habito() {
    }

    /**
     * Constructor para crear un hábito con los atributos especificados.
     *
     * @param frecuencia La frecuencia con la que se realiza el hábito (no debe
     * ser nula).
     * @param fechaCreacion La fecha de creación del hábito (no debe ser nula).
     * @param diasSemana Los días de la semana en que se realiza el hábito en
     * formato de bits.
     * @param nombre El nombre del hábito (no debe ser nulo).
     * @param cuenta La cuenta asociada al hábito (no debe ser nula).
     */
    public Habito(String frecuencia, Date fechaCreacion, String diasSemana, String nombre, Cuenta cuenta) {
        this.frecuencia = frecuencia;
        this.fechaCreacion = fechaCreacion;
        this.diasSemana = diasSemana;
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    /**
     * Obtiene la fecha de realización del hábito.
     *
     * @return fechaRealizacion la fecha en la que se realizó el hábito.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de realización del hábito.
     *
     * @param fechaCreacion la fecha a establecer para la realización del
     * hábito.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene los días de la semana en los que se puede realizar el hábito.
     *
     * @return diasSemana un valor Long que representa los días de la semana en
     * los que se puede realizar el hábito, almacenado como un patrón de bits.
     */
    public String getDiasSemana() {
        return diasSemana;
    }

    /**
     * Establece los días de la semana en los que se puede realizar el hábito.
     *
     * @param diasSemana el valor Long a establecer que representa los días de
     * la semana en los que se puede realizar el hábito, almacenado como un
     * patrón de bits.
     */
    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    /**
     * Obtiene el identificador de la actividad.
     *
     * @return el identificador de la actividad
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la actividad.
     *
     * @param id el identificador de la actividad
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la frecuencia de la actividad.
     *
     * @return la frecuencia de la actividad
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * Establece la frecuencia de la actividad.
     *
     * @param frecuencia la frecuencia de la actividad
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return el nombre de la actividad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombre el nombre de la actividad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cuenta asociada a la actividad.
     *
     * @return la cuenta asociada a la actividad
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Establece la cuenta asociada a la actividad.
     *
     * @param cuenta la cuenta asociada a la actividad
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Calcula el código hash de la actividad, basado en el identificador.
     *
     * @return el código hash de la actividad
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Verifica si dos objetos Actividad son iguales. Dos actividades se
     * consideran iguales si tienen el mismo identificador.
     *
     * @param object el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Habito)) {
            return false;
        }
        Habito other = (Habito) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
