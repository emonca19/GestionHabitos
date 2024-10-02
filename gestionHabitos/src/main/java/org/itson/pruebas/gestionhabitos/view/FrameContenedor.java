package org.itson.pruebas.gestionhabitos.view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Eliana Monge
 * @author Cristina Castro
 * @author Eduardo Talavera
 * @author Roberto García
 */
public class FrameContenedor extends javax.swing.JFrame {

    private JPanel panelActual;
    private static final Logger logger = Logger.getLogger(FrameContenedor.class.getName());

    public FrameContenedor() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.log(Level.WARNING, "Failed to initialize LaF");
        }

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Infloria");
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(750, 750));
        setName("gestioHabitos"); // NOI18N
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 /**
     * Método para limpiar el contenido de la ventana. Si hay un panel
     * actualmente mostrado, lo elimina de la ventana.
     */
    public void limpiarFrame() {
        if (panelActual != null) {
            this.remove(panelActual);
            panelActual = null;
        }
    }

    /**
     * Método para agregar un panel a la ventana. Este método agrega el panel
     * especificado a la ventana, lo posiciona y ajusta su tamaño
     * automáticamente.
     *
     * @param panel El panel que se va a agregar a la ventana.
     */
    public void ponerEnFrame(JPanel panel) {
        this.add(panel);
        panel.setBounds(0, 0, 750, 750);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    // Métodos de aviso, confirmación, etc., sin cambios
    public void mostrarAviso(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public boolean mostrarConfirmacion(String mensaje, String titulo) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION);
        return respuesta == JOptionPane.OK_OPTION;
    }

    public void mostrarInformacion(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public Font cargarFuente(String fontPath, float fontSize) throws FontFormatException, IOException {
        InputStream fontStream = Inicio.class.getResourceAsStream(fontPath);
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        return font.deriveFont(fontSize);
    }

    public void mostrarInicioSesion() {
        limpiarFrame();
        InicioSesion inicioSesion = new InicioSesion(this);
        ponerEnFrame(inicioSesion);
        panelActual = inicioSesion;
    }

    public void mostrarInicio() {
        limpiarFrame();
        Inicio inicio = new Inicio(this);
        ponerEnFrame(inicio);
        panelActual = inicio;
    }

    public void mostrarRegistrarUsuario() {
        limpiarFrame();
        RegistrarUsuario registrarUsuario = new RegistrarUsuario(this);
        ponerEnFrame(registrarUsuario);
        panelActual = registrarUsuario;
    }

    public void mostrarListaHabitos() {
        limpiarFrame();
        ListaHabitos listaHabitos = new ListaHabitos(this);
        ponerEnFrame(listaHabitos);
        panelActual = listaHabitos;
    }

    public void mostrarProgresoSemanal() {
        limpiarFrame();
        ProgresoSemanal progresoSemanal = new ProgresoSemanal(this);
        ponerEnFrame(progresoSemanal);
        panelActual = progresoSemanal;
    }

    public void mostrarProgresoMensual() {
        limpiarFrame();
        ProgresoMensual progresoMensual = new ProgresoMensual(this);
        ponerEnFrame(progresoMensual);
        panelActual = progresoMensual;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
