
package modelo.DAO;

import modelo.Cliente;
import servicio.Conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao {
    
    private Connection conexion;

    public ClienteDao() {
        try {
            this.conexion = Conexion.obtener();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Método para guardar un cliente en la base de datos
    public void guardar(Cliente cliente) {
        PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement("INSERT INTO cliente (cedula, nombre, email, telefono, quien_recomienda) VALUES (?, ?, ?, ?, ?)");
            consulta.setString(1, cliente.getCedula());
            consulta.setString(2, cliente.getNombre());
            consulta.setString(3, cliente.getEmail());
            consulta.setString(4, cliente.getTelefono());
            consulta.setString(5, cliente.getQuienRecomienda());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (consulta != null) consulta.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para actualizar un cliente
    public void actualizar(Cliente cliente) {
        PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement("UPDATE cliente SET nombre = ?, email = ?, telefono = ?, quien_recomienda = ? WHERE cedula = ?");
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getEmail());
            consulta.setString(3, cliente.getTelefono());
            consulta.setString(4, cliente.getQuienRecomienda());
            consulta.setString(5, cliente.getCedula());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (consulta != null) consulta.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para eliminar un cliente por cédula
    public void eliminar(String cedula) {
        PreparedStatement consulta = null;
        try {
            consulta = conexion.prepareStatement("DELETE FROM cliente WHERE cedula = ?");
            consulta.setString(1, cedula);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (consulta != null) consulta.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para obtener un cliente por cédula
    public Cliente obtenerPorCedula(String cedula) {
        Cliente cliente = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM cliente WHERE cedula = ?");
            consulta.setString(1, cedula);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente(
                    resultado.getString("cedula"),
                    resultado.getString("nombre"),
                    resultado.getString("email"),
                    resultado.getString("telefono"),
                    resultado.getString("quien_recomienda")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (consulta != null) consulta.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
}

