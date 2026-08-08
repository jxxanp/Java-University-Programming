package com.examen.empleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmRegistrarEmpleado extends JDialog {
    private JTextField txtId, txtNombre, txtDepartamento, txtSalario, txtExtra;
    private JRadioButton rbtnTiempoCompleto, rbtnMedioTiempo;
    private JButton btnGuardar, btnCancelar;
    private GestorEmpleados gestor;

    public FrmRegistrarEmpleado(Frame owner, GestorEmpleados gestor) {
        super(owner, "Registrar Empleado", true);
        this.gestor = gestor;
        initComponents();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setSize(420, 320);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6,6,6,6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lId = new JLabel("ID:");
        JLabel lNombre = new JLabel("Nombre:");
        JLabel lDepto = new JLabel("Departamento:");
        JLabel lSal = new JLabel("Salario:");
        JLabel lExtra = new JLabel("Extra:");

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtDepartamento = new JTextField();
        txtSalario = new JTextField();
        txtExtra = new JTextField();

        rbtnTiempoCompleto = new JRadioButton("Tiempo Completo");
        rbtnMedioTiempo = new JRadioButton("Medio Tiempo");
        ButtonGroup g = new ButtonGroup();
        g.add(rbtnTiempoCompleto);
        g.add(rbtnMedioTiempo);
        rbtnTiempoCompleto.setSelected(true);

        // extra label changes depending on selection
        rbtnTiempoCompleto.addActionListener(e -> updateExtraLabel());
        rbtnMedioTiempo.addActionListener(e -> updateExtraLabel());

        gc.gridx = 0; gc.gridy = 0; p.add(lId, gc);
        gc.gridx = 1; gc.gridy = 0; p.add(txtId, gc);

        gc.gridx = 0; gc.gridy = 1; p.add(lNombre, gc);
        gc.gridx = 1; gc.gridy = 1; p.add(txtNombre, gc);

        gc.gridx = 0; gc.gridy = 2; p.add(lDepto, gc);
        gc.gridx = 1; gc.gridy = 2; p.add(txtDepartamento, gc);

        gc.gridx = 0; gc.gridy = 3; p.add(lSal, gc);
        gc.gridx = 1; gc.gridy = 3; p.add(txtSalario, gc);

        gc.gridx = 0; gc.gridy = 4; p.add(lExtra, gc);
        gc.gridx = 1; gc.gridy = 4; p.add(txtExtra, gc);

        gc.gridx = 0; gc.gridy = 5; p.add(rbtnTiempoCompleto, gc);
        gc.gridx = 1; gc.gridy = 5; p.add(rbtnMedioTiempo, gc);

        add(p, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        botones.add(btnGuardar);
        botones.add(btnCancelar);
        add(botones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> onGuardar());
        btnCancelar.addActionListener(e -> dispose());

        // Enter to save
        getRootPane().setDefaultButton(btnGuardar);
        updateExtraLabel();
    }

    private void updateExtraLabel() {
        if (rbtnTiempoCompleto.isSelected()) {
            // extra = dias de vacaciones
            txtExtra.setToolTipText("Días de vacaciones (entero)");
        } else {
            txtExtra.setToolTipText("Horas por semana (entero)");
        }
    }

    private void onGuardar() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String departamento = txtDepartamento.getText().trim();
        String salarioS = txtSalario.getText().trim();
        String extraS = txtExtra.getText().trim();

        // validaciones simples
        if (id.isEmpty() || nombre.isEmpty() || departamento.isEmpty() || salarioS.isEmpty() || extraS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double salario;
        int extra;
        try {
            salario = Double.parseDouble(salarioS);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario debe ser número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            extra = Integer.parseInt(extraS);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Campo extra debe ser entero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // verificar ID único
        if (gestor.buscar(id) != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un empleado con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Empleado em;
        if (rbtnTiempoCompleto.isSelected()) {
            em = new TiempoCompleto(id, nombre, departamento, salario, extra);
        } else {
            em = new MedioTiempo(id, nombre, departamento, salario, extra);
        }

        boolean ok = gestor.agregarEmpleado(em);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Empleado registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
