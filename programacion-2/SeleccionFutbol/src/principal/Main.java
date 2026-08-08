package principal;

import vista.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer look and feel del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Crear y mostrar la ventana principal
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
            
            System.out.println("═══════════════════════════════════════════");
            System.out.println("⚽ SISTEMA DE SELECCIÓN DE FÚTBOL INICIADO ⚽");
            System.out.println("═══════════════════════════════════════════");
        });
    }
}