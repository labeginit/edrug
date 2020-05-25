package model.dBConnection;

import java.sql.*;

public class DBConnection {

   public static String url= "jdbc:mysql://ro03.beginit.se:23306/edrugs_test?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";
   public static String username = "ap";
   public static String password="BNXVEvRY#9R^";

    private static DBConnection myDBConnection;
    public static Connection dbConnection;
    private Statement statement;
    private ResultSet resultSet;

    private DBConnection() {

    }

    public static DBConnection getInstance() {
        if (myDBConnection == null) {
            myDBConnection = new DBConnection();
            try {
                dbConnection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection to DB established");
            } catch (SQLException e) {
                System.out.println("Failed to connect to DB");
                e.printStackTrace();
            }
        }

        return myDBConnection;
    }

    public static Connection getConnection() {

        return dbConnection;
    }

    public void disconnect() {

        try {
            System.out.println("Disconnected from DB");
            if (dbConnection != null)
                dbConnection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Failed to disconnect from DB!");
        }
    }


}
