package org.itson.pruebas.gestionhabitos.view;

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
     *
     * @param frameContenedor
     */
    public InicioSesion(FrameContenedor frameContenedor) {
        this.frameContenedor = frameContenedor;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBlanco = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tituloSesion = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(750, 750));
        setMinimumSize(new java.awt.Dimension(750, 750));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBlanco.setBackground(new java.awt.Color(255, 255, 255, 190));
        panelBlanco.setVerifyInputWhenFocusTarget(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(69, 38, 38));
        jLabel5.setText("¿No tienes una cuenta?");

        tituloSesion.setBackground(new java.awt.Color(69, 38, 38));
        tituloSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tituloSesion.setForeground(new java.awt.Color(69, 38, 38));
        tituloSesion.setText("Iniciar Sesión");

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(69, 38, 38));
        btnRegistrar.setText("Registrate");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
        });

        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(69, 38, 38));
        jLabel4.setText("Contraseña");

        txtContraseña.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(69, 38, 38));
        jLabel3.setText("Usuario");

        txtUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botonIniciarSesion.png"))); // NOI18N
        btnIngresar.setBorder(null);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBlancoLayout = new javax.swing.GroupLayout(panelBlanco);
        panelBlanco.setLayout(panelBlancoLayout);
        panelBlancoLayout.setHorizontalGroup(
            panelBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(tituloSesion))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel4))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnIngresar))
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addComponent(btnRegistrar))
        );
        panelBlancoLayout.setVerticalGroup(
            panelBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlancoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(tituloSesion)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngresar)
                .addGap(4, 4, 4)
                .addGroup(panelBlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(btnRegistrar)))
        );

        add(panelBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 420, 350));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked

    }//GEN-LAST:event_btnIngresarMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        frameContenedor.mostrarRegistrarUsuario();
    }//GEN-LAST:event_btnRegistrarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel btnRegistrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelBlanco;
    private javax.swing.JLabel tituloSesion;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
