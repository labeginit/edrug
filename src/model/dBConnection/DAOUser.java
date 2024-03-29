package model.dBConnection;

import model.Admin;
import model.Doctor;
import model.Patient;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser {
    private ResultSet resultSet = null;
    private List<User> userList = new ArrayList<>();
    private int linesAffected = 0;
    private User user;
    private DAOCommon common = new DAOCommon();
    private String sSN;
    private int userType;  //   1 = Patient, 2 = Doctor, 3 = Admin
    private String firstName;
    private String lastName;
    private java.sql.Date birthDate;
    private String zipCode;
    private String city;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isActive;
    private int value1;
    private String value2;

    private List<User> retrieveUserList(String usType) {
        resultSet = null;
        userList.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM User where type = ?;", usType);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        userList.add(createObjects(resultSet));
                    }
                } else throw new Exception("Empty resultSet");
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return userList;
        }
    }

    private List<User> retrieveUserList() {
        resultSet = null;
        userList.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM User;");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        userList.add(createObjects(resultSet));
                    }
                } else throw new Exception("Empty resultSet");

            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return userList;
        }
    }

    protected List<User> retrieveUserList(boolean isActive) {
        user = null;
        userList.clear();
        if (isActive) {
            value2 = "1";
        } else {
            value2 = "0";
        }
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM User where active = ?;", value2);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        userList.add(createObjects(resultSet));
                    }
                } else throw new Exception("Empty resultSet");

            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return userList;
        }
    }

    private User createObjects(ResultSet resultSet) throws Exception {
        sSN = resultSet.getString("ssn");
        userType = resultSet.getInt("type");
        firstName = resultSet.getString("first_name");
        lastName = resultSet.getString("last_name");
        birthDate = resultSet.getDate("birth_date");
        zipCode = resultSet.getString("zip_code");
        city = resultSet.getString("city");
        address = resultSet.getString("address");
        email = resultSet.getString("email");
        phoneNumber = resultSet.getString("phone_number");
        password = resultSet.getString("password");
        isActive = resultSet.getBoolean("active");

        if (userType == 1) {
            user = new Patient(sSN, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, isActive);
        } else if (userType == 2) {
            user = new Doctor(sSN, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, isActive);
        } else {
            user = new Admin(sSN, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, isActive);
        }
        return user;
    }

    protected List<User> getUserList(String userType) throws IllegalArgumentException { // 0 - all users will be shown; values 1-3 - a corresponding type of users.
        if (userType.matches("[0-3]")) {
            if (userType.compareTo("0") == 0) {
                userList = retrieveUserList();
            } else {
                userList = retrieveUserList(userType);
            }
        } else {
            throw new IllegalArgumentException("Illegal argument. Possible values are 0, 1, 2, 3");
        }
        return userList;
    }

    private User retrieveUser(String query, String sSN) {
        user = null;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, sSN);
                if (resultSet != null) {
                    if (resultSet.first()) {
                        return user = createObjects(resultSet);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return user;
        }
    }

    protected User getUser(String sSN) {
        User temp = null;
        String query = "SELECT * FROM User where ssn = ?;";
        try {
            temp = retrieveUser(query, sSN);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    protected int addUser(User user) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    sSN = user.getSsn();
                    userType = user.getUserType();
                    firstName = user.getFirstName();
                    lastName = user.getLastName();
                    birthDate = user.getBDate();
                    zipCode = user.getZipCode();
                    city = user.getCity();
                    address = user.getAddress();
                    email = user.getEmail();
                    phoneNumber = user.getPhoneNumber();
                    password = user.getPassword();
                    isActive = user.getActive();
                    if (isActive) {
                        value1 = 1;
                    } else {
                        value1 = 0;
                    }
                    String query = "INSERT INTO `User` (`ssn`, `type`, `first_name`, `last_name`, `birth_date`, `zip_code`, `city`, `address`, `email`, `phone_number`, `password`, `active`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertUser(query, sSN, userType, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, value1);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int updateUser(User user) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    firstName = user.getFirstName();
                    lastName = user.getLastName();
                    birthDate = user.getBDate();
                    zipCode = user.getZipCode();
                    city = user.getCity();
                    address = user.getAddress();
                    email = user.getEmail();
                    phoneNumber = user.getPhoneNumber();
                    isActive = user.getActive();
                    sSN = user.getSsn();
                    if (isActive) {
                        value1 = 1;
                    } else {
                        value1 = 0;
                    }
                    String query = "UPDATE `User` SET `first_name` = ?, `last_name` = ?, `birth_date` = ?, `zip_code` = ?, `city` = ?,`address` = ?, `email` = ?, `phone_number` = ?, `active` = ? WHERE (`ssn` = ?);";
                    linesAffected = common.updateUser(query, sSN, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, value1);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int updatePassword(User user) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    password = user.getPassword();
                    sSN = user.getSsn();
                    String query = "UPDATE `User` SET `password` = ? WHERE (`ssn` = ?);";
                    linesAffected = common.updateRecordStr(query, password, sSN);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int removeUser(User user) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (user != null) {
                    sSN = user.getSsn();
                    String query = "UPDATE `User` SET `active` = '0' WHERE (`ssn` = ?)";
                    linesAffected = common.updateRecordStr(query, sSN);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }
}
