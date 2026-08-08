package com.miempresa.trabajadores;

public class Trabajador {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String departamento;
    private int aniosTrabajados;
    private String cargo;
    private double salario;

    public Trabajador(String codigo, String nombres, String apellidos,
                      String departamento, int aniosTrabajados, String cargo, double salario) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.departamento = departamento;
        this.aniosTrabajados = aniosTrabajados;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDepartamento() { return departamento; }
    public int getAniosTrabajados() { return aniosTrabajados; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }

    public void setSalario(double salario) { this.salario = salario; }

    // Para mostrar en JTable
    public Object[] toRow() {
        return new Object[] { codigo, nombres, apellidos, departamento, aniosTrabajados, cargo, salario };
    }
}
