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

    //Declarem els strings per a cada peça
    /**
     * Peça 1 es tracta de un quadrat 2x2
     */
    static String[][] peça1 = new String[2][2];

    /**
     * Peça2 es tracta de una L
     */
    static String[][] peça2 = new String[2][3];

    /**
     * Peça3 es tracta de una J
     */
    static String[][] peça3 = new String[3][2];

    /**
     * Peça 4 es tracta de un pal de 4 de alçaad |
     */
    static String[][] peça4 = new String[4][1];

    //Declarem la variable partida_acabada i els punts
    static boolean partida_acabada = false;
    static int punts = 0;

    //Declarem un string per a retenir la peça actual i la seva posició en tot moment
    static String[][] peça_actual;
    static int[] posicio = {0, 0};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Inserim l'escaner
        Scanner scan = new Scanner(System.in);

        //Demanem el numero de columnes del taulell, amb un valor minim de 3 i un maxim de 20
        int columnes = Utils.LlegirInt(scan, "Digues la quantitat de columnes: ", 3, 20);

        //Demanem el numero de files del taulell, amb un minim de 4 i un màxim de 20
        int files = Utils.LlegirInt(scan, "Digues la quantitat de files: ", 4, 20);

        //Creem el tauler
        String[][] tauler = new String[files + 4][columnes];

        //Executem el procediment que creearà les peçes
        DefinirPeçes();

        //Decidim una peça al atzar i la posem a la variable peça actual
        peça_actual = DecidirPeça();
        //Mostrem la peça, que el que fa és moure peça_actual a dins del tauler
        MostrarPeça(peça_actual, tauler, posicio);

        //Imprimim el tauler
        ImprimirTauler(tauler);

        //Fem un while per a que s'executi el programa fins que partida_acabada sigui true
        while (!partida_acabada) {
            //Executem la funció mourePeça(), que com el seu nom indica, va movent la peça fins que arriba a un punt on no pot baixar més
            MourePeça(tauler, peça_actual, posicio, scan);

            //Calculem els punts i mirem si hi ha alguna linia completa a la vegada
            punts = punts + comprovarLinies(tauler);
            //Esborrem la xona nova, per a que quedi més net
            BorrarZonaPecesNoves(tauler);
        }
        //Quan acaba el programa imprimim el nombre de punts que l'usuari ha aconseguit
        System.out.println("Els teus punts han sigut: " + punts);

    }

    /**
     * Aquest procediment crea la peça 1 que es tracta de un quadrat de 2x2
     */
    public static void Crearpeça1() {
        for (int i = 0; i < peça1.length; i++) {
            for (int j = 0; j < peça1[i].length; j++) {
                peça1[i][j] = "X";
            }
        }
    }

    /**
     * Aquest procediment crea la peça 2, la cual tracta de una L de 3 d'altura
     * i 2 d'amplada
     */
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

    /**
     * Aquest procediment crea la peça 3, la cual és una J de 3 d'altura i 2
     * d'amplada
     */
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

    /**
     * Aquest proçediment crea la peça 4 que tracta de un pal de 4 d'altura
     */
    public static void Crearpeça4() {
        for (int i = 0; i < peça4.length; i++) {
            peça4[i][0] = "X";

        }
    }

    /**
     * Aquest procediment crida les funcions crea peça d'una en una
     */
    public static void DefinirPeçes() {
        Crearpeça1();
        Crearpeça2();
        Crearpeça3();
        Crearpeça4();

    }

    /**
     * Aquesta funció decideix una de les peçes creades aleatoriament
     *
     * @return Retorna una peça aleatoria de les que tenim creades
     */
    public static String[][] DecidirPeça() {
        //Inserim el objecte random
        Random rnd = new Random();
        //Definim les variables de amplitud i alçada m+àxima de qualsevo peça
        final int ALCADAMAXIMA = 10;
        final int AMPLITUDMAXIMA = 5;
        final int NUMPECES = 4 + 1;
        //Creem una variable resultat lo suficientment gran per a que càpigui qualsevol peça
        String[][] resultat = new String[ALCADAMAXIMA][AMPLITUDMAXIMA];

        //Escollim un numero random, que no pot ser 0, fins el numero de pees que tinguem
        int numeropeça = rnd.nextInt(NUMPECES);
        while (numeropeça == 0) {
            numeropeça = rnd.nextInt(NUMPECES);
        }

        //Fem un switch per a posar el resultat = una peça diferent depepent dell numero random
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

        //Retornem el resultat
        return resultat;

    }

    /**
     * Aquest procediment serveix per a seleccionar una nova`peça aleatoria i inserirla al tauler
     * @param tauler necesita el tauler com a parametre
     */
    public static void ColocarPeçaNova(String[][] tauler) {
        //Decidim la peça aleatoriament
        peça_actual = DecidirPeça();
        //Posem la posició a 0, 0
        posicio[0] = 0;
        posicio[1] = 0;
        
        //Mostrem la peça
        MostrarPeça(peça_actual, tauler, posicio);
    }

    /**
     * Aquest procediment ensenya el tauler amb la peça nova
     * @param peça necesita la peça que estem fent servir ara mateix
     * @param tauler neceista el String[][] del tauler
     * @param posicio necesita la posició actual de la peça
     */
    public static void MostrarPeça(String[][] peça, String[][] tauler, int[] posicio) {
        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i + posicio[0]][j + posicio[1]] = peça[i][j];
                }
            }
        }
    }

    /**
     * Aquest procediment serveix per a esborrar la peça antiga després de haver-la mogut
     * @param peça necesita saber quina és la peça actual
     * @param tauler Necesita el string[][] del tauler
     * @param posicio Necesita saber la posició inicial de la peça
     */
    public static void BorrarPeça(String[][] peça, String[][] tauler, int[] posicio) {
        for (int i = 0; i < peça.length; i++) {
            for (int j = 0; j < peça[i].length; j++) {
                if (peça[i][j] != null) {
                    tauler[i + posicio[0]][j + posicio[1]] = null;
                }
            }
        }
    }

    /**
     * Aquest procediment imprimeix el tauler, delimitant-lo amb | i - i posant
     * · on no hi ha ninguna peça
     *
     * @param tauler Neceista el String[][] tauler per a poder-se executar
     */
    public static void ImprimirTauler(String[][] tauler) {
        //Creem la constant de la alçada de la zona de aparició
        final int ZONAAPARICIO = 4;

        //Creem un for que iteri per tot el tauler
        for (int i = 0; i < tauler.length; i++) {
            //Iterem per totes les columnes
            for (int j = 0; j < tauler[i].length; j++) {

                //Si estem a la primera columna imprimim | per al delimitant
                if (j == 0) {
                    System.out.print("|");
                }
                //Si la posició conté alguna cosa, la imprimim
                if (tauler[i][j] != null) {
                    System.out.print(tauler[i][j]);
                    //Si el tauler es troba a la zona de aparició imprimim * en coomptes de ·
                } else if (i < ZONAAPARICIO) {
                    System.out.print("*");
                    //Si és null imprimim ·
                } else if (tauler[i][j] == null) {
                    System.out.print("·");
                }

                //Si estem a la última columna imprimim |
                if (j == tauler[i].length - 1) {
                    System.out.print("|");
                }
            }
            //Imprimim un enter per a saltar de linia
            System.out.println("");
        }
        //Si ens trobem al final imprimim -
        for (int i = ZONAAPARICIO; i < tauler.length + 2; i++) {
            System.out.print("-");
        }
        //Imprimim un espai
        System.out.println("");

    }

    /**
     * Aquest procediment detecta el que s'escriu per el teclat, i fa comprovacions per a veure cap a on ho ha de moure
     * @param tauler Demana el String[][] del tauler
     * @param peça Demana la peça actual
     * @param posicio Demana la posicio de la peça
     * @param scan Demana el scanner
     */
    public static void MourePeça(String[][] tauler, String[][] peça, int[] posicio, Scanner scan) {
        //Calculem quantes posicions es pot moure a cada costat
        //Dreta
        int posicions_dreta = tauler[0].length - posicio[1] - peça[0].length;
        //Esquerra
        int posicions_esquerra = posicio[1];

        String string_moure = scan.nextLine();
        boolean validacio_moure_dreta = ValidacioMoureDreta(string_moure, posicions_dreta);
        boolean validacio_moure_esquerra = ValidacioMoureEsquerra(string_moure, posicions_esquerra);
        boolean validacio_caure_peça = ValidacioCaurePeça(string_moure);
        while (!validacio_moure_dreta && !validacio_moure_esquerra && !validacio_caure_peça) {
            string_moure = scan.nextLine();
            validacio_moure_dreta = ValidacioMoureDreta(string_moure, posicions_dreta);
            validacio_moure_esquerra = ValidacioMoureEsquerra(string_moure, posicions_esquerra);
            validacio_caure_peça = ValidacioCaurePeça(string_moure);
        }
        Moviment(tauler, string_moure, validacio_moure_dreta, validacio_moure_esquerra, validacio_caure_peça);
    }

    /**
     * Aquesta funció serveix per a veure si l'entrada del teclat és una d + un numero, i comprova si es pot moure
     * @param string_moure Necesita el string de la comanda per moure
     * @param posicions_dreta se li pasarà el total de espais en blanc que hi ha a la dreta de la peça
     * @return retorna un true si es pot moure la peça a la dreta i un false si no
     */
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

    /**
     * Aquesta funció serveix per a veure si l'entrada del teclat és una e + un numero, i comprova que hi hagi suficient espai a la esquerra de la peça per a moure-la
     * @param string_moure El input del teclat que ha posat l'usuari
     * @param posicions_esquerra El numero de posicions lliures a l'esquerra de la peça
     * @return Retorna un true si es pot moure, i un false si no es pot
     */
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

    /**
     * Detecta si l'entrada és un enter
     * @param string_moure Necesita l'entrada del teclat
     * @return retorna un true si l'entrada és un enter i un false si no ho és
     */
    public static boolean ValidacioCaurePeça(String string_moure) {
        boolean validacio_caure_peça = false;
        if (string_moure.equals("")) {
            validacio_caure_peça = true;
        }
        return validacio_caure_peça;
    }
    
    /**
     * Aquest procediment mou una peça el numero de cops que se li hagi dit
     * @param tauler String[]][ del tauler
     * @param string_moure entrada del teclat
     * @param validacio_moure_dreta funció ValidacioMoureDreta()
     * @param validacio_moure_esquerra funció ValidacioMoureEsquerra()
     * @param validacio_caure_peça funcio ValidacioCaurePeça
     */
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
    
    /**
     * Aques procediment fa caure la peça fins a baix de tot, fins que arribi fins al final del taulell o xoqui amb una altre peça
     * @param tauler String[][] del tauler
     * @param peça peça actual
     * @param posicio posicio de la p´ça actual
     */
    public static void FerCaureLaPeça(String[][] tauler, String[][] peça, int[] posicio) {
        //Anem baixant la peça fins que ComprovarPeçaFixada detecti que la peça ja està fixada
        boolean peça_fixada = false;
        while (!peça_fixada) {
            BorrarPeça(peça_actual, tauler, posicio);
            posicio[0]++;
            peça_fixada = ComprovarPeçaFixada(tauler, peça, posicio);
        }
    }
    
    /**
     * Aquesta funció comprova si la peça ha arribat al seu top, i quan ha arribat, mira si la aprtida ha acabat
     * @param tauler String[][] del tauler
     * @param peça Strig[][] de la peça
     * @param posicio posicio actual de la peça
     * @return retorna true si la peça ha arribat al final i un false si no
     */
    public static boolean ComprovarPeçaFixada(String[][] tauler, String[][] peça, int posicio[]) {
        //Comprovem si una peça està fixada perquè ja s'ha posicionat sobre una altra mirant si la base toca per sota amb una altra peça

        boolean peça_fixada = false;
        if (posicio[0] + peça.length < tauler.length) {
            for (int i = posicio[1]; i < posicio[1]+peça[0].length; i++) {
                if (tauler[posicio[0] + peça.length][i] != null) {
                    peça_fixada = true;

                }
            }
        } if (tauler.length - posicio[0] == peça.length && !peça_fixada) {
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
     * Aquesta funció comprova linia per linia per a veure si té alguna
     * completa, quan troba una completa l'esborra i suma un 1 al contador de
     * linies, després crida a la funció punts() per a calcular quants punts se
     * sumen
     *
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
     * Aquest procediment mou totes les linies cap abaix quan es trenca la que
     * tenien a sota
     *
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
     * Aquesta funció calcula el numero de punts depenent de quantes linies
     * s'hagin trencat de cop
     *
     * @param linies Necesita el numero de linies que s'han trencat en aquellla
     * jugada
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
     * Comprova que la zona d'apari´ió estigui buida, si no esta buida, la
     * partida acaba
     *
     * @param tauler Necesita el String[][] del tauler
     * @return Retonra un true si la aprtida ha acabat, i un false si la aprtida
     * segueix
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

    /**
     * Aqeust procediment esborra tot el contingut de la zona de peçes noces si la partida ha acabat
     * @param tauler Necesita el String[][] del tauler
     */
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