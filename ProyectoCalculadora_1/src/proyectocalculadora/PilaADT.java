/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectocalculadora;

/**
 *
 * @author eduar
 */
public interface PilaADT <T> {//indicamos que hay miembro de tipo generico
    public void push(T dato);
    public T pop();
    public T peek();
    public boolean isEmpty();
    
}
