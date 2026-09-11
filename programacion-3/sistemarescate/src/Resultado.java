public class Resultado {
    private String robotId;
    private String zona;
    private String sensor;

    public Resultado(String robotId, String zona, String sensor) {
        this.robotId = robotId;
        this.zona = zona;
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "El robot con id" + robotId + "busco en" + zona + "con el sensor" + sensor;
    }
}



