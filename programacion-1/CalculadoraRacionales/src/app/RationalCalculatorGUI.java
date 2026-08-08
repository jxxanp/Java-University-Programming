package app;

import javax.swing.*;
import java.awt.event.*;

/**
 * RationalCalculatorGUI.java
 * Interfaz gráfica (JFrame) para operar con racionales.
 */
public class RationalCalculatorGUI extends JFrame implements ActionListener {
    private JLabel lNum1, lDen1, lNum2, lDen2, lOp, lResult;
    private JTextField tNum1, tDen1, tNum2, tDen2, tResult;
    private JComboBox<String> cbOp;
    private JButton bCalc, bClear, bExit;

    public RationalCalculatorGUI() {
        super("Calculadora de Racionales");
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lNum1 = new JLabel("Num 1:"); lNum1.setBounds(20,20,60,25); add(lNum1);
        tNum1 = new JTextField(); tNum1.setBounds(80,20,80,25); add(tNum1);

        lDen1 = new JLabel("Den 1:"); lDen1.setBounds(170,20,60,25); add(lDen1);
        tDen1 = new JTextField(); tDen1.setBounds(230,20,80,25); add(tDen1);

        lNum2 = new JLabel("Num 2:"); lNum2.setBounds(20,60,60,25); add(lNum2);
        tNum2 = new JTextField(); tNum2.setBounds(80,60,80,25); add(tNum2);

        lDen2 = new JLabel("Den 2:"); lDen2.setBounds(170,60,60,25); add(lDen2);
        tDen2 = new JTextField(); tDen2.setBounds(230,60,80,25); add(tDen2);

        lOp = new JLabel("Operación:"); lOp.setBounds(20,100,80,25); add(lOp);
        cbOp = new JComboBox<>(new String[]{"+","-","*","/"}); cbOp.setBounds(100,100,80,25); add(cbOp);

        bCalc = new JButton("Calcular"); bCalc.setBounds(200,100,100,25); bCalc.addActionListener(this); add(bCalc);
        bClear = new JButton("Limpiar"); bClear.setBounds(310,100,80,25); bClear.addActionListener(this); add(bClear);

        lResult = new JLabel("Resultado:"); lResult.setBounds(20,140,80,25); add(lResult);
        tResult = new JTextField(); tResult.setBounds(100,140,290,25); tResult.setEditable(false); add(tResult);

        bExit = new JButton("Salir"); bExit.setBounds(310,180,80,25); bExit.addActionListener(e -> System.exit(0)); add(bExit);

        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == bCalc) {
            try {
                int n1 = Integer.parseInt(tNum1.getText().trim());
                int d1 = Integer.parseInt(tDen1.getText().trim());
                int n2 = Integer.parseInt(tNum2.getText().trim());
                int d2 = Integer.parseInt(tDen2.getText().trim());
                Rational r1 = new Rational(n1, d1);
                Rational r2 = new Rational(n2, d2);
                String op = (String) cbOp.getSelectedItem();
                Rational res;
                switch (op) {
                    case "+": res = r1.add(r2); break;
                    case "-": res = r1.sub(r2); break;
                    case "*": res = r1.mul(r2); break;
                    case "/": res = r1.div(r2); break;
                    default: throw new IllegalStateException("Operación desconocida");
                }
                tResult.setText(res.toString() + "  =  " + res.toDouble());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese enteros válidos en numeradores y denominadores.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException | ArithmeticException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == bClear) {
            tNum1.setText(""); tDen1.setText(""); tNum2.setText(""); tDen2.setText(""); tResult.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RationalCalculatorGUI().setVisible(true));
    }
}
