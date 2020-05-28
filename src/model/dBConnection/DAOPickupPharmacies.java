package model.dBConnection;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPickupPharmacies {
    private ResultSet resultSet = null;
    private List<Pharmacy> pharmacies = new ArrayList<>();
    private Pharmacy pharmacy;
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();
    private int storeId;
    private String storeName;
    private String address;
    private String zipcode;
    private String city;
    private String phoneNumber;
    private String email;

    protected int retrieveLastPharmacyId() {
        storeId = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(id) AS maxId FROM `Pharmacy`;");
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    return storeId = resultSet.getInt("maxId");
                }
            } else return storeId;

        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return storeId;
        }
    }
    protected List<Pharmacy> retrievePharmacyList() {
        resultSet = null;
        pharmacies.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Pharmacy;");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        pharmacies.add(createObjects(resultSet));
                    }
                } else throw new Exception("Empty resultSet");

            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return pharmacies;
        }
    }

    protected Pharmacy createObjects(ResultSet resultSet) throws Exception {
        storeId = resultSet.getInt("id");
        storeName = resultSet.getString("store_name");
        zipcode = resultSet.getString("zipcode");
        address = resultSet.getString("address");
        city = resultSet.getString("city");
        email = resultSet.getString("email");
        phoneNumber = resultSet.getString("phone_number");

        pharmacy = new Pharmacy(storeId, storeName, address, zipcode, city, phoneNumber, email);

        return pharmacy;
    }

    protected int addPharmacy(Pharmacy pharmacy) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (pharmacy != null) {
                    storeId = pharmacy.getStoreId();
                    storeName = pharmacy.getStoreName();
                    city = pharmacy.getCity();
                    zipcode = pharmacy.getZipcode();
                    address = pharmacy.getAddress();
                    email = pharmacy.getEmail();
                    phoneNumber = pharmacy.getPhoneNumber();

                    String query = "INSERT INTO `Pharmacy` ( `id` `store_name`, `address`, `zipcode`, `city`, `phone_number`, `email`) VALUES (?,?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertPharmacy(query, storeId, storeName, address, zipcode, city, phoneNumber, email);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int updatePharmacy(Pharmacy pharmacy) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (pharmacy != null) {
                    storeId = pharmacy.getStoreId();
                    storeName = pharmacy.getStoreName();
                    address = pharmacy.getAddress();
                    city = pharmacy.getCity();
                    zipcode = pharmacy.getZipcode();
                    address = pharmacy.getAddress();
                    email = pharmacy.getEmail();
                    phoneNumber = pharmacy.getPhoneNumber();

                    String query = "UPDATE `Pharmacy` SET `id` = ?, `store_name` = ?, `address` = ?, `zipcode` = ?, `city` = ?, `phone_number` = ?, `email` = ? WHERE (`id` = ?);";
                    linesAffected = common.updatePharmacy(query, storeId, storeName, address, zipcode, city, phoneNumber, email);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int removePharmacy(Pharmacy pharmacy) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (pharmacy != null) {
                    storeId = pharmacy.getStoreId();

                    String query = "DELETE `Pharmacy` WHERE (`id` = ?);";
                    linesAffected = common.removePharmacy(query, storeId);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }
}
