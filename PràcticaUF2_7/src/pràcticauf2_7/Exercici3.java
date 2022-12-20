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
        
    }
    
    public static String Roman (int numero){
        /*Fem un for al número per anar escrvint-lo amb els números més grans possibles. Quan escrivim un número romà, el restem
        al número*/
        
        //Iniciem el resultat del número expressat en números romans i la variable del número romà a afegir
        String resultat_numero_roma;
        int numero_roma_a_afegir;
        for (int i=0;i>0;i=i-numero_roma_a_afegir){
            
        }
    }
}
