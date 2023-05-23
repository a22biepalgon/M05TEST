/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteuf6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ausias
 */
public class AssignaturaTable extends ORMTable {

    public AssignaturaTable() {
        super("ALUMNE");
    }

    @Override
    public int Insert(ORMEntity o) throws NullConnectionException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AssignaturaEntity> GetAll() throws NullConnectionException, SQLException {
        /**
         * Obté tots els registres de la taula
         *
         * @return Retorna una llista amb tots els registres de la taula
         * @throws NullConnectionException
         * @throws SQLException
         */
        ArrayList<AssignaturaEntity> resultList = new ArrayList<AssignaturaEntity>();

        Statement consulta = getBDConnection().getConnection().createStatement();
        ResultSet resultat = consulta.executeQuery("SELECT * FROM ASSIGNATURA");

        while (resultat.next()) {
            AssignaturaEntity p = new AssignaturaEntity(
                    resultat.getInt("codiAlumne"), resultat.getString("nomAssignatura"));

            resultList.add(p);
        }

        //Tancar resultat i consulta
        resultat.close();
        consulta.close();

        return resultList;
    }
}
