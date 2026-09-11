public class Anfibio extends Robot implements Rastreable, Arrastrable, Sumergible {

    public Anfibio(String id, int bateria) {
        super(id, bateria);
    }

    @Override
    public Resultado rastrear(String zona) {
        return new Resultado(getId(), zona, "Termico 2");
    }
}