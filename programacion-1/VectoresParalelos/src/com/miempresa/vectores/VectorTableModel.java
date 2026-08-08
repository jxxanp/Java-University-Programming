package com.miempresa.vectores;

import javax.swing.table.AbstractTableModel;

public class VectorTableModel extends AbstractTableModel {
    private final String[] columnas = {"Índice","Código","Nombre","Precio","Cantidad","Total (P*C)"};

    @Override
    public int getRowCount() { return DataStore.vectorObjetos.length; }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public String getColumnName(int column) { return columnas[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VectorObjeto v = DataStore.vectorObjetos[rowIndex];
        switch (columnIndex) {
            case 0: return rowIndex;
            case 1: return v == null ? "-" : v.getCodigo();
            case 2: return v == null ? "" : v.getNombre();
            case 3: return v == null ? "" : v.getPrecio();
            case 4: return v == null ? "" : v.getCantidad();
            case 5: return DataStore.totales[rowIndex];
            default: return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

    public void refresh() { fireTableDataChanged(); }
}
