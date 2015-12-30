/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.dataclasses;

import dedheproject.exceptions.TransformerParentNotFoundException;
import dedheproject.exceptions.PowerPlantParentNotFoundException;
import dedheproject.exceptions.NoActiveDbConnectionException;
import MySQlConnection.DBConnection;
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
public class CachedData {

    static boolean debug = false;
    static DBConnection conn;
    
    public static Vector<PowerPlant> powerplants;
    public static Vector<Transformer> transformers;
    public static Vector<Breaker> breakers;
    public static Semaphore mutex;

    static {
        conn = null;
        mutex = new Semaphore(1);
        powerplants=new Vector<>();
        transformers=new Vector<>();
        breakers=new Vector<>();
    }

    public static void loadall(DBConnection dbconn) throws InterruptedException {
        conn = dbconn;
        try {
            refresh_powerplants();
            if (debug) {
                System.out.println("powerplants done size :" + powerplants.size());
            }
            refresh_transformers();
            if (debug) {
                System.out.println("transformers done size :" + transformers.size());
            }
            refresh_breakers();
            if (debug) {
                System.out.println("breakers done size :" + breakers.size());
            }
        } catch (NoActiveDbConnectionException ex) {
            System.err.println("dbconn parameter was invalid");
        } catch (PowerPlantParentNotFoundException ex) {
            System.err.println("a powerplant is missing");
        } catch (TransformerParentNotFoundException ex) {
            System.err.println("a transformer is missing");
        }

    }

    public static void storeall(){
        
    }
    public static void refresh_powerplants() throws InterruptedException, NoActiveDbConnectionException {

        mutex.acquire();
        if (conn == null) {
            mutex.release();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            powerplants = new Vector<PowerPlant>();
            try {
                rs = conn.execute_simple_query("SELECT name,id FROM Electrical_Plant; ");
            } catch (SQLException ex) {

                mutex.release();
                System.err.println("get powerplants query failed");
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {
                    PowerPlant p = new PowerPlant(rs.getInt("id"), rs.getString("name"));
                    powerplants.add(p);
                }
            } catch (SQLException ex) {
                mutex.release();
                System.err.println("error at powerplant #" + powerplants.size());
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            mutex.release();
        }

    }

    public static void refresh_transformers() throws InterruptedException, NoActiveDbConnectionException, PowerPlantParentNotFoundException {

        mutex.acquire();
        if (conn == null) {
            mutex.release();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            transformers = new Vector<Transformer>();
            try {
                rs = conn.execute_simple_query("SELECT name,id,Electrical_Plant_ID FROM Transformer; ");
            } catch (SQLException ex) {

                mutex.release();
                System.err.println("get Transformers query failed");
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {

                    int parent_id = rs.getInt("Electrical_Plant_ID");
                    boolean done = false;
                    if (powerplants.size() >= parent_id - 1) {
                        if (powerplants.get(parent_id - 1).id == parent_id) {

                            PowerPlant parent = powerplants.get(parent_id - 1);

                            Transformer t = new Transformer(rs.getInt("id"), rs.getString("name"), parent);

                            transformers.add(t);
                            done = true;

                        }
                    }
                    if (done == false) {
                        for (PowerPlant parent : powerplants) {
                            if (parent.id == parent_id) {

                                Transformer t = new Transformer(rs.getInt("id"), rs.getString("name"), parent);

                                transformers.add(t);
                                done = true;
                                break;
                            }
                        }
                    }
                    if (done == false) {
                        mutex.release();
                        System.err.println("error at transformer #" + transformers.size());
                        throw new PowerPlantParentNotFoundException();
                    }
                }
            } catch (SQLException ex) {
                mutex.release();
                System.err.println("error at Transformer #" + transformers.size());
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            mutex.release();
        }

    }

    public static void refresh_breakers() throws InterruptedException, NoActiveDbConnectionException, TransformerParentNotFoundException {

        mutex.acquire();
        if (conn == null) {
            mutex.release();
            throw new NoActiveDbConnectionException();
        } else {
            ResultSet rs = null;
            breakers = new Vector<Breaker>();
            try {
                rs = conn.execute_simple_query("SELECT name,id,Transformer_ID FROM Breaker; ");
            } catch (SQLException ex) {

                mutex.release();
                System.err.println("get Breakers query failed");
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try {
                while (rs.next()) {
                    int parent_id = rs.getInt("Transformer_ID");
                    boolean done = false;
                    if (transformers.size() >= parent_id-1) {
                        if (transformers.get(parent_id-1).id == parent_id) {

                            Transformer parent = transformers.get(parent_id-1);

                            Breaker b = new Breaker(rs.getInt("id"), rs.getString("name"), parent);
                            breakers.add(b);
                            done = true;

                        }
                    }
                    if (done == false) {
                        for (Transformer parent : transformers) {
                            if (parent.id == parent_id) {
                                Breaker b = new Breaker(rs.getInt("id"), rs.getString("name"), parent);
                                breakers.add(b);
                                done = true;
                                break;
                            }
                        }
                    }
                    if (done == false) {
                        mutex.release();
                        System.err.println("error at breaker #" + transformers.size());
                        throw new TransformerParentNotFoundException();
                    }
                }
            } catch (SQLException ex) {
                mutex.release();
                System.err.println("error at breaker #" + transformers.size());
                Logger.getLogger(CachedData.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            mutex.release();
        }

    }
}
