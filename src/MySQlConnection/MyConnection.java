/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQlConnection;

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
public class MyConnection {

    public static Connection connect() throws CouldntConnectException {
        Connection conn = null;

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
            return (conn);

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

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public static void disablekeys(String arrayname, Connection conn) throws SQLException {

        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("LOCK TABLES " + arrayname + " WRITE;");
        rs = stmt.executeQuery("/*!40000 ALTER TABLE " + arrayname + " DISABLE KEYS */;");
        rs = stmt.executeQuery("SET foreign_key_checks = 0;");
    }

    public static ResultSet execute_simple_query(Connection conn, String query) throws SQLException {
        Statement stmt = (Statement) conn.createStatement();
        return (stmt.executeQuery("query"));
    }

    public static void enablekeys(String arrayname, Connection conn) throws SQLException {

        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("/*!40000 ALTER TABLE " + arrayname + " ENABLE KEYS */;");
        rs = stmt.executeQuery("SET foreign_key_checks = 1;");
        rs = stmt.executeQuery("UNLOCK TABLES;");

    }

}
