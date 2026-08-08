package modelo;

public class Empleado extends Persona {
    private String codigoEmpleado;
    private String cargo;
    private double salario;
    private String departamento;
    private String fechaContratacion;
    
    public Empleado() {
        super();
        this.codigoEmpleado = "";
        this.cargo = "";
        this.salario = 0.0;
        this.departamento = "";
        this.fechaContratacion = "";
    }
    
    public Empleado(String nombre, String apellido, int edad, 
                    String direccion, String telefono,
                    String codigoEmpleado, String cargo, double salario,
                    String departamento, String fechaContratacion) {
        super(nombre, apellido, edad, direccion, telefono);
        this.codigoEmpleado = codigoEmpleado;
        this.cargo = cargo;
        this.salario = salario;
        this.departamento = departamento;
        this.fechaContratacion = fechaContratacion;
    }
    
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(String fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    
    public double calcularSalarioAnual() {
        return salario * 12;
    }
    
    public void aumentarSalario(double porcentaje) {
        salario = salario + (salario * porcentaje / 100);
    }
    
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n" +
               "--- Información de Empleado ---\n" +
               "Código: " + codigoEmpleado + "\n" +
               "Cargo: " + cargo + "\n" +
               "Salario: $" + String.format("%.2f", salario) + "\n" +
               "Departamento: " + departamento + "\n" +
               "Fecha de Contratación: " + fechaContratacion;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [" + cargo + " - " + codigoEmpleado + "]";
    }
}