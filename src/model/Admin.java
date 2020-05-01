package model;

import java.sql.Date;

public class Admin extends User {

    public Admin(String SSN, String firstName, String lastName, Date birthDate, String zipcode, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(SSN, 3, firstName, lastName, birthDate, zipcode, address, email, phoneNumber, password, isActive);
    }

    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, true);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
