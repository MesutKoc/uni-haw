package Login;
//========= DATABASE =======
import AdminP.ScriptRunner;
import AdminP.admin_view;
import Database.Verbindung;
import Dashboard.*;
import static Database.Verbindung.dbURL;
import static Database.Verbindung.getPasswort;
import static Database.Verbindung.getUser;
import static Database.Verbindung.setRolleID;
//==========================
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() { 
        initComponents(); 
        if(setUpCheck()) setUp();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Anmeldung");
        setBackground(new java.awt.Color(102, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Anmelden"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(490, 10, 90, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Login im System");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 190, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Benutzer:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(430, 10, 59, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Password:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(590, 10, 63, 17);

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(660, 10, 80, 20);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(106, 260, 0, 0);

        jButton1.setText("Anmelden");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(650, 50, 100, 23);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(-80, 80, 840, 690);

        jButton3.setText("Registieren");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(550, 50, 100, 23);

        setSize(new java.awt.Dimension(766, 409));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

public void selection(java.awt.event.ActionEvent evt){
    Selection auswahl = new Selection();
    auswahl.setVisible(true);
    this.setVisible(false);
}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String passwort = String.valueOf(jPasswordField1.getPassword());
    String benutzername = jTextField1.getText();
    String toLowerCase = benutzername.toLowerCase();
    //==========================================================================
    Verbindung verbindung = new Verbindung(getUser(), getPasswort());
    if (!verbindung.getResult()) JOptionPane.showMessageDialog(null, "Falsche Zugangsdaten", "Verbindung nicht erfolgreich.", WIDTH); 
    //===========================================================================
    
    //===========================================================================
    Verbindung.setBenutzer2(toLowerCase);
    getBenutzerID();
    getRolleID();
    setRolleID(getRolleID());
    //===========================================================================
   
    //========================================================================
    try { 
            String username_fromdb = "", 
                   password_fromdb = "";
            
            Connection con = DriverManager.getConnection(dbURL,getUser(),getPasswort());
            Statement stat = (Statement) con.createStatement();
            String sql = "SELECT * FROM benutzer WHERE vorname = '"+toLowerCase+"' ";
            ResultSet rs = stat.executeQuery(sql);

            while(rs.next()){
                username_fromdb = rs.getString("VORNAME");
                password_fromdb = rs.getString("PASSWORD_");
            }
            
            if(toLowerCase.equals(username_fromdb) && password_fromdb.equals(passwort)) selection(evt);
            else if(toLowerCase.equals(username_fromdb) && BCrypt.checkpw(passwort, password_fromdb))
                    selection(evt);
            else JOptionPane.showMessageDialog(null, "Username oder Passwort ist falsch", "Anmeldung nicht erfolgreich.", WIDTH);
               
    
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
}//GEN-LAST:event_jButton1ActionPerformed
    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) jButton1ActionPerformed(null);
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Register test = new Register();
        test.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public int getBenutzerID(){
        int benutzerid = 0;
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT BENUTZERID from Benutzer WHERE vorname = '"+Verbindung.benutzer2()+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) benutzerid = rs0.getInt("BenutzerID");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
       Verbindung.setBenutzerID(benutzerid);
       return benutzerid;
    }
    
public int getRolleID(){
        int rolleid = 0;
        try { 
             Statement stmt = Verbindung.con.createStatement();
             String sql = "SELECT * FROM hat_rolle WHERE Benutzerid = '"+Verbindung.getBenutzerID()+"' ";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()) rolleid = rs.getInt("RID");
        } catch (SQLException e) {}
        Verbindung.setRolleID(rolleid);
        return rolleid;
}

private boolean setUpCheck(){
       try { 
             Statement stmt = Verbindung.con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Benutzer, Rolle, hat_rolle");
             if(rs.next()) return false;
        } catch (SQLException e) {}
        return true;
}

public static void setUp(){
    JOptionPane.showMessageDialog(null, "Die Tabellen: Benutzer und Rolle werden erstelt.");
        try {
            Connection con = DriverManager.getConnection(dbURL,getUser(),getPasswort());
            ScriptRunner runner = new ScriptRunner(con, false, true);
            String filePath = new File("").getAbsolutePath();
            runner.runScript(new BufferedReader(new FileReader(filePath + "/src/SQL/SETUP.sql")));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        } 
    JOptionPane.showMessageDialog(null, "Tabellen erstellt. Login Sie sich mit den Daten (user: admin, pw: admin) ein!");
}
    
public static void main(String args[]) {
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
           }
       }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
