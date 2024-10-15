package org.itson.pruebas.gestionhabitos.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author rover
 */
public class FechaUtil {
    private static String fechaFormateada;
    private static LocalDate fechaActual;
    
    private FechaUtil() {
    }
    
    public static void setFechaFormateada(String fecha) {
        fechaFormateada = fecha;
    }
    
    public static String getFechaFormateada() {
        return fechaFormateada;
    }
    
    public static void establecerFechaHoy() {
        LocalDate fechaHoy = LocalDate.now();
        String nombreDia = fechaHoy.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
        String mes = fechaHoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toLowerCase();
        
        FechaUtil.setFechaFormateada(nombreDia + " " + fechaHoy.getDayOfMonth() + " de " + mes + " de " + fechaHoy.getYear());
    }
    
    public static LocalDate getFechaActual() {
        return fechaActual;
    }
    
    public static void setFechaActual(LocalDate fecha) {
        FechaUtil.fechaActual = fecha;
    }
    
    public static Date convertirLocalDateADate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
