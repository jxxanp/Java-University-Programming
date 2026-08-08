package principal;

import vista.VentanaPrincipal;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel: " + e.getMessage());
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
                
                JOptionPane.showMessageDialog(
                    ventana,
                    "Bienvenido al Sistema de Gestión Académica\n\n" +
                    "Este sistema gestiona:\n" +
                    "• Alumnos\n" +
                    "• Instructores\n\n" +
                    "Incluye validación automática de datos",
                    "Sistema de Gestión Académica",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}