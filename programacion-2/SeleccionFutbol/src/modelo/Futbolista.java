package modelo;

public class Futbolista extends SeleccionFutbol {
    // Atributos específicos
    private int dorsal;
    private String demarcacion; // Portero, Defensa, Centrocampista, Delantero
    
    // Constructor vacío
    public Futbolista() {
        super();
    }
    
    // Constructor completo
    public Futbolista(int id, String nombre, String apellidos, int edad,
                      int dorsal, String demarcacion) {
        super(id, nombre, apellidos, edad);
        this.dorsal = dorsal;
        this.demarcacion = demarcacion;
    }
    
    // Getters y Setters
    public int getDorsal() {
        return dorsal;
    }
    
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    
    public String getDemarcacion() {
        return demarcacion;
    }
    
    public void setDemarcacion(String demarcacion) {
        this.demarcacion = demarcacion;
    }
    
    // Implementación de métodos abstractos
    @Override
    public void viajar() {
        System.out.println("⚽ El futbolista " + nombre + " está viajando con el equipo");
    }
    
    @Override
    public void concentrarse() {
        System.out.println("⚽ El futbolista " + nombre + " está concentrado para el partido");
    }
    
    @Override
    public void entrenar() {
        System.out.println("⚽ El futbolista " + nombre + " está entrenando en el campo");
    }
    
    @Override
    public void partidoFutbol() {
        System.out.println("⚽ El futbolista " + nombre + " está jugando el partido");
    }
    
    // Método específico
    public void entrevista() {
        System.out.println("⚽ El futbolista " + nombre + " está dando una entrevista");
    }
    
    // Sobrescribir mostrarInfo
    @Override
    public String mostrarInfo() {
        return String.format(
            "═══════════ ⚽ FUTBOLISTA ⚽ ═══════════\n" +
            "%s\n" +
            "Dorsal: #%d\n" +
            "Demarcación: %s\n" +
            "════════════════════════════════════",
            super.mostrarInfo(), dorsal, demarcacion
        );
    }
}