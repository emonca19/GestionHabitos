package org.itson.pruebas.gestionhabitos.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.Sesion;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class InicioSesion extends javax.swing.JPanel {

    private final FrameContenedor frame;

    /**
     * Creates new form InicioSesion
     *
     * @param frame
     */
    public InicioSesion(FrameContenedor frame) {
        this.frame = frame;
        initComponents();
        generarConexion();
        setFonts();
    }

    public CuentaDTO consultarCuenta() {
        try {
            return new GestionarHabitosNegocio().consultarCuenta(txtUsuario.getText(), String.copyValueOf(txtContrasena.getPassword()));
        } catch (ControllerException ex) {
            frame.mostrarAviso("El usuario no existe.", "Error de consulta");
        }
        return null;
    }

    public void limpiar() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }

    public boolean validar() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.trim().isEmpty()) {
            frame.mostrarAviso("El campo de usuario está vacío.", "Error de validación");
            return false;
        }

        if (contrasena.trim().isEmpty()) {
            frame.mostrarAviso("El campo de contraseña está vacío.", "Error de validación");
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrarse = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        fondo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(750, 750));
        setMinimumSize(new java.awt.Dimension(750, 750));
        setPreferredSize(new java.awt.Dimension(750, 750));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setContentAreaFilled(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 535, 220, 50));

        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 605, 283, 25));

        txtUsuario.setBackground(new java.awt.Color(245, 245, 245));
        txtUsuario.setBorder(null);
        add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 349, 540, 25));

        txtContrasena.setBackground(new java.awt.Color(245, 245, 245));
        txtContrasena.setBorder(null);
        add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 443, 540, 25));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inicioSesion.png"))); // NOI18N
        add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        frame.mostrarRegistrarUsuario();
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        if (!validar()) {
            return;
        }

        CuentaDTO cuenta = consultarCuenta();

        if (cuenta != null) {
            Sesion.iniciarSesion(cuenta);
            frame.mostrarInicio();
        }

        limpiar();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void generarConexion(){
        
    }
    
    private void setFonts() {
        try {
            Font nunitoB = frame.cargarFuente("/fonts/Nunito/static/Nunito-SemiBold.ttf", 18F);
            txtUsuario.setFont(nunitoB);
            txtContrasena.setFont(nunitoB);
        } catch (FontFormatException | IOException ex) {
            frame.mostrarAviso(ex.getMessage(), "Aviso");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
