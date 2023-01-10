/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf2_9;

import java.util.Scanner;

/**
 * Implementació versió iterativa de funcions recursives
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //a)
        //Demanem i validem l'enter n igual o més gran que 0
        int n1 = utils.Utils.LlegirInt(scan, "Introdueix un enter igual o més gran que 0: ", 0);
        //Cridem la funció m1 per calcular el factorial
        int factorial = m1(n1);
        //Imprimim
        System.out.println("El factorial del " + n1 + " és " + factorial);
        
        //b)
        //Demanem i validem l'enter n igual o més gran que 0
        int n2 = utils.Utils.LlegirInt(scan, "Introdueix un enter igual o més gran que 0: ", 0);
        //Cridem la funció m2
        int suma_m2 = m2(0,n2);
        //Imprimim
        System.out.println("La suma de tots els enters positius entre 0 i " + n2 + " és " + suma_m2);
        
        //c)
        //Demanem i validem l'enter n
        int n3 = utils.Utils.LlegirInt("Introdueix un enter: ");
        //Cridem la funció m3
        int suma_m3 = m3(n3);
        //Imprimim
        System.out.println("El resultat de la funció m3 amb l'enter " + n3 + " és " + suma_m3);
    }
    
    //a)
    /**
     * Funció factorial d'un enter n
     * @param n Enter n
     * @return Retorna el valor del factorial
     */
    public static int m1 (int n){
        /*El resultat és una multiplicació dels números enters positius entre 1 i n, per tan 
        iniciem una variable resultat i anem multiplicant el resultat per cada número*/
        int resultat = 1;
        for (int i=1;i<=n;i++){
            resultat = resultat*i;
        }
        //Retornem el resultat
        return resultat;
    }
    
    //b)
    /**
     * Funció que suma tots els enters positius entre 0 i un enter n
     * @param x
     * @param n Enter n
     * @return Retorna la suma
     */
    public static int m2 (double x, int n){
        /*El resultat és la suma de tots els números enters positius entre 0 i n, per tan
        iniciem una variable resultat i anem sumant cada número al resultat
        */
        int resultat = 0;
        for (int i=1;i<=n;i++){
            resultat = resultat+i;
        }
        //Retornem el resultat
        return resultat;
    }
    
    //c)
    public static int m3 (int n){
        
    }
}
