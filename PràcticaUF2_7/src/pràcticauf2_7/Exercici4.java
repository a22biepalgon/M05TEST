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
        //Provem la funció superusuari
        if (ValidarSuperusuari()) {
            System.out.println("Benvingut superusuari");
        } else {
            System.out.println("Has gastat tots els intents");
        }
    }

    /**
     * Aquesta funció serveix per a comprovar que la contrasenya introduida
     * sigui igual a la emmagatzemanda amb un máxim de 3 intents
     *
     * @return Retorna un boolean true o false
     */
    static boolean ValidarSuperusuari() {
        //Creem la variable return
        boolean resultat = false;
        //Creem la contrasenya correcta
        final String CONTRASENYA = "12345";
        //Creem un contador per al numero d'intents
        int intents = 0;

        //Fem un while per a 3 intents o fins que acertem la contrasenya
        while (intents < 3 && resultat == false) {
            //Demanem la contrasenya amb el llegirString de Utils
            String contra = Utils.LLegirString("Digues la contrasenya per al superusuari, et queda/en " + (3 - intents) + " intent/s: ");
            //Comprovem si son iguals i retornem el resultat
            if (contra.equals(CONTRASENYA)) {
                resultat = true;
            } else {
                intents++;
            }
        }
        //Retornem el resultat
        return resultat;
    }

}
