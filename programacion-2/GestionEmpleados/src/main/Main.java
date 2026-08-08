package main;

import vista.VentanaPrincipal;
import controlador.ControladorEmpleado;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación
 * Sistema de Gestión de Empleados
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // 1. Crear la vista (interfaz gráfica)
            VentanaPrincipal vista = new VentanaPrincipal();
            
            // 2. Crear el controlador y pasarle la vista
            // El controlador conecta la lógica con la interfaz
            ControladorEmpleado controlador = new ControladorEmpleado(vista);
            
            // 3. Mostrar la ventana
            vista.setVisible(true);
            
            System.out.println("Sistema iniciado correctamente");
        });
    }
}