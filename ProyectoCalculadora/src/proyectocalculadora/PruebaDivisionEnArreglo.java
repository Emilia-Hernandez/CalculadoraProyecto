/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora;

/**
 *
 * @author emilia
 */
public class PruebaDivisionEnArreglo {
     public static String imprimeArreglo(String[] arr){
        int n = Integer.parseInt(arr[0]);
        StringBuilder res = new StringBuilder(); 
        for (int i = 0; i < n; i++) {
            res.append("\t "+ arr[i]);
        }
        return res.toString(); 
    }
    public static void main(String[] args) {
        System.out.println("Separacion de cadena: ((3/4+2)*6)^3: " + imprimeArreglo(ProyectoCalculadora.dividirCadena("((3/4+2)*6)^3")));
        System.out.println("Separacion de cadena: ((32/4+2.48)*6)^3: " + imprimeArreglo((ProyectoCalculadora.dividirCadena("((32/4+2.48)*6)^3"))));
        System.out.println("Separacion de cadena: ((32/4+2)*~6)^3: " + imprimeArreglo((ProyectoCalculadora.dividirCadena("((32/4+2)*~6)^3"))));
        System.out.println("Separacion de cadena: ~574+~98.4: " + imprimeArreglo((ProyectoCalculadora.dividirCadena("~574+~98.4"))));
    }
}
