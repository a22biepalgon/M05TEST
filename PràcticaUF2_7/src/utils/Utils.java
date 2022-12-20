/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 * Llibreria d'utilitats
 *
 * @author biel
 * @version version
 *
 */
public class Utils {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt()">

    private static Scanner scan = null;
    
    /**
     * Aquesta funció llegeix el teclat i comprova que l'introduit sigui un numero
     * @return Retorna el numero enter introduit per teclat
     */
    public static int LlegirInt() {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan);

        return result;
    }

    /**
     * Aquesta funció demana un numero amb un missatge i comprova que sigui un numero
     * @param missatge Demana el numero amb un missatge ontroduit com a parametre
     * @return Retorna el numero enter que ha llegit del teclat
     */
    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan, missatge);

        return result;
    }

    /**
     * Serveix per a demanr un numero i comprovarlo amb el teu scanner i no el de .utils
     * @param scan Pots posar el teu scanner, i n o utilitzar el de .utils
     * @return retorna el numero llegit del teclat
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    /**
     * Demana un numero i el comprova, entre els dos numeros de valor min i valor max, amb un missatge
     * @param scan Pots posar el teu scanner
     * @param missatge Escull quin missatge surt per pantalla a l'hora de demanar el numero
     * @param valorMin El valor minim acceptable per el numero a retornar
     * @param valorMax El valor maxim acceptable per el numero a retornar
     * @return Retorna un enter llegit del teclat, que estigui entre valorMin i valorMax
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Demana un Integer amb un Missatge i el nostre escaner
     * @param scan Pot ser el nostre escanerr si volem
     * @param missatge El missatge que volem que surti per pamntalla a l'hora de demanar el numero
     * @return Retorna el integer llegit del teclat
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirFloat()">
    /**
     * LLegeix un float i el comprova abans de retornar-lo a
     * @return Retorna un valor float 
     */
    public static float LlegirFloat() {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan);

        return result;
    }

    /**
     * Serveix per a demanar un float amb un missatge que passarem com a parametre
     * @param missatge POdem demanar un numero amb aquest missatge com a parametre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(String missatge) {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan, missatge);

        return result;
    }

    /**
     * Serveix per a llegir un float amb el nostre scan
     * @param scan Serveix per a posar un scanner que no sigui el nostre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(Scanner scan) {
        return LlegirFloat(scan, null);
    }

    /**
     * Serveix per a llegir un float amb un missatge, i que estigui entre un minim i un maxim
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @param valorMax Diem el valor maxim del numero a introduir
     * @return retorna un float introduit per el teclat
     */
    public static float LlegirFloat(Scanner scan, String missatge, float valorMin, float valorMax) {
        float result = 0;
        do {
            result = LlegirFloat(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Serveix per a demanr un numero amb el nostre escaner i un missatge
     * @param scan Podem dir el nostre escaner
     * @param missatge Podem dirt un missatge per a que imprimeixi abans de demanr el numero
     * @return Retorna unn float introduit al teclat
     */
    public static float LlegirFloat(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        float result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextFloat();
            if (dadesCorrectes) {
                result = scan.nextFloat();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }
    // </editor-fold>

    /**
     * Serveix per a Imprimir un menú i recollir la selecció donant-li un array amb les opcions del menú
     * @param array_opcions Demana un array String[] per a imprimir les opcions del menu
     * @return Retorna un numero enter depenent de la entrada seleccionada
     */
    public static int Menu(String[] array_opcions) {
        int resultat;

        for (int i = 0; i < array_opcions.length; i++) {
            System.out.println(i + 1 + ") " + array_opcions[i]);
        }
        System.out.println(array_opcions.length + 1 + ") Sortir");
        resultat = LlegirInt(scan, "Opció seleccionada: ", 1, array_opcions.length);

        return resultat;
    }

    /**
     * Serveix per a endreçar un array de int de mes petit a més gran
     * @param array_int Hem de posar el array int[]
     * @param izq Diem per quin index ha de començar a endreçar l'array, sol ser zero
     * @param der Diem l'ultim index que ha de tenir en compte per a endreçar, sol ser array.length -1
     */
    public static void QuicksortInt(int[] array_int, int izq, int der) {

        int pivote = array_int[izq];
        int i = izq;
        int j = der;
        int aux;

        while (i < j) {
            while (array_int[i] <= pivote && i < j) {
                i++;
            }
            while (array_int[j] > pivote) {
                j--;
            }
            if (i < j) {
                aux = array_int[i];
                array_int[i] = array_int[j];
                array_int[j] = aux;
            }
        }

        array_int[izq] = array_int[j];
        array_int[j] = pivote;

        if (izq < j - 1) {
            QuicksortInt(array_int, izq, j - 1);
        }
        if (j + 1 < der) {
            QuicksortInt(array_int, j + 1, der);
        }
    }
    
}
