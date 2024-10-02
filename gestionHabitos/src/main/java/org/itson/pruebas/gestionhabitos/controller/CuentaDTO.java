/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

/**
 * Clase DTO que representa una cuenta de usuario.
 * Se utiliza para transferir datos sobre una cuenta entre capas de la aplicación.
 */
public class CuentaDTO {

    // Nombre completo del usuario
    private String nombre;

    // Nombre de usuario (identificador único)
    private String usuario;

    // Contraseña del usuario
    private String contraseña;

    /**
     * Constructor vacío
     */
    public CuentaDTO() {
    }

    
    /**
     * Constructor para crear una nueva instancia de CuentaDTO.
     *
     * @param nombre     el nombre completo del usuario.
     * @param usuario    el nombre de usuario.
     * @param contraseña la contraseña del usuario.
     */
    public CuentaDTO(String nombre, String usuario, String contraseña) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre completo del usuario a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario el nombre de usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contraseña la contraseña a establecer para el usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
     /**
     * Método para validar si todos los campos están completos.
     * 
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    public boolean sonCamposValidos() {
        return nombre != null && !nombre.trim().isEmpty() &&
               usuario != null && !usuario.trim().isEmpty() &&
               contraseña != null && !contraseña.trim().isEmpty();
    }
}
