package Beobachtung;

/*
 @import bibs
 @no unsused bibs
*/
import Dashboard.Selection;
import Database.Verbindung;
import static Database.Verbindung.getBenutzerID;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class insert extends javax.swing.JFrame {
    /*
    @start constructor
    @init gui + data
    */
    private boolean clicked;
    public insert() {
        initComponents();
        insertDate();
        try {
            lade_region();
            lade_land();
            lade_stadt();
           } catch (SQLException ex) {
               Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_kommentar = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jDatum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_zeitanfang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_zeitende = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2_land = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField1_search = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jLabel8.setText("jLabel8");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg.jpg"))); // NOI18N
        jLabel9.setText("jLabel6");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setIconTextGap(5);
        jLabel9.setInheritsPopupMenu(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hinzufügen");
        setName("Hinzufügen"); // NOI18N
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Beobachtung hinzufügen");

        jButton1.setText("Hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane_kommentar);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Vogelname");

        jButton2.setText("Dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Datum");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Zeitende");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg.jpg"))); // NOI18N
        jLabel10.setText("jLabel6");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setIconTextGap(5);
        jLabel10.setInheritsPopupMenu(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Region");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Land");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kommentar");

        jButton3.setText("Vogelnamen laden");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setText("Stadt");

        jComboBox2_land.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField1_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1_searchKeyPressed(evt);
            }
        });

        jButton4.setText("Suchen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Vogel suchen");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Lateinischer Name", "Deutscher Name", "Englischer Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jCheckBox1.setText("Unterart");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jList2);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Unterart");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Zeitanfang");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1_search)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDatum)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox2_land, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField_zeitanfang))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_zeitende, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3))
                                    .addComponent(jScrollPane3)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jButton2))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox2_land, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_zeitanfang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_zeitende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jCheckBox1)))
        );

        setSize(new java.awt.Dimension(796, 503));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("null")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int bID2 = 0, vID = 0;
            String datum = jDatum.getText();
            String region = (String)jComboBox1.getSelectedItem();
            String land = (String)jComboBox2_land.getSelectedItem();
            String stadt = (String)jComboBox3.getSelectedItem();
            String zeitanfang = jTextField_zeitanfang.getText();
            String zeitende = jTextField_zeitende.getText();
            String kommentar = jTextPane_kommentar.getText();
            String getUnterart = (String) jList2.getSelectedValue();
            int selectedRow = jTable1.getSelectedRow();
            selectedRow = jTable1.convertRowIndexToModel(selectedRow);
            String vogel = (String)jTable1.getModel().getValueAt(selectedRow, 0);
           
            if( vogel == null || region == null){
                JOptionPane.showMessageDialog(null, "Sie haben kein Vogel oder eine Region ausgewält!", "Beobachtung", WIDTH);
                return;
            }
            //=====================================================
            // VID
            //=====================================================
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT VID FROM Vogelart WHERE NAME_LAT = '"+vogel+"'");
            while (rst.next()) vID = rst.getInt("VID");
            
            //=====================================================
            // Region
            //=====================================================
            @SuppressWarnings("UnusedAssignment")
            Statement stm2 = Verbindung.con.createStatement();
            ResultSet rs = null;
            
            //===========================================
            // Verbieten, nur Stadt auszuwähln!
            //===========================================
            if(stadt != null && land == null){
                JOptionPane.showMessageDialog(null, "Wählen Sie ein Land, bevor Sie eine Stadt auswählen");
                return;
            }
            
            if(land == null && stadt == null)
                rs = stm2.executeQuery("SELECT Beobachtungsortid from Beobachtungsort WHERE Region = '"+region+"' AND LAND IS NULL AND STADT IS NULL");
            else 
                rs = stm2.executeQuery("SELECT Beobachtungsortid from Beobachtungsort WHERE Region = '"+region+"' AND LAND = '"+land+"' AND STADT = '"+stadt+"'");   
            
            if(land != null && stadt == null) rs = stm2.executeQuery("SELECT Beobachtungsortid from Beobachtungsort WHERE Region = '"+region+"' AND LAND = '"+land+"'");   
            while (rs.next()) bID2 = rs.getInt("BeobachtungsortID");
            
            //===========================================================
            // Gucken, ob der Vogel in der Checkliste vorhanden ist
            //===========================================================
            Statement stmt10 = Verbindung.con.createStatement();
            ResultSet result10 = stmt10.executeQuery("select MERLIN.BIRDS_DE.DE_LATEIN from MERLIN.BIRDS_DE WHERE de_latein = '" + vogel + "'");
            if (!result10.next()){
                JOptionPane.showMessageDialog(null, "Dieser Vogel ist nicht in der Checkliste vorhanden!");
                return;
            }
            //===========================================================
            // Je nach Eingabe von Land,Stadt einfügen
            //===========================================================
            
            //===========================================================
            // Einfügen in Beobachtung
            //===========================================================
            try (PreparedStatement ps = Verbindung.con.prepareStatement("INSERT INTO beobachtung(beobachtungsortid,vid,benutzerid,zeitanfang,zeitende,kommentar,datum,unterart) VALUES(?,?,?,?,?,?,?,?)")) {
                  ps.setInt(1, bID2);
                  ps.setInt(2, vID);
                  ps.setInt(3, getBenutzerID());
                  ps.setString(4, zeitanfang);
                  ps.setString(5, zeitende);
                  ps.setString(6, kommentar);
                  ps.setString(7, datum);
                  ps.setString(8, getUnterart);
                  ps.execute();
                  ps.close();
            }
            JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich hinzugefügt");

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Die Daten konnten nicht hinzugefügt werden, siehe Output-Stream.");
           Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    Selection dashboard = new Selection();
    dashboard.setVisible(true);
    this.setVisible(false);
    
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            JOptionPane.showMessageDialog(null, "Bitte warten Sie.\nDie Daten wird nun geladen.");
            setLimitJTable(1000);
            lade_daten();
            } catch (SQLException ex) {Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);}
        this.clicked = true;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(!clicked) JOptionPane.showMessageDialog(null, "Sie müssen zuerst alle Vogelnamen laden!");
        else         newSearch(jTextField1_search.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) jButton4ActionPerformed(null);
    }//GEN-LAST:event_jTextField1_searchKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        selectedRow = jTable1.convertRowIndexToModel(selectedRow);
        String vogel = (String)jTable1.getModel().getValueAt(selectedRow, 0);
            
        if (jCheckBox1.isSelected()) {
            try {
                JOptionPane.showMessageDialog(null, "Bitte wählen Sie nun die passende Unterart aus.");
                Statement stmt = Verbindung.con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT b_scientific_name FROM Merlin.birds "
                        + "WHERE b_scientific_name LIKE '" + vogel + "%'"
                        + "ORDER BY b_scientific_name ASC");
                
                DefaultListModel<String> liste = new DefaultListModel<String>();

                while (rs.next()) liste.addElement(rs.getString("b_scientific_name")); 
               jList2.setModel(liste);
               
            } catch (SQLException ex) {
                Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sie sind wieder zurück bei den Oberarten.");
            try {
                lade_daten();
            } catch (SQLException ex) { Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void newSearch(String text){
            for (int row = 0; row <= jTable1.getRowCount() - 1; row++) {
                for (int col = 0; col <= jTable1.getColumnCount() - 1; col++) {
                    if (text.equals(jTable1.getValueAt(row, col))) {
                        jTable1.scrollRectToVisible(jTable1.getCellRect(row, 0, true));
                        jTable1.setRowSelectionInterval(row, row);

                        for (int i = 0; i <= jTable1.getColumnCount() - 1; i++) 
                            jTable1.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
                    }
                }
            }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new insert().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2_land;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JTextField jDatum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1_search;
    private javax.swing.JTextField jTextField_zeitanfang;
    private javax.swing.JTextField jTextField_zeitende;
    private javax.swing.JTextPane jTextPane_kommentar;
    // End of variables declaration//GEN-END:variables

private void lade_daten() throws SQLException {
    Statement stmt = Verbindung.con.createStatement();
    ResultSet rs = stmt.executeQuery("select b.B_SCIENTIFIC_NAME, b.B_ENGLISH_NAME, bd.DE_DEUTSCH from MERLIN.birds b, MERLIN.birds_de bd " +
                                     "WHERE b.B_SCIENTIFIC_NAME = bd.DE_LATEIN AND B_CATEGORY = 'species'");
    int row = 0;
    while (rs.next()) {
        jTable1.setValueAt(rs.getString("B_SCIENTIFIC_NAME"),row,0); 
        jTable1.setValueAt(rs.getString("DE_DEUTSCH"),row,1);
        jTable1.setValueAt(rs.getString("B_ENGLISH_NAME"),row,2);
        row++;
    }
}

private void setLimitJTable(int anz){
    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
    dtm.setRowCount(anz);
    jTable1.setModel(dtm);
}

private void lade_region() throws SQLException {
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("select DISTINCT Region FROM Beobachtungsort WHERE Region = 'Westpaläarktis	(WP)'");
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel();

            while (rs.next()) comboBoxModel.addElement(rs.getString("Region"));
            jComboBox1.setModel(comboBoxModel);
        } catch (SQLException ex) {Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);}   
    }

private void lade_land() throws SQLException {
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("select DISTINCT Land FROM Beobachtungsort WHERE Region = 'Westpaläarktis	(WP)'");
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel();

            while (rs.next()) comboBoxModel.addElement(rs.getString("Land"));
            jComboBox2_land.setModel(comboBoxModel);
        } catch (SQLException ex) {Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);}   
    }

private void lade_stadt() throws SQLException {
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("select DISTINCT stadt FROM Beobachtungsort WHERE Region = 'Westpaläarktis	(WP)'");
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel();

            while (rs.next()) comboBoxModel.addElement(rs.getString("Stadt"));
            jComboBox3.setModel(comboBoxModel);
        } catch (SQLException ex) {Logger.getLogger(insert.class.getName()).log(Level.SEVERE, null, ex);}   
    }

private void insertDate() {
     DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
     Date date = new Date();
     jDatum.setText(dateFormat.format(date));
}

// CLASS FOR RENDERER
private class HighlightRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row == table.getSelectedRow()) setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
        return this;
    }
}

}
