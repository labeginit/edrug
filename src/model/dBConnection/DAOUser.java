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
    User user;
    DAOCommon common = new DAOCommon();
    Connection connection = DBConnection.getConnection();

    private String sSN;
    private int userType;  //   1 = Patient, 2 = Doctor, 3 = Admin
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String zipCode;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;

    private List<User> retrieveUserList(String query) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        sSN = resultSet.getString("ssn");
                        userType = resultSet.getInt("type");
                        firstName = resultSet.getString("first_name");
                        lastName = resultSet.getString("last_name");
                        birthDate = resultSet.getDate("birth_date");
                        zipCode = resultSet.getString("zip_code");
                        address = resultSet.getString("address");
                        email = resultSet.getString("email");
                        phoneNumber = resultSet.getString("phone_number");
                        password = resultSet.getString("password");

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
                    if (resultSet.first()){
                        sSN = resultSet.getString("ssn");
                        userType = resultSet.getInt("type");
                        firstName = resultSet.getString("first_name");
                        lastName = resultSet.getString("last_name");
                        birthDate = resultSet.getDate("birth_date");
                        zipCode = resultSet.getString("zip_code");
                        address = resultSet.getString("address");
                        email = resultSet.getString("email");
                        phoneNumber = resultSet.getString("phone_number");
                        password = resultSet.getString("password");

                        if (userType == 1) {
                            return user = new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else if (userType == 2) {
                            return user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        } else {
                            return user = new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                        }
                    }
                } else {
                    System.out.println("empty resultSet");
                    return user = null;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return user;
        }

    }

    public List<User> getUserList(String userType) { // 0 - all users will be shown; values 1-3 - a corresponding type of users.
        if (userType.matches("[0-3]")) {
            if (userType.compareTo("0") == 0) {
                userList = retrieveUserList("SELECT * FROM User;");
            } else {
                userList = retrieveUserList("SELECT * FROM User where type = " + userType + ";");
            }
        } else {
            System.out.println("Illegal argument. Possible values are 0, 1, 2, 3");
            userList = null;
        }
        return userList;
    }

  /*  public User getUser(String sSN) {
        User temp = retrieveUser("SELECT * FROM User where ssn = " + sSN + ";");
        if (temp != null) {
            return temp;
        } else {
            return null;
        }
    }*/
    //   WIP
    private User retrieveUser(String query, String sSN) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, sSN);
                if (resultSet != null) {
                    if (resultSet.first()){
                  //  sSN = resultSet.getString("ssn");
                    userType = resultSet.getInt("type");
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");
                    birthDate = resultSet.getDate("birth_date");
                    zipCode = resultSet.getString("zip_code");
                    address = resultSet.getString("address");
                    email = resultSet.getString("email");
                    phoneNumber = resultSet.getString("phone_number");
                    password = resultSet.getString("password");

                    if (userType == 1) {
                        return user = new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                    } else if (userType == 2) {
                        return user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                    } else {
                        return user = new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
                    }
                }
                } else {
                    System.out.println("empty resultSet");
                    return user = null;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return user;
        }

    }
/*
    public List<User> getUserList(String userType) { // 0 - all users will be shown; values 1-3 - a corresponding type of users.
        if (userType.matches("[0-3]")) {
            if (userType.compareTo("0") == 0) {
                userList = retrieveUserList("SELECT * FROM User;");
            } else {
                userList = retrieveUserList("SELECT * FROM User where type ?;");
            }
        } else {
            System.out.println("Illegal argument. Possible values are 0, 1, 2, 3");
            userList = null;
        }
        return userList;
    }
*/
    public User getUser(String sSN) {
        User temp = null;
        String query = "SELECT * FROM User where ssn = ?;";
        try {

            temp = retrieveUser(query, sSN);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        if (temp != null) {
            return temp;
        } else {
            return null;
        }
    }
}
