package Models;

public class FilmGenero {

    // ATRIBUTOS
    private int film_id;
    private int genero_id;

    // GET AND SET
    public int getFilmId() {
        return film_id;
    }

    public void setFilmId(int filmId) {
        this.film_id = filmId;
    }

    public int getGeneroId() {
        return genero_id;
    }

    public void setGeneroId(int generoId) {
        this.genero_id = generoId;
    }

    // CONSTRUCTOR
    public FilmGenero(int filmId, int generoId) {
        this.film_id = filmId;
        this.genero_id = generoId;
    }
}
