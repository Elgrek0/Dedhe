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
   public String data_x_y[][];
   public String sheetname;
   public int max_row;
   public int max_column;
   
    public ExcelSheetOpener(int sheet_number,File excelfile) throws FileNotFoundException, NoSuchSheetException {
        FileInputStream fstream = new FileInputStream(excelfile);

        try {
            Workbook workbook = new Workbook(fstream);

            if (workbook.getWorksheets().get(sheet_number) == null) {
                throw new NoSuchSheetException("sheet did not exist");
            }
            sheetname = "";
            ws = workbook.getWorksheets().get(sheet_number);
            sheetname = ws.getName();
            Cells cells = ws.getCells();
            
            
            max_column=cells.getMaxColumn();
            max_row=cells.getMaxRow();
            
            data_x_y = new String[max_column+1][max_row+1];//x,y

            for (int y = 0; y <= max_row; y++) {
                for (int x = 0; x <= max_column; x++) {
                    data_x_y[x][y] = cells.checkCell(y,x).getStringValue();
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
