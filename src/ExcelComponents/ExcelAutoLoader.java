/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelComponents;

import DB_connection.FixValues;
import DB_data_loader.LoadDataFromDB;
import DB_data_loader.StaticCachedData;
import DB_data_loader.StoreDatatoDB;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import Gui.AddNewElectricalItems;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import exceptions.BadDateInputException;
import exceptions.BadTimeInputException;
import exceptions.CouldntStoreDataException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import panels.ErrorPopup;
import panels.Popup;

/**
 *
 * @author Paris
 */
public class ExcelAutoLoader {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    Vector<Worksheet> worksheets = new Vector<Worksheet>();
    Vector<String> sheetnames;
    String plant_name;
    Vector<String> transformernames;
    Vector<Vector<String>> breakernames;

    PowerPlant p;

    int breakercount;
    int transformercount;
    int total;
    JProgressBar bar = null;

    private Vector<String> getallbreakernames(int x, Cells cells) {
        Vector<String> localbreakernames = new Vector<>();

        int y = 5;
        int count = 0;
        while (true) {
            try {
                String temp = cells.get(y + count, x + 1).getStringValue();
                if (temp.equals("")) {
                    break;
                } else {
                    localbreakernames.add(temp);
                }
            } catch (IllegalArgumentException ex) {
                break;
            }
            count++;
        }
        return localbreakernames;
    }

    private void getallTransformernames(Cells cells) {

        int y = 3;
        int x = 4;
        int x_add = 3;

        int count = 0;

        transformernames = new Vector<>();
        breakernames = new Vector<>();
        while (true) {
            try {
                String temp = cells.get(y, x + x_add * count).getStringValue();
                if (temp.equals("")) {
                    break;
                }
                transformernames.add(temp);
                breakernames.add(getallbreakernames(x + x_add * count, cells));
            } catch (IllegalArgumentException ex) {
                break;
            }
            count++;
        }
    }

