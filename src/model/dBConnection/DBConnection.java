package model.dBConnection;

import java.sql.*;

public class DBConnection {
   static String url= "jdbc:mysql://192.168.22.21:3306/edrugs_test";
   static String username = "ap";
   static String password="BNXVEvRY#9R^";

    private static DBConnection myDBConnection;
    public static Connection dbConnection;
    private Statement statement;
    private ResultSet resultSet;

    private DBConnection(){

    }

    public static DBConnection getInstance(){
        if (myDBConnection == null){
            myDBConnection = new DBConnection();
            try {
                dbConnection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return myDBConnection;
    }
    public static Connection getConnection(){

        return dbConnection;
    }

    public void disconnect(){

        try{
            if(dbConnection != null)
                dbConnection.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }catch (SQLException ex){
            System.out.println("Failed to disconnect!");
        }
    }


}
