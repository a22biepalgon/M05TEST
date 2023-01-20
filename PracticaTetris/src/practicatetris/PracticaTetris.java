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
        int punts = 0;

        DefinirPeçes();

        while (!partida_acabada) {
            String[][] peça_actual = DecidirPeça(tauler);
            int posicio = ColocarPeçaTauler(peça_actual,tauler);
            while (!enter) {
                MourePeça(tauler, peça_actual, posicio, scan);

            MourePeça(tauler, peçaactual, scan);

            punts = punts + comprovarLinies(tauler);
            partida_acabada = comprovarPartida(tauler);

            System.out.println("Els teus punts han sigut: " + punts);
        }
    }

    public static boolean comprovarPartida(String[][] tauler) {
        boolean resultat = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                if (tauler[i][j] != null) {
                    resultat = true;
                }
            }
        }

        return resultat;
    }

    public static int comprovarLinies(String[][] tauler) {
        int resultat = 0;
        int contador_linies = 0;
        for (int i = 0; i < tauler.length; i++) {
            boolean linia_sencera = true;
            for (int j = 0; j < tauler[i].length; j++) {
                if (tauler[i][j] == null) {
                    linia_sencera = false;
                }
            }
            if (linia_sencera) {
                for (int j = 0; j < tauler[i].length; j++) {
                    tauler[i][j] = null;
                }
                MoureLinies(tauler, i);
                contador_linies++;
            }

        }
        resultat = punts(contador_linies);
        return resultat;
    }

    public static int punts(int linies) {
        int resultat = 0;
        int bonus = 1;
        switch (linies) {
            case 1:
                bonus = 1;
                break;
            case 2:
                bonus = 22;
                break;
            case 3:
                bonus = 33;
                break;
            case 4:
                bonus = 44;
                break;
        }
        resultat = linies * 1000 * bonus;
        return resultat;
    }

    public static void MoureLinies(String[][] tauler, int index) {
        for (int i = index; i > 0; i--) {
            for (int j = 0; j < tauler[i].length; j++) {
                tauler[i][j] = tauler[i - 1][j];
            }
        }
    }

    public static void MourePeça(String[][] tauler, String[][] peça, int posicio, Scanner scan){
        //Calculem quantes posicions es pot moure a cada costat
        //Dreta
        int posicions_dreta = tauler.length-posicio;
        //Esquerra
        int posicions_esquerra = posicio;
        
        ImprimirTauler(tauler);
        String moure = scan.nextLine();
        boolean validacio_string_moure = false;
        while (!validacio_string_moure){
            if (moure.length()==2 && (moure.charAt(0)=='d'||moure.charAt(0)=='e')){
                if (moure.charAt(0)=='d' || moure.charAt(0)=='D'){
                    if ()
                }
                
            }
        }
        
        
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

    public static String[][] DecidirPeça(String[][] tauler) {
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
    
    public static int ColocarPeçaTauler (String [][] peça, String [][] tauler){
        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i][j] = peça[i][j];
                }
            }
        }
        /*Ens quedem també amb la posició inicial de la peça en el tauler, la seva última columna
        bàsicament*/
        int posicio = peça.length;
        //Retornem la posició
        return posicio;
    }

}


//Thread.sleep(1000);
