package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    
    // Componentes de la interfaz
    private JTextField txtId, txtNombre, txtDepartamento, txtSalario, txtAtributoEspecial;
    private JComboBox<String> comboTipo;
    private JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JLabel lblAtributoEspecial;
    
    public VentanaPrincipal() {
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        // Panel superior - Formulario de entrada
        JPanel panelFormulario = crearPanelFormulario();
        
        // Panel central - Tabla de empleados
        JPanel panelTabla = crearPanelTabla();
        
        // Panel inferior - Botones de acción
        JPanel panelBotones = crearPanelBotones();
        
        // Agregar paneles al frame
        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Datos del Empleado",
            0, 0, 
            new Font("Arial", Font.BOLD, 14)
        ));
        panel.setBackground(new Color(240, 248, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fila 0: ID y Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        txtId = new JTextField(15);
        panel.add(txtId, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 3;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);
        
        // Fila 1: Departamento y Salario
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Departamento:"), gbc);
        
        gbc.gridx = 1;
        txtDepartamento = new JTextField(15);
        panel.add(txtDepartamento, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Salario:"), gbc);
        
        gbc.gridx = 3;
        txtSalario = new JTextField(15);
        panel.add(txtSalario, gbc);
        
        // Fila 2: Tipo de empleado y atributo especial
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tipo:"), gbc);
        
        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Tiempo Completo", "Medio Tiempo"});
        comboTipo.addActionListener(e -> actualizarCampoEspecial());
        panel.add(comboTipo, gbc);
        
        gbc.gridx = 2;
        lblAtributoEspecial = new JLabel("Días de Vacaciones:");
        panel.add(lblAtributoEspecial, gbc);
        
        gbc.gridx = 3;
        txtAtributoEspecial = new JTextField(15);
        panel.add(txtAtributoEspecial, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Lista de Empleados",
            0, 0,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        // Crear modelo de tabla
        String[] columnas = {"ID", "Nombre", "Departamento", "Salario", "Tipo", "Info Adicional"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Crear tabla
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.setRowHeight(25);
        tablaEmpleados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEmpleados.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);
        
        // Ajustar anchos de columnas
        tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaEmpleados.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaEmpleados.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaEmpleados.getColumnModel().getColumn(4).setPreferredWidth(120);
        tablaEmpleados.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(240, 248, 255));
        
        // Crear botones con iconos y colores
        btnAgregar = crearBoton("Agregar", new Color(34, 139, 34));
        btnModificar = crearBoton("Modificar", new Color(255, 140, 0));
        btnEliminar = crearBoton("Eliminar", new Color(220, 20, 60));
        btnLimpiar = crearBoton("Limpiar", new Color(70, 130, 180));
        
        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(120, 35));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    private void actualizarCampoEspecial() {
        String tipo = (String) comboTipo.getSelectedItem();
        if ("Tiempo Completo".equals(tipo)) {
            lblAtributoEspecial.setText("Días de Vacaciones:");
        } else {
            lblAtributoEspecial.setText("Horas/Semana:");
        }
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestión de Empleados");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(240, 248, 255));
    }
    
    // Getters para los componentes (necesarios para el controlador)
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtDepartamento() { return txtDepartamento; }
    public JTextField getTxtSalario() { return txtSalario; }
    public JTextField getTxtAtributoEspecial() { return txtAtributoEspecial; }
    public JComboBox<String> getComboTipo() { return comboTipo; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTable getTablaEmpleados() { return tablaEmpleados; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}