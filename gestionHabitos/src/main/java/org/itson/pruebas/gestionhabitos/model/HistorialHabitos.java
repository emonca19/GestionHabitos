/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa el historial de hábitos en la base de datos.
 * Esta entidad se utiliza para almacenar información sobre 
 * la realización de hábitos en fechas específicas.
 */
@Entity
public class HistorialHabitos implements Serializable {

    // Identificador único del historial de hábitos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Fecha en la que se registró el hábito
    @Column(name = "dia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dia;

    // Indica si el hábito fue completado en esa fecha
    @Column(name = "completado", nullable = false)
    private boolean completado;

    // Relación con la entidad Habito
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "id_habito", nullable = false)
    private Habito habito;

    /**
     * Constructor vacío para la clase HistorialHabitos.
     * Se utiliza para la creación de instancias sin inicializar atributos.
     */
    public HistorialHabitos() {
    }

    /**
     * Constructor para crear una nueva instancia de HistorialHabitos.
     *
     * @param dia       La fecha en que se registró el hábito.
     * @param completado Indica si el hábito fue completado.
     * @param habito    La instancia de Habito asociada a este historial.
     */
    public HistorialHabitos(Date dia, boolean completado, Habito habito) {
        this.dia = dia;
        this.completado = completado;
        this.habito = habito;
    }

    /**
     * Obtiene el identificador del historial de hábitos.
     *
     * @return el identificador único del historial.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del historial de hábitos.
     *
     * @param id El nuevo identificador para el historial.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha en la que se registró el hábito.
     *
     * @return La fecha del registro.
     */
    public Date getDia() {
        return dia;
    }

    /**
     * Establece la fecha en la que se registró el hábito.
     *
     * @param dia La nueva fecha del registro.
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * Indica si el hábito fue completado en la fecha registrada.
     *
     * @return true si el hábito fue completado, false en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Establece el estado de completado del hábito.
     *
     * @param completado El nuevo estado del hábito (completado o no).
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    /**
     * Obtiene la instancia de Habito asociada a este historial.
     *
     * @return La instancia de Habito.
     */
    public Habito getHabito() {
        return habito;
    }

    /**
     * Establece la instancia de Habito asociada a este historial.
     *
     * @param habito La nueva instancia de Habito para asociar.
     */
    public void setHabito(Habito habito) {
        this.habito = habito;
    }
    
}
