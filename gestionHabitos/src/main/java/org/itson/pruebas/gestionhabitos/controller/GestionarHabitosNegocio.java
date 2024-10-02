/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.itson.pruebas.gestionhabitos.model.Conexion;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.HistorialHabitos;
import org.itson.pruebas.gestionhabitos.model.IConexion;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 * Clase que implementa la lógica de negocio para gestionar los hábitos.
 */
public class GestionarHabitosNegocio implements IGestionarHabitosNegocio {

    private final GestionarHabitosDAO habitoDAO;

    /**
     * Constructor de la clase.
     *
     */
    public GestionarHabitosNegocio() {
        IConexion conexion = new Conexion();
        this.habitoDAO = new GestionarHabitosDAO(conexion);
    }

    /**
     * Crea un nuevo hábito a partir de un DTO.
     *
     * @param habitoDTO el DTO del hábito a crear
     * @throws ControllerException si ocurre un error al crear el hábito
     */
    @Override
    public void crearHabito(HabitoDTO habitoDTO) throws ControllerException {
        if (habitoDTO.sonCamposValidos()) {
            try {
                habitoDAO.crearHabito(HabitoDTOConvertirAEntidad(habitoDTO));
            } catch (ModelException ex) {
                throw new ControllerException(ex);
            }
        } else {
            throw new ControllerException("No se puede crear cuenta porque no cumple con los datos necesarios");
        }
    }

    /**
     * Crea una cuenta
     *
     * @param cuentaDTO Cuenta a crear
     * @throws ControllerException si algo sale mal al crear la cuenta
     */
    @Override
    public void crearCuenta(CuentaDTO cuentaDTO) throws ControllerException {
        if (cuentaDTO.sonCamposValidos()) {
            try {
                habitoDAO.crearCuenta(cuentaDTOAEntidad(cuentaDTO));
            } catch (ModelException ex) {
                throw new ControllerException(ex);
            }
        } else {
            throw new ControllerException("No se puede crear cuenta porque no cumple con los datos necesarios");
        }
    }

    /**
     * Obtiene la lista de hábitos asociados a una cuenta específica.
     *
     * @param cuentaDTO el DTO de la cuenta cuyos hábitos se desean obtener
     * @return la lista de hábitos convertidos a DTO
     * @throws ControllerException si ocurre un error al obtener los hábitos
     */
    @Override
    public List<HabitoDTO> obtenerHabitos(CuentaDTO cuentaDTO) throws ControllerException {
        List<Habito> habitos = null;
        try {
            habitos = habitoDAO.obtenerHabitos(cuentaDTOAEntidad(cuentaDTO));
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
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @param contraseña Verificar que concuerda con la contraseña
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta correctamente
     */
    @Override
    public CuentaDTO consultarCuenta(String usuario, String contraseña) throws ControllerException {
        try {
            return entidadACuentaDTO(habitoDAO.consultarCuenta(usuario, contraseña));
        } catch (ModelException e) {
            throw new ControllerException(e);
        }
    }

    /**
     * Convierte una entidad Habito en un DTO HabitoDTO.
     *
     * @param habito la entidad Habito a convertir
     * @return el DTO HabitoDTO que representa el hábito
     */
    private HabitoDTO HabitoConvertirADTO(Habito habito) {
        return new HabitoDTO(
                habito.getId(),
                habito.getFrecuencia(),
                habito.getFechaCreacion(),
                habito.getDiasSemana(),
                habito.getNombre(),
                habito.getDiasSemanaRealizado(),
                habito.getCuenta() // Asumiendo que 'cuenta' no es null
        );
    }

    /**
     * Convierte un DTO HabitoDTO en una entidad Habito.
     *
     * @param habitoDTO el DTO HabitoDTO a convertir
     * @return la entidad Habito que representa el hábito
     */
    private Habito HabitoDTOConvertirAEntidad(HabitoDTO habitoDTO) {
        Habito habito = new Habito();
        habito.setId(habitoDTO.getId());
        habito.setFrecuencia(habitoDTO.getFrecuencia());
        habito.setFechaCreacion(habitoDTO.getFechaCreacion());
        habito.setDiasSemanaRealizado(habitoDTO.getDiasSemanaRealizado());
        habito.setDiasSemana(habitoDTO.getDiasSemana());
        habito.setCuenta(habitoDTO.getCuentaId());
        return habito;
    }

    /**
     * Convierte un DTO CuentaDTO en una entidad Cuenta.
     *
     * @param cuentaDTO el DTO CuentaDTO a convertir
     * @return la entidad Cuenta
     */
    public Cuenta cuentaDTOAEntidad(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta(cuentaDTO.getUsuario(), cuentaDTO.getContraseña(), cuentaDTO.getNombre());

        return cuenta;
    }

    /**
     * Convierte una entidad de cuenta a una cuentaDTO
     *
     * @param cuenta Cuenta a convertir a DTO
     * @return CuenataDTO convertida
     */
    public CuentaDTO entidadACuentaDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO(cuenta.getNombre(), cuenta.getUsuario(), cuenta.getContrasena());
        return cuentaDTO;
    }

    /**
     * Convierte una entidad HistorialHabitos en un DTO HistorialHabitosDTO.
     *
     * @param historial el objeto HistorialHabitos a convertir
     * @return el DTO HistorialHabitosDTO que representa el historial de hábitos
     */
    private HistorialHabitosDTO historialConvertirADTO(HistorialHabitos historial) {
        return new HistorialHabitosDTO(
                historial.getId(),
                historial.getDia(),
                historial.isCompletado(),
                historial.getHabito() // Asumiendo que 'idHabito' no es null
        );
    }

    /**
     * Convierte un DTO HistorialHabitosDTO en una entidad HistorialHabitos.
     *
     * @param historialDTO el DTO HistorialHabitosDTO a convertir
     * @return la entidad HistorialHabitos que representa el historial de hábitos
     */
    private HistorialHabitos historialDTOConvertirAEntidad(HistorialHabitosDTO historialDTO) {
        HistorialHabitos historial = new HistorialHabitos();
        historial.setId(historialDTO.getId());
        historial.setDia(historialDTO.getDia());
        historial.setCompletado(historialDTO.isCompletado());
        historial.setHabito(historialDTO.getHabito()); // Asumiendo que el idHabito es un entero
        return historial;
    }

    /**
     * Metodo que devuelve los habitos que concuerden con el usuario y dia de la semana
     *
     * @param cuenta Cuenta a buscar los habitos
     * @param diaSemana Dia de la semana que concuerde con los habitos
     * @return Lista de habitosDTO que concuerden con las especificaciones
     * @throws ControllerException Si no se encuentra infromacion
     */
    public List<HabitoDTO> identificarDias(CuentaDTO cuenta, String diaSemana) throws ControllerException {
        List<HabitoDTO> habitos = obtenerHabitos(cuenta);
        List<HabitoDTO> habitosPorDia = new ArrayList<>();
        for (int i = 0; i < habitos.size(); i++) {
            String dias = String.valueOf(habitos.get(i).getDiasSemana());
            if (dias.charAt(0) == 1 && diaSemana.equalsIgnoreCase("lunes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(1) == 1 && diaSemana.equalsIgnoreCase("martes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(2) == 1 && diaSemana.equalsIgnoreCase("miercoles")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(3) == 1 && diaSemana.equalsIgnoreCase("jueves")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(4) == 1 && diaSemana.equalsIgnoreCase("viernes")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(5) == 1 && diaSemana.equalsIgnoreCase("sabado")) {
                habitosPorDia.add(habitos.get(i));
            } else if (dias.charAt(6) == 1 && diaSemana.equalsIgnoreCase("domingo")) {
                habitosPorDia.add(habitos.get(i));
            } else {
                throw new ControllerException("No se ha encontrado información");
            }

        }
        return habitosPorDia;
    }

    /**
     * Buscar historial de hábitos por fecha y ID de hábito.
     *
     * @param dia La fecha a buscar.
     * @param idHabito El identificador del hábito.
     * @return Lista de registros de historial de hábitos que coinciden con la fecha y el ID de hábito.
     * @throws ControllerException Si ocurre un error al buscar
     */
    @Override
    public HistorialHabitosDTO buscarPorFechaYIdHabito(Date dia, int idHabito) throws ControllerException {
        try {
            return historialConvertirADTO(habitoDAO.buscarPorFechaYIdHabito(dia, idHabito));
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    /**
     * Actualizar un registro de historial de hábitos utilizando la entidad.
     *
     * @param historial Habito a actualizar.
     * @return El registro actualizado de historial de hábitos.
     * @throws ControllerException Si ocurre un error al actualizar.
     */
    @Override
    public HistorialHabitosDTO actualizarHistorial(HistorialHabitosDTO historial) throws ControllerException {
        try {
            HistorialHabitosDTO habito = historialConvertirADTO(habitoDAO.actualizarHistorial(historialDTOConvertirAEntidad(historial)));
            return habito;
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }
    
    /**
     * Consulta la existencia de una cuenta
     *
     * @param usuario Usuario a consultar
     * @return Cuenta consultada
     * @throws ControllerException si no se puede consultar la cuenta correctamente
     */
    @Override
    public boolean cuentaExiste(String usuario) throws ControllerException {
        try {
            return habitoDAO.cuentaExiste(usuario);
        } catch (ModelException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public HistorialHabitosDTO crearHistorial(HistorialHabitosDTO historial) throws ControllerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
