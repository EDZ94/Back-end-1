/*package DAOs;

import Models.Factura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDAO extends DAO<Factura> {

    private static final String LISTALL = "SELECT * FROM Factura";
    private static final String LISTONE = "SELECT * FROM Factura WHERE num_factura = ?";
    private static final String INSERT = "INSERT INTO Factura (suscripcion_id, cuenta_id, importe_pvp, metodo_pago, fecha_factura) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Factura SET suscripcion_id=?, cuenta_id=?, importe_pvp=?, metodo_pago=?, fecha_factura=? WHERE num_factura=?";
    private static final String DELETE = "DELETE FROM Factura WHERE num_factura = ?";

    public FacturaDAO() {
        super(); // Hereda db y logger de DAO
    }

    // Método para crear el objeto desde el ResultSet
    private Factura crearFactura (ResultSet rs) throws SQLException {
        return new Factura(
                rs.getInt("num_factura"),
                rs.getInt("suscripcion_id"),
                rs.getInt("cuenta_id"),
                rs.getDouble("importe_pvp"),
                rs.getString("metodo_pago"),
                rs.getDate("fecha_factura")
        );
    }

    @Override
    protected void cargarDatos(PreparedStatement stmnt, Factura f) throws SQLException {
        stmnt.setInt(1, f.getSuscripcion_id());
        stmnt.setInt(2, f.getCuenta_id());
        stmnt.setDouble(3, f.getImporte_pvp());
        stmnt.setString(4, f.getMetodo_pago());
        stmnt.setDate(5, f.getFecha_factura());
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
                System.out.println(crearFactura(rs));
            }
        } catch (SQLException e) {
            logger.error("Error LISTALL Factura: " + e.getMessage());
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
                System.out.println(crearFactura(rs));
            } else {
                System.out.println("Factura nº " + id + " no encontrada.");
            }
        } catch (SQLException e) {
            logger.error("Error LISTONE Factura: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
    }

    @Override
    public void insert(Factura f) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);
            cargarDatos(stmnt, f);
            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Factura generada correctamente.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al insertar factura: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    @Override
    public void update(Factura f) throws SQLException {
        PreparedStatement stmnt = null;
        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, f);
            stmnt.setInt(6, f.getNum_factura()); // El ID es el sexto parámetro
            stmnt.executeUpdate();
            db.getConexion().commit();
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al actualizar factura: " + e.getMessage());
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
            System.out.println("Factura eliminada.");
        } catch (SQLException e) {
            hacerRollback(db);
            logger.error("Error al eliminar factura: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }
}*/
