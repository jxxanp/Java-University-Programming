package banco;

public class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;
    private double tasaInteresAnual;
    private double limiteRetiroDiario;
    private double saldoMinimo;
    private double tasaInversion;
    private double retiroAcumuladoDia;

    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldo,
                          double tasaInteresAnual, double limiteRetiroDiario,
                          double saldoMinimo, double tasaInversion) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.tasaInteresAnual = tasaInteresAnual;
        this.limiteRetiroDiario = limiteRetiroDiario;
        this.saldoMinimo = saldoMinimo;
        this.tasaInversion = tasaInversion;
        this.retiroAcumuladoDia = 0;
    }

    // Getters y Setters
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public double getTasaInteresAnual() { return tasaInteresAnual; }
    public void setTasaInteresAnual(double tasaInteresAnual) { this.tasaInteresAnual = tasaInteresAnual; }

    public double getLimiteRetiroDiario() { return limiteRetiroDiario; }
    public void setLimiteRetiroDiario(double limiteRetiroDiario) { this.limiteRetiroDiario = limiteRetiroDiario; }

    public double getSaldoMinimo() { return saldoMinimo; }
    public void setSaldoMinimo(double saldoMinimo) { this.saldoMinimo = saldoMinimo; }

    public double getTasaInversion() { return tasaInversion; }
    public void setTasaInversion(double tasaInversion) { this.tasaInversion = tasaInversion; }

    public double getRetiroAcumuladoDia() { return retiroAcumuladoDia; }
    public void resetRetiroAcumuladoDia() { this.retiroAcumuladoDia = 0; }

    // Métodos principales
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if ((retiroAcumuladoDia + monto) > limiteRetiroDiario) return false;
        if ((saldo - monto) < 0) return false;
        saldo -= monto;
        retiroAcumuladoDia += monto;
        return true;
    }

    public boolean transferir(CuentaBancaria destino, double monto) {
        if (retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    public double calcularInteresMensual() {
        double tasaMensual = tasaInteresAnual / 12.0;
        double interes = saldo * tasaMensual;
        saldo += interes;
        return interes;
    }

    public double simularInversion(double monto, int meses) {
        double r = tasaInversion / 12.0;
        return monto * Math.pow(1 + r, meses);
    }

    public void aplicarCargosPorSaldoBajo() {
        if (saldo < saldoMinimo) {
            double cargo = 12000.0;
            saldo -= cargo;
        }
    }

    public String calcularAmortizacionPrestamo(double monto, double tasaAnual, int meses) {
        StringBuilder sb = new StringBuilder();
        double r = tasaAnual / 12.0;
        double cuota = monto * (r / (1 - Math.pow(1 + r, -meses)));

        double saldoRestante = monto;
        sb.append(String.format("Cuota mensual: %.2f\n", cuota));
        sb.append("Mes\tInterés\tAmortización\tSaldo\n");
        for (int i = 1; i <= meses; i++) {
            double interes = saldoRestante * r;
            double amortizacion = cuota - interes;
            saldoRestante -= amortizacion;
            sb.append(String.format("%d\t%.2f\t%.2f\t%.2f\n", i, interes, amortizacion, saldoRestante));
        }
        return sb.toString();
    }

    public String verEstadoCuenta() {
        return String.format("Cuenta: %s\nTitular: %s\nSaldo: %.2f\n",
                numeroCuenta, titular, saldo);
    }
}
