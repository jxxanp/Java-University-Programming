package com.examen.empleados;

public class MedioTiempo extends Empleado {
    private int horasSemana;

    public MedioTiempo(String id, String nombre, String departamento, double salario, int horasSemana) {
        super(id, nombre, departamento, salario);
        this.horasSemana = horasSemana;
    }

    public int getHorasSemana() {
        return horasSemana;
    }

    @Override
    public String tipo() { return "Medio Tiempo"; }

    @Override
    public String datosExtra() {
        return "Horas/Sem: " + horasSemana;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Medio Tiempo | " + datosExtra();
    }
}
