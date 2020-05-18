package model;

import java.io.Serializable;
import java.sql.Date;

public class Patient extends User implements Serializable {

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, isActive);
    }

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, city, address, email, phoneNumber, password, true);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
