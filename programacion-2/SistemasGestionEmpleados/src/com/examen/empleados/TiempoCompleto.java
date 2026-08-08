package com.examen.empleados;

public class TiempoCompleto extends Empleado {
    private int diasVacaciones;

    public TiempoCompleto(String id, String nombre, String departamento, double salario, int diasVacaciones) {
        super(id, nombre, departamento, salario);
        this.diasVacaciones = diasVacaciones;
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    @Override
    public String tipo() { return "Tiempo Completo"; }

    @Override
    public String datosExtra() {
        return "Vacaciones(días): " + diasVacaciones;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Tiempo Completo | " + datosExtra();
    }
}
