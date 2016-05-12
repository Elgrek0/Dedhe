/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.panels.error_panels;

import javax.swing.JOptionPane;

/**
 *
 * @author Paris
 */
public class Popup {

    
    public Popup(String s) {
        JOptionPane.showMessageDialog(null, s, "Message ",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void popup(String msg) {

        JOptionPane.showMessageDialog(null, msg, "Message ",
                JOptionPane.INFORMATION_MESSAGE);
    }

 

}
