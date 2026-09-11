public abstract class Robot {
    private final String id;
    private int bateria;

    public Robot (String identificador , int porcentajeBateria) {
        this.id = identificador;
        this.bateria = porcentajeBateria;
    }

    public String getId() {
        return id;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}
