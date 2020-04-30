package model;

import java.sql.Date;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private Date date;
    private enum DeliveryMethod {SELFPICKUP, SCHENKER, POSTEN}
    private enum PaymentMethod {CREDIT_CARD, INVOICE, BANK_TRANSFER}
    private DeliveryMethod deliveryMethod;
    private PaymentMethod paymentMethod;
    private double totalSum;
    private double totalVAT;
    private List<OrderLine> specification;

    public Order(int id, User user, Date date,  DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, List<OrderLine> specification){
        setId(id);
        setUser(user);
        setDate(date);
        setDeliveryMethod(deliveryMethod);
        setPaymentMethod(paymentMethod);
        setSpecification(specification);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT = totalVAT;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSpecification(List<OrderLine> specification) {
        this.specification = specification;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public List<OrderLine> getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentMethod=" + paymentMethod +
                ", totalSum=" + totalSum +
                ", totalVAT=" + totalVAT +
                ", specification=" + specification +
                '}';
    }
}
