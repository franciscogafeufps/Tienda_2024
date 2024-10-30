
package modelo;


public class Cliente {
    
    private String cedula; 
    private String nombre; 
    private String email;
    private String telefono; 
    private String quienRecomienda;

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String email, String telefono, String quienRecomienda) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.quienRecomienda = quienRecomienda;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getQuienRecomienda() {
        return quienRecomienda;
    }

    public void setQuienRecomienda(String quienRecomienda) {
        this.quienRecomienda = quienRecomienda;
    }
    
}
