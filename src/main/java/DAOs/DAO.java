package DAOs;

import DbManager.DbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class DAO<T> {

    //Atributos
    protected DbManager db;

    //Constructor
    public DAO() {
        db = new DbManager();
    }

    //Funciones que he implementado
    protected void cerrarEstados(PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (stmt != null) try {
            stmt.close();
        } catch (SQLException e) {
            throw new SQLException("Error al cerrar Statement. ", e);
        }
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            throw new SQLException("Error al cerrar ResultSet. ", e);
        }
     }

    protected abstract void cargarDatos(PreparedStatement stmt, T dato) throws SQLException;

    protected void hacerRollback(DbManager db) throws SQLException {
        try {
            db.getConexion().rollback();
        } catch (SQLException e) {
            throw new SQLException("Error al hacer Rollback. ", e);
        }
    }

    //Metodos
    public abstract void listAll() throws SQLException;

    public abstract void listOne(int id) throws SQLException;

    public abstract void insert(T dato_insert) throws SQLException;

    public abstract void update(T dato_update) throws SQLException;

    public abstract void delete(int id) throws SQLException;

} //Llave clase


