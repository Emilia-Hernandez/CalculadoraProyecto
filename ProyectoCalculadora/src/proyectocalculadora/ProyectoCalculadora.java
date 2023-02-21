/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectocalculadora;
import java.util.ArrayList;
/**
 *
 * @author eduar
 */
public class ProyectoCalculadora {
    //para el metodo 1
    public static boolean revisaParentesis(String dato){
        PilaA<Character> pila = new PilaA();
        boolean resp = true;
        int i=0; 
        while(resp && i<dato.length()){
            if(dato.charAt(i)=='('){
                pila.push('(');
            }else{
                if(dato.charAt(i)==')'){
                    if(pila.isEmpty()){
                        resp=false;
                    }else{
                        pila.pop();
                    }
                }
            }
            i++;
        }
        if(!pila.isEmpty()){
            resp=false;
        }
        return resp;
    }
    
    public static boolean revisaContenidoParentesis(String dato){
        PilaADT<Character> pila = new PilaA();
        boolean resp = true;
        int i=0; //esta mal
        while(resp && i<dato.length()){
            if(dato.charAt(i)=='('){
                pila.push('(');
            }else{
                if(dato.charAt(i)!=')'){//asegurarnos que haya algo entre parentesis
                    pila.push(dato.charAt(i));
                }else{
                    if(!pila.isEmpty()){
                        if(pila.peek()=='('){//si lo ultimo fue un parentesis, es decir no hubo nada entre parentesis
                            resp=false;
                        }
                    }
                }
            }
            i++;
        }
        return resp;
    }
    public static boolean revisaNumerosOperandos(String dato){
        PilaADT<Character> pila = new PilaA();
        boolean resp = true;
        int i=0;
        while(resp && i<dato.length()){
            if(dato.charAt(i)=='0'|| dato.charAt(i)=='1' || dato.charAt(i)=='2' || dato.charAt(i)=='3' || dato.charAt(i)=='4' || dato.charAt(i)=='5' || dato.charAt(i)=='6' || dato.charAt(i)=='7' || dato.charAt(i)=='8' || dato.charAt(i)=='9' || dato.charAt(i)=='.' || dato.charAt(i)=='~'){
                pila.push(dato.charAt(i));
            }else{
                if(dato.charAt(i)=='+' ||dato.charAt(i)=='-'||dato.charAt(i)=='/'||dato.charAt(i)=='*'||dato.charAt(i)=='^'){
                    if(pila.isEmpty()){//si no hay un numero antes de un operando es falso
                        resp=false;
                    }else{
                        //checamos que no haya justo un operando antes, para meter el nuevo operando
                        if(pila.peek()=='+'||pila.peek()=='-'||pila.peek()=='/'||pila.peek()=='*'||pila.peek()=='^'){//si hay justo un operando antes esta mal
                            resp=false;
                        }else{
                               pila.push(dato.charAt(i));
                        }
                    }
                }
            }
            i++;
        }
        //checamos que ultimo dato sea numero, y que no sea punto o ~
        if(!pila.isEmpty()){
            if(pila.peek()=='+'||pila.peek()=='-'||pila.peek()=='/'||pila.peek()=='*'||pila.peek()=='^'||pila.peek()=='.'||pila.peek()=='~'){
                resp=false;
            }
        }
        return resp;
    }
    public static boolean revisaSintaxisInfija(String dato){
        boolean resp=true;
        PilaA<Character> pila = new PilaA();
        int i=0;
        if(dato.equals("")){//checamos que tenga datos
            resp=false;
        }else{//si tiene datos, checamos parentesis
            if(revisaParentesis(dato)){//checa balance de parentesis
                if(revisaContenidoParentesis(dato)){//checa que haya algo entre parentesis
                    if(revisaNumerosOperandos(dato)==false){ //checa que operadores y operandos esten bien
                        resp=false;
                    }
                }else{
                    resp=false;
                }
            }else{//si no tiene bien parentesis es false.
                resp=false;
            }
        }
        return resp;
    }
    
