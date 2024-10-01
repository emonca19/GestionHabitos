/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 * Clase que implementa la lógica de negocio para gestionar los hábitos.
 */
public class GestionarHabitosNegocio implements IGestionarHabitosNegocio {

    private GestionarHabitosDAO habitoDAO;

    /**
     * Constructor de la clase.
     *
     * @param habitoDAO el objeto DAO para la gestión de los hábitos
     */
    public GestionarHabitosNegocio(GestionarHabitosDAO habitoDAO) {
        this.habitoDAO = habitoDAO;
    }

    /**
     * Crea un nuevo hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a crear
     * @throws ControllerException si ocurre un error al crear el hábito
     */
    @Override
    public void crearHabito(HabitoDTO habitoDTO) throws ControllerException {
        try {
            habitoDAO.crearHabito(HabitoDTOConvertirAEntidad(habitoDTO));
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Obtiene la lista de hábitos asociados a una cuenta específica.
     *
     * @param cuenta el DTO de la cuenta cuyos hábitos se desean obtener
     * @return la lista de hábitos convertidos a DTO
     * @throws ControllerException si ocurre un error al obtener los hábitos
     */
    @Override
    public List<HabitoDTO> obtenerHabitos(CuentaDTO cuenta) throws ControllerException {
        List<Habito> habitos = null;
        try {
            habitos = habitoDAO.obtenerHabitos(cuentaDTOAEntidad(cuenta));
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
        return habitos.stream().map(this::HabitoConvertirADTO).collect(Collectors.toList());
    }

    /**
     * Elimina un hábito por su ID.
     *
     * @param id el ID del hábito a eliminar
     * @return true si el hábito fue eliminado, false en caso contrario
     * @throws ControllerException si ocurre un error al eliminar el hábito
     */
    @Override
    public boolean eliminarHabito(Long id) throws ControllerException {
        boolean eliminado = false;
        try {
            eliminado = habitoDAO.eliminarHabito(id);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
        return eliminado;
    }

    /**
     * Actualiza un hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a actualizar
     * @return el hábito actualizado convertido a DTO
     * @throws ControllerException si ocurre un error al actualizar el hábito
     */
    @Override
    public HabitoDTO actualizarHabito(HabitoDTO habitoDTO) throws ControllerException {
        try {
            Habito habito = habitoDAO.actualizarHabito(HabitoDTOConvertirAEntidad(habitoDTO));
            return HabitoConvertirADTO(habito);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Convierte una entidad Habito en un DTO HabitoDTO.
     *
     * @param habito la entidad Habito a convertir
     * @return el DTO HabitoDTO
     */
    private HabitoDTO HabitoConvertirADTO(Habito habito) {
        return new HabitoDTO(
                habito.getId(),
                habito.getFrecuencia(),
                habito.isRealizado(),
                habito.getFecha(),
                habito.getNombre(),
                habito.getCuenta().getUsuario()
        );
    }

    /**
     * Convierte un DTO HabitoDTO en una entidad Habito.
     *
     * @param habitoDTO el DTO HabitoDTO a convertir
     * @return la entidad Habito
     */
    private Habito HabitoDTOConvertirAEntidad(HabitoDTO habitoDTO) {
        Habito habito = new Habito();
        habito.setId(habitoDTO.getId());
        habito.setFrecuencia(habitoDTO.getFrecuencia());
        habito.setRealizado(habitoDTO.isRealizado());
        habito.setFecha(habitoDTO.getFecha());
        habito.setNombre(habitoDTO.getNombre());
        return habito;
    }

    /**
     * Convierte un DTO CuentaDTO en una entidad Cuenta.
     *
     * @param cuentaDTO el DTO CuentaDTO a convertir
     * @return la entidad Cuenta
     */
    public Cuenta cuentaDTOAEntidad(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(cuentaDTO.getUsuario());
        cuenta.setContrasena(cuentaDTO.getContraseña());
        cuenta.setNombre(cuentaDTO.getNombre());
        return cuenta;
    }
}
