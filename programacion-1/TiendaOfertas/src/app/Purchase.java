package app;

/**
 * Purchase.java
 * Modelo que representa una compra al por mayor con descuentos y obsequios.
 */
public class Purchase {
    private double price;               // precio (por docena o por unidad según flag)
    private boolean priceIsPerDozen;    // true si price es precio por docena; false -> precio por unidad
    private int dozens;                 // cantidad de docenas compradas

    // Resultados
    private double montoCompra;
    private double descuento;
    private double montoPagar;
    private int obsequios;              // unidades obsequiadas

    public Purchase() {
        this(0.0, true, 0);
    }

    public Purchase(double price, boolean priceIsPerDozen, int dozens) {
        if (price < 0) throw new IllegalArgumentException("Precio no puede ser negativo");
        if (dozens < 0) throw new IllegalArgumentException("Cantidad de docenas no puede ser negativa");
        this.price = price;
        this.priceIsPerDozen = priceIsPerDozen;
        this.dozens = dozens;
        calculate();
    }

    // Calcula montoCompra, descuento, montoPagar y obsequios
    public void calculate() {
        // montoCompra: si priceIsPerDozen -> price * dozens
        //             si priceIsPerUnit  -> price * (dozens*12)
        if (priceIsPerDozen) {
            montoCompra = price * dozens;
        } else {
            montoCompra = price * (dozens * 12);
        }

        // descuento: 15% si docenas > 3, sino 10%
        if (dozens > 3) {
            descuento = montoCompra * 0.15;
        } else {
            descuento = montoCompra * 0.10;
        }

        // monto a pagar
        montoPagar = montoCompra - descuento;

        // obsequios: por cada docena en exceso sobre 3, se regala 1 unidad
        if (dozens > 3) {
            obsequios = dozens - 3; // 1 unidad por cada docena extra
        } else {
            obsequios = 0;
        }
    }

    // Getters y setters (si necesitas actualizar y recalcular)
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; calculate(); }

    public boolean isPriceIsPerDozen() { return priceIsPerDozen; }
    public void setPriceIsPerDozen(boolean priceIsPerDozen) { this.priceIsPerDozen = priceIsPerDozen; calculate(); }

    public int getDozens() { return dozens; }
    public void setDozens(int dozens) { this.dozens = dozens; calculate(); }

    public double getMontoCompra() { return montoCompra; }
    public double getDescuento() { return descuento; }
    public double getMontoPagar() { return montoPagar; }
    public int getObsequios() { return obsequios; }

    @Override
    public String toString() {
        return String.format("Monto compra: %.2f, Descuento: %.2f, A pagar: %.2f, Obsequios(unidades): %d",
                montoCompra, descuento, montoPagar, obsequios);
    }
}
