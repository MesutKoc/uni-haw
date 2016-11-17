package Database;

import Login.Register;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Verbindung {
    //========== Attribute for the db ===============
    public static Connection con;
    public static Statement statment;
    public static ResultSet r;
    //===============================================
    public final static String dbURL = "jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:inf14"; 
    //========= for the user and password=============
    private static String benutzer,
                          passwort,
                          benutzer2;
    
    private static int benutzerid, rolleid;
    //===============================================
    private boolean result = false;

    public Verbindung(String benutzer, String password) {
        Verbindung.benutzer = benutzer;
        Verbindung.passwort = password;
        
        Properties info = new Properties( );
        info.put( "user", benutzer );
        info.put( "password", password );

        try {
            //=============== Choose the jdbc driver for oracle ===================
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //=====================================================================
            
            //============= CONNECTION AUFBAUEN ===================================
            con = DriverManager.getConnection(dbURL,benutzer,password);
            //=====================================================================
            
            //============= CREATE STATMENT with result ===================================
            statment = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //r = statment.executeQuery("select * ");
            //=====================================================================
            this.result = true;
          } catch (ClassNotFoundException | SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
        
        
    }
 
    public static String getUser() { 
        return benutzer; 
    }
    public static String getPasswort() { 
        return passwort; 
    }
    
    public boolean getResult() {
        return result;
    }
    
    public static void setBenutzer2(String benutzername){
        Verbindung.benutzer2 = benutzername;
    }
    
    public static String benutzer2(){
        return benutzer2;
    }
    
    public static int getRolleID(){
        return rolleid;
    }
    
    public static int getBenutzerID(){
        return benutzerid;
    }
    
     public static void setBenutzerID(int benutzerid){
        Verbindung.benutzerid = benutzerid;
    }
     
     public static void setRolleID(int rolleid){
        Verbindung.rolleid = rolleid;
    }
}
