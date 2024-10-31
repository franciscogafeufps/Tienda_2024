
package modelo.DAO;


import servicio.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
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
    
    // Método para obtener todos los productos
       public List<Producto> obtenerTodos() throws SQLException, ClassNotFoundException {
        List<Producto> productos = new ArrayList<>();
        Connection conexion = Conexion.obtener();
        PreparedStatement seleccionar = conexion.prepareStatement("SELECT id, nombre, descripcion, precio, proveedor_id FROM producto");
        ResultSet consulta = seleccionar.executeQuery();

       while (consulta.next()) {
            Producto producto = new Producto();
            producto.setCodigo(consulta.getInt("id")); // Asegúrate que el nombre es correcto
            producto.setNombre(consulta.getString("nombre"));
            producto.setDescripcion(consulta.getString("descripcion"));
            producto.setPrecio(consulta.getDouble("precio"));
            producto.setProveedor(consulta.getInt("proveedor_id"));
            productos.add(producto);
       }

           return productos;
       }


    
}
