package com.miempresa.trabajadores;

public class VectorTrabajadores {
    private static Trabajador[] vec;
    private static int capacidad;

    public VectorTrabajadores(int tam) {
        capacidad = tam;
        vec = new Trabajador[capacidad];
    }

    public boolean agregar(Trabajador t) {
        // validar codigo unico
        if (buscarIndexPorCodigo(t.getCodigo()) != -1) return false;
        for (int i = 0; i < capacidad; i++) {
            if (vec[i] == null) {
                vec[i] = t;
                return true;
            }
        }
        return false; // lleno
    }

    public int buscarIndexPorCodigo(String codigo) {
        for (int i = 0; i < capacidad; i++) {
            if (vec[i] != null && vec[i].getCodigo().equals(codigo)) return i;
        }
        return -1;
    }

    public Trabajador buscarPorCodigo(String codigo) {
        int idx = buscarIndexPorCodigo(codigo);
        return idx == -1 ? null : vec[idx];
    }

    public boolean eliminarPorCodigo(String codigo) {
        int idx = buscarIndexPorCodigo(codigo);
        if (idx == -1) return false;
        vec[idx] = null; // deja la posición vacía
        return true;
    }

    // Ordenamiento por burbuja descendente por codigo (tratamos codigo como string; usamos compareTo)
    public void ordenarBurbujaDescPorCodigo() {
        // Hacemos una copia para ordenar sin perder nulls al final.
        for (int i = 0; i < capacidad - 1; i++) {
            for (int j = 0; j < capacidad - 1 - i; j++) {
                Trabajador a = vec[j];
                Trabajador b = vec[j+1];
                // Regla: nulls van al final (consideramos null < cualquier valor)
                if (a == null && b == null) continue;
                if (a == null && b != null) {
                    // swap para llevar null a la derecha: null < b => swap para poner b antes
                    vec[j] = vec[j+1];
                    vec[j+1] = a;
                } else if (a != null && b != null) {
                    // comparar codigo en orden descendente
                    if (a.getCodigo().compareTo(b.getCodigo()) < 0) {
                        vec[j] = b;
                        vec[j+1] = a;
                    }
                }
            }
        }
    }

    public Trabajador getMayorSalario() {
        Trabajador mayor = null;
        for (Trabajador t : vec) {
            if (t == null) continue;
            if (mayor == null || t.getSalario() > mayor.getSalario()) mayor = t;
        }
        return mayor;
    }

    public Trabajador getMenorSalario() {
        Trabajador menor = null;
        for (Trabajador t : vec) {
            if (t == null) continue;
            if (menor == null || t.getSalario() < menor.getSalario()) menor = t;
        }
        return menor;
    }

    public double promedioSalarios() {
        double suma = 0;
        int count = 0;
        for (Trabajador t : vec) {
            if (t == null) continue;
            suma += t.getSalario();
            count++;
        }
        return count == 0 ? 0 : suma / count;
    }

    public Trabajador[] getTodos() {
        // devuelve el arreglo original (puede contener nulls)
        return vec;
    }

    public Trabajador[] getPorDebajoDelPromedio() {
        double prom = promedioSalarios();
        java.util.List<Trabajador> lista = new java.util.ArrayList<>();
        for (Trabajador t : vec) {
            if (t != null && t.getSalario() < prom) lista.add(t);
        }
        return lista.toArray(new Trabajador[0]);
    }

    public Trabajador[] getPorEncimaDelPromedio() {
        double prom = promedioSalarios();
        java.util.List<Trabajador> lista = new java.util.ArrayList<>();
        for (Trabajador t : vec) {
            if (t != null && t.getSalario() > prom) lista.add(t);
        }
        return lista.toArray(new Trabajador[0]);
    }
}
