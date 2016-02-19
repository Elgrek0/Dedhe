/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DB_data_loader.LoadDataFromDB;
import panels.plant_transformer_breaker_component.ChoosingPanel;
import exceptions.NoParentTransformerException;
import exceptions.NoParentPlantException;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.StaticCachedData;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import exceptions.ErrorPopup;
import exceptions.NoActiveDbConnectionException;
import exceptions.PowerPlantParentNotFoundException;
import exceptions.TransformerParentNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class LoadFromExcelFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoadFromExcelFrame
     */
    ChoosingPanel choosingpanel;

    public LoadFromExcelFrame() {
        initComponents();

        setSize(500, 400);

        choosingpanel = new ChoosingPanel();
        choosingpanel.setVisible(true);
        choosingpanel.setSize(500, 100);
        add(choosingpanel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        add_plant_button = new javax.swing.JButton();
        breakername_textfield = new javax.swing.JTextField();
        add_breaker_button = new javax.swing.JButton();
        plantname_textfield = new javax.swing.JTextField();
        transformername_textfield = new javax.swing.JTextField();
        add_transformer_button = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        add_plant_button.setText("add new plant");
        add_plant_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_plant_buttonActionPerformed(evt);
            }
        });

        add_breaker_button.setText("add new breaker");
        add_breaker_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_breaker_buttonActionPerformed(evt);
            }
        });

        add_transformer_button.setText("add new transformer");
        add_transformer_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_transformer_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(transformername_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(plantname_textfield))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(add_plant_button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(add_transformer_button))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(breakername_textfield)
                        .addGap(18, 18, 18)
                        .addComponent(add_breaker_button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_plant_button)
                    .addComponent(plantname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transformername_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_transformer_button))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_breaker_button)
                    .addComponent(breakername_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_plant_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_plant_buttonActionPerformed
        PowerPlant p = new PowerPlant(StaticCachedData.db_powerplants.lastElement().id + 1, plantname_textfield.getText());
        StaticCachedData.db_powerplants.add(p);
        p.store();
        try {
            LoadDataFromDB.refresh_powerplants();
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoActiveDbConnectionException ex) {
            Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        choosingpanel.refresh();
    }//GEN-LAST:event_add_plant_buttonActionPerformed

    private void add_transformer_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_transformer_buttonActionPerformed
        if (choosingpanel.selected_plant != null) {
            Transformer t = new Transformer(StaticCachedData.db_transformers.lastElement().id + 1, transformername_textfield.getText(), choosingpanel.selected_plant);
            StaticCachedData.db_transformers.add(t);
            t.store();
            try {
                LoadDataFromDB.refresh_transformers();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoActiveDbConnectionException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PowerPlantParentNotFoundException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                throw new NoParentPlantException();
            } catch (NoParentPlantException ex) {
                new ErrorPopup(ex);
            }
        }
        choosingpanel.refresh();
    }//GEN-LAST:event_add_transformer_buttonActionPerformed

    private void add_breaker_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_breaker_buttonActionPerformed
        if (choosingpanel.selected_transformer != null) {
            Breaker b = new Breaker(StaticCachedData.db_breakers.lastElement().id + 1, breakername_textfield.getText(), choosingpanel.selected_transformer);
            StaticCachedData.db_breakers.add(b);
            b.store();
            try {
                LoadDataFromDB.refresh_breakers();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoActiveDbConnectionException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerParentNotFoundException ex) {
                Logger.getLogger(LoadFromExcelFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                throw new NoParentTransformerException();
            } catch (NoParentTransformerException ex) {
                new ErrorPopup(ex);
            }
        }
        choosingpanel.refresh();
    }//GEN-LAST:event_add_breaker_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_breaker_button;
    private javax.swing.JButton add_plant_button;
    private javax.swing.JButton add_transformer_button;
    private javax.swing.JTextField breakername_textfield;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField plantname_textfield;
    private javax.swing.JTextField transformername_textfield;
    // End of variables declaration//GEN-END:variables
}
