package model;

import java.util.Date;

public class Doctor extends User{
    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Doctor: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
