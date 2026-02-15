package DAOs;

import DbManager.DbManager;
import Models.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO extends DAO<Genero> {

    // ATRIBUTOS QUERIES
    private static final String LISTALL = "SELECT * FROM Pais";
    private static final String LISTONE = "SELECT * FROM Pais WHERE id = ?";
    private static final String INSERT = "INSERT INTO Pais (nombre) VALUES (?)";
    private static final String UPDATE = "UPDATE Pais SET nombre=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Pais WHERE id=?";

    // CONSTRUCTOR
    public GeneroDAO() {
        super(); //Hereda db y logger
    }

    // FUNCION CREAR GENERO
    public Genero crearGenero(ResultSet rs) throws SQLException {
        try {
            return new Genero(
                    rs.getInt("id"),
                    rs.getString("nombre")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir ResultSet a Genero: " + e.getMessage());
        }
    }

    // FUNCION CARGAR DATOS
    @Override
    protected void cargarDatos(PreparedStatement stmnt, Genero g) throws SQLException {
        stmnt.setString(1, g.getNombre());
}

    // LISTAR GENEROS
    @Override
    public List<Genero> listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<Genero> arrayGenero = new ArrayList<>();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();

            while (rs.next()) {
                Genero g = crearGenero(rs);
                arrayGenero.add(g);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return arrayGenero;
    }

    // LISTAR UN GENERO POR ID
    @Override
    public Genero listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Genero g = new Genero();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();
            g = crearGenero(rs);
            
        } catch (SQLException e) {
            System.out.println("Error LISTONE: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return g;
    }

    // INSERTAR GENERO
    @Override
    public void insert(Genero g) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);

            cargarDatos(stmnt, g); 

            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Genero insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error INSERT: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ACTUALIZAR GENERO
    @Override
    public void update(Genero g) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, g); 
            stmnt.setInt(2, g.getId()); 
            int filasAfectadas = stmnt.executeUpdate();
            db.getConexion().commit();
            
            if (filasAfectadas > 0) {
                System.out.println("Genero actualizado correctamente.");
            } else {
                System.out.println("No se encontró el genero con ID: " + g.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ELIMINAR GENERO
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
                System.out.println("Genero eliminado correctamente.");
            } else {
                System.out.println("No se encontró el genero con ID: " + id);
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
