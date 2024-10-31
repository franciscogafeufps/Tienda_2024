package servicio;

import java.sql.*;

public class Conexion {
    
    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "");
            } catch (SQLException | ClassNotFoundException ex) {
                throw ex;
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
            cnx = null;
        }
    }
}
