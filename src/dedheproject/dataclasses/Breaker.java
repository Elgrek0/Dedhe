/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedheproject.dataclasses;

import MySQlConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class Breaker implements DBItem {

    public Transformer parent_transformer;

    public int id;
    public String name;

    public Breaker(int id, String name, Transformer parent_transformer) {
        this.id = id;
        this.name = name;
        this.parent_transformer = parent_transformer;
        parent_transformer.breakers.add(this);
    }
    @Override
    public void store() {
        DBConnection dbconn=null;
        try {
            dbconn = CachedData.conn;
            dbconn.disablekeys("Breaker");
            PreparedStatement pstmt = null;

            String query = " INSERT IGNORE INTO Breaker VALUES (" + id + ",'" + name + "',"+parent_transformer.id +");\n";

            pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PowerPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dbconn != null) {
            try {
                dbconn.enablekeys("Breaker");
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
