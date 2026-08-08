package controlador;

import modelo.*;
import vista.VentanaPrincipal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class ControladorEmpleado {
    private VentanaPrincipal vista;
    private ArrayList<Empleado> listaEmpleados;
    
    public ControladorEmpleado(VentanaPrincipal vista) {
        this.vista = vista;
        this.listaEmpleados = new ArrayList<>();
        inicializarEventos();
    }
    
    private void inicializarEventos() {
        // Botón Agregar
        vista.getBtnAgregar().addActionListener(e -> agregarEmpleado());
        
        // Botón Modificar
        vista.getBtnModificar().addActionListener(e -> modificarEmpleado());
        
        // Botón Eliminar
        vista.getBtnEliminar().addActionListener(e -> eliminarEmpleado());
        
        // Botón Limpiar
        vista.getBtnLimpiar().addActionListener(e -> limpiarCampos());
        
        // Evento de selección en la tabla
        vista.getTablaEmpleados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatosTabla();
            }
        });
        
        // Menú adicional
        agregarMenu();
    }
    
    // ========== AGREGAR EMPLEADO ==========
    private void agregarEmpleado() {
        try {
            // Validar campos vacíos
            if (!validarCampos()) {
                return;
            }
            
            // Obtener datos
            String id = vista.getTxtId().getText().trim();
            String nombre = vista.getTxtNombre().getText().trim();
            String departamento = vista.getTxtDepartamento().getText().trim();
            double salario = Double.parseDouble(vista.getTxtSalario().getText().trim());
            String tipo = (String) vista.getComboTipo().getSelectedItem();
            int atributoEspecial = Integer.parseInt(vista.getTxtAtributoEspecial().getText().trim());
            
            // Validar que no exista el ID
            if (buscarEmpleadoPorId(id) != null) {
                JOptionPane.showMessageDialog(vista, 
                    "El ID ya existe. No se permiten IDs duplicados.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear empleado según tipo
            Empleado empleado;
            if ("Tiempo Completo".equals(tipo)) {
                empleado = new TiempoCompleto(id, nombre, departamento, salario, atributoEspecial);
            } else {
                empleado = new MedioTiempo(id, nombre, departamento, salario, atributoEspecial);
            }
            
            // Agregar a la lista
            listaEmpleados.add(empleado);
            
            // Actualizar tabla
            actualizarTabla();
            
            // Limpiar campos
            limpiarCampos();
            
            JOptionPane.showMessageDialog(vista, 
                "Empleado agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error en formato de número. Verifica Salario y campo adicional.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== MODIFICAR EMPLEADO ==========
    private void modificarEmpleado() {
        int filaSeleccionada = vista.getTablaEmpleados().getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, 
                "Selecciona un empleado de la tabla para modificar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            if (!validarCampos()) {
                return;
            }
            
            String idOriginal = (String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 0);
            String nuevoId = vista.getTxtId().getText().trim();
            
            // Si cambió el ID, verificar que no exista
            if (!idOriginal.equals(nuevoId) && buscarEmpleadoPorId(nuevoId) != null) {
                JOptionPane.showMessageDialog(vista, 
                    "El nuevo ID ya existe", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener datos
            String nombre = vista.getTxtNombre().getText().trim();
            String departamento = vista.getTxtDepartamento().getText().trim();
            double salario = Double.parseDouble(vista.getTxtSalario().getText().trim());
            String tipo = (String) vista.getComboTipo().getSelectedItem();
            int atributoEspecial = Integer.parseInt(vista.getTxtAtributoEspecial().getText().trim());
            
            // Eliminar empleado anterior
            listaEmpleados.remove(filaSeleccionada);
            
            // Crear nuevo empleado
            Empleado empleado;
            if ("Tiempo Completo".equals(tipo)) {
                empleado = new TiempoCompleto(nuevoId, nombre, departamento, salario, atributoEspecial);
            } else {
                empleado = new MedioTiempo(nuevoId, nombre, departamento, salario, atributoEspecial);
            }
            
            listaEmpleados.add(filaSeleccionada, empleado);
            actualizarTabla();
            limpiarCampos();
            
            JOptionPane.showMessageDialog(vista, 
                "Empleado modificado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error en formato de número", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== ELIMINAR EMPLEADO ==========
    private void eliminarEmpleado() {
        int filaSeleccionada = vista.getTablaEmpleados().getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, 
                "Selecciona un empleado de la tabla para eliminar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vista, 
            "¿Estás seguro de eliminar este empleado?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            listaEmpleados.remove(filaSeleccionada);
            actualizarTabla();
            limpiarCampos();
            
            JOptionPane.showMessageDialog(vista, 
                "Empleado eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ========== LIMPIAR CAMPOS ==========
    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtDepartamento().setText("");
        vista.getTxtSalario().setText("");
        vista.getTxtAtributoEspecial().setText("");
        vista.getComboTipo().setSelectedIndex(0);
        vista.getTablaEmpleados().clearSelection();
    }
    
    // ========== CARGAR DATOS DE LA TABLA AL FORMULARIO ==========
    private void cargarDatosTabla() {
        int filaSeleccionada = vista.getTablaEmpleados().getSelectedRow();
        
        if (filaSeleccionada != -1) {
            vista.getTxtId().setText((String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 0));
            vista.getTxtNombre().setText((String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 1));
            vista.getTxtDepartamento().setText((String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 2));
            vista.getTxtSalario().setText(vista.getTablaEmpleados().getValueAt(filaSeleccionada, 3).toString());
            
            String tipo = (String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 4);
            vista.getComboTipo().setSelectedItem(tipo);
            
            String infoAdicional = (String) vista.getTablaEmpleados().getValueAt(filaSeleccionada, 5);
            String valor = infoAdicional.replaceAll("[^0-9]", "");
            vista.getTxtAtributoEspecial().setText(valor);
        }
    }
    
    // ========== ACTUALIZAR TABLA ==========
    private void actualizarTabla() {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);
        
        for (Empleado emp : listaEmpleados) {
            String infoAdicional;
            if (emp instanceof TiempoCompleto) {
                TiempoCompleto tc = (TiempoCompleto) emp;
                infoAdicional = tc.getDiasVacaciones() + " días";
            } else {
                MedioTiempo mt = (MedioTiempo) emp;
                infoAdicional = mt.getHorasTrabajadasPorSemana() + " hrs/sem";
            }
            
            modelo.addRow(new Object[]{
                emp.getId(),
                emp.getNombre(),
                emp.getDepartamento(),
                String.format("$%.2f", emp.getSalario()),
                emp instanceof TiempoCompleto ? "Tiempo Completo" : "Medio Tiempo",
                infoAdicional
            });
        }
    }
    
    // ========== VALIDAR CAMPOS ==========
    private boolean validarCampos() {
        if (vista.getTxtId().getText().trim().isEmpty() ||
            vista.getTxtNombre().getText().trim().isEmpty() ||
            vista.getTxtDepartamento().getText().trim().isEmpty() ||
            vista.getTxtSalario().getText().trim().isEmpty() ||
            vista.getTxtAtributoEspecial().getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(vista, 
                "Todos los campos son obligatorios", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    // ========== BUSCAR EMPLEADO POR ID ==========
    private Empleado buscarEmpleadoPorId(String id) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }
    
    // ========== AGREGAR MENÚ ==========
    private void agregarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuConsultas = new JMenu("Consultas");
        
        JMenuItem itemBuscar = new JMenuItem("Buscar por ID");
        itemBuscar.addActionListener(e -> buscarPorId());
        
        JMenuItem itemContarDept = new JMenuItem("Contar por Departamento");
        itemContarDept.addActionListener(e -> contarPorDepartamento());
        
        JMenuItem itemMayorSalario = new JMenuItem("Mayor Salario");
        itemMayorSalario.addActionListener(e -> mostrarMayorSalario());
        
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        
        menuConsultas.add(itemBuscar);
        menuConsultas.add(itemContarDept);
        menuConsultas.add(itemMayorSalario);
        menuConsultas.addSeparator();
        menuConsultas.add(itemSalir);
        
        menuBar.add(menuConsultas);
        vista.setJMenuBar(menuBar);
    }
    
    // ========== BUSCAR POR ID (MENÚ) ==========
    private void buscarPorId() {
        String id = JOptionPane.showInputDialog(vista, "Ingresa el ID del empleado:");
        
        if (id != null && !id.trim().isEmpty()) {
            Empleado emp = buscarEmpleadoPorId(id.trim());
            
            if (emp != null) {
                String mensaje = String.format(
                    "ID: %s\nNombre: %s\nDepartamento: %s\nSalario: $%.2f\nTipo: %s",
                    emp.getId(), emp.getNombre(), emp.getDepartamento(), 
                    emp.getSalario(),
                    emp instanceof TiempoCompleto ? "Tiempo Completo" : "Medio Tiempo"
                );
                
                if (emp instanceof TiempoCompleto) {
                    mensaje += "\nDías de vacaciones: " + ((TiempoCompleto) emp).getDiasVacaciones();
                } else {
                    mensaje += "\nHoras/semana: " + ((MedioTiempo) emp).getHorasTrabajadasPorSemana();
                }
                
                JOptionPane.showMessageDialog(vista, mensaje, "Información del Empleado", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "Empleado no encontrado", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== CONTAR POR DEPARTAMENTO ==========
    private void contarPorDepartamento() {
        String dept = JOptionPane.showInputDialog(vista, "Ingresa el nombre del departamento:");
        
        if (dept != null && !dept.trim().isEmpty()) {
            int countTC = 0, countMT = 0;
            
            for (Empleado emp : listaEmpleados) {
                if (emp.getDepartamento().equalsIgnoreCase(dept.trim())) {
                    if (emp instanceof TiempoCompleto) {
                        countTC++;
                    } else {
                        countMT++;
                    }
                }
            }
            
            String mensaje = String.format(
                "Departamento: %s\n\nTiempo Completo: %d\nMedio Tiempo: %d\nTotal: %d",
                dept, countTC, countMT, countTC + countMT
            );
            
            JOptionPane.showMessageDialog(vista, mensaje, "Conteo por Departamento", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ========== MOSTRAR EMPLEADO CON MAYOR SALARIO ==========
    private void mostrarMayorSalario() {
        if (listaEmpleados.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay empleados registrados", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Empleado empMayorSalario = listaEmpleados.get(0);
        for (Empleado emp : listaEmpleados) {
            if (emp.getSalario() > empMayorSalario.getSalario()) {
                empMayorSalario = emp;
            }
        }
        
        String mensaje = String.format(
            "Empleado con Mayor Salario:\n\nID: %s\nNombre: %s\nDepartamento: %s\nSalario: $%.2f\nTipo: %s",
            empMayorSalario.getId(), empMayorSalario.getNombre(), 
            empMayorSalario.getDepartamento(), empMayorSalario.getSalario(),
            empMayorSalario instanceof TiempoCompleto ? "Tiempo Completo" : "Medio Tiempo"
        );
        
        JOptionPane.showMessageDialog(vista, mensaje, "Mayor Salario", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}