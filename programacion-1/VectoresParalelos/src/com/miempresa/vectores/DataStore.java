package com.miempresa.vectores;

public class DataStore {
    public static final int MAX = 50;
    public static VectorObjeto[] vectorObjetos = new VectorObjeto[MAX];
    public static double[] totales = new double[MAX];

    public static int primerIndiceLibre() {
        for (int i = 0; i < vectorObjetos.length; i++)
            if (vectorObjetos[i] == null) return i;
        return -1;
    }

    public static int buscarIndicePorCodigo(int codigo) {
        for (int i = 0; i < vectorObjetos.length; i++) {
            VectorObjeto v = vectorObjetos[i];
            if (v != null && v.getCodigo() == codigo) return i;
        }
        return -1;
    }

    public static String[] generarInformePrecio2500Cantidad5() {
        java.util.List<String> lista = new java.util.ArrayList<>();
        for (int i = 0; i < vectorObjetos.length; i++) {
            VectorObjeto v = vectorObjetos[i];
            if (v != null) {
                if (v.getPrecio() > 2500.0 && v.getCantidad() < 5) {
                    lista.add(v.getNombre());
                }
            }
        }
        return lista.toArray(new String[0]);
    }

    public static void calcularTotales() {
        for (int i = 0; i < vectorObjetos.length; i++) {
            VectorObjeto v = vectorObjetos[i];
            if (v != null) totales[i] = v.getPrecio() * v.getCantidad();
            else totales[i] = 0.0;
        }
    }

    public static String generarCSVdeTotales() {
        StringBuilder sb = new StringBuilder();
        sb.append("indice,codigo,nombre,total\n");
        for (int i = 0; i < vectorObjetos.length; i++) {
            VectorObjeto v = vectorObjetos[i];
            if (v != null) {
                sb.append(i).append(",")
                  .append(v.getCodigo()).append(",")
                  .append(v.getNombre()).append(",")
                  .append(totales[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
