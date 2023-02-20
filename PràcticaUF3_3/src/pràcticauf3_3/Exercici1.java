/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf3_3;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author Biel Palomar i Franc Villalba
 */
public class Exercici1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        DataOutputStream fEscr = ObrirFitxerEscriptura("prova.dat", true);
        int num_escriure = Utils.LlegirInt();
        while (num_escriure != 0) {
            Escriure(fEscr, num_escriure);
            num_escriure = Utils.LlegirInt();
        }
        fEscr.close();
    }

    public static void Escriure(DataOutputStream fEscr, int dada) throws IOException {
        //Esrivim mitjançant pw.println
        String data1 =  "" + dada;
        fEscr.writeUTF(data1);
        fEscr.flush();
    }

    public static DataOutputStream ObrirFitxerEscriptura(String nom_fitxer, boolean afegir) {
        DataOutputStream dos = null;
        File f = ObrirFitxer(nom_fitxer);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileOutputStream writer;
            try {
                writer = new FileOutputStream(f, afegir);
                // PrintWriter para poder escribir más comodamente
                dos = new DataOutputStream(writer);
            } catch (IOException ex) {
                Logger.getLogger(Exercici1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dos;
    }

    public static File ObrirFitxer(String nom_fitxer) {

        File result = new File(nom_fitxer);

        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Exercici2.class.getName()).log(Level.SEVERE, null, ex);
                result = null;
            }
        }

        return result;
    }
}
