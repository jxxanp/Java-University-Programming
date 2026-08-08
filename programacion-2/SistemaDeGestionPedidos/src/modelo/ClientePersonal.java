package modelo;

public class ClientePersonal extends Cliente {
    private String tarjetaCredito; // numero simplificado

    public ClientePersonal(String id, String nombre, String direccion, String telefono, String tarjetaCredito) {
        super(id, nombre, direccion, telefono);
        this.tarjetaCredito = tarjetaCredito;
    }
    public String getTarjetaCredito() { return tarjetaCredito; }
    public void setTarjetaCredito(String tarjetaCredito) { this.tarjetaCredito = tarjetaCredito; }

    @Override
    public String medioPagoDetalle() {
        return "Tarjeta Crédito: " + (tarjetaCredito != null ? tarjetaCredito : "No especificada");
    }
}
