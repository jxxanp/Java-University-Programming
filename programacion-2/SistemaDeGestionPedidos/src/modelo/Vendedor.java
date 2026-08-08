package modelo;

import java.util.ArrayList;

public class Vendedor {
    private String id;
    private String nombre;
    private ArrayList<ClienteCorporativo> cartera;

    public Vendedor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.cartera = new ArrayList<>();
    }
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public ArrayList<ClienteCorporativo> getCartera() { return cartera; }
    public void agregarCliente(ClienteCorporativo c) {
        if(!cartera.contains(c)) {
            cartera.add(c);
            c.setVendedor(this);
        }
    }
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
