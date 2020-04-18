package model.dBConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCommon {
    private ResultSet resultSet;

    public ResultSet retrieveSet(String queryString, String... params) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                for (int i = 0; i < params.length; i++) {
                    prepStmt.setString(i+1, params[i]);
                }
                resultSet = prepStmt.executeQuery();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public ResultSet retrieveSet(String queryString) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                resultSet = prepStmt.executeQuery();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public int insertUser(String queryString, String ssn, int type, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        int linesAdded = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setString(1, ssn);
                prepStmt.setInt(2, type);
                prepStmt.setString(3, firstName);
                prepStmt.setString(4, lastName);
                prepStmt.setDate(5, birthDate);
                prepStmt.setString(6, zipCode);
                prepStmt.setString(7, address);
                prepStmt.setString(8, email);
                prepStmt.setString(9, phoneNumber);
                prepStmt.setString(10, password);

                linesAdded = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAdded;
    }

    public int updateUser(String queryString, String ssn, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber) {
        int linesAdded = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setString(1, firstName);
                prepStmt.setString(2, lastName);
                prepStmt.setDate(3, birthDate);
                prepStmt.setString(4, zipCode);
                prepStmt.setString(5, address);
                prepStmt.setString(6, email);
                prepStmt.setString(7, phoneNumber);
                prepStmt.setString(8, ssn);
                linesAdded = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAdded;
    }

    //DELETE FROM `edrugs_test`.`User` WHERE (`ssn` = '2');
}
