/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pràcticauf3_2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Scanner;
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
        "3)Consulta d'un client per codi","4)Modificar un client", "5)Esborrar un client", "6)Llistat de tots els clients", 
        "7)Sortir"};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        /*Creem el fitxer dels clients, el OutputStream, el FileInputStream i el DataInputStream. L'exercici és de fitxers binaris i utilitzem les funcions
        corresponents a fitxers binaris per escriure, però per algun motiu que desconeixem escriu en format text. Per llegir també volem utilitzar les
        funcions de binaris, però segurament no les coneixem totes i per fer el programa ho hem pogut fer amb les de text, aprofitant que també s'escriu
        en format text...*/
        File fitxer_clients = new File("./fitxer_clients.bin");
        OutputStream os = new FileOutputStream(fitxer_clients,true);
        FileInputStream fis = new FileInputStream(fitxer_clients);
        DataInputStream dis = new DataInputStream(fis);
        FileReader reader = new FileReader(fitxer_clients);
        BufferedReader buffer = new BufferedReader(reader);
        buffer.mark(100000);
        //Cridem la funció Menú del Utils per a què ens retorni l'opció escollida
        int opcio_escollida = Utils.Menu(scan,array_opcions);
        System.out.println(""); //Deixem una línia de separació
        
        //Mentre l'opció escollida no sigui sortir
        while (opcio_escollida!=array_opcions.length){
            if (opcio_escollida==OPCIO_1){
                //Cridem la funció AltaClient
                AltaClient(buffer,os);
            }
            else if (opcio_escollida==OPCIO_2){
                //Demanem la posició del client que es vol consultar
                int posicio = DemanarPosicio(buffer);
                ConsultaPerPosicio(buffer,posicio);
            }
            else if (opcio_escollida==OPCIO_3){
                //Demanem el codi del client que es vol consultar
                String codi = DemanarCodi();
                ConsultaPerCodi(buffer,codi);
            }
            else if (opcio_escollida==OPCIO_4){
                //Demanem el codi del client que es vol modificar
                String codi = DemanarCodi();
                ModificarClient(fitxer_clients,reader,buffer,os,codi);
            }
            else if (opcio_escollida==OPCIO_5){
                //Demanem el codi del client que es vol esborrar
                String codi = DemanarCodi();
                EsborrarClient(fitxer_clients,reader,buffer,os,codi);
            }
            else if (opcio_escollida==OPCIO_6){
                LlistatClients(buffer);
            }
            System.out.println(""); //Deixem una línia de separació
            opcio_escollida = Utils.Menu(scan,array_opcions);
            System.out.println(""); //Deixem una línia de separació
        }
        //Tanquem tot
        scan.close();
        reader.close();
        os.flush();
        os.close();
    }
    
    /**
     * Funció que demana el codi del client
     * @return Retorna el codi en tipus String
     */
    public static String DemanarCodi (){
        //Demanem el codi mitjançant LlegirInt. Com a màxim ha de ser de 6 xifres, posem un tope de 999999
        int codi = Utils.LlegirInt(scan, "Introdueix el codi: ", 0, TOPE_NUMEROS_6_XIFRES);
        //Transformem el codi en String
        String codi_string = Integer.toString(codi);
        //Mirem la seva longitud i si no té la longitud pre-establerta afegim espais al final fins arribar a la longitud pre-establerta
        int longitud_codi_string = codi_string.length();
        if (longitud_codi_string<LONGITUD_CODI){
            for (int i=1;i<=LONGITUD_CODI-longitud_codi_string;i++){
                codi_string = codi_string+" ";
            }
        }
        //Retornem el codi
        return codi_string;
    }
    
    /**
     * Funció que demana el nom del client
     * @return Retorna el nom
     */
    public static String DemanarNom(){
        //Demanem el nom mitjançant LlegirString
        String nom = Utils.LLegirString("Introdueix el nom: ");
        //Si el nom és de més 20 caràcters, el tornem a demanar
        while (nom.length()>LONGITUD_NOM){
            nom = Utils.LLegirString("Introdueix el nom: ");
        }
        //Si és de menys de 20, omplim al final amb espais
        int longitud_nom = nom.length();
        if (longitud_nom<LONGITUD_NOM){
            for (int i=1;i<=20-longitud_nom;i++){
                nom = nom+" ";
            }           
        }
        //Retornem el nom
        return nom;
    }
    
    /**
     * Funció que demana els cognoms del client
     * @return Retorna els cognoms del client
     */
    public static String DemanarCognoms(){
        //Demanem els cognoms mitjançant LlegirString
        String cognoms = Utils.LLegirString("Introdueix els cognoms: ");
        //Si el String té una longitud superior a 30, els tornem a demanar
        while (cognoms.length()>LONGITUD_COGNOMS){
            cognoms = Utils.LLegirString("Introdueix els cognoms: ");
        }
        //Si és de menys de 30, omplim al final amb espais
        int longitud_cognoms = cognoms.length();
        if (longitud_cognoms<LONGITUD_COGNOMS){
            for (int i=1;i<=LONGITUD_COGNOMS-longitud_cognoms;i++){
                cognoms = cognoms+" ";
            }           
        }
        //Retornem els cognoms
        return cognoms;
    }
    
    /**
     * Funció que demana la data de naixement del client
     * @return Retorna la data de naixement en tipus String
     */
    public static String DemanarDataNaixement(){
        //Demanem la data i la transformem a String mitjançan LlegirData del Utils
        String data = Utils.LlegirData("ddMMyyyy", "Introdueix la data de naixement (DDMMYYYY): ");
        //Retornem la data
        return data;
    }
    
    /**
     * Funció que demana l'adreça postal del client
     * @return Retorna l'adreça postal en tipus String
     */
    public static String DemanarAdreçaPostal(){
        //Demanem l'adreça postal mitjançan LlegirAdreçaPostal del Utils
        String adreça_postal = Utils.LlegirAdreçaPostal("Introdueix la adreça postal: ");
        //Retornem l'adreça postal
        return adreça_postal;
    }
    
    /**
     * Funció que demana l'email del client
     * @return Retorna l'email del client
     */
    public static String DemanarEmail(){
        //Demanem l'email mitjançant LlegirString
        String email = Utils.LLegirString("Introdueix el e-mail: ");
        //Si és de més de 30 caràcters, el tornem a demanar
        while (email.length()>LONGITUD_EMAIL){
            email = Utils.LLegirString("Introdueix el e-mail: ");
        }
        //Si és de menys de 30 caràcters, omplim al final amb espais
        int longitud_email = email.length();
        if (longitud_email<LONGITUD_EMAIL){
            for (int i=1;i<=LONGITUD_EMAIL-longitud_email;i++){
                email = email+" ";
            }           
        }
        //Retornem l'email
        return email;
    }
    
    /**
     * Funció que demana la posició del client en la llista
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer
     * @return Retorna la posició del client
     * @throws IOException 
     */
    public static int DemanarPosicio(BufferedReader buffer) throws IOException{
        //Mirem quantes línies té el fitxer
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        //Demanem la posició mitjançant LlegirInt posant com a tope el número de línies
        int posicio = Utils.LlegirInt(scan, "Introdueix la posicio: ", 1, numero_linies);
        //Retornem la posició
        return posicio;
    }
    
    /**
     * Procediment que escriu en el fitxer
     * @param os Objecte os de OutputStream per escriure en el fitxer
     * @param dada Dada que escrivim
     * @throws IOException 
     */
    public static void Escriure(OutputStream os, String dada) throws IOException {
        //Esrivim mitjançant os.write
        os.write(dada.getBytes(),0,dada.length());        
    }
    
    /**
     * Procediment que dóna d'alta a un client nou
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer
     * @param os Objecte os de OutputStream per escriure en el fitxer
     * @throws IOException 
     */
    public static void AltaClient (BufferedReader buffer, OutputStream os) throws IOException{
        //Demanem cada dada del client i l'afegim a informacio_client
        String codi = DemanarCodi();
        //Mirem pel codi que el client no estigui ja en el fitxer. Si ho està, haurem de tornar a demanar el codi
        boolean validat = false;
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        while (!validat){
            boolean codi_trobat=false;
            for (int i=1;i<=numero_linies;i++){
                if (buffer.readLine().contains(codi)){
                    codi_trobat=true;
                }
            }
            buffer.reset();
            if (!codi_trobat){
                validat=true;
            }
            else{
                codi = DemanarCodi();
            }
        }
        Escriure(os,codi);
        String nom = DemanarNom();
        Escriure(os,nom);
        String cognoms = DemanarCognoms();
        Escriure(os,cognoms);
        String data_naixement = DemanarDataNaixement();
        Escriure(os,data_naixement);
        String adreça_postal = DemanarAdreçaPostal();
        Escriure(os,adreça_postal);
        String email = DemanarEmail();
        Escriure(os,email);
        //Salt de línia
        Escriure(os,"\n");
    }
    
    /**
     * Procediment que consulta el client per la posició en la llista
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer
     * @param posicio Posició en la llista del client que es busca
     * @throws IOException 
     */
    public static void ConsultaPerPosicio(BufferedReader buffer, int posicio) throws IOException {
        //Recorrem les línies del fitxer per imprimir la línia del client que toca
        boolean acabat = false;
        for (int i = 1; !acabat; i++) {
            String linia = buffer.readLine();
            if (i == posicio) {
                System.out.println(linia);
                acabat = true;
                buffer.reset();
            }
        }
    }
    
    /**
     * Procediment que consulta el client pel codi
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer
     * @param codi Codi del client que es busca
     * @throws IOException 
     */
    public static void ConsultaPerCodi(BufferedReader buffer, String codi) throws IOException {
        //Recorrem les línies del fitxer per imprimir la que conté el codi
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        for (int i = 1; i<=numero_linies; i++) {
            String linia = buffer.readLine();
            if (linia.contains(codi)) {
                System.out.println(linia);        
            }           
        }
        buffer.reset();
    }   
    
    /**
     * Procediment que modifica la informació d'un client
     * @param fitxer_clients Fitxer dels clients
     * @param reader Objecte reader de FileReader per llegir el fitxer_clients
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer_clients
     * @param os Objecte os de OutputStream per escriure en el fitxer_clients
     * @param codi Codi del client perquè identifiquem el client que volem modificar pel codi
     * @throws IOException 
     */
    public static void ModificarClient(File fitxer_clients, FileReader reader, BufferedReader buffer, OutputStream os, String codi) throws IOException{
        //Creem un nou arxiu per reescriure tot, amb les modificacions
        File fitxer_clients_nou = new File ("./fitxer_clients_nou.bin");
        FileOutputStream os_nou = new FileOutputStream(fitxer_clients_nou,false);
        //Validem el codi perquè si no està en el fitxer no hi ha client per modificar
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        boolean validat = false;
        while (!validat){
            boolean codi_trobat=false;
            for (int i=1;i<=numero_linies;i++){
                if (buffer.readLine().contains(codi)){
                    codi_trobat=true;
                }
            }
            buffer.reset();
            if (codi_trobat){
                validat=true;
            }
            else{
                codi = DemanarCodi();
            }
        }
        System.out.println(""); //Deixem una línia de separació
        //Demanem les noves dades
        System.out.println("Insereix les noves dades del client");
        //Mirem que el codi estigui en el fitxer perquè si no, no té sentit
        String codi_nou = DemanarCodi();        
        String nom = DemanarNom();
        String cognoms = DemanarCognoms();
        String data_naixement = DemanarDataNaixement();
        String adreça_postal = DemanarAdreçaPostal();
        String email = DemanarEmail();
        //Creem la nova línia juntant totes les dades
        String nova_linia = codi_nou+nom+cognoms+data_naixement+adreça_postal+email;

        /*Recorrem totes les línies per anar escrivint en el nou arxiu. Si la línia no és la modificada, l'escrivim tal qual. En canvi, si és la que conté
        el codi, escrivim la nova línia*/
        buffer.reset();
        for (int i = 1; i<=numero_linies; i++) {
            String linia = buffer.readLine();           
            if (linia.contains(codi)) {
                linia=linia.replaceAll(linia,nova_linia);
            }
            linia=linia+"\n";
            Escriure(os_nou,linia);
        }
        //Tanquem tot per poder reemplaçar els arxius
        buffer.reset();
        buffer.close();
        reader.close();
        os.flush();
        os.close();
        os_nou.flush();
        os_nou.close();
        Files.delete(fitxer_clients.toPath()); //No es borra perquè diu que l'arxiu està sent utilitzat per un altre procés
        //fitxer_clients_nou.renameTo(fitxer_clients);
    }
    
    public static void EsborrarClient(File fitxer_clients, FileReader reader, BufferedReader buffer, OutputStream os, String codi) throws FileNotFoundException, IOException{
        //Creem un nou arxiu per reescriure tot, sense el client esborrat
        File fitxer_clients_nou = new File ("./fitxer_clients_nou.bin");
        FileOutputStream os_nou = new FileOutputStream(fitxer_clients_nou,false);
        //Recorrem el fitxer antic per afegir totes les línies en el nou excepte la del client que volem esborrar
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        for (int i = 1; i<=numero_linies; i++) {
            String linia = buffer.readLine();
            if (!linia.contains(codi)) {
                Escriure(os_nou,linia);
            }           
        }
        //Tanquem tot per poder reemplaçar els arxius
        buffer.reset();
        buffer.close();
        reader.close();
        os.flush();
        os.close();
        os_nou.flush();
        os_nou.close();
        Files.delete(fitxer_clients.toPath()); //No es borra perquè diu que l'arxiu està sent utilitzat per un altre procés
        //fitxer_clients_nou.renameTo(fitxer_clients);        
    }
    
    /**
     * Procediment que mostrat tot el llistat de clients
     * @param buffer Objecte buffer de BufferedReader per llegir el fitxer
     * @throws IOException 
     */
    public static void LlistatClients(BufferedReader buffer) throws IOException{
        //Recorrem les línies i les anem imprimint
        int numero_linies = (int)buffer.lines().count();
        buffer.reset();
        for (int i=1;i<=numero_linies;i++){
            System.out.println(buffer.readLine());
        }
        buffer.reset();
    }
}
