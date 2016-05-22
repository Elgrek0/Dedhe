/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Gui.panels.db_panels.ChoosingPanel;
import Gui.panels.analyticsguipanels.GraphPanel;
import DB_connection.DBConnection;
import DB_data_loader.data_classes.ElectricalValue;
import DB_data_loader.data_classes.ElectricalItemPath;
import Gui.panels.analyticsguipanels.StatisticsPanel;
import cache.BaseCache;
import exceptions.NoActiveDbConnectionException;
import exceptions.NoItemSelectedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import Gui.panels.error_panels.ErrorPopup;
import Gui.panels.analyticsguipanels.CondencePanel;
import Gui.panels.date_panel.DatePanel;
import Gui.panels.analyticsguipanels.SmoothingPanel;

/**
 *
 * @author Paris
 */
public class AnalyticsGui extends javax.swing.JFrame {

    /**
     * Creates new form AnalyticsGui
     */
    DBConnection dbconn;

    BaseCache cache;
    ChoosingPanel choosing_panel = new ChoosingPanel();
    DatePanel date_panel = new DatePanel();
    CondencePanel condence_panel = new CondencePanel();
    SmoothingPanel smoothing_panel = new SmoothingPanel();
    GraphPanel graph_panel = null;
    StatisticsPanel statistics_panel = new StatisticsPanel();

    List<Vector<ElectricalValue>> data = new ArrayList<Vector<ElectricalValue>>();
    DefaultListModel<ElectricalItemPath> toloadlistmodel = new DefaultListModel();

