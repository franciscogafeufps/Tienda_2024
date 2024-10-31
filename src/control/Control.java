
package control;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;

import modelo.DAO.ProductoDao;
import modelo.DAO.ClienteDao;
import servicio.Conexion;

import vista.Formulario;



public class Control implements ActionListener{
    
    private Cliente c;
    private Producto p;
    private Venta v;
    private Formulario form;
    

    public Control(Cliente c, Producto p, Venta v, Formulario form) {
        this.c = c;
        this.p = p;
        this.v = v;
        this.form = form;
    }

    public Control(Formulario form) {
        this.form = form;
        this.actionListener();
    }

    private void actionListener() {
        System.out.println("Si esta escuchando");
        this.form.btnGuardarProducto.addActionListener(this);
        this.form.btnLimpiarP.addActionListener(this);
        this.form.btnGuardarCliente.addActionListener(this);
        this.form.btnLimpiarC.addActionListener(this);
        this.form.btnGuardarVenta.addActionListener(this);
        this.form.btnMostar.addActionListener(this);
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contentEquals("Guardar Producto")) {
            try {
                // Crear un nuevo objeto producto a partir de los datos del formulario
                Producto nuevoProducto = new Producto();
                nuevoProducto.setCodigo(Integer.parseInt(form.txtCodigo.getText())); // Asegúrate de que el texto sea un número válido
                nuevoProducto.setNombre(form.txtNombre.getText());
                nuevoProducto.setDescripcion(form.txtDescripcion.getText());
                nuevoProducto.setPrecio(Double.parseDouble(form.txtPrecio.getText())); // Asegúrate de que el texto sea un número válido
                nuevoProducto.setProveedor(Integer.parseInt(form.txtProveedor.getText())); // Asegúrate de que el texto sea un número válido

                ProductoDao productoDao = new ProductoDao();
                productoDao.guardar(nuevoProducto);
                JOptionPane.showMessageDialog(form, "Producto guardado exitosamente!");

                // Limpiar campos después de guardar
                form.txtCodigo.setText("");
                form.txtNombre.setText("");
                form.txtDescripcion.setText("");
                form.txtPrecio.setText("");
                form.txtProveedor.setText("");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(form, "Por favor, ingrese valores válidos para los números.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error al guardar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
}
