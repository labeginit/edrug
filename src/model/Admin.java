package model;

import model.dBConnection.DAOUser;

import java.util.Date;
import java.util.List;

public class Admin extends User {

    public Admin(){}

    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password);
    }

    public List<User> getAdminList(){
        DAOUser daoUser = new DAOUser();
        return daoUser.getUserList("3");
    }

    @Override
    public String toString(){
        return "Admin: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
