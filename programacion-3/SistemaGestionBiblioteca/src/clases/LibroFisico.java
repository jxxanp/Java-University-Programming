package clases;

import interfaces.Prestable;
import interfaces.Renovable;
import interfaces.Reservable;

public class LibroFisico extends MaterialBiblioteca implements Prestable, Renovable, Reservable {
    private String autor;
    private String isbn;
    private String ubicacion;
    private boolean prestado;
    private String usuarioPrestamo;
    private int renovaciones;
    private boolean reservado;
    private String usuarioReserva;

    public LibroFisico(String titulo, String autor, String isbn, String ubicacion) {
        super(titulo);
        this.autor = autor;
        this.isbn = isbn;
        this.ubicacion = ubicacion;
        this.prestado = false;
        this.usuarioPrestamo = null;
        this.renovaciones = 0;
        this.reservado = false;
        this.usuarioReserva = null;
    }

    @Override
    public void prestar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (prestado) {
            System.out.println("Operación inválida: el libro ya está prestado a " + usuarioPrestamo);
            return;
        }
        if (reservado && !usuarioReserva.equals(usuario)) {
            System.out.println("Operación inválida: el libro está reservado para " + usuarioReserva);
            return;
        }
        prestado = true;
        usuarioPrestamo = usuario;
        renovaciones = 0;
        if (reservado && usuarioReserva.equals(usuario)) {
            reservado = false;
            usuarioReserva = null;
        }
        System.out.println("Prestando libro a: " + usuario);
    }

    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Operación inválida: el libro no está prestado.");
            return;
        }
        prestado = false;
        usuarioPrestamo = null;
        System.out.println("Devolviendo libro...");
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public int diasPrestamo() {
        return 15;
    }

    @Override
    public void renovar() {
        if (!puedeRenovarse()) {
            System.out.println("No se puede renovar más veces.");
            return;
        }
        renovaciones++;
        System.out.println("Renovando libro...");
    }

    @Override
    public int vecesRenovado() {
        return renovaciones;
    }

    @Override
    public boolean puedeRenovarse() {
        return prestado && renovaciones < 2;
    }

    @Override
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (prestado) {
            System.out.println("Operación inválida: no se puede reservar un libro prestado.");
            return;
        }
        if (reservado) {
            System.out.println("Operación inválida: el libro ya está reservado para " + usuarioReserva);
            return;
        }
        reservado = true;
        usuarioReserva = usuario;
        System.out.println("Reservando libro para: " + usuario);
    }

    @Override
    public void cancelarReserva() {
        if (!reservado) {
            System.out.println("Operación inválida: no existe una reserva.");
            return;
        }
        reservado = false;
        usuarioReserva = null;
        System.out.println("Reserva cancelada.");
    }

    @Override
    public boolean estaReservado() {
        return reservado;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== LIBRO FÍSICO ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Prestado: " + prestado);
        System.out.println("Reservado: " + reservado);
        System.out.println();
    }

    public String getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public String getUsuarioReserva() {
        return usuarioReserva;
    }
}
