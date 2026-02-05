package DAOs;

import DbManager.DbManager;
import Models.Filmografia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmografiaDAO {

    // ATRIBUTO CONEXIÓN
    private DbManager db;

    // INSERT
    private static final String INSERT
            = "INSERT INTO Filmografia (titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id) VALUES (?,?,?,?,?)";

    // DELETE
    private static final String DELETE
            = "DELETE FROM Filmografia WHERE id = ?";

    // UPDATE
    private static final String UPDATE
            = "UPDATE Filmografia SET titulo = ?, fecha_estreno = ?, sinopsis = ?, pais_id = ?, clasificacion_id = ? WHERE id = ?";

    // LIST ALL
    private static final String LISTALL
            = "SELECT * FROM Filmografia";

    // LIST ONE
    private static final String LISTONE
            = "SELECT * FROM Filmografia WHERE id = ?";

    // CONSTRUCTOR
    public FilmografiaDAO() {
        db = new DbManager();
    }

    // MÉTODO LISTALL
    public List<Filmografia> listAll() {

        List<Filmografia> lista = new ArrayList<>();

        try {
            db.conectar();

            PreparedStatement stmt = db.getConexion().prepareStatement(LISTALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Filmografia f = new Filmografia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_estreno"),
                        rs.getString("sinopsis"),
                        rs.getInt("pais_id"),
                        rs.getInt("clasificacion_id")
                );

                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL Filmografia: " + e.getMessage());
        } finally {
            db.desconectar();
        }

        return lista;
    }

    // METODO LISTONE
    public Filmografia listOne(int id) {
        Filmografia f = null; // Inicialmente lo dejo null

        try {
            db.conectar();

            PreparedStatement stmt = db.getConexion().prepareStatement(LISTONE);
            stmt.setInt(1, id); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { 
                f = new Filmografia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_estreno"),
                        rs.getString("sinopsis"),
                        rs.getInt("pais_id"),
                        rs.getInt("clasificacion_id")
                );
            }

        } catch (Exception e) {
            System.out.println("Error LISTONE Filmografia: " + e.getMessage());
        } finally {
            db.desconectar();
        }

        return f;
    }
}
