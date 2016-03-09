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
import panels.ErrorPopup;

/**
 *
 * @author Paris
 */
public class ExcelSheetOpener extends SpreadSheetOpener {

    public Worksheet ws = null;
    public String sheetname = "";
    Cells cells = null;

    public ExcelSheetOpener(int sheet_number, File excelfile) throws NoSuchSheetException {
        Workbook workbook ;
        try {
            FileInputStream fstream = new FileInputStream(excelfile);

            workbook = new Workbook(fstream);
        } catch (Exception ex) {
            ErrorPopup.popup("Couldn't open Spreadsheet");
            return;
        }

        try {
            ws = workbook.getWorksheets().get(sheet_number);
        } catch (IndexOutOfBoundsException ex) {
            throw new NoSuchSheetException("sheet did not exist");
        }
        sheetname = ws.getName();
        cells = ws.getCells();

        max_column = cells.getMaxColumn();
        max_row = cells.getMaxRow();

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

    public String getsheetname() {
        return sheetname;
    }

}
