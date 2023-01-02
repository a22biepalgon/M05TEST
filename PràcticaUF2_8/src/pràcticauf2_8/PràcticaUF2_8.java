/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf2_8;

/**
 *
 * @author Biel
 */
public class PràcticaUF2_8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    static int Jugada(int[][] tauler, int columna, int jugador){
        int resultat = -1;
        boolean posat = false;
        for (int i = tauler.length; i >= 0 && !posat; i--){
            if (tauler[0][columna] != 0){
                resultat = -1;
                posat = true;
            }else if (tauler[i][columna] == 0){
               tauler [i][columna] = jugador;
               posat = true;
               resultat = i;
            }
                
        }
        return resultat;
    }
    static boolean EnRatlla(int[][] tauler, int fila, int columna){
        //Mira no sortirse per la dreta
        if (columna > tauler[fila].length - 3){
            
        }
        //Mira no sortirse per la esquerra
        if (columna < 4){
            
        }
        //Mira nos sortirse per dalt
        if (fila < 4){
            
        }
        //Mira de no so
        if (fila > tauler.length - 3){
            
        }
    }
    
}
