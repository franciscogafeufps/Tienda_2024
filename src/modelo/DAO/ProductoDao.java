
package modelo.DAO;

import servicio.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;


public class ProductoDao {
    
    Connection conexion;
    
    public ProductoDao() {
        try {
            this.conexion = Conexion.obtener();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void guardar(Producto producto) {
    PreparedStatement consulta = null;
    try {
        consulta = conexion.prepareStatement("INSERT INTO producto (nombre, descripcion, precio, proveedor_id) VALUES (?, ?, ?, ?)");
        consulta.setString(1, producto.getNombre());
        consulta.setString(2, producto.getDescripcion());
        consulta.setDouble(3, producto.getPrecio());
        consulta.setInt(4, producto.getProveedor());
        consulta.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException("Error al guardar el producto en la base de datos.", ex);
    } finally {
        try {
            if (consulta != null) {
                consulta.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    
}
