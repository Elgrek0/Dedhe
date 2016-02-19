/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import plant_transformer_breaker_component.ChoosingPanel;
import DB_connection.FixValues;
import DB_connection.DBConnection;
import MySQlConnection.MainWindow;
import com.mysql.jdbc.PreparedStatement;
import dedheproject.ExcelSheetOpener;
import dedheproject.Fileopener;
import data_classes.Breaker;
import dedheproject.exceptions.BadDateInputException;
import dedheproject.exceptions.BadTimeInputException;
import dedheproject.exceptions.NoSuchSheetException;
import dedheproject.exceptions.ErrorPopup;
import dedheproject.exceptions.badfileexception;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paris
 */
public class LoadExcelDataGui extends javax.swing.JFrame {

    /**
     * Creates new form LoadExcelDataGui
     */
    DBConnection dbconn;
    ChoosingPanel cp;

    public LoadExcelDataGui(DBConnection dbconn) {
        this.dbconn = dbconn;
        initComponents();
        cp = new ChoosingPanel();
        cp.setVisible(true);
        add(cp);
        itemcount = (int) to_line_spinner.getValue() - (int) from_line_spinner.getValue();
    }
    File excelfile;
    ExcelSheetOpener sheet;
    int itemcount = 0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sample_data_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        load_temporary_data_button = new javax.swing.JButton();
        sheet_number_spinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        pass_data_to_DB_button = new javax.swing.JButton();
        from_line_spinner = new javax.swing.JSpinner();
        to_line_spinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        sample_data_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(sample_data_table);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Sample Data");

