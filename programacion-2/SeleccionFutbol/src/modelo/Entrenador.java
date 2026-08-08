package modelo;

public class Entrenador extends SeleccionFutbol {
    // Atributo específico
    private int idFederacion;
    
    // Constructor vacío
    public Entrenador() {
        super();
    }
    
    // Constructor completo
    public Entrenador(int id, String nombre, String apellidos, int edad,
                      int idFederacion) {
        super(id, nombre, apellidos, edad);
        this.idFederacion = idFederacion;
    }
    
    // Getters y Setters
    public int getIdFederacion() {
        return idFederacion;
    }
    
    public void setIdFederacion(int idFederacion) {
        this.idFederacion = idFederacion;
    }
    
    // Implementación de métodos abstractos
    @Override
    public void viajar() {
        System.out.println("🎓 El entrenador " + nombre + " está viajando con la delegación");
    }
    
    @Override
    public void concentrarse() {
        System.out.println("🎓 El entrenador " + nombre + " está concentrado preparando estrategias");
    }
    
    @Override
    public void entrenar() {
        System.out.println("🎓 El entrenador " + nombre + " está dirigiendo el entrenamiento");
    }
    
    @Override
    public void partidoFutbol() {
        System.out.println("🎓 El entrenador " + nombre + " está dirigiendo el partido desde el banquillo");
    }
    
    // Método específico
    public void planificarEntrenamiento() {
        System.out.println("🎓 El entrenador " + nombre + " está planificando el entrenamiento");
    }
    
    // Sobrescribir mostrarInfo
    @Override
    public String mostrarInfo() {
        return String.format(
            "═══════════ 🎓 ENTRENADOR 🎓 ═══════════\n" +
            "%s\n" +
            "ID Federación: %d\n" +
            "════════════════════════════════════",
            super.mostrarInfo(), idFederacion
        );
    }
}