package model.dBConnection;

import model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrder {
    private ResultSet resultSet = null;
    private int id;
    private User user = null;
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
    private int orderId;
    private Medicine medicine = null;
    private int articleNo;
    private String name = "";
    private double price;
    private int quantity;


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

    protected List<Order> retrieveOrderList() {
        resultSet = null;
        orders.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Order;");
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

    protected Order createObjects(ResultSet resultSet) throws Exception {
        id = resultSet.getInt("id");
        user.setSsn((resultSet.getString("user_ssn")));
        date = resultSet.getDate("date");
        deliveryMethod = Order.DeliveryMethod.valueOf(resultSet.getString("delivery_method"));
        paymentMethod = Order.PaymentMethod.valueOf(resultSet.getString("payment_method"));
        totalSum = resultSet.getDouble("total");
        totalVAT = resultSet.getDouble("total_VAT");
        specification = retrieveOrderSpecification(id);

        Order order = new Order(id,user,date,deliveryMethod,paymentMethod, specification, totalSum,totalVAT);

        return order;
    }

    protected List<OrderLine> retrieveOrderSpecification(int id) {
        resultSet = null;
        specification.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                String queryString = "SELECT * FROM Order_has_Medicine WHERE `order_id` =" + id +";";
                resultSet = common.retrieveSet(queryString);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        specification.add(createObjectsSpecification(resultSet));
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
            return specification;
        }
    }
    protected OrderLine createObjectsSpecification(ResultSet resultSet) throws Exception {
        orderId = id;
        articleNo = resultSet.getInt("article");
        medicine.setArticleNo(articleNo);
        name = medicine.getName();
        price = resultSet.getDouble("price");
        quantity = resultSet.getInt("quantity");

        OrderLine orderLine = new OrderLine(orderId,user,medicine,price,quantity);

        return orderLine;
    }
    protected int addOrder(Order order) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (order != null) {
                    id = order.getId();
                    user = order.getUser();
                    date = order.getDate();
                    deliveryMethod = order.getDeliveryMethod();
                    paymentMethod = order.getPaymentMethod();
                    totalSum = order.getTotalSum();
                    totalVAT = order.getTotalVAT();
                    specification = order.getSpecification();
                    String query = "INSERT INTO `edrugs_test`.`Order` (`id`, `user_ssn`, `date`, `delivery_method`, `payment_method`, `total`, `total_VAT`) VALUES (?, ?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertOrder(query, id, user.getSsn(), date, deliveryMethod, paymentMethod, totalSum, totalVAT);
                    specification = order.getSpecification();
                    for (OrderLine element : specification) {
                        articleNo = element.getMedicine().getArticleNo();
                        name = element.getName();
                        user = element.getUser();
                        price = element.getPrice();
                        quantity = element.getQuantity();
                        String queryString = "INSERT INTO `edrugs_test`.`Order_has_Medicine` (`order_id`, `user_ssn`,`article`, `price`, `quantity`) VALUES (?, ?, ?, ?, ?);";
                        linesAffected = linesAffected + common.insertOrderHasMedicine(queryString, id, user.getSsn(),articleNo, price, quantity);
                    }
                } else {
                    throw new NullPointerException("The order object is null");
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

    protected Order getOrder(int id) {
        Order temp = null;
        String query = "SELECT * FROM Order WHERE id = " + id + ";";
        try {
            temp = retrieveOrder(query, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    private Order retrieveOrder(String query, int id) {
        Order order = null;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query);
                if (resultSet != null) {
                    if (resultSet.first()) {
                        this.id = resultSet.getInt("id");
                        user.setSsn((resultSet.getString("user_ssn")));
                        date = resultSet.getDate("date");
                        deliveryMethod = Order.DeliveryMethod.valueOf(resultSet.getString("delivery_method"));
                        paymentMethod = Order.PaymentMethod.valueOf(resultSet.getString("payment_method"));
                        totalSum = resultSet.getDouble("total");
                        totalVAT = resultSet.getDouble("total_VAT");
                        specification = retrieveOrderSpecification(id);

                       order = new Order(id, user, date, deliveryMethod, paymentMethod, specification, totalSum, totalVAT);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return order;
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
            return order;
        }
    }
}
