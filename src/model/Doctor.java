package model;

import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public class Doctor extends User{
    DAOUser daoUser = new DAOUser();

  //  public Doctor(){}

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password, boolean isActive) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, isActive);
    }

    public Doctor(String sSN, String firstName, String lastName, Date birthDate, String zipCode, String address, String email, String phoneNumber, String password) {
        super(sSN, 2, firstName, lastName, birthDate, zipCode, address, email, phoneNumber, password, true);
    }

    public List<User> getDoctorList(){
        return daoUser.getUserList("2");
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
