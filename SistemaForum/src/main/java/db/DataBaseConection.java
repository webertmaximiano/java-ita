package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConection {

	private static String driver = "org.postgresql.Driver";
    private static String urlconn = "jdbc:postgresql://localhost:5432/coursera";
    private static String user = "postgres";
    private static String password = "";

    private static Connection connection;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setConnection(Connection conn) {
        connection = conn;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(urlconn, user, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
