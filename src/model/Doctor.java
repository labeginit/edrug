package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public class Doctor extends User{
    DAOUser daoUser = new DAOUser();

    public Doctor(){}

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
    }

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, true);
    }

    public List<User> getDoctorList(){
        return daoUser.getUserList("2");
    }

    public int addDoctor(User user){
        int linesAdded = 0;
        if (user instanceof Doctor) {
            return linesAdded = daoUser.addUser(user);
        }
        System.out.println("records added: " + linesAdded);
        return linesAdded;
    }

    public int addDoctor(){
        return addDoctor(this);
    }

    public int updateDoctor(){
        return daoUser.updateUser(this);
    }

    public int removeDoctor(){
        return daoUser.removeUser(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
