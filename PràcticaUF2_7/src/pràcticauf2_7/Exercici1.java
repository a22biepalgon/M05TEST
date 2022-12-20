/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf2_7;

import java.util.Scanner;

/**
 * Escriu una funció anomenada “CalcularDescompte” que, rebi com a paràmetres el
 * preu i la categoria d’un producte i retorni el preu amb el descompte aplicat.
 * Les categories poden ser de 3 tipus: A, B i C. Els productes de categoria A
 * tenen un 5% de descompte, els de categoria B un 10% de descompte i els de
 * categoria C tenen un 20% de descompte si el preu és inferior a 100€ i de 35%
 * si el preu és igual o superior a 100€.
 * 
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici1 {

    /**
     * @param args the command line arguments
     */
    
    //CONSTANTS
    final String CATEGORIA_A = "A";
    final String CATEGORIA_B = "B";
    final String CATEGORIA_C = "C";
        
    public void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //Demanem el preu i la categoria del producte
        double preu = utils.Utils.LlegirFloat("Introdueix el preu del producte: ");
        scan.nextLine();
        String categoria = scan.nextLine();
        //Validem la categoria amb un while fins que sigui vàlida
        while (!categoria.equals(CATEGORIA_A) && !categoria.equals(CATEGORIA_B) && !categoria.equals(CATEGORIA_C)){
            System.out.println("Error, torna a provar");
            categoria = scan.nextLine();
        }        
    }
    
    public double CalcularDescompte (double preu, String categoria){
        //CONSTANTS
        final double DESCOMPTE_CATEGORIA_A = 0.05;
        //Apliquem el descompte segons el tipus de categoria. Cada cas implementat en un if
        //CATEGORIA_A
        if (categoria.equals(CATEGORIA_A)){
            preu = preu - DESCOMPTE_CATEGORIA_A*preu; 
        }
    }
    
}
