package Beobachtung;

import Dashboard.Selection;
import Login.*;
import Database.*;
import static Database.Verbindung.getBenutzerID;
import static Database.Verbindung.getRolleID;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Ansehen extends javax.swing.JFrame {
    private boolean clicked;
    
    public Ansehen() {
        initComponents();
          try { 
            lade_db_daten();
          }catch (SQLException ex) { Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex); 
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        txt_update = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();

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

        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Datum-Filter:");

        jButton2.setText("Dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Beobachtungen");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BID", "Region", "Land", "Stadt", "Benutzer", "Vogel", "Datum", "Zeitanfang", "Zeitende", "Kommentar", "Unterart"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setColumnSelectionAllowed(true);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton3.setText("Filtern");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Daten laden");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg.jpg"))); // NOI18N
        jLabel10.setText("jLabel6");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setIconTextGap(5);
        jLabel10.setInheritsPopupMenu(false);

        jButton5.setText("Lifer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(jList2);

        jButton6.setText("Tick");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setText("Kommentar");

        jLabel2.setText("Datum");

        txt_update.setText("Update");
        txt_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_updateActionPerformed(evt);
            }
        });

        jButton4.setText("Löschen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_update)
                    .addComponent(jButton4))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jLabel4.setText("Zeitanfang");

        jLabel9.setText("BID");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("DATEN BEARBEITEN");

        jLabel7.setText("Zeitende");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton7.setText("N-Gesehen");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3))
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addComponent(jTextField5))
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1114, 542));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        Selection dashboard = new Selection();
        dashboard.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    @SuppressWarnings("null")
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(!clicked) {
            JOptionPane.showMessageDialog(null, "Sie müssen vorerst die Daten laden!");
            return;
        }
        //===============================================================
        // SORTIERE
        //===============================================================
        String datum1 = (String) jList1.getSelectedValue();
        DefaultTableModel deft =(DefaultTableModel)jTable3.getModel();
        TableRowSorter <TableModel> sorter = new TableRowSorter<TableModel>(); 
        jTable3.setRowSorter(sorter); 
        sorter.setModel(deft);
        sorter.setRowFilter(RowFilter.regexFilter(datum1)); 
    }//GEN-LAST:event_jButton3ActionPerformed

