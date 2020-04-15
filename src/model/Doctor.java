package model;

import java.util.Date;

enum typeDoctor {Doctor}

public class Doctor extends User {
    public Doctor(String ssn, String firstName, String lastName, Date bDay, String zipCode, String address, String email, String phoneNumber, String password) {
        super(ssn, firstName, lastName, bDay, zipCode, address, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Doctor: " + getFirstName() + " " + getLastName();
    }
}
