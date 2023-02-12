/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Paquet de la llibreria d'utilitats
 */
package utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Llibreria d'utilitats
 *
 * @author Franc Villalba
 * @version version
 *
 */
public class Utils {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt()">

    /**
     * Crea un punt amb les seves coordenades x i y
     */
    public static class Punt {

        float x;
        float y;
    }

    /**
     * scan null
     */
    private static Scanner scan = null;

    /**
     * Llegeix i valida un enter sense passar scan ni missatge com a paràmetres.
     * Crea un scan i crida la mateixa funció però amb el scan com a paràmetre
     *
     * @return Retorna l'enter validat, que passa per LlegirInt(Scanner
     * scan)>LlegirInt(Scanner scan, String missatge)
     */
    public static int LlegirInt() {
        //S'inicia el resultat
        int result;

        //Com no hi ha scan iniciat, s'inicia
        if (scan == null) {
            scan = new Scanner(System.in);
        }
        //Es crida la funció LlegirInt(Scanner scan)
        result = LlegirInt(scan);

        //Retornem
        return result;
    }

    /**
     * Llegeix i valida un enter passant el missatge que demana l'enter com a
     * paràmetre
     *
     * @param missatge Missatge que demana l'enter
     * @return Retorna l'enter validat, que passa per LlegirInt(Scanner scan,
     * String missatge)
     */
    public static int LlegirInt(String missatge) {
        //S'inicia el resultat
        int result;

        //Com no hi ha scan iniciat, s'inicia
        if (scan == null) {
            scan = new Scanner(System.in);
        }
        //Es crida la funció LlegirInt(Scanner scan, String missatge)
        result = LlegirInt(scan, missatge);

        //Retornem
        return result;
    }

