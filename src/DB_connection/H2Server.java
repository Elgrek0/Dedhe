/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_connection;

import panels.ErrorPopup;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Paris
 */
public class H2Server {

    Connection conn;
    public boolean readonly;

    public H2Server(String s, boolean readonly) {

        JdbcDataSource ds = new JdbcDataSource();
        this.readonly=readonly;
        if (readonly) {
            try {
                ds.setURL("jdbc:h2:mem:database./DB;"
                        + "INIT=RUNSCRIPT FROM './dbcreate.sql'\\;");
                ds.setUser(s);
                ds.getConnection();

            } catch (Exception e) {
                ErrorPopup.popup(e);
            }
        }
        else{
           try {
                ds.setURL("jdbc:h2:database./DB;"
                        + "INIT=RUNSCRIPT FROM './dbcreate.sql'\\;");
                ds.setUser(s);
                ds.getConnection();

            } catch (Exception e) {
                ErrorPopup.popup(e);
            } 
        }

    }

}
