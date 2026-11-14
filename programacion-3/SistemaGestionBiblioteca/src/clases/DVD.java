package clases;

import interfaces.Prestable;
import interfaces.Reservable;

public class DVD extends MaterialBiblioteca implements Prestable, Reservable {
    private String director;
    private int duracion;
    private String genero;
    private boolean prestado;
    private String usuarioPrestamo;
    private boolean reservado;
    private String usuarioReserva;

    public DVD(String titulo, String director, int duracion, String genero) {
        super(titulo);
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
        this.prestado = false;
        this.usuarioPrestamo = null;
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
            System.out.println("Operación inválida: el DVD ya está prestado a " + usuarioPrestamo);
            return;
        }
        if (reservado && !usuarioReserva.equals(usuario)) {
            System.out.println("Operación inválida: el DVD está reservado para " + usuarioReserva);
            return;
        }
        prestado = true;
        usuarioPrestamo = usuario;
        if (reservado && usuarioReserva.equals(usuario)) {
            reservado = false;
            usuarioReserva = null;
        }
        System.out.println("Prestando DVD a: " + usuario);
    }

    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("Operación inválida: el DVD no está prestado.");
            return;
        }
        prestado = false;
        usuarioPrestamo = null;
        System.out.println("Devolviendo DVD...");
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public int diasPrestamo() {
        return 5;
    }

    @Override
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (prestado) {
            System.out.println("Operación inválida: no se puede reservar un DVD prestado.");
            return;
        }
        if (reservado) {
            System.out.println("Operación inválida: el DVD ya está reservado para " + usuarioReserva);
            return;
        }
        reservado = true;
        usuarioReserva = usuario;
        System.out.println("Reservando DVD para: " + usuario);
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
        System.out.println("=== DVD ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Director: " + director);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Género: " + genero);
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
