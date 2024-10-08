/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsistemamodelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representa una cuenta de usuario en el sistema de gestión de hábitos. Cada
 * cuenta tiene un nombre de usuario único, una contraseña y un nombre asociado.
 *
 * Esta clase es serializable para permitir su persistencia y transmisión.
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 * @version 1.0
 */
@Entity
public class Cuenta implements Serializable {

    // Versión de la clase para garantizar la compatibilidad durante la serialización
    private static final long serialVersionUID = 1L;

    // Identificador único del usuario
    @Id
    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    // Contraseña de la cuenta (no nulo)
    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    // Nombre del usuario (no nulo)
    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombre;

    /**
     * Constructor por defecto para la clase Cuenta. Este constructor no
     * inicializa los atributos de la cuenta.
     */
    public Cuenta() {
    }

    /**
     * Constructor para crear una cuenta con los atributos especificados.
     *
     * @param usuario El nombre de usuario de la cuenta (debe ser único).
     * @param contrasena La contraseña de la cuenta (no debe ser nula).
     * @param nombre El nombre del usuario asociado a la cuenta (no debe ser
     * nulo).
     */
    public Cuenta(String usuario, String contrasena, String nombre) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario el nombre de usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña de la cuenta.
     *
     * @return la contraseña de la cuenta
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña de la cuenta.
     *
     * @param contrasena la contraseña de la cuenta
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Calcula el código hash de la cuenta, basado en el nombre de usuario.
     *
     * @return el código hash de la cuenta
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    /**
     * Verifica si dos objetos Cuenta son iguales. Dos cuentas se consideran
     * iguales si tienen el mismo nombre de usuario.
     *
     * @param object el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        return (this.usuario != null || other.usuario == null) && (this.usuario == null || this.usuario.equals(other.usuario));
    }

}

