/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject;

import dedheproject.exceptions.badfileexception;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Paris
 */
public class Fileopener {

    static boolean debug = true;

   public static File openfile() throws badfileexception {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path=selectedFile.getAbsolutePath();
            if (debug) {
                System.out.println(path);
            }
            String[] splitpath = path.split("\\.");

            if (debug) {
                for (String s : splitpath) {
                    System.out.println(s);
                }
            }
            
            String extension = splitpath[splitpath.length-1];
            if (debug) {
                System.out.println(extension);
            }
            if (extension.equals("xls")) {
                return (selectedFile);
            }
        }
        throw new badfileexception();

    }
}
