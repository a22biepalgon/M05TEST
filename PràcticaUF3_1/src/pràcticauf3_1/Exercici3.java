/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pràcticauf3_1;

import java.io.BufferedReader;
import utils.Utils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Amplia el programa anterior per tal que: a) Mostri per pantalla tot el
 * contingut del fitxer b) Mostri per pantalla el contingut de la línia X, sent
 * X un número que li demanarem a l’usuari. c) Inserti una línia al començament
 * del fitxer
 *
 * @author Biel i Franc
 */
public class Exercici3 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);

        //Creemel objecte file
        File f = new File("./textos.txt");
        //a) Si el fitxer no existeix, el generem
        if (f.exists()) {
            f.createNewFile();
        }
        //b) Modifiquem l'argument append del FileWriter a true per a què afegeixi el contingut al final del fitxer
        FileWriter writer = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(writer);

        boolean sortir = false;

        //IMprimim el menu
        while (!sortir) {
            System.out.println("******** MENU ********");
            System.out.println("1) Llegir tot el fitxer");
            System.out.println("2) Veure una única línia");
            System.out.println("3) Inserir una linia al començament del fitxer");
            System.out.println("4) Sortir");
            int opcio = Utils.LlegirInt(scan, "Quina opció selecciones?", 1, 5);
            FileReader reader = new FileReader(f);
            BufferedReader buffer = new BufferedReader(reader);

            //Fem unes linies de codi depenent de la funcio
            switch (opcio) {
                //Llegim totes les linies del fitxer
                case 1:
                    String linea = buffer.readLine();
                    while (linea != null) {
                        System.out.println(linea);
                        linea = buffer.readLine();
                    }
                    break;
                    //Llegim una linia del fitxer
                case 2:
                    System.out.print("Quina linia vols llegir: ");
                    int numero = Utils.LlegirInt();
                    boolean acabat = false;
                    for (int i = 1; !acabat; i++) {
                        String linia = buffer.readLine();
                        if (i == numero) {
                            System.out.println(linia);
                            acabat = true;
                        }

                    }
                    break;
                    //Ni idea, en teoria posa la linia al principi del codi, pero no funciona bé
                case 3:
                    System.out.print("Que vols escriure? ");
                    scan.nextLine();
                    String frase_afegir = scan.nextLine();
                    if (frase_afegir.equals("@ESBORRA")) {
                        FileWriter writerr = new FileWriter(f, false);
                        pw.print("");

                    } else {
                        File text_temp = new File("./textos_temp.txt");
                        FileWriter writer_temp = new FileWriter(text_temp, true);
                        PrintWriter pw_temp = new PrintWriter(writer_temp);
                        FileReader reader2 = new FileReader(text_temp);
                        BufferedReader buffer2 = new BufferedReader(reader2);
                        pw_temp.println(frase_afegir);
                        pw_temp.flush();

                        String liniavella = buffer.readLine();
                        pw_temp.println(liniavella);
                        while (liniavella != null) {
                            liniavella = buffer.readLine();
                            if (liniavella != null) {
                                pw_temp.println(liniavella);
                                pw_temp.flush();
                            }
                            System.out.println("a");
                        }
                        FileWriter writerr = new FileWriter(f, false);
                        pw.println("");
                        writerr.close();
                        String liniavella2 = buffer2.readLine();
                        pw.println(liniavella2);
                        pw.flush();

                        while (liniavella2 != null) {
                            liniavella2 = buffer2.readLine();
                            if (liniavella2 != null) {
                                pw.println(liniavella2);
                                pw.flush();
                            }

                        }
                        writer_temp.close();
                        pw_temp.close();
                        text_temp.delete();
                    }
                    break;
                    
                    //Sortim del bucle
                case 4:
                    sortir = true;
                    break;
            }

        }
        //Tanquem tots els objectes
        pw.flush();
        writer.close();
        scan.close();
    }
}
