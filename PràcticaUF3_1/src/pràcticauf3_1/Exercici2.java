/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf3_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Amplia el programa anterior per tal que: 
 * a) Si el fitxer no existeix, que el generi. 
 * b) Si el fitxer ja existeix, que afegeixi el contingut al final. 
 * c) Si l’usuari escriu @ESBORRA, cal esborrar el contingut del fitxer.
 *
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);

        //Creem els objectes File, FileWriter i PrintWriter
        File f = new File("./textos.txt");
        //a) Si el fitxer no existeix, el generem
        if ( f.exists() ){
            f.createNewFile();
        }    
        //b) Modifiquem l'argument append del FileWriter a true per a què afegeixi el contingut al final del fitxer
        FileWriter writer = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(writer);

        //Fem un while per anar guardant en l'arxiu fins que el que entri per teclat sigui una cadena buida
        String frase_afegir = scan.nextLine();
        while (!frase_afegir.equals("")) {
            pw.println(frase_afegir);
            frase_afegir = scan.nextLine();
        }
        pw.flush();
        writer.close();
        scan.close();
    }
}