    /**
     * Llegeix i valida un enter passan el scan com a paràmetre
     *
     * @param scan scan per llegir el que entra per teclat
     * @return Retorna l'enter validat passant per LlegirInt(Scanner scan,
     * String missatge)
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    /**
     * Llegeix i valida un enter passant com a paràmetre el scan, el missatge
     * que demana l'enter i uns valors mínim i màxim que acoten l'enter
     *
     * @param scan scan per llegir el que entra per teclat
     * @param missatge Missatge que demana l'enter
     * @param valorMin Valor mínim que acota l'enter, és a dir, l'enter ha de
     * ser igual o més gran que aquest valor
     * @param valorMax Valor màxim que acota l'enter, és a dir, l'enter ha de
     * ser igual o més petit que aquest valor
     * @return Retorna l'enter validat passant per LlegirInt(Scanner scan,
     * String missatge)
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        //S'inicia el resultat
        int result = 0;
        //Crida la funció LlegirInt(Scanner scan, String missatge) mentre l'enter que entre estigui fora de l'interval acotat
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        //Retornem
        return result;
    }
    
    /**
     * Serveix per a llegir un int amb un missatge, i que sigui igual o estigui per sobre
     * d'un minim
     *
     * @param missatge Podem dir el missatge que volem que imprimeixi el
     * programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @return retorna un double introduit per el teclat
     */
    public static int LlegirInt(String missatge, int valorMin) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin);

        return result;
    }

    /**
     * Llegeix i valida un enter passant com a paràmetre el scan i el missatge
     * que demana l'enter
     *
     * @param scan scan per llegir el que entra per teclat
     * @param missatge Missatge que demana l'enter
     * @return Retorna l'enter validat
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        //S'inicia un boolean per saber quan l'enter està validat
        boolean dadesCorrectes;
        //S'inicia el resultat on es guarda l'enter. Com encara no ha estat llegit, inicialment és 0
        int result = 0;
        /*Primer s'imprimeix el missatge que demana l'enter. Després es comprova si el que es llegeix és un enter amb el mètode
        hasNextInt(). Si el que entra no és un enter, es mostra un missatge d'error i com tot està dins d'un while regit pel
        boolean dadesCorrectes, es repeteix l'execució fins que l'enter estigui validat*/
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
                scan.nextLine();
            } else if (scan.hasNext()) {
                scan.next();
                System.out.println("Error, torna a provar");
            }
        } while (!dadesCorrectes);

        //Retornem
        return result;
    }
    
    /**
     * LLegeix un float i el comprova abans de retornar-lo a
     *
     * @return Retorna un valor float
     */
    public static float LlegirFloat() {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan);

        return result;
    }

    /**
     * Serveix per a demanar un float amb un missatge que passarem com a
     * parametre
     *
     * @param missatge POdem demanar un numero amb aquest missatge com a
     * parametre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(String missatge) {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan, missatge);

        return result;
    }

    /**
     * Serveix per a llegir un float amb el nostre scan
     *
     * @param scan Serveix per a posar un scanner que no sigui el nostre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(Scanner scan) {
        return LlegirFloat(scan, null);
    }

    /**
     * Serveix per a llegir un float amb un missatge, i que estigui entre un
     * minim i un maxim
     *
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el
     * programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @param valorMax Diem el valor maxim del numero a introduir
     * @return retorna un float introduit per el teclat
     */
    public static float LlegirFloat(Scanner scan, String missatge, float valorMin, float valorMax) {
        float result = 0;
        do {
            result = LlegirFloat(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Llegeix i valida un float passant com a paràmetre el scan i el missatge
     * que demana l'enter
     *
     * @param scan scan per llegir el que entra per teclat
     * @param missatge Missatge que demana l'enter
     * @return Retorna el float validat
     */
    public static float LlegirFloat(Scanner scan, String missatge) {
        //S'inicia un boolean per saber quan l'enter està validat
        boolean dadesCorrectes;
        //S'inicia el resultat on es guarda l'enter. Com encara no ha estat llegit, inicialment és 0
        float result = 0;
        /*Primer s'imprimeix el missatge que demana el float. Després es comprova si el que es llegeix és un float amb el mètode
        hasNextFloat(). Si el que entra no és un float, es mostra un missatge d'error i com tot està dins d'un while regit pel
        boolean dadesCorrectes, es repeteix l'execució fins que el float estigui validat*/
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextFloat();
            if (dadesCorrectes) {
                result = scan.nextFloat();
            } else if (scan.hasNext()) {
                scan.next();
                System.out.println("Error, torna a provar");
            }
        } while (!dadesCorrectes);

        //Retornem
        return result;
    }
    
    /**
     * Llegeix un String sense demanar-lo amb missatge
     * @return Retorna el String llegit
     */
    public static String LLegirString() {
        String resultat = Utils.LLegirString(null);
        return resultat;
    }
    
    /**
     * Llegeix un String demanant-lo amb un missatge
     * @param missatge Missatge que demana el String
     * @return Retorna el String llegit
     */
    public static String LLegirString(String missatge) {
        String resultat = "";
        if (scan == null) {
            scan = new Scanner(System.in);
        }
        if (missatge != null) {
            System.out.print(missatge);
        }
        resultat = scan.nextLine();
        return resultat;
    }
    
    /**
     * LLegeix un double i el comprova abans de retornar-lo a
     *
     * @return Retorna un valor double
     */
    public static double LlegirDouble() {
        double result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirDouble(scan);

        return result;
    }

    /**
     * Serveix per a demanar un double amb un missatge que passarem com a
     * parametre
     *
     * @param missatge POdem demanar un numero amb aquest missatge com a
     * parametre
     * @return Retorna el double llegit per el teclat
     */
    public static double LlegirDouble(String missatge) {
        double result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirDouble(scan, missatge);

        return result;
    }

    /**
     * Serveix per a llegir un double amb el nostre scan
     *
     * @param scan Serveix per a posar un scanner que no sigui el nostre
     * @return Retorna el double llegit per el teclat
     */
    public static double LlegirDouble(Scanner scan) {
        return LlegirDouble(scan, null);
    }

    /**
     * Serveix per a llegir un double amb un missatge, i que estigui entre un
     * minim i un maxim
     *
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el
     * programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @param valorMax Diem el valor maxim del numero a introduir
     * @return retorna un double introduit per el teclat
     */
    public static double LlegirDouble(Scanner scan, String missatge, float valorMin, float valorMax) {
        double result = 0;
        do {
            result = LlegirDouble(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Serveix per a llegir un double amb un missatge, i que estigui per sobre
     * d'un minim
     *
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el
     * programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @return retorna un double introduit per el teclat
     */
    public static double LlegirDouble(Scanner scan, String missatge, float valorMin) {
        double result = 0;
        do {
            result = LlegirDouble(scan, missatge);
        } while (result < valorMin);

        return result;
    }

    /**
     * Serveix per a demanr un numero amb el nostre escaner i un missatge
     *
     * @param scan Podem dir el nostre escaner
     * @param missatge Podem dirt un missatge per a que imprimeixi abans de
     * demanr el numero
     * @return Retorna unn double introduit al teclat
     */
    public static double LlegirDouble(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        double result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextDouble();
            if (dadesCorrectes) {
                result = scan.nextDouble();
                scan.nextLine();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }
    
    public static String LlegirData(String format, String missatge){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        Date data = null;
        while (data==null){
            System.out.print(missatge);
            try {
                data = formatter.parse(scan.nextLine());
            } catch (ParseException e) {
                // Ara per ara, no farem res
                System.out.println("La data no és vàlida");
            }
        }
        return formatter.format(data);
    }
    
    public static String LlegirAdreçaPostal(String missatge){
        DecimalFormat formatter = new DecimalFormat("00000");
        //Una adreça postal consta de 5 dígits, així que la llegim mitjançant LlegirInt entre 0 i 99.999
        int adreça_postal = LlegirInt(scan,missatge,0,99999);
        return formatter.format(adreça_postal);
    }

    /**
     * Crea un Menú a partir del scan i un array amb les opcions del menú
     * 
     * @param scan Escaner
     * @param array_opcions Array amb les opcions del menú, les quals són
     * Strings
     * @return Retorna la opció escollida per l'usuari
     */
    public static int Menu(Scanner scan, String array_opcions[]) {
        //Imprimeix el títol MENÚ
        System.out.println("******** MENU ********");
        //Recorrem l'array d'opcions per anar imprimint les diferents opcions
        for (int i = 0; i <= array_opcions.length - 1; i++) {
            System.out.println(array_opcions[i]);
        }
        System.out.println(""); //Deixem una línia de separació
        //La registrem cridant LlegirInt()
        int opcio_escollida = LlegirInt(scan,"Escolleix una opcio (numero): ",1,array_opcions.length);
        //Retornem l'opció escollida
        return opcio_escollida;
    }

    /**
     * Calcula la mitja d'un vector
     *
     * @param v Vector de floats
     * @return Retorna la mitja del vector de floats
     */
    public static float mitja_vector(float[] v) {
        //Recorrem el vector per anar sumant els elements
        float suma_elements = 0;
        for (int i = 0; i <= v.length - 1; i++) {
            suma_elements = suma_elements + v[i];
        }
        //Calculem la mitja dividint la suma_elements entre la longitud del vector
        float mitja = suma_elements / v.length;
        //Retornem
        return mitja;
    }

    /**
     * Fa la suma d'un vector d'enters
     *
     * @param vector Vector d'enters
     * @return Retorna la suma dels elements del vector
     */
    public static int suma_elements_vector(int[] vector) {
        //Recorrem el vector per anar sumant els elements
        int suma_elements = 0;
        for (int i = 0; i <= vector.length - 1; i++) {
            suma_elements = suma_elements + vector[i];
        }
        //Retornem
        return suma_elements;
    }

    /**
     * Dona un punt resultant de sumar un punt més un vector
     *
     * @param punt Punt
     * @param vector Vector
     * @return Retorna el punt resultant
     */
    public static Punt suma_punt_mes_vector(Punt punt, Punt vector) {
        Punt punt_resultat;
        punt_resultat = new Punt();
        punt_resultat.x = punt.x + vector.x;
        punt_resultat.y = punt.y + vector.y;

        return punt_resultat;
    }

    /**
     * Dona un nou vector resultant de multiplicar un vector per un escalar
     *
     * @param vector Vector
     * @param factor_escala Escalar
     * @return Retorna el vector resultant
     */
    public static Punt escalar_per_vector(Punt vector, float factor_escala) {
        Punt punt_resultat;
        punt_resultat = new Punt();
        punt_resultat.x = factor_escala * vector.x;
        punt_resultat.y = factor_escala * vector.y;

        return punt_resultat;
    }

    /**
     * Comprova si dos punts són iguals
     *
     * @param punt1 Primer punt
     * @param punt2 Segon punt
     * @return Retorna un boolean que és true si els punts són iguals i false si
     * els punts són diferents
     */
    public static boolean comparacio_punts(Punt punt1, Punt punt2) {
        if (punt1.x == punt2.x && punt1.y == punt2.y) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida una contrasenya segons les següents condicions: -Mínim 8 caràcters
     * -Màxim 15 caràcters -Ha d'incloure almenys una majúscula, una minúscula,
     * un número i un símbol -No pot ser cap dels inclosos a la següent llista:
     * Admin_00, rooT12AaBbCc!, super_2000, #321Cba# -No pot tenir cap seqüència
     * de 3 o més caràcters consecutius del tipus 123, abc, ...
     *
     * @param pwd La contrasenya o password
     * @return Retorna un boolean que es true si la contrasenya és vàlida i
     * false si no és vàlida
     */
    public boolean IsValid(String pwd) {
        boolean result = true;

        /**
         * Aquí cal desenvolupar la funció
         */
        //CONSTANTS 
        final int LONGITUD_MINIMA_PWD = 8;
        final int LONGITUD_MAXIMA_PWD = 15;
        final String ARRAY_PWD_PROHIBITS[] = {"Admin_00", "rooT12AaBbCc!", "super_2000", "#321Cba#"};
        final int INDEX_2 = 2; //El número 2 dins dels índexos que utilitzem per comprovar les seqüències de tres o més caràcters consecutius

        //Comprovem que el password té entre 8 i 15 caràcters
        if (pwd.length() < LONGITUD_MINIMA_PWD || LONGITUD_MAXIMA_PWD < pwd.length()) {
            result = false;
        }

        /*Comprovem que el password contingui almenys una majúscula, una minúscula, un número i un símbol. Ho fem recorrent el
       password i utilitzant comptadors*/
        //Recorrem el String comprovant si és majúscula o minúscula i ho sumem a un comptador
        int comptador_minuscules = 0;
        int comptador_majuscules = 0;
        int comptador_numeros = 0;
        int comptador_simbols = 0;
        for (int i = 0; i <= pwd.length() - 1; i++) {
            //MAJÚSCULA I MINÚSCULA
            if ('a' <= pwd.charAt(i) && pwd.charAt(i) <= 'z') {
                comptador_minuscules++;
            } else if ('A' <= pwd.charAt(i) && pwd.charAt(i) <= 'Z') {
                comptador_majuscules++;
            } //NÚMERO
            else if (Character.isDigit(pwd.charAt(i))) {
                comptador_numeros++;
            } //SÍMBOL
            else {
                comptador_simbols++;
            }
            //SEQÜÈNCIA TRES CARÀCTERS CONSECUTIUS
            if (i >= INDEX_2) {
                //Passem tots els caràcters a integers
                int caracter_dos_indexos_enrere = pwd.charAt(i - 2);
                int caracter_un_index_enrere = pwd.charAt(i - 1);
                int caracter_actual = pwd.charAt(i);
                //Comprovem si són una seqüència de caràcters consecutius
                if (caracter_dos_indexos_enrere == caracter_actual - 2 && caracter_un_index_enrere == caracter_actual - 1) {
                    result = false;
                }
            }
        }
        //Si algun dels comptadors és igual a 0, el password no és vàlid
        if (comptador_minuscules == 0 || comptador_majuscules == 0) {
            result = false;
        }
        //Si comptador_numeros és igual a 0, el password no és vàlid
        if (comptador_numeros == 0) {
            result = false;
        }
        //Si comptador_simbols és igual a 0, el password no és vàlid
        if (comptador_simbols == 0) {
            result = false;
        }

        //Comprovem que el password no pertany a la llista de passwords prohibits recorrent el ARRAY_PWD_PROHIBITS
        for (int i = 0; i <= ARRAY_PWD_PROHIBITS.length - 1; i++) {
            if (ARRAY_PWD_PROHIBITS[i].equals(pwd)) {
                result = false;
            }
        }

        return result;
    }
    
    public static void BubbleSort (int[] array_numeros){
        for (int j=0;j<=array_numeros.length-1;j++){
            for (int k=0;k<=array_numeros.length-2-j;k++){
                if (array_numeros[k]>array_numeros[k+1]){
                    int valor_guardat = array_numeros[k];
                    array_numeros[k] = array_numeros[k+1];
                    array_numeros[k+1] = valor_guardat;
                }
            }
        }        
    }
    
    public static void QuickSort (int[] array_numeros){
        boolean array_estat_ordenacio [] = new boolean[array_numeros.length];
        int index_numero_que_ordenem = 0;
        int index_numero_per_comparar = array_numeros.length-1;
        while (array_estat_ordenacio[index_numero_que_ordenem]==false){
            //Si estan ordenats però que el que volem ordenar és més petit que el de comparar, canviem el de comparar baixant un índex
            if (index_numero_que_ordenem<index_numero_per_comparar && array_numeros[index_numero_que_ordenem]<=array_numeros[index_numero_per_comparar]){
                index_numero_per_comparar--;
            }
            //Si estan ordenats però que el que volem ordenar és més gran que el de comparar, canviem el de comparar pujant un índex
            else if (index_numero_que_ordenem>index_numero_per_comparar && array_numeros[index_numero_que_ordenem]>=array_numeros[index_numero_per_comparar]){
                index_numero_per_comparar++;
            }
            //Si no estan ordenats entre ells, els ordenem. Guardem els valors necessaris i intercanviem
            else{
                int valor_guardat_index = index_numero_que_ordenem;
                int valor_guardat_numero_que_ordenem = array_numeros[index_numero_que_ordenem];
                int valor_guardat_numero_per_comparar = array_numeros[index_numero_per_comparar];
                index_numero_que_ordenem = index_numero_per_comparar;
                array_numeros[index_numero_que_ordenem] = valor_guardat_numero_que_ordenem;
                index_numero_per_comparar = valor_guardat_index;
                array_numeros[index_numero_per_comparar] = valor_guardat_numero_per_comparar;
                //Un cop intercanviats tornem a fer el canvi del número per comparar com abans
                if (index_numero_que_ordenem<index_numero_per_comparar){
                    index_numero_per_comparar--;
                }
                else if (index_numero_que_ordenem>index_numero_per_comparar){
                    index_numero_per_comparar++;
                }
            }
            /*Si després de canviar el número a comparar ens trobem que aquest coincidex amb el que estem ordenant, vol dir que ja
            hem ordenat el número
            */
            if (index_numero_que_ordenem==index_numero_per_comparar){
                array_estat_ordenacio[index_numero_que_ordenem] = true;
                /*Ara toca canviar el número a ordenar i obviament el de comparació també.*/
                boolean canvi_fet_index_numero_que_ordenem = false;
                boolean canvi_fet_index_numero_per_comparar = false;
                for (int i = 0; i <= array_numeros.length - 1; i++) {
                    if (canvi_fet_index_numero_que_ordenem == false && array_estat_ordenacio[i] == false) {
                        index_numero_que_ordenem = i;
                        canvi_fet_index_numero_que_ordenem = true;
                    }
                    if (canvi_fet_index_numero_que_ordenem == true && array_estat_ordenacio[i] == true) {
                        index_numero_per_comparar = i - 1;
                        canvi_fet_index_numero_per_comparar = true;
                    }
                    if (canvi_fet_index_numero_per_comparar == false && canvi_fet_index_numero_que_ordenem == true && i == array_numeros.length - 1) {
                        index_numero_per_comparar = i;
                    }
                }
            }
        }
    }
    
    public static void SelectionSort (int[] array_numeros){
        for (int j=0;j<=array_numeros.length-1;j++){
            for (int k=j;k<=array_numeros.length-1;k++){
                if (array_numeros[j]>array_numeros[k]){
                    int valor_guardat = array_numeros[j];
                    array_numeros[j] = array_numeros[k];
                    array_numeros[k] = valor_guardat;
                }
            }
        }
    }
    
    public static int[] CountingSort (int[] array_numeros){
        Random rnd = new Random();
        final int RANG_VALORS_ALEATORIS = 201;
        int maxim = array_numeros[0];
        for (int i=1;i<=array_numeros.length-1;i++){
            array_numeros[i] = rnd.nextInt(RANG_VALORS_ALEATORIS);
            //Ens guardem el màxim de l'array perquè el necessitem per definir la longitud del segon array, el de les sumes
            if (array_numeros[i]>maxim){
                maxim = array_numeros[i];
            }
        }
        //Creem l'array de sumes de longitud igual al màxim
        int array_sumes [] = new int[maxim+1];
        /*Recorrem l'array de números per anar sumant 1 en la casella de array_sumes amb índex
        igual al número de la casella que ens trobem de l'array de números
        */
        for (int k=0;k<=array_numeros.length-1;k++){
            array_sumes[array_numeros[k]]++;
        }
        /*Recorrem l'array de sumes sumant en cada casella l'anterior
        */
        for (int l=0;l<=maxim;l++){
            if (l!=0){
                array_sumes[l] = array_sumes[l] + array_sumes[l-1];
            }
        }
        //Creem la llista on afegim els números ja ordenats
        int array_ordenat [] = new int[array_numeros.length];
        /*Recorrem l'array inicial dels números, ara des del final fins al principi, agafant
        un altre cop el valor de la casella com a índex del array_sumes. Després el valor de
        la casella de array_sumes li restem 1 i l'agafem com índex de l'array_ordenat, on afegim el valor
        de la casella que ens trobem del for
        */
        for (int m=array_numeros.length-1;m>=0;m--){
            if (array_sumes[array_numeros[m]]!=0){
                array_sumes[array_numeros[m]]--;
                array_ordenat[array_sumes[array_numeros[m]]] = array_numeros[m];
            }
            else{
                array_ordenat[array_sumes[array_numeros[m]]] = array_numeros[m];
            }
        }
        return array_ordenat;
    }

// </editor-fold>
}
