package modelo;

public abstract class Cliente {
    protected String id;
    protected String nombre;
    protected String direccion;
    protected String telefono;

    public Cliente(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    // Método abstracto que puede usarse para validación específica del tipo de cliente
    public abstract String medioPagoDetalle();

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
