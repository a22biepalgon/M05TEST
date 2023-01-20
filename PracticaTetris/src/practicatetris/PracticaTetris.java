/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicatetris;

import java.util.Random;
import java.util.Scanner;
import utils.Utils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author ausias
 */
public class PracticaTetris {

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
        String[][] tauler = new String[files + 4][columnes];
        boolean partida_acabada = false;

        DefinirPeçes();

        while (!partida_acabada) {
            String[][] peçaactual = MostrarPeça();
            while (!enter) {
                MourePeça(tauler, peçaactual, scan);

            }

            scan.nextInt();
            partida_acabada = true;

        }
    }

    public static void MourePeça(String[][] tauler, String[][] peça, Scanner scan) {

        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i][j] = peça[i][j];
                }
            }
        }

        ImprimirTauler(tauler);
        String moure = scan.nextLine();

    }

    public static void ImprimirTauler(String[][] tauler) {
        for (int i = 0; i < tauler.length; i++) {

            for (int j = 0; j < tauler[i].length; j++) {

                if (j == 0) {
                    System.out.print("|");
                }
                if (tauler[i][j] != null) {
                    System.out.print(tauler[i][j]);
                } else if (i < 4) {
                    System.out.print("*");
                } else if (tauler[i][j] == null) {
                    System.out.print("·");
                }

                if (j == tauler[i].length - 1) {
                    System.out.print("|");
                }
            }

            System.out.println("");
        }
        for (int i = 4; i < tauler.length + 2; i++) {
            System.out.print("-");
        }
        System.out.println("");

    }
    static boolean enter = false;

    public static void DefinirPeçes() {
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
                if (i == 2) {
                    peça3[i][j] = "X";
                } else {
                    peça3[i][1] = "X";
                }
            }
        }
    }

    public static void Crearpeça4() {
        for (int i = 0; i < peça4.length; i++) {
            peça4[i][0] = "X";

        }
    }

    public static String[][] MostrarPeça() {
        Random rnd = new Random();
        String[][] resultat = new String[10][5];

        int numeropeça = rnd.nextInt(5);

        while (numeropeça == 0) {
            numeropeça = rnd.nextInt(5);
        }
        switch (numeropeça) {
            case 1:
                resultat = peça1;
                break;
            case 2:
                resultat = peça2;
                break;
            case 3:
                resultat = peça3;
                break;
            case 4:
                resultat = peça4;
                break;
        }
        System.out.println("");
        return resultat;
    }

}


//Thread.sleep(1000);