        load_temporary_data_button.setText("Open File");
        load_temporary_data_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_temporary_data_buttonActionPerformed(evt);
            }
        });

        sheet_number_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sheet_number_spinnerStateChanged(evt);
            }
        });

        jLabel2.setText("Sheet Number");

        pass_data_to_DB_button.setText("Pass to DB");
        pass_data_to_DB_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_data_to_DB_buttonActionPerformed(evt);
            }
        });

        from_line_spinner.setValue(1);
        from_line_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                from_line_spinnerStateChanged(evt);
            }
        });

        to_line_spinner.setValue(11);
        to_line_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                to_line_spinnerStateChanged(evt);
            }
        });

        jLabel6.setText("from line");

        jLabel7.setText("to");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 306, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(load_temporary_data_button, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(sheet_number_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addComponent(pass_data_to_DB_button, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(from_line_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(to_line_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(load_temporary_data_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sheet_number_spinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(from_line_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(to_line_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_data_to_DB_button, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void load_temporary_data_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_temporary_data_buttonActionPerformed
        try {
            excelfile = Fileopener.openfile();

            if (excelfile == null) {
                return;
            }
            //int sheet_number, int start_x, int start_y, int columns_to_read, int rows_to_read, File excelfile
            int columncount = 3;
            sheet = new ExcelSheetOpener((int) sheet_number_spinner.getValue(), 0, (int) from_line_spinner.getValue(), columncount, 10, excelfile);
            String columnnames[] = new String[columncount];
            for (int i = 0; i < columncount; i++) {
                columnnames[i] = "";
            }
            DefaultTableModel dtb = new DefaultTableModel(sheet.data, columnnames);
            sample_data_table.setModel(dtb);
            revalidate();
        } catch (FileNotFoundException | NoSuchSheetException | badfileexception ex) {
            excelfile = null;
            ErrorPopup.popup(ex);
        }
    }//GEN-LAST:event_load_temporary_data_buttonActionPerformed

    private void pass_data_to_DB_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_data_to_DB_buttonActionPerformed

            pass_data_to_breaker();
        

    }//GEN-LAST:event_pass_data_to_DB_buttonActionPerformed

    private void sheet_number_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sheet_number_spinnerStateChanged
        if ((int) sheet_number_spinner.getValue() < 0 || excelfile == null) {
            sheet_number_spinner.setValue(0);
        } else {
            try {
                int columncount = 3;
                sheet = new ExcelSheetOpener((int) sheet_number_spinner.getValue(), 0, (int) from_line_spinner.getValue(), columncount, 10, excelfile);

                if (sheet.data != null) {
                    String columnnames[] = new String[columncount];
                    for (int i = 0; i < columncount; i++) {
                        columnnames[i] = "";
                    }
                    DefaultTableModel dtb = new DefaultTableModel(sheet.data, columnnames);
                    sample_data_table.setModel(dtb);
                    revalidate();
                } else {
                    sheet_number_spinner.setValue((int) sheet_number_spinner.getValue() - 1);
                }
            } catch (FileNotFoundException | NoSuchSheetException ex) {
                excelfile = null;
                ErrorPopup.popup(ex);
            }
        }
    }//GEN-LAST:event_sheet_number_spinnerStateChanged

    private void from_line_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_from_line_spinnerStateChanged
        if ((int) from_line_spinner.getValue() > (int) to_line_spinner.getValue()) {
            from_line_spinner.setValue(to_line_spinner.getValue());
        }
        if ((int) from_line_spinner.getValue() < 1) {
            from_line_spinner.setValue(1);
        }

        itemcount = (int) to_line_spinner.getValue() - (int) from_line_spinner.getValue();
    }//GEN-LAST:event_from_line_spinnerStateChanged

    private void to_line_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_to_line_spinnerStateChanged
        if ((int) from_line_spinner.getValue() > (int) to_line_spinner.getValue()) {
            to_line_spinner.setValue(from_line_spinner.getValue());
        }

        itemcount = (int) to_line_spinner.getValue() - (int) from_line_spinner.getValue();        // TODO add your handling code here:
    }//GEN-LAST:event_to_line_spinnerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner from_line_spinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton load_temporary_data_button;
    private javax.swing.JButton pass_data_to_DB_button;
    private javax.swing.JTable sample_data_table;
    private javax.swing.JSpinner sheet_number_spinner;
    private javax.swing.JSpinner to_line_spinner;
    // End of variables declaration//GEN-END:variables

    private void pass_data_to_breaker() {
        try {
            // to 1 sto x einai giati ta onomata einai axrista
            // to 1 sto y giati exoun balei kati columnname
            sheet = new ExcelSheetOpener((int) sheet_number_spinner.getValue(), 1, (int) from_line_spinner.getValue(), 2,
                    itemcount, excelfile);
        } catch (FileNotFoundException | NoSuchSheetException ex) {
            excelfile = null;
            ErrorPopup.popup(ex);
        }

        int errors = 0;
        boolean fatalerror = false;
        try {
            dbconn.disablekeys("breaker_data");
            Statement stmt = dbconn.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Breaker b=(Breaker)cp.breaker_combobox.getSelectedItem();
            for (int i = 0; i < itemcount - 1; i++) {
                try {
                    String query = " INSERT INTO breaker_data VALUES (" + "'" + FixValues.reversedate(sheet.data[i][0], '/', ':')
                            + "'" + "," + sheet.data[i][1].replace(',', '.')
                            + "," + b.id + ");\n";

                    stmt.executeUpdate(query);

                } catch (BadDateInputException ex) {
                    System.out.println("Bad date format in Excel at row : " + i);
                } catch (BadTimeInputException ex) {
                    System.out.println("Bad time format in Excel at row : " + i);
                }
            }

            dbconn.enablekeys("breaker_data");
        } catch (SQLException ex) {
            try {
                dbconn.enablekeys("breaker_data");
            } catch (SQLException ex1) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex1);
            }
            fatalerror = true;
            System.out.println("query error at loadexceldatagui");
            System.out.println(ex.getMessage());
        }
        if (fatalerror == false) {
            System.out.println("querycompleted sucesfully");
            System.out.println("errrors #" + errors);
        }
    }
}
