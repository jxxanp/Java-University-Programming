package modelo;

public class Alumno extends Persona {
    // Atributos específicos de Alumno
    private String m_FechaDeNacimiento;
    private String m_IdAlumno;
    
    // Constructor vacío
    public Alumno() {
        super();
        this.m_FechaDeNacimiento = "";
        this.m_IdAlumno = "";
    }
    
    // Constructor con parámetros
    public Alumno(String apellido, String correo, String nombre,
                  String fechaNacimiento, String telefono, String direccion,
                  String fechaDeNacimiento, String idAlumno) {
        super(apellido, correo, nombre, fechaNacimiento, telefono, direccion);
        this.m_FechaDeNacimiento = fechaDeNacimiento;
        this.m_IdAlumno = idAlumno;
    }
    
    // Getters específicos
    public String getFechaDeNacimiento() {
        return m_FechaDeNacimiento;
    }
    
    public String getIdAlumno() {
        return m_IdAlumno;
    }
    
    // Setters específicos
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.m_FechaDeNacimiento = fechaDeNacimiento;
    }
    
    public void setIdAlumno(String idAlumno) {
        this.m_IdAlumno = idAlumno;
    }
    
    // Implementación del método abstracto toValidate
    @Override
    public String toValidate() {
        StringBuilder validacion = new StringBuilder();
        validacion.append("=== VALIDACIÓN DE ALUMNO ===\n");
        
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
        
        if (m_IdAlumno == null || m_IdAlumno.trim().isEmpty()) {
            validacion.append("❌ ID de Alumno es obligatorio\n");
        } else {
            validacion.append("✓ ID de Alumno válido\n");
        }
        
        return validacion.toString();
    }
    
    // Método para verificar si el alumno es válido
    public boolean esValido() {
        return m_Nombre != null && !m_Nombre.trim().isEmpty() &&
               m_Apellido != null && !m_Apellido.trim().isEmpty() &&
               m_CorreoElectronico != null && !m_CorreoElectronico.trim().isEmpty() &&
               m_CorreoElectronico.contains("@") &&
               m_IdAlumno != null && !m_IdAlumno.trim().isEmpty();
    }
    
    // Método idAlumno (según diagrama)
    public String idAlumno() {
        return m_IdAlumno;
    }
    
    // Método fechaDeNacimiento (según diagrama)
    public String fechaDeNacimiento() {
        return m_FechaDeNacimiento;
    }
    
    // Sobrescribir mostrarInformacion
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n" +
               "--- Información de Alumno ---\n" +
               "ID Alumno: " + m_IdAlumno + "\n" +
               "Fecha de Nacimiento (Alumno): " + m_FechaDeNacimiento;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Alumno - " + m_IdAlumno + "]";
    }
}