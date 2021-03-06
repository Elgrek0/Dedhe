/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DB_connection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Paris
 */
public class QueryFrame extends javax.swing.JFrame {

    /**
     * Creates new form QueryFrame
     */
    DBConnection dbconn;

    public QueryFrame(DBConnection dbconn) {
        this.dbconn = dbconn;
        initComponents();
        setLocation(400, 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        QueryResult = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        QueryArea1 = new javax.swing.JTextArea();
        RunQuery = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        QueryArea = new javax.swing.JTextArea();

        jTextField1.setText("jTextField1");

        jScrollPane2.setViewportView(QueryResult);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        QueryArea1.setColumns(20);
        QueryArea1.setRows(5);
        QueryArea1.setText("select *\nfrom task\nwhere EndDate is not null and EndDate!='';");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        RunQuery.setText("run query");
        RunQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunQueryActionPerformed(evt);
            }
        });

        QueryArea.setColumns(20);
        QueryArea.setRows(5);
        jScrollPane1.setViewportView(QueryArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RunQuery)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(RunQuery)
                .addContainerGap(256, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RunQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunQueryActionPerformed
        if (dbconn != null) {
            try {

                String query = QueryArea.getText();
                PreparedStatement pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
                pstmt.addBatch();
                pstmt.execute();

                ResultSet rs = pstmt.getResultSet();
                if (rs != null) {
                    JTable ResultTable = new JTable(dbconn.buildTableModel(rs));
                    JFrame result = new JFrame();
                    result.add(new JScrollPane(ResultTable));
                    result.setVisible(true);
                    result.setSize(1000, 500);
                }
            } catch (SQLException ex) {

                System.out.println("bad query");
                System.out.println(ex.getMessage());

            }
        }
    }//GEN-LAST:event_RunQueryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea QueryArea;
    public javax.swing.JTextArea QueryArea1;
    public javax.swing.JList QueryResult;
    private javax.swing.JButton RunQuery;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
