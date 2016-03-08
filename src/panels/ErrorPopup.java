/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import Gui.MainMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Paris
 */
public class ErrorPopup {

    public ErrorPopup(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString(), "Error",
                JOptionPane.ERROR_MESSAGE);

    }

    public static void popup(Exception ex) {
        if (MainMenu.ErrorArea != null) {
            MainMenu.ErrorArea.append(ex.toString() + "\n");
        }
        JOptionPane.showMessageDialog(null, ex.toString(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
        public static void popup(String msg) {
        if (MainMenu.ErrorArea != null) {
            MainMenu.ErrorArea.append(msg + "\n");
        }
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
