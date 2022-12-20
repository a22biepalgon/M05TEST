/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import utils.Utils;

/**
 *
 * @author Biel
 */
public class Exercici6 {

    public static void main(String[] args) {
        boolean sortir = false;
        String[] opcions_menu = {"Afegir número (push)", "Treure número (pop)", "Mostrar contingut de la pila"};
        int[] array_pila = new int[10];
        int seguent_n = -1;
        while (!sortir){
            int seleccio = Utils.Menu(opcions_menu);
            switch (seleccio){
                case (1):
                    if (seguent_n > 9){
                        System.out.println("La pila està plena");
                    }else{
                        seguent_n++;
                        array_pila[seguent_n] = Utils.LlegirInt("Digues el numero a afegir a la pila: ");
                    }
                    break;
                case (2):
                    if (seguent_n < 0){
                        System.out.println("La pila està buida");
                    }else{
                        System.out.println("S'ha esborrat el numero " + array_pila[seguent_n]);
                        array_pila[seguent_n] = 0;
                        seguent_n--;
                    }
                    break;
                case (3):
                    if (seguent_n == 0){
                        System.out.println("La pila está buida");
                    }else{
                        System.out.println("La pila té els següents valors: ");
                        for (int i = 0; i < array_pila.length; i++){
                        if (array_pila[i] != 0){
                        System.out.println(i + ") " + array_pila[i]);
                        }
                    }
                    }
                    
            }
        }
    }
    

}
