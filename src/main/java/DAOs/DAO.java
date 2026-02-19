package DAOs;

import DbManager.DbManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Configuracion.Logger;
import java.util.ArrayList;
import java.util.List;


public abstract class DAO<T> {

    //Atributos
    protected DbManager db;
    protected Logger logger = new Logger();

    //Constructor
    public DAO() {
        db = new DbManager();
    }

    public DbManager getDb() {
        return db;
    }
    
    
    //Funciones que he implementado
    protected void cerrarEstados(PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (stmt != null) try {
            stmt.close();
        } catch (SQLException e) {
            logger.error("Error al cerrar Statement. ");
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
            logger.error("Error al cerrar Rollback. ");
            throw new SQLException("Error al hacer Rollback. ", e);
        }
    }

    //Metodos
    public abstract List<T> listAll() throws SQLException; // devuelve una lista T

    public abstract T listOne(int id) throws SQLException; //devuelve un T

    public abstract void insert(T dato_insert) throws SQLException;

    public abstract void update(T dato_update) throws SQLException;

    public abstract void delete(int id) throws SQLException;

} //Llave clase


