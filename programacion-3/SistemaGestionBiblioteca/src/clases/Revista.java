package clases;

import interfaces.Prestable;
import interfaces.Renovable;

public class Revista extends MaterialBiblioteca implements Prestable, Renovable {
    private int numero;
    private String fechaPublicacion;
    private String editorial;
    private boolean prestado;
    private String usuarioPrestamo;
    private int renovaciones;

    public Revista(String titulo, int numero, String fechaPublicacion, String editorial) {
        super(titulo);
        this.numero = numero;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
        this.prestado = false;
        this.usuarioPrestamo = null;
        this.renovaciones = 0;
    }

    @Override
    public void prestar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (prestado) {
            System.out.println("Operación inválida: la revista ya está prestada a " + usuarioPrestamo);
            return;
        }
        prestado = true;
        usuarioPrestamo = usuario;
        renovaciones = 0;
        System.out.println("Prestando revista a: " + usuario);
    }

    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Operación inválida: la revista no está prestada.");
            return;
        }
        prestado = false;
        usuarioPrestamo = null;
        System.out.println("Devolviendo revista...");
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public int diasPrestamo() {
        return 7;
    }

    @Override
    public void renovar() {
        if (!puedeRenovarse()) {
            System.out.println("No se puede renovar más veces.");
            return;
        }
        renovaciones++;
        System.out.println("Renovando revista...");
    }

    @Override
    public int vecesRenovado() {
        return renovaciones;
    }

    @Override
    public boolean puedeRenovarse() {
        return prestado && renovaciones < 1;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== REVISTA ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Número: " + numero);
        System.out.println("Fecha de publicación: " + fechaPublicacion);
        System.out.println("Editorial: " + editorial);
        System.out.println("Prestado: " + prestado);
        System.out.println("Renovaciones: " + renovaciones);
        System.out.println();
    }

    public String getUsuarioPrestamo() {
        return usuarioPrestamo;
    }
}
