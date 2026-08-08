package modelo;

public abstract class BusinessEntityBase {
    protected String m_Apellido;
    protected String m_CorreoElectronico;
    protected String m_Nombre;
    
    public BusinessEntityBase() {
        this.m_Apellido = "";
        this.m_CorreoElectronico = "";
        this.m_Nombre = "";
    }
    
    public BusinessEntityBase(String apellido, String correo, String nombre) {
        this.m_Apellido = apellido;
        this.m_CorreoElectronico = correo;
        this.m_Nombre = nombre;
    }
    
    public String getApellido() { return m_Apellido; }
    public void setApellido(String apellido) { this.m_Apellido = apellido; }
    public String getCorreoElectronico() { return m_CorreoElectronico; }
    public void setCorreoElectronico(String correo) { this.m_CorreoElectronico = correo; }
    public String getNombre() { return m_Nombre; }
    public void setNombre(String nombre) { this.m_Nombre = nombre; }
    
    public abstract String toValidate();
    
    public String mostrarInformacion() {
        return "Nombre: " + m_Nombre + "\n" +
               "Apellido: " + m_Apellido + "\n" +
               "Correo: " + m_CorreoElectronico;
    }
    
    @Override
    public String toString() {
        return m_Nombre + " " + m_Apellido;
    }
}