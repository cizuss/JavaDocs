package ro.teamnet.zth.api.database;

import java.sql.*;

public class DBManager {

<<<<<<< HEAD
    public static final String CONNECTION_STRING = "jdbc:h2:"
            + DBProperties.IP + ":" + DBProperties.PORT;
=======
    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@"
            + DBProperties.IP + ":" + DBProperties.PORT + ":" + DBProperties.SID;
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4

    private static boolean isDriverRegistered = false;

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver() throws ClassNotFoundException {

        if (!isDriverRegistered) {
            Class.forName(DBProperties.DRIVER_CLASS);
            isDriverRegistered = true;
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        registerDriver();
<<<<<<< HEAD
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
=======
        return DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
    }

    public static boolean checkConnection(Connection connection) {
        boolean result = false;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select 1 from dual");
            if (rs.next()) {
                result = rs.getLong(1) == 1l;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
