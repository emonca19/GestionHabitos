package org.itson.pruebas.gestionhabitos.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.JDialog;
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

    private JDialog dialogAvisoNombre;
    private JDialog dialogAvisoUsuario;
    private JDialog dialogAvisoContraseña;
    private JDialog dialogAvisoConfirmar;

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

        setearAvisos();
    }

    public final void setearAvisos() {
        btnAvisoNombre.setVisible(false);
        btnAvisoUsuario.setVisible(false);
        btnAvisoContraseña.setVisible(false);
        btnAvisoConfirmar.setVisible(false);
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

    public boolean validarNombre() {
        String nombre = txtNombre.getText();

        if (nombre.trim().isEmpty()) {
            btnAvisoNombre.setVisible(true);
            return false;
        }

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            btnAvisoNombre.setVisible(true);
            return false;
        }

        btnAvisoNombre.setVisible(false);
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

    public boolean validarConfirmar() {
        String confirmarCont = txtContrasenaConfirmar.getText();

        if (confirmarCont.trim().isEmpty()) {
            btnAvisoConfirmar.setVisible(true);
            return false;
        }

        btnAvisoConfirmar.setVisible(false);
        return true;
    }

    public boolean validarCoincidencia() {
        if (!txtContrasena.getText().equals(txtContrasenaConfirmar.getText())) {
            btnAvisoConfirmar.setVisible(true);
            return false;
        }

        btnAvisoConfirmar.setVisible(false);
        return true;
    }

    public boolean validar() {
        if (!validarNombre()) {
            return false;
        }
        if (!validarUsuario()) {
            return false;
        }
        if (!validarContraseña()) {
            return false;
        }
        if (!validarConfirmar()) {
            return false;
        }
        if (!validarCoincidencia()) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciarSesion = new javax.swing.JButton();
        btnAvisoConfirmar = new javax.swing.JButton();
        btnAvisoUsuario = new javax.swing.JButton();
        btnRegistrarse = new javax.swing.JButton();
        btnAvisoNombre = new javax.swing.JButton();
        btnAvisoContraseña = new javax.swing.JButton();
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

        btnAvisoConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/warning.png"))); // NOI18N
        btnAvisoConfirmar.setBorder(null);
        btnAvisoConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvisoConfirmarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvisoConfirmarMouseExited(evt);
            }
        });
        btnAvisoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvisoConfirmarActionPerformed(evt);
            }
        });
        add(btnAvisoConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 30, -1));

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
        add(btnAvisoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 30, 40));

        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 660, 230, 25));

        btnAvisoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/warning.png"))); // NOI18N
        btnAvisoNombre.setBorder(null);
        btnAvisoNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvisoNombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvisoNombreMouseExited(evt);
            }
        });
        btnAvisoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvisoNombreActionPerformed(evt);
            }
        });
        add(btnAvisoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 30, 30));

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
        add(btnAvisoContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 30, 40));

        txtNombre.setBackground(new java.awt.Color(245, 245, 245));
        txtNombre.setBorder(null);
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 256, 540, 25));

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

        txtContrasenaConfirmar.setBackground(new java.awt.Color(245, 245, 245));
        txtContrasenaConfirmar.setBorder(null);
        txtContrasenaConfirmar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContrasenaConfirmarFocusLost(evt);
            }
        });
        txtContrasenaConfirmar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContrasenaConfirmarKeyReleased(evt);
            }
        });
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

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAvisoConfirmarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoConfirmarMouseEntered
        String confirmar = txtContrasenaConfirmar.getText().trim();
        if (confirmar.isEmpty()) {
            dialogAvisoConfirmar = frame.avisoNombreRegistro("El campo de confirmar contraseña está vacío.", btnAvisoConfirmar.getX(), btnAvisoConfirmar.getY());
        } else if (!txtContrasena.getText().equals(txtContrasenaConfirmar.getText())) {
            dialogAvisoConfirmar = frame.avisoNombreRegistro("Las contraseñas no coinciden.", btnAvisoConfirmar.getX(), btnAvisoConfirmar.getY());
        }
    }//GEN-LAST:event_btnAvisoConfirmarMouseEntered

    private void btnAvisoConfirmarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoConfirmarMouseExited
        if (dialogAvisoConfirmar != null) {
            dialogAvisoConfirmar.dispose();
        }
    }//GEN-LAST:event_btnAvisoConfirmarMouseExited

    private void btnAvisoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvisoConfirmarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvisoConfirmarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (validarNombre()) {
            if (dialogAvisoNombre != null) {
                dialogAvisoNombre.dispose();
                dialogAvisoNombre = null;
            }
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        if (validarNombre()) {
            if (dialogAvisoNombre != null) {
                dialogAvisoNombre.dispose();
                dialogAvisoNombre = null;
            }
        }
    }//GEN-LAST:event_txtNombreFocusLost

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

    private void btnAvisoNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoNombreMouseEntered
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            dialogAvisoNombre = frame.avisoNombreRegistro("El campo de nombre está vacío.", btnAvisoNombre.getX(), btnAvisoNombre.getY());
        } else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            dialogAvisoNombre = frame.avisoNombreRegistro("El nombre solo debe de contener letras.", btnAvisoNombre.getX(), btnAvisoNombre.getY());
        }
    }//GEN-LAST:event_btnAvisoNombreMouseEntered

    private void btnAvisoNombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvisoNombreMouseExited
        if (dialogAvisoNombre != null) {
            dialogAvisoNombre.dispose();
        }
    }//GEN-LAST:event_btnAvisoNombreMouseExited

    private void btnAvisoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvisoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvisoNombreActionPerformed

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

    private void txtContrasenaConfirmarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContrasenaConfirmarFocusLost
        if (validarConfirmar() && validarCoincidencia()) {
            if (dialogAvisoConfirmar != null) {
                dialogAvisoConfirmar.dispose();
                dialogAvisoConfirmar = null;
            }
        }
    }//GEN-LAST:event_txtContrasenaConfirmarFocusLost

    private void txtContrasenaConfirmarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaConfirmarKeyReleased
        if (validarConfirmar() && validarCoincidencia()) {
            if (dialogAvisoConfirmar != null) {
                dialogAvisoConfirmar.dispose();
                dialogAvisoConfirmar = null;
            }
        }
    }//GEN-LAST:event_txtContrasenaConfirmarKeyReleased

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
    private javax.swing.JButton btnAvisoConfirmar;
    private javax.swing.JButton btnAvisoContraseña;
    private javax.swing.JButton btnAvisoNombre;
    private javax.swing.JButton btnAvisoUsuario;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel fondo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JPasswordField txtContrasenaConfirmar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
