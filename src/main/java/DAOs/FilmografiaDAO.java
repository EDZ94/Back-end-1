package DAOs;

import DbManager.DbManager;
import Models.Filmografia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmografiaDAO extends DAO<Filmografia> {

    // ATRIBUTOS QUERIES
    private static final String LISTALL = "SELECT * FROM Filmografia";
    private static final String LISTONE = "SELECT * FROM Filmografia WHERE id = ?";
    private static final String INSERT = "INSERT INTO Filmografia (titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Filmografia SET titulo=?, fecha_estreno=?, sinopsis=?, pais_id=?, clasificacion_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Filmografia WHERE id=?";

    // CONSTRUCTOR
    public FilmografiaDAO() {
        super(); //Hereda db y logger
    }

    // FUNCION CREAR FILMOGRAFÍA
    public Filmografia crearFilmografia(ResultSet rs) throws SQLException {
        try {
            return new Filmografia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getDate("fecha_estreno"),
                    rs.getString("sinopsis"),
                    rs.getInt("pais_id"),
                    rs.getInt("clasificacion_id")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir ResultSet a Filmografia: " + e.getMessage());
        }
    }

    // FUNCION CARGAR DATOS (Mapea los 5 campos comunes a INSERT y UPDATE)
    @Override
    protected void cargarDatos(PreparedStatement stmnt, Filmografia f) throws SQLException {
        stmnt.setString(1, f.getTitulo());
        stmnt.setDate(2, f.getFechaEstreno());
        stmnt.setString(3, f.getSinopsis());
        stmnt.setInt(4, f.getPaisId());
        stmnt.setInt(5, f.getClasificacionId());
    }

    // LISTAR LAS PELÍCULAS
    @Override
    public List<Filmografia> listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<Filmografia> arrayFilmo = new ArrayList<>();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();

            while (rs.next()) {
                Filmografia f = crearFilmografia(rs);
                arrayFilmo.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return arrayFilmo;
    }

    // LISTAR UNA PELÍCULA POR ID
    @Override
    public Filmografia listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Filmografia filmo = new Filmografia();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();
            filmo = crearFilmografia(rs);
            
        } catch (SQLException e) {
            System.out.println("Error LISTONE: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return filmo;
    }

    // INSERTAR PELÍCULA
    @Override
    public void insert(Filmografia f) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);

            cargarDatos(stmnt, f); // Carga parámetros del 1 al 5

            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Película insertada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error INSERT: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ACTUALIZAR PELÍCULA
    @Override
    public void update(Filmografia f) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, f); // Carga parámetros del 1 al 5
            stmnt.setInt(6, f.getId()); // Carga el parámetro 6
            int filasAfectadas = stmnt.executeUpdate();
            db.getConexion().commit();
            
            if (filasAfectadas > 0) {
                System.out.println("Película actualizada correctamente.");
            } else {
                System.out.println("No se encontró la película con ID: " + f.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ELIMINAR PELÍCULA
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(DELETE);
            stmnt.setInt(1, id);
            int filasAfectadas = stmnt.executeUpdate();
            db.getConexion().commit();
            
            if (filasAfectadas > 0) {
                System.out.println("Película eliminada correctamente.");
            } else {
                System.out.println("No se encontró la película con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error DELETE: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }
}