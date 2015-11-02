/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author Paris
 */
public class ShowErrorPopup {

    public static void popup(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
