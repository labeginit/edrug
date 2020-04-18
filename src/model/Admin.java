package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
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

    public int addAdmin(User user){
        int linesAdded = 0;
        DAOUser daoUser = new DAOUser();
        if (user instanceof Admin) {
            return linesAdded = daoUser.addUser(user);
        }
        System.out.println("records added: " + linesAdded);
        return linesAdded;
    }

    @Override
    public String toString(){
        return "Admin: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
