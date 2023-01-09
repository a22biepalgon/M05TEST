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

        int primer = Utils.LlegirInt("Digues el primer numero: ");
        int segon = Utils.LlegirInt("Digues el segon numero: ");

        int mcd = Euclides(primer, segon);
        System.out.println("El mcd de " + primer + " i " + segon + " és " + mcd);
    }

    static int Euclides(int x, int y) {
        int resultat = 0;

        if (x > y) {
            x = x - y;
        } else if (y > x) {
            y = y - x;
        }

        if (x != y) {
            resultat = Euclides(x, y);
        } else {
            resultat = x;
        }
        return resultat;

    }
}
