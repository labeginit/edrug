package model;

import java.util.Date;

enum typeAdmin {ADMIN}

public class Admin extends User {
    public Admin(String ssn, String firstName, String lastName, Date bDay, String zipCode, String address, String email, String phoneNumber, String password) {
        super(ssn, firstName, lastName, bDay, zipCode, address, email, phoneNumber, password);
    }

    @Override
    public String toString() {
        return "Admin: " + getFirstName() + " " + getLastName();
    }
}
