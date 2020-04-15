package model;

import java.util.Date;

public class Admin extends User {
    public Admin(long sSN, String firstName, String lastName, Date birthDate, int zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }
}
