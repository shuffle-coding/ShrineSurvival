package erik.wiesi.view.playSolo.handler;

import java.sql.*;

public class DatabaseConnection {

    private static Connection con = null;

    public DatabaseConnection() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "shrinesurvival";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
