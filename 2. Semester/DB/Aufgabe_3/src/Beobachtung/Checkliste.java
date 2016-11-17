package Beobachtung;
//========= DATABASE =======
import Database.Verbindung;
import Dashboard.*;
import static Database.Verbindung.getRolleID;
import Login.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Toschka
 */
public class Checkliste extends javax.swing.JFrame {
    public Checkliste() {
        initComponents();
        
        if(getRolleID() == 3){
            jButton2.setVisible(false);
            jButton1.setVisible(false);
        }
        try {
            JOptionPane.showMessageDialog(null, "Gesehene Vögel werden nun aktualisiert..Bitte warten.");
            update_gesehen();
        } catch (SQLException ex) {
            Logger.getLogger(Checkliste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2_Load_Data = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Checkliste");

        jButton4.setText("Dashboard");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2_Load_Data.setText("Daten Laden");
        jButton2_Load_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_Load_DataActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Region", "Deutscher Name", "Gesehen?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton1.setText("Eintrag hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eintrag löschen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Sortieren");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 347, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(350, 350, 350)
                                .addComponent(jButton4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2_Load_Data)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2_Load_Data)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jCheckBox1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Selection selection = new Selection();
        selection.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2_Load_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_Load_DataActionPerformed
       JOptionPane.showMessageDialog(null, "Daten werden geladen, bitte warten Sie ein Moment.");
        try {
            Statement stmt = Verbindung.con.createStatement();
            int zeile = 0;
            ResultSet result = stmt.executeQuery("Select * from Checkliste");
            
            //===============================
            // Limit setzen
            //===============================
            setLimitJTable(600);
           
            while (result.next()) {
                jTable1.setValueAt(printRegion(result.getInt(1)),zeile,0);   
                jTable1.setValueAt(printDEUVogel(result.getInt("VID")),zeile,1); 
                jTable1.setValueAt(result.getString("Lifer_Tick"),zeile,2);
                zeile++;
            }
        }catch(SQLException ex) {Logger.getLogger(Checkliste.class.getName()).log(Level.SEVERE, null, ex);}
    }//GEN-LAST:event_jButton2_Load_DataActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CheckListEntry checklist = new CheckListEntry();
        checklist.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(null, "Löschen können Sie nur, wenn Sie die VID anklicken!");
   
        int row = jTable1.getSelectedRow(), neu = 0;
        DefaultTableModel model= (DefaultTableModel)jTable1.getModel();
        String selected_string =  model.getValueAt(row, 1).toString();
        
        //BESORGE DIE VID VON DEM GEGEBENEN STRING
        try {
            neu = giveVID(selected_string);
        } catch (SQLException ex) {
            Logger.getLogger(Checkliste.class.getName()).log(Level.SEVERE, null, ex);
        }
        // LÖSCHE
        if (row >= 0) {
            model.removeRow(row);
            String query = "delete from checkliste where VID="+neu+"";
            try (PreparedStatement ps = Verbindung.con.prepareStatement(query)){
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Checkliste.class.getName()).log(Level.SEVERE, null, ex);
            } 
            System.out.println(query);
            JOptionPane.showMessageDialog(null, "Sie haben die Daten nun erfolgreich gelöscht.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        DefaultTableModel deft =(DefaultTableModel)jTable1.getModel();
        TableRowSorter <TableModel> sorter = new TableRowSorter<TableModel>(); 
        jTable1.setRowSorter(sorter); 
        sorter.setModel(deft);
        sorter.setRowFilter(RowFilter.regexFilter("Gesehen")); 
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Checkliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Checkliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Checkliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checkliste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Checkliste().setVisible(true);
            }
        });
    }
    
private void setLimitJTable(int anz){
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(anz);
        jTable1.setModel(dtm);
}

public String printRegion(int regionid) throws SQLException {
        String RegionName = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Region FROM Beobachtungsort WHERE BeobachtungsortID = "+regionid+"";
            try (ResultSet rs0 = stmt.executeQuery(userid)) {
                while(rs0.next()) RegionName = rs0.getString("Region");
            }
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return RegionName;
}

public int giveRegionID(String region) throws SQLException {
    int regionid = 0;
    try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT BEOBACHTUNGSORTID FROM Beobachtungsort WHERE Region = '"+region+"'";
            try (ResultSet rs0 = stmt.executeQuery(userid)) {
                while(rs0.next()) regionid = rs0.getInt("BEOBACHTUNGSORTID");
            }
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return regionid;
    
}

public String printDEUVogel(int vid) throws SQLException {
        String VogelName = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Name_DEU from Vogelart WHERE VID = '"+vid+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) VogelName = rs0.getString("Name_DEU");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return VogelName;
}

public int giveVID(String vid) throws SQLException {
        int VogelName = 0;
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT VID from Vogelart WHERE NAME_DEU = '"+vid+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) VogelName = rs0.getInt("VID");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return VogelName;
}
    @SuppressWarnings("null")
    private void update_gesehen() throws SQLException {
    try{
        Statement stmt = Verbindung.con.createStatement();
        String update_query = "UPDATE CHECKLISTE SET LIFER_TICK = 'Gesehen' WHERE VID IN (SELECT VID FROM Beobachtung)";
        stmt.executeUpdate(update_query);
     } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton2_Load_Data;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

