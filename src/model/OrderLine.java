package model;

import javafx.scene.control.CheckBox;
import java.io.Serializable;

public class OrderLine implements Serializable {
    private int orderId;
    private Medicine medicine;
    private int articleNo;
    private String name;
    private User user;
    private double price;
    private int quantity;
    private CheckBox checkBox;

    public OrderLine(int orderId, User user, Medicine medicine, double price, int quantity){
        setOrderId(orderId);
        setUser(user);
        setMedicine(medicine);
        setPrice(price);
        setQuantity(quantity);

    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderId=" + getOrderId() +
                ", medicine=" + getMedicine() +
                ", user=" + getUser() +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                '}';
    }
}
