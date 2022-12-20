/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ausias
 */
public class Exercici2 {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 01, 05);
        Date prova = calendar.getTime();

        System.out.println(CalcularIVA(100, "Reduït", prova));
    }

    static float CalcularIVA(float preu_inicial, String tipus_iva, Date data_compra) {
        float resultat;
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
