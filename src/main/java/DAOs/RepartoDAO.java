package DAOs;

import DbManager.DbManager;
import Models.Reparto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartoDAO extends DAO<Reparto> {

    // ATRIBUTOS QUERIES
    private static final String LISTALL = "SELECT * FROM Reparto";
    private static final String LISTONE = "SELECT * FROM Reparto WHERE id_reparto = ?";
    private static final String INSERT = "INSERT INTO Reparto (id_filmografia, nombre_actor, papel) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Reparto SET id_filmografia=?, SET nombre_actor=?, SET papel=? WHERE id_reparto=?";
    private static final String DELETE = "DELETE FROM Reparto WHERE id_reparto=?";

    // CONSTRUCTOR
    public RepartoDAO() throws SQLException {
        super(); //Hereda db y logger
    }

    // FUNCION CREAR REPARTO
    public Reparto crearReparto(ResultSet rs) throws SQLException {
        try {
            return new Reparto(
                    rs.getInt("id_reparto"),
                    rs.getInt("id_filmografia"),
                    rs.getString("nombre_actor"),
                    rs.getString("papel")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir ResultSet a Reparto: " + e.getMessage());
        }
    }

    // FUNCION CARGAR DATOS 
    @Override
    protected void cargarDatos(PreparedStatement stmnt, Reparto r) throws SQLException {
        stmnt.setInt(1, r.getId_filmografia());
        stmnt.setString(2, r.getNombre_actor());
        stmnt.setString(3, r.getPapel());
    }

    // LISTAR REPARTO
    @Override
    public List<Reparto> listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<Reparto> arrayReparto = new ArrayList<>();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();

            while (rs.next()) {
                Reparto r = crearReparto(rs);
                arrayReparto.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return arrayReparto;
    }

    // LISTAR REPARTO POR ID
    @Override
    public Reparto listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Reparto r = new Reparto();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();
            r = crearReparto(rs);

        } catch (SQLException e) {
            System.out.println("Error LISTONE: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return r;
    }

    // INSERTAR REPARTO
    @Override
    public void insert(Reparto r) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);

            cargarDatos(stmnt, r);

            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Reparto insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error INSERT: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ACTUALIZAR REPARTO
    @Override
    public void update(Reparto r) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, r);
            stmnt.setInt(4, r.getId_reparto());
            int filasAfectadas = stmnt.executeUpdate();
            db.getConexion().commit();

            if (filasAfectadas > 0) {
                System.out.println("Reparto actualizada correctamente.");
            } else {
                System.out.println("No se encontró la película con ID: " + r.getId_reparto());
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ELIMINAR REPARTO
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
                System.out.println("Reparto eliminado correctamente.");
            } else {
                System.out.println("No se encontró el reparto con ID: " + id);
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
