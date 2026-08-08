package principal;

import vista.VentanaPrincipal;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Establecer Look and Feel del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel: " + e.getMessage());
        }
        
        // Crear y mostrar la ventana principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
                
                // Mensaje de bienvenida
                JOptionPane.showMessageDialog(
                    ventana,
                    "Bienvenido al Sistema de Gestión\n" +
                    "Persona - Empleado - Cliente\n\n" +
                    "Utilice las pestañas para navegar",
                    "Sistema de Gestión",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}