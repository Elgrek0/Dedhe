/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader;

import dedheproject.exceptions.TransformerParentNotFoundException;
import dedheproject.exceptions.PowerPlantParentNotFoundException;
import dedheproject.exceptions.NoActiveDbConnectionException;
import DB_connection.DBConnection;
import data_classes.Breaker;
import data_classes.PowerPlant;
import data_classes.Transformer;
import java.sql.ResultSet;
import java.sql.SQLException;
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
