/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader;

import DB_connection.DBConnection;
import static DB_data_loader.StaticCachedData.conn;
import static DB_data_loader.StaticCachedData.db_breakers;
import static DB_data_loader.StaticCachedData.db_powerplants;
import static DB_data_loader.StaticCachedData.db_transformers;
import DB_data_loader.data_classes.Breaker;
import DB_data_loader.data_classes.PowerPlant;
import DB_data_loader.data_classes.Transformer;
import exceptions.NoActiveDbConnectionException;
import exceptions.PowerPlantParentNotFoundException;
import exceptions.TransformerParentNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class LoadDataFromDB {

    static boolean debug = false;

    public static void initialize(DBConnection dbconn) throws NoActiveDbConnectionException {
        if (dbconn != null) {
            StaticCachedData.conn = dbconn;
        } else {
            throw new NoActiveDbConnectionException("connection was null");
        }
    }

    public static void loadall() throws InterruptedException {

        try {
            refresh_powerplants();
            if (debug) {
                System.out.println("powerplants done size :" + db_powerplants.size());
            }
            refresh_transformers();
            if (debug) {
                System.out.println("transformers done size :" + db_transformers.size());
            }
            refresh_breakers();
            if (debug) {
                System.out.println("breakers done size :" + db_breakers.size());
            }
        } catch (NoActiveDbConnectionException ex) {
            System.err.println("dbconn parameter was invalid");
        } catch (PowerPlantParentNotFoundException ex) {
            System.err.println("a powerplant is missing");
        } catch (TransformerParentNotFoundException ex) {
            System.err.println("a transformer is missing");
        }

    }

    public static void storeall() {

    }

    public static void refresh_powerplants() throws InterruptedException, NoActiveDbConnectionException {

        StaticCachedData.lock();
        if (conn == null) {
            StaticCachedData.unlock();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            db_powerplants = new Vector<PowerPlant>();
            try {
                rs = conn.execute_simple_query("SELECT name,id FROM Electrical_Plant; ");
            } catch (SQLException ex) {

                StaticCachedData.unlock();
                System.err.println("get powerplants query failed");
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {
                    PowerPlant p = new PowerPlant(rs.getInt("id"), rs.getString("name"));
                    db_powerplants.add(p);
                }
            } catch (SQLException ex) {
                StaticCachedData.unlock();
                System.err.println("error at powerplant #" + db_powerplants.size());
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            StaticCachedData.unlock();
        }

    }

    public static void refresh_transformers() throws InterruptedException, NoActiveDbConnectionException, PowerPlantParentNotFoundException {

        StaticCachedData.lock();
        if (StaticCachedData.conn == null) {
            StaticCachedData.unlock();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            db_transformers = new Vector<Transformer>();
            try {
                rs = conn.execute_simple_query("SELECT name,id,Electrical_Plant_ID FROM Transformer; ");
            } catch (SQLException ex) {

                StaticCachedData.unlock();
                System.err.println("get Transformers query failed");
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {

                    int parent_id = rs.getInt("Electrical_Plant_ID");
                    boolean done = false;
                    if (db_powerplants.size() >= parent_id - 1) {
                        if (db_powerplants.get(parent_id - 1).id == parent_id) {

                            PowerPlant parent = db_powerplants.get(parent_id - 1);

                            Transformer t = new Transformer(rs.getInt("id"), rs.getString("name"), parent);

                            db_transformers.add(t);
                            done = true;

                        }
                    }
                    if (done == false) {
                        for (PowerPlant parent : db_powerplants) {
                            if (parent.id == parent_id) {

                                Transformer t = new Transformer(rs.getInt("id"), rs.getString("name"), parent);

                                db_transformers.add(t);
                                done = true;
                                break;
                            }
                        }
                    }
                    if (done == false) {
                        StaticCachedData.unlock();
                        System.err.println("error at transformer #" + db_transformers.size());
                        throw new PowerPlantParentNotFoundException();
                    }
                }
            } catch (SQLException ex) {
                StaticCachedData.unlock();
                System.err.println("error at Transformer #" + db_transformers.size());
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            StaticCachedData.unlock();
        }

    }

    public static void refresh_breakers() throws InterruptedException, NoActiveDbConnectionException, TransformerParentNotFoundException {

        StaticCachedData.lock();
        if (conn == null) {
            StaticCachedData.unlock();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            db_breakers = new Vector<Breaker>();
            try {
                rs = conn.execute_simple_query("SELECT name,id,Transformer_ID FROM Breaker; ");
            } catch (SQLException ex) {

                StaticCachedData.unlock();
                System.err.println("get Breakers query failed");
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {
                    int parent_id = rs.getInt("Transformer_ID");
                    boolean done = false;
                    if (db_transformers.size() >= parent_id - 1) {
                        if (db_transformers.get(parent_id - 1).id == parent_id) {

                            Transformer parent = db_transformers.get(parent_id - 1);

                            Breaker b = new Breaker(rs.getInt("id"), rs.getString("name"), parent);
                            db_breakers.add(b);
                            done = true;

                        }
                    }
                    if (done == false) {
                        for (Transformer parent : db_transformers) {
                            if (parent.id == parent_id) {
                                Breaker b = new Breaker(rs.getInt("id"), rs.getString("name"), parent);
                                db_breakers.add(b);
                                done = true;
                                break;
                            }
                        }
                    }
                    if (done == false) {
                        StaticCachedData.unlock();
                        System.err.println("error at breaker #" + db_transformers.size());
                        throw new TransformerParentNotFoundException();
                    }
                }
            } catch (SQLException ex) {
                StaticCachedData.unlock();
                System.err.println("error at breaker #" + db_transformers.size());
                Logger.getLogger(StaticCachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            StaticCachedData.unlock();
        }

    }

}
