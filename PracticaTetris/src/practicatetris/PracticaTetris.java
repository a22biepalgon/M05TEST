/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicatetris;

import java.util.Random;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author ausias
 */
public class PracticaTetris {

    public class peça {

        int files;
        int columnes;

    }

    static String[] peçes = new String[4];
    static String[][] peça1 = new String[2][2];
    static String[][] peça2 = new String[2][3];
    static String[][] peça3 = new String[3][2];
    static String[][] peça4 = new String[4][1];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int columnes = Utils.LlegirInt(scan, "Digues la quantitat de columnes: ", 3, 20);
        int files = Utils.LlegirInt(scan, "Digues la quantitat de files: ", 4, 20);
        String[][] tauler = new String[files][columnes];
        boolean partida_acabada = false;

        DefinirPeçes();

        for (int i = 0; i < peça1.length; i++) {
            for (int j = 0; j < peça1[i].length; j++) {
                System.out.println(peça1[i][j]);
            }
        }

        while (!partida_acabada) {
            //MostrarPeça();
        }
    }

    public static void DefinirPeçes() {

        for (int i = 0; i < peçes.length; i++) {
            switch (i) {
                case 0:
                    peçes[i] = peça1;
                    break;
                case 1:
                    peçes[i] = peça2;
                    break;
                case 2:
                    peçes[i] = peça3;
                    break;
                case 3:
                    peçes[i] = peça4;
                    break;
            }
        }

        Crearpeça1();
        Crearpeça2();
        Crearpeça3();
        Crearpeça4();

    }

    public static void Crearpeça1() {
        for (int i = 0; i < peça1.length; i++) {
            for (int j = 0; j < peça1[i].length; j++) {
                peça1[i][j] = "X";
            }
        }
    }

    public static void Crearpeça2() {

        for (int i = 0; i < peça2.length; i++) {
            for (int j = 0; j < peça2[i].length; j++) {
                if (i != 0) {
                    peça2[i][j] = "X";
                } else {
                    peça2[i][0] = "X";
                }

            }
        }
    }

    public static void Crearpeça3() {
        for (int i = 0; i < peça3.length; i++) {
            for (int j = 0; j < peça3[i].length; j++) {
                if (j != 0) {
                    peça3[i][j] = "X";
                } else {
                    peça3[2][0] = "X";
                }
            }
        }
    }

    public static void Crearpeça4() {
        for (int i = 0; i < peça4.length; i++) {
            peça4[i][0] = "X";

        }
    }

    public static void MostrarPeça() {
        Random rnd = new Random();

        int numeropeça = rnd.nextInt(5);

        String peça = "peça" + numeropeça;
        /*for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                System.out.println(peça[i][j]);
            }
            System.out.println("");aa
        }*/
    }

}
