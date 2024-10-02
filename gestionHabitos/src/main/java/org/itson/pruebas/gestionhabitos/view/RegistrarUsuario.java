package org.itson.pruebas.gestionhabitos.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class RegistrarUsuario extends javax.swing.JPanel {

    private final FrameContenedor frame;

    /**
     * Creates new form InicioSesion
     *
     * @param frame
     */
    public RegistrarUsuario(FrameContenedor frame) {
        this.frame = frame;
        initComponents();
        setFonts();
    }

    public void registrar() {
        CuentaDTO cuenta = new CuentaDTO(txtNombre.getText(), txtUsuario.getText(), txtContrasena.getText());
        try {
            new GestionarHabitosNegocio().crearCuenta(cuenta);
            frame.mostrarInformacion("El usuario se ha registrado con éxito.", "Éxito");
        } catch (ControllerException ex) {
            frame.mostrarAviso("La cuenta ya existe.", "Error de validación");
        }
    }

    public boolean cuentaExiste() {
        try {
            boolean existe = new GestionarHabitosNegocio().cuentaExiste(txtUsuario.getText());
            if (existe) {
                frame.mostrarAviso("La cuenta ya existe con ese usuario.", "Error de validación");
            }
            return existe;
        } catch (ControllerException ex) {
            frame.mostrarAviso("Error al verificar la existencia de la cuenta: " + ex.getMessage(), "Error de consulta");
            return false;
        }
    }

    public void limpiar() {
        txtNombre.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtContrasenaConfirmar.setText("");
    }

    public boolean validar() {
        String nombre = txtNombre.getText();
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String confirmarCont = txtContrasenaConfirmar.getText();

        if (nombre.trim().isEmpty()) {
            frame.mostrarAviso("El campo de nombre está vacío.", "Error de validación");
            return false;
        }

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            frame.mostrarAviso("El nombre solo puede contener letras y espacios.", "Error de validación");
            return false;
        }

        if (usuario.trim().isEmpty()) {
            frame.mostrarAviso("El campo de usuario está vacío.", "Error de validación");
            return false;
        }

        if (usuario.length() > 10) {
            frame.mostrarAviso("El usuario no puede tener más de 10 caracteres.", "Error de validación");
            return false;
        }

        if (contrasena.trim().isEmpty()) {
            frame.mostrarAviso("El campo de contraseña está vacío.", "Error de validación");
            return false;
        }

        if (confirmarCont.trim().isEmpty()) {
            frame.mostrarAviso("El campo de confirmar contraseña está vacío.", "Error de validación");
            return false;
        }

        if (!contrasena.equals(confirmarCont)) {
            frame.mostrarAviso("Las contraseñas no coinciden.", "Error de validación");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrarse = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        txtContrasenaConfirmar = new javax.swing.JPasswordField();
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
        add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 595, 220, 50));

        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 660, 230, 25));

        txtNombre.setBackground(new java.awt.Color(245, 245, 245));
        txtNombre.setBorder(null);
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 256, 540, 25));

        txtUsuario.setBackground(new java.awt.Color(245, 245, 245));
        txtUsuario.setBorder(null);
        add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 349, 540, 25));

        txtContrasena.setBackground(new java.awt.Color(245, 245, 245));
        txtContrasena.setBorder(null);
        add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 443, 540, 25));

        txtContrasenaConfirmar.setBackground(new java.awt.Color(245, 245, 245));
        txtContrasenaConfirmar.setBorder(null);
        add(txtContrasenaConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 535, 540, 25));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        if (!validar()) {
            return;
        }
        if (cuentaExiste()) {
            return;
        }
        registrar();
        frame.mostrarInicioSesion();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        frame.mostrarInicioSesion();
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void setFonts() {
        try {
            Font nunitoB = frame.cargarFuente("/fonts/Nunito/static/Nunito-SemiBold.ttf", 18F);
            txtNombre.setFont(nunitoB);
            txtUsuario.setFont(nunitoB);
            txtContrasena.setFont(nunitoB);
            txtContrasenaConfirmar.setFont(nunitoB);
        } catch (FontFormatException | IOException ex) {
            frame.mostrarAviso(ex.getMessage(), "Aviso");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JPasswordField txtContrasenaConfirmar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
