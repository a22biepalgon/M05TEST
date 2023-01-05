/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf2_8;

import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Biel
 */
public class PràcticaUF2_8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] taula = new int[4][4];
        int jugador = 1;
        boolean sortir = false;
        while (!sortir) {
            Imprimir(taula);
            int columna = Utils.LlegirInt(scan, "Digues el numero de columna", 1, 4);
            int fila = Jugada(taula, columna, jugador);
            while (fila == -1) {
                System.out.println("La columna està plena");
                columna = Utils.LlegirInt(scan, "Digues el numero de columna", 1, 4);
                fila = Jugada(taula, columna, jugador);
            }
            if (EnRatlla(taula, fila, columna)) {
                System.out.println("3 En ratlla");
            }
            if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
        }
    }

    public static void Imprimir(int[][] tauler) {
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                System.out.print(tauler[i][j]);
            }
            System.out.println("");
        }
    }

    public static int Jugada(int[][] tauler, int columna, int jugador) {
        int resultat = -1;
        boolean posat = false;
        for (int i = tauler.length - 1; i >= 0 && !posat; i--) {
            if (tauler[0][columna - 1] != 0) {
                resultat = -1;
                posat = true;
            } else if (tauler[i][columna - 1] == 0) {
                tauler[i][columna - 1] = jugador;
                posat = true;
                resultat = i;
            }

        }
        if (resultat != -1) {
            resultat = resultat + 1;
        }
        return resultat;
    }

    /**
     * Escriu una funció EnRatlla que a partir d’una matriu, un número de fila i
     * un número de columna donats com a paràmetres, indiqui si la casella
     * corresponent pertany a un quatre en ratlla o no. Per a simplificar-ho,
     * només es tindrà en compte les fitxes connectades en horitzontal o en
     * vertical.
     *
     * @param tauler Tauler del joc
     * @param fila Fila de la casella
     * @param columna Columna de la casella
     * @return Retorna true si la casella pertany a un quatre en ratlla i false
     * en cas contrari
     */
    public static boolean EnRatlla(int[][] tauler, int fila, int columna) {
        /*Mirem horitzontal i verticalment al voltant de la casella si hi ha fitxes del mateix jugador. Si hi ha, continuem en línia pel mateix camí comptant
        el número de fitxes a veure si arriba a quatre en ratlla*/

        //Iniciem variables per situar-nos en la fila i columna actuals
        int fila_actual = fila - 1; //Restem 1 a la fila i la columna perquè els índexos dels arrays són 1 número menys
        int columna_actual = columna - 2;

        //HORTITZONTAL
        //Iniciem un comptador per les fitxes horitzontals
        int fitxes_horitzontals = 0;

        //Horitzontal esquerra
        /*Fem un while per comptar la fitxa de l'esquerra de la casella sempre i quan estiguem dins de la matriu. Després, si hi ha
        fitxa, comptem*/
        while (columna_actual >= 0 && tauler[fila_actual][columna_actual] != 0) {
            /*Hem de diferenciar entre la primera fitxa i la resta perquè la resta no només haurem de tindre que hi hagi fitxa, sinó 
            que aquesta sigui del mateix jugador*/

            //Primera fitxa
            if (fitxes_horitzontals == 0) {
                fitxes_horitzontals++;
            } //Resta de les fitxes
            else if (fitxes_horitzontals != 0 && tauler[fila_actual][columna_actual] == tauler[fila][columna_actual + 1]) {
                fitxes_horitzontals++;
            }
            columna_actual--;
        }

        //Horitzontal dreta
        /*Repetim el mateix procediment però ara per la dreta i sense avaluar la primera fitxa. Abans del while canviem columna_actual
        a la primera per la dreta*/
        columna_actual = columna; //Pels índexos del array, la primera columna per la dreta és equivalent a la variable columna
        while (columna_actual <= tauler[fila_actual].length - 1 && tauler[fila_actual][columna_actual] != 0 && fitxes_horitzontals != 0) {
            if (tauler[fila_actual][columna_actual] == tauler[fila_actual][columna_actual - 1]) {
                fitxes_horitzontals++;
            }
            columna_actual++;
        }

        //El mateix verticalment, però primer tornem a canviar la columna actual a la de la casella paràmetre
        columna_actual = columna - 1;

        //VERTICAL
        int fitxes_verticals = 0;

        //Vertical adalt
        while (fila_actual >= 0 && tauler[fila_actual][columna_actual] != 0) {
            /*Hem de diferenciar entre la primera fitxa i la resta perquè la resta no només haurem de tindre que hi hagi fitxa, sinó 
            que aquesta sigui del mateix jugador*/

            //Primera fitxa
            if (fitxes_verticals == 0) {
                fitxes_verticals++;
            } //Resta de les fitxes
            else if (fitxes_verticals != 0 && tauler[fila_actual][columna_actual] == tauler[fila_actual + 1][columna_actual]) {
                fitxes_verticals++;
            }
            fila_actual--;
        }

        //Vertical abaix
        fila_actual = fila; //Pels índexos del array, la primera columna per la dreta és equivalent a la variable columna
        while (fila_actual <= tauler.length - 1 && tauler[fila_actual][columna_actual] != 0 && fitxes_verticals != 0) {
            if (tauler[fila_actual][columna_actual] == tauler[fila_actual - 1][columna_actual]) {
                fitxes_verticals++;
            }
            fila_actual++;
        }

        /*Un cop tenim el recompte fet, preguntem si les fitxes_horitzontals o les fitxes_vertcials són igual a 4. Si alguna ho és,
        retornem true, altrament false*/
        boolean quatre_en_ratlla = false;
        if (fitxes_horitzontals == 4 || fitxes_verticals == 4) {
            quatre_en_ratlla = true;
        }

        //Retornem
        return quatre_en_ratlla;
    }

}
