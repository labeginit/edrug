package model.dBConnection;

import model.Admin;
import model.Doctor;
import model.Patient;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser {
    private Statement statement;
    private ResultSet resultSet;
    private List<User> userList = new ArrayList<>();
    private int linesAffected = 0;
    private static User user;
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
    private boolean isActive;
    private int value1;

    // to be used to retrieve a specific user list (types 1-3) (internal use)
    private List<User> retrieveUserList(String usType) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM User where type = ? and active = 1;", usType);
                createObjects(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return userList;
        }
    }
    // to be used to retrieve the whole user list (internal use)
    private List<User> retrieveUserList() {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM User where active = 1;");
                createObjects(resultSet);
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

    private void createObjects(ResultSet resultSet) throws Exception{
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
                isActive = resultSet.getBoolean("active");

                if (userType == 1) {
                    user = new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                } else if (userType == 2) {
                    user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                } else {
                    user = new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                }
                userList.add(user);
            }
        } else throw new Exception("Empty resultSet");
    }

    public List<User> getUserList(String userType) throws IllegalArgumentException{ // 0 - all users will be shown; values 1-3 - a corresponding type of users.
        if (userType.matches("[0-3]")) {
            if (userType.compareTo("0") == 0) {
                userList = retrieveUserList();
            } else {
                userList = retrieveUserList(userType);
            }
        } else {
            userList = null;
            throw new IllegalArgumentException("Illegal argument. Possible values are 0, 1, 2, 3");
        }
        return userList;
    }

    private User retrieveUser(String query, String sSN) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, sSN);
                if (resultSet != null) {
                    if (resultSet.first()){
                    this.sSN = resultSet.getString("ssn");
                    userType = resultSet.getInt("type");
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");
                    birthDate = resultSet.getDate("birth_date");
                    zipCode = resultSet.getString("zip_code");
                    address = resultSet.getString("address");
                    email = resultSet.getString("email");
                    phoneNumber = resultSet.getString("phone_number");
                    password = resultSet.getString("password");
                    isActive = resultSet.getBoolean("active");

                    if (userType == 1) {
                        return user = new Patient(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                    } else if (userType == 2) {
                        return user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                    } else {
                        return user = new Admin(sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
                    }
                }
                } else {
                    System.out.println("Empty resultSet");
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

    public User getUser(String sSN) {
        User temp = null;
        String query = "SELECT * FROM User where ssn = ?;";
        try {
            temp = retrieveUser(query, sSN);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return temp;
    }

    public int addUser(User user) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                sSN = user.getSsn();
                userType = user.getUserType();
                firstName = user.getFirstName();
                lastName = user.getLastName();
                birthDate = user.getBDate();
                zipCode = user.getZipCode();
                address = user.getAddress();
                email = user.getEmail();
                phoneNumber = user.getPhoneNumber();
                password = user.getPassword();
                isActive = user.getActive();
                    if (isActive){
                        value1 = 1;
                    } else {value1 = 0;}
                String query = "INSERT INTO `edrugs_test`.`User` (`ssn`, `type`, `first_name`, `last_name`, `birth_date`, `zip_code`, `address`, `email`, `phone_number`, `password`, `active`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                linesAffected = common.insertUser(query, sSN, userType, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, value1);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return linesAffected;
        }
    }



    public int updateUser(User user){
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    firstName = user.getFirstName();
                    lastName = user.getLastName();
                    birthDate = user.getBDate();
                    zipCode = user.getZipCode();
                    address = user.getAddress();
                    email = user.getEmail();
                    phoneNumber = user.getPhoneNumber();
                    isActive = user.getActive();
                    sSN = user.getSsn();
                    if (isActive){
                        value1 = 1;
                    } else {value1 = 0;}
                    String query = "UPDATE `edrugs_test`.`User` SET `first_name` = ?, `last_name` = ?, `birth_date` = ?, `zip_code` = ?, `address` = ?, `email` = ?, `phone_number` = ?, `active` = ? WHERE (`ssn` = ?);";
                    linesAffected = common.updateUser(query, sSN, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, value1);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return linesAffected;
        }
    }

    public int removeUser(User user){
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    sSN = user.getSsn();
                    String query = "UPDATE `edrugs_test`.`User` SET `active` = '0' WHERE (`ssn` = ?)";
                    linesAffected = common.updateRecordStr(query, sSN);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return linesAffected;
        }
    }
}
