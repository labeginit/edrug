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

    public List<User> getPatientList(){
        DAOUser daoUser = new DAOUser();
        return daoUser.getUserList("1");
    }

    public int addPatient(User user){
        int linesAdded = 0;
        if (user instanceof Patient) {
            return linesAdded = daoUser.addUser(user);
        }
        System.out.println("records added: " + linesAdded);
        return linesAdded;
    }

    public int addPatient(){
        return addPatient(this);
    }

    public int updatePatient(){
        return daoUser.updateUser(this);
    }

    public int removePatient(){
        return daoUser.removeUser(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
