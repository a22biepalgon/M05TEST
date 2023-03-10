/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import java.util.Calendar;
import java.util.Date;
import utils.Utils;

/**
 * Escriu una funció anomenada “CalcularIVA” a la que se li passa un preu, un
 * tipus d’IVA i la data de la compra i retorna el preu amb l’IVA aplicat. El
 * tipus d’IVA pot ser General, Reduït, Superreduït o Exempt.
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici2 {

    public static void main(String[] args) {
        //Demanem el preu inicial
        float preu = Utils.LlegirFloat("Digues el preu incial del producte: ");
        
        //Demanem el tipus d'iva
        System.out.println("Digues el tipus d'IVA");
        String[] opcions = {"Exempt", "Superreduit", "Reduït", "General"};
        String tipusiva = "";
        switch (Utils.MenuSeleccio(opcions)){
            case (1):
                tipusiva = "Exempt";
                break;
            case (2):
                tipusiva = "Superreduit";
                break;
            case(3):
                tipusiva = "Reduït";
                break;
            case (4):
                tipusiva = "General";
                break;
            default:
                tipusiva = "General";

        }
        
        
        //Demanem la data
        Calendar calendar = Calendar.getInstance();
        calendar.set(Utils.LlegirInt("Digues l'any de compra del producte: "), Utils.LlegirInt("Digues el mes de compra del producte: ") - 1, Utils.LlegirInt("Digues el dia de compra del producte: "));
        Date prova = calendar.getTime();
        

        //Provem el caluclar iva
        System.out.println("El total és de " + CalcularIVA(preu, tipusiva, prova) + "€");
    }

    /**
     * Aquesta funció calcula el total del preu quan depenent del tipus d'iva ,
     * la data de vigencia, i el preu inicial
     *
     * @param preu_inicial donem un float com a preu inicial
     * @param tipus_iva demanem quin tipus d'iva és, pot ser (General, Reduït,
     * Superreduït o Exempt)
     * @param data_compra Demanem la data de compra, ha de ser en fromat Date
     * @return Retorna un float amb el valor del preu + l'iva corresponent
     */
    static float CalcularIVA(float preu_inicial, String tipus_iva, Date data_compra) {
        //Creem la variable resultat
        float resultat;
        //Creem ttoes les variables dels anys
        Calendar calendar = Calendar.getInstance();
        calendar.set(1986, 00, 01);
        Date vuitantasis = calendar.getTime();
        calendar.set(1992, 00, 01);
        Date nurantados = calendar.getTime();
        calendar.set(1993, 00, 01);
        Date nurantatres = calendar.getTime();
        calendar.set(1995, 00, 01);
        Date nurantacinc = calendar.getTime();
        calendar.set(2010, 00, 01);
        Date deu = calendar.getTime();
        calendar.set(2012, 06, 15);
        Date dotze = calendar.getTime();

        //Fem un switch per a mirar el tipus d'iva
        switch (tipus_iva) {
            //Si és exempt el preu no es toca
            case ("Exempt"):
                resultat = preu_inicial;
                break;
            //Si és superreduit serà o 0% o 3% o 4%, depenent de la data de vigencia
            case ("Superreduit"):
                if (data_compra.before(nurantatres)) {
                    resultat = preu_inicial;
                } else if (data_compra.before(nurantacinc)) {
                    resultat = preu_inicial + (preu_inicial * 3) / 100;
                } else {
                    resultat = preu_inicial + (preu_inicial * 4) / 100;
                }
                break;
            //Si és reduït serà 0% o 6% o 7% o 8% o 10% depenent de la data de vigencia
            case ("Reduït"):
                if (data_compra.before(vuitantasis)) {
                    resultat = preu_inicial;
                } else if (data_compra.before(nurantacinc)) {
                    resultat = preu_inicial + (preu_inicial * 6) / 100;
                } else if (data_compra.before(deu)) {
                    resultat = preu_inicial + (preu_inicial * 7) / 100;
                } else if (data_compra.before(dotze)) {
                    resultat = preu_inicial + (preu_inicial * 8) / 100;
                } else {
                    resultat = preu_inicial + (preu_inicial * 10) / 100;
                }
                break;
            //Si és general serà 0% o 12% o 15% o 16% o 18% o 21% depenent de la data de vigencia
            case ("General"):
                if (data_compra.before(vuitantasis)) {
                    resultat = preu_inicial;
                } else if (data_compra.before(nurantados)) {
                    resultat = preu_inicial + (preu_inicial * 12) / 100;
                } else if (data_compra.before(nurantacinc)) {
                    resultat = preu_inicial + (preu_inicial * 15) / 100;
                } else if (data_compra.before(deu)) {
                    resultat = preu_inicial + (preu_inicial * 16) / 100;
                } else if (data_compra.before(dotze)) {
                    resultat = preu_inicial + (preu_inicial * 18) / 100;
                } else {
                    resultat = preu_inicial + (preu_inicial * 21) / 100;
                }
                break;
            //Com a default retornem un 0
            default:
                resultat = 0;
                break;
        }
        //Retornem el resultat
        return resultat;
    }

}
