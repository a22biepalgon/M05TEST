/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf2_7;

import java.util.Scanner;

/**
 * Escriu un procediment anomenat “Titol” que donat un text, el mostri per
 * pantalla de forma centrada. Pots assumir que la pantalla té un ample de 80
 * caràcters.
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici5 {
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Demanem el Títol
        String titol = utils.Utils.LLegirString("Introdueix un títol: ");
        //Cridem la funció que imprimeix el títol centrat
        Titol(titol);
    }
    
    /**
     * Imprimeix un títol centrat en pantalla
     * @param titol Títol que s'imprimeix
     */
    public static void Titol (String titol){
        
        //CONSTANTS
        final int AMPLADA_PANTALLA = 80;
        final int DIVIDIR_MEITAT_PANTALLA = 2; /*Dividir la pantalla entre 2 per tindre dos
        parts iguals*/
        
        /*Calculem els espais de la pantalla que s'han d'imprimir abans del títol. Ho 
        fem restant-li la longitud del String del títol a l'amplada de la pantalla i després 
        dividit entre 2 ja que la resta de pantalla s'ha de dividir en dos parts iguals*/
        int numero_espais = (AMPLADA_PANTALLA-titol.length())/DIVIDIR_MEITAT_PANTALLA;
        //Fem un for al número d'espais per imprimir-los i després imprimim el títol
        for (int i=1;i<=numero_espais;i++){
            System.out.print(" ");
        }
        System.out.println(titol);
    }
}
