package modelo;

public class MedioTiempo extends Empleado {
    // Atributo específico
    private int horasTrabajadasPorSemana;
    
    // Constructor vacío
    public MedioTiempo() {
        super();
    }
    
    // Constructor con parámetros
    public MedioTiempo(String id, String nombre, String departamento, 
                       double salario, int horasTrabajadasPorSemana) {
        super(id, nombre, departamento, salario);
        this.horasTrabajadasPorSemana = horasTrabajadasPorSemana;
    }
    
    // Getter y Setter
    public int getHorasTrabajadasPorSemana() {
        return horasTrabajadasPorSemana;
    }
    
    public void setHorasTrabajadasPorSemana(int horasTrabajadasPorSemana) {
        this.horasTrabajadasPorSemana = horasTrabajadasPorSemana;
    }
    
    // Sobrescritura del método mostrarInformacion
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Medio Tiempo");
        System.out.println("Horas Trabajadas por Semana: " + horasTrabajadasPorSemana);
    }
    
    // Método para obtener el tipo de empleado
    public String getTipo() {
        return "Medio Tiempo";
    }
}