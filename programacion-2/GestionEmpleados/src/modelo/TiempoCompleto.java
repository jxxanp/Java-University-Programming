package modelo;

public class TiempoCompleto extends Empleado {
    // Atributo específico
    private int diasVacaciones;
    
    // Constructor vacío
    public TiempoCompleto() {
        super();
    }
    
    // Constructor con parámetros
    public TiempoCompleto(String id, String nombre, String departamento, 
                          double salario, int diasVacaciones) {
        super(id, nombre, departamento, salario);
        this.diasVacaciones = diasVacaciones;
    }
    
    // Getter y Setter
    public int getDiasVacaciones() {
        return diasVacaciones;
    }
    
    public void setDiasVacaciones(int diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }
    
    // Sobrescritura del método mostrarInformacion
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Tiempo Completo");
        System.out.println("Días de Vacaciones: " + diasVacaciones);
    }
    
    // Método para obtener el tipo de empleado
    public String getTipo() {
        return "Tiempo Completo";
    }
}