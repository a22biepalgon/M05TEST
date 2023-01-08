/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf2_8;

import java.util.Scanner;
import utils.Utils;

/**
 * Programa que simul·la el joc del Quatre en Ratlla
 * @author Biel Palomar i Franc Villalba
 */
public class PràcticaUF2_8 {

    public static final String ANSI_RED = "\u001B[31m"; //Color vermell String
    public static final String ANSI_BLUE = "\u001B[34m"; //Color blau String
    public static final String ANSI_RESET = "\u001B[0m"; //Reset

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Demanem i validem els números de files i columnes del tauler
        int numfiles = Utils.LlegirInt("Digues el numero de files que tindrà el tauler: ");
        int numcolumnes = Utils.LlegirInt("Digues el numero de columnes que tindrà el tauler: ");
        //Creem el tauler com una matriu d'enters
        int[][] taula = new int[numfiles][numcolumnes];
        int jugador = 1; //Comença el jugador 1
        //Mentre no s'hagi seleccionat la opció de sortir, tot el programa funciona dins d'un while
        boolean sortir = false;
        while (!sortir) {
            Dibuixa(taula); //Cridem la funció Dibuixa per dibuixar el tauler
            //Demanem i validem la columna on el jugador que li toca vol col·locar la següent fitxa
            int columna = Utils.LlegirInt(scan,"Jugador " + jugador + ", digues el numero de columna: ", 1, numcolumnes);
            //La fila on caurà la fitxa s'obté cridant la funció Jugada
            int fila = Jugada(taula, columna, jugador);
            //Mentre la fila no sigui vàlida es torna a demanar dins d'un while fins que aquesta ho sigui
            while (fila == -1) {
                System.out.println("La columna està plena");
                columna = Utils.LlegirInt(scan,"Jugador " + jugador + ", digues el numero de columna: ", 1, numcolumnes);
                fila = Jugada(taula, columna, jugador);
            }
            
            //Canvi de torn
            if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
            //Després de cada jugada s'avalua si ja hi ha un quatre en ratlla o bé si el tauler està ple. Es fa cridant les funcions EnRatlla i Ple
            if (EnRatlla(taula, fila, columna) || Ple(taula)) {
                if (EnRatlla(taula, fila, columna)){
                    if (jugador == 1) {
                        jugador = 2;
                    } else {
                        jugador = 1;
                    }
                    System.out.println("Jugador " + jugador + ", has guanyat la partida!");
                }
                else{
                    System.out.println("El tauler està ple, s'ha acabat la partida! ");
                }
                
                Dibuixa(taula);
                //Demanem què volen fer els usuaris quan acaba la partida, si fer una altra o sortir. Per a això, utilitzem la funció Menu de Utils
                System.out.println("Que vols fer? ");
                String[] menu = {"Fer una altra partida"};
                int seleccio = Utils.Menu(menu);
                if (seleccio == 2) {
                    sortir = true;
                } else {
                    //Comença una nova partida!
                    numfiles = Utils.LlegirInt("Digues el numero de files que tindrà el tauler: ");
                    numcolumnes = Utils.LlegirInt("Digues el numero de columnes que tindrà el tauler: ");
                    taula = new int[numfiles][numcolumnes];
                    jugador = 1;

                }

            }
        }
    }

    /**
     * Avalua si el tauler està ple
     * @param tauler Tauler de joc
     * @return Retorna true si el tauler està ple i false si no ho està
     */
    public static boolean Ple(int[][] tauler) {
        boolean resultat = true; //Utilitzem un boolean resultat que indica si el tauler està ple
        //Recorrem la primera fila per saber-ho
        for (int i = 0; i < tauler[0].length; i++) {
            if (tauler[0][i] == 0) {
                resultat = false;
            }
        }
        return resultat;
    }

    /**
     * Dibuixa l'estat actual del tauler
     * @param tauler Tauler de joc
     */
    public static void Dibuixa(int[][] tauler) {
        //Recorrem la matriu del tauler i anem imprimint blau pel jugador 1 i vermell pel jugador 2
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                if (tauler[i][j] == 1) {
                    System.out.print(ANSI_BLUE + tauler[i][j] + ANSI_RESET);
                } else if (tauler[i][j] == 2) {
                    System.out.print(ANSI_RED + tauler[i][j] + ANSI_RESET);
                } else {
                    System.out.print(tauler[i][j]);
                }
            }
            System.out.println("");

        }
    }

    /**
     * Escriure una funció Jugada que a partir d’una matriu, un número de
     * columna i un número de jugador, col·loqui si és possible la fitxa
     * d’aquest jugador en la casella adequada de la columna donada. A més a
     * més, la funció tornarà el número de la fila en què situa la fitxa, o el
     * valor -1 si no es pot col·locar la fitxa.
     *
     * @param tauler Tauler de joc
     * @param columna Columna on es vol afegir la fitxa
     * @param jugador Jugador que li toca
     * @return Retorna la fila on s'ha pogut afegir la fitxa. Serà -1 si no es pot afegir la fitxa
     */
    public static int Jugada(int[][] tauler, int columna, int jugador) {
        int resultat = -1; //Inicialment el resultat és -1, la fila que correspon per si no es pot afegir
        boolean posat = false; //Boolean que ens indica si hem posat la fitxa o no
        //Recorrem la columna on volem afegir la fitxa
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
        int columna_actual = columna - 1;

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
            else if (fitxes_horitzontals != 0 && tauler[fila_actual][columna_actual] == tauler[fila_actual][columna_actual + 1]) {
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
