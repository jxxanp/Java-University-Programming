package vista;

import modelo.Persona;
import modelo.Empleado;
import modelo.Cliente;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    
    // ArrayLists para almacenar objetos
    private ArrayList<Persona> listaPersonas = new ArrayList<>();
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    
    // Componentes de la interfaz - PESTAÑA PERSONA
    private JTextField txtNombreP, txtApellidoP, txtEdadP, txtDireccionP, txtTelefonoP;
    private JButton btnGuardarP, btnMostrarP, btnLimpiarP;
    private JTextArea txtAreaPersona;
    
    // Componentes de la interfaz - PESTAÑA EMPLEADO
    private JTextField txtNombreE, txtApellidoE, txtEdadE, txtDireccionE, txtTelefonoE;
    private JTextField txtCodigoE, txtCargoE, txtSalarioE, txtDepartamentoE, txtFechaE;
    private JButton btnGuardarE, btnMostrarE, btnAumentarE, btnLimpiarE;
    private JTextArea txtAreaEmpleado;
    
    // Componentes de la interfaz - PESTAÑA CLIENTE
    private JTextField txtNombreC, txtApellidoC, txtEdadC, txtDireccionC, txtTelefonoC;
    private JTextField txtCodigoC, txtEmailC, txtCreditoC, txtPuntosC;
    private JComboBox<String> cboTipoC;
    private JButton btnGuardarC, btnMostrarC, btnAgregarPuntosC, btnCanjearPuntosC, btnLimpiarC;
    private JTextArea txtAreaCliente;
    
    // Constructor
    public VentanaPrincipal() {
        initComponents();
    }
    
    // Método para inicializar componentes
    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Sistema de Gestión - Persona, Empleado, Cliente");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Crear el JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar las tres pestañas
        tabbedPane.addTab("Gestión de Personas", crearPanelPersonas());
        tabbedPane.addTab("Gestión de Empleados", crearPanelEmpleados());
        tabbedPane.addTab("Gestión de Clientes", crearPanelClientes());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // ==================== PANEL DE PERSONAS ====================
    private JPanel crearPanelPersonas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de Persona"));
        
        txtNombreP = new JTextField();
        txtApellidoP = new JTextField();
        txtEdadP = new JTextField();
        txtDireccionP = new JTextField();
        txtTelefonoP = new JTextField();
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombreP);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellidoP);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdadP);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccionP);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefonoP);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardarP = new JButton("Guardar Persona");
        btnMostrarP = new JButton("Mostrar Información");
        btnLimpiarP = new JButton("Limpiar");
        
        btnGuardarP.addActionListener(e -> btnGuardarPActionPerformed());
        btnMostrarP.addActionListener(e -> btnMostrarPActionPerformed());
        btnLimpiarP.addActionListener(e -> btnLimpiarPActionPerformed());
        
        panelBotones.add(btnGuardarP);
        panelBotones.add(btnMostrarP);
        panelBotones.add(btnLimpiarP);
        
        // Panel con área de texto
        txtAreaPersona = new JTextArea(15, 40);
        txtAreaPersona.setEditable(false);
        txtAreaPersona.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaPersona);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Personas"));
        
        // Ensamblar panel
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== PANEL DE EMPLEADOS ====================
    private JPanel crearPanelEmpleados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(10, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de Empleado"));
        
        txtNombreE = new JTextField();
        txtApellidoE = new JTextField();
        txtEdadE = new JTextField();
        txtDireccionE = new JTextField();
        txtTelefonoE = new JTextField();
        txtCodigoE = new JTextField();
        txtCargoE = new JTextField();
        txtSalarioE = new JTextField();
        txtDepartamentoE = new JTextField();
        txtFechaE = new JTextField();
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombreE);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellidoE);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdadE);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccionE);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefonoE);
        panelFormulario.add(new JLabel("Código Empleado:"));
        panelFormulario.add(txtCodigoE);
        panelFormulario.add(new JLabel("Cargo:"));
        panelFormulario.add(txtCargoE);
        panelFormulario.add(new JLabel("Salario:"));
        panelFormulario.add(txtSalarioE);
        panelFormulario.add(new JLabel("Departamento:"));
        panelFormulario.add(txtDepartamentoE);
        panelFormulario.add(new JLabel("Fecha Contratación:"));
        panelFormulario.add(txtFechaE);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardarE = new JButton("Guardar Empleado");
        btnMostrarE = new JButton("Mostrar Información");
        btnAumentarE = new JButton("Aumentar Salario");
        btnLimpiarE = new JButton("Limpiar");
        
        btnGuardarE.addActionListener(e -> btnGuardarEActionPerformed());
        btnMostrarE.addActionListener(e -> btnMostrarEActionPerformed());
        btnAumentarE.addActionListener(e -> btnAumentarEActionPerformed());
        btnLimpiarE.addActionListener(e -> btnLimpiarEActionPerformed());
        
        panelBotones.add(btnGuardarE);
        panelBotones.add(btnMostrarE);
        panelBotones.add(btnAumentarE);
        panelBotones.add(btnLimpiarE);
        
        // Panel con área de texto
        txtAreaEmpleado = new JTextArea(10, 40);
        txtAreaEmpleado.setEditable(false);
        txtAreaEmpleado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaEmpleado);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Empleados"));
        
        // Ensamblar panel
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== PANEL DE CLIENTES ====================
    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(10, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de Cliente"));
        
        txtNombreC = new JTextField();
        txtApellidoC = new JTextField();
        txtEdadC = new JTextField();
        txtDireccionC = new JTextField();
        txtTelefonoC = new JTextField();
        txtCodigoC = new JTextField();
        txtEmailC = new JTextField();
        cboTipoC = new JComboBox<>(new String[]{"Nuevo", "Regular", "VIP"});
        txtCreditoC = new JTextField();
        txtPuntosC = new JTextField();
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombreC);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellidoC);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdadC);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccionC);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefonoC);
        panelFormulario.add(new JLabel("Código Cliente:"));
        panelFormulario.add(txtCodigoC);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmailC);
        panelFormulario.add(new JLabel("Tipo Cliente:"));
        panelFormulario.add(cboTipoC);
        panelFormulario.add(new JLabel("Crédito:"));
        panelFormulario.add(txtCreditoC);
        panelFormulario.add(new JLabel("Puntos:"));
        panelFormulario.add(txtPuntosC);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardarC = new JButton("Guardar Cliente");
        btnMostrarC = new JButton("Mostrar Información");
        btnAgregarPuntosC = new JButton("Agregar Puntos");
        btnCanjearPuntosC = new JButton("Canjear Puntos");
        btnLimpiarC = new JButton("Limpiar");
        
        btnGuardarC.addActionListener(e -> btnGuardarCActionPerformed());
        btnMostrarC.addActionListener(e -> btnMostrarCActionPerformed());
        btnAgregarPuntosC.addActionListener(e -> btnAgregarPuntosCActionPerformed());
        btnCanjearPuntosC.addActionListener(e -> btnCanjearPuntosCActionPerformed());
        btnLimpiarC.addActionListener(e -> btnLimpiarCActionPerformed());
        
        panelBotones.add(btnGuardarC);
        panelBotones.add(btnMostrarC);
        panelBotones.add(btnAgregarPuntosC);
        panelBotones.add(btnCanjearPuntosC);
        panelBotones.add(btnLimpiarC);
        
        // Panel con área de texto
        txtAreaCliente = new JTextArea(10, 40);
        txtAreaCliente.setEditable(false);
        txtAreaCliente.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaCliente);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Clientes"));
        
        // Ensamblar panel
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== EVENTOS DE PERSONA ====================
    private void btnGuardarPActionPerformed() {
        try {
            String nombre = txtNombreP.getText().trim();
            String apellido = txtApellidoP.getText().trim();
            int edad = Integer.parseInt(txtEdadP.getText().trim());
            String direccion = txtDireccionP.getText().trim();
            String telefono = txtTelefonoP.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Nombre y Apellido son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Persona persona = new Persona(nombre, apellido, edad, direccion, telefono);
            listaPersonas.add(persona);
            
            JOptionPane.showMessageDialog(this, 
                "Persona guardada exitosamente\nTotal: " + listaPersonas.size(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCamposPersona();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La edad debe ser un número válido", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnMostrarPActionPerformed() {
        txtAreaPersona.setText("");
        
        if (listaPersonas.isEmpty()) {
            txtAreaPersona.setText("No hay personas registradas");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PERSONAS ===\n\n");
        
        for (int i = 0; i < listaPersonas.size(); i++) {
            sb.append("PERSONA #").append(i + 1).append("\n");
            sb.append(listaPersonas.get(i).mostrarInformacion());
            sb.append("\n").append("=".repeat(50)).append("\n\n");
        }
        
        txtAreaPersona.setText(sb.toString());
        txtAreaPersona.setCaretPosition(0);
    }
    
    private void btnLimpiarPActionPerformed() {
        limpiarCamposPersona();
    }
    
    private void limpiarCamposPersona() {
        txtNombreP.setText("");
        txtApellidoP.setText("");
        txtEdadP.setText("");
        txtDireccionP.setText("");
        txtTelefonoP.setText("");
        txtNombreP.requestFocus();
    }
    
    // ==================== EVENTOS DE EMPLEADO ====================
    private void btnGuardarEActionPerformed() {
        try {
            String nombre = txtNombreE.getText().trim();
            String apellido = txtApellidoE.getText().trim();
            int edad = Integer.parseInt(txtEdadE.getText().trim());
            String direccion = txtDireccionE.getText().trim();
            String telefono = txtTelefonoE.getText().trim();
            String codigo = txtCodigoE.getText().trim();
            String cargo = txtCargoE.getText().trim();
            double salario = Double.parseDouble(txtSalarioE.getText().trim());
            String departamento = txtDepartamentoE.getText().trim();
            String fecha = txtFechaE.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Complete los campos obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Empleado empleado = new Empleado(nombre, apellido, edad, direccion, 
                                             telefono, codigo, cargo, salario, 
                                             departamento, fecha);
            listaEmpleados.add(empleado);
            
            JOptionPane.showMessageDialog(this, 
                "Empleado guardado exitosamente\nTotal: " + listaEmpleados.size(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCamposEmpleado();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Verifique que edad y salario sean números válidos", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnMostrarEActionPerformed() {
        txtAreaEmpleado.setText("");
        
        if (listaEmpleados.isEmpty()) {
            txtAreaEmpleado.setText("No hay empleados registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE EMPLEADOS ===\n\n");
        
        for (int i = 0; i < listaEmpleados.size(); i++) {
            sb.append("EMPLEADO #").append(i + 1).append("\n");
            sb.append(listaEmpleados.get(i).mostrarInformacion());
            sb.append("\nSalario Anual: $")
              .append(String.format("%.2f", listaEmpleados.get(i).calcularSalarioAnual()));
            sb.append("\n").append("=".repeat(50)).append("\n\n");
        }
        
        txtAreaEmpleado.setText(sb.toString());
        txtAreaEmpleado.setCaretPosition(0);
    }
    
    private void btnAumentarEActionPerformed() {
        if (listaEmpleados.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay empleados registrados", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String[] opciones = new String[listaEmpleados.size()];
        for (int i = 0; i < listaEmpleados.size(); i++) {
            opciones[i] = (i + 1) + ". " + listaEmpleados.get(i).toString();
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione el empleado:",
            "Aumentar Salario",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null) return;
        
        int indice = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        Empleado empleado = listaEmpleados.get(indice);
        
        String porcentajeStr = JOptionPane.showInputDialog(
            this,
            "Salario actual: $" + String.format("%.2f", empleado.getSalario()) + 
            "\nIngrese el porcentaje de aumento:",
            "Aumentar Salario",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (porcentajeStr == null || porcentajeStr.trim().isEmpty()) return;
        
        try {
            double porcentaje = Double.parseDouble(porcentajeStr.trim());
            double salarioAnterior = empleado.getSalario();
            empleado.aumentarSalario(porcentaje);
            
            JOptionPane.showMessageDialog(this,
                "Aumento aplicado exitosamente\n" +
                "Salario anterior: $" + String.format("%.2f", salarioAnterior) + "\n" +
                "Nuevo salario: $" + String.format("%.2f", empleado.getSalario()),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "El porcentaje debe ser un número válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void btnLimpiarEActionPerformed() {
        limpiarCamposEmpleado();
    }
    
    private void limpiarCamposEmpleado() {
        txtNombreE.setText("");
        txtApellidoE.setText("");
        txtEdadE.setText("");
        txtDireccionE.setText("");
        txtTelefonoE.setText("");
        txtCodigoE.setText("");
        txtCargoE.setText("");
        txtSalarioE.setText("");
        txtDepartamentoE.setText("");
        txtFechaE.setText("");
        txtNombreE.requestFocus();
    }
    
    // ==================== EVENTOS DE CLIENTE ====================
    private void btnGuardarCActionPerformed() {
        try {
            String nombre = txtNombreC.getText().trim();
            String apellido = txtApellidoC.getText().trim();
            int edad = Integer.parseInt(txtEdadC.getText().trim());
            String direccion = txtDireccionC.getText().trim();
            String telefono = txtTelefonoC.getText().trim();
            String codigo = txtCodigoC.getText().trim();
            String email = txtEmailC.getText().trim();
            String tipo = (String) cboTipoC.getSelectedItem();
            double credito = Double.parseDouble(txtCreditoC.getText().trim());
            int puntos = Integer.parseInt(txtPuntosC.getText().trim());
            
            if (nombre.isEmpty() || apellido.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Complete los campos obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Cliente cliente = new Cliente(nombre, apellido, edad, direccion, 
                                          telefono, codigo, email, tipo, 
                                          credito, puntos);
            listaClientes.add(cliente);
            
            JOptionPane.showMessageDialog(this, 
                "Cliente guardado exitosamente\nTotal: " + listaClientes.size(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCamposCliente();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Verifique que edad, crédito y puntos sean números válidos", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnMostrarCActionPerformed() {
        txtAreaCliente.setText("");
        
        if (listaClientes.isEmpty()) {
            txtAreaCliente.setText("No hay clientes registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE CLIENTES ===\n\n");
        
        for (int i = 0; i < listaClientes.size(); i++) {
            sb.append("CLIENTE #").append(i + 1).append("\n");
            sb.append(listaClientes.get(i).mostrarInformacion());
            sb.append("\n").append("=".repeat(50)).append("\n\n");
        }
        
        txtAreaCliente.setText(sb.toString());
        txtAreaCliente.setCaretPosition(0);
    }
    
    private void btnAgregarPuntosCActionPerformed() {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay clientes registrados", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String[] opciones = new String[listaClientes.size()];
        for (int i = 0; i < listaClientes.size(); i++) {
            opciones[i] = (i + 1) + ". " + listaClientes.get(i).toString();
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione el cliente:",
            "Agregar Puntos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null) return;
        
        int indice = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        Cliente cliente = listaClientes.get(indice);
        
        String puntosStr = JOptionPane.showInputDialog(
            this,
            "Puntos actuales: " + cliente.getPuntosAcumulados() + 
            "\nIngrese puntos a agregar:",
            "Agregar Puntos",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (puntosStr == null || puntosStr.trim().isEmpty()) return;
        
        try {
            int puntos = Integer.parseInt(puntosStr.trim());
            if (puntos < 0) {
                JOptionPane.showMessageDialog(this,
                    "Los puntos deben ser un número positivo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            int puntosAnteriores = cliente.getPuntosAcumulados();
            cliente.agregarPuntos(puntos);
            
            JOptionPane.showMessageDialog(this,
                "Puntos agregados exitosamente\n" +
                "Puntos anteriores: " + puntosAnteriores + "\n" +
                "Nuevos puntos: " + cliente.getPuntosAcumulados() + "\n" +
                "Tipo de cliente: " + cliente.getTipoCliente(),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Los puntos deben ser un número válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void btnCanjearPuntosCActionPerformed() {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay clientes registrados", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String[] opciones = new String[listaClientes.size()];
        for (int i = 0; i < listaClientes.size(); i++) {
            opciones[i] = (i + 1) + ". " + listaClientes.get(i).toString();
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione el cliente:",
            "Canjear Puntos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null) return;
        
        int indice = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        Cliente cliente = listaClientes.get(indice);
        
        String puntosStr = JOptionPane.showInputDialog(
            this,
            "Puntos disponibles: " + cliente.getPuntosAcumulados() + 
            "\nIngrese puntos a canjear:",
            "Canjear Puntos",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (puntosStr == null || puntosStr.trim().isEmpty()) return;
        
        try {
            int puntos = Integer.parseInt(puntosStr.trim());
            
            if (cliente.canjearPuntos(puntos)) {
                JOptionPane.showMessageDialog(this,
                    "Puntos canjeados exitosamente\n" +
                    "Puntos restantes: " + cliente.getPuntosAcumulados(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(this,
                    "Puntos insuficientes\n" +
                    "Puntos disponibles: " + cliente.getPuntosAcumulados() + "\n" +
                    "Puntos requeridos: " + puntos,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Los puntos deben ser un número válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void btnLimpiarCActionPerformed() {
        limpiarCamposCliente();
    }
    
    private void limpiarCamposCliente() {
        txtNombreC.setText("");
        txtApellidoC.setText("");
        txtEdadC.setText("");
        txtDireccionC.setText("");
        txtTelefonoC.setText("");
        txtCodigoC.setText("");
        txtEmailC.setText("");
        cboTipoC.setSelectedIndex(0);
        txtCreditoC.setText("");
        txtPuntosC.setText("");
        txtNombreC.requestFocus();
    }
}