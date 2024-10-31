
package principal;


import control.Control;
import vista.Formulario;

public class Tienda {
    
    public static void main(String args[]){
        
        System.out.println("Inicializa el programa");
        
        Formulario form = new Formulario();
        form.setVisible(true);
        Control control = new Control(form);
    }
}