/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.panels.db_panels;

import DB_data_loader.data_classes.Breaker;
import DB_data_loader.StaticCachedData;
import DB_data_loader.data_classes.ElectricalItemPath;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Paris
 */
public final class ChoosingPanel extends javax.swing.JPanel {

    public PowerPlant selected_plant = null;
    public Transformer selected_transformer = null;
    public Breaker selected_breaker = null;
    public ElectricalItemPath collection;

    int plantselection = 0;
    int transformerselection = 0;
    int breakerselection = 0;

    Vector<ActionListener> changelisteners = new Vector<ActionListener>();

    public void addChangeListener(ActionListener changelistener) {
        changelisteners.add(changelistener);

    }

    private void statechangedevent() {
        collection = new ElectricalItemPath(selected_plant, selected_transformer, selected_breaker);
        for (int i = 0; i < changelisteners.size(); i++) {
            changelisteners.get(i).actionPerformed(null);
        }
    }

    public ChoosingPanel() {

        initComponents();
        setBounds(0, 0, 400, 70);
        refresh();
        setVisible(true);
        StaticCachedData.addChangeListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                statechangedevent();
            }
        });

        collection = new ElectricalItemPath(selected_plant, selected_transformer, selected_breaker);

    }

    public void refresh() {

        update_plants();
        update_transformers();
        update_breakers();

    }

    void update_plants() {

        selected_plant = null;
        selected_transformer = null;
        selected_breaker = null;

        DefaultComboBoxModel plantcombobox = new DefaultComboBoxModel();
        plant_combobox.setModel(plantcombobox);

        StaticCachedData.lock();
        if (StaticCachedData.db_powerplants.size() > 0) {
            plantcombobox = new DefaultComboBoxModel(StaticCachedData.db_powerplants);
            plant_combobox.setModel(plantcombobox);
            try {
                if (plantselection > 0) {
                    plant_combobox.setSelectedIndex(plantselection);
                } else {
                    plant_combobox.setSelectedIndex(0);
                    plantselection = 0;
                }
            } catch (Exception ex) {
                plant_combobox.setSelectedIndex(-1);
                plantselection = -1;
            }
            selected_plant = (PowerPlant) plant_combobox.getSelectedItem();

        }

        StaticCachedData.unlock();
        update_transformers();
    }

    void update_transformers() {

        selected_transformer = null;
        selected_breaker = null;

        DefaultComboBoxModel transformercombobox = new DefaultComboBoxModel();
        transformer_combobox.setModel(transformercombobox);
        if (selected_plant != null) {
            StaticCachedData.lock();
            if (selected_plant.transformers.size() > 0) {

                transformercombobox = new DefaultComboBoxModel(selected_plant.transformers);
                transformer_combobox.setModel(transformercombobox);
                try {
                    if (transformerselection >= transformer_combobox.getItemCount()) {
                        transformerselection = transformer_combobox.getItemCount() - 1;
                    }
                    if (transformerselection == -1 && transformer_combobox.getItemCount() > 0) {
                        transformerselection = 0;
                    }

                    transformer_combobox.setSelectedIndex(transformerselection);
                } catch (Exception ex) {
                    try {
                        transformer_combobox.setSelectedIndex(0);
                        transformerselection = 0;
                    } catch (Exception ex2) {

                        transformer_combobox.setSelectedIndex(-1);
                        transformerselection = -1;
                    }

                }
                selected_transformer = (Transformer) transformer_combobox.getSelectedItem();

            }

        }
        StaticCachedData.unlock();
        update_breakers();
    }

    void update_breakers() {

        selected_breaker = null;

        DefaultComboBoxModel breakercombobox = new DefaultComboBoxModel();
        breaker_combobox.setModel(breakercombobox);
        if (selected_transformer != null) {
            StaticCachedData.lock();
            if (selected_transformer.breakers.size() > 0) {

                breakercombobox = new DefaultComboBoxModel(selected_transformer.breakers);
                breaker_combobox.setModel(breakercombobox);
                try {
                    if (breakerselection >= breaker_combobox.getItemCount()) {
                        breakerselection = breaker_combobox.getItemCount() - 1;
                    }
                    if (breakerselection == -1 && breaker_combobox.getItemCount() > 0) {
                        breakerselection = 0;
                    }
                    breaker_combobox.setSelectedIndex(breakerselection);
                } catch (Exception ex) {
                    try {
                        breaker_combobox.setSelectedIndex(0);
                        breakerselection = 0;
                    } catch (Exception ex2) {
                        breaker_combobox.setSelectedIndex(-1);
                        breakerselection = -1;
                    }

                }
                selected_breaker = (Breaker) breaker_combobox.getSelectedItem();
            }

        }
        StaticCachedData.unlock();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    /*
     }*/
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        breaker_combobox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        transformer_label = new javax.swing.JLabel();
        breaker_label = new javax.swing.JLabel();
        plant_combobox = new javax.swing.JComboBox();
        transformer_combobox = new javax.swing.JComboBox();

        breaker_combobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                breaker_comboboxItemStateChanged(evt);
            }
        });

        jLabel1.setText("Plant");

        transformer_label.setText("Transformer");

        breaker_label.setText("Breaker");

        plant_combobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                plant_comboboxItemStateChanged(evt);
            }
        });

        transformer_combobox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                transformer_comboboxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plant_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(transformer_label)
                        .addGap(47, 47, 47)
                        .addComponent(breaker_label)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transformer_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(breaker_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transformer_label)
                    .addComponent(breaker_label)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plant_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transformer_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(breaker_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void plant_comboboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_plant_comboboxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            selected_plant = (PowerPlant) plant_combobox.getSelectedItem();

            plantselection = plant_combobox.getSelectedIndex();
            transformerselection = transformer_combobox.getSelectedIndex();
            breakerselection = breaker_combobox.getSelectedIndex();

            update_transformers();
            update_breakers();
            statechangedevent();
        }
    }//GEN-LAST:event_plant_comboboxItemStateChanged

    private void transformer_comboboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_transformer_comboboxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            selected_transformer = (Transformer) transformer_combobox.getSelectedItem();

            plantselection = plant_combobox.getSelectedIndex();
            transformerselection = transformer_combobox.getSelectedIndex();
            breakerselection = breaker_combobox.getSelectedIndex();
            breakerselection = breaker_combobox.getSelectedIndex();

            update_breakers();
            statechangedevent();
        }
    }//GEN-LAST:event_transformer_comboboxItemStateChanged

    private void breaker_comboboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_breaker_comboboxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            selected_breaker = (Breaker) breaker_combobox.getSelectedItem();

            plantselection = plant_combobox.getSelectedIndex();
            transformerselection = transformer_combobox.getSelectedIndex();
            breakerselection = breaker_combobox.getSelectedIndex();

            statechangedevent();
        }
    }//GEN-LAST:event_breaker_comboboxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox breaker_combobox;
    private javax.swing.JLabel breaker_label;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JComboBox plant_combobox;
    public javax.swing.JComboBox transformer_combobox;
    private javax.swing.JLabel transformer_label;
    // End of variables declaration//GEN-END:variables

}
