/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf3_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 * Fes un programa que permeti gestionar el fitxer de clients amb les següents
 * operacions: a) Alta d’un client (registrar un client que no existia abans al
 * fitxer) b) Consulta d’un client per posició c) Consulta d’un client per codi
 * d) Modificar un client e) Esborrar un client f) Llistat de tots els clients
 *
 * @author Biel Palomar i Franc Villalba
 */
public class PràcticaUF3_2 {

    public static Scanner scan = new Scanner(System.in);

    //CONSTANTS
    public static String NOM_FITXER = "./fitxer_clients.txt";
    public static String NOM_FITXER_REEMPLAÇ = "./fitxer_clients_nou.txt"; /*Nom del fitxer de clients momentani que creem quan volem
    modificar el fitxer original
    */
    public static final int OPCIO_1 = 1; //Opció 1 del Menú
    public static final int OPCIO_2 = 2; //Opció 2 del Menú
    public static final int OPCIO_3 = 3; //Opció 3 del Menú
    public static final int OPCIO_4 = 4; //Opció 4 del Menú
    public static final int OPCIO_5 = 5; //Opció 5 del Menú
    public static final int OPCIO_6 = 6; //Opció 6 del Menú
    public static final int TOPE_NUMEROS_6_XIFRES = 999999;
    public static final int LONGITUD_CODI = 6; //Longitud que ha de tindre el codi en el fitxer
    public static final int LONGITUD_NOM = 20; //Longitud que ha de tindre el nom en el fitxer
    public static final int LONGITUD_COGNOMS = 30; //Longitud que ha de tindre el String de cognoms al fitxer
    public static final int LONGITUD_EMAIL = 30; //Longitud que ha de tindre el email en el fitxer

    //Creem un array de opcions del menú
    public static final String[] array_opcions = {"1)Alta d'un client", "2)Consulta d'un client per posició",
        "3)Consulta d'un client per codi", "4)Modificar un client", "5)Esborrar un client", "6)Llistat de tots els clients",
        "7)Sortir"};

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        //Cridem la funció Menú del Utils per a què ens retorni l'opció escollida
        int opcio_escollida = Utils.Menu(scan, array_opcions);
        System.out.println(""); //Deixem una línia de separació
        Programa(opcio_escollida);
        
