package model.dBConnection;

import model.Delivery;
import model.Order;
import model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODelivery {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int zipcode;
    private String phoneNumber;
    private Date shipDate;
    private List<Delivery> deliveries = new ArrayList<>();
    private ResultSet resultSet = null;
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();

    protected int retrieveLastDeliveryId() {
        id = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(id) AS maxId FROM `edrugs_test`.`Delivery`;");
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    return id = resultSet.getInt("maxId");
                }
            } else return id;

        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return id;
        }
    }

    protected List<Delivery> retrieveDeliveryList() {
        resultSet = null;
        deliveries.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Delivery;");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        deliveries.add(createObjects(resultSet));
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
            return deliveries;
        }
    }

    protected Delivery createObjects(ResultSet resultSet) throws Exception{
        id = resultSet.getInt("delivery_id");
        firstName = resultSet.getString("first_name");
        lastName = resultSet.getString("last_name");
        shipDate = resultSet.getDate("ship_date");
        zipcode = resultSet.getInt("zipcode");
        city = resultSet.getString("city");
        address = resultSet.getString("address");
        phoneNumber = resultSet.getString("phone_number");
        Delivery delivery = new Delivery(id,firstName,lastName,address,city,zipcode,phoneNumber,shipDate);
        return delivery;
    }

    protected int addDelivery(Delivery delivery) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (delivery != null) {
                    id = delivery.getId();
                    firstName = delivery.getFirstName();
                    lastName = delivery.getLastName();
                    shipDate = delivery.getDate();
                    zipcode = delivery.getZipcode();
                    city = delivery.getCity();
                    address = delivery.getAddress();
                    phoneNumber = delivery.getPhoneNumber();

                    String query = "INSERT INTO `edrugs_test`.`Delivery` (`delivery_id`, `first_name`, `last_name`, `ship_date`, `zip_code`, `city`, `address`, `phone_number`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertDelivery(query, id, firstName, lastName, shipDate, zipcode, city, address, phoneNumber);
                } else {
                    throw new NullPointerException("The delivery object is null");
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

    protected Delivery getDelivery(int id) {
        Delivery temp = null;
        String query = "SELECT * FROM Order WHERE id = " + id + ";";
        try {
            temp = retrieveDelivery(query, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }
    private Delivery retrieveDelivery(String query, int id) {
        Delivery delivery = null;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query);
                if (resultSet != null) {
                    if (resultSet.first()) {
                        this.id = resultSet.getInt("delivery_id");
                        firstName = resultSet.getString("first_name");
                        lastName = resultSet.getString("last_name");
                        address = resultSet.getString("address");
                        city = resultSet.getString("city");
                        zipcode = resultSet.getInt("zipcode");
                        phoneNumber = resultSet.getString("phone_number");
                        shipDate = resultSet.getDate("ship_date");

                        delivery = new Delivery(id, firstName, lastName, address, city, zipcode, phoneNumber, shipDate);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return delivery;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return delivery;
        }
    }
}
