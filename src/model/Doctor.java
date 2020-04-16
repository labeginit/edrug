package model;

import model.dBConnection.DAOUser;

import java.util.Date;
import java.util.List;

public class Doctor extends User{

    public Doctor(){}

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }

    public List<User> getDoctorList(){
        DAOUser daoUser = new DAOUser();
        return daoUser.getUserList("2");
    }

    @Override
    public String toString() {
        return "Doctor: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
