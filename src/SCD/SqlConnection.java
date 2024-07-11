package SCD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

    public static void main(String[] args) {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=SwingTest";

            String user = "sa";
            String pass = "Mohid123";

            try (Connection conn = DriverManager.getConnection(dbURL, user, pass)) {
                if (conn != null) {
                    DatabaseMetaData dm = conn.getMetaData();
                    System.out.println("Driver name: " + dm.getDriverName());
                    System.out.println("Driver version: " + dm.getDriverVersion());
                    System.out.println("Product name: " + dm.getDatabaseProductName());
                    System.out.println("Product version: " + dm.getDatabaseProductVersion());
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC");
        }
    }
}
