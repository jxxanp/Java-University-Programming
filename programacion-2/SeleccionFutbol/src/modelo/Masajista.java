package modelo;

public class Masajista extends SeleccionFutbol {
    // Atributos específicos
    private String titulacion;
    private int aniosExperiencia;
    
    // Constructor vacío
    public Masajista() {
        super();
    }
    
    // Constructor completo
    public Masajista(int id, String nombre, String apellidos, int edad,
                     String titulacion, int aniosExperiencia) {
        super(id, nombre, apellidos, edad);
        this.titulacion = titulacion;
        this.aniosExperiencia = aniosExperiencia;
    }
    
    // Getters y Setters
    public String getTitulacion() {
        return titulacion;
    }
    
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
    
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }
    
    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
    
    // Implementación de métodos abstractos
    @Override
    public void viajar() {
        System.out.println("💆 El masajista " + nombre + " está viajando con el equipo médico");
    }
    
    @Override
    public void concentrarse() {
        System.out.println("💆 El masajista " + nombre + " está concentrado preparando el material");
    }
    
    @Override
    public void entrenar() {
        System.out.println("💆 El masajista " + nombre + " está atendiendo a los jugadores durante el entrenamiento");
    }
    
    @Override
    public void partidoFutbol() {
        System.out.println("💆 El masajista " + nombre + " está atento en el banquillo para dar asistencia");
    }
    
    // Método específico
    public void darMasaje() {
        System.out.println("💆 El masajista " + nombre + " está dando un masaje terapéutico");
    }
    
    // Sobrescribir mostrarInfo
    @Override
    public String mostrarInfo() {
        return String.format(
            "═══════════ 💆 MASAJISTA 💆 ═══════════\n" +
            "%s\n" +
            "Titulación: %s\n" +
            "Años de Experiencia: %d\n" +
            "════════════════════════════════════",
            super.mostrarInfo(), titulacion, aniosExperiencia
        );
    }
}