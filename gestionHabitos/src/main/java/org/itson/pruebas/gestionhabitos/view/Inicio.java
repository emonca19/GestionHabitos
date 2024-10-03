package org.itson.pruebas.gestionhabitos.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import org.itson.pruebas.gestionhabitos.controller.Sesion;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class Inicio extends javax.swing.JPanel {

    private final FrameContenedor frame;
    private JPanel pnlHabitosPendientes;
    private JPanel pnlHabitosRealizados;

    /**
     * Creates new form Inicio
     *
     * @param frame
     */
    public Inicio(FrameContenedor frame) {
        this.frame = frame;
        initComponents();
        try {
            setFonts();
            mostrarHabitos();
        } catch (FontFormatException | IOException e) {
            frame.mostrarAviso(e.getMessage(), "Aviso");
        }
        setearDatos();
    }

    public void agregarHabito() {
        frame.mostrarCrearHabito();
    }

    public final void setearDatos() {
        lblNombreUsuario.setText(Sesion.getCuenta().getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMes = new javax.swing.JLabel();
        btnIzquierda = new javax.swing.JButton();
        btnDerecha = new javax.swing.JButton();
        btnDia1 = new javax.swing.JButton();
        btnDia2 = new javax.swing.JButton();
        btnDia3 = new javax.swing.JButton();
        btnDia4 = new javax.swing.JButton();
        btnDia5 = new javax.swing.JButton();
        btnDia6 = new javax.swing.JButton();
        btnDia7 = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        pnlContenedorHabitosPendientes = new javax.swing.JPanel();
        pnlContenedorHabitosRealizados = new javax.swing.JPanel();
        lblHabitosPendientes = new javax.swing.JLabel();
        lblHabitosRealizados = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        btnHoy = new javax.swing.JButton();
        btnHabitos = new javax.swing.JButton();
        btnProgreso = new javax.swing.JButton();
        lblNombreUsuario = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(750, 750));
        setMinimumSize(new java.awt.Dimension(750, 750));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMes.setForeground(new java.awt.Color(255, 255, 255));
        lblMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMes.setText("mes");
        add(lblMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 69, 350, 30));

        btnIzquierda.setForeground(new java.awt.Color(245, 245, 245));
        btnIzquierda.setBorderPainted(false);
        btnIzquierda.setContentAreaFilled(false);
        btnIzquierda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIzquierda.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzquierdaActionPerformed(evt);
            }
        });
        add(btnIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 105, 16, 27));

        btnDerecha.setForeground(new java.awt.Color(245, 245, 245));
        btnDerecha.setBorderPainted(false);
        btnDerecha.setContentAreaFilled(false);
        btnDerecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDerecha.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });
        add(btnDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(713, 105, 16, 27));

        btnDia1.setForeground(new java.awt.Color(245, 245, 245));
        btnDia1.setText("<html>D<br>N</html>");
        btnDia1.setBorderPainted(false);
        btnDia1.setContentAreaFilled(false);
        btnDia1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia1ActionPerformed(evt);
            }
        });
        add(btnDia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 106, 60, 60));

        btnDia2.setForeground(new java.awt.Color(245, 245, 245));
        btnDia2.setText("<html>D<br>N</html>");
        btnDia2.setBorderPainted(false);
        btnDia2.setContentAreaFilled(false);
        btnDia2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia2ActionPerformed(evt);
            }
        });
        add(btnDia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 106, 60, 60));

        btnDia3.setForeground(new java.awt.Color(245, 245, 245));
        btnDia3.setText("<html>D<br>N</html>");
        btnDia3.setBorderPainted(false);
        btnDia3.setContentAreaFilled(false);
        btnDia3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia3ActionPerformed(evt);
            }
        });
        add(btnDia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 106, 60, 60));

        btnDia4.setForeground(new java.awt.Color(245, 245, 245));
        btnDia4.setText("<html>D<br>N</html>");
        btnDia4.setBorderPainted(false);
        btnDia4.setContentAreaFilled(false);
        btnDia4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia4ActionPerformed(evt);
            }
        });
        add(btnDia4, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 106, 60, 60));

        btnDia5.setForeground(new java.awt.Color(245, 245, 245));
        btnDia5.setText("<html>D<br>N</html>");
        btnDia5.setBorderPainted(false);
        btnDia5.setContentAreaFilled(false);
        btnDia5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia5ActionPerformed(evt);
            }
        });
        add(btnDia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 106, 60, 60));

        btnDia6.setForeground(new java.awt.Color(245, 245, 245));
        btnDia6.setText("<html>D<br>N</html>");
        btnDia6.setBorderPainted(false);
        btnDia6.setContentAreaFilled(false);
        btnDia6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia6ActionPerformed(evt);
            }
        });
        add(btnDia6, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 106, 60, 60));

        btnDia7.setForeground(new java.awt.Color(245, 245, 245));
        btnDia7.setText("<html>D<br>N</html>");
        btnDia7.setBorderPainted(false);
        btnDia7.setContentAreaFilled(false);
        btnDia7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDia7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDia7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDia7ActionPerformed(evt);
            }
        });
        add(btnDia7, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 106, 60, 60));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregarHabitos.png"))); // NOI18N
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 570, 60, 60));

        pnlContenedorHabitosPendientes.setOpaque(false);
        add(pnlContenedorHabitosPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 233, 700, 170));

        pnlContenedorHabitosRealizados.setOpaque(false);
        add(pnlContenedorHabitosRealizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 460, 700, 170));

        lblHabitosPendientes.setText("HÁBITOS PENDIENTES");
        add(lblHabitosPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 198, -1, -1));

        lblHabitosRealizados.setText("HÁBITOS REALIZADOS");
        add(lblHabitosRealizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 425, -1, -1));

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("fecha");
        add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 193, 250, 25));

        lblDivision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/division.png"))); // NOI18N
        add(lblDivision, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 415, -1, -1));

        btnHoy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hoy.png"))); // NOI18N
        btnHoy.setBorderPainted(false);
        btnHoy.setContentAreaFilled(false);
        btnHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoyActionPerformed(evt);
            }
        });
        add(btnHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 654, 80, 81));

        btnHabitos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/habitos.png"))); // NOI18N
        btnHabitos.setBorderPainted(false);
        btnHabitos.setContentAreaFilled(false);
        btnHabitos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHabitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabitosActionPerformed(evt);
            }
        });
        add(btnHabitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 654, 80, 81));

        btnProgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/progreso.png"))); // NOI18N
        btnProgreso.setBorderPainted(false);
        btnProgreso.setContentAreaFilled(false);
        btnProgreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgresoActionPerformed(evt);
            }
        });
        add(btnProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 654, 80, 81));

        lblNombreUsuario.setForeground(new java.awt.Color(37, 52, 18));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(lblNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 18, 300, 27));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoyActionPerformed

    private void btnProgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgresoActionPerformed
        frame.mostrarProgresoSemanal();
    }//GEN-LAST:event_btnProgresoActionPerformed

    private void btnHabitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabitosActionPerformed
        frame.mostrarListaHabitos();
    }//GEN-LAST:event_btnHabitosActionPerformed

    private void btnDia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia1ActionPerformed

    private void btnDia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia2ActionPerformed

    private void btnDia3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia3ActionPerformed

    private void btnDia4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia4ActionPerformed

    private void btnDia5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia5ActionPerformed

    private void btnDia6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia6ActionPerformed

    private void btnDia7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDia7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDia7ActionPerformed

    private void btnIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzquierdaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIzquierdaActionPerformed

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarHabito();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void mostrarHabitos() throws FontFormatException, IOException {
        listarHabitosPendientes();
        listarHabitosRealizados();
    }

    private void listarHabitosPendientes() throws FontFormatException, IOException {
        pnlHabitosPendientes = new JPanel();
        pnlHabitosPendientes.setLayout(new BoxLayout(pnlHabitosPendientes, BoxLayout.Y_AXIS));  // Establecer el BoxLayout correctamente
        pnlHabitosPendientes.setName("pnlHabitosRealizados");
        pnlHabitosPendientes.setOpaque(false);

        JScrollPane scpHabitosPendientes = new JScrollPane(pnlHabitosPendientes);
        scpHabitosPendientes.setOpaque(false);
        scpHabitosPendientes.getViewport().setOpaque(false);
        scpHabitosPendientes.setBorder(null);
        scpHabitosPendientes.getVerticalScrollBar().setUnitIncrement(16);
        scpHabitosPendientes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Aquí puedes ajustar solo el tamaño del JScrollPane
        scpHabitosPendientes.setPreferredSize(new Dimension(700, 170));

        addHabit("Leer", false);
        addHabit("Meditar", false);

        pnlContenedorHabitosPendientes.add(scpHabitosPendientes);

//        // Añadir paneles al contenedor principal
//        pnlContenedorHabitosPendientes.add(jPanel);
//        pnlContenedorHabitosPendientes.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre paneles
//        pnlContenedorHabitosPendientes.add(jPanel2);
    }

    private void listarHabitosRealizados() throws FontFormatException, IOException {
        pnlHabitosRealizados = new JPanel();
        pnlHabitosRealizados.setLayout(new BoxLayout(pnlHabitosRealizados, BoxLayout.Y_AXIS));  // Establecer el BoxLayout correctamente
        pnlHabitosRealizados.setName("pnlHabitosRealizados");
        pnlHabitosRealizados.setOpaque(false);

        JScrollPane scpHabitosRealizados = new JScrollPane(pnlHabitosRealizados);
        scpHabitosRealizados.setOpaque(false);
        scpHabitosRealizados.getViewport().setOpaque(false);
        scpHabitosRealizados.setBorder(null);
        scpHabitosRealizados.getVerticalScrollBar().setUnitIncrement(16);
        scpHabitosRealizados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Aquí puedes ajustar solo el tamaño del JScrollPane
        scpHabitosRealizados.setPreferredSize(new Dimension(700, 170));

        // No fijar el tamaño del panel de hábitos realizados para permitir que crezca
        addHabit("Ejercicio", true);
        addHabit("Dormir", true);
        addHabit("Gym", true);
        addHabit("Pasear al perro", true);
        addHabit("Tarea", true);
        addHabit("Bitcoin", true);

        pnlContenedorHabitosRealizados.add(scpHabitosRealizados);
    }

    private void addHabit(String habitName, Boolean isCompleted) throws FontFormatException, IOException {
        HabitPanel habit = new HabitPanel(habitName, isCompleted);

        // Añadir el evento de clic para mover entre listas
        habit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Determinar si está en la lista de pendientes o realizados
                if (!habit.isCompleted()) {
                    pnlHabitosPendientes.remove(habit);  // Eliminar de pendientes
                    pnlHabitosRealizados.add(habit);     // Mover a realizados
                    habit.setCompleted(true);            // Actualizar el estado a "completado"

                } else {
                    pnlHabitosRealizados.remove(habit);  // Eliminar de realizados
                    pnlHabitosPendientes.add(habit);     // Mover a pendientes
                    habit.setCompleted(false);            // Actualizar el estado a "pendiente"
                }

                // Refrescar las listas
                pnlHabitosPendientes.revalidate();
                pnlHabitosPendientes.repaint();
                pnlHabitosRealizados.revalidate();
                pnlHabitosRealizados.repaint();
            }
        });

        if (isCompleted == false) {
            pnlHabitosPendientes.add(habit);
            pnlHabitosPendientes.revalidate();
            pnlHabitosPendientes.repaint();

        } else {
            pnlHabitosRealizados.add(habit);
            pnlHabitosRealizados.revalidate();
            pnlHabitosRealizados.repaint();

        }
        // Añadir el panel del hábito a la lista correspondiente
    }

