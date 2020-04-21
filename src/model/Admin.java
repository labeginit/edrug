package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public class Admin extends User {
    DAOUser daoUser = new DAOUser();

    public Admin(){}

    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
    }

    public Admin(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 3, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, true);
    }

    public List<User> getAdminList(){
        return daoUser.getUserList("3");
    }

    public int addAdmin(User user){
        int linesAdded = 0;
        if (user instanceof Admin) {
            return linesAdded = daoUser.addUser(user);
        }
        System.out.println("records added: " + linesAdded);
        return linesAdded;
    }

    public int addAdmin(){
        return addAdmin(this);
    }

    public int updateAdmin(){
        return daoUser.updateUser(this);
    }

    public int removeDoctor(){
        return daoUser.removeUser(this);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
