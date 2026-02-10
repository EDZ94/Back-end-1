import DAOs.FilmografiaDAO;
import Models.Filmografia;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        FilmografiaDAO dao_film = new FilmografiaDAO();

        // LISTAR TODAS LAS PELÍCULAS
        System.out.println("----- LISTA DE TODAS LAS PELÍCULAS -----");
        dao_film.listAll();

        // LISTAR UNA PELÍCULA POR ID
        System.out.println("\n----- BUSCAR PELÍCULA CON ID = 3 -----");
        dao_film.listOne(3);

        // INSERTAR UNA NUEVA PELÍCULA
        System.out.println("\n----- INSERTAR NUEVA PELÍCULA -----");
        Filmografia nueva = new Filmografia(
                0,
                "Nueva peli",
                Date.valueOf("2024-05-01"),
                "Película de prueba",
                1, // pais_id
                2  // clasificacion_id
        );
        dao_film.insert(nueva);

        // ACTUALIZAR UNA PELÍCULA
        System.out.println("\n----- ACTUALIZAR PELÍCULA CON ID = 1 -----");
        Filmografia actualizar = new Filmografia(
                1,
                "El viaje (Editada)",
                Date.valueOf("2020-05-10"),
                "Sinopsis actualizada",
                1,
                2
        );
        dao_film.update(actualizar);

        // ELIMINAR UNA PELÍCULA
        System.out.println("\n----- ELIMINAR PELÍCULA CON ID = 5 -----");
        dao_film.delete(5);

        // LISTAR DE NUEVO
        System.out.println("\n----- LISTA FINAL DE PELÍCULAS -----");
        dao_film.listAll();
    }
}


