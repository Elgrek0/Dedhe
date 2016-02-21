/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_connection;

import Gui.MainMenu;
import LoginFrame.DBinfo;
import exceptions.CouldntConnectException;
import java.sql.SQLException;
import LoginFrame.LoginInfo;
import java.sql.DriverManager;

/**
 *
 * @author Paris
 */
public class MySQLConnection extends DBConnection {

    @Override
    public void connect(LoginInfo login,DBinfo dbinfo) throws CouldntConnectException {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        try {
            String pass = login.password;
            conn = DriverManager.getConnection("jdbc:mysql://" + login.username
                    + "/" + dbinfo.DB_name + "?"
                    + "user=" + login.username + "&password="
                    + pass + "");
            dbname = dbinfo.DB_name;
            conn.prepareStatement("use " + dbname + ";").execute();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            throw new CouldntConnectException("SQLException: " + ex.getMessage()
                    + " SQLState: " + ex.getSQLState()
                    + " VendorError: " + ex.getErrorCode());
        }

    }

}
