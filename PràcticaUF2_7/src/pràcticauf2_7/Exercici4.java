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
public class Exercici4 {

    public static void main(String[] args) {
        if (ValidarSuperusuari()) {
            System.out.println("Benvingut superusuari");
        } else {
            System.out.println("Has gastat tots els intents");
        }
    }

    static boolean ValidarSuperusuari() {
        boolean resultat = false;
        final String CONTRASENYA = "12345";
        int intents = 0;

        while (intents < 3 && resultat == false) {
            String contra = Utils.LLegirString("Digues la contrasenya per al superusuari, et queda/en " + (3 - intents) + " intent/s: ");
            if (contra.equals(CONTRASENYA)) {
                resultat = true;
            } else {
                intents++;
            }
        }
        return resultat;
    }

}
