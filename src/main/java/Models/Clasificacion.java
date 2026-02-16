package Models;

public class Clasificacion {

    // ATRIBUTOS
    private int id;
    private String nombre;

    // GET AND SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // CONSTRUCTOR
    public Clasificacion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Clasificacion{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
