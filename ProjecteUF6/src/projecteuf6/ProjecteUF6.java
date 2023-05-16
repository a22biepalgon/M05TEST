/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecteuf6;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ausias
 */
public class ProjecteUF6 {

    /**
     * @param args the command line arguments
     */
    // <editor-fold defaultstate="collapsed" desc="DadesConnexio">
    static final String USER = "a22biepalgon_userm03";
    static final String PWD = "Apeji13$";
    static final String URL = "labs.inspedralbes.cat";
    static final String PORT = "3306";
    static final String BD_NAME = "a22biepalgon_m03_db";
    // </editor-fold> 

    public static void main(String[] args) {
        try {
            // TODO code application logic here

            //Crear la connexió a la BD
            BDConnection bdCon = new BDConnection(URL, PORT, BD_NAME, USER, PWD);
            AlumneTable pt = new AlumneTable();
            pt.setConnection(bdCon);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProjecteUF6.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
