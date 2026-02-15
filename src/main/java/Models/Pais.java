package Models;

public class Pais {
    
    //ATRIBUTOS
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

    //CONSTRUCTOR
    public Pais(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    //CONSTRUCTOR POR DEFECTO
    public Pais() {
        this.id = 0;
        this.nombre = "";
    }

    
    // TO STRING
    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
    
}