    //ya que checamos que este bien escrito en infija
    //para el metodo 2
     public static String[] dividirCadena(String cadena){
    	PilaADT p = new PilaA(); 
        StringBuilder num = new StringBuilder();  
        int length = cadena.length(); 
        String[] res= new String[length+1];//elegimos la capacidad máxima del arreglo como length +1, pues este va a ser el caso extremo en el que cada caracter es un operando o operacion y en la casilla cero metemos la n del arreglo
        ArrayList<Character> op = new ArrayList<Character>();
        op.add('+');
        op.add('-');
        op.add('*');
        op.add('~');
        op.add('/');
        op.add('^');
        op.add(')');
        op.add('(');
        int i = 1; 
        char valor;
        
        for(int j=0; j<length; j++){
            valor=cadena.charAt(j);
            if(op.contains(valor)){
                res[i] = String.valueOf(valor);
                i++;
            }
            else{
                num.append(valor);
                if(j<length-1 && op.contains(cadena.charAt(j+1))){
                    res[i] =num.toString();
                    i++; 
                    num.setLength(0);
                }
                else if(j==length-1){
                    res[i] = num.toString();
                    num.setLength(0);
                    i++;
                }
            }
        }
        res[0]= i+""; 
        return res; 
    }
   
        public static String conviertePostfija(String cadena){
        String [] dato= dividirCadena(cadena);
        StringBuilder postfija= new StringBuilder(); 
        PilaADT <String> operadores= new PilaA();
        int i=1;
        boolean menosParentesis=false;
        boolean menos=false;
        double numero;
        String operador;
     
        while(i<Integer.parseInt(dato[0])){
            if(obtenPrioridad(dato[i])>0){
                while(!operadores.isEmpty() && obtenPrioridad(dato[i])<=obtenPrioridad(operadores.peek())){
                    postfija.append(operadores.pop());
                    postfija.append(" ");
                }
                operadores.push(dato[i]);
            }
            else{
                switch(dato[i].charAt(0)){
                    case '(':
                        operadores.push(dato[i]);
                        break; 
                    case ')':
                        while(!operadores.peek().equals("(")){
                            operador=operadores.pop();
                            if(menosParentesis){
                                switch(operador.charAt(0)){
                                    case'+':
                                        operador="-";
                                        break;
                                    case '-':
                                        operador="+";
                                        break;
                                    default:
                                        break;
                                }
                            }
                            postfija.append(operador);
                            postfija.append(" ");
                        }   
                        operadores.pop();
                        menosParentesis=false;
                        break;
                    case '~':
                        if(dato[i+1].charAt(0)=='('){
                            menosParentesis=true;
                            menos=true;
                        }
                        else{
                            postfija.append(dato[i]);
                            postfija.append(" ");
                        }  
                        break;
                    default: 
                        numero=Double.parseDouble(dato[i]);
                        if(menos){
                            numero*=-(1);
                            menos=false;
                        }
                        postfija.append(numero+" ");
                        break;
                }
            }
            i++;
        }
        while(!operadores.isEmpty()){
            postfija.append(operadores.pop());
            postfija.append(" ");
        }
        return postfija.toString();
    }
    


  public static int obtenPrioridad(String oper){
        int prioridad; 
        
        switch(oper.charAt(0)){
            case '^':
                prioridad=3; 
                break; 
            case '/':
                prioridad=2; 
                break; 
            case '*':
                prioridad=2; 
                break; 
            case '+':
                prioridad=1; 
                break;
            case '-':
                prioridad=1;
                break;
            default:
                prioridad=-1; 
                //lo pongo como default pq ya cheque que sea uno de esos 5. 
        }
        return prioridad;    
    }

  
    //falta metodo 3
    public static String evalua(String cadena){
        String []a=cadena.split(" ");
        PilaA<Double> pila=new PilaA();
        double num1;
        double num2;
        double operacion;
        int i=0;
        boolean menos=false;
        
        while(i<a.length){
           try{
               num1=Double.parseDouble(a[i]);
               System.out.println(num1);
               pila.push(num1);
           } 
           catch(Exception e){
               if(a[i].charAt(0)=='~'){
                    i++;
                    num1=-Double.parseDouble(a[i]);
                    pila.push(num1);
               }
               else{
                    num2=pila.pop();
                    num1=pila.pop();
                    switch(a[i].charAt(0)){
                        case '+':
                            operacion=num1+num2;
                            break;
                        case '-':
                            operacion=num1-num2;
                            break;
                        case '*':
                            operacion=num1*num2;
                            break;
                        case '/':
                            operacion=num1/num2;
                            break;
                        case '^':
                            operacion=Math.pow(num1, num2);
                            break;
                        default:
                            operacion=0;
                            break;
                    }
                    pila.push(operacion);
               }
            }
           i++;
        }
        return pila.pop()+"";
    }
     
    // metodo final
    public static String metodoFinal(String cadena){//a este llamara el boton cuando clikemos = en la calculadora
        String resp="Syntax Error";
        
        if(revisaSintaxisInfija(cadena)){//si la sintaxis es correcta
            resp = evalua(conviertePostfija(cadena));
        }
        return resp;
    }
    public static void main(String[] args) { 
     
    }
    
}