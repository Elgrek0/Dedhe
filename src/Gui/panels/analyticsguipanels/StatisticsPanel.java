/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.panels.analyticsguipanels;

import DB_data_loader.data_classes.ElectricalValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.joda.time.DateTime;

/**
 *
 * @author vatian
 */
public final class StatisticsPanel extends javax.swing.JPanel {

    private List<Vector<ElectricalValue>> data = new ArrayList();
    public float avg, sum;
    public ElectricalValue max = null, min = null;

    /**
     * Creates new form max_panel
     */
    public StatisticsPanel(Vector<ElectricalValue> data_input) {
        data.add(data_input);
        initComponents();

        min_finder();
        min_textfield.setEditable(false);
        max_finder();
        max_textfield.setEditable(false);

        draw();

        setSize(400, 400);
        setVisible(true);
    }

    public StatisticsPanel() {

        initComponents();
        min_textfield.setEditable(false);
        max_textfield.setEditable(false);
        setSize(400, 400);
        setVisible(true);
    }

    public void update(List<Vector<ElectricalValue>> data_input) {

        this.data = data_input;

        min_finder();

        max_finder();

        draw();

    }

    public void draw() {

        if (max != null) {
            max_textfield.setText(" " + max.toString());
        } else {
            max_textfield.setText("No data");
        }

        if (min != null) {
            min_textfield.setText(" " + min.toString());
        } else {
            min_textfield.setText("No data");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        max_textfield = new javax.swing.JTextField();
        min_textfield = new javax.swing.JTextField();
        max_label = new javax.swing.JLabel();
        min_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 154, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 105, -1, -1));
        add(max_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 210, -1));
        add(min_textfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 210, -1));

        max_label.setText("Maximum is :");
        add(max_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        min_label.setText("Minimum is :");
        add(min_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel1.setText("statistics");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, -1));
    }// </editor-fold>//GEN-END:initComponents
    public void max_finder() {

        max = new ElectricalValue(new DateTime(), Float.MIN_VALUE);
        for (int j = 0; j < data.size(); j++) {
            for (int i = 0; i < data.get(j).size(); i++) {

                ElectricalValue curr = data.get(j).get(i);
                if (curr.value > max.value) {
                    max = curr;
                }
            }
        }

    }

    public void min_finder() {

        min = new ElectricalValue(new DateTime(), Float.MAX_VALUE);
        for (int j = 0; j < data.size(); j++) {
            for (int i = 0; i < data.get(j).size(); i++) {
                ElectricalValue curr = data.get(j).get(i);
                if (curr.value < min.value) {
                    min = curr;
                }
            }

        }
    }

    public static void main(String args[]) {
        Vector<ElectricalValue> v = new Vector<ElectricalValue>();
        v.add(new ElectricalValue(new DateTime(), 17.5f));
        v.add(new ElectricalValue(new DateTime(), 18.5f));
        v.add(new ElectricalValue(new DateTime(), 19.5f));
        v.add(new ElectricalValue(new DateTime(), 17.0f));
        v.add(new ElectricalValue(new DateTime(), 17.0f));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /* JFrame frame = new JFrame();
                 frame.add(new ReportPanel(v));
                 frame.setVisible(true);
                 frame.setSize(400, 250);*/
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel max_label;
    private javax.swing.JTextField max_textfield;
    private javax.swing.JLabel min_label;
    private javax.swing.JTextField min_textfield;
    // End of variables declaration//GEN-END:variables
}
