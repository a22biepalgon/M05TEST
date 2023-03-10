/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf3_4;

import utils.Utils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rexru
 */
public class PràcticaUF3_4 {

    public static Scanner scan = new Scanner(System.in);

    //CONSTANTS
    public static final String NOM_FITXER_DADES = "./fitxer_clients.bin";
    public static final String NOM_FITXER_DADES_REEMPLAÇ = "./fitxer_clients_nou.bin";
    /*Nom del fitxer de clients momentani que creem quan volem
    modificar el fitxer original*/
    public static final String NOM_FITXER_INDEX = "./fitxer_clients.idx_pos";
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
    public static final int TAMANY_REGISTRE_INDEX = 8+4+4; //Tamany en bits d'un registre a líndex: long+int
    

    /*Classe clients. Està tot en String perquè el codi prové de la pràctica anterior en què la funció d'escriure escriu ja el String.
    La validació de la dada la fa abans*/
    public static class Clients {

        int Codi;
        String Nom;
        String Cognoms;
        String DataNaixement;
        String AdreçaPostal;
        String eMail;
        String VIP;
    }

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
     *
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
                    ConsultaPerCodi(codi.trim());
                    break;
                }
                case OPCIO_4: {
                    //Demanem el codi del client que es vol modificar
                    String codi = DemanarCodi();
                    ModificarClient(codi.trim());
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

    public static File ObrirFitxer(String nom_fitxer) {

        File result = new File(nom_fitxer);

        if (!result.exists()) {
            try {
                result.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
                result = null;
            }
        }

        return result;
    }

    public static DataInputStream ObrirFitxerLectura(String nom_fitxer) {
        DataInputStream dis = null;
        File f = ObrirFitxer(nom_fitxer);

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileInputStream reader;
            try {
                reader = new FileInputStream(f);
                // PrintWriter para poder escribir más comodamente
                dis = new DataInputStream(reader);
            } catch (IOException ex) {
                Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dis;
    }

    public static DataOutputStream ObrirFitxerEscriptura(File f, boolean afegir) {
        DataOutputStream dos = null;

        if (f != null) {
            // Declarar el writer para poder escribir en el fichero¡
            FileOutputStream writer;
            try {
                writer = new FileOutputStream(f, afegir);
                // PrintWriter para poder escribir más comodamente
                dos = new DataOutputStream(writer);
            } catch (IOException ex) {
                Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dos;
    }

    public static void TancarFitxerLectura(DataInputStream dis) {
        try {
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void TancarFitxerLectura(RandomAccessFile raf) {
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void TancarFitxerEscriptura(DataOutputStream dos) {
        try {
            dos.flush();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void BorrarFitxer(String nom_fitxer) {
        File f = new File(nom_fitxer);
        f.delete();
    }

    public static void RenombrarFitxer(String nom_fitxer_inici, String nom_fitxer_final) {
        File f = new File(nom_fitxer_inici);
        File f2 = new File(nom_fitxer_final);
        f.renameTo(f2);
    }
    
    public static int ComptarRegistres (){
        DataInputStream dis = ObrirFitxerLectura(NOM_FITXER_INDEX);
        String borrat_string = LlegirBorrat(dis);
        int count = 0;
        while (borrat_string!=null){
            if (Integer.parseInt(borrat_string)==1){
                count++;
            }
            borrat_string = LlegirBorrat(dis);
        }
        return count;
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

    public static String DemanarVIP() {
        //Demanem el VIP mitjançant LlegirBoolean
        boolean VIP = Utils.LlegirBoolean(scan, "Introdueix si el client és VIP: ");
        String VIP_string = Boolean.toString(VIP);

        //Retornem el VIP
        return VIP_string;
    }

    /**
     * Funció que demana la posició del client en la llista
     *
     * @return Retorna la posició del client
     * @throws IOException
     */
    public static int DemanarPosicio() throws IOException {
        //Obrim el fitxer de clients
        File f = ObrirFitxer(NOM_FITXER_DADES);
        //Demanem la posició mitjançant LlegirInt posant com a tope el número de línies
        int numero_registres = ComptarRegistres();
        int posicio = Utils.LlegirInt(scan, "Introdueix la posicio: ", 1, numero_registres);
        //Retornem la posició
        return posicio;
    }

    public static String LlegirLiniaDades(RandomAccessFile raf) {
        String linia;
        try {
            //Anem llegint les dades de la línia mitjançant el "dis"
            String codi = raf.readUTF();
            String nom = raf.readUTF();
            String cognoms = raf.readUTF();
            String data_naixement = raf.readUTF();
            String codi_postal = raf.readUTF();
            String email = raf.readUTF();
            String VIP = raf.readUTF();
            String salt_linia = raf.readUTF();
            linia = codi + nom + cognoms + data_naixement + codi_postal + email + VIP;
        } catch (IOException ex) {
            linia = null;
        }
        //Retornem la línia
        return linia;
    }
    
    public static String LlegirLiniaIndex(RandomAccessFile raf) {
        String linia;
        try {
            //Anem llegint les dades de la línia mitjançant el "dis"
            long posicio = raf.readLong();
            int codi = raf.readInt();
            int borrat = raf.readInt();
            linia = Integer.toString(codi);
        } catch (IOException ex) {
            linia = null;
        }
        //Retornem la línia
        return linia;
    }
    
    public static String LlegirBorrat(DataInputStream dis) {
        String borrat;
        try {
            //Anem llegint les dades de la línia mitjançant el "dis"
            long posicio = dis.readLong();
            int codi = dis.readInt();
            borrat = Integer.toString(dis.readInt());
         
        } catch (IOException ex) {
            borrat = null;
        }
        //Retornem la línia
        return borrat;
    }

    public static DataOutputStream EscriureIndexClientPosicio(long posicio) {
        File f = ObrirFitxer(NOM_FITXER_INDEX);
        DataOutputStream dos = ObrirFitxerEscriptura(f, true);

        try {
            dos.writeLong(posicio);
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dos;
    }

    public static void EscriureIndexClientCodi(DataOutputStream dos, int codi) {
        try {
            dos.writeInt(codi);
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void EscriureIndexClientMarcaBorrat(DataOutputStream dos, int marca_borrat) {
        try {
            dos.writeInt(marca_borrat);
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }

        TancarFitxerEscriptura(dos);
    }

    /**
     * Procediment que escriu en el fitxer
     *
     * @param dos El DataOutputStream per escriure
     * @param dada Dada que escrivim
     * @throws IOException
     */
    public static void Escriure(DataOutputStream dos, String dada) throws IOException {
        //Esrivim mitjançant pw.println
        dos.writeUTF(dada);
    }

    /**
     * Procediment que dóna d'alta a un client nou
     *
     * @throws IOException
     */
    public static void AltaClient() throws IOException {
        File f = new File(NOM_FITXER_DADES);
        if (!f.exists()) {
            f.createNewFile();
        }
        //Demanem cada dada del client i l'afegim a informacio_client
        Clients client_nou = new Clients();
        String codi = CodiNoTrobat();
        client_nou.Codi = Integer.parseInt(codi.trim());
        EscriureClient(client_nou, f);
    }

    /**
     * Funcio que retorna el numero de codi si aquest no existeix al fitxer
     * actual
     *
     * @return etorna el numero de codi en string
     * @throws IOException
     */
    public static String CodiNoTrobat() throws IOException {
        //Obrim el fitxer de lectura
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
        String codi = DemanarCodi();
        //Mirem pel codi que el client no estigui ja en el fitxer. Si ho està, haurem de tornar a demanar el codi
        boolean validat = false;
        while (!validat) {
            boolean codi_trobat = false;
            String linia = LlegirLiniaDades(raf);
            while (linia != null) {
                if (linia.contains(codi)) {
                    codi_trobat = true;
                }
                linia = LlegirLiniaDades(raf);
            }
            if (!codi_trobat) {
                validat = true;
            } else {
                codi = DemanarCodi();
            }
        }

        //Tanquem el fitxer de lectura
        raf.close();

        //Retornem el codi
        return codi;
    }

    /**
     * Procediment per a escriure un nou client
     *
     * @param client_nou nou codi que s'afegeix al fitxer
     * @param f fitxer sobre el qual es treballa
     * @throws IOException
     */
    public static void EscriureClient(Clients client_nou, File f) throws IOException {
        //Obrim fitxer de lectura i escriptura
        DataOutputStream dos_fitxer_dades = ObrirFitxerEscriptura(f, true);
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
        
        String linia = LlegirLiniaDades(raf);
        if (linia==null){
            DataOutputStream dos_fitxer_index = EscriureIndexClientPosicio(0);
            EscriureIndexClientCodi(dos_fitxer_index,client_nou.Codi);
            EscriureIndexClientMarcaBorrat(dos_fitxer_index,1);
        }
        else{
            DataOutputStream dos_fitxer_index = EscriureIndexClientPosicio(f.length());
            EscriureIndexClientCodi(dos_fitxer_index,client_nou.Codi);
            EscriureIndexClientMarcaBorrat(dos_fitxer_index,1);
        }
             
        Escriure(dos_fitxer_dades, Integer.toString(client_nou.Codi));
        client_nou.Nom = DemanarNom();
        Escriure(dos_fitxer_dades, client_nou.Nom);
        client_nou.Cognoms = DemanarCognoms();
        Escriure(dos_fitxer_dades, client_nou.Cognoms);
        client_nou.DataNaixement = DemanarDataNaixement();
        Escriure(dos_fitxer_dades, client_nou.DataNaixement);
        client_nou.AdreçaPostal = DemanarAdreçaPostal();
        Escriure(dos_fitxer_dades, client_nou.AdreçaPostal);
        client_nou.eMail = DemanarEmail();
        Escriure(dos_fitxer_dades, client_nou.eMail);
        client_nou.VIP = DemanarVIP();
        Escriure(dos_fitxer_dades, client_nou.VIP);
        //Salt de línia
        Escriure(dos_fitxer_dades, "\n");

        //Tanquem el fitxer d'escriptura
        TancarFitxerEscriptura(dos_fitxer_dades);
    }

    public static int StringaInt(String codi_String) {
        int resultat = 0;
        String resultatS = "";
        for (int i = 0; i < codi_String.length() - 1; i++) {
            if (!codi_String.substring(i, i + 1).contains(" ")) {
                resultatS = codi_String.substring(i, i + 1);
            }
        }
        resultat = Integer.parseInt(resultatS);
        return resultat;
    }

    /**
     * Procediment que consulta el client per la posició en la llista
     *
     * @param posicio Posició en la llista del client que es busca
     * @throws IOException
     */
    public static void ConsultaPerPosicio(int posicio) throws IOException {

        long posicio_index = (posicio - 1) * TAMANY_REGISTRE_INDEX;
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_INDEX, "r");
        raf.seek(posicio_index);
        long posicio_dades = raf.readLong();
        raf.close();
        boolean valid = ComprobarBorrado(posicio_index);

        if (valid) {

            RandomAccessFile rafClient = new RandomAccessFile(NOM_FITXER_DADES, "r");
            rafClient.seek(posicio_dades);
            String linia = LlegirLiniaDades(rafClient);
            System.out.println(linia);
            rafClient.close();
        }
    }

    public static boolean ComprobarBorrado(long posicio_index) {
        boolean resultat = false;
        try {
            posicio_index = posicio_index+8+4;
            RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_INDEX, "r");
            raf.seek(posicio_index);
            int marca_borrat = raf.readInt();
            if (marca_borrat == 1) {
                resultat = true;
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PràcticaUF3_4.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    /**
     * Procediment que consulta el client pel codi
     *
     * @param codi Codi del client que es busca
     * @throws IOException
     */
    public static void ConsultaPerCodi(String codi) throws IOException {
        //Obrim el fitxer de lectura
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_INDEX, "r");
        //Recorrem les línies del fitxer per imprimir la que conté el codi
        String mirarcodi = LlegirLiniaIndex(raf);
        int count = 0;
        boolean valid = false;
        long posicio_index = 0;
        while (mirarcodi!=null && !valid) {
            
            count++;

            if (mirarcodi.contains(codi)) {
                posicio_index = (count - 1) * TAMANY_REGISTRE_INDEX;
                valid = ComprobarBorrado(posicio_index);
            }
            mirarcodi = LlegirLiniaIndex(raf);

        }
        
        if (valid) {
            raf.seek(posicio_index);
            long posicio_dades = raf.readLong();
            RandomAccessFile rafClient = new RandomAccessFile(NOM_FITXER_DADES, "r");
            rafClient.seek(posicio_dades);
            String linia = LlegirLiniaDades(rafClient);
            System.out.println(linia);
            rafClient.close();
            
        }
        //Tanquem el fitxer de lectura
        TancarFitxerLectura(raf);
    }

    /**
     * Funció que serveix per a comprovar si un codi existeix al fitxer
     *
     * @param codi codi inserit per primer cop a el lector
     * @return retorna el codi si es troba al fitxer en String
     * @throws IOException
     */
    public static String CodiTrobat(String codi) throws IOException {

        boolean validat = false;
        while (!validat) {
            //Obrim el fitxer de lectura
            RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
            boolean codi_trobat = false;
            String linia = LlegirLiniaDades(raf);
            while (linia != null) {
                if (linia.contains(codi)) {
                    codi_trobat = true;
                }
                linia = LlegirLiniaDades(raf);
            }
            //Tanquem el fitxer de lectura
            if (codi_trobat) {
                validat = true;
            } else {
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
        File f = new File(NOM_FITXER_DADES);
        //Validem el codi perquè si no està en el fitxer no hi ha client per modificar
        codi = CodiTrobat(codi);
        EsborrarClient(codi);
        System.out.println(""); //Deixem una línia de separació
        Clients client_nou = new Clients();
        System.out.println("Insereix les noves dades del client"); 
        String codi_nou = CodiNoTrobat();
        client_nou.Codi = Integer.parseInt(codi_nou.trim());
        EscriureClient(client_nou, f);

    }

    public static String NovaLinia() {
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
        //Creem un nou arxiu per reescriure tot, sense el client esborrat
        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
        RandomAccessFile raf2 = new RandomAccessFile(NOM_FITXER_INDEX, "rw");
        long posicio_index = 0;
        String mirarcodi = LlegirLiniaIndex(raf);
        int count = 0;
        boolean valid = false;
        while (mirarcodi!=null && !valid) {
            count++;
            if (mirarcodi.contains(codi)){
                posicio_index = (count - 1) * TAMANY_REGISTRE_INDEX;
                valid = ComprobarBorrado(posicio_index);
            }
            mirarcodi = LlegirLiniaDades(raf);
            
        }
        if (valid){
            posicio_index = posicio_index+8+4;
            raf2.seek(posicio_index);
            raf2.writeLong(0);
        }        

        //Tanqem
        TancarFitxerLectura(raf);
        TancarFitxerLectura(raf2);
    }

    /**
     * Procediment que mostrat tot el llistat de clients
     *
     * @throws IOException
     */
    public static void LlistatClients() throws IOException {
        //Obrim el fitxer de lectura

        //Imprimim les línies
        int[] indexos = IndexArray();

        Utils.BubbleSort(indexos);
        for (int i = 0; i < indexos.length; i++) {
            RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
            String linia = LlegirLiniaDades(raf);

            while (linia != null) {
                String numero = "" + indexos[i];
                int posicio_index = (indexos[i] -1) * 16;
                if (linia.contains(numero) && ComprobarBorrado(posicio_index)) {
                    System.out.println(linia);
                }
                linia = LlegirLiniaDades(raf);
            }
            TancarFitxerLectura(raf);

        }
        //Tanqem
    }

    public static int[] IndexArray() throws FileNotFoundException, IOException {

        RandomAccessFile raf = new RandomAccessFile(NOM_FITXER_DADES, "r");
        String linia = LlegirLiniaDades(raf);
        int contador = 0;
        while (linia != null) {
            linia = LlegirLiniaDades(raf);
            contador++;
        }
        TancarFitxerLectura(raf);
        int[] resultat = new int[contador];
        RandomAccessFile raf2 = new RandomAccessFile(NOM_FITXER_DADES, "r");
        for (int i = 0; i < resultat.length; i++) {
            resultat[i] = NomesCodi(raf2);
        }
        TancarFitxerLectura(raf2);

        return resultat;
    }

    public static int NomesCodi(RandomAccessFile raf) throws IOException {

        int resultat = 0;
        //Anem llegint les dades de la línia mitjançant el "raf"
        String codi = raf.readUTF();
        String nom = raf.readUTF();
        String cognoms = raf.readUTF();
        String data_naixement = raf.readUTF();
        String codi_postal = raf.readUTF();
        String email = raf.readUTF();
        String VIP = raf.readUTF();
        String salt_linia = raf.readUTF();

        resultat = Integer.parseInt(TreureEspais(codi));

        return resultat;

    }

    public static String TreureEspais(String paraula) {
        String resultat = "";
        for (int i = 0; i < paraula.length() - 1; i++) {
            if (!paraula.substring(i, i + 1).contains(" ")) {
                resultat += paraula.substring(i, i + 1);
            }
        }
        return resultat;
    }
}
