package Models;

import java.sql.Date;

public class Filmografia {
    
    //ATRIBUTOS
    private int id;
    private String titulo;
    private Date fecha_estreno;
    private String sinopsis;
    private int pais_id;
    private int clasificacion_id;
    
    //GET AND SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaEstreno() {
        return fecha_estreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fecha_estreno = fechaEstreno;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getPaisId() {
        return pais_id;
    }

    public void setPaisId(int paisId) {
        this.pais_id = paisId;
    }

    public int getClasificacionId() {
        return clasificacion_id;
    }

    public void setClasificacionId(int clasificacionId) {
        this.clasificacion_id = clasificacionId;
    }
    
    //CONSTRUCTOR
    public Filmografia(int id, String titulo, Date fechaEstreno, String sinopsis, int paisId, int clasificacionId) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_estreno = fechaEstreno;
        this.sinopsis = sinopsis;
        this.pais_id = paisId;
        this.clasificacion_id = clasificacionId;
    }
    
    // TO STRING
    @Override
    public String toString() {
        return "Filmografia{" + "id=" + id + ", titulo=" + titulo + ", fecha_estreno=" + fecha_estreno + ", sinopsis=" + sinopsis + ", pais_id=" + pais_id + ", clasificacion_id=" + clasificacion_id + '}';
    }
        
}
