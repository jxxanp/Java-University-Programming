package modelo;

public class ClienteCorporativo extends Cliente {
    private String contratoId;
    private double limiteCredito;
    private Vendedor vendedor; // el vendedor encargado

    public ClienteCorporativo(String id, String nombre, String direccion, String telefono,
                              String contratoId, double limiteCredito) {
        super(id, nombre, direccion, telefono);
        this.contratoId = contratoId;
        this.limiteCredito = limiteCredito;
    }
    public String getContratoId() { return contratoId; }
    public void setContratoId(String contratoId) { this.contratoId = contratoId; }
    public double getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }
    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    @Override
    public String medioPagoDetalle() {
        return "Contrato: " + contratoId + " - Límite: $" + limiteCredito +
               (vendedor != null ? " - Vendedor: " + vendedor.getNombre() : "");
    }
}
