
import DAOs.DAO;
import externalAPI.api;

import DAOs.FilmografiaDAO;
import Models.Filmografia;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
     
        Scanner scan = new Scanner(System.in);
        int marcar = -1;
        FilmografiaDAO dao_film = new FilmografiaDAO();
        String titulo = "";
        String fecha_estreno = "";
        String sinopsis = "";
        int pais_id = -1;
        int clasificacion_id = -1;

        /*while (marcar != 0) {
            System.out.print("Para la funcion LISTALL pulsa 1\n"
                    + "Para la funcion LISTONE pulsa 2\n"
                    + "Para la funcion INSERT pulsa 3\n"
                    + "Para la funcion UPDATE pulsa 4\n"
                    + "Para la funcion DELETE pulsa 5\n");
            
            try{
                marcar = scan.nextInt();
            } catch (Exception e) {
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
                    Filmografia nueva = new Filmografia();
                    dao_film.insert(nueva);
                    break;
                 case 4:
                    System.out.println("Funcion UPDATE");
                    System.out.println("Actualiza pelicula con ID");
                    Filmografia peli_actualizada = new Filmografia();
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

        }*/
        //INSERTAR UNA NUEVA PELÍCULA
        System.out.println("\n----- INSERTAR NUEVA PELÍCULA -----");
        String query = "Pitufos";
        JsonArray results = null;
        results = externalAPI.api.searchMovie(query);
        System.out.println("Resultados de busqueda");
        ArrayList<Filmografia> filmografias = new ArrayList<>();

        //Iteramos en los resultados cogiendo el titulo y su release_date
        for (int i = 0; i < results.size(); i++) {
            JsonObject movie = results.get(i).getAsJsonObject();
            Filmografia film = new Filmografia(movie.get("title").getAsString());
            dao_film.insert(film);

        }

        
    }//llave metodo

}//llave clase

