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
            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa
                //peçes[i] = peça1;
            
        }
        
        
        for (int i = 0; i < peça1.length; i++) {
            for (int j = 0; j < peça1[i].length; j++) {
                peça1[i][j] = "X";
            }
        }

        for (int i = 0; i < peça2.length; i++) {
            for (int j = 0; j < peça2[i].length; j++) {
                if (i != 0) {
                    peça2[i][j] = "X";
                } else {
                    peça2[i][0] = "X";
                }

            }
        }

        for (int i = 0; i < peça3.length; i++) {
            for (int j = 0; j < peça3[i].length; j++) {
                if (j != 0) {
                    peça3[i][j] = "X";
                } else {
                    peça3[2][0] = "X";
                }
            }
        }
        for (int i = 0; i < peça4.length; i++) {
                peça4[i][0] = "X";

        }

    }

    public static void MostrarPeça() {
        Random rnd = new Random();

        int numpeça = rnd.nextInt(5);

        
        
        String peça = "peça" + numpeça;
        /*for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                System.out.println(peça[i][j]);
            }
            System.out.println("");aa
        }*/
    }

}
