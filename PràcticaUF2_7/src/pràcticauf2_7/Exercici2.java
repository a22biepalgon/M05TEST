/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ausias
 */
public class Exercici2 {

    public static void main(String[] args) {
        Date prova = new Date(2010-07-30);

        System.out.println(CalcularIVA(100, "Superreduit", prova));
    }

    static float CalcularIVA(float preu_inicial, String tipus_iva, Date data_compra) {
        float resultat;
        Date vuitantasis = new Date(1986 - 01 - 01);
        Date nurantados = new Date(1992 - 01 - 01);
        Date nurantatres = new Date(1993 - 01 - 01);
        Date nurantacinc = new Date(1995 - 01 - 01);
        Date deu = new Date(2010 - 01 - 01);
        Date dotze = new Date(2012 - 07 - 15);

        switch (tipus_iva) {
            case ("Exempt"):
                resultat = preu_inicial;
                break;
            case ("Superreduit"):
                if (data_compra.before(nurantatres)) {
                    resultat = preu_inicial;
                } else if (data_compra.before(nurantacinc)) {
                    resultat = preu_inicial + (preu_inicial * 3) / 100;
                } else {
                    resultat = preu_inicial + (preu_inicial * 4) / 100;
                }
                break;
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
                    resultat = preu_inicial + (preu_inicial * 21) / 18;
                }
                break;
            default:
                resultat = 0;
                break;
        }
        return resultat;
    }

}
