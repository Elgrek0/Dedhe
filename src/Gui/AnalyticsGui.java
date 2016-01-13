/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Analytics.GraphPanel;
import MySQlConnection.DBConnection;
import com.mysql.jdbc.PreparedStatement;
import dedheproject.dataclasses.Breaker;
import dedheproject.dataclasses.CachedData;
import dedheproject.dataclasses.Transformer;
import dedheproject.exceptions.BadDateInputException;
import dedheproject.exceptions.ErrorPopup;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class AnalyticsGui extends javax.swing.JFrame {

    /**
     * Creates new form AnalyticsGui
     */
    DBConnection dbconn;
    ResultSet rs;

    public AnalyticsGui(DBConnection dbconn) {
        this.dbconn = dbconn;
        initComponents();
        start_year_spinner.setValue(new Integer(2015));
        start_month_spinner.setValue(new Integer(1));
        start_day_spinner.setValue(new Integer(14));
        end_year_spinner.setValue(new Integer(2015));
        end_month_spinner.setValue(new Integer(1));
        end_day_spinner.setValue(new Integer(15));
        try {
            rs = dbconn.execute_simple_query("SELECT name,id FROM breaker; ");
            db_names_combobox.removeAllItems();
            while (rs.next()) {
                db_names_combobox.addItem(rs.getObject(1));
            }
        } catch (SQLException ex) {
            ErrorPopup.popup(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        data_types_combobox = new javax.swing.JComboBox();
        db_names_combobox = new javax.swing.JComboBox();
        end_day_spinner = new javax.swing.JSpinner();
        end_month_spinner = new javax.swing.JSpinner();
        end_year_spinner = new javax.swing.JSpinner();
        start_day_spinner = new javax.swing.JSpinner();
        start_month_spinner = new javax.swing.JSpinner();
        start_year_spinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        graph_panel = new javax.swing.JPanel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        data_types_combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Breaker", "Transformer" }));
        data_types_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_types_comboboxActionPerformed(evt);
            }
        });
        jPanel1.add(data_types_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        db_names_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                db_names_comboboxActionPerformed(evt);
            }
        });
        jPanel1.add(db_names_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 10, 80, -1));

        end_day_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                end_day_spinnerStateChanged(evt);
            }
        });
        jPanel1.add(end_day_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 60, -1));

        end_month_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                end_month_spinnerStateChanged(evt);
            }
        });
        jPanel1.add(end_month_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 60, -1));
        jPanel1.add(end_year_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 90, -1));

        start_day_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                start_day_spinnerStateChanged(evt);
            }
        });
        jPanel1.add(start_day_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 60, -1));

        start_month_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                start_month_spinnerStateChanged(evt);
            }
        });
        jPanel1.add(start_month_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 60, -1));
        jPanel1.add(start_year_spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 90, -1));

        jLabel1.setText("Starting Date :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, -1, 30));

        jLabel2.setText("Ending Date :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 40));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Highlight Max", "Highlight Min" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout graph_panelLayout = new javax.swing.GroupLayout(graph_panel);
        graph_panel.setLayout(graph_panelLayout);
        graph_panelLayout.setHorizontalGroup(
            graph_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graph_panelLayout.setVerticalGroup(
            graph_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graph_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(graph_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void data_types_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_types_comboboxActionPerformed

        try {
            CachedData.mutex.acquire();
        } catch (InterruptedException ex) {
            CachedData.mutex.release();            
            Logger.getLogger(AnalyticsGui.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (data_types_combobox.getSelectedItem().toString().equals("Breaker")) {

            db_names_combobox.removeAllItems();
            for (Breaker b : CachedData.breakers) {
                db_names_combobox.addItem(b.name);
            }

        }
        if (data_types_combobox.getSelectedItem().toString().equals("Transformer")) {

            db_names_combobox.removeAllItems();
            for (Transformer t : CachedData.transformers) {
                db_names_combobox.addItem(t.name);
            }
        }
        CachedData.mutex.release();
    }//GEN-LAST:event_data_types_comboboxActionPerformed

    private void db_names_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_db_names_comboboxActionPerformed
        queryfornewdata();
    }//GEN-LAST:event_db_names_comboboxActionPerformed

    private void start_day_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_start_day_spinnerStateChanged
        queryfornewdata();
    }//GEN-LAST:event_start_day_spinnerStateChanged

    private void end_day_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_end_day_spinnerStateChanged
        queryfornewdata();
    }//GEN-LAST:event_end_day_spinnerStateChanged

    private void start_month_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_start_month_spinnerStateChanged
        queryfornewdata();
    }//GEN-LAST:event_start_month_spinnerStateChanged

    private void end_month_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_end_month_spinnerStateChanged
        queryfornewdata();
    }//GEN-LAST:event_end_month_spinnerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox data_types_combobox;
    private javax.swing.JComboBox db_names_combobox;
    private javax.swing.JSpinner end_day_spinner;
    private javax.swing.JSpinner end_month_spinner;
    private javax.swing.JSpinner end_year_spinner;
    private javax.swing.JPanel graph_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSpinner start_day_spinner;
    private javax.swing.JSpinner start_month_spinner;
    private javax.swing.JSpinner start_year_spinner;
    // End of variables declaration//GEN-END:variables

    private void check_if_valid(String startdate) throws BadDateInputException {
        System.out.println("check not yet fixed");
        if (false) {
            throw new BadDateInputException("Wrong Date");
        }
    }

    private void queryfornewdata() {
        if (rs != null) {
            String startdate = "'" + start_year_spinner.getValue() + "-"
                    + start_month_spinner.getValue() + "-" + start_day_spinner.getValue() + "'";
            String enddate;
            enddate = "'" + end_year_spinner.getValue() + "-"
                    + end_month_spinner.getValue() + "-" + end_day_spinner.getValue() + "'";

            if (dbconn != null) {
                try {

                    int selection = db_names_combobox.getSelectedIndex();
                    rs.first();
                    if (selection > 0) {
                        for (int k = 0; k < selection; k++) {
                            rs.next();
                        }
                    }
                    int id = rs.getInt(2);
                    String query = "";

                    if (data_types_combobox.getSelectedItem().toString().equals("Breaker")) {
                        query = "SELECT datetime,current FROM breaker_data "
                                + " where datetime  BETWEEN  " + startdate + " and " + enddate + " and Breaker_ID = " + id + ";";
                    }
                    if (data_types_combobox.getSelectedItem().toString().equals("Transformer")) {
                        query = "SELECT datetime,current FROM transformer_data "
                                + " where datetime  BETWEEN  " + startdate + " and " + enddate + " and Breaker_ID = " + id + ";";
                    }
                    System.out.println(query);
                    PreparedStatement pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
                    pstmt.addBatch();
                    pstmt.execute();

                    GraphPanel gp = new GraphPanel(pstmt.getResultSet());
                    graph_panel.setLayout(new BorderLayout());
                    graph_panel.removeAll();
                    graph_panel.add(gp.panel, BorderLayout.CENTER);
                    graph_panel.setVisible(true);
                    revalidate();

                } catch (SQLException ex) {

                    System.out.println("bad query");
                    System.out.println(ex.getMessage());

                }
            }
        }
    }

}
