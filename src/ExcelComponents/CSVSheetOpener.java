/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class CSVSheetOpener extends SpreadSheetOpener {

    List<String[]> tempdata;
    public CSVSheetOpener(File f) throws FileNotFoundException {

        CSVReader reader = new CSVReader(new FileReader(f),';');
        
        try {
            tempdata = reader.readAll();
            max_column = 0;
            for (int i = 0; i < tempdata.size(); i++) {
                if (tempdata.get(i).length > max_column) {
                    max_column = tempdata.get(i).length;
                }

            }
            max_row = tempdata.size();

        } catch (IOException ex) {
            Logger.getLogger(CSVSheetOpener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getdata_at(int x, int y) {
        String[] row = tempdata.get(y);
        if (row.length <= x) {
            return "";
        } else {
            return row[x];
        }

    }
       

}
