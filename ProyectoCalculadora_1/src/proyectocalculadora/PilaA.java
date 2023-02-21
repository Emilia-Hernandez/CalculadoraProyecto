/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora;

/**
 *
 * @author eduar
 */
public class PilaA <T> implements PilaADT <T>{
    private T[] pila;
    private int tope;
    private final int MAX=100;

    public PilaA() {
        tope=-1;
        pila = (T[]) new Object[MAX];//construimos con objetc y convertimos a T;
    }
    
    public PilaA(int max) {
        tope=-1;
        pila = (T[]) new Object[max];//construimos con objetc y convertimos a T;
    }
    
    public boolean isEmpty(){
        return tope == -1;
    }
    
    public void push(T dato){
        if(tope == pila.length-1){
            expande();
        }
        tope++;
        pila[tope]=dato;
    }
    
    private void expande(){
        T[] masGrande = (T[]) new Object[pila.length * 2];
        
        for(int i=0; i<=tope; i++){
            masGrande[i] = pila[i];
        }
        pila = masGrande;
       
    }
    
    public T pop(){
        if(isEmpty()){
            throw new ExcepcionColeccionVacia("la pila no tiene elementos");//regresa el main pero con el mensaje de error
        }//si se activa el throw saca del metodo
        
        T eliminado = pila[tope];
        pila[tope] = null;
        tope--;  
        return eliminado;
    }
    
    public T peek(){
        if(isEmpty()){
            throw new ExcepcionColeccionVacia("la pila no tiene elementos");//regresa el main pero con el mensaje de error
        }//si se activa el throw saca del metodo
        
        return pila[tope];
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(int i =0; i <= tope; i++)
            sb.append(pila[i]).append(" ");
        return sb.toString();
    }
}
