/*package DAOs;

import DbManager.DbManager;
import Models.Clasificacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClasificacionDAO extends DAO<Clasificacion> {

    private static final String LISTALL = "SELECT * FROM Clasificacion";
    private static final String LISTONE = "SELECT * FROM Clasificacion WHERE id = ?";
    private static final String INSERT = "INSERT INTO Clasificacion (nombre) VALUES (?)";
    private static final String UPDATE = "UPDATE Clasificacion SET nombre=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Clasificacion WHERE id=?";

    public ClasificacionDAO() {
        super(); // Hereda db y logger de DAO
    }

    // Método para crear el objeto desde el ResultSet
    private Clasificacion crearClasificacion(ResultSet rs) throws SQLException {
        return new Clasificacion(rs.getInt("id"), rs.getString("nombre"));
    }

    @Override
    protected void cargarDatos(PreparedStatement stmnt, Clasificacion c) throws SQLException {
        stmnt.setString(1, c.getNombre());
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
                System.out.println(crearClasificacion(rs));
            }
        } catch (SQLException e) {
            logger.error("Error LISTALL Clasificacion: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();
            if (rs.next()) {
                System.out.println(crearClasificacion(rs));
            }
        } catch (SQLException e) {
            logger.error("Error LISTONE Clasificacion: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void insert(Clasificacion c) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);
            cargarDatos(stmnt, c);
            stmnt.executeUpdate();
            db.getConexion().commit();
        } catch (SQLException e) {
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    @Override
    public void update(Clasificacion c) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, c);
            stmnt.setInt(2, c.getId());
            stmnt.executeUpdate();
            db.getConexion().commit();
        } catch (SQLException e) {
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(DELETE);
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            db.getConexion().commit();
        } catch (SQLException e) {
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }
}*/
