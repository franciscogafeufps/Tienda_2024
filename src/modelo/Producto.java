
package modelo;


public class Producto {
    
    private Integer codigo; 
    private String nombre; 
    private String descripcion;
    private double precio; 
    private Integer proveedor;

    public Producto() {
    }

    public Producto(Integer codigo, String nombre, String descripcion, double precio, Integer proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

   
   @Override
   public String toString() {
    return ("Código: " + this.getCodigo() + " El nombre del producto es: " + this.getNombre()+ " La descripción del producto es: " 
            +this.getDescripcion()+ " El precio del producto es: " +this.getPrecio()+" El provedeor es: "+this.getProveedor());
  }
    
}
