/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_9;

/**
 *
 * @author ausias
 */
public class Exercici3 {

    public static void main(String[] args) {
        //Demanem els dos numeros a multiplicar
        int x = utils.Utils.LlegirInt("Digues el primer valor a multiplicar: ");
        int y = utils.Utils.LlegirInt("Digues el segon valor a multiplicar: ");
        
        //Treiem el resultat per pantalla
        System.out.println("El resultat de multiplicar " + x + "*" + y + " és " + Multiplicar(x, y));
    }
    
    //Creem una variable per al valor restant a sumar després de la operació.
    public static int restant = 0;

    /**
     * Aquesta funció serveix per a multiplicar dos numeros sense utilitzar la divisió ni la suma ni modul
     * @param a un valor integer
     * @param b un valor integer
     * @return retorna el resultat de a * b
     */
    private static int Multiplicar(int a, int b) {
        //Creem la varible resultat
        int resultat = 0;
        //Si b no és 2
        if (b > 2) {
            restant = restant + a;
            resultat = Multiplicar(a, b - 1);
        }

        if (b == 2) {
            resultat = (a << 1) + restant;
        }else if(b == 1){
            resultat = a;
        }
        return resultat;
    }
}
