/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import exceptions.NoFileSelectedException;
import exceptions.ErrorPopup;
import exceptions.NoSuchSheetException;
import exceptions.badfileexception;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Paris
 */
public class ExcelFileOpener {

    public static File open_excel_file() throws NoFileSelectedException, badfileexception {
        File excelfile = null;
        excelfile = Fileopener.openfile();

        if (excelfile == null) {
            throw new NoFileSelectedException();
        }
        if (!FilenameUtils.getExtension(excelfile.getPath()).equals("xls")) {
            throw new badfileexception("file type is " + FilenameUtils.getExtension(excelfile.getPath()));
        }

        return excelfile;
    }
}
