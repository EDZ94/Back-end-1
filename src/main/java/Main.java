import externalAPI.api;

import DAOs.FilmografiaDAO;
import Models.Filmografia;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.SQLException;

//public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        /*Scanner scan = new Scanner(System.in);
        int marcar = -1;
        FilmografiaDAO dao_film = new FilmografiaDAO();
        
        while (marcar != 0) {
            System.out.print("Para la funcion LISTALL pulsa 1\n"
                    + "Para la funcion LISTONE pulsa 2\n"
                    + "Para la funcion INSERT pulsa 3\n"
                    + "Para la funcion UPDATE pulsa 4\n"
                    + "Para la funcion DELETE pulsa 5\n");
            
            try{
                marcar = scan.nextInt();
            } catch (SQLException e) {
                System.out.println("Introduce un numero");
                        scan.nextLine();
                        marcar = -1;
            }
            switch (marcar){
                case 1:
                    System.out.println("Funcion LISTALL");
                    dao_film.listAll();
                    break;
                case 2:
                    System.out.println("Funcion LISTONE");
                    dao_film.listOne(3);
                    break;
                 case 3:
                    System.out.println("Funcion INSERT");
                    System.out.println("Inserta una nueva pelicula");
                    Filmografia nueva = new Filmografia(marcar, titulo, fechaEstreno, sinopsis, marcar, marcar);
                    dao_film.insert(nueva);
                    break;
                 case 4:
                    System.out.println("Funcion UPDATE");
                    System.out.println("Actualiza pelicula con ID");
                    Filmografia peli_actualizada = new Filmografia(marcar, titulo, fechaEstreno, sinopsis, marcar, marcar);
                    dao_film.update(peli_actualizada);
                    break;
                 case 5:
                    System.out.println("Funcion DELETE");
                    System.out.println("Elimina pelicula con ID");
                    dao_film.delete(5);  
                    break;
                 case 0:
                     System.out.println("No quieres utilizar ninguna funcion");
                     break;
                 default:
                     System.out.println("Te has equivocado de numero");                   
                    
            }

        }
        
                
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
    }*/
        
    
        String query = "Pitufos";
        externalAPI.api.searchMovie(query);
        
        
    
    
}//llave main


