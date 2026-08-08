package app;

import javax.swing.*;
import java.awt.event.*;

/**
 * PurchaseGUI.java
 * Interfaz gráfica (JFrame) que calcula monto, descuento, monto a pagar y obsequios
 * según la compra en docenas de un producto.
 *
 * Usa únicamente JLabel, JButton, JTextField y JComboBox.
 */
public class PurchaseGUI extends JFrame implements ActionListener {
    // Labels
    private JLabel lPrice, lPriceType, lDozens, lMontoCompra, lDescuento, lMontoPagar, lObsequios;
    // Inputs
    private JTextField tPrice, tDozens;
    // Result fields
    private JTextField tMontoCompra, tDescuento, tMontoPagar, tObsequios;
    // ComboBox
    private JComboBox<String> cbPriceType;
    // Buttons
    private JButton bCalc, bClear, bExit;

    public PurchaseGUI() {
        super("Venta al por mayor - Oferta");
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setSize(540, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Precio
        lPrice = new JLabel("Precio:"); lPrice.setBounds(20, 20, 100, 25); add(lPrice);
        tPrice = new JTextField(); tPrice.setBounds(120, 20, 120, 25); add(tPrice);

        // Tipo de precio
        lPriceType = new JLabel("Tipo de precio:"); lPriceType.setBounds(260, 20, 100, 25); add(lPriceType);
        cbPriceType = new JComboBox<>(new String[] {"Precio por docena", "Precio por unidad"});
        cbPriceType.setBounds(360, 20, 150, 25); add(cbPriceType);

        // Docenas
        lDozens = new JLabel("Cantidad (docenas):"); lDozens.setBounds(20, 60, 140, 25); add(lDozens);
        tDozens = new JTextField(); tDozens.setBounds(160, 60, 80, 25); add(tDozens);

        // Botones
        bCalc = new JButton("Calcular"); bCalc.setBounds(260, 60, 100, 25); bCalc.addActionListener(this); add(bCalc);
        bClear = new JButton("Limpiar"); bClear.setBounds(370, 60, 100, 25); bClear.addActionListener(this); add(bClear);

        // Resultados (labels y campos)
        lMontoCompra = new JLabel("Monto compra:"); lMontoCompra.setBounds(20, 120, 120, 25); add(lMontoCompra);
        tMontoCompra = new JTextField(); tMontoCompra.setBounds(140, 120, 200, 25); tMontoCompra.setEditable(false); add(tMontoCompra);

        lDescuento = new JLabel("Descuento:"); lDescuento.setBounds(20, 160, 120, 25); add(lDescuento);
        tDescuento = new JTextField(); tDescuento.setBounds(140, 160, 200, 25); tDescuento.setEditable(false); add(tDescuento);

        lMontoPagar = new JLabel("Monto a pagar:"); lMontoPagar.setBounds(20, 200, 120, 25); add(lMontoPagar);
        tMontoPagar = new JTextField(); tMontoPagar.setBounds(140, 200, 200, 25); tMontoPagar.setEditable(false); add(tMontoPagar);

        lObsequios = new JLabel("Obsequios (unidades):"); lObsequios.setBounds(20, 240, 140, 25); add(lObsequios);
        tObsequios = new JTextField(); tObsequios.setBounds(160, 240, 80, 25); tObsequios.setEditable(false); add(tObsequios);

        bExit = new JButton("Salir"); bExit.setBounds(420, 240, 80, 25); bExit.addActionListener(e -> System.exit(0)); add(bExit);

        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == bCalc) {
            try {
                // Validaciones básicas
                if (tPrice.getText().trim().isEmpty() || tDozens.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos de entrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = Double.parseDouble(tPrice.getText().trim());
                int dozens = Integer.parseInt(tDozens.getText().trim());
                if (price < 0 || dozens < 0) {
                    JOptionPane.showMessageDialog(this, "No se permiten valores negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean priceIsPerDozen = cbPriceType.getSelectedIndex() == 0;

                // Crear Purchase y calcular
                Purchase p = new Purchase(price, priceIsPerDozen, dozens);
                p.calculate();

                // Mostrar resultados con 2 decimales
                tMontoCompra.setText(String.format("%.2f", p.getMontoCompra()));
                tDescuento.setText(String.format("%.2f", p.getDescuento()));
                tMontoPagar.setText(String.format("%.2f", p.getMontoPagar()));
                tObsequios.setText(Integer.toString(p.getObsequios()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese números válidos (precio: decimal, docenas: entero).", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == bClear) {
            tPrice.setText(""); tDozens.setText("");
            tMontoCompra.setText(""); tDescuento.setText(""); tMontoPagar.setText(""); tObsequios.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PurchaseGUI().setVisible(true));
    }
}
