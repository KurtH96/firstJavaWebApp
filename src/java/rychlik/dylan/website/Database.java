package rychlik.dylan.website;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_ADDRESS = "jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase";
    private static final String DB_USER = "uzaqleuw_root";
    private static final String DB_PASSWORD = "3Hotdogs!";
    private static final String LOG_NAME = "Database Connection";

    private static void log(Exception exception) {
        Logger.getLogger(LOG_NAME).log(Level.SEVERE, exception.getMessage(), exception);
    }

    protected static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            log(exception);
            return null;
        }
    }

    private static ResultSet query(String statement) {
        try {
            return getConnection().createStatement().executeQuery(statement);
        } catch (SQLException exception) {
            log(exception);
            return null;
        }
    }

    private static ResultSet query(String statement, String[] values) {
        try {
            PreparedStatement preppedStatement = getConnection().prepareStatement(statement);
            for (int value = 0; value < values.length; value++) {
                preppedStatement.setString(value+1, values[value]);
            }
            return preppedStatement.executeQuery();
        } catch (SQLException exception) {
            log(exception);
            return null;
        }
    }

    private static int update(String statement, String[] values) {
        try {
            PreparedStatement preppedStatement = getConnection().prepareStatement(statement);
            for (int value = 0; value < values.length; value++) {
                preppedStatement.setString(value+1, values[value]);
            }
            return preppedStatement.executeUpdate();
        } catch (SQLException exception) {
            log(exception);
            return -1;
        }
    }

    protected static ResultSet selectAll(String table) {
        return query("SELECT * FROM " + table);
    }

    protected static ResultSet selectWhere(String table, String[] columns, String[] values) {
        StringBuilder builder = new StringBuilder("SELECT * FROM " + table + " WHERE ");
        
        for (int column = 0; column < columns.length; column++) {
            builder.append(columns[column] + "=?");
            if (column == columns.length-1) {
                builder.append(", ");
            }
        }
        
        return query(builder.toString(), values);
    }

    protected static int add(String table, String[] columns, String[] values) {
        StringBuilder builder = new StringBuilder("INSERT INTO " + table + "(");
        
        for (int column = 0; column < columns.length; column++) {
            builder.append(columns[column]);
            if (column == columns.length-1) {
                builder.append(", ");
            } else {
                builder.append(") values(");
            }
        }

        for (int column = 0; column < columns.length; column++) {
            builder.append("?");
            if (column == columns.length-1) {
                builder.append(", ");
            } else {
                builder.append(")");
            }
        }        

        return update(builder.toString(), values);
    }

    protected static int update(String table, String[] columns, String[] values) {
        StringBuilder builder = new StringBuilder("UPDATE " + table + "SET ");
        
        for (int column = 0; column < columns.length; column++) {
            if (column != columns.length-1) {
                builder.append(columns[column] + "=?, ");
            } else {
                builder.append("WHERE " + columns[column] + "=?");
            }
        }

        return update(builder.toString(), values);
    }


}