package model;

public class OrderLine {
    private int orderId;
    private Medicine medicine;
    private User user;
    private double price;
    private int quantity;

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

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderId=" + orderId +
                ", medicine=" + medicine +
                ", user=" + user +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
