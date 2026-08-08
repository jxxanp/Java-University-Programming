package app;

/**
 * Rational.java
 * POJO que representa un número racional (numerador/denominador) con
 * normalización (MCD) y operaciones básicas.
 */
public class Rational {
    private int num;
    private int den;

    public Rational() { this(0,1); }

    public Rational(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominador no puede ser 0");
        this.num = num;
        this.den = den;
        normalize();
    }

    private int gcd(int a, int b) {
        a = Math.abs(a); b = Math.abs(b);
        while (b != 0) {
            int t = a % b;
            a = b; b = t;
        }
        return a == 0 ? 1 : a;
    }

    private void normalize() {
        if (den < 0) { num = -num; den = -den; }
        int g = gcd(num, den);
        num /= g; den /= g;
    }

    public int getNum() { return num; }
    public int getDen() { return den; }

    public Rational add(Rational r) {
        int n = this.num * r.den + r.num * this.den;
        int d = this.den * r.den;
        return new Rational(n, d);
    }

    public Rational sub(Rational r) {
        int n = this.num * r.den - r.num * this.den;
        int d = this.den * r.den;
        return new Rational(n, d);
    }

    public Rational mul(Rational r) {
        return new Rational(this.num * r.num, this.den * r.den);
    }

    public Rational div(Rational r) {
        if (r.num == 0) throw new ArithmeticException("División por cero (racional con numerador 0)");
        return new Rational(this.num * r.den, this.den * r.num);
    }

    public double toDouble() { return (double) num / den; }

    @Override
    public String toString() { return num + "/" + den; }
}
