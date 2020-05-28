package model;

public class Pharmacy {
    private final int storeId;
    private String storeName;
    private String address;
    private String zipcode;
    private String city;
    private String phoneNumber;
    private String email;

    public Pharmacy(int storeId,String storeName, String address, String zipcode, String city, String phoneNumber, String email) {
        setStoreName(storeName);
        setAddress(address);
        setZipcode(zipcode);
        setCity(city);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        if (storeName.length() > 45) {
            this.storeName = storeName.substring(0, 44);
        } else this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 45) {
            this.address = address.substring(0, 44);
        } else this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        if (zipcode.length() > 6) {
            this.zipcode = zipcode.substring(0, 5);
        } else this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.length() > 45) {
            this.city = city.substring(0, 44);
        } else this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 13) {
            this.phoneNumber = phoneNumber.substring(0, 12);
        } else this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.length() > 45) {
            this.email = email.substring(0, 44);
        } else this.email = email;
    }

    @Override
    public String toString() {
        return
                 getStoreName() + ' ' +
                 getAddress() + ' ' +
                 getCity() ;
    }
}
