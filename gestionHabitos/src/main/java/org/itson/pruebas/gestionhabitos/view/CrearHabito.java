package org.itson.pruebas.gestionhabitos.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author rover
 */
public class CrearHabito extends javax.swing.JPanel {

    private FrameContenedor frame;
    private ImageIcon[] uncheckedIcons;
    private ImageIcon[] checkedIcons;

    /**
     * Creates new form CrearHabito
     */
    public CrearHabito(FrameContenedor frame) {
        this.frame = frame;
        initComponents();
        cargarIconos();
        asignarIconosIniciales();
        setFonts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHabito = new javax.swing.JTextField();
        cbxLunes = new javax.swing.JCheckBox();
        cbxMartes = new javax.swing.JCheckBox();
        cbxMiercoles = new javax.swing.JCheckBox();
        cbxJueves = new javax.swing.JCheckBox();
        cbxViernes = new javax.swing.JCheckBox();
        cbxSabado = new javax.swing.JCheckBox();
        cbxDomingo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(375, 250));
        setPreferredSize(new java.awt.Dimension(375, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHabito.setBackground(new java.awt.Color(245, 245, 245));
        txtHabito.setForeground(new java.awt.Color(0, 0, 0));
        txtHabito.setBorder(null);
        add(txtHabito, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 57, 310, 25));

        cbxLunes.setToolTipText("");
        cbxLunes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxLunes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLunesItemStateChanged(evt);
            }
        });
        add(cbxLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 100, -1, -1));

        cbxMartes.setToolTipText("");
        cbxMartes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxMartes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxMartes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMartesItemStateChanged(evt);
            }
        });
        add(cbxMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 100, -1, -1));

        cbxMiercoles.setToolTipText("");
        cbxMiercoles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxMiercoles.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxMiercoles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMiercolesItemStateChanged(evt);
            }
        });
        add(cbxMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 100, -1, -1));

        cbxJueves.setToolTipText("");
        cbxJueves.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxJueves.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxJueves.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxJuevesItemStateChanged(evt);
            }
        });
        add(cbxJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 100, -1, -1));

        cbxViernes.setToolTipText("");
        cbxViernes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxViernes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxViernes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxViernesItemStateChanged(evt);
            }
        });
        add(cbxViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 100, -1, -1));

        cbxSabado.setToolTipText("");
        cbxSabado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSabado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxSabado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSabadoItemStateChanged(evt);
            }
        });
        add(cbxSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        cbxDomingo.setToolTipText("");
        cbxDomingo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxDomingo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbxDomingo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDomingoItemStateChanged(evt);
            }
        });
        add(cbxDomingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 100, -1, -1));

        btnAceptar.setBorderPainted(false);
        btnAceptar.setContentAreaFilled(false);
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 148, 80, 77));

        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 148, 80, 77));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/crearHabito.png"))); // NOI18N
        add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cbxLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLunesItemStateChanged
        updateCheckboxIcon(cbxLunes, 0);

    }//GEN-LAST:event_cbxLunesItemStateChanged

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbxMartesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMartesItemStateChanged
        updateCheckboxIcon(cbxMartes, 1);
    }//GEN-LAST:event_cbxMartesItemStateChanged

    private void cbxMiercolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMiercolesItemStateChanged
        updateCheckboxIcon(cbxMiercoles, 2);
    }//GEN-LAST:event_cbxMiercolesItemStateChanged

    private void cbxJuevesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxJuevesItemStateChanged
        updateCheckboxIcon(cbxJueves, 3);
    }//GEN-LAST:event_cbxJuevesItemStateChanged

    private void cbxViernesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxViernesItemStateChanged
        updateCheckboxIcon(cbxViernes, 4);
    }//GEN-LAST:event_cbxViernesItemStateChanged

    private void cbxSabadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSabadoItemStateChanged
        updateCheckboxIcon(cbxSabado, 5);
    }//GEN-LAST:event_cbxSabadoItemStateChanged

    private void cbxDomingoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDomingoItemStateChanged
        updateCheckboxIcon(cbxDomingo, 6);
    }//GEN-LAST:event_cbxDomingoItemStateChanged

    private void cargarIconos() {
        uncheckedIcons = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/img/unselectedDays/lunes.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/martes.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/miercoles.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/jueves.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/viernes.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/sabado.png")),
            new ImageIcon(getClass().getResource("/img/unselectedDays/domingo.png"))
        };

        checkedIcons = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/img/selectedDays/lunes.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/martes.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/miercoles.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/jueves.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/viernes.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/sabado.png")),
            new ImageIcon(getClass().getResource("/img/selectedDays/domingo.png"))
        };
    }

    private void asignarIconosIniciales() {
        cbxLunes.setIcon(uncheckedIcons[0]);
        cbxMartes.setIcon(uncheckedIcons[1]);
        cbxMiercoles.setIcon(uncheckedIcons[2]);
        cbxJueves.setIcon(uncheckedIcons[3]);
        cbxViernes.setIcon(uncheckedIcons[4]);
        cbxSabado.setIcon(uncheckedIcons[5]);
        cbxDomingo.setIcon(uncheckedIcons[6]);
    }

    private void updateCheckboxIcon(javax.swing.JCheckBox checkbox, int dayIndex) {
        if (checkbox.isSelected()) {
            checkbox.setIcon(checkedIcons[dayIndex]);
        } else {
            checkbox.setIcon(uncheckedIcons[dayIndex]);
        }
    }

    private void setFonts() {
        try {
            txtHabito.setFont(frame.cargarFuente("/fonts/Nunito/static/Nunito-SemiBold.ttf", 18F));
        } catch (FontFormatException | IOException ex) {
            frame.mostrarAviso(ex.getMessage(), "Aviso");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox cbxDomingo;
    private javax.swing.JCheckBox cbxJueves;
    private javax.swing.JCheckBox cbxLunes;
    private javax.swing.JCheckBox cbxMartes;
    private javax.swing.JCheckBox cbxMiercoles;
    private javax.swing.JCheckBox cbxSabado;
    private javax.swing.JCheckBox cbxViernes;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField txtHabito;
    // End of variables declaration//GEN-END:variables
}
