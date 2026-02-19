package externalAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api {
    
    //ATRIBUTOS: CLAVE Y URL
    private static final String API_KEY = "e3670b56d2138d6d5cf1b0cf25a0863c";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    
    
    // Meterlo en .env
    
    
    //Este metodo va a ser nuestro listone pero en vez de buscar en la bbdd, va a buscar en la api externa
    public static JsonArray searchMovie(String query) throws IOException{
        
        //Creamos un cliente http para hacer la peticion a la api es necesario para enviar la solicitud y recibir la respuesta
        OkHttpClient client = new OkHttpClient();
        
        //Codificamos el nombre de la pelicula para que sea segura para usar en una URL es necsario para manejar caracteres especiales
        // la URL podria ser invalida y la peticion fallaria ademas evitamos problemas de seguridad como inyecciones de codigo 
        //Con el codequery codifico la clave para evitar errores, borrar espacios, evita deletes...
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        
        //Construimos la URL de la peticion, incluyendo la API KEY y el nombre de la pelicula codificado
        String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + encodedQuery;
        
        //Creamos un objeto de tipo request que define la url usando un patron builder (get por defecto)
        Request request = new Request.Builder().url(url).build();
        JsonArray results = null;
        
        try (Response response = client.newCall(request).execute()){
            
            //Si falla paramos el programa aqui
            if (!response.isSuccessful()){
                System.out.println("Error en la peticion: " + response);
                return results;
            }
            
            //Creamos el objeto String de la response que nos ha llegado. Si todo va bien, leemos la respuesta (viene en formato Json) y la guardamos en un String para luego pasarsela a un JsonObjetct
            String responseBody = response.body().string();
            
            //Creamos un objeto Json, y el String de la respuesta la convertimos en objeto JSON
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            
            //Obtenemos un resultado en un Array de JSON, cada elemento del array representa una pelicula 
            results = json.getAsJsonArray("results");
                        
        }
        return results;
        
        
    }
    
}
