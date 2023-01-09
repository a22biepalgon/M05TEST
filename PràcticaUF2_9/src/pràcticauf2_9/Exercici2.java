/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_9;

import utils.Utils;

/**
 *
 * @author ausias
 */
public class Exercici2 {

    public static void main(String[] args) {
        //Demanem els dos numeros per a fer el calcul
        int primer = Utils.LlegirInt("Digues el primer numero: ");
        int segon = Utils.LlegirInt("Digues el segon numero: ");

        //Demanem el mcd crindant a la funció Euclides()
        int mcd = Euclides(primer, segon);
        //Mostrem el resultat per pantalla
        System.out.println("El mcd de " + primer + " i " + segon + " és " + mcd);

    }

    /**
     * Aquesta funció calcula quin és el mcd de dos numeros amb la formula de euclides, i de manera recursiva
     * @param x Primer numero de la formula
     * @param y Segon numero de la formula
     * @return retornem el mcd dels dos numeros introduits com a parametres
     */
    static int Euclides(int x, int y) {
        //Creem la variable resultat
        int resultat = 0;

        //Mirem quin és el numero més gran
        if (x > y) {
            //fem la resta
            x = x - y;
        } else if (y > x) {
            //fem la resta
            y = y - x;
        }

        //Mirem si tenim el mcd
        if (x != y) {
            //si no el tenim tornem a cridar la funció un cop s'han restat els dos numeros
            resultat = Euclides(x, y);
        } else {
            //si tenim el mcd retornem el valor de x 
            resultat = x;
        }
        //retornem el resultat
        return resultat;

    }
}
