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
public class ExcelSheetOpener extends SpreadSheetOpener {

    public Worksheet ws;

    public String sheetname;
    Cells cells;

    public ExcelSheetOpener(int sheet_number, File excelfile) throws FileNotFoundException, NoSuchSheetException {
        FileInputStream fstream = new FileInputStream(excelfile);

        try {
            Workbook workbook = new Workbook(fstream);

            if (workbook.getWorksheets().get(sheet_number) == null) {
                throw new NoSuchSheetException("sheet did not exist");
            }
            sheetname = "";
            ws = workbook.getWorksheets().get(sheet_number);
            sheetname = ws.getName();
            cells = ws.getCells();

            max_column = cells.getMaxColumn();
            max_row = cells.getMaxRow();

        } catch (Exception ex) {

        }

    }

    @Override
    public String getdata_at(int x, int y) {
        if (cells != null) {
            if (cells.checkCell(y, x) != null) {
                return cells.checkCell(y, x).getStringValue();
            }
        }
        return "";

    }

}
