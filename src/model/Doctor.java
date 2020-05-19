package model;

import java.sql.Date;

public class Doctor extends User{

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, isActive);
    }

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, true);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
