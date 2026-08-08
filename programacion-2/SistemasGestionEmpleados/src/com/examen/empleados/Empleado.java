package com.examen.empleados;

public class Empleado {
    protected String id;
    protected String nombre;
    protected String departamento;
    protected double salario;

    public Empleado(String id, String nombre, String departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }

    public String tipo() { return "Empleado"; }

    public String datosExtra() { return ""; }

    public void mostrarInformacion() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Nombre: %s | Departamento: %s | Salario: %.2f", id, nombre, departamento, salario);
    }
}
