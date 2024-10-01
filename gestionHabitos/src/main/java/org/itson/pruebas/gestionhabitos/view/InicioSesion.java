package org.itson.pruebas.gestionhabitos.view;

import java.awt.Color;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class InicioSesion extends javax.swing.JPanel {

    private final FrameContenedor frameContenedor;
    /**
     * Creates new form InicioSesion
     */
    public InicioSesion(FrameContenedor frameContenedor) {
        this.frameContenedor = frameContenedor;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBlanco = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tituloSesion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(750, 750));
        setMinimumSize(new java.awt.Dimension(750, 750));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBlanco.setBackground(new java.awt.Color(255, 255, 255));
        panelBlanco.setVerifyInputWhenFocusTarget(false);
        panelBlanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(69, 38, 38));
        jLabel5.setText("¿No tienes una cuenta?");
        panelBlanco.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        tituloSesion.setBackground(new java.awt.Color(69, 38, 38));
        tituloSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tituloSesion.setForeground(new java.awt.Color(69, 38, 38));
        tituloSesion.setText("Iniciar Sesión");
        panelBlanco.add(tituloSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(69, 38, 38));
        jLabel6.setText("Registrate");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        panelBlanco.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        panelBlanco.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 296, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(69, 38, 38));
        jLabel4.setText("Contraseña");
        panelBlanco.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 161, -1, -1));
        panelBlanco.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 187, 322, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(69, 38, 38));
        jLabel3.setText("Usuario");
        panelBlanco.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 95, -1, -1));
        panelBlanco.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 121, 322, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonIniciarSesion.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        panelBlanco.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        add(panelBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 420, 390));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        frameContenedor.mostrarRegistrarUsuario();
    }//GEN-LAST:event_jLabel6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel panelBlanco;
    private javax.swing.JLabel tituloSesion;
    // End of variables declaration//GEN-END:variables
}
