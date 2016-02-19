/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_connection;

import MySQlConnection.MainWindow;
import data_classes.LoginInfo;
import dedheproject.exceptions.CouldntConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paris
 */
public class H2MyConnection extends DBConnection {

    @Override
    public void connect(LoginInfo login) throws CouldntConnectException {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String pass =login.password;
            conn = DriverManager.getConnection("jdbc:h2:database./DB", login.username, pass);
            dbname= MainWindow.DBNameBox.getText();
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
   
    @Override
    public void disablekeys(String tablename) throws SQLException {
        conn.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE;").execute();
    }

    @Override
    public ResultSet execute_simple_query( String query) throws SQLException {
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query);
        pstmt.addBatch();
        pstmt.execute();
        return (pstmt.getResultSet());
    }

    @Override
    public void enablekeys(String tablename) throws SQLException {
        conn.prepareStatement("SET REFERENTIAL_INTEGRITY TRUE;").execute();
    }

}
