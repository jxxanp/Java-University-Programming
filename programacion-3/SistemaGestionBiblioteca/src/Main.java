import clases.*;
import interfaces.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE BIBLIOTECA");
        System.out.println("==============================================");

        System.out.println("\n========== 1. CREACIÓN DE MATERIALES ==========");
        LibroFisico libro1 = new LibroFisico("Clean Code", "Robert Martin", "978-0132350884", "Estante A-12");
        LibroDigital ebook1 = new LibroDigital("Effective Java", "Joshua Bloch", "978-0134685991", "PDF", 5);
        Revista revista1 = new Revista("National Geographic", 245, "2024-01", "NG Partners");
        DVD dvd1 = new DVD("Inception", "Christopher Nolan", 148, "Ciencia Ficción");
        Audiolibro audio1 = new Audiolibro("Sapiens", "Derek Perkins", 450, "MP3", 250);
        System.out.println("Materiales creados correctamente.");

        System.out.println("\n========== 2. POLIMORFISMO CON INTERFACES ==========");
        Prestable materialPrestable = libro1;
        Descargable materialDescargable = ebook1;
        Renovable materialRenovable = revista1;
        Reservable materialReservable = dvd1;
        System.out.println("Libro físico asignado a Prestable.");
        System.out.println("Libro digital asignado a Descargable.");
        System.out.println("Revista asignada a Renovable.");
        System.out.println("DVD asignado a Reservable.");

        System.out.println("\n========== INFORMACIÓN DE LOS MATERIALES ==========");
        MaterialBiblioteca[] materiales = {libro1, ebook1, revista1, dvd1, audio1};
        for (MaterialBiblioteca material : materiales) {
            material.mostrarInformacion();
            System.out.println("----------------------------------------------");
        }

        System.out.println("\n========== 3. PRÉSTAMO Y DEVOLUCIÓN ==========");
        System.out.println("Prestando libro a Juan...");
        materialPrestable.prestar("Juan");
        System.out.println("¿Está prestado?: " + materialPrestable.estaPrestado());
        System.out.println("Días de préstamo: " + materialPrestable.diasPrestamo());
        System.out.println("Devolviendo libro...");
        materialPrestable.devolver();
        System.out.println("¿Está prestado?: " + materialPrestable.estaPrestado());

        System.out.println("\n========== 4. DESCARGA DEL LIBRO DIGITAL ==========");
        System.out.println("Descargando eBook para María...");
        materialDescargable.descargar("María");
        System.out.println("Formato: " + materialDescargable.formatoArchivo());
        System.out.println("Tamaño: " + materialDescargable.tamañoArchivo() + " MB");

        System.out.println("\n========== 5. RENOVACIÓN DE REVISTA ==========");
        System.out.println("Prestando revista a Pedro...");
        revista1.prestar("Pedro");
        System.out.println("¿Puede renovarse?: " + materialRenovable.puedeRenovarse());
        System.out.println("Realizando primera renovación...");
        materialRenovable.renovar();
        System.out.println("Veces renovada: " + materialRenovable.vecesRenovado());
        System.out.println("Realizando segunda renovación...");
        materialRenovable.renovar();
        System.out.println("¿Puede renovarse?: " + materialRenovable.puedeRenovarse());

        System.out.println("\n========== 6. RESERVA Y CANCELACIÓN ==========");
        System.out.println("Reservando DVD para Ana...");
        materialReservable.reservar("Ana");
        System.out.println("¿Está reservado?: " + materialReservable.estaReservado());
        System.out.println("Cancelando reserva...");
        materialReservable.cancelarReserva();
        System.out.println("¿Está reservado?: " + materialReservable.estaReservado());

        System.out.println("\n========== 7a. PRÉSTAMO DUPLICADO ==========");
        LibroFisico libroPrueba = new LibroFisico("Java Básico", "Autor Prueba", "ISBN001", "Estante B-01");
        System.out.println("Ana solicita el libro...");
        libroPrueba.prestar("Ana");
        System.out.println("Luis intenta solicitar el mismo libro...");
        libroPrueba.prestar("Luis");
        System.out.println("Usuario actual: " + libroPrueba.getUsuarioPrestamo());
        System.out.println("¿Está prestado?: " + libroPrueba.estaPrestado());
        System.out.println("Ana devuelve el libro...");
        libroPrueba.devolver();
        System.out.println("Se intenta devolver nuevamente...");
        libroPrueba.devolver();
        System.out.println("¿Está prestado?: " + libroPrueba.estaPrestado());

        System.out.println("\n========== 7b. RESERVA Y USUARIO RESERVANTE ==========");
        DVD dvdPrueba = new DVD("Interstellar", "Christopher Nolan", 169, "Ciencia Ficción");
        System.out.println("Ana reserva el DVD...");
        dvdPrueba.reservar("Ana");
        System.out.println("Luis intenta reservarlo...");
        dvdPrueba.reservar("Luis");
        System.out.println("Luis intenta pedirlo prestado...");
        dvdPrueba.prestar("Luis");
        System.out.println("Usuario del préstamo: " + dvdPrueba.getUsuarioPrestamo());
        System.out.println("Usuario de la reserva: " + dvdPrueba.getUsuarioReserva());
        System.out.println("Ana intenta pedirlo prestado...");
        dvdPrueba.prestar("Ana");
        System.out.println("Usuario del préstamo: " + dvdPrueba.getUsuarioPrestamo());
        System.out.println("¿Continúa reservado?: " + dvdPrueba.estaReservado());
        System.out.println("Carlos intenta reservar mientras está prestado...");
        dvdPrueba.reservar("Carlos");

        System.out.println("\n========== 7c. CANCELACIÓN DE RESERVA ==========");
        DVD dvdReserva = new DVD("Matrix", "Lana y Lilly Wachowski", 136, "Ciencia Ficción");
        System.out.println("Carlos reserva el DVD...");
        dvdReserva.reservar("Carlos");
        System.out.println("¿Está reservado?: " + dvdReserva.estaReservado());
        System.out.println("Carlos cancela la reserva...");
        dvdReserva.cancelarReserva();
        System.out.println("¿Está reservado?: " + dvdReserva.estaReservado());
        System.out.println("Se intenta cancelar nuevamente...");
        dvdReserva.cancelarReserva();
        System.out.println("Usuario de reserva: " + dvdReserva.getUsuarioReserva());

        System.out.println("\n========== 7d. LÍMITES DE RENOVACIÓN ==========");
        Revista revistaPrueba = new Revista("Science", 100, "2025-01", "Science Editorial");
        System.out.println("Se intenta renovar sin préstamo...");
        revistaPrueba.renovar();
        System.out.println("Prestando revista a Carlos...");
        revistaPrueba.prestar("Carlos");
        System.out.println("Primera renovación...");
        revistaPrueba.renovar();
        System.out.println("Veces renovada: " + revistaPrueba.vecesRenovado());
        System.out.println("Segunda renovación...");
        revistaPrueba.renovar();
        System.out.println("Veces renovada: " + revistaPrueba.vecesRenovado());
        System.out.println("Devolviendo revista...");
        revistaPrueba.devolver();
        System.out.println("Se presta nuevamente a Carlos...");
        revistaPrueba.prestar("Carlos");
        System.out.println("Contador después del nuevo préstamo: " + revistaPrueba.vecesRenovado());
        System.out.println("¿Puede renovarse?: " + revistaPrueba.puedeRenovarse());

        System.out.println("\n--- Límite de renovaciones del Libro Físico ---");
        LibroFisico libroRenovable = new LibroFisico("Clean Architecture", "Robert Martin", "ISBN002", "Estante C-10");
        libroRenovable.prestar("Pedro");
        System.out.println("Primera renovación...");
        libroRenovable.renovar();
        System.out.println("Segunda renovación...");
        libroRenovable.renovar();
        System.out.println("Tercera renovación...");
        libroRenovable.renovar();
        System.out.println("Total de renovaciones: " + libroRenovable.vecesRenovado());
        System.out.println("¿Puede renovarse?: " + libroRenovable.puedeRenovarse());

        System.out.println("\n========== 7e. RESERVA Y DESCARGA DIGITAL ==========");
        LibroDigital ebookPrueba = new LibroDigital("Java Programming", "Autor Digital", "ISBN003", "EPUB", 10);
        System.out.println("Ana reserva el libro digital...");
        ebookPrueba.reservar("Ana");
        System.out.println("Luis descarga el libro...");
        ebookPrueba.descargar("Luis");
        System.out.println("¿Continúa reservada?: " + ebookPrueba.estaReservado());
        System.out.println("Ana descarga el libro...");
        ebookPrueba.descargar("Ana");
        System.out.println("¿Continúa reservada?: " + ebookPrueba.estaReservado());

        Audiolibro audioPrueba = new Audiolibro("El Principito", "Narrador Prueba", 120, "MP3", 100);
        System.out.println("\nAna reserva el audiolibro...");
        audioPrueba.reservar("Ana");
        System.out.println("Luis descarga el audiolibro...");
        audioPrueba.descargar("Luis");
        System.out.println("¿Continúa reservado?: " + audioPrueba.estaReservado());
        System.out.println("Ana descarga el audiolibro...");
        audioPrueba.descargar("Ana");
        System.out.println("¿Continúa reservado?: " + audioPrueba.estaReservado());

        System.out.println("\n========== 7f. USUARIOS INVÁLIDOS ==========");
        LibroFisico libroInvalido = new LibroFisico("Libro de Prueba", "Autor", "ISBN004", "Estante D-01");
        System.out.println("\n--- Préstamos con usuarios inválidos ---");
        libroInvalido.prestar(null);
        libroInvalido.prestar("");
        libroInvalido.prestar("   ");
        System.out.println("¿Está prestado?: " + libroInvalido.estaPrestado());

        System.out.println("\n--- Reservas con usuarios inválidos ---");
        DVD dvdInvalido = new DVD("Avatar", "James Cameron", 162, "Ciencia Ficción");
        dvdInvalido.reservar(null);
        dvdInvalido.reservar("");
        dvdInvalido.reservar("   ");
        System.out.println("¿Está reservado?: " + dvdInvalido.estaReservado());

        System.out.println("\n--- Descargas con usuarios inválidos ---");
        LibroDigital ebookInvalido = new LibroDigital("Libro Digital", "Autor", "ISBN005", "PDF", 8);
        ebookInvalido.descargar(null);
        ebookInvalido.descargar("");
        ebookInvalido.descargar("   ");
        Audiolibro audioInvalido = new Audiolibro("Audiolibro de Prueba", "Narrador", 100, "AAC", 80);
        audioInvalido.descargar(null);
        audioInvalido.descargar("");
        audioInvalido.descargar("   ");

        System.out.println("\n==============================================");
        System.out.println("      FIN DE LAS PRUEBAS DEL TALLER");
        System.out.println("==============================================");
    }
}
