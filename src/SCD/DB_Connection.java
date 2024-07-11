package SCD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {

            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true;"
                    ,"sa", "Mohid123"
            );
        }
        return connection;
    }


    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

public static void main(String[] args) throws SQLException {
    DB_Connection myDb = new DB_Connection();
    Connection  conn = DB_Connection.getConnection();

}

}
