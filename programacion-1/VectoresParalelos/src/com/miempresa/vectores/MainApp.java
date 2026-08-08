package com.miempresa.vectores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;

public class MainApp extends JFrame {
    private VectorTableModel tableModel;
    private JTable table;

    public MainApp() {
        setTitle("Vectores en Paralelo - Extensión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        tableModel = new VectorTableModel();
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnGenerarInforme = new JButton("Generar Informe (>2500 y <5)");
        JButton btnBuscar = new JButton("Buscar por Código");
        JButton btnCalcularTotales = new JButton("Calcular Totales");
        JButton btnExportTotales = new JButton("Exportar Totales CSV");

        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnGenerarInforme);
        botonesPanel.add(btnBuscar);
        botonesPanel.add(btnCalcularTotales);
        botonesPanel.add(btnExportTotales);

        add(scroll, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.NORTH);

        // Acciones
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnEditar.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro para editar.");
                return;
            }
            mostrarDialogoEditar(fila);
        });
        btnEliminar.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
                return;
            }
            DataStore.vectorObjetos[fila] = null;
            DataStore.totales[fila] = 0.0;
            tableModel.refresh();
        });

        btnGenerarInforme.addActionListener(e -> {
            String[] nombres = DataStore.generarInformePrecio2500Cantidad5();
            if (nombres.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay productos que cumplan la condición.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Productos con precio > 2500 y cantidad < 5:\n\n");
                for (String n : nombres) sb.append("- ").append(n).append("\n");
                JTextArea ta = new JTextArea(sb.toString());
                ta.setEditable(false);
                ta.setRows(Math.min(15, nombres.length+3));
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Informe", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                String s = JOptionPane.showInputDialog(this, "Ingrese el código del producto a buscar:");
                if (s == null) return;
                int codigo = Integer.parseInt(s.trim());
                int idx = DataStore.buscarIndicePorCodigo(codigo);
                if (idx == -1) {
                    JOptionPane.showMessageDialog(this, "Producto con código " + codigo + " no encontrado.");
                } else {
                    VectorObjeto v = DataStore.vectorObjetos[idx];
                    String info = String.format("Índice: %d\nCódigo: %d\nNombre: %s\nPrecio: %.2f\nCantidad: %d\nTotal: %.2f",
                            idx, v.getCodigo(), v.getNombre(), v.getPrecio(), v.getCantidad(), DataStore.totales[idx]);
                    JOptionPane.showMessageDialog(this, info, "Producto encontrado", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código inválido.");
            }
        });

        btnCalcularTotales.addActionListener(e -> {
            DataStore.calcularTotales();
            tableModel.refresh();
            JOptionPane.showMessageDialog(this, "Vector de totales calculado correctamente.");
        });

        btnExportTotales.addActionListener(e -> {
            DataStore.calcularTotales();
            String csv = DataStore.generarCSVdeTotales();
            JFileChooser fc = new JFileChooser();
            int op = fc.showSaveDialog(this);
            if (op == JFileChooser.APPROVE_OPTION) {
                try {
                    Files.writeString(fc.getSelectedFile().toPath(), csv);
                    JOptionPane.showMessageDialog(this, "CSV exportado correctamente.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
                }
            }
        });
    }

    private void mostrarDialogoAgregar() {
        if (DataStore.primerIndiceLibre() == -1) {
            JOptionPane.showMessageDialog(this, "El vector está lleno.");
            return;
        }
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtCantidad = new JTextField();
        Object[] campos = {"Código:", txtCodigo, "Nombre:", txtNombre, "Precio:", txtPrecio, "Cantidad:", txtCantidad};
        int opcion = JOptionPane.showConfirmDialog(this, campos, "Agregar producto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                int idx = DataStore.primerIndiceLibre();
                DataStore.vectorObjetos[idx] = new VectorObjeto(codigo, nombre, precio, cantidad);
                // actualizar totales para mantener consistencia
                DataStore.calcularTotales();
                tableModel.refresh();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        }
    }

    private void mostrarDialogoEditar(int fila) {
        VectorObjeto v = DataStore.vectorObjetos[fila];
        if (v == null) {
            JOptionPane.showMessageDialog(this, "La fila seleccionada está vacía.");
            return;
        }
        JTextField txtCodigo = new JTextField(String.valueOf(v.getCodigo()));
        txtCodigo.setEditable(false); // no editar código para mantener integridad
        JTextField txtNombre = new JTextField(v.getNombre());
        JTextField txtPrecio = new JTextField(String.valueOf(v.getPrecio()));
        JTextField txtCantidad = new JTextField(String.valueOf(v.getCantidad()));
        Object[] campos = {"Código:", txtCodigo, "Nombre:", txtNombre, "Precio:", txtPrecio, "Cantidad:", txtCantidad};
        int opcion = JOptionPane.showConfirmDialog(this, campos, "Editar producto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                v.setNombre(nombre);
                v.setPrecio(precio);
                v.setCantidad(cantidad);
                DataStore.calcularTotales();
                tableModel.refresh();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // ejemplo: llenar algunos datos de prueba (opcional)
        DataStore.vectorObjetos[0] = new VectorObjeto(1232, "Leche", 2500, 20);
        DataStore.vectorObjetos[1] = new VectorObjeto(2213, "Azúcar", 1400, 5);
        DataStore.vectorObjetos[2] = new VectorObjeto(3812, "Aceite", 5000, 8);
        DataStore.vectorObjetos[3] = new VectorObjeto(4115, "Huevos", 500, 30);
        DataStore.vectorObjetos[4] = new VectorObjeto(3214, "Carne", 6500, 4);
        DataStore.calcularTotales();

        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
