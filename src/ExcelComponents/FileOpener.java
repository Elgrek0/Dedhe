/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FilenameUtils;
import org.h2.util.Utils;

/**
 *
 * @author Paris
 */
public class FileOpener {

    static FileFilter excelfilter = new FileFilter() {

        @Override
        public boolean accept(File f) {
            String extension = FilenameUtils.getExtension(f.getAbsolutePath());
            return extension.equals("xls") || extension.equals("");// ||extension.equals("csv")
        }

        @Override
        public String getDescription() {
            return "Excel Files";
        }
    };

    public static File openfile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(excelfilter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return (selectedFile);
        }
        return null;

    }

    public static File[] openfiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(excelfilter);
        fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles().clone();
            return (selectedFiles);
        }
        return null;

    }
}
