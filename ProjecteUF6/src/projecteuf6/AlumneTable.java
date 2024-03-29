package projecteuf6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class AlumneTable extends ORMTable {

    // <editor-fold defaultstate="collapsed" desc="Propietats de l'objecte">
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public AlumneTable() {
        super("ALUMNE");
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters i Setters">
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Operacions amb la Base de Dades">
    /**
     * Inserta l'objecte a la taula de Persones
     *
     * @return int: nombre de files afectades
     * @throws NullConnectionException: La connexió és nul·la o es troba tancada
     * @throws SQLException: Qualsevol altra excepció SQL
     */
    @Override
    public int Insert(ORMEntity o) throws NullConnectionException, SQLException {
        int numFilesAfectades = 0;
        if (getBDConnection() == null) {
            throw new NullConnectionException();
        }

        if (getBDConnection().getConnection() == null) {
            throw new NullConnectionException();
        }

        try {
            if (getBDConnection().getConnection().isClosed()) {
                throw new NullConnectionException();
            }
        } catch (SQLException e) {
            throw new NullConnectionException();
        }
        try {
            AlumneEntity p = (AlumneEntity) o;
            boolean majorEdat = false;
            if (p.getEdat() >= 18) {
                majorEdat = true;
            }
            
            String sqlCommand = "INSERT INTO ALUMNE (nom, edat, majorEdat) VALUES ("
                    + "'" + p.getNom().replace("'", "''") + "', " + p.getEdat() + "," + p.isMajorEdat() + ");";

            Statement st = getBDConnection().getConnection().createStatement();
            numFilesAfectades = st.executeUpdate(sqlCommand);
            st.close();

            //Confirma els canvis
            getBDConnection().getConnection().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nom Invàlid");
        }
        return numFilesAfectades;
    }

    public int Update(ORMEntity o) throws NullConnectionException, SQLException {
        int numFilesAfectades = 0;
        if (getBDConnection() == null) {
            throw new NullConnectionException();
        }

        if (getBDConnection().getConnection() == null) {
            throw new NullConnectionException();
        }

        try {
            if (getBDConnection().getConnection().isClosed()) {
                throw new NullConnectionException();
            }
        } catch (SQLException e) {
            throw new NullConnectionException();
        }
        try {
            AlumneEntity p = (AlumneEntity) o;
            boolean major = false;
            if (p.getEdat() >= 18) {
                major = true;
            }
            String sqlCommand = "UPDATE ALUMNE SET nom = '"
                    + p.getNom().replace("'", "''") + "', edat = " + p.getEdat() + ", majorEdat = " + major + " WHERE codiAlumne = " + p.getCodiAlumne() + ";";

            Statement st = getBDConnection().getConnection().createStatement();
            numFilesAfectades = st.executeUpdate(sqlCommand);
            st.close();

            //Confirma els canvis
            getBDConnection().getConnection().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nom Invàlid");

        }
        return numFilesAfectades;
    }

    public int Delete(ORMEntity o) throws NullConnectionException, SQLException {
        if (getBDConnection() == null) {
            throw new NullConnectionException();
        }

        if (getBDConnection().getConnection() == null) {
            throw new NullConnectionException();
        }

        try {
            if (getBDConnection().getConnection().isClosed()) {
                throw new NullConnectionException();
            }
        } catch (SQLException e) {
            throw new NullConnectionException();
        }
        AlumneEntity p = (AlumneEntity) o;
        String sqlCommand = "DELETE FROM ALUMNE WHERE codiAlumne = "
                + p.getCodiAlumne() + ";";

        Statement st = getBDConnection().getConnection().createStatement();
        int numFilesAfectades = st.executeUpdate(sqlCommand);
        st.close();

        //Confirma els canvis
        getBDConnection().getConnection().commit();

        return numFilesAfectades;
    }

    /**
     * Obté tots els registres de la taula
     *
     * @return Retorna una llista amb tots els registres de la taula
     * @throws NullConnectionException
     * @throws SQLException
     */
    @Override
    public ArrayList<AlumneEntity> GetAll() throws NullConnectionException, SQLException {

        ArrayList<AlumneEntity> resultList = new ArrayList<AlumneEntity>();

        Statement consulta = getBDConnection().getConnection().createStatement();
        ResultSet resultat = consulta.executeQuery("SELECT * FROM ALUMNE");

        while (resultat.next()) {
            AlumneEntity p = new AlumneEntity(
                    resultat.getInt("codiAlumne"),
                    resultat.getString("Nom"),
                    resultat.getInt("Edat"));

            resultList.add(p);
        }

        //Tancar resultat i consulta
        resultat.close();
        consulta.close();

        return resultList;
    }
    // </editor-fold>

}
