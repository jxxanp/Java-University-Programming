package com.miempresa.trabajadores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private VectorTrabajadores vector;
    private DefaultTableModel model;
    private JTable table;

    // Campos de entrada
    private JTextField txtCodigo, txtNombres, txtApellidos, txtDepartamento, txtAnios, txtCargo, txtSalario;
    // Botones
    private JButton btnAgregar, btnEliminar, btnBuscar, btnListarDesc, btnMayorMenor, btnMenorProm, btnMayorProm;

    public MainFrame() {
        vector = new VectorTrabajadores(20); // o la capacidad que quieras
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Trabajadores - Ejercicio 02");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Panel de inputs
        JPanel pIn = new JPanel(new GridLayout(2,7,5,5));
        txtCodigo = new JTextField();
        txtNombres = new JTextField();
        txtApellidos = new JTextField();
        txtDepartamento = new JTextField();
        txtAnios = new JTextField();
        txtCargo = new JTextField();
        txtSalario = new JTextField();

        pIn.add(new JLabel("Código")); pIn.add(new JLabel("Nombres")); pIn.add(new JLabel("Apellidos"));
        pIn.add(new JLabel("Departamento")); pIn.add(new JLabel("Años")); pIn.add(new JLabel("Cargo"));
        pIn.add(new JLabel("Salario"));

        pIn.add(txtCodigo); pIn.add(txtNombres); pIn.add(txtApellidos);
        pIn.add(txtDepartamento); pIn.add(txtAnios); pIn.add(txtCargo); pIn.add(txtSalario);

        // Botones
        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8,8));
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar por Código");
        btnBuscar = new JButton("Buscar por Código");
        btnListarDesc = new JButton("Listar Desc por Código");
        btnMayorMenor = new JButton("Mayor y Menor Salario");
        btnMenorProm = new JButton("Menor al Promedio");
        btnMayorProm = new JButton("Mayor al Promedio");

        pBtns.add(btnAgregar); pBtns.add(btnEliminar); pBtns.add(btnBuscar);
        pBtns.add(btnListarDesc); pBtns.add(btnMayorMenor); pBtns.add(btnMenorProm); pBtns.add(btnMayorProm);

        // Tabla
        model = new DefaultTableModel(new Object[]{"Código","Nombres","Apellidos","Departamento","Años","Cargo","Salario"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(pIn, BorderLayout.NORTH);
        add(pBtns, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);
        // ajustar tamaño de paneles
        scroll.setPreferredSize(new Dimension(880,300));

        // Listeners
        btnAgregar.addActionListener(e -> agregarTrabajador());
        btnEliminar.addActionListener(e -> eliminarTrabajador());
        btnBuscar.addActionListener(e -> buscarTrabajador());
        btnListarDesc.addActionListener(e -> listarDescPorCodigo());
        btnMayorMenor.addActionListener(e -> mostrarMayorMenor());
        btnMenorProm.addActionListener(e -> mostrarMenorAlPromedio());
        btnMayorProm.addActionListener(e -> mostrarMayorAlPromedio());
    }

    private void agregarTrabajador() {
        try {
            String c = txtCodigo.getText().trim();
            if (c.isEmpty()) { JOptionPane.showMessageDialog(this,"Código obligatorio"); return; }
            String nombres = txtNombres.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String dept = txtDepartamento.getText().trim();
            int anios = Integer.parseInt(txtAnios.getText().trim());
            String cargo = txtCargo.getText().trim();
            double salario = Double.parseDouble(txtSalario.getText().trim());

            Trabajador t = new Trabajador(c, nombres, apellidos, dept, anios, cargo, salario);
            boolean exito = vector.agregar(t);
            if (!exito) {
                JOptionPane.showMessageDialog(this, "No se pudo agregar. Código repetido o vector lleno.");
            } else {
                JOptionPane.showMessageDialog(this, "Trabajador agregado.");
                refrescarTabla(vector.getTodos());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Años y salario deben ser numéricos.");
        }
    }

    private void eliminarTrabajador() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código a eliminar:");
        if (codigo == null || codigo.trim().isEmpty()) return;
        boolean exito = vector.eliminarPorCodigo(codigo.trim());
        JOptionPane.showMessageDialog(this, exito ? "Eliminado (posición dejada vacía)" : "Código no encontrado");
        refrescarTabla(vector.getTodos());
    }

    private void buscarTrabajador() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código a buscar:");
        if (codigo == null || codigo.trim().isEmpty()) return;
        Trabajador t = vector.buscarPorCodigo(codigo.trim());
        if (t == null) {
            JOptionPane.showMessageDialog(this, "No encontrado");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Código: " + t.getCodigo() + "\nNombre: " + t.getNombres() + " " + t.getApellidos() +
                    "\nDepartamento: " + t.getDepartamento() + "\nAños: " + t.getAniosTrabajados() +
                    "\nCargo: " + t.getCargo() + "\nSalario: " + t.getSalario());
        }
    }

    private void listarDescPorCodigo() {
        vector.ordenarBurbujaDescPorCodigo();
        refrescarTabla(vector.getTodos());
    }

    private void mostrarMayorMenor() {
        Trabajador mayor = vector.getMayorSalario();
        Trabajador menor = vector.getMenorSalario();
        String msg = "";
        msg += mayor == null ? "No hay trabajadores\n" : "Mayor salario:\n" + mayor.getCodigo() + " - " + mayor.getNombres() + " " + mayor.getApellidos() + " => " + mayor.getSalario() + "\n";
        msg += menor == null ? "" : "Menor salario:\n" + menor.getCodigo() + " - " + menor.getNombres() + " " + menor.getApellidos() + " => " + menor.getSalario();
        JOptionPane.showMessageDialog(this, msg);
    }

    private void mostrarMenorAlPromedio() {
        Trabajador[] arr = vector.getPorDebajoDelPromedio();
        refrescarTabla(arr);
    }

    private void mostrarMayorAlPromedio() {
        Trabajador[] arr = vector.getPorEncimaDelPromedio();
        refrescarTabla(arr);
    }

    private void refrescarTabla(Trabajador[] datos) {
        model.setRowCount(0);
        if (datos == null) return;
        for (Trabajador t : datos) {
            if (t != null) model.addRow(t.toRow());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
