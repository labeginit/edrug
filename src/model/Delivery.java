package model;

import java.sql.Date;

public class Delivery {
    private int orderId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private Date date;

    public Delivery(int orderId, String firstName, String lastName, String address, String city, String zipCode, String phoneNumber, Date date) {
        this.orderId = orderId;
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setCity(city);
        setZipCode(zipCode);
        setPhoneNumber(phoneNumber);
        setDate(date);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 45) {
            this.firstName = firstName.substring(0, 44);
        } else this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 45) {
            this.lastName = lastName.substring(0, 44);
        } else this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 45) {
            this.address = address.substring(0, 44);
        } else this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.length() > 45) {
            this.city = city.substring(0, 44);
        } else this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (zipCode.length() > 6) {
            this.zipCode = zipCode.substring(0, 5);
        } else this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 13) {
            this.phoneNumber = phoneNumber.substring(0, 12);
        } else this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
