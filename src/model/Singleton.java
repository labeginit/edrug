package model;

import java.sql.Date;
import java.util.List;

public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {
    }
    
    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();

        return singleton;
    }

    User user = new User() {
        @Override
        public String getSsn() {
            return super.getSsn();
        }

        @Override
        public void setSsn(String sSN) {
            super.setSsn(sSN);
        }

        @Override
        public int getUserType() {
            return super.getUserType();
        }

        @Override
        public void setUserType(int userType) {
            super.setUserType(userType);
        }

        @Override
        public String getFirstName() {
            return super.getFirstName();
        }

        @Override
        public void setFirstName(String firstName) {
            super.setFirstName(firstName);
        }

        @Override
        public String getLastName() {
            return super.getLastName();
        }

        @Override
        public void setLastName(String lastName) {
            super.setLastName(lastName);
        }

        @Override
        public Date getBDate() {
            return super.getBDate();
        }

        @Override
        public void setBDate(Date bDate) {
            super.setBDate(bDate);
        }

        @Override
        public String getZipCode() {
            return super.getZipCode();
        }

        @Override
        public void setZipCode(String zipCode) {
            super.setZipCode(zipCode);
        }

        @Override
        public String getAddress() {
            return super.getAddress();
        }

        @Override
        public void setAddress(String address) {
            super.setAddress(address);
        }

        @Override
        public String getEmail() {
            return super.getEmail();
        }

        @Override
        public void setEmail(String email) {
            super.setEmail(email);
        }

        @Override
        public String getPhoneNumber() {
            return super.getPhoneNumber();
        }

        @Override
        public void setPhoneNumber(String phoneNumber) {
            super.setPhoneNumber(phoneNumber);
        }

        @Override
        public String getPassword() {
            return super.getPassword();
        }

        @Override
        public void setPassword(String password) {
            super.setPassword(password);
        }

        @Override
        public void setActive(boolean active) {
            super.setActive(active);
        }

        @Override
        public boolean getActive() {
            return super.getActive();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

