package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-2NFEM03:1433;databaseName=cnm;encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String password = "ad123456";

            c = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
    public static void closeConnection(Connection c) {
        try {
            if (c!=null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
