/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.negocio;


import java.util.List;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 *
 * @author crist
 */
public interface IGestionarHabitosNegocio {

     /**
     * Crea un nuevo hábito.
     *
     * @param habitoDTO Los datos del hábito a crear.
     * @return El DTO del hábito creado.
     */
    public HabitoDTO crearHabito(HabitoDTO habitoDTO);

    /**
     * Obtiene todos los hábitos.
     *
     * @return Una lista de DTOs de hábitos.
     */
    public List<HabitoDTO> obtenerHabitos();

    /**
     * Actualiza un hábito existente.
     *
     * @param id El ID del hábito a actualizar.
     * @param habitoDTO Los nuevos datos del hábito.
     * @return El DTO del hábito actualizado.
     */
   // public HabitoDTO actualizarHabito(Long id, HabitoDTO habitoDTO);

    /**
     * Elimina un hábito por su ID.
     *
     * @param id El habito del hábito a eliminar.
     */
    public void eliminarHabito(Habito habito);
}
