package com.examen.empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class FrmPrincipal extends JFrame {
    private GestorEmpleados gestor;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscarId;
    private JTextField txtContarDepartamento;

    public FrmPrincipal() {
        super("Gestión de Empleados - Examen 003");
        gestor = new GestorEmpleados();
        initComponents();
        setSize(850, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel superior con botones
        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnRegistrar = new JButton("Registrar Empleado");
        JButton btnMostrar = new JButton("Mostrar todos");
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnContar = new JButton("Contar por Departamento");
        JButton btnMayor = new JButton("Empleado Mayor Salario");
        JButton btnSalir = new JButton("Salir");

        txtBuscarId = new JTextField(10);
        txtContarDepartamento = new JTextField(10);

        pnlTop.add(btnRegistrar);
        pnlTop.add(new JLabel("ID:"));
        pnlTop.add(txtBuscarId);
        pnlTop.add(btnBuscar);
        pnlTop.add(btnMostrar);
        pnlTop.add(new JLabel("Departamento:"));
        pnlTop.add(txtContarDepartamento);
        pnlTop.add(btnContar);
        pnlTop.add(btnMayor);
        pnlTop.add(btnSalir);

        add(pnlTop, BorderLayout.NORTH);

        // Tabla
        modelo = new DefaultTableModel(new Object[] {"ID", "Nombre", "Departamento", "Tipo", "Salario", "Extra"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnRegistrar.addActionListener(e -> abrirRegistrar());
        btnMostrar.addActionListener(e -> mostrarTodos());
        btnBuscar.addActionListener(e -> buscarPorId());
        btnContar.addActionListener(e -> contarPorDepartamento());
        btnMayor.addActionListener(e -> mostrarMayorSalario());
        btnSalir.addActionListener(e -> salir());

        // Doble click en fila -> mostrar info
        tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int r = tabla.getSelectedRow();
                    if (r >= 0) {
                        String id = (String) modelo.getValueAt(r, 0);
                        Empleado emp = gestor.buscar(id);
                        if (emp != null) {
                            JOptionPane.showMessageDialog(FrmPrincipal.this, emp.toString(), "Detalle Empleado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    private void abrirRegistrar() {
        FrmRegistrarEmpleado dlg = new FrmRegistrarEmpleado(this, gestor);
        dlg.setVisible(true);
        // después de cerrar, actualizamos la tabla para ver el nuevo registro
        mostrarTodos();
    }

    public void mostrarTodos() {
        modelo.setRowCount(0);
        for (Empleado e : gestor.getTodos()) {
            modelo.addRow(new Object[] {
                e.getId(),
                e.getNombre(),
                e.getDepartamento(),
                e.tipo(),
                String.format("%.2f", e.getSalario()),
                e.datosExtra()
            });
        }
    }

    private void buscarPorId() {
        String id = txtBuscarId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID para buscar");
            return;
        }
        Empleado e = gestor.buscar(id);
        if (e == null) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Buscar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, e.toString(), "Empleado encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void contarPorDepartamento() {
        String dep = txtContarDepartamento.getText().trim();
        if (dep.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa departamento");
            return;
        }
        int tot = gestor.contarPorDepartamento(dep, null);
        int tiempoCompleto = gestor.contarPorDepartamento(dep, "Tiempo Completo");
        int medio = gestor.contarPorDepartamento(dep, "Medio Tiempo");

        String msg = String.format("Departamento: %s\nTotal empleados: %d\nTiempo completo: %d\nMedio tiempo: %d",
                dep, tot, tiempoCompleto, medio);
        JOptionPane.showMessageDialog(this, msg, "Conteo por Departamento", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMayorSalario() {
        Empleado e = gestor.mayorSalario();
        if (e == null) {
            JOptionPane.showMessageDialog(this, "No hay empleados registrados", "Mayor salario", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Empleado con mayor salario:\n" + e.toString(), "Mayor salario", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        int resp = JOptionPane.showConfirmDialog(this, "¿Deseas salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
