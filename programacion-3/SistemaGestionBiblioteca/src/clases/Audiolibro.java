package clases;

import interfaces.Descargable;
import interfaces.Reservable;

public class Audiolibro extends MaterialBiblioteca implements Descargable, Reservable {

    private String narrador;
    private int duracion;
    private String formato;
    private long tamaño;
    private boolean reservado;
    private String usuarioReserva;

    public Audiolibro(String titulo, String narrador, int duracion, String formato, long tamaño) {
        super(titulo);
        this.narrador = narrador;
        this.duracion = duracion;
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
            System.out.println("Descargando audiolibro para: " + usuario);
            System.out.println("Prioridad de reserva aplicada.");
            reservado = false;
            usuarioReserva = null;
        } else {
            System.out.println("Descargando audiolibro para: " + usuario);
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
            System.out.println("Operación inválida: el audiolibro ya está reservado para " + usuarioReserva);
            return;
        }
        reservado = true;
        usuarioReserva = usuario;
        System.out.println("Reservando audiolibro para: " + usuario);
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
        System.out.println("=== AUDIOLIBRO ===");
        System.out.println("Título: " + getTitulo());
        System.out.println("Narrador: " + narrador);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Formato: " + formato);
        System.out.println("Tamaño: " + tamaño + " MB");
        System.out.println("Reservado: " + reservado);
        System.out.println();
    }

    public String getUsuarioReserva() {
        return usuarioReserva;
    }
}
