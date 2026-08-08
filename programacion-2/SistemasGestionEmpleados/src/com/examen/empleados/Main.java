package com.examen.empleados;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmPrincipal frm = new FrmPrincipal();
            frm.setVisible(true);
        });
    }
}
