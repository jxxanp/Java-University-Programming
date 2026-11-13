public class Curso {

    private String nombre;
    private String area;
    private double precio;
    private int cupos;

    public Curso(String nombre, String area, double precio, int cupos) {
        this.nombre = nombre;
        this.area = area;
        this.precio = precio;
        this.cupos = cupos;
    }

    public String getNombre() { return nombre;}
    public String getArea() { return area; }
    public double getPrecio() { return precio; }
    public int getCupos() { return cupos; }

    @Override
    public String toString() {
        return nombre + "Area:" + area + "$" + precio + " Cupos:" + cupos;
    }
}
