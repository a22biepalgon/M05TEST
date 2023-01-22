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

    static String[][] peça1 = new String[2][2];
    static String[][] peça2 = new String[2][3];
    static String[][] peça3 = new String[3][2];
    static String[][] peça4 = new String[4][1];

    static boolean partida_acabada = false;
    static int punts = 0;

    static String[][] peça_actual;
    static int[] posicio = {0, 0};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int columnes = Utils.LlegirInt(scan, "Digues la quantitat de columnes: ", 3, 20);
        int files = Utils.LlegirInt(scan, "Digues la quantitat de files: ", 4, 20);
        String[][] tauler = new String[files + 4][columnes];
        DefinirPeçes();
        peça_actual = DecidirPeça(tauler);
        MostrarPeça(peça_actual, tauler, posicio);
        ImprimirTauler(tauler);

        while (!partida_acabada) {
                MourePeça(tauler,peça_actual,posicio,scan);
                //punts = punts + comprovarLinies(tauler);
                //System.out.println("Els teus punts han sigut: " + punts);
                BorrarZonaPecesNoves(tauler);
        }
        System.out.println("Els teus punts han sigut: " + punts);

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

    public static void DefinirPeçes() {
        Crearpeça1();
        Crearpeça2();
        Crearpeça3();
        Crearpeça4();

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

    public static void ColocarPeçaNova(String[][] tauler) {
        peça_actual = DecidirPeça(tauler);
        posicio[0] = 0;
        posicio[1] = 0;
        MostrarPeça(peça_actual, tauler, posicio);
    }

    public static void MostrarPeça(String[][] peça, String[][] tauler, int[] posicio) {
        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i + posicio[0]][j + posicio[1]] = peça[i][j];
                }
            }
        }
    }

    public static void BorrarPeça(String[][] peça, String[][] tauler, int[] posicio) {
        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i + posicio[0]][j + posicio[1]] = null;
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

    public static void MourePeça(String[][] tauler, String[][] peça, int[] posicio, Scanner scan) {
        //Calculem quantes posicions es pot moure a cada costat
        //Dreta
        int posicions_dreta = tauler[0].length-posicio[1]-peça[0].length;
        //Esquerra
        int posicions_esquerra = posicio[1];

        String string_moure = scan.nextLine();
        boolean validacio_moure_dreta = ValidacioMoureDreta(string_moure, posicions_dreta);
        boolean validacio_moure_esquerra = ValidacioMoureEsquerra(string_moure, posicions_esquerra);
        boolean validacio_caure_peça = ValidacioCaurePeça(string_moure);
        while (!validacio_moure_dreta && !validacio_moure_esquerra && !validacio_caure_peça){
            string_moure = scan.nextLine();
            validacio_moure_dreta = ValidacioMoureDreta(string_moure,posicions_dreta);
            validacio_moure_esquerra = ValidacioMoureEsquerra(string_moure,posicions_esquerra);
            validacio_caure_peça = ValidacioCaurePeça(string_moure);
        }
        Moviment(tauler, string_moure, validacio_moure_dreta, validacio_moure_esquerra, validacio_caure_peça);
    }

    public static boolean ValidacioMoureDreta(String string_moure, int posicions_dreta) {
        boolean validacio_moure_dreta = false;
        if (string_moure.length() == 2) {
            if (string_moure.charAt(0) == 'd' || string_moure.charAt(0) == 'D') {
                if (Character.isDigit(string_moure.charAt(1))) {
                    if (Integer.parseInt("" + string_moure.charAt(1)) <= posicions_dreta) {
                        validacio_moure_dreta = true;
                    }
                }
            }
        }

        return validacio_moure_dreta;
    }

    public static boolean ValidacioMoureEsquerra(String string_moure, int posicions_esquerra) {
        boolean validacio_moure_esquerra = false;
        if (string_moure.length() == 2) {
            if (string_moure.charAt(0) == 'e' || string_moure.charAt(0) == 'E') {
                if (Character.isDigit(string_moure.charAt(1))) {
                    if (Integer.parseInt("" + string_moure.charAt(1)) <= posicions_esquerra) {
                        validacio_moure_esquerra = true;
                    }
                }
            }
        }

        return validacio_moure_esquerra;
    }

    public static boolean ValidacioCaurePeça(String string_moure) {
        boolean validacio_caure_peça = false;
        if (string_moure.equals("")) {
            validacio_caure_peça = true;
        }
        return validacio_caure_peça;
    }

    public static void Moviment(String[][] tauler, String string_moure, boolean validacio_moure_dreta, boolean validacio_moure_esquerra, boolean validacio_caure_peça) {
        BorrarPeça(peça_actual, tauler, posicio);
        if (validacio_moure_dreta || validacio_moure_esquerra) {
            if (validacio_moure_dreta) {
                posicio[1] = posicio[1] + Integer.parseInt("" + string_moure.charAt(1));
            } else {
                posicio[1] = posicio[1] - Integer.parseInt("" + string_moure.charAt(1));
            }
            posicio[0]++;
            boolean peça_fixada = ComprovarPeçaFixada(tauler, peça_actual, posicio);
        } else {
            FerCaureLaPeça(tauler, peça_actual, posicio);
        }
    }

    public static void FerCaureLaPeça(String[][] tauler, String[][] peça, int[] posicio) {
        //Anem baixant la peça fins que ComprovarPeçaFixada detecti que la peça ja està fixada
        boolean peça_fixada = false;
        while (!peça_fixada) {
            BorrarPeça(peça_actual, tauler, posicio);
            posicio[0]++;
            peça_fixada = ComprovarPeçaFixada(tauler, peça, posicio);
        }
    }

    public static boolean ComprovarPeçaFixada(String[][] tauler, String[][] peça, int posicio[]) {
        //Comprovem si una peça està fixada perquè ja s'ha posicionat sobre una altra mirant si la base toca per sota amb una altra peça

        boolean peça_fixada = false;
        if (posicio[0] + peça.length < tauler.length) {
            for (int i = posicio[1]; i < peça[0].length; i++) {
                if (tauler[posicio[0] + peça.length][i] != null) {
                    peça_fixada = true;

                }
            }
        } else if (tauler.length - posicio[0] == peça.length) {
            peça_fixada = true;
        }
        MostrarPeça(peça, tauler, posicio);
        if (peça_fixada) {
            ColocarPeçaNova(tauler);
            partida_acabada = comprovarPartida(tauler);
        }

        return peça_fixada;
    }

    /**
     * Aquesta funció comprova linia per linia per a veure si té alguna completa, quan troba una completa l'esborra i suma un 1 al contador de linies, després crida a la funció punts() per a calcular quants punts se sumen
     * @param tauler demana el string[][] del tauler
     * @return retorna un integer amb la quantitat de punts guanyats
     */
    
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
    
    /**
     * Aquest procediment mou totes les linies cap abaix quan es trenca la que tenien a sota
     * @param tauler necesita el String[][] del tauler
     * @param index Necesita el index on s'ha trencat la fila
     */

    public static void MoureLinies(String[][] tauler, int index) {
        for (int i = index; i > 4; i--) {
            for (int j = 0; j < tauler[i].length; j++) {
                tauler[i][j] = tauler[i - 1][j];
            }
        }
    }
    
    
    
    /**
     * Aquesta funció calcula el numero de punts depenent de quantes linies s'hagin trencat de cop
     * @param linies Necesita el numero de linies que s'han trencat en aquellla jugada
     * @return Retorna el resultatde les linies * bonus
     */

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

    /**
     * Comprova que la zona d'apari´ió estigui buida, si no esta buida, la partida acaba
     * @param tauler Necesita el String[][] del tauler
     * @return Retonra un true si la aprtida ha acabat, i un false si la aprtida segueix
     */
    
    public static boolean comprovarPartida(String[][] tauler) {
        boolean resultat = false;
        for (int i = 0; i < tauler[4].length; i++) {
            if (tauler[4][i] != null) {
                resultat = true;
            }

        }

        return resultat;
    }

    public static void BorrarZonaPecesNoves(String[][] tauler) {
        if (partida_acabada) {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j < tauler[0].length; j++) {
                    if (tauler[i][j] != null) {
                        tauler[i][j] = null;
                    }
                }

            }
        }
        ImprimirTauler(tauler);

    }

}


//Thread.sleep(1000);
