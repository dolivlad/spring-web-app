package kpp.pz5_webapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    //private static final String DB_URL = "?useSSL=false&serverTimezone=Europe/Warsaw";
    private static final String DB_URL = "jdbc:mysql://localhost/MonitorHierarchy";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "qwerty123";

    public static Connection createConnection() throws SQLException {
        Properties connInfo = new Properties();
        connInfo.put("user", DB_USER);
        connInfo.put("password", DB_PASSWORD);
        connInfo.put("useUnicode", "true");
        connInfo.put("characterEncoding", "utf-8");
        return DriverManager.getConnection(DB_URL, connInfo);
    }
}