package model;

import model.dBConnection.DAOUser;

import java.util.Date;
import java.util.List;

public class Patient extends User {

    public Patient(){}

    public Patient(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 1, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }

    public List<User> getPatientList(){
        DAOUser daoUser = new DAOUser();
        return daoUser.getUserList("1");
    }

    @Override
    public String toString() {
        return "Patient: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }

}
