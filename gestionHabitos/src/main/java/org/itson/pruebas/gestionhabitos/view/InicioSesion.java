package org.itson.pruebas.gestionhabitos.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import org.itson.pruebas.gestionhabitos.controller.ControllerException;
import org.itson.pruebas.gestionhabitos.controller.CuentaDTO;
import org.itson.pruebas.gestionhabitos.controller.FechaUtil;
import org.itson.pruebas.gestionhabitos.controller.GestionarHabitosNegocio;
import org.itson.pruebas.gestionhabitos.controller.HistorialHabitosDTO;
import org.itson.pruebas.gestionhabitos.controller.Sesion;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class InicioSesion extends javax.swing.JPanel {

    private JDialog dialogAvisoUsuario;
    private JDialog dialogAvisoContraseña;
    private final FrameContenedor frame;

    /**
     * Creates new form InicioSesion
     *
     * @param frame
     */
    public InicioSesion(FrameContenedor frame) {
        this.frame = frame;
        initComponents();
        setFonts();
        setearAvisos();
    }

    public final void setearAvisos() {
        btnAvisoUsuario.setVisible(false);
        btnAvisoContraseña.setVisible(false);
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
        if (!validarUsuario()) {
            return false;
        }
        if (!validarContraseña()) {
            return false;
        }
        return true;
    }

    public boolean validarUsuario() {
        String usuario = txtUsuario.getText();

        if (usuario.trim().isEmpty()) {
            btnAvisoUsuario.setVisible(true);
            return false;
        }

        if (usuario.length() > 10) {
            btnAvisoUsuario.setVisible(true);
            return false;
        }

        btnAvisoUsuario.setVisible(false);
        return true;
    }

    public boolean validarContraseña() {
        String contrasena = txtContrasena.getText();

        if (contrasena.trim().isEmpty()) {
            btnAvisoContraseña.setVisible(true);
            return false;
        }

        btnAvisoContraseña.setVisible(false);
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciarSesion = new javax.swing.JButton();
        btnRegistrarse = new javax.swing.JButton();
        btnAvisoUsuario = new javax.swing.JButton();
        btnAvisoContraseña = new javax.swing.JButton();
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

        btnAvisoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/warning.png"))); // NOI18N
        btnAvisoUsuario.setBorder(null);
        btnAvisoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvisoUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvisoUsuarioMouseExited(evt);
            }
        });
        btnAvisoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvisoUsuarioActionPerformed(evt);
            }
        });
        add(btnAvisoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 30, 40));

        btnAvisoContraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/warning.png"))); // NOI18N
        btnAvisoContraseña.setBorder(null);
        btnAvisoContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvisoContraseñaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvisoContraseñaMouseExited(evt);
            }
        });
        btnAvisoContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvisoContraseñaActionPerformed(evt);
            }
        });
        add(btnAvisoContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 30, 30));

        txtUsuario.setBackground(new java.awt.Color(245, 245, 245));
        txtUsuario.setBorder(null);
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 349, 540, 25));

        txtContrasena.setBackground(new java.awt.Color(245, 245, 245));
        txtContrasena.setBorder(null);
        txtContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContrasenaFocusLost(evt);
            }
        });
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyReleased(evt);
            }
        });
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
            frame.obtenerHabitosHoy();
            FechaUtil.establecerFechaHoy();
            FechaUtil.setFechaActual(LocalDate.now());
            frame.mostrarInicio();
        }

        limpiar();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if (validarUsuario()) {
            if (dialogAvisoUsuario != null) {
                dialogAvisoUsuario.dispose();
                dialogAvisoUsuario = null;
            }
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        if (validarUsuario()) {
            if (dialogAvisoUsuario != null) {
                dialogAvisoUsuario.dispose();
                dialogAvisoUsuario = null;
            }
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnAvisoUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoUsuarioMouseEntered
        String usuario = txtUsuario.getText();
        if (usuario.trim().isEmpty()) {
            dialogAvisoUsuario = frame.avisoNombreRegistro("El campo de usuario está vacío.", btnAvisoUsuario.getX(), btnAvisoUsuario.getY());
        } else if (usuario.length() > 10) {
            dialogAvisoUsuario = frame.avisoNombreRegistro("El usuario no puede tener más de 10 caracteres.", btnAvisoUsuario.getX(), btnAvisoUsuario.getY());
        }
    }//GEN-LAST:event_btnAvisoUsuarioMouseEntered

    private void btnAvisoUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoUsuarioMouseExited
        if (dialogAvisoUsuario != null) {
            dialogAvisoUsuario.dispose();
        }
    }//GEN-LAST:event_btnAvisoUsuarioMouseExited

    private void btnAvisoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvisoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvisoUsuarioActionPerformed

    private void btnAvisoContraseñaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoContraseñaMouseEntered
        String contrasena = txtContrasena.getText();
        if (contrasena.trim().isEmpty()) {
            dialogAvisoContraseña = frame.avisoNombreRegistro("El campo de contraseña está vacío.", btnAvisoContraseña.getX(), btnAvisoContraseña.getY());
        }
    }//GEN-LAST:event_btnAvisoContraseñaMouseEntered

    private void btnAvisoContraseñaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoContraseñaMouseExited
        if (dialogAvisoContraseña != null) {
            dialogAvisoContraseña.dispose();
        }
    }//GEN-LAST:event_btnAvisoContraseñaMouseExited

    private void btnAvisoContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvisoContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvisoContraseñaActionPerformed

    private void txtContrasenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaFocusLost
        if (validarContraseña()) {
            if (dialogAvisoContraseña != null) {
                dialogAvisoContraseña.dispose();
                dialogAvisoContraseña = null;
            }
        }
    }//GEN-LAST:event_txtContrasenaFocusLost

    private void txtContrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyReleased
        if (validarContraseña()) {
            if (dialogAvisoContraseña != null) {
                dialogAvisoContraseña.dispose();
                dialogAvisoContraseña = null;
            }
        }
    }//GEN-LAST:event_txtContrasenaKeyReleased

    
    
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
    private javax.swing.JButton btnAvisoContraseña;
    private javax.swing.JButton btnAvisoUsuario;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