// Panel personalizado para cada hábito
    private class HabitPanel extends JPanel {

        private Boolean isCompleted;
        private JLabel imagePanel;

        public HabitPanel(String habitName, Boolean isCompleted) throws FontFormatException, IOException {
            this.isCompleted = isCompleted;

            setLayout(new FlowLayout(FlowLayout.LEFT));
            setPreferredSize(new Dimension(0, 30));  // Altura fija de 30, ancho ajustable
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));  // Establece la altura máxima

            JLabel nameLabel = new JLabel(habitName);
            nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
            nameLabel.setFont(frame.cargarFuente("/fonts/Nunito/static/Nunito-Regular.ttf", 18F));

            // Imagen opcional (ejemplo con un cuadrado de color)
            imagePanel = new JLabel(new ImageIcon(getClass().getResource(isCompleted
                    ? "/img/checkColoreado.png"
                    : "/img/checkVacio.png")));

            setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));  // Espacio de 8px en la parte inferior

            add(imagePanel);
            add(nameLabel);
        }

        public Boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(Boolean completed) {
            this.isCompleted = completed;
            // Cambiar el ícono de la imagen dependiendo del estado
            imagePanel.setIcon(new ImageIcon(getClass().getResource(completed
                    ? "/img/checkColoreado.png"
                    : "/img/checkVacio.png")));
        }
    }

    private void setFonts() throws FontFormatException, IOException {
        lblNombreUsuario.setFont(frame.cargarFuente("/fonts/Nunito/static/Nunito-Medium.ttf", 20F));
        lblMes.setFont(frame.cargarFuente("/fonts/Kurale/Kurale-Regular.ttf", 20F));
        Font semiBoldFont = frame.cargarFuente("/fonts/Nunito/static/Nunito-SemiBold.ttf", 22F);
        Font nunitoRegular = frame.cargarFuente("/fonts/Nunito/static/Nunito-Regular.ttf", 18F);
        lblHabitosPendientes.setFont(semiBoldFont);
        lblHabitosRealizados.setFont(semiBoldFont);
        lblFecha.setFont(nunitoRegular);
        btnDia1.setFont(nunitoRegular);
        btnDia2.setFont(nunitoRegular);
        btnDia3.setFont(nunitoRegular);
        btnDia4.setFont(nunitoRegular);
        btnDia5.setFont(nunitoRegular);
        btnDia6.setFont(nunitoRegular);
        btnDia7.setFont(nunitoRegular);

    }

    // Borde y tamaño
//            setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            setMaximumSize(new Dimension(300, 60));
    // Primer JPanel
//            JPanel jPanel = new JPanel();
//            jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//            jPanel.setPreferredSize(new Dimension(0, 30)); // Altura fija de 30, ancho ajustable
//            jPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Establece la altura máxima
//            JLabel lblImage1 = new JLabel(new ImageIcon(getClass().getResource("/img/checkVacio.png")));
//            jPanel.add(lblImage1);
//            jPanel.add(new JLabel("bañarme"));
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnDia1;
    private javax.swing.JButton btnDia2;
    private javax.swing.JButton btnDia3;
    private javax.swing.JButton btnDia4;
    private javax.swing.JButton btnDia5;
    private javax.swing.JButton btnDia6;
    private javax.swing.JButton btnDia7;
    private javax.swing.JButton btnHabitos;
    private javax.swing.JButton btnHoy;
    private javax.swing.JButton btnIzquierda;
    private javax.swing.JButton btnProgreso;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHabitosPendientes;
    private javax.swing.JLabel lblHabitosRealizados;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JPanel pnlContenedorHabitosPendientes;
    private javax.swing.JPanel pnlContenedorHabitosRealizados;
    // End of variables declaration//GEN-END:variables
}