    public ExcelAutoLoader(File excelfile, JProgressBar bar) {
        this.bar = bar;
        
        System.out.println("Reading File "+excelfile.getName());
        Workbook workbook;
        try {
            FileInputStream fstream = new FileInputStream(excelfile);

            workbook = new Workbook(fstream);
        } catch (Exception ex) {
            ErrorPopup.popup("Couldn't open File "+excelfile.getName());
            return;
        }
        int loc = 0;
        while (true) {
            try {
                worksheets.add(workbook.getWorksheets().get(loc++));
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
        }
        if (worksheets.size() < 2) {
            ErrorPopup.popup("Invalid Auto Excel File : too few sheets");
            return;
        }

        String plant_name_unprocesed = worksheets.get(1).getCells().get(2, 0).getStringValue();
        plant_name = plant_name_unprocesed.split(" ")[1];

        sheetnames = new Vector< String>();
        for (int i = 0; i < worksheets.size(); i++) {
            sheetnames.add(worksheets.get(i).getName());
        }
        if (!sheetnames.get(0).matches(".*ΜΣ.*")) {
            ErrorPopup.popup("Invalid Auto Excel File : first sheet name is invalid");
            return;
        }

        getallTransformernames(worksheets.get(0).getCells());

        bar.setValue(10);
        bar.update(bar.getGraphics());
        generate_electrical_items();

        bar.setValue(20);
        bar.update(bar.getGraphics());
        transformercount = breakernames.size();

        breakercount = 0;
        for (int i = 0; i < breakernames.size(); i++) {
            breakercount += breakernames.get(i).size();
        }
        total = breakercount + transformercount;

        loadBreakerdata();

        loadTransformerdata();
    }

    public ExcelAutoLoader(File excelfile) {
        Workbook workbook;
        try {
            FileInputStream fstream = new FileInputStream(excelfile);

            workbook = new Workbook(fstream);
        } catch (Exception ex) {
            ErrorPopup.popup("Couldn't open File "+excelfile.getName());
            return;
        }
        int loc = 0;
        while (true) {
            try {
                worksheets.add(workbook.getWorksheets().get(loc++));
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
        }
        if (worksheets.size() < 2) {
            ErrorPopup.popup("Invalid Auto Excel File : too few sheets");
            return;
        }

        String plant_name_unprocesed = worksheets.get(1).getCells().get(2, 0).getStringValue();
        plant_name = plant_name_unprocesed.split(" ")[1];

        sheetnames = new Vector< String>();
        for (int i = 0; i < worksheets.size(); i++) {
            sheetnames.add(worksheets.get(i).getName());
        }
        if (!sheetnames.get(0).matches(".*ΜΣ.*")) {
            ErrorPopup.popup("Invalid Auto Excel File : first sheet name is invalid");
            return;
        }

        getallTransformernames(worksheets.get(0).getCells());

        generate_electrical_items();

        loadBreakerdata();

        loadTransformerdata();
    }

    private void loadBreakerdata() {

        int breakercounter=0;
        try {
            for (int i = 0; i < p.transformers.size(); i++) {
                Transformer t = p.transformers.elementAt(i);
                for (int j = 0; j < t.breakers.size(); j++) {

                    Breaker b = t.breakers.elementAt(j);
                    int errors = 0;

                    for (int k = 0; k < sheetnames.size(); k++) {
                        if (sheetnames.get(k).split("-").length > 1) {
                            if (b.name.split("-")[1].equals(sheetnames.get(k).split("-")[1])) {

                                Cells cells = worksheets.get(k).getCells();
                                String data;
                                for (int l = 1; l < cells.getMaxRow(); l++) {
                                    try {
                                        String date = cells.get(l, 1).getStringValue();
                                        String value = cells.get(l, 2).getStringValue();
                                        if (!date.equals("") && !value.equals("")) {
                                            data = "'" + FixValues.reversedate(date, '/', ':')
                                                    + "'" + "," + value.replace(',', '.')
                                                    + "," + b.id;

                                            StoreDatatoDB.store("Breaker_data", data);
                                        } else {
                                            errors++;
                                        }
                                    } catch (BadDateInputException ex) {
                                        errors++;
                                    } catch (BadTimeInputException ex) {
                                        errors++;
                                    } catch (CouldntStoreDataException ex) {
                                        errors++;
                                    }

                                }
                                System.out.println("query of breaker " + b.name + " completed sucesfully");
                                System.out.println("errors #" + errors);
                                System.out.println("sucesses #" + (cells.getMaxRow() - errors));

                                break;
                            }
                        }
                    }

                    if (bar != null) {
                        breakercounter++;
                        bar.setValue((int) (20 + breakercounter / (float) total * 80));
                        bar.update(bar.getGraphics());
                    }
                }
            }
        } catch (Exception ex) {
            ErrorPopup.popup("Failed to pass data automaticaly to breakers");
        }
    }

    private void loadTransformerdata() {
        try {
            for (int i = 0; i < p.transformers.size(); i++) {
                Transformer t = p.transformers.elementAt(i);

                int errors = 0;

                for (int k = 0; k < sheetnames.size(); k++) {
                    if (sheetnames.get(k).split("ΜΣ").length > 0) {
                        if (t.name.split(" ")[t.name.split(" ").length - 1].equals(sheetnames.get(k).split("ΜΣ")[sheetnames.get(k).split("ΜΣ").length - 1])) {

                            Cells cells = worksheets.get(k).getCells();
                            String data;
                            for (int l = 1; l < cells.getMaxRow(); l++) {
                                try {
                                    String date = cells.get(l, 1).getStringValue();
                                    String value = cells.get(l, 2).getStringValue();
                                    if (!date.equals("") && !value.equals("")) {
                                        data = "'" + FixValues.reversedate(date, '/', ':')
                                                + "'" + "," + value.replace(',', '.')
                                                + "," + t.id;

                                        StoreDatatoDB.store("Transformer_data", data);
                                    } else {
                                        errors++;
                                    }
                                } catch (BadDateInputException ex) {
                                    errors++;
                                } catch (BadTimeInputException ex) {
                                    errors++;
                                } catch (CouldntStoreDataException ex) {
                                    errors++;
                                }

                            }
                            System.out.println("query of transformer " + t.name + " completed sucesfully");
                            System.out.println("errors #" + errors);
                            System.out.println("sucesses #" + (cells.getMaxRow() - errors));

                            break;
                        }
                    }

                }
                if (bar != null) {
                    bar.setValue((int) (20 + (breakercount + i) / (float) total * 80));
                    bar.update(bar.getGraphics());
                }
            }
        } catch (Exception ex) {
            ErrorPopup.popup("Failed to pass data automaticaly to transformers");
        }
    }

    private void generate_electrical_items() {

        p = null;
        for (PowerPlant temp : StaticCachedData.db_powerplants) {
            if (temp.name.equals(plant_name)) {
                p = temp;
                break;
            }
        }
        if (p == null) {
            if (StaticCachedData.db_powerplants.size() > 0) {
                p = new PowerPlant(StaticCachedData.db_powerplants.lastElement().id + 1, plant_name);
            } else {
                p = new PowerPlant(1, plant_name);
            }
            StaticCachedData.db_powerplants.add(p);
            try {
                p.store();
            } catch (CouldntStoreDataException ex) {
                Logger.getLogger(AddNewElectricalItems.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < transformernames.size(); i++) {
            Transformer t = null;
            for (Transformer temp : p.transformers) {
                if (temp.name.equals(transformernames.get(i))) {
                    t = temp;
                    break;
                }
            }
            if (t == null) {
                if (StaticCachedData.db_transformers.size()
                        > 0) {
                    t = new Transformer(StaticCachedData.db_transformers.lastElement().id + 1, transformernames.get(i), p);
                } else {
                    t = new Transformer(1, transformernames.get(i), p);
                }

                StaticCachedData.db_transformers.add(t);

                try {
                    t.store();
                } catch (CouldntStoreDataException ex) {
                    Logger.getLogger(AddNewElectricalItems.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (int j = 0; j < breakernames.get(i).size(); j++) {
                Breaker b = null;

                for (Breaker temp : t.breakers) {
                    if (temp.name.equals(breakernames.get(i).get(j))) {
                        b = temp;
                        break;
                    }
                }

                if (b == null) {
                    if (StaticCachedData.db_breakers.size() > 0) {
                        b = new Breaker(StaticCachedData.db_breakers.lastElement().id + 1, breakernames.get(i).get(j), t);
                    } else {
                        b = new Breaker(1, breakernames.get(i).get(j), t);
                    }

                    StaticCachedData.db_breakers.add(b);
                    try {
                        b.store();

                    } catch (CouldntStoreDataException ex) {
                        Logger.getLogger(AddNewElectricalItems.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        try {
            LoadDataFromDB.loadall();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExcelAutoLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
