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
        String[][] tauler = new String[files][columnes];
        boolean partida_acabada = false;

        DefinirPeçes();
        keypressed();

        while (!partida_acabada) {
            String[][] peçaactual = MostrarPeça();
            posicioinicial(peçaactual);
            while (!enter) {
                keypressed();
                System.out.println(posicio);
            }
            scan.nextInt();
            partida_acabada = true;

        }
    }

    public static void posicioinicial(String[][] peça) {
        posicio = peça[0].length;
        System.out.println(posicio);
    }
    static int posicio = 0;
    static boolean enter = false;

    public static void keypressed() {

        JFrame myJFrame = new JFrame();

        myJFrame.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {

                    posicio--;
                    System.out.println("Left Arrrow-Key is pressed!");

                } else if (keyCode == KeyEvent.VK_RIGHT) {

                    posicio++;
                    System.out.println("Right Arrrow-Key is pressed!");

                } else if (keyCode == KeyEvent.VK_ENTER) {
                    enter = true;
                }

            }

        });

        myJFrame.setVisible(true);

    }

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

        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[i].length; j++) {
                if (resultat[i][j] != null) {
                    System.out.print(resultat[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        return resultat;
    }

}
