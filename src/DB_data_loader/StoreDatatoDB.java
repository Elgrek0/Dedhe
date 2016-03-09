/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_data_loader;

import exceptions.CouldntStoreDataException;
import DB_connection.DBConnection;
import static DB_data_loader.StaticCachedData.conn;
import DB_data_loader.data_classes.PowerPlant;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import panels.ErrorPopup;

/**
 *
 * @author Paris
 */
public class StoreDatatoDB {
    
    public static void store(String arrayname, String values) throws CouldntStoreDataException {
        
        DBConnection dbconn = conn;
        try {
            dbconn = StaticCachedData.conn;
            dbconn.disablekeys(arrayname);
            PreparedStatement pstmt = null;
            
            String query = " MERGE INTO " + arrayname + " VALUES(" + values + ");\n";
            
            pstmt = (PreparedStatement) dbconn.conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            try {
                dbconn.enablekeys(arrayname);
            } catch (SQLException ex1) {
                Logger.getLogger(StoreDatatoDB.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new CouldntStoreDataException();
        }
        try {
            dbconn.enablekeys(arrayname);
        } catch (SQLException ex) {
            ErrorPopup.popup("Couldnt enable table keys writing is now unsafe");
        }
        
    }
}
