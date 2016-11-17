package Database;
//========= IMPORT =======
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Login.*;
//==========================

public class db_login extends javax.swing.JFrame {
    public db_login() { initComponents(); }
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
        jLabel1.setText("DATENBANK-LOGIN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 210, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("DB-Name:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(419, 10, 70, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Passwort:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(590, 10, 60, 17);

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

        setSize(new java.awt.Dimension(766, 409));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

public void selection(java.awt.event.ActionEvent evt){
    Login auswahl = new Login();
    auswahl.setVisible(true);
    this.setVisible(false);
}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String passwort = String.valueOf(jPasswordField1.getPassword());
    String benutzername = jTextField1.getText();
    //=============== BITTE GEBEN SIE HIER IHRE DB USER DATEN AN ================
    Verbindung verbindung = new Verbindung(benutzername, passwort);
    if (!verbindung.getResult()){
        JOptionPane.showMessageDialog(null, "Falsche Zugangsdaten", "Verbindung nicht erfolgreich.", WIDTH);
    } else {
         JOptionPane.showMessageDialog(null, "Verbindung wurde Erfolgreich erstellt.", "DB-Connection: Succes", WIDTH);
        selection(evt);
    }
    //===========================================================================
   
}//GEN-LAST:event_jButton1ActionPerformed
    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) jButton1ActionPerformed(null);
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
   
public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(db_login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(() -> {
            new db_login().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
