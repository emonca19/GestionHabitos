/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author elimo
 */
@Entity
public class Habitos implements Serializable {

    private static final long serialVersionUID = 1L;

    // Identificador único de la actividad
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Frecuencia de la actividad (no nulo)
    @Column(nullable = false, length = 50)
    private String frecuencia;

    // Nombre de la actividad (no nulo)
    @Column(nullable = false, length = 100)
    private String nombre;

     // Relación con la entidad Cuenta (clave foránea)
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "usuario", nullable = false)
    private Cuenta cuenta;

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
     * Verifica si dos objetos Actividad son iguales.
     * Dos actividades se consideran iguales si tienen el mismo identificador.
     * 
     * @param object el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Habitos)) {
            return false;
        }
        Habitos other = (Habitos) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
