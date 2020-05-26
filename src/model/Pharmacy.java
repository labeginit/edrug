package model;


public class Pharmacy {
    private final int storeId;
    private String storeName;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;
    private String email;


    public Pharmacy(int storeId,String storeName, String address, String zipCode, String city, String phoneNumber, String email) {
        setStoreName(storeName);
        setAddress(address);
        setZipCode(zipCode);
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
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                 getStoreName() + ' ' +
                 getAddress() + ' ' +
                 getCity() ;
    }
}
