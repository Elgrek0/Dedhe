/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_connection;

import LoginFrame.DBinfo;
import LoginFrame.LoginInfo;
import exceptions.CouldntConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paris
 */
public abstract class DBConnection {

    public Connection conn = null;
    String dbname;

    public abstract void connect(LoginInfo login,DBinfo dbinfo) throws CouldntConnectException;

    public DefaultTableModel buildTableModel(ResultSet rs)
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

    public void disablekeys(String tablename) throws SQLException {

        Statement stmt = (Statement) conn.createStatement();
        stmt.executeQuery("LOCK TABLES " + tablename + " WRITE;");
        stmt.executeQuery("/*!40000 ALTER TABLE " + tablename + " DISABLE KEYS */;");
        stmt.executeQuery("SET foreign_key_checks = 0;");
    }

    public ResultSet execute_simple_query(String query) throws SQLException {
        Statement stmt = (PreparedStatement) conn.createStatement();
        stmt.execute(query);
        return (stmt.getResultSet());
    }

    public void enablekeys(String tablename) throws SQLException {

        Statement stmt = (Statement) conn.createStatement();
        stmt.executeQuery("/*!40000 ALTER TABLE " + tablename + " ENABLE KEYS */;");
        stmt.executeQuery("SET foreign_key_checks = 1;");
        stmt.executeQuery("UNLOCK TABLES;");

    }
}
