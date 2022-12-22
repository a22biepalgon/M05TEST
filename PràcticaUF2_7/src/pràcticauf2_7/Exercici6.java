/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import utils.Utils;

/**
 * Implementa un programa que faci servir un tipus de dada anomenat “Pila”.
 * Una pila és un tipus de dada on es van afegint valors i es van traient en
 * l’ordre invers al qual s’han afegit, també conegut com LIFO (Last In First
 * Out). El programa ha de presentar el següent menú d’opcions: 1.- Afegir
 * número (push) 2.- Treure número (pop) 3.- Mostrar contingut de la pila 4.-
 * Sortir La pila admet com a màxim 10 valors. Si intentem afegir un onzè
 * element el sistema no ho ha de permetre i ha d’avisar a l’usuari que la pila
 * es troba plena. De la mateixa manera, si intentem treure un número d’una pila
 * que es troba buida, també s’ha d’informar a l’usuari que no pot fer aquesta
 * operació. Al treure un número de la pila, s’ha de mostrar per pantalla quin
 * és el número que s’ha tret.
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici6 {

    public static void main(String[] args) {
        //Creem una avriable per a sortir del programa
        boolean sortir = false;
        //Creem un array amb les opcions del menu
        String[] opcions_menu = {"Afegir número (push)", "Treure número (pop)", "Mostrar contingut de la pila"};
        //Creem l'array de la pila
        int[] array_pila = new int[10];
        //Creem una variable per a seguir per on van els numeros de la pila
        int seguent_n = -1;
        //Creem un while fins que l'usuari vulgui sortir
        while (!sortir) {
            //Demanem una opció del menu amb la funcio dins de utils, i el nostra aarray d'opcions
            int seleccio = Utils.Menu(opcions_menu);
            //Fem un switch per a qualsevol de les eleccions
            switch (seleccio) {
                //Si vol afegir un numero, mirarem que no ens poguem passar de index, si poguessim li direm que la pila está plena
                case (1):
                    if (seguent_n > 8) {
                        System.out.println("La pila està plena");
                        //Sino li afegirm un numero amb el LLegirInt i un missatge
                    } else {
                        seguent_n++;
                        array_pila[seguent_n] = Utils.LlegirInt("Digues el numero a afegir a la pila: ");
                    }
                    break;
                //En cas que volgués esborrar un numero, mirarem si la pila té algun numero
                case (2):
                    if (seguent_n < 0) {
                        System.out.println("La pila està buida");
                        //Si té algun numero l'esborrarem i li direm quiin numero hem esborrat
                    } else {
                        System.out.println("S'ha esborrat el numero " + array_pila[seguent_n]);
                        array_pila[seguent_n] = 0;
                        seguent_n--;
                    }
                    break;
                //Si volem mostrar tots els numeros, mirarem que la pila nos estigui buida
                case (3):
                    if (seguent_n < 0) {
                        System.out.println("La pila está buida");
                        //Si no està buida mostrarem els valors amb un for, fins que i sigui igual al numero per el que estem
                    } else {
                        System.out.println("La pila té els següents valors: ");
                        for (int i = 0; i <= seguent_n; i++) {

                            System.out.println(i + 1 + ") " + array_pila[i]);

                        }
                        System.out.println("");
                    }
                    break;
                //Si l'usuari vopl sortir, posarem la variable sortir a true, i sortirem del while
                case (4):
                    sortir = true;
                    break;

            }
        }
    }

}