        //Tanquem l'escaner
        scan.close();
    }

    /**
     * Procediment que executa les opcions escollides del menu
     * @param opcio_escollida Opció escollida del menú
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void Programa(int opcio_escollida) throws FileNotFoundException, IOException {
        
        //Mentre l'opció escollida no sigui sortir
        while (opcio_escollida != array_opcions.length) {
            switch (opcio_escollida) {
                case OPCIO_1:
                    //Cridem la funció AltaClient
                    AltaClient();
                    break;
                case OPCIO_2:
                    //Demanem la posició del client que es vol consultar
                    int posicio = DemanarPosicio();
                    ConsultaPerPosicio(posicio);
                    break;
                case OPCIO_3: {
                    //Demanem el codi del client que es vol consultar
                    String codi = DemanarCodi();
                    ConsultaPerCodi(codi);
                    break;
                }
                case OPCIO_4: {
                    //Demanem el codi del client que es vol modificar
                    String codi = DemanarCodi();
                    ModificarClient(codi);
                    break;
                }
                case OPCIO_5: {
                    //Demanem el codi del client que es vol esborrar
                    String codi = DemanarCodi();
                    EsborrarClient(codi);
                    break;
                }
                case OPCIO_6:
                    LlistatClients();
                    break;
                default:
                    break;
            }
            System.out.println(""); //Deixem una línia de separació
            opcio_escollida = Utils.Menu(scan, array_opcions);
            System.out.println(""); //Deixem una línia de separació
        }
    }
    
    public static File ObrirFitxer (String nom_fitxer){
        

        File result = new File(nom_fitxer);

        if (!result.exists()) {
                try {
                    result.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(PràcticaUF3_2.class.getName()).log(Level.SEVERE, null, ex);
                    result = null;
                }
            } 
        

        return result;
    }
    
    public static BufferedReader ObrirFitxerLectura (String nom_fitxer){
        BufferedReader buffer = null;
        File f = ObrirFitxer(nom_fitxer);

        if (f != null) {
            // Declarar el reader para poder leer el fichero¡
            FileReader reader;
            try {
                reader = new FileReader(f);
                // Buffered reader para poder leer más comodamente
                buffer = new BufferedReader(reader);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PràcticaUF3_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return buffer;
    }
    
    public static PrintWriter ObrirFitxerEscriptura (String nom_fitxer, boolean afegir){
        PrintWriter pw = null;
        File f = ObrirFitxer(nom_fitxer);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileWriter writer;
            try {
                writer = new FileWriter(f, afegir);
                // PrintWriter para poder escribir más comodamente
                pw = new PrintWriter(writer);
            } catch (IOException ex) {
                Logger.getLogger(PràcticaUF3_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pw;
    }
    
    public static void TancarFitxerLectura (BufferedReader buffer){
        try {
            buffer.close();
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void TancarFitxerEscriptura (PrintWriter pw){
        pw.flush();
        pw.close();
    }
    
    public static void BorrarFitxer (String nom_fitxer){
        File f = new File(nom_fitxer);
        f.delete();       
    }
    
    public static void RenombrarFitxer (String nom_fitxer_inici, String nom_fitxer_final){
        File f = new File(nom_fitxer_inici);
        File f2 = new File(nom_fitxer_final);
        f.renameTo(f2);
    }
    /**
     * Funció que demana el codi del client
     *
     * @return Retorna el codi en tipus String
     */
    public static String DemanarCodi() {
        //Demanem el codi mitjançant LlegirInt. Com a màxim ha de ser de 6 xifres, posem un tope de 999999
        int codi = Utils.LlegirInt(scan, "Introdueix el codi: ", 0, TOPE_NUMEROS_6_XIFRES);
        //Transformem el codi en String
        String codi_string = Integer.toString(codi);
        //Mirem la seva longitud i si no té la longitud pre-establerta afegim espais al final fins arribar a la longitud pre-establerta
        int longitud_codi_string = codi_string.length();
        if (longitud_codi_string < LONGITUD_CODI) {
            for (int i = 1; i <= LONGITUD_CODI - longitud_codi_string; i++) {
                codi_string = codi_string + " ";
            }
        }
        //Retornem el codi
        return codi_string;
    }

    /**
     * Funció que demana el nom del client
     *
     * @return Retorna el nom
     */
    public static String DemanarNom() {
        //Demanem el nom mitjançant LlegirString
        String nom = Utils.LLegirString("Introdueix el nom: ");
        //Si el nom és de més 20 caràcters, el tornem a demanar
        while (nom.length() > LONGITUD_NOM) {
            nom = Utils.LLegirString("Introdueix el nom: ");
        }
        //Si és de menys de 20, omplim al final amb espais
        int longitud_nom = nom.length();
        if (longitud_nom < LONGITUD_NOM) {
            for (int i = 1; i <= 20 - longitud_nom; i++) {
                nom = nom + " ";
            }
        }
        //Retornem el nom
        return nom;
    }

    /**
     * Funció que demana els cognoms del client
     *
     * @return Retorna els cognoms del client
     */
    public static String DemanarCognoms() {
        //Demanem els cognoms mitjançant LlegirString
        String cognoms = Utils.LLegirString("Introdueix els cognoms: ");
        //Si el String té una longitud superior a 30, els tornem a demanar
        while (cognoms.length() > LONGITUD_COGNOMS) {
            cognoms = Utils.LLegirString("Introdueix els cognoms: ");
        }
        //Si és de menys de 30, omplim al final amb espais
        int longitud_cognoms = cognoms.length();
        if (longitud_cognoms < LONGITUD_COGNOMS) {
            for (int i = 1; i <= LONGITUD_COGNOMS - longitud_cognoms; i++) {
                cognoms = cognoms + " ";
            }
        }
        //Retornem els cognoms
        return cognoms;
    }

    /**
     * Funció que demana la data de naixement del client
     *
     * @return Retorna la data de naixement en tipus String
     */
    public static String DemanarDataNaixement() {
        //Demanem la data i la transformem a String mitjançan LlegirData del Utils
        String data = Utils.LlegirData("ddMMyyyy", "Introdueix la data de naixement (DDMMYYYY): ");
        //Retornem la data
        return data;
    }

    /**
     * Funció que demana l'adreça postal del client
     *
     * @return Retorna l'adreça postal en tipus String
     */
    public static String DemanarAdreçaPostal() {
        //Demanem l'adreça postal mitjançan LlegirAdreçaPostal del Utils
        String adreça_postal = Utils.LlegirAdreçaPostal("Introdueix la adreça postal: ");
        //Retornem l'adreça postal
        return adreça_postal;
    }

    /**
     * Funció que demana l'email del client
     *
     * @return Retorna l'email del client
     */
    public static String DemanarEmail() {
        //Demanem l'email mitjançant LlegirString
        String email = Utils.LLegirString("Introdueix el e-mail: ");
        //Si és de més de 30 caràcters, el tornem a demanar
        while (email.length() > LONGITUD_EMAIL) {
            email = Utils.LLegirString("Introdueix el e-mail: ");
        }
        //Si és de menys de 30 caràcters, omplim al final amb espais
        int longitud_email = email.length();
        if (longitud_email < LONGITUD_EMAIL) {
            for (int i = 1; i <= LONGITUD_EMAIL - longitud_email; i++) {
                email = email + " ";
            }
        }
        //Retornem l'email
        return email;
    }

    /**
     * Funció que demana la posició del client en la llista
     *
     * @return Retorna la posició del client
     * @throws IOException
     */
    public static int DemanarPosicio() throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Mirem quantes línies té el fitxer
        int numero_linies = (int) buffer.lines().count();
        //Demanem la posició mitjançant LlegirInt posant com a tope el número de línies
        int posicio = Utils.LlegirInt(scan, "Introdueix la posicio: ", 1, numero_linies);
        //Tanquem el fitxer de lectura
        TancarFitxerLectura(buffer);
        //Retornem la posició
        return posicio;
    }

    /**
     * Procediment que escriu en el fitxer
     *
     * @param pw
     * @param dada Dada que escrivim
     * @throws IOException
     */
    public static void Escriure(PrintWriter pw, String dada) throws IOException {
        //Esrivim mitjançant pw.println
        pw.print(dada);
    }

    /**
     * Procediment que dóna d'alta a un client nou
     *
     * @throws IOException
     */
    public static void AltaClient() throws IOException {
        //Demanem cada dada del client i l'afegim a informacio_client
        String codi = CodiNoTrobat();
        EscriureClient(codi);

    }

    /**
     * Funcio que retorna el numero de codi si aquest no existeix al fitxer actual
     * @return etorna el numero de codi en string
     * @throws IOException 
     */
    public static String CodiNoTrobat() throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura (NOM_FITXER);
        String codi = DemanarCodi();
        //Mirem pel codi que el client no estigui ja en el fitxer. Si ho està, haurem de tornar a demanar el codi
        boolean validat = false;
        while (!validat) {
            boolean codi_trobat = false;
            String linia = buffer.readLine();
            while (linia != null) {
                if (linia.contains(codi)) {
                    codi_trobat = true;
                }
                linia = buffer.readLine();
            }   
            if (!codi_trobat) {
                validat = true;
            } 
            else {
                codi = DemanarCodi();
            }
        }
      
        //Tanquem el fitxer de lectura
        TancarFitxerLectura(buffer);
        
        //Retornem el codi
        return codi;
    }

    /**
     * Procediment per a escriure un nou client
     *
     * @param codi nou codi que utlitzara el client
     * @throws IOException
     */
    public static void EscriureClient(String codi) throws IOException {
        //Obrim el fitxer d'escriptura
        PrintWriter pw = ObrirFitxerEscriptura(NOM_FITXER,true);
        Escriure(pw,codi);
        String nom = DemanarNom();
        Escriure(pw,nom);
        String cognoms = DemanarCognoms();
        Escriure(pw,cognoms);
        String data_naixement = DemanarDataNaixement();
        Escriure(pw,data_naixement);
        String adreça_postal = DemanarAdreçaPostal();
        Escriure(pw,adreça_postal);
        String email = DemanarEmail();
        Escriure(pw,email);
        //Salt de línia
        Escriure(pw,"\n");
        
        //Tanquem el fitxer d'escriptura
        TancarFitxerEscriptura(pw);
    }

    /**
     * Procediment que consulta el client per la posició en la llista
     *
     * @param posicio Posició en la llista del client que es busca
     * @throws IOException
     */
    public static void ConsultaPerPosicio(int posicio) throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Recorrem les línies del fitxer per imprimir la línia del client que toca
        boolean acabat = false;
        for (int i = 1; !acabat; i++) {
            String linia = buffer.readLine();
            if (i == posicio) {
                System.out.println(linia);
                acabat = true;
            }
        }
        //Tanquem el fitxer de lectura
        TancarFitxerLectura(buffer);
    }

    /**
     * Procediment que consulta el client pel codi
     *
     * @param codi Codi del client que es busca
     * @throws IOException
     */
    public static void ConsultaPerCodi(String codi) throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Recorrem les línies del fitxer per imprimir la que conté el codi
        String linia = buffer.readLine();
        while (linia!=null) {
            if (linia.contains(codi)) {
                System.out.println(linia);
            }
            linia = buffer.readLine();
        }
        //Tanquem el fitxer de lectura
        TancarFitxerLectura(buffer);
    }

    /**
     * Funció que serveix per a comprovar si un codi existeix al fitxer 
     * @param codi codi inserit per primer cop a el lector
     * @return retorna el codi si es troba al fitxer en String
     * @throws IOException 
     */
    public static String CodiTrobat(String codi) throws IOException {
        
        
        boolean validat = false;
        while (!validat) {
            //Obrim el fitxer de lectura
            BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
            boolean codi_trobat = false;
            String linia = buffer.readLine();
            while (linia != null) {
                if (linia.contains(codi)) {
                    codi_trobat = true;
                }
                linia = buffer.readLine();
            }  
            //Tanquem el fitxer de lectura
            TancarFitxerLectura(buffer);
            if (codi_trobat) {
                validat = true;
            } 
            else {
                codi = DemanarCodi();
            }
        }

        //Retornem el codi
        return codi;
    }

    /**
     * Procediment que modifica la informació d'un client
     *
     * @param codi Codi del client perquè identifiquem el client que volem
     * modificar pel codi
     * @throws IOException
     */
    public static void ModificarClient(String codi) throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Creem un nou arxiu per reescriure tot, amb les modificacions
        PrintWriter pw_nou = ObrirFitxerEscriptura(NOM_FITXER_REEMPLAÇ, false);
        //Validem el codi perquè si no està en el fitxer no hi ha client per modificar
        codi = CodiTrobat(codi);
        System.out.println(""); //Deixem una línia de separació
        //Demanem les noves dades
        System.out.println("Insereix les noves dades del client");
        //Creem la nova línia juntant totes les dades
        String nova_linia = NovaLinia();
        /*Recorrem totes les línies per anar escrivint en el nou arxiu. Si la línia no és la modificada, l'escrivim tal qual. En canvi, si és la que conté
        el codi, escrivim la nova línia*/
        String linia = buffer.readLine();
        while (linia!=null) {
            if (linia.contains(codi)) {
                linia = linia.replaceAll(linia, nova_linia);
            }
            linia = linia + "\n";
            Escriure(pw_nou,linia);
            linia = buffer.readLine();
        }
        //Tanquem
        TancarFitxerLectura(buffer);
        TancarFitxerEscriptura(pw_nou);
        
        //Reemplacem el fitxer inicial pel nou
        BorrarFitxer(NOM_FITXER);
        RenombrarFitxer(NOM_FITXER_REEMPLAÇ,NOM_FITXER);
        
        /*Path to = fitxer_clients.toPath();
        Path from = fitxer_clients_nou.toPath();
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(fitxer_clients_nou.toPath()); //No es borra perquè diu que l'arxiu està sent utilitzat per un altre procés
        //fitxer_clients_nou.renameTo(fitxer_clients);*/
    }

    public static String NovaLinia(){
        String resultat = "";
        String codi_nou = DemanarCodi();
        String nom = DemanarNom();
        String cognoms = DemanarCognoms();
        String data_naixement = DemanarDataNaixement();
        String adreça_postal = DemanarAdreçaPostal();
        String email = DemanarEmail();
        
        resultat = codi_nou + nom + cognoms + data_naixement + adreça_postal + email;
        
        return resultat;
    }
    /**
     * Procediment que esborra un client un cop se li dona el codi d'aquest
     *
     * @param codi Codi del client perquè identifiquem el client que volem
     * modificar pel codi
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void EsborrarClient(String codi) throws FileNotFoundException, IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Creem un nou arxiu per reescriure tot, sense el client esborrat
        PrintWriter pw_nou = ObrirFitxerEscriptura(NOM_FITXER_REEMPLAÇ, false);
        //Validem el codi perquè si no està en el fitxer no hi ha client per modificar
        codi = CodiTrobat(codi);
        //Recorrem el fitxer antic per afegir totes les línies en el nou excepte la del client que volem esborrar
        String linia = buffer.readLine();
        while (linia!=null) {
            if (!linia.contains(codi)) {
                Escriure(pw_nou,linia);
                Escriure(pw_nou,"\n");
            }
            linia = buffer.readLine();
        }
        //Tanqem
        TancarFitxerLectura(buffer);
        TancarFitxerEscriptura(pw_nou);
        
        //Reemplacem el fitxer inicial pel nou
        BorrarFitxer(NOM_FITXER);
        RenombrarFitxer(NOM_FITXER_REEMPLAÇ,NOM_FITXER);
        
        /*Files.delete(fitxer_clients.toPath()); //No es borra perquè diu que l'arxiu està sent utilitzat per un altre procés
        //fitxer_clients_nou.renameTo(fitxer_clients);       */ 
    }

    /**
     * Procediment que mostrat tot el llistat de clients
     *
     * @throws IOException
     */
    public static void LlistatClients() throws IOException {
        //Obrim el fitxer de lectura
        BufferedReader buffer = ObrirFitxerLectura(NOM_FITXER);
        //Imprimim les línies
        String linia = buffer.readLine();
        while (linia!=null) {
            System.out.println(linia);
            linia = buffer.readLine();
        }
        //Tanqem
        TancarFitxerLectura(buffer);
    }
}
