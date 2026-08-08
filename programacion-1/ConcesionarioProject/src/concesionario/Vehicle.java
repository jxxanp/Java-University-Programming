package concesionario;

public class Vehicle {
private String matricula;
private String marca;
private String modelo;
private double precio;
private boolean estado; // true = vendido, false = disponible


public Vehicle() {}

public Vehicle(String matricula, String marca, String modelo, double precio, boolean estado) {
    this.matricula = matricula;
    this.marca = marca;
    this.modelo = modelo;
    this.precio = precio;
    this.estado = estado;
}

public String getMatricula() { return matricula; }
public void setMatricula(String matricula) { this.matricula = matricula; }

public String getMarca() { return marca; }
public void setMarca(String marca) { this.marca = marca; }

public String getModelo() { return modelo; }
public void setModelo(String modelo) { this.modelo = modelo; }

public double getPrecio() { return precio; }
public void setPrecio(double precio) { this.precio = precio; }

public boolean isEstado() { return estado; }
public void setEstado(boolean estado) { this.estado = estado; }

@Override
public String toString() {
    return matricula + " | " + marca + " | " + modelo + " | " + precio + " | " + (estado? "VENDIDO":"DISPONIBLE");
}


}
