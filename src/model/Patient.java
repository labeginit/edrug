package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public class Patient extends User {
    DAOUser daoUser = new DAOUser();

    public Patient(){}

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
    }

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, true);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
