package model;

import java.util.Date;

public abstract class User {
    protected long sSN;
    protected int userType;  //   1 = Patient, 2 = Doctor, 3 = Admin
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected int zipCode;
    protected String address;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public User(long sSN, int userType, String firstName, String lastName, Date birthDate, int zipCode, String address, String email, String phoneNumber, String password) {
        this.sSN = sSN;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.zipCode = zipCode;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "sSN=" + sSN +
                ", userType='" + userType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", zipCode=" + zipCode +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
