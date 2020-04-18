package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
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

    public int addDoctor(User user){
        int linesAdded = 0;
        DAOUser daoUser = new DAOUser();
        if (user instanceof Doctor) {
            return linesAdded = daoUser.addUser(user);
        }
        System.out.println("records added: " + linesAdded);
        return linesAdded;
    }

    @Override
    public String toString() {
        return "Doctor: " + getSsn() + " " + getFirstName() + " " + getLastName();
    }
}
