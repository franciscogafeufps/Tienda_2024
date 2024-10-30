
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
    Conexion conectar = Conexion.getInstance();

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
        
        System.out.println("hola estoy imprimiendo");
        
        if(e.getActionCommand().contentEquals("Guardar Producto")){
            System.out.println("hola estoy imprimiendo");
            
            // Crear un nuevo objeto producto a partir de los datos del formulario
             Producto nuevoProducto = new Producto();
             nuevoProducto.setCodigo(Integer.parseInt(form.txtCodigo.getText()));
             nuevoProducto.setNombre(form.txtNombre.getText());
             nuevoProducto.setDescripcion(form.txtDescripcion.getText());
             nuevoProducto.setPrecio(Double.parseDouble(form.txtPrecio.getText()));
             nuevoProducto.setProveedor(Integer.parseInt(form.txtProveedor.getText()));
               /* p.setCodigo(form.txtCodigo.getText());
                p.setNombre(form.txtNombre.getText());
                p.setDescripcion(form.txtDescripcion.getText());
                p.setPrecio(Double.parseDouble(form.txtPrecio.getText()));
                p.setProveedor(form.txtProveedor.getText());*/

             System.out.println(nuevoProducto.toString());
             
             ProductoDao productoDao = new ProductoDao();
             productoDao.guardar(nuevoProducto);
            
            } else if (e.getActionCommand().contentEquals("Limpiar")) {
             form.txtCodigo.setText("");
             form.txtNombre.setText("");
             form.txtDescripcion.setText("");
             form.txtPrecio.setText("");
             form.txtProveedor.setText("");
        }
        
        
        if(e.getActionCommand().contentEquals("Mostrar Productos")){
            
            System.out.println("Entra a Boton Mostar productos");
            
            form.areaProductos.setText("");
            
            try{
            Connection conexion = Conexion.obtener();
            
            PreparedStatement seleccionar = conexion.prepareStatement("SELECT * FROM producto");
            ResultSet consulta = seleccionar.executeQuery(); //Realiza consultas
            
            while(consulta.next()){
                form.areaProductos.append(consulta.getString(1));
                form.areaProductos.append("    ");
                form.areaProductos.append(consulta.getString(2));
                form.areaProductos.append("    ");
                form.areaProductos.append(consulta.getString(3));
                form.areaProductos.append("    ");
                form.areaProductos.append(consulta.getString(4));
                form.areaProductos.append("    ");
                form.areaProductos.append("\n");
            }
            
            Conexion.cerrar();
            
        }   catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
        
    
    
            
    
}
