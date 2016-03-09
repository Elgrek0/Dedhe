/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import javax.swing.JTextField;

/**
 *
 * @author Paris
 */
public abstract class SpreadSheetOpener {

    public int max_row;
    public int max_column;

    public abstract String getdata_at(int x, int y);

    public String[][] getdata(int start_x, int end_x, int start_y, int end_y) {

        String[][] data = new String[end_y - start_y + 2][end_x - start_x + 2];

        for (int x = start_x; x <= end_x; x++) {
            for (int y = start_y; y <= end_y; y++) {
                try {
                    data[y - start_y][x - start_x] = getdata_at(x, y);
                } catch (Exception e) {
                          data[y - start_y][x - start_x]="";
                }
            }
        }
        return data;
    }

    public String[] getrow(int start_x, int end_x, int row) {
        String[] data = new String[end_x - start_x + 1];

        for (int x = start_x; x <= end_x; x++) {
            data[x - start_x] = getdata_at(x, row);

        }
        return data;
    }

    public String[] getcolumn(int column, int start_y, int end_y) {
        String[] data = new String[end_y - start_y + 1];

        for (int y = start_y; y <= end_y; y++) {
            data[y - start_y] = getdata_at(column, y);

        }
        return data;
    }


}
