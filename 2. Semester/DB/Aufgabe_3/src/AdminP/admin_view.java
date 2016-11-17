package AdminP;

import Beobachtung.*;
import Dashboard.Selection;
import Login.*;
import Database.*;
import static Database.Verbindung.dbURL;
import static Database.Verbindung.getPasswort;
import static Database.Verbindung.getRolleID;
import static Database.Verbindung.getUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class admin_view extends javax.swing.JFrame {
    public admin_view() {
        initComponents();
        deleteRights(2);
        
        try { 
            lade_region();
            lade_land();
            lade_benutzer();
        }catch (SQLException ex) {}
        
    }
   
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldArt = new javax.swing.JTextField();
        jTextFieldUnterArt = new javax.swing.JTextField();
        jDeutsch = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();

        jLabel3.setText("jLabel3");

        jLabel6.setText("jLabel6");

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton2.setText("Dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Administration");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg.jpg"))); // NOI18N
        jLabel10.setText("jLabel6");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setIconTextGap(5);
        jLabel10.setInheritsPopupMenu(false);

        jLabel2.setText("Region:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Land:");

        jButton1.setText("Eintrag hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Datenbankeinstellungen");

        jButton3.setText("Stammdaten laden");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Datenbank-Schema löschen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Datenbank-Schema erstellen");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Stadt:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Vogelart hinzufügen");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Beobachtungsort hinzufügen");

        jLabel1.setText("Art:");

        jLabel7.setText("Unterart");

        jLabel13.setText("Deutscher Name:");

        jButton6.setText("Hinzufügen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Benutzer", "Rechte"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Rechtevergabe ändern");

        jButton7.setText("Änderungen speichern");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldArt)
                                    .addComponent(jTextFieldUnterArt)
                                    .addComponent(jDeutsch)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUnterArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDeutsch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(740, 453));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        Selection dashboard = new Selection();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String region = (String)jComboBox1.getSelectedItem();
        String land = (String)jComboBox2.getSelectedItem();
        String stadt = jTextField3.getText();
        
        if (land.isEmpty()){
            JOptionPane.showMessageDialog(null, "Bitte geben Sie mindestens das Land an", "Eintrag nicht erfolgreich", WIDTH);
            return;
        }
        
        String query_vogelart = "INSERT INTO beobachtungsort(region,land,stadt) VALUES(?,?,?)";
        try (PreparedStatement ps = Verbindung.con.prepareStatement(query_vogelart)) {
                ps.setString(1, region);
                ps.setString(2, land);
                ps.setString(3, stadt);
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Daten wurden erfolgreich hinzugefügt!");
    }//GEN-LAST:event_jButton1ActionPerformed
    /*
       @brief: Stammdaten laden
       @param: none
       @return: 0 | 1
    */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "Stammdaten werden geladen.");
        try {
            Connection con = DriverManager.getConnection(dbURL,getUser(),getPasswort());
            ScriptRunner runner = new ScriptRunner(con, false, true);
            String filePath = new File("").getAbsolutePath();
            
            runner.runScript(new BufferedReader(new FileReader(filePath + "/src/SQL/Merlin_Tabelle_Insert.sql")));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        } 
        JOptionPane.showMessageDialog(null, "Stammdaten wurden geladen.");
    }//GEN-LAST:event_jButton3ActionPerformed
    /*
       @brief: Datenbank-Tabellen löschen:
       @param: none
       @return: 0 | 1
    */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Connection con = DriverManager.getConnection(dbURL,getUser(),getPasswort());
            ScriptRunner runner = new ScriptRunner(con, false, true);
            String filePath = new File("").getAbsolutePath();

            runner.runScript(new BufferedReader(new FileReader(filePath + "/src/SQL/M_Tabellen_Drop.sql")));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        } 
        JOptionPane.showMessageDialog(null, "Datenbank-Schema wurde gelöscht.");
    }//GEN-LAST:event_jButton4ActionPerformed
   /*
       @brief: Datenbank-Tabellen erstellen:
       @param: none
       @return: 0 | 1
    */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Connection con = DriverManager.getConnection(dbURL,getUser(),getPasswort());
            ScriptRunner runner = new ScriptRunner(con, false, true);
            String filePath = new File("").getAbsolutePath();

            runner.runScript(new BufferedReader(new FileReader(filePath + "/src/SQL/M_Tabellen_Create.sql")));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        } 
        JOptionPane.showMessageDialog(null, "Datenbank-Schema wurde erstellt.");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String art = jTextFieldArt.getText();
        String unterart = jTextFieldUnterArt.getText();
        String deutsch = jDeutsch.getText();
        
        if (deutsch.isEmpty()){
            JOptionPane.showMessageDialog(null, "Bitte geben Sie mindestens den deutschen Namen ein!", "Eintrag nicht erfolgreich", WIDTH);
            return;
        }
        
        try {
            Statement stm2 = Verbindung.con.createStatement();
            ResultSet res = stm2.executeQuery("SELECT * FROM Vogelart WHERE NAME_DEU = '"+deutsch+"'");
            if(res.next()){
                JOptionPane.showMessageDialog(null, "Dieser Vogel existiert schon!");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query_vogelart = "INSERT INTO vogelart(name_deu,art,unterart) VALUES(?,?,?)";
        try (PreparedStatement ps = Verbindung.con.prepareStatement(query_vogelart)) {
                ps.setString(1, deutsch);
                ps.setString(2, art);
                ps.setString(3, unterart);
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
            Logger.getLogger(admin_view.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Daten wurden erfolgreich hinzugefügt!");                    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            @SuppressWarnings("UnusedAssignment")
            int row = 0;
            
            for (int spalte = 0; spalte < jTable3.getRowCount(); spalte++) {
                row = 0;
                if (jTable3.getValueAt(spalte, row) == null) break;
                
                String benutzername = (String) jTable3.getValueAt(spalte, row);
                row++;
                
                int RID = (Integer) jTable3.getValueAt(spalte,row);
                row++;
                
                String query21 = "UPDATE hat_rolle SET "
                        + "RID = " + RID + ""
                        + " WHERE BenutzerID = "+getBenutzerIDFromName(benutzername)+"";

                Verbindung verbindung = new Verbindung(Verbindung.getUser(), Verbindung.getPasswort());
                
                @SuppressWarnings("static-access")
                Statement stmt = verbindung.statment;
                stmt.executeUpdate(query21);
            }
            JOptionPane.showMessageDialog(null, "Sie haben die Daten nun erfolgreich aktualisiert.");

        } catch (SQLException ex) {
            Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

/*
  GENERIERT DEN BENUTZERNMANE ANHAND EINES BENUTZERIDS
*/
public String printBenutzer(int benutzerid) throws SQLException {
        String benutzername = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Vorname from Benutzer WHERE benutzerid = '"+benutzerid+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) benutzername = rs0.getString("Vorname");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return benutzername;
}

public int getBenutzerIDFromName(String benutzerid) throws SQLException {
        int benutzerID = 0;
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT BenutzerID from Benutzer WHERE vorname = '"+benutzerid+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) benutzerID = rs0.getInt("BenutzerID");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return benutzerID;
}


public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    //</editor-fold>
        //</editor-fold>
    //</editor-fold>
        //</editor-fold>
    //</editor-fold>
        //</editor-fold>
    //</editor-fold>

    java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
         new admin_view().setVisible(true);
        }
   });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JTextField jDeutsch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextFieldArt;
    private javax.swing.JTextField jTextFieldUnterArt;
    // End of variables declaration//GEN-END:variables

private void lade_region() throws SQLException {
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("select DISTINCT Region FROM Beobachtungsort WHERE Region = 'Westpaläarktis	(WP)'");
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel();

            while (rs.next()) {
                String s = rs.getString("Region");
                comboBoxModel.addElement(s);
            }
       jComboBox1.setModel(comboBoxModel);
        } catch (SQLException ex) {}   
    }

private void lade_land() throws SQLException {
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("select DISTINCT Land FROM Beobachtungsort WHERE Region = 'Westpaläarktis	(WP)'");
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel();

            while (rs.next()) {
                String s = rs.getString("Land");
                comboBoxModel.addElement(s);
            }
       jComboBox2.setModel(comboBoxModel);
        } catch (SQLException ex) {}   
    }



private void lade_benutzer() throws SQLException {
    try {
            Statement stmt = Verbindung.con.createStatement();
            int row = 0;
            
            @SuppressWarnings("UnusedAssignment")
            ResultSet result = stmt.executeQuery("SELECT * FROM hat_rolle");
          
            /*
              @brief: Befülle Inhalt
            */
            while (result.next()) {
                jTable3.setValueAt(printBenutzer(result.getInt("BenutzerID")),row,0);      
                jTable3.setValueAt(result.getInt("RID"),row,1);                
                row++;
            }
        } catch (SQLException ex) { Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex); }
   }

    private void deleteRights(int rights) {
        if(getRolleID() == rights){
            jLabel14.setVisible(false);
            jScrollPane1.setVisible(false);
            jButton7.setVisible(false);
        }
    }
}
