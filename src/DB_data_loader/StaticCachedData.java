/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader;

import DB_connection.DBConnection;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class StaticCachedData {

    static {
        conn = null;
        mutex = new Semaphore(1);
        db_powerplants = new Vector<>();
        db_transformers = new Vector<>();
        db_breakers = new Vector<>();
    }

    protected static DBConnection conn;

    public static Vector<PowerPlant> db_powerplants;
    public static Vector<Transformer> db_transformers;
    public static Vector<Breaker> db_breakers;
    protected static Semaphore mutex;

   static  Vector<ActionListener> changelisteners = new Vector<ActionListener>();

    public static void addChangeListener(ActionListener changelistener) {
        changelisteners.add(changelistener);

    }

    public static void cacheupdateevent() {
        for (int i = 0; i < changelisteners.size(); i++) {
            changelisteners.get(i).actionPerformed(null);
        }
    }

    public static void lock() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void unlock() {

        mutex.release();
    }
}
