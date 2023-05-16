package projecteuf6;

/**
 *
 * @author santi
 */
public class AlumneEntity extends ORMEntity {
    
    // <editor-fold defaultstate="collapsed" desc="Propietats de l'objecte">
    /* Propietats de l'objecte */
    /* Camps de la taula */
    private int codiAlumne;
    private String nom;
    private int edat;
    private boolean majorEdat;

    /* Connexió a la Base de dades */
    private BDConnection c;
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public AlumneEntity(int ID, String nom, int edat) {
        this.codiAlumne = ID;
        this.nom = nom;
        this.edat = edat;
        if (edat >= 18){
            this.majorEdat = true;
        }else{
            this.majorEdat = false;
        }
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters i Setters">
    /** 
     * Getters i Setters 
     */
    public int getCodiAlumne() {
        return codiAlumne;
    }

    public boolean isMajorEdat() {
        return majorEdat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public void setConnection(BDConnection c) {
        this.c = c;
    }
    //</editor-fold>


}