    public AnalyticsGui(DBConnection dbconn, BaseCache cache) {
        this.cache = cache;
        this.dbconn = dbconn;

        add(choosing_panel);
        add(date_panel);
        add(condence_panel);
        add(smoothing_panel);
        add(statistics_panel);
        statistics_panel.setLocation(0, 400);
        smoothing_panel.setLocation(0, 200);
        condence_panel.setLocation(0, 300);
        choosing_panel.setLocation(600, 0);
        setResizable(true);
        initComponents();
        to_load_list.setModel(toloadlistmodel);
        setSize(getPreferredSize());

        choosing_panel.addChangeListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
            }
        });
        date_panel.addChangeListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
            }
        });

        condence_panel.addChangeListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
            }
        });
        smoothing_panel.addChangeListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
            }
        });

        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
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
        breaker_radio_button = new javax.swing.JRadioButton();
        transformer_radio_button = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        to_load_list = new javax.swing.JList();
        add_to_list_button = new javax.swing.JButton();
        remove_from_list_button = new javax.swing.JButton();
        sum_radio_button = new javax.swing.JRadioButton();
        overlap_radio_button = new javax.swing.JRadioButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(1300, 608));

        breaker_radio_button.setSelected(true);
        breaker_radio_button.setText("load Breaker Data");
        breaker_radio_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breaker_radio_buttonActionPerformed(evt);
            }
        });

        transformer_radio_button.setText("load Transformer Data");
        transformer_radio_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transformer_radio_buttonActionPerformed(evt);
            }
        });

        to_load_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(to_load_list);

        add_to_list_button.setText("Add");
        add_to_list_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_to_list_buttonActionPerformed(evt);
            }
        });

        remove_from_list_button.setText("Remove");
        remove_from_list_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_from_list_buttonActionPerformed(evt);
            }
        });

        sum_radio_button.setText("Sum");
        sum_radio_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sum_radio_buttonActionPerformed(evt);
            }
        });

        overlap_radio_button.setSelected(true);
        overlap_radio_button.setText("Overlap");
        overlap_radio_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overlap_radio_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(1082, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transformer_radio_button)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(breaker_radio_button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(overlap_radio_button)
                            .addComponent(sum_radio_button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add_to_list_button, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(remove_from_list_button)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(breaker_radio_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transformer_radio_button)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_to_list_button)
                    .addComponent(remove_from_list_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sum_radio_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(overlap_radio_button)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void breaker_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breaker_radio_buttonActionPerformed
        transformer_radio_button.setSelected(false);
        breaker_radio_button.setSelected(true);

        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
    }//GEN-LAST:event_breaker_radio_buttonActionPerformed

    private void transformer_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transformer_radio_buttonActionPerformed
        transformer_radio_button.setSelected(true);
        breaker_radio_button.setSelected(false);
        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
    }//GEN-LAST:event_transformer_radio_buttonActionPerformed

    private void add_to_list_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_to_list_buttonActionPerformed
        ElectricalItemPath currcollection;
        if (transformer_radio_button.isSelected()) {
            currcollection = new ElectricalItemPath(choosing_panel.selected_plant, choosing_panel.selected_transformer);
        } else {
            currcollection = new ElectricalItemPath(choosing_panel.selected_plant, choosing_panel.selected_transformer, choosing_panel.selected_breaker);
        }
        if (!toloadlistmodel.contains(currcollection)) {
            toloadlistmodel.addElement(currcollection);
        }


    }//GEN-LAST:event_add_to_list_buttonActionPerformed

    private void remove_from_list_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_from_list_buttonActionPerformed
        int[] loc = to_load_list.getSelectedIndices();
        for (int i = 0; i < loc.length; i++) {
            if (loc[i] != -1) {
                toloadlistmodel.remove(loc[i] - i);
            }
        }
        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);

    }//GEN-LAST:event_remove_from_list_buttonActionPerformed

    private void sum_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sum_radio_buttonActionPerformed
        overlap_radio_button.setSelected(false);
        sum_radio_button.setSelected(true);

        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
    }//GEN-LAST:event_sum_radio_buttonActionPerformed

    private void overlap_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overlap_radio_buttonActionPerformed
        overlap_radio_button.setSelected(true);
        sum_radio_button.setSelected(false);

        queryfornewdata(choosing_panel.collection, date_panel.startdate, date_panel.enddate);
    }//GEN-LAST:event_overlap_radio_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_to_list_button;
    private javax.swing.JRadioButton breaker_radio_button;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton overlap_radio_button;
    private javax.swing.JButton remove_from_list_button;
    private javax.swing.JRadioButton sum_radio_button;
    private javax.swing.JList to_load_list;
    private javax.swing.JRadioButton transformer_radio_button;
    // End of variables declaration//GEN-END:variables

    private Vector<ElectricalValue> modify_data(Vector<ElectricalValue> olddata) {
        int condence = condence_panel.condence_value;
        if (condence != 1) {
            Vector<ElectricalValue> modifieddata = new Vector<ElectricalValue>();
            if (condence_panel.sum_radio_button.isSelected()) {
                int j = 0;
                for (int i = 0; i < olddata.size(); i += j) {
                    DateTime start = olddata.get(i).datetime;
                    double avg = 0;
                    j = 0;
                    while ((i + j) < olddata.size() && condence_panel.is_inside_period(start, olddata.get(i + j).datetime)) {

                        avg += olddata.get(i + j).value;
                        j++;
                    }
                    modifieddata.add(new ElectricalValue(olddata.get((int) i + (j) / 2).datetime, (float) (avg)));
                }

            } else if (condence_panel.average_radio_button.isSelected()) {
                int j = 0;
                for (int i = 0; i < olddata.size(); i += j) {
                    DateTime start = olddata.get(i).datetime;
                    double avg = 0;
                    j = 0;
                    while ((i + j) < olddata.size() && condence_panel.is_inside_period(start, olddata.get(i + j).datetime)) {

                        avg += olddata.get(i + j).value;
                        j++;
                    }
                    modifieddata.add(new ElectricalValue(olddata.get((int) i + (j - 1) / 2).datetime, (float) (avg / j)));
                }
            }
            return modifieddata;
        }
        return olddata;
    }

    private void queryfornewdata(ElectricalItemPath e, LocalDate startdate, LocalDate enddate) {
        data = new ArrayList<>();

        if (breaker_radio_button.isSelected()) {
            try {
                data.add(modify_data(cache.get_data(e.breaker, startdate, enddate)));

            } catch (NoActiveDbConnectionException | NoItemSelectedException ex) {
                ErrorPopup.popup(ex);
                data = new ArrayList<>();
            }

            for (int i = 0; i < toloadlistmodel.getSize(); i++) {
                if (!toloadlistmodel.get(i).equals(e)) {
                    if (toloadlistmodel.get(i).breaker != null) {
                        try {
                            data.add(modify_data(cache.get_data(toloadlistmodel.get(i).breaker, startdate, enddate)));
                        } catch (NoActiveDbConnectionException | NoItemSelectedException ex) {
                            ErrorPopup.popup(ex);
                        }
                    }
                }
            }

        } else {
            try {
                data.add(modify_data(cache.get_data(e.transformer, startdate, enddate)));

            } catch (NoActiveDbConnectionException | NoItemSelectedException ex) {
                ErrorPopup.popup(ex);
                data = new ArrayList<>();
            }
            for (int i = 0; i < toloadlistmodel.getSize(); i++) {
                if (!toloadlistmodel.get(i).equals(e)) {
                    if (toloadlistmodel.get(i).breaker == null) {
                        try {
                            data.add(modify_data(cache.get_data(toloadlistmodel.get(i).transformer, startdate, enddate)));
                        } catch (NoActiveDbConnectionException | NoItemSelectedException ex) {
                            ErrorPopup.popup(ex);
                        }
                    }

                }
            }

        }
        if (data.size() > 0) {
            if (graph_panel != null) {
                graph_panel.setVisible(true);
            }
            if (!overlap_radio_button.isSelected()) {
                Vector<ElectricalValue> sumdata = new Vector<>();
                for (int k = 0; k < data.get(0).size(); k++) {
                    float sum = 0;
                    for (int i = 0; i < data.size(); i++) {
                        sum += data.get(i).get(k).value;
                    }
                    sumdata.add(new ElectricalValue(data.get(0).get(k).datetime, sum));
                }
                data = new ArrayList<>();
                data.add(sumdata);
            }
            remakegraph(data);
            recalculatemetrics();
            repaint();
        } else {
            if (graph_panel != null) {
                graph_panel.setVisible(false);
                graph_panel.datetime_textfield.setText("No Data");
            }
        }

    }

    private void recalculatemetrics() {

        statistics_panel.update(data);

    }

    private void remakegraph(Vector<ElectricalValue> data) {
        if (graph_panel != null) {
            remove(graph_panel);
            remove(graph_panel.datetime_textfield);
        }
        graph_panel = new GraphPanel(data, smoothing_panel.jSlider1.getValue());

        add(graph_panel);
        add(graph_panel.datetime_textfield);
        graph_panel.datetime_textfield.setLocation(640, 500);
        graph_panel.setSize(graph_panel.getPreferredSize());
        graph_panel.setLocation(400, 80);
        revalidate();
    }

    private void remakegraph(List<Vector<ElectricalValue>> datalist) {
        if (graph_panel != null) {
            remove(graph_panel);
            remove(graph_panel.datetime_textfield);
        }
        graph_panel = new GraphPanel(datalist, smoothing_panel.jSlider1.getValue());

        add(graph_panel);
        add(graph_panel.datetime_textfield);
        graph_panel.datetime_textfield.setLocation(640, 500);
        graph_panel.setSize(graph_panel.getPreferredSize());
        graph_panel.setLocation(400, 80);
        revalidate();
    }

}
