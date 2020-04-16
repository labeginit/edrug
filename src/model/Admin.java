package model;

import java.util.Date;

public class Admin extends User {
    public Admin(){}
    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }

    @Override
    public String toString(){
        return "Admin: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
