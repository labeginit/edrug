package model.dBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOCommon {
    private Statement statement;
    private ResultSet resultSet;

    public ResultSet retrieveSet(String queryString) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                statement = DBConnection.getConnection().createStatement();
                resultSet = statement.executeQuery(queryString);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }
}
