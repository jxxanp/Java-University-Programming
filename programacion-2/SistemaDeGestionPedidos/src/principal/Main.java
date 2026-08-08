package principal;

import vista.VentanaPrincipal;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look & Feel no disponible: " + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal v = new VentanaPrincipal();
                v.setVisible(true);
                JOptionPane.showMessageDialog(v, "Bienvenido al Sistema de Gestión de Pedidos\nEjercicio 04", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
