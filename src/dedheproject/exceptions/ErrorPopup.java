/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.exceptions;

import MySQlConnection.MainWindow;
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
        MainWindow.ErrorArea.append(ex.toString() + "\n");
        JOptionPane.showMessageDialog(null, ex.toString(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
