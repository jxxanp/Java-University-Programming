package modelo;

public abstract class SeleccionFutbol {
    // Atributos protegidos
    protected int id;
    protected String nombre;
    protected String apellidos;
    protected int edad;
    
    // Constructor vacío
    public SeleccionFutbol() {
    }
    
    // Constructor con parámetros
    public SeleccionFutbol(int id, String nombre, String apellidos, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    // Métodos abstractos que deben implementar las subclases
    public abstract void viajar();
    public abstract void concentrarse();
    public abstract void entrenar();
    public abstract void partidoFutbol();
    
    // Método para mostrar información básica
    public String mostrarInfo() {
        return String.format(
            "ID: %d\n" +
            "Nombre: %s %s\n" +
            "Edad: %d años",
            id, nombre, apellidos, edad
        );
    }
}