package com.examen.empleados;

import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    private final ArrayList<Empleado> lista;

    public GestorEmpleados() {
        lista = new ArrayList<>();
    }

    public boolean agregarEmpleado(Empleado e) {
        if (buscar(e.getId()) != null) {
            return false; // ya existe id
        }
        lista.add(e);
        return true;
    }

    public List<Empleado> getTodos() {
        return new ArrayList<>(lista);
    }

    public Empleado buscar(String id) {
        for (Empleado e : lista) {
            if (e.getId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }

    public int contarPorDepartamento(String depto, String tipoFiltro) {
        // tipoFiltro: "Tiempo Completo", "Medio Tiempo" o null => contar todos del depto
        int c = 0;
        for (Empleado e : lista) {
            if (!e.getDepartamento().equalsIgnoreCase(depto)) continue;
            if (tipoFiltro == null) { c++; continue; }
            if (e.tipo().equalsIgnoreCase(tipoFiltro)) c++;
        }
        return c;
    }

    public Empleado mayorSalario() {
        if (lista.isEmpty()) return null;
        Empleado mayor = lista.get(0);
        for (Empleado e : lista) {
            if (e.getSalario() > mayor.getSalario()) mayor = e;
        }
        return mayor;
    }
}
