package model.dBConnection;

import model.Delivery;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODelivery {
    private int orderId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private Date shipDate;
    private List<Delivery> deliveries = new ArrayList<>();
    private ResultSet resultSet = null;
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();


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
        orderId = resultSet.getInt("order_id");
        firstName = resultSet.getString("first_name");
        lastName = resultSet.getString("last_name");
        shipDate = resultSet.getDate("ship_date");
        zipCode = resultSet.getString("zipcode");
        city = resultSet.getString("city");
        address = resultSet.getString("address");
        phoneNumber = resultSet.getString("phone_number");
        Delivery delivery = new Delivery(orderId,firstName,lastName,address,city,zipCode,phoneNumber,shipDate);
        return delivery;
    }

    protected int addDelivery(Delivery delivery) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (delivery != null) {
                    orderId = delivery.getOrderId();
                    firstName = delivery.getFirstName();
                    lastName = delivery.getLastName();
                    shipDate = delivery.getDate();
                    zipCode = delivery.getZipCode();
                    city = delivery.getCity();
                    address = delivery.getAddress();
                    phoneNumber = delivery.getPhoneNumber();

                    String query = "INSERT INTO `edrugs_test`.`Delivery` (`order_id`, `first_name`, `last_name`, `address`, `city`, `zipcode`, `phone_number`, `ship_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertDelivery(query, orderId, firstName, lastName, address , city, zipCode, phoneNumber, shipDate);
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
}
