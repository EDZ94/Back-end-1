package DAOs;

import Models.FilmGenero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmGeneroDAO extends DAO<FilmGenero> {

    private static final String LISTALL = "SELECT * FROM Film_Genero";
    private static final String LISTBYFILM = "SELECT * FROM Film_Genero WHERE film_id = ?";
    private static final String INSERT = "INSERT INTO Film_Genero (film_id, genero_id) VALUES (?,?)";
    private static final String DELETE_ALL_FROM_FILM = "DELETE FROM Film_Genero WHERE film_id = ?";

    public FilmGeneroDAO() {
        super(); // Hereda db y logger de DAO
    }

    // Método para crear el objeto desde el ResultSet
    private FilmGenero crearFIlmGenero (ResultSet rs) throws SQLException {
        return new FilmGenero(
            rs.getInt("film_id"),
            rs.getInt("genero_id")
        );
    }

    @Override
    protected void cargarDatos(PreparedStatement stmnt, FilmGenero fg) throws SQLException {
        stmnt.setInt(1, fg.getFilmId());
        stmnt.setInt(2, fg.getGeneroId());
    }

    @Override
    public void listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();
            while (rs.next()) {
                System.out.println("Film ID: " + rs.getInt("film_id") + " - Genero ID: " + rs.getInt("genero_id"));
            }
        } catch (SQLException e) {
            logger.error("Error LISTALL FilmGenero: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void listOne(int id_film) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTBYFILM);
            stmnt.setInt(1, id_film);
            rs = stmnt.executeQuery();
            System.out.println("Géneros para la película ID " + id_film + ":");
            while (rs.next()) {
                System.out.println("- ID Género: " + rs.getInt("genero_id"));
            }
        } catch (SQLException e) {
            logger.error("Error al buscar géneros del film " + id_film + ": " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void insert(FilmGenero fg) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);
            cargarDatos(stmnt, fg);
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Relación Película-Género añadida.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al insertar FilmGenero: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // En una tabla intermedia, el UPDATE suele ser borrar y volver a insertar, 
    // pero para cumplir con tu estructura, lo dejamos vacío o lanzamos aviso.
    @Override
    public void update(FilmGenero fg) throws SQLException {
        System.out.println("Update no disponible para tabla intermedia. Se recomienda delete e insert.");
    }

    @Override
    public void delete(int id_film) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(DELETE_ALL_FROM_FILM);
            stmnt.setInt(1, id_film);
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Se han quitado todos los géneros de la película ID: " + id_film);
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al eliminar géneros del film: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }
}