package DAOs;

import DbManager.DbManager;
import Models.Filmografia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class FilmografiaDAO {

    // ATRIBUTO CONEXION
    private DbManager db;

    // ATRIBUTOS QUERIES
    private static final String LISTALL = "SELECT * FROM Filmografia";
    private static final String LISTONE = "SELECT * FROM Filmografia WHERE id = ?";
    private static final String INSERT = "INSERT INTO Filmografia (titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Filmografia SET titulo=?, fecha_estreno=?, sinopsis=?, pais_id=?, clasificacion_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Filmografia WHERE id=?";

    // CONSTRUCTOR
    public FilmografiaDAO() {
        db = new DbManager();
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

    // FUNCION CERRAR ESTADOS
    private void cerrarEstado(PreparedStatement stmnt, ResultSet rs) throws SQLException {
        try {
            if (rs != null) rs.close();
            if (stmnt != null) stmnt.close();
        } catch (SQLException e) {
            throw new SQLException("Error al cerrar recursos: " + e.getMessage());
        }
    }

    // LISTAR LAS PELÍCULAS
    public void listAll() throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTALL);
            rs = stmnt.executeQuery();

            while (rs.next()) {
                Filmografia f = crearFilmografia(rs);
                System.out.println(f);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTALL: " + e.getMessage());
        } finally {
            cerrarEstado(stmnt, rs);
            db.desconectar();
        }
    }

    // LISTAR UNA PELÍCULA POR ID
    public void listOne(int id) throws SQLException {
        PreparedStatement stmnt = null;
        ResultSet rs = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(LISTONE);
            stmnt.setInt(1, id);
            rs = stmnt.executeQuery();

            if (rs.next()) {
                Filmografia f = crearFilmografia(rs);
                System.out.println(f);
            } else {
                System.out.println("No se encontró la película con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error LISTONE: " + e.getMessage());
        } finally {
            cerrarEstado(stmnt, rs);
            db.desconectar();
        }
    }

    // INSERTAR PELÍCULA
    public void insert(Filmografia f) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(INSERT);

            stmnt.setString(1, f.getTitulo());
            stmnt.setDate(2, f.getFechaEstreno());
            stmnt.setString(3, f.getSinopsis());
            stmnt.setInt(4, f.getPaisId());
            stmnt.setInt(5, f.getClasificacionId());

            stmnt.executeUpdate();
            System.out.println("Película insertada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error INSERT: " + e.getMessage());
        } finally {
            cerrarEstado(stmnt, null);
            db.desconectar();
        }
    }

    // ACTUALIZAR PELÍCULA
    public void update(Filmografia f) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(UPDATE);

            stmnt.setString(1, f.getTitulo());
            stmnt.setDate(2, f.getFechaEstreno());
            stmnt.setString(3, f.getSinopsis());
            stmnt.setInt(4, f.getPaisId());
            stmnt.setInt(5, f.getClasificacionId());
            stmnt.setInt(6, f.getId());

            int rows = stmnt.executeUpdate();
            if (rows > 0) {
                System.out.println("Película actualizada correctamente.");
            } else {
                System.out.println("No se encontró la película con ID: " + f.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error UPDATE: " + e.getMessage());
        } finally {
            cerrarEstado(stmnt, null);
            db.desconectar();
        }
    }

    // ELIMINAR PELÍCULA
    public void delete(int id) throws SQLException {
        PreparedStatement stmnt = null;

        try {
            db.conectar();
            stmnt = db.getConexion().prepareStatement(DELETE);
            stmnt.setInt(1, id);

            int rows = stmnt.executeUpdate();
            if (rows > 0) {
                System.out.println("Película eliminada correctamente.");
            } else {
                System.out.println("No se encontró la película con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error DELETE: " + e.getMessage());
        } finally {
            cerrarEstado(stmnt, null);
            db.desconectar();
        }
    }

}


/*
private void cerrarEstado(PreparedStatement stmnt, ResultSet rs) {
    try {
        if (rs != null) rs.close();
        if (stmnt != null) stmnt.close();
    } catch (SQLException e) {
        System.out.println("Error al cerrar recursos: " + e.getMessage());
    }
}
//ANTES
finally {
    try {
        if (rs != null) rs.close();
        if (stmnt != null) stmnt.close();
        db.desconectar();
    } catch (SQLException e) {
        System.out.println("Error cerrando recursos: " + e.getMessage());
    }
}

//DESPUES
finally {
    cerrarEstado(stmnt, rs);
    db.desconectar();
}

*/