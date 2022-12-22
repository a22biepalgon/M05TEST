/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import java.util.Scanner;
import utils.Utils;

/**
 * Escriu una funció anomenada “Roman” al que se li passa un número enter entre
 * 1 i 3999 i retorni el seu valor en números romans
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici3 {
    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //CONSTANTS
        final int NUMERO_MINIM = 1; //Valor mínim que ha de ser el número que entra per teclat
        final int NUMERO_MAXIM = 3999; //Valor màxim que ha de ser el número que entra per teclat
        
        //Demanem i validem el número enter entre 1 i 3999
        int numero = Utils.LlegirInt(scan, "Introdueix un numero enter entre 1 i 3999: ", NUMERO_MINIM, NUMERO_MAXIM );
        //Cridem la funció que tradueix el número en números romans
        String resultat_numero_roma = Roman(numero);
        //Imprimim
        System.out.println("El " + numero + " expressat en numeros romans es " + resultat_numero_roma);
    }
    
    /**
     * Escriu un número enter en números romans
     * @param numero Número enter que es transcriu
     * @return Retorna el número expressat en números romans
     */
    public static String Roman (int numero){
        
        //CONSTANTS
        final int DIVISOR_1000 = 1000; //Per dividir entre 1000 el número i trobar els milers
        final int DIVISOR_100 = 100; //Per dividir entre 100 el número i trobar les centenes
        final int DIVISOR_10 = 10; //Per dividir entre 10 el número i trobar les centenes, desenes i unitats
        char I = 'I'; //Igual a 1 en sistema romà
        char V = 'V'; //Igual a 5 en sistema romà
        char X = 'X'; //Igual a 10 en sistema romà
        char L = 'L'; //Igual a 50 en sistema romà
        char C = 'C'; //Igual a 100 en sistema romà
        char D = 'D'; //Igual a 500 en sistema romà
        char M = 'M'; //Igual a 1000 en sistema romà
        
        //Iniciem el resultat del número expressat en números romans
        String resultat_numero_roma = "";
        
        //Busquem els milers, les centenes, les desenes i les unitats del número
        int milers = numero/DIVISOR_1000;
        int centenes = numero / DIVISOR_100 % DIVISOR_10;
        int desenes = numero / DIVISOR_10 % DIVISOR_10;
        int unitats = numero % DIVISOR_10;
        
        //Ara expressem cadascun dels valors anteriors en números romans
        
        //MILERS
        //Fem un for a milers, i afegim per cada unitat la lletra M que representa 1000
        for (int i = 1; i <= milers; i++) {
            resultat_numero_roma = resultat_numero_roma + M;
        }
        
        //CENTENES
        //Si les centenes són 9, les hem d'expressar com a resta de 1000-100
        if (centenes == 9) {
            resultat_numero_roma = resultat_numero_roma + C + M;
        }
        //Si les centenes són igual o més gran que 5, afegim la D que representa 500
        else if (centenes >= 5) {
            resultat_numero_roma = resultat_numero_roma + D;
            //Després, si les centenes estan entre 6 i 8, ho recorrem i afegim una centena de valor 100 per cadascuna, la lletra C
            for (int i = 6; i <= centenes; i++) {
                resultat_numero_roma = resultat_numero_roma + C;
            }
        }
        //Quan les centenes són igual a 4, les hem d'expressar com a resta de 500-100
        else if (centenes == 4) {
            resultat_numero_roma = resultat_numero_roma + C + D;
        }
        //Si les centenes són més petites de 4, per cadascuna afegim la C que representa la centena
        else {
            for (int i = 1; i <= centenes; i++) {
                resultat_numero_roma = resultat_numero_roma + C;
            }
        }
        
        //DESENES
        //Exactament el mateix procediment que les centenes, però amb els símbols i els valors de les desenes
        if (desenes == 9) {
            resultat_numero_roma = resultat_numero_roma + X + C;
        } 
        else if (desenes >= 5) {
            resultat_numero_roma = resultat_numero_roma + L;
            for (int i = 6; i <= desenes; i++) {
                resultat_numero_roma = resultat_numero_roma + X;
            }
        } 
        else if (desenes == 4) {
            resultat_numero_roma = resultat_numero_roma + X + L;
        } 
        else {
            for (int i = 1; i <= desenes; i++) {
                resultat_numero_roma = resultat_numero_roma + X;
            }
        }
        
        //UNITATS
        //Exactament el mateix procediment que els milers i les centenes, però amb els símbols i els valors de les unitats
        if (unitats == 9) {
            resultat_numero_roma = resultat_numero_roma + I + X;
        } 
        else if (unitats >= 5) {
            resultat_numero_roma = resultat_numero_roma + V;
            for (int i = 6; i <= unitats; i++) {
                resultat_numero_roma = resultat_numero_roma + I;
            }
        } 
        else if (unitats == 4) {
            resultat_numero_roma = resultat_numero_roma + I + V;
        } 
        else {
            for (int i = 1; i <= unitats; i++) {
                resultat_numero_roma = resultat_numero_roma + I;
            }
        }
        
        //Retornem el número en números romans
        return resultat_numero_roma;
    }
}

