/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author elimo
 */
@Entity
public class Cuenta implements Serializable {

    // Versión de la clase para garantizar la compatibilidad durante la serialización
    private static final long serialVersionUID = 1L;

    // Identificador único de la cuenta
    @Id
    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    // Contraseña de la cuenta (no nulo)
    @Column(nullable = false, length = 100)
    private String contrasena;

    // Nombre del usuario (no nulo)
    @Column(nullable = false, length = 100)
    private String nombre;

    public Cuenta() {
    }

    
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
     * Verifica si dos objetos Cuenta son iguales. Dos cuentas se consideran iguales si tienen el mismo nombre de usuario.
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
