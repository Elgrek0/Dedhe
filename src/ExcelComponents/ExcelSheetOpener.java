/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import com.aspose.cells.Cells;
import exceptions.NoSuchSheetException;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Paris
 */
public class ExcelSheetOpener {

   public Worksheet ws;
   public String data[][];
   public String sheetname;

    public ExcelSheetOpener(int sheet_number, int start_x, int start_y, int columns_to_read, int rows_to_read, File excelfile) throws FileNotFoundException, NoSuchSheetException {
        FileInputStream fstream = new FileInputStream(excelfile);

        try {
            Workbook workbook = new Workbook(fstream);

            if (workbook.getWorksheets().get(sheet_number) == null) {
                throw new NoSuchSheetException("sheet did not exist");
            }

            sheetname = "";
            data = new String[rows_to_read][columns_to_read];//y , x

            ws = workbook.getWorksheets().get(sheet_number);
            sheetname = ws.getName();
            Cells cells = ws.getCells();

            for (int y = 0; y < rows_to_read; y++) {
                for (int x = 0; x < columns_to_read; x++) {
                    data[y][x] = cells.checkCell(y + start_y, x + start_x).getStringValue();
                    //System.out.println(cells.checkCell(x, y).getStringValueWithoutFormat()); - 42016.354166666664
                    //System.out.println(cells.checkCell(x, y).getStringValue()); - 1/12/2015 8:30
                    //System.out.println(cells.checkCell(x, y).toString()); -  Aspose.Cells.Cell [ B2; ValueType : IsDateTime; Value : 1/12/2015 8:30 ]
                    // System.out.println(cells.checkCell(x, y).getStringValue(0)); - 42016.354166666664
                }
            }
        } catch (Exception ex) {

        }

    }
}
