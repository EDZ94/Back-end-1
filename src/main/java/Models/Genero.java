
package Models;

/**
 *
 * @author EDU
 */
public class Genero {
    
    //ATRIBUTOS
    private int id;
    private String nombre;

    
    //GET AND SET
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
    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Genero(){
        this.id = 0;
        this.nombre = "";
    }

    
    //TO STRING
    @Override
    public String toString() {
        return "Genero{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    //METODO
    
}
