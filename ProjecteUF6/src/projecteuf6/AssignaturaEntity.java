/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecteuf6;

/**
 *
 * @author ausias
 */
public class AssignaturaEntity extends ORMEntity {
        // <editor-fold defaultstate="collapsed" desc="Propietats de l'objecte">
    /* Propietats de l'objecte */
    /* Camps de la taula */
    private int codiAlumne;
    private String nomAssignatura;

    /* Connexió a la Base de dades */
    private BDConnection c;
    //</editor-fold>

    public int getCodiAlumne() {
        return codiAlumne;
    }

    public String getNomAssignatura() {
        return nomAssignatura;
    }

    public BDConnection getC() {
        return c;
    }

    public void setCodiAlumne(int codiAlumne) {
        this.codiAlumne = codiAlumne;
    }

    public void setNomAssignatura(String nomAssignatura) {
        this.nomAssignatura = nomAssignatura;
    }

    public void setC(BDConnection c) {
        this.c = c;
    }

    public AssignaturaEntity(int codiAlumne, String nomAssignatura) {
        this.codiAlumne = codiAlumne;
        this.nomAssignatura = nomAssignatura;
    }
    
}
