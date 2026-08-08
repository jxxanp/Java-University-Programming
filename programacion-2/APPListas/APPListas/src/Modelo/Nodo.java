/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MARIA LUGO
 */

// Definición de la clase Nodo
public class Nodo {
    public Estudiante dato; //Declaro variable nodo de tipo estudiante
    public Nodo siguiente; //Conexion o referencia hacia el siguiente

    //Contructor de la clase nodo bajo el atributo dato
    public Nodo(Estudiante dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
