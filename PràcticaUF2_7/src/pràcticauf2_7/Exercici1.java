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
    final static String CATEGORIA_A = "A";
    final static String CATEGORIA_B = "B";
    final static String CATEGORIA_C = "C";
        
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //Demanem el preu i la categoria del producte
        double preu = utils.Utils.LlegirDouble(scan,"Introdueix el preu del producte: ",0);
        System.out.print("Introdueix la categoria del producte (A,B o C): ");
        String categoria = scan.nextLine();
        //Validem la categoria amb un while fins que sigui vàlida
        while (!categoria.equals(CATEGORIA_A) && !categoria.equals(CATEGORIA_B) && !categoria.equals(CATEGORIA_C)){
            System.out.println("Error, torna a provar");
            System.out.print("Introdueix la categoria del producte (A,B o C): ");
            categoria = scan.nextLine();
        }
        //Cridem la funció que aplica el descompte
        preu = CalcularDescompte (preu,categoria);
        //Imprimim
        System.out.println("El preu amb el descompte aplicat es: " + preu + " €");
    }
    
    /**
     * Calcula el preu final després d'aplicar el descompte segons la categoria
     * del producte. Les categories poden ser de 3 tipus: A, B i C. Els
     * productes de categoria A tenen un 5% de descompte, els de categoria B un
     * 10% de descompte i els de categoria C tenen un 20% de descompte si el
     * preu és inferior a 100€ i de 35% si el preu és igual o superior a 100€.
     * @param preu Preu del producte
     * @param categoria Categoria del producte
     * @return Retorna el preu final amb el descompte aplicat
     */
    public static double CalcularDescompte (double preu, String categoria){
        //CONSTANTS
        final double DESCOMPTE_CATEGORIA_A = 0.05;
        final double DESCOMPTE_CATEGORIA_B = 0.10;
        final double DESCOMPTE_CATEGORIA_C_INFERIOR_A_100 = 0.20;
        final double DESCOMPTE_CATEGORIA_C_IGUAL_O_SUPERIOR_100 = 0.35;
        
        //Apliquem el descompte segons el tipus de categoria. Cada cas implementat en un if
        
        //CATEGORIA_A
        if (categoria.equals(CATEGORIA_A)){
            preu = preu - DESCOMPTE_CATEGORIA_A*preu; 
        }
        //CATEGORIA_B
        else if (categoria.equals(CATEGORIA_B)){
            preu = preu - DESCOMPTE_CATEGORIA_B*preu;
        }
        //CATEGORIA_C
        else{
            //Mirem amb condicionals si el preu és inferior a 100 o igual o superior a 100 per aplicar el descompte corresponent
            if (preu<100){
                preu = preu - preu*DESCOMPTE_CATEGORIA_C_INFERIOR_A_100;
            }
            else{
                preu = preu - preu*DESCOMPTE_CATEGORIA_C_IGUAL_O_SUPERIOR_100;
            }
        }
        //Retornem el preu
        return preu;
    }
    
}
