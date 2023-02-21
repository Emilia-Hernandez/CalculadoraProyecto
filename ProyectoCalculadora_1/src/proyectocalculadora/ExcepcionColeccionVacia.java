/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora;

/**
 *
 * @author eduar
 */
public class ExcepcionColeccionVacia extends RuntimeException{//para que por concepto de herencia java reconozca nuestra excepcion

    public ExcepcionColeccionVacia() {
    }

    public ExcepcionColeccionVacia(String message) {
        super(message);//llamo al super de RuntimeException
    }
    
    
}
