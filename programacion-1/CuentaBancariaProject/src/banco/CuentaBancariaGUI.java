package banco;

import javax.swing.*;
import java.awt.*;

public class CuentaBancariaGUI extends JFrame {

    private CuentaBancaria cuenta;
    private JTextField txtNumero, txtTitular, txtSaldo, txtTasa, txtLimite, txtSaldoMin, txtTasaInv;
    private JTextField txtMontoOperacion, txtMontoInv, txtMesesInv, txtMontoPrestamo, txtTasaPrestamo, txtMesesPrestamo;
    // campos para transferencia
    private JTextField txtCuentaDestino, txtMontoTransferir;

    private JTextArea txtReporte;

    public CuentaBancariaGUI() {
        setTitle("Cuenta Bancaria Integral");
        setSize(600, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Número de cuenta:"));
        txtNumero = new JTextField();
        panel.add(txtNumero);

        panel.add(new JLabel("Titular:"));
        txtTitular = new JTextField();
        panel.add(txtTitular);

        panel.add(new JLabel("Saldo inicial:"));
        txtSaldo = new JTextField();
        panel.add(txtSaldo);

        panel.add(new JLabel("Tasa Interés Anual:"));
        txtTasa = new JTextField();
        panel.add(txtTasa);

        panel.add(new JLabel("Límite Retiro Diario:"));
        txtLimite = new JTextField();
        panel.add(txtLimite);

        panel.add(new JLabel("Saldo Mínimo:"));
        txtSaldoMin = new JTextField();
        panel.add(txtSaldoMin);

        panel.add(new JLabel("Tasa Inversión:"));
        txtTasaInv = new JTextField();
        panel.add(txtTasaInv);

        JButton btnCrear = new JButton("Crear Cuenta");
        btnCrear.addActionListener(e -> crearCuenta());
        panel.add(btnCrear);

        getContentPane().add(panel, BorderLayout.NORTH);

        txtReporte = new JTextArea(15, 50);
        txtReporte.setEditable(false);
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);

        JPanel panelOps = new JPanel(new GridLayout(0, 2, 5, 5));

        // Depósito / retiro
        txtMontoOperacion = new JTextField();
        panelOps.add(new JLabel("Monto operación:"));
        panelOps.add(txtMontoOperacion);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(e -> {
            double monto = Double.parseDouble(txtMontoOperacion.getText());
            cuenta.depositar(monto);
            txtReporte.append("Depósito: " + monto + " Saldo: " + cuenta.getSaldo() + "\n");
        });
        panelOps.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(e -> {
            double monto = Double.parseDouble(txtMontoOperacion.getText());
            if (cuenta.retirar(monto)) {
                txtReporte.append("Retiro: " + monto + " Saldo: " + cuenta.getSaldo() + "\n");
            } else {
                txtReporte.append("Error al retirar (límite diario o saldo insuficiente)\n");
            }
        });
        panelOps.add(btnRetirar);

        JButton btnInteres = new JButton("Aplicar Interés Mensual");
        btnInteres.addActionListener(e -> {
            double interes = cuenta.calcularInteresMensual();
            txtReporte.append("Interés aplicado: " + interes + " Saldo: " + cuenta.getSaldo() + "\n");
        });
        panelOps.add(btnInteres);

        // Simulación de inversión
        txtMontoInv = new JTextField();
        txtMesesInv = new JTextField();
        panelOps.add(new JLabel("Monto Inversión:"));
        panelOps.add(txtMontoInv);
        panelOps.add(new JLabel("Meses Inversión:"));
        panelOps.add(txtMesesInv);
        JButton btnSimularInv = new JButton("Simular Inversión");
        btnSimularInv.addActionListener(e -> {
            double m = Double.parseDouble(txtMontoInv.getText());
            int n = Integer.parseInt(txtMesesInv.getText());
            double vf = cuenta.simularInversion(m, n);
            txtReporte.append("Inversión VF: " + vf + "\n");
        });
        panelOps.add(btnSimularInv);

        // Prestamo
        txtMontoPrestamo = new JTextField();
        txtTasaPrestamo = new JTextField();
        txtMesesPrestamo = new JTextField();
        panelOps.add(new JLabel("Monto Préstamo:"));
        panelOps.add(txtMontoPrestamo);
        panelOps.add(new JLabel("Tasa Anual Prestamo:"));
        panelOps.add(txtTasaPrestamo);
        panelOps.add(new JLabel("Meses Prestamo:"));
        panelOps.add(txtMesesPrestamo);
        JButton btnPrestamo = new JButton("Calcular Amortización");
        btnPrestamo.addActionListener(e -> {
            double m = Double.parseDouble(txtMontoPrestamo.getText());
            double t = Double.parseDouble(txtTasaPrestamo.getText());
            int n = Integer.parseInt(txtMesesPrestamo.getText());
            String plan = cuenta.calcularAmortizacionPrestamo(m, t, n);
            txtReporte.append(plan + "\n");
        });
        panelOps.add(btnPrestamo);

        JButton btnCargo = new JButton("Aplicar Cargo Saldo Bajo");
        btnCargo.addActionListener(e -> {
            cuenta.aplicarCargosPorSaldoBajo();
            txtReporte.append("Saldo tras cargo: " + cuenta.getSaldo() + "\n");
        });
        panelOps.add(btnCargo);

        // --- NUEVA SECCIÓN: TRANSFERIR ---
        txtCuentaDestino = new JTextField();
        txtMontoTransferir = new JTextField();
        panelOps.add(new JLabel("Cuenta Destino:"));
        panelOps.add(txtCuentaDestino);
        panelOps.add(new JLabel("Monto a Transferir:"));
        panelOps.add(txtMontoTransferir);

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(e -> {
            String numeroDestino = txtCuentaDestino.getText();
            double monto = Double.parseDouble(txtMontoTransferir.getText());

            // En un sistema real deberías buscar la cuenta destino en una lista o mapa.
            // Aquí creamos una cuenta destino ficticia solo para mostrar el saldo:
            CuentaBancaria cuentaDestino = new CuentaBancaria(
                    numeroDestino,
                    "Destinatario",
                    0,
                    cuenta.getTasaInteresAnual(),
                    cuenta.getLimiteRetiroDiario(),
                    cuenta.getSaldoMinimo(),
                    cuenta.getTasaInversion()
            );

            if (cuenta.transferir(cuentaDestino, monto)) {
                txtReporte.append("Transferidos " + monto + " de " + cuenta.getNumeroCuenta()
                        + " a " + cuentaDestino.getNumeroCuenta() + "\n");
                txtReporte.append("Saldo origen: " + cuenta.getSaldo() + "\n");
                txtReporte.append("Saldo destino: " + cuentaDestino.getSaldo() + "\n");
            } else {
                txtReporte.append("Transferencia fallida (saldo insuficiente o límite diario)\n");
            }
        });
        panelOps.add(btnTransferir);

        add(panelOps, BorderLayout.SOUTH);
    }

    private void crearCuenta() {
        String numero = txtNumero.getText();
        String titular = txtTitular.getText();
        double saldo = Double.parseDouble(txtSaldo.getText());
        double tasa = Double.parseDouble(txtTasa.getText());
        double limite = Double.parseDouble(txtLimite.getText());
        double salMin = Double.parseDouble(txtSaldoMin.getText());
        double tasaInv = Double.parseDouble(txtTasaInv.getText());

        cuenta = new CuentaBancaria(numero, titular, saldo, tasa, limite, salMin, tasaInv);
        txtReporte.setText("Cuenta creada:\n" + cuenta.verEstadoCuenta() + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CuentaBancariaGUI().setVisible(true));
    }
}
