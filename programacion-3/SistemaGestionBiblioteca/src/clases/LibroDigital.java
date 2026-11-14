package clases;

import interfaces.Descargable;
import interfaces.Reservable;

public class LibroDigital extends MaterialBiblioteca implements Descargable, Reservable {
    private String autor;
    private String isbn;
    private String formato;
    private long tamaño;
    private boolean reservado;
    private String usuarioReserva;

    public LibroDigital(String titulo, String autor, String isbn, String formato, long tamaño) {
        super(titulo);
        this.autor = autor;
        this.isbn = isbn;
        this.formato = formato;
        this.tamaño = tamaño;
        this.reservado = false;
        this.usuarioReserva = null;
    }

    @Override
    public void descargar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (reservado && usuarioReserva.equals(usuario)) {
            System.out.println("Descargando eBook para: " + usuario);
            System.out.println("Prioridad de reserva aplicada.");
            reservado = false;
            usuarioReserva = null;
        } else {
            System.out.println("Descargando eBook para: " + usuario);
            if (reservado) {
                System.out.println("La reserva de " + usuarioReserva + " se mantiene.");
            }
        }
    }

    @Override
    public long tamañoArchivo() {
        return tamaño;
    }

    @Override
    public String formatoArchivo() {
        return formato;
    }

    @Override
    public void reservar(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Operación inválida: el usuario no puede estar vacío.");
            return;
        }
        if (reservado) {
            System.out.println("Operación inválida: el libro ya está reservado para " + usuarioReserva);
            return;
        }
        reservado = true;
        usuarioReserva = usuario;
        System.out.println("Reservando eBook para: " + usuario);
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
        System.out.println("=== LIBRO DIGITAL ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Formato: " + formato);
        System.out.println("Tamaño: " + tamaño + " MB");
        System.out.println("Reservado: " + reservado);
        System.out.println();
    }

    public String getUsuarioReserva() {
        return usuarioReserva;
    }
}
