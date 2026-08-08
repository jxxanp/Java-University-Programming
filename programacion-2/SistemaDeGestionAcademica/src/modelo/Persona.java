package modelo;

public abstract class Persona extends BusinessEntityBase {
    // Atributos adicionales de Persona
    protected String fechaNacimiento;
    protected String telefono;
    protected String direccion;
    
    // Constructor vacío
    public Persona() {
        super();
        this.fechaNacimiento = "";
        this.telefono = "";
        this.direccion = "";
    }
    
    // Constructor con parámetros
    public Persona(String apellido, String correo, String nombre,
                   String fechaNacimiento, String telefono, String direccion) {
        super(apellido, correo, nombre);
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    // Getters
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    // Setters
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    // Sobrescribir mostrarInformacion
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n" +
               "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Dirección: " + direccion;
    }
}