private void setLimitJTable(int anz){
    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
    dtm.setRowCount(anz);
    jTable3.setModel(dtm);
}
    
    @SuppressWarnings("null")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Statement stmt = Verbindung.con.createStatement();
            String datum = (String) jList1.getSelectedValue();
            int row = 0;
            
            @SuppressWarnings("UnusedAssignment")
            ResultSet result = null;
                 
            if(getRolleID() == 3){
                Statement stm2 = Verbindung.con.createStatement();
                result = stm2.executeQuery("SELECT * FROM Beobachtung WHERE BENUTZERID = "+getBenutzerID()+"");
            }else{
                result = stmt.executeQuery("SELECT * FROM Beobachtung");
            }
       
            while (result.next()) {
                jTable3.setValueAt(result.getInt("Beobachtungsid"),row,0); 
                jTable3.setValueAt(printRegion(result.getInt("Beobachtungsortid")),row,1); 
                jTable3.setValueAt(printLand(result.getInt("Beobachtungsortid")),row,2);
                jTable3.setValueAt(printStadt(result.getInt("Beobachtungsortid")),row,3);
                jTable3.setValueAt(printBenutzer(result.getInt("BenutzerID")),row,4);                
                jTable3.setValueAt(printVogelname(result.getInt("VID")),row,5);
                jTable3.setValueAt(result.getString("Datum"),row,6);
                jTable3.setValueAt(result.getString("Zeitanfang"),row,7);
                jTable3.setValueAt(result.getString("Zeitende"),row,8);
                jTable3.setValueAt(result.getString("Kommentar"),row,9);
                jTable3.setValueAt(result.getString("Unterart"),row,10);
                row++;
            }
            result.close();
        } catch (SQLException ex) { Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex); }
        this.clicked = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rs = stmt.executeQuery("Select vid, count(VID), min(Datum) from beobachtung,beobachtungsort WHERE BEOBACHTUNG.BEOBACHTUNGSORTID = beobachtungsort.beobachtungsortID " +
                                            "AND BENUTZERID = "+getBenutzerID()+" " +
                                            "group by VID");
            
            DefaultListModel<String> liste = new DefaultListModel<String>();
            while (rs.next()) liste.addElement(printVogelname(rs.getInt("VID")) + " gesehen am: "+rs.getString("min(Datum)")+"");
            jList2.setModel(liste);

        } catch (SQLException ex) {
            Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    try{
        Statement stmt = Verbindung.con.createStatement();
        ResultSet tick = stmt.executeQuery("SELECT b.BeobachtungsortID,b.VID,b.Datum, b.Zeitanfang, b.Zeitende, bo.LAND " +
                                         "FROM BEOBACHTUNG b, Vogelart v, Beobachtungsort bo " +
                                         "WHERE BENUTZERID = "+getBenutzerID()+" AND " +
                                         "b.VID = v.VID AND " +
                                         "bo.BeobachtungsortID = b.BeobachtungsortID");
        
       DefaultListModel<String> liste = new DefaultListModel<String>();
       
       while(tick.next()){
            String tick_string = printVogelname(tick.getInt("Vid"));
            liste.addElement(tick_string);
        }
        jList2.setModel(liste);
    }catch(SQLException ex) {
            Logger.getLogger(Ansehen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_updateActionPerformed
        try{
            String getDat = jTextField2.getText();
            String getZeitA = jTextField3.getText();
            String getZeitE = jTextField4.getText();
            String kommenT = jTextField5.getText();
            String getID   = jTextField6.getText();
            Statement stmt = Verbindung.con.createStatement();
            String query = "UPDATE beobachtung set datum = '"+getDat+"', zeitanfang = '"+getZeitA+"', zeitende = '"+getZeitE+"', kommentar = '"+kommenT+"' where BeobachtungsID = "+getID+"";
            stmt.executeUpdate(query);
        }catch (Exception e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
        JOptionPane.showMessageDialog(null, "Aktualisiert");
        
    }//GEN-LAST:event_txt_updateActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        try{
            int row = jTable3.getSelectedRow();
            String table_click = jTable3.getModel().getValueAt(row,0).toString();
            
            String query = "SELECT * FROM BEOBACHTUNG WHERE BEOBACHTUNGSID = "+table_click+"";
            Statement stmt = Verbindung.con.createStatement();
            ResultSet rst = stmt.executeQuery(query);
            
            if(rst.next()){
                String biD = rst.getString("BEOBACHTUNGSID");
                jTextField6.setText(biD);
                String datumt = rst.getString("Datum");
                jTextField2.setText(datumt);
                String zeitanfang = rst.getString("Zeitanfang");
                jTextField3.setText(zeitanfang);
                String zeitende = rst.getString("Zeitende");
                jTextField4.setText(zeitende);
                String kommentar = rst.getString("Kommentar");
                jTextField5.setText(kommentar);
            }
        }catch (Exception e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            String getID   = jTextField6.getText();
            Statement stmt = Verbindung.con.createStatement();
            String query = "DELETE beobachtung where beobachtungsid = "+getID+"";
            stmt.executeUpdate(query);
        }catch (Exception e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
        JOptionPane.showMessageDialog(null, "Gelöscht.");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try{
            DefaultListModel<String> liste = new DefaultListModel<String>();
            Statement stmt = Verbindung.con.createStatement();
            String query = "select NAME_DEU from vogelart v " +
                       "WHERE NOT EXISTS (select * from beobachtung b where v.vid = b.vid " +
                       "AND b.benutzerid = "+getBenutzerID()+") AND EXISTS (select * from checkliste ch where v.vid = ch.vid)";
            
            ResultSet rst = stmt.executeQuery(query);
         
            while(rst.next()){
                String name = rst.getString("NAME_DEU");
                liste.addElement(name);
            }
            rst.close();
        jList2.setModel(liste);
        }catch (Exception e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    }//GEN-LAST:event_jButton7ActionPerformed

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

/*
  GENERIERT DEN VOGELNAMEN ANHAND EINES VOGELIDS
*/
public String printVogelname(int vogelid) throws SQLException {
        String benutzername = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Name_DEU from Vogelart WHERE VID = '"+vogelid+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) benutzername = rs0.getString("Name_DEU");
            rs0.close();
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
        
    return benutzername;
}
public int giveVOID(String beobachtungsortname) throws SQLException {
        int ID = 0;
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT VID from Vogelart WHERE NAME_DEU = '"+beobachtungsortname+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) ID = rs0.getInt("VID");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return ID;
}

public int giveBOID(String beobachtungsortname) throws SQLException {
        int ID = 0;
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Beobachtungsortid from Beobachtungsort WHERE Region = '"+beobachtungsortname+"'";
            ResultSet rs0 = stmt.executeQuery(userid);
            while(rs0.next()) ID = rs0.getInt("Beobachtungsortid");
             
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return ID;
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
            java.util.logging.Logger.getLogger(Ansehen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ansehen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ansehen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ansehen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    //</editor-fold>

    java.awt.EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
         new Ansehen().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton txt_update;
    // End of variables declaration//GEN-END:variables

private void lade_db_daten() throws SQLException {
        Statement stmt = Verbindung.con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM "
                + "Beobachtung, Beobachtungsort WHERE Beobachtung.BENUTZERID = '"
                + getBenutzerID() + "' AND beobachtung.beobachtungsortid = beobachtungsort.beobachtungsortid ORDER BY Beobachtung.DATUM ASC ");

        DefaultListModel<String> liste = new DefaultListModel<>();
        List neueListe = new ArrayList();

        while (rs.next()) {
            String s = rs.getString("Datum");
            neueListe.add(s);

            neueListe.stream().filter((elem) -> (!liste.contains(elem))).forEach(new Consumer() {
                @Override
                public void accept(Object elem) { liste.addElement((String) elem); }
            });
        }
        jList1.setModel(liste);
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

public String printLand(int regionid) throws SQLException {
        String RegionName = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Land FROM Beobachtungsort WHERE BeobachtungsortID = "+regionid+"";
            try (ResultSet rs0 = stmt.executeQuery(userid)) {
                while(rs0.next()) RegionName = rs0.getString("Land");
            }
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return RegionName;
}

public String printStadt(int regionid) throws SQLException {
        String RegionName = "";
        try{
            Statement stmt = Verbindung.con.createStatement();
            String userid = "SELECT Stadt FROM Beobachtungsort WHERE BeobachtungsortID = "+regionid+"";
            try (ResultSet rs0 = stmt.executeQuery(userid)) {
                while(rs0.next()) RegionName = rs0.getString("Stadt");
            }               
        } catch (SQLException e) {Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e); }
    return RegionName;
}
}
