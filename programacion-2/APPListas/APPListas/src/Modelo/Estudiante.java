/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MARIA LUGO
 */

//Clase para recopilar los datos de los estudiantes

public class Estudiante {
    private String identificacion;
    private String nombre;
    private String sexo;
    private int edad;
    private int grado;
    private String nombreAcudiente;
    private String telefono;

    //Metodo constructor
    public Estudiante(String identificacion, String nombre, String sexo, int edad, int grado, String nombreAcudiente, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.grado = grado;
        this.nombreAcudiente = nombreAcudiente;
        this.telefono = telefono;
    }

    // Metodos Get y Set
    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public int getEdad() { return edad; }
    public int getGrado() { return grado; }
    public String getNombreAcudiente() { return nombreAcudiente; }
    public String getTelefono() { return telefono; }
}
