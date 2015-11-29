/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQlConnection;

import com.mysql.jdbc.PreparedStatement;
import dedheproject.exceptions.CouldntConnectException;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paris
 */
public class MySQLConnection extends DBConnection {

    @Override
    public void connect() throws CouldntConnectException {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        try {
            String pass = "";
            for (char c : MainWindow.PasswordBox.getPassword()) {
                pass = pass + c;
            }
            conn = DriverManager.getConnection("jdbc:mysql://" + MainWindow.AdressBox.getText()
                    + "/" + MainWindow.DBNameBox.getText() + "?"
                    + "user=" + MainWindow.UsernameBox.getText() + "&password="
                    + pass + "");
            dbname = MainWindow.DBNameBox.getText();
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