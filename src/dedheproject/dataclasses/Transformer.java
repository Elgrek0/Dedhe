/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.dataclasses;

import MySQlConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class Transformer implements DBItem{

    public PowerPlant parent_powerplant;

    public int id;
    public String name;
    public Vector<Breaker> breakers=new Vector<>();

    public Transformer(int id, String name, PowerPlant parent_powerplant) {
        this.id = id;
        this.name = name;
        this.parent_powerplant = parent_powerplant;
        parent_powerplant.transformers.add(this);

    }
    public  void store() {
        DBConnection dbconn=null;
        try {
            dbconn = CachedData.conn;
            dbconn.disablekeys("Transformer");
            PreparedStatement pstmt = null;

            String query = " INSERT INTO Transformer VALUES (" + id + ",'" + name + "',"+parent_powerplant.id +");\n";

            pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PowerPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dbconn != null) {
            try {
                dbconn.enablekeys("Transformer");
            } catch (SQLException ex) {
                Logger.getLogger(PowerPlant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
}
