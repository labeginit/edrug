package model;

import java.util.Date;

enum typePatient {PATIENT}

public class Patient extends User {
    public Patient(String ssn, String firstName, String lastName, Date bDay, String zipCode, String address, String email, String phoneNumber, String password) {
        super(ssn, firstName, lastName, bDay, zipCode, address, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Patient: " + getFirstName() + " " + getLastName();
    }
}
