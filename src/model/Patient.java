package model;

import java.util.Date;

public class Patient extends User {

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }


    @Override
    public String toString() {
        return "Patient: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }

}
