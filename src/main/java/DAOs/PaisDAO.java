package DAOs;

import DbManager.DbManager;
import Models.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO extends DAO<Pais>{

    // ATRIBUTOS QUERIES
    private static final String LISTALL = "SELECT * FROM Pais";
    private static final String LISTONE = "SELECT * FROM Pais WHERE id = ?";
    private static final String INSERT = "INSERT INTO Pais (nombre) VALUES (?)";
    private static final String UPDATE = "UPDATE Pais SET nombre=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Pais WHERE id=?";

    // CONSTRUCTOR
    public PaisDAO() {
        super(); //Hereda db y logger
    }

    // FUNCION CREAR PAIS
    public Pais crearPais(ResultSet rs) throws SQLException {
        try {
            return new Pais(
                    rs.getInt("id"),
                    rs.getString("nombre")
            );
        } catch (SQLException e) {
            throw new SQLException("Error al convertir ResultSet a Pais: " + e.getMessage());
        }
    }
    
    // FUNCION CARGAR DATOS
    @Override
    protected void cargarDatos(PreparedStatement stmnt, Pais p) throws SQLException {
        stmnt.setString(1, p.getNombre());
}

    // LISTAR PAISES
    @Override
    public List<Pais> listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        List<Pais> arrayPais = new ArrayList<>();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();

            while (rs.next()) {
                Pais p = crearPais(rs);
                arrayPais.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return arrayPais;
    }

    // LISTAR UN PAIS POR ID
    @Override
    public Pais listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Pais p = new Pais();

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();
            p = crearPais(rs);

        } catch (SQLException e) {
            System.out.println("Error LISTONE: " + e.getMessage());
        } finally {
            cerrarEstados(stmnt, rs);
            db.desconectar();
        }
        return p;
    }

    // INSERTAR PAIS
    @Override
    public void insert(Pais p) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);

            cargarDatos(stmnt, p); 

            stmnt.executeUpdate();
            db.getConexion().commit();
            System.out.println("Pais insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error INSERT: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ACTUALIZAR PAIS
    @Override
    public void update(Pais p) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);
            cargarDatos(stmnt, p); 
            stmnt.setInt(2, p.getId()); 
            int filasAfectadas = stmnt.executeUpdate();
            db.getConexion().commit();

            if (filasAfectadas > 0) {
                System.out.println("Pais actualizado correctamente.");
            } else {
                System.out.println("No se encontró el pais con ID: " + p.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
            hacerRollback(db);
        } finally {
            cerrarEstados(stmnt, null);
            db.desconectar();
        }
    }

    // ELIMINAR PAIS
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
                System.out.println("Pais eliminado correctamente.");
            } else {
                System.out.println("No se encontró el pais con ID: " + id);
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
