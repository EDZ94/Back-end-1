import DAOs.FilmografiaDAO;
import Models.Filmografia;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FilmografiaDAO dao = new FilmografiaDAO();

        //LISTALL
        System.out.println("Lista de todas las películas");
        List<Filmografia> filmografias = dao.listAll();
        for (Filmografia f : filmografias) {
            System.out.println(f);
        }
        //LISTONE
        System.out.println("Buscar película con ID = 3"); // Puedo cambiar el ID
        Filmografia peli = dao.listOne(3);

        if (peli != null) {
            System.out.println(peli);
        } else {
            System.out.println("No se encontró la película con ese ID");
        }
    }
}
