/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DB_connection.FixValues;
import DB_connection.H2Server;
import DB_connection.H2MyConnection;
import DB_connection.DBConnection;
import DB_connection.MySQLConnection;
import DB_data_loader.LoadDataFromDB;
import Gui.AnalyticsGui;
import panels.plant_transformer_breaker_component.ChoosingPanel;
import Gui.LoadExcelDataGui;
import Gui.AddNewElectricalItems;
import LoginFrame.LoginInfo;
import ExcelComponents.ExcelSheetOpener;
import ExcelComponents.Fileopener;
import DB_data_loader.StaticCachedData;
import exceptions.BadDateInputException;
import exceptions.BadTimeInputException;
import exceptions.CouldntConnectException;
import exceptions.NoActiveDbConnectionException;
import exceptions.NoSuchSheetException;
import exceptions.badfileexception;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Paris
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindows
     */
    DBConnection dbconn;
    public static boolean debug = false;

    public MainWindow(DBConnection dbconn) {
        this.dbconn=dbconn;
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        QueryButton = new javax.swing.JButton();
        ErrorArea = new java.awt.TextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        QueryButton.setText("Queries");
        QueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QueryButtonActionPerformed(evt);
            }
        });
        getContentPane().add(QueryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 125, -1));
        getContentPane().add(ErrorArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 114, 243, 182));

        jLabel6.setText("Error Window");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 86, -1, -1));

        jButton3.setText("Excel to db");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        jButton4.setText("analytics");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 130, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Thread() {

            @Override
            public void run() {

                LoadExcelDataGui a = new LoadExcelDataGui(dbconn);
                a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                a.setVisible(true);

            }

        }.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Thread() {

            @Override
            public void run() {

                AnalyticsGui a = new AnalyticsGui(dbconn);
                a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                a.setVisible(true);

            }

        }.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void QueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QueryButtonActionPerformed
        new Thread() {

            @Override
            public void run() {
                QueryFrame a = new QueryFrame(dbconn);
                a.setVisible(true);

            }

        }.start();
    }//GEN-LAST:event_QueryButtonActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.TextArea ErrorArea;
    private javax.swing.JButton QueryButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
