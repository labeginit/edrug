package model.dBConnection;

import model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrder {
    private ResultSet resultSet = null;
    private ResultSet resultSet2 = null;
    private int id;
    private User user;
    private Date date;
    private Order.DeliveryMethod deliveryMethod;
    private Order.PaymentMethod paymentMethod;
    private double totalSum;
    private double totalVAT;
    private List<OrderLine> specification = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();
    private int orderId;
    private Medicine medicine = null;
    private int articleNo;
    private String name = "";
    private double price;
    private int quantity;
    private Delivery delivery;
    private Integer pharmacyId;
    DAOUser daoUser = new DAOUser();
    DAOMedicine daoMedicine = new DAOMedicine();
    DAODelivery daoDelivery = new DAODelivery();

    protected int retrieveLastOrderId() {
        id = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(id) AS maxId FROM `Order`;");
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
                resultSet = common.retrieveSet("SELECT * FROM `Order`;");
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
        user = daoUser.getUser(((resultSet.getString("user_ssn"))));
        date = resultSet.getDate("date");
        deliveryMethod = Order.DeliveryMethod.valueOf(resultSet.getString("delivery_method"));
        paymentMethod = Order.PaymentMethod.valueOf(resultSet.getString("payment_method"));
        totalSum = resultSet.getDouble("total");
        totalVAT = resultSet.getDouble("total_VAT");
        specification = retrieveOrderSpecification(id);
        pharmacyId = resultSet.getInt("pharmacy_id");
        if (pharmacyId == 0) {
            delivery = daoDelivery.getDelivery(id);
        }
        Order order = new Order(id, user, date, deliveryMethod, paymentMethod, specification, totalSum, totalVAT, delivery, pharmacyId);
        return order;
    }

    protected List<OrderLine> retrieveOrderSpecification(int id) {
        resultSet2 = null;
        specification.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                String queryString = "SELECT * FROM Order_has_Medicine WHERE `order_id` =" + id +";";
                resultSet2 = common.retrieveSet(queryString);
                if (resultSet2 != null) {
                    while (resultSet2.next()) {
                        specification.add(createObjectsSpecification(resultSet2));
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
                resultSet2.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return specification;
        }
    }
    protected OrderLine createObjectsSpecification(ResultSet resultSet) throws Exception {
        orderId = id;
        articleNo = resultSet.getInt("article");
        medicine = daoMedicine.getMedicine(articleNo);
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
                    pharmacyId = order.getPharmacyId();
                        String query = "INSERT INTO `Order` (`id`, `user_ssn`, `date`, `delivery_method`, `payment_method`, `total`, `total_VAT`, `pharmacy_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                        linesAffected = common.insertOrderPharmacy(query, id, user.getSsn(), date, deliveryMethod, paymentMethod, totalSum, totalVAT, pharmacyId);

                    specification = order.getSpecification();
                    for (OrderLine element : specification) {
                        articleNo = element.getMedicine().getArticleNo();
                        name = element.getName();
                        user = element.getUser();
                        price = element.getPrice();
                        quantity = element.getQuantity();
                        String queryString = "INSERT INTO `Order_has_Medicine` (`order_id`, `user_ssn`,`article`, `price`, `quantity`) VALUES (?, ?, ?, ?, ?);";
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
        String query = "SELECT * FROM `Order` WHERE id = " + id + ";";
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
                        user = daoUser.getUser((resultSet.getString("user_ssn")));
                        date = resultSet.getDate("date");
                        deliveryMethod = Order.DeliveryMethod.valueOf(resultSet.getString("delivery_method"));
                        paymentMethod = Order.PaymentMethod.valueOf(resultSet.getString("payment_method"));
                        totalSum = resultSet.getDouble("total");
                        totalVAT = resultSet.getDouble("total_VAT");
                        specification = retrieveOrderSpecification(id);
                        pharmacyId = resultSet.getInt("pharmacy_id");
                        if (pharmacyId == 0) {
                            delivery = daoDelivery.getDelivery(id);
                        }

                        order = new Order(id, user, date, deliveryMethod, paymentMethod, specification, totalSum, totalVAT, delivery, pharmacyId);
                        return order;
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
