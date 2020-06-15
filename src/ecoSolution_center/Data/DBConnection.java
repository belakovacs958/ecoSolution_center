package ecoSolution_center.Data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connect;
    private static final DBConnection instance = new DBConnection();




    private DBConnection() {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            this.connect = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=Ecosolutions", "sa", "123456");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */

    public static Connection getConnect() {
        return connect;
    }

    public static DBConnection getInstance() {
        return instance;
    }
}