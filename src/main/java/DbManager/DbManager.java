package DbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DbManager {
    
    // ATRIBUTOS
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USUARIO = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASS");
    // ATRIBUTO CONECTION
    private Connection conexion;

    // GET
    public Connection getConexion() {
    return conexion;
}

    // METODOS
    public void conectar() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, PASS);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Desconexión exitosa ");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }

}
