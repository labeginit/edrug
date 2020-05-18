package model;

import java.sql.Date;

public class Admin extends User {

    public Admin(String SSN, String firstName, String lastName, Date birthDate, String zipcode, String city, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(SSN, 3, firstName, lastName, birthDate, zipcode, city, address, email, phoneNumber, password, isActive);
    }

    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, true);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
