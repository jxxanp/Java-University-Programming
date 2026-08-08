/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estudiante;
import Modelo.Nodo;

/**
 *
 * @author MARIA LUGO
 */

public class ListaSimple {
    private Nodo cabeza;

    public ListaSimple() {
        cabeza = null;
    }

    
       //  Agregar estudiante al inicio
    public void agregarInicio(Estudiante e) {
        Nodo nuevo = new Nodo(e);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }
    
    //  Agregar estudiante al final
    public void agregarFinal(Estudiante e) {
        Nodo nuevo = new Nodo(e);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    //  Metodo para Buscar por identificación estudiante de la lista
    public Estudiante buscarPorId(String id) {
        Nodo aux = cabeza;
        while (aux != null) {
            if (aux.dato.getIdentificacion().equals(id)) {
                return aux.dato;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    //  Metodo Eliminar estudiante de la lista
    public void eliminar(String id) {
        if (cabeza == null) return;

        if (cabeza.dato.getIdentificacion().equals(id)) {
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo aux = cabeza;
        while (aux.siguiente != null) {
            if (aux.siguiente.dato.getIdentificacion().equals(id)) {
                aux.siguiente = aux.siguiente.siguiente;
                return;
            }
            aux = aux.siguiente;
        }
    }

    //  Recorrer lista
    public Nodo getCabeza() {
        return cabeza;
    }
}
