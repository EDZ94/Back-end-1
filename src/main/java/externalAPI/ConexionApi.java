package ExternalAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConexionApi {

    // ATRIBUTOS DE LA CLASE
    private static final String API_KEY = "e3670b56d2138d6d5cf1b0cf25a0863c";
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    public static void searchMovie(String query) throws IOException {

        OkHttpClient client = new OkHttpClient();

        // 1. Codificar el texto de búsqueda (por si tiene espacios)
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        // 2. Construir la URL correctamente usando las variables
        String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + encodedQuery;

        // 3. Crear la petición
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 4. Ejecutar la petición (OJO: Sin punto y coma al final del try)
        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                System.out.println("Error en la petición: " + response.code());
                return;
            }

            String responseBody = response.body().string();

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            JsonArray results = json.getAsJsonArray("results");
            System.out.println("Resultados de búsqueda");

            for (int i = 0; i < results.size(); i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                System.out.println("- " + movie.get("title").getAsString() + " (" + movie.get("release_date").getAsString() + ")");
            }

        }
    }
}
