package DAOs;

import Models.Cuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO extends DAO<Cuenta> {

    private static final String LISTALL = "SELECT * FROM Cuenta";
    private static final String LISTONE = "SELECT * FROM Cuenta WHERE id_cuenta = ?";
    private static final String INSERT = "INSERT INTO Cuenta (tipo_cuenta, nombre, password_hash) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE Cuenta SET tipo_cuenta=?, nombre=?, password_hash=? WHERE id_cuenta=?";
    private static final String DELETE = "DELETE FROM Cuenta WHERE id_cuenta=?";

    public CuentaDAO() {
        super(); // Hereda db y logger de DAO
    }

    // Método para crear el objeto desde el ResultSet
    private Cuenta crearCuenta(ResultSet rs) throws SQLException {
        return new Cuenta(
                rs.getInt("id_cuenta"),
                rs.getString("tipo_cuenta"),
                rs.getString("nombre"),
                rs.getString("password_hash")
        );
    }

    @Override
    protected void cargarDatos(PreparedStatement stmnt, Cuenta c) throws SQLException {
        stmnt.setString(1, c.getTipo_cuenta());
        stmnt.setString(2, c.getNombre());
        stmnt.setString(3, c.getPassword_hash());
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
                System.out.println(crearCuenta(rs));
            }
        } catch (SQLException e) {
            logger.error("Error al listar todas las cuentas: " + e.getMessage());
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
                System.out.println(crearCuenta(rs));
            } else {
                System.out.println("No se encontró la cuenta con ID: " + id);
            }
        } catch (SQLException e) {
            logger.error("Error al buscar la cuenta " + id + ": " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void insert(Cuenta c) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);
            cargarDatos(stmnt, c);
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Cuenta de " + c.getNombre() + " insertada con éxito.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al insertar cuenta: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    @Override
    public void update(Cuenta c) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, c);
            stmnt.setInt(4, c.getId_cuenta()); // El ID es el último parámetro
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Cuenta ID " + c.getId_cuenta() + " actualizada.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al actualizar cuenta: " + e.getMessage());
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
            int rows = stmnt.executeUpdate();
            db.getConexion().commit();
            if (rows > 0) {
                System.out.println("Cuenta eliminada.");
            }
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al eliminar cuenta: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }
}
