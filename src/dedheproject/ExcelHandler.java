/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject;

import com.aspose.cells.Cells;
import dedheproject.exceptions.NoSuchSheetException;
import com.aspose.cells.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class ExcelHandler {

    public static String[][] returnsheet(int sheet_number, int start_x, int start_y, int end_x, int end_y, File excelfile) throws FileNotFoundException, NoSuchSheetException {
        String toreturn[][] = new String[end_x - start_x][end_y - start_y];

        FileInputStream fstream = null;
        if (true) {
            fstream = new FileInputStream(excelfile);
        }
        try {
            Workbook workbook = new Workbook(fstream);

            if (workbook.getWorksheets().get(sheet_number) == null) {
                throw new NoSuchSheetException();
            }
            Cells cells=workbook.getWorksheets().get(sheet_number).getCells();
            for (int x = start_x; x < end_x; x++) {
                for (int y = start_y; y < end_y; y++) {
                    toreturn[x - start_x][y - start_y]
                            = cells.checkCell(x, y).getStringValue();
                    //System.out.println(cells.checkCell(x, y).getStringValueWithoutFormat()); - 42016.354166666664
                    //System.out.println(cells.checkCell(x, y).getStringValue()); - 1/12/2015 8:30
                    //System.out.println(cells.checkCell(x, y).toString()); -  Aspose.Cells.Cell [ B2; ValueType : IsDateTime; Value : 1/12/2015 8:30 ]
                   // System.out.println(cells.checkCell(x, y).getStringValue(0)); - 42016.354166666664
                }
            }
        } catch (Exception ex) {
           
        }
        return (toreturn);

    }
}
