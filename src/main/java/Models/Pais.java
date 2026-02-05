/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EDU
 */
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

    
    // TO STRING
    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
    
}
