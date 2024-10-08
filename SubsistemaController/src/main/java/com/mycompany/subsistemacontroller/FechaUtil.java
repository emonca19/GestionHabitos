/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.subsistemacontroller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Utilidad para gestionar y formatear fechas.
 * <p>
 * Esta clase proporciona métodos estáticos para manejar fechas en varios formatos,
 * así como para convertir entre objetos {@link LocalDate} y {@link Date}.
 * Además, se puede obtener la fecha actual y formatearla en español.
 * </p>
 * 
 * <h2>Uso básico:</h2>
 * <pre>{@code
 * FechaUtil.establecerFechaHoy();
 * String fechaFormateada = FechaUtil.getFechaFormateada();
 * }</pre>
 * 
 * @author rover
 */
public class FechaUtil {

    /** Almacena la fecha en formato String después de ser formateada. */
    private static String fechaFormateada;

    /** Almacena la fecha actual en formato {@link LocalDate}. */
    private static LocalDate fechaActual;

    /**
     * Constructor privado para evitar la instanciación de la clase.
     * Esta es una clase de utilidad con solo métodos estáticos.
     */
    private FechaUtil() {
    }

    /**
     * Establece una cadena de texto con la fecha formateada.
     *
     * @param fecha La fecha formateada en {@link String}.
     */
    public static void setFechaFormateada(String fecha) {
        fechaFormateada = fecha;
    }

    /**
     * Obtiene la fecha formateada como una cadena de texto.
     *
     * @return La fecha formateada en {@link String}.
     */
    public static String getFechaFormateada() {
        return fechaFormateada;
    }

    /**
     * Establece la fecha actual en formato de texto en español.
     * <p>
     * El formato de la fecha es: "NombreDelDía Día de NombreDelMes Año", por ejemplo:
     * "lunes 1 de enero de 2023".
     * </p>
     */
    public static void establecerFechaHoy() {
        LocalDate fechaHoy = LocalDate.now();
        String nombreDia = fechaHoy.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
        String mes = fechaHoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toLowerCase();
        
        FechaUtil.setFechaFormateada(nombreDia + " " + fechaHoy.getDayOfMonth() + " de " + mes + " de " + fechaHoy.getYear());
    }

    /**
     * Obtiene la fecha actual en formato {@link LocalDate}.
     *
     * @return La fecha actual como un {@link LocalDate}.
     */
    public static LocalDate getFechaActual() {
        return fechaActual;
    }

    /**
     * Establece la fecha actual en formato {@link LocalDate}.
     *
     * @param fecha La fecha actual en formato {@link LocalDate}.
     */
    public static void setFechaActual(LocalDate fecha) {
        FechaUtil.fechaActual = fecha;
    }

    /**
     * Convierte un objeto {@link LocalDate} a un objeto {@link Date}.
     *
     * @param localDate El objeto {@link LocalDate} a convertir.
     * @return El objeto {@link Date} correspondiente a la fecha proporcionada.
     */
    public static Date convertirLocalDateADate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
