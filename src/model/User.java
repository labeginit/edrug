package model;

import javafx.scene.control.CheckBox;
import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public abstract class User {
    private String sSN;
    private int userType;  //   1 = Patient, 2 = Doctor, 3 = Admin
    private String firstName;
    private String lastName;
    private java.sql.Date bDate;
    private String zipCode;
    private String address;
    private String city;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isActive;
    private CheckBox checkBox;

    public User(){}

    public User(String sSN, int userType, String firstName, String lastName, java.sql.Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password, boolean isActive) {
        setSsn(sSN);
        setUserType(userType);
        setFirstName(firstName);
        setLastName(lastName);
        setBDate(birthDate);
        setZipCode(zipCode);
        setCity(city);
        setAddress(address);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setActive(isActive);
        setCheckBox(new CheckBox());
    }

    public User(String sSN, int userType, String firstName, String lastName, java.sql.Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password) {
        this(sSN, userType, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, true);
    }

    public String getSsn() {
        return sSN;
    }

    public void setSsn(String sSN) {
        this.sSN = sSN;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBDate() {
        return bDate;
    }

    public void setBDate(java.sql.Date bDate) {
        this.bDate = bDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" +
                "sSN='" + getSsn() + '\'' +
                ", userType=" + getUserType() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", bDate=" + getBDate() +
                ", zipCode='" + getZipCode() + '\'' +
                ", city='" + getCity() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", isActive=" + getActive() +
                '}';
    }
}
