/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf3_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Crea un programa que llegeixi frases per teclat i les vagi guardant en un
 * fitxer “textos.txt”. El programa finalitzarà quan s’entri una cadena buida.
 *
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        //Creem els objectes File, FileWriter i PrintWriter
        File f = new File("./textos.txt");
   
        FileWriter writer = new FileWriter(f,false);
        PrintWriter pw = new PrintWriter(writer);
      
        //Fem un while per anar guardant en l'arxiu fins que el que entri per teclat sigui una cadena buida
        String frase_afegir = scan.nextLine();
        while (!frase_afegir.equals("")){
            pw.println(frase_afegir);
            frase_afegir = scan.nextLine();
        }
        pw.flush();
        writer.close();
        scan.close();
    }
    
}
