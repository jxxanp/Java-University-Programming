package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Pedido {
    private String id;
    private Cliente cliente;
    private Date fecha;
    private ArrayList<LineaPedido> lineas;

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = new Date();
        this.lineas = new ArrayList<>();
    }
    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Date getFecha() { return fecha; }
    public ArrayList<LineaPedido> getLineas() { return lineas; }
    public void agregarLinea(LineaPedido lp) { lineas.add(lp); }
    public double getTotal() {
        double t = 0;
        for (LineaPedido lp : lineas) t += lp.getSubtotal();
        return t;
    }
    public String getFechaFormateada() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(fecha);
    }
    @Override
    public String toString() {
        return "Pedido " + id + " - Cliente: " + cliente.getNombre() + " - Total: $" + getTotal();
    }
}
