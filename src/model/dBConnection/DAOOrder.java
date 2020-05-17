package model.dBConnection;

import model.Order;
import model.OrderLine;
import model.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrder {
    private ResultSet resultSet = null;
    private int id;
    private User user;
    private Date date;
    public enum DeliveryMethod {SELFPICKUP, SCHENKER, POSTEN}
    public enum PaymentMethod {CREDIT_CARD, INVOICE, BANK_TRANSFER}
    private Order.DeliveryMethod deliveryMethod;
    private Order.PaymentMethod paymentMethod;
    private double totalSum;
    private double totalVAT;
    private List<OrderLine> specification;
    private List<Order> orders = new ArrayList<>();
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();


    protected int retrieveLastOrderId() {
        id = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(id) AS maxId FROM `edrugs_test`.`Order`;");
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
/*
    protected List<Order> retrieveOrderList() {
        resultSet = null;
        orders.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Pharmacy;");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        orders.add(createObjects(resultSet));
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
            return orders;
        }
    }
/*
    protected Order createObjects(ResultSet resultSet) throws Exception {
        id = resultSet.getInt("id");
        user = commonMethods.getUser(resultSet.getString("user_ssn"));
        date = resultSet.getDate("date");
        deliveryMethod = Order.DeliveryMethod.valueOf(resultSet.getString("delivery_method"));
        paymentMethod = Order.PaymentMethod.valueOf(resultSet.getString("payment_method"));
        totalSum = resultSet.getDouble("total");
        totalVAT = resultSet.getDouble("total_VAT");
        specification =

        Order = new Order(id,user,date,deliveryMethod,paymentMethod,);

        return Order;
    } */
}
