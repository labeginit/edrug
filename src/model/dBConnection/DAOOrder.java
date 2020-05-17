package model.dBConnection;

import model.Medicine;
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
    private String name;
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
                    linesAffected = common.insertOrder(query, id, user.getSsn(), date, deliveryMethod, paymentMethod, totalSum, totalVAT) + addOrderHasMedicine(specification);
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
    protected int addOrderHasMedicine(List<OrderLine> specification) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (specification != null) {
                    for (int i = 0; i < specification.size(); i++) {
                        orderId = specification.get(i).getOrderId();
                        medicine = specification.get(i).getMedicine();
                        articleNo = specification.get(i).getArticleNo();
                        name = specification.get(i).getName();
                        user = specification.get(i).getUser();
                        price = specification.get(i).getPrice();
                        quantity = specification.get(i).getQuantity();
                        String query = "INSERT INTO `edrugs_test`.`Order_has_Medicine` (`order_id`, `user_ssn`, `article`, `price`, `quantity`) VALUES (?, ?, ?, ?, ?);";
                        linesAffected = common.insertOrderHasMedicine(query, orderId, user.getSsn(), articleNo, price, quantity);
                    }
                } else {
                    throw new NullPointerException("The specification object is null");
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
