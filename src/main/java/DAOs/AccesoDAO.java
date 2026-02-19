/*package DAOs;

import DbManager.DbManager;
import Models.Acceso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoDAO extends DAO<Acceso> {

    private static final String LISTALL = "SELECT * FROM Acceso";
    private static final String LISTONE = "SELECT * FROM Acceso WHERE id_acceso = ?";
    private static final String INSERT = "INSERT INTO Acceso (id_filmografia, id_cuenta, fecha_acceso, tipo_suscripcion_id) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE Acceso SET id_filmografia=?, id_cuenta=?, fecha_acceso=?, tipo_suscripcion_id=? WHERE id_acceso=?";
    private static final String DELETE = "DELETE FROM Acceso WHERE id_acceso=?";

    public AccesoDAO() {
        super(); // Hereda db y logger de DAO
    }

    // Método para crear el objeto desde el ResultSet
    private Acceso crearAcceso (ResultSet rs) throws SQLException {
        return new Acceso(
                rs.getInt("id_acceso"),
                rs.getInt("id_filmografia"),
                rs.getInt("id_cuenta"),
                rs.getDate("fecha_acceso"), // SQL Date mapea bien el DATETIME (perdiendo la hora)
                rs.getInt("tipo_suscripcion_id")
        );
    }

    @Override
    protected void cargarDatos(PreparedStatement stmnt, Acceso a) throws SQLException {
        stmnt.setInt(1, a.getId_filmografia());
        stmnt.setInt(2, a.getId_cuenta());
        stmnt.setDate(3, a.getFecha_acceso());
        stmnt.setInt(4, a.getTipo_suscripcion_id());
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
                System.out.println(crearAcceso(rs));
            }
        } catch (SQLException e) {
            logger.error("Error LISTALL Acceso: " + e.getMessage());
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
                System.out.println(crearAcceso(rs));
            } else {
                System.out.println("Acceso no registrado.");
            }
        } catch (SQLException e) {
            logger.error("Error LISTONE Acceso: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void insert(Acceso a) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);
            cargarDatos(stmnt, a);
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Registro de acceso guardado.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error INSERT Acceso: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    @Override
    public void update(Acceso a) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, a);
            stmnt.setInt(5, a.getId_acceso());
            stmnt.executeUpdate();
            db.getConexion().commit();
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error UPDATE Acceso: " + e.getMessage());
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
            logger.error("Error DELETE Acceso: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

}*/
