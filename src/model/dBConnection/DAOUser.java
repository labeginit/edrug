package model.dBConnection;

import model.Admin;
import model.Doctor;
import model.Patient;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOUser {
    private Statement statement;
    private ResultSet resultSet;
    private List<User> userList = new ArrayList<>();
    DAOCommon common = new DAOCommon();

    private long sSN;
    private int userType;  //   1 = Patient, 2 = Doctor, 3 = Admin
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int zipCode;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;

    public List<User> retrieveUserList(String query) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        sSN = resultSet.getLong("ssn");
                        userType = resultSet.getInt("type");
                        firstName = resultSet.getString("first_name");
                        lastName = resultSet.getString("last_name");
                        birthDate = resultSet.getDate("birth_date");
                        zipCode = resultSet.getInt("zip_code");
                        address = resultSet.getString("address");
                        email = resultSet.getString("email");
                        phoneNumber = resultSet.getString("phone_number");
                        password = resultSet.getString("password");
                       // System.out.println(sSN + " " + userType + " " + firstName + " " + lastName + " " + birthDate + " " + zipCode + " " + address + " " + email + " " + phoneNumber + " " + password);

                        User user;
                        if (userType == 1) {
                            user = new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else if (userType == 2) {
                            user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else {
                            user = new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        }
                        userList.add(user);
                    }
                } else System.out.println("Empty resultSet");
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return userList;
        }
    }

    public User retrieveUser(String query) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        sSN = resultSet.getLong("ssn");
                        userType = resultSet.getInt("type");
                        firstName = resultSet.getString("first_name");
                        lastName = resultSet.getString("last_name");
                        birthDate = resultSet.getDate("birth_date");
                        zipCode = resultSet.getInt("zip_code");
                        address = resultSet.getString("address");
                        email = resultSet.getString("email");
                        phoneNumber = resultSet.getString("phone_number");
                        password = resultSet.getString("password");
                        System.out.println(sSN + " " + userType + " " + firstName + " " + lastName + " " + birthDate + " " + zipCode + " " + address + " " + email + " " + phoneNumber + " " + password);

                        if (userType == 1) {
                            return new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else if (userType == 2) {
                            return new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else {
                            return new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        }
                    }
                } else System.out.println("empty resultSet");
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return null;
        }
    }
}
