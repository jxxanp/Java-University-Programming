package modelo;

public class Cliente extends Persona {
    private String codigoCliente;
    private String email;
    private String tipoCliente;
    private double creditoDisponible;
    private int puntosAcumulados;
    
    public Cliente() {
        super();
        this.codigoCliente = "";
        this.email = "";
        this.tipoCliente = "Regular";
        this.creditoDisponible = 0.0;
        this.puntosAcumulados = 0;
    }
    
    public Cliente(String nombre, String apellido, int edad,
                   String direccion, String telefono,
                   String codigoCliente, String email, String tipoCliente,
                   double creditoDisponible, int puntosAcumulados) {
        super(nombre, apellido, edad, direccion, telefono);
        this.codigoCliente = codigoCliente;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.creditoDisponible = creditoDisponible;
        this.puntosAcumulados = puntosAcumulados;
    }
    
    public String getCodigoCliente() { return codigoCliente; }
    public void setCodigoCliente(String codigoCliente) { this.codigoCliente = codigoCliente; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    public double getCreditoDisponible() { return creditoDisponible; }
    public void setCreditoDisponible(double creditoDisponible) { this.creditoDisponible = creditoDisponible; }
    public int getPuntosAcumulados() { return puntosAcumulados; }
    public void setPuntosAcumulados(int puntosAcumulados) { this.puntosAcumulados = puntosAcumulados; }
    
    public void agregarPuntos(int puntos) {
        this.puntosAcumulados += puntos;
        verificarTipoCliente();
    }
    
    public boolean canjearPuntos(int puntos) {
        if (puntosAcumulados >= puntos) {
            puntosAcumulados -= puntos;
            return true;
        }
        return false;
    }
    
    public void agregarCredito(double monto) {
        this.creditoDisponible += monto;
    }
    
    public boolean usarCredito(double monto) {
        if (creditoDisponible >= monto) {
            creditoDisponible -= monto;
            return true;
        }
        return false;
    }
    
    private void verificarTipoCliente() {
        if (puntosAcumulados >= 1000) {
            tipoCliente = "VIP";
        } else if (puntosAcumulados >= 500) {
            tipoCliente = "Regular";
        } else {
            tipoCliente = "Nuevo";
        }
    }
    
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n" +
               "--- Información de Cliente ---\n" +
               "Código: " + codigoCliente + "\n" +
               "Email: " + email + "\n" +
               "Tipo: " + tipoCliente + "\n" +
               "Crédito Disponible: $" + String.format("%.2f", creditoDisponible) + "\n" +
               "Puntos Acumulados: " + puntosAcumulados;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [" + tipoCliente + " - " + codigoCliente + "]";
    }
}