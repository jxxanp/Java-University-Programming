package modelo;

public class Instructor extends Persona {
    // Atributos específicos de Instructor
    private String m_FechaIngreso;
    private String m_IdInstructor;
    
    // Constructor vacío
    public Instructor() {
        super();
        this.m_FechaIngreso = "";
        this.m_IdInstructor = "";
    }
    
    // Constructor con parámetros
    public Instructor(String apellido, String correo, String nombre,
                      String fechaNacimiento, String telefono, String direccion,
                      String fechaIngreso, String idInstructor) {
        super(apellido, correo, nombre, fechaNacimiento, telefono, direccion);
        this.m_FechaIngreso = fechaIngreso;
        this.m_IdInstructor = idInstructor;
    }
    
    // Getters específicos
    public String getFechaIngreso() {
        return m_FechaIngreso;
    }
    
    public String getIdInstructor() {
        return m_IdInstructor;
    }
    
    // Setters específicos
    public void setFechaIngreso(String fechaIngreso) {
        this.m_FechaIngreso = fechaIngreso;
    }
    
    public void setIdInstructor(String idInstructor) {
        this.m_IdInstructor = idInstructor;
    }
    
    // Implementación del método abstracto toValidate
    @Override
    public String toValidate() {
        StringBuilder validacion = new StringBuilder();
        validacion.append("=== VALIDACIÓN DE INSTRUCTOR ===\n");
        
        // Validar campos obligatorios
        if (m_Nombre == null || m_Nombre.trim().isEmpty()) {
            validacion.append("❌ Nombre es obligatorio\n");
        } else {
            validacion.append("✓ Nombre válido\n");
        }
        
        if (m_Apellido == null || m_Apellido.trim().isEmpty()) {
            validacion.append("❌ Apellido es obligatorio\n");
        } else {
            validacion.append("✓ Apellido válido\n");
        }
        
        if (m_CorreoElectronico == null || m_CorreoElectronico.trim().isEmpty()) {
            validacion.append("❌ Correo electrónico es obligatorio\n");
        } else if (!m_CorreoElectronico.contains("@")) {
            validacion.append("❌ Correo electrónico inválido\n");
        } else {
            validacion.append("✓ Correo electrónico válido\n");
        }
        
        if (m_IdInstructor == null || m_IdInstructor.trim().isEmpty()) {
            validacion.append("❌ ID de Instructor es obligatorio\n");
        } else {
            validacion.append("✓ ID de Instructor válido\n");
        }
        
        if (m_FechaIngreso == null || m_FechaIngreso.trim().isEmpty()) {
            validacion.append("❌ Fecha de Ingreso es obligatoria\n");
        } else {
            validacion.append("✓ Fecha de Ingreso válida\n");
        }
        
        return validacion.toString();
    }
    
    // Método para verificar si el instructor es válido
    public boolean esValido() {
        return m_Nombre != null && !m_Nombre.trim().isEmpty() &&
               m_Apellido != null && !m_Apellido.trim().isEmpty() &&
               m_CorreoElectronico != null && !m_CorreoElectronico.trim().isEmpty() &&
               m_CorreoElectronico.contains("@") &&
               m_IdInstructor != null && !m_IdInstructor.trim().isEmpty() &&
               m_FechaIngreso != null && !m_FechaIngreso.trim().isEmpty();
    }
    
    // Método idInstructor (según diagrama)
    public String idInstructor() {
        return m_IdInstructor;
    }
    
    // Método fechaIngreso (según diagrama)
    public String fechaIngreso() {
        return m_FechaIngreso;
    }
    
    // Sobrescribir mostrarInformacion
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n" +
               "--- Información de Instructor ---\n" +
               "ID Instructor: " + m_IdInstructor + "\n" +
               "Fecha de Ingreso: " + m_FechaIngreso;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Instructor - " + m_IdInstructor + "]";
    }
}