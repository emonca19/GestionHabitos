/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.pruebas.gestionhabitos.negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.itson.pruebas.gestionhabitos.model.Cuenta;
import org.itson.pruebas.gestionhabitos.model.GestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.Habito;
import org.itson.pruebas.gestionhabitos.model.IGestionarHabitosDAO;
import org.itson.pruebas.gestionhabitos.model.ModelException;

/**
 *
 * @author crist
 */
public class GestionarHabitosNegocio implements IGestionarHabitosNegocio{

     private GestionarHabitosDAO habitoDAO;
     private CuentaDAO cuentaDAO;

    public GestionarHabitosNegocio(GestionarHabitosDAO habitoDAO){
        this.habitoDAO = habitoDAO;
    }

     @Override
    public HabitoDTO crearHabito(HabitoDTO habitoDTO) {
        // Obtener la cuenta asociada por el nombre de usuario
        Cuenta cuenta = cuentaDAO.obtenerCuentaPorUsuario(habitoDTO.getUsuarioCuenta());
        if (cuenta == null) {
            throw new RuntimeException("La cuenta no existe");
        }

        // Convertir el DTO en entidad
        Habito habito = convertirAEntidad(habitoDTO);
        habito.setCuenta(cuenta); // Asignar la cuenta
         try {
             // Guardar el hábito
             habito = habitoDAO.crearHabito(habito);
         } catch (ModelException ex) {
             Logger.getLogger(GestionarHabitosNegocio.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        // Devolver el DTO actualizado
        return convertirADTO(habito);
    }

     @Override
    public List<HabitoDTO> obtenerHabitos() {
        List<Habito> habitos = null;
         try {
             habitos = (List<Habito>) habitoDAO.verHabitos();
         } catch (ModelException ex) {
             Logger.getLogger(GestionarHabitosNegocio.class.getName()).log(Level.SEVERE, null, ex);
         }
        return habitos.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

/*
     @Override
    public HabitoDTO actualizarHabito(Long id, HabitoDTO habitoDTO) {
        Habito habito = habitoDAO.obtenerPorId(id);
        if (habito == null) {
            throw new RuntimeException("Hábito no encontrado");
        }

        // Actualizar los campos del hábito
        habito.setFrecuencia(habitoDTO.getFrecuencia());
        habito.setRealizado(habitoDTO.isRealizado());
        habito.setFecha(habitoDTO.getFecha());
        habito.setNombre(habitoDTO.getNombre());

         try {
             habito = habitoDAO.actualizarHabito(habito);
         } catch (ModelException ex) {
             Logger.getLogger(GestionarHabitosNegocio.class.getName()).log(Level.SEVERE, null, ex);
         }
        return convertirADTO(habito);
    }
*/
     @Override
    public void eliminarHabito(Habito habito) {
         try {
             habitoDAO.eliminarHabito(habito);
         } catch (ModelException ex) {
             Logger.getLogger(GestionarHabitosNegocio.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    private HabitoDTO convertirADTO(Habito habito) {
        return new HabitoDTO(
            habito.getId(),
            habito.getFrecuencia(),
            habito.isRealizado(),
            habito.getFecha(),
            habito.getNombre(),
            habito.getCuenta().getUsuario() 
        );
    }

    private Habito convertirAEntidad(HabitoDTO habitoDTO) {
        Habito habito = new Habito();
        habito.setId(habitoDTO.getId());
        habito.setFrecuencia(habitoDTO.getFrecuencia());
        habito.setRealizado(habitoDTO.isRealizado());
        habito.setFecha(habitoDTO.getFecha());
        habito.setNombre(habitoDTO.getNombre());
        return habito;
    }

}
