public class DroneRescate extends Robot implements Rastreable {
    public DroneRescate (String id, int bateria) {
        super(id, bateria);
    }

    @Override
    public Resultado rastrear(String zona) {
        return new Resultado(getId(), "norte", "termico");
    }
}

