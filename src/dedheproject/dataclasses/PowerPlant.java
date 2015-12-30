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
public class PowerPlant  implements DBItem{

    public int id;
    public String name;
    public Vector<Transformer> transformers = new Vector<>();

   public PowerPlant(int id, String name) {
        this.id = id;
        this.name = name;
    }

  public  void store() {
        DBConnection dbconn=null;
        try {
            dbconn = CachedData.conn;
            dbconn.disablekeys("Electrical_Plant");
            PreparedStatement pstmt = null;

            String query = " INSERT INTO Electrical_Plant VALUES (" + id + ",'" + name + "');\n";

            pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PowerPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dbconn != null) {
            try {
                dbconn.enablekeys("breaker_data");
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

