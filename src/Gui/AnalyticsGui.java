/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import panels.plant_transformer_breaker_component.ChoosingPanel;
import panels.Analytics.GraphPanel;
import DB_connection.DBConnection;
import com.mysql.jdbc.PreparedStatement;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.StaticCachedData;
import DB_data_loader.data_classes.Transformer;
import exceptions.BadDateInputException;
import exceptions.ErrorPopup;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    ChoosingPanel cp = new ChoosingPanel();

    public AnalyticsGui(DBConnection dbconn) {
        this.dbconn = dbconn;
        this.add(cp);
        cp.setVisible(true);
        cp.setLocation(400, 0);
        initComponents();
        start_year_spinner.setValue(new Integer(2015));
        start_month_spinner.setValue(new Integer(1));
        start_day_spinner.setValue(new Integer(14));
        end_year_spinner.setValue(new Integer(2015));
        end_month_spinner.setValue(new Integer(1));
        end_day_spinner.setValue(new Integer(15));

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
            .addGap(0, 681, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        String startdate = "'" + start_year_spinner.getValue() + "-"
                + start_month_spinner.getValue() + "-" + start_day_spinner.getValue() + "'";
        String enddate;
        enddate = "'" + end_year_spinner.getValue() + "-"
                + end_month_spinner.getValue() + "-" + end_day_spinner.getValue() + "'";

        if (dbconn != null) {
            try {

                String query = "";

                Breaker b = (Breaker) cp.breaker_combobox.getSelectedItem();
                query = "SELECT datetime,current FROM breaker_data "
                        + " where datetime  BETWEEN  " + startdate + " and " + enddate + " and Breaker_ID = " + b.id + ";";

                System.out.println(query);
                Statement pstmt = dbconn.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                // (PreparedStatement) dbconn.conn.prepareStatement(query);
                pstmt.executeQuery(query);

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
