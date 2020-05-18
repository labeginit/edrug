package model;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private static CartSingleton ourInstance = new CartSingleton();

    private CartSingleton() {
    }

    public static CartSingleton getOurInstance() {
        return ourInstance;
    }

    public List<OrderLine> cart = new ArrayList<>();

    public Order.DeliveryMethod deliveryMethod;

    public Order.PaymentMethod paymentMethod;

    public List<OrderLine> getCart() {
        return cart;
    }

    public Order.DeliveryMethod getDeliveryMethod() { return deliveryMethod;}

    public void setDeliveryMethod(Order.DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setCart(List<OrderLine> cart) {
        this.cart = cart;
    }

    public Order.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Order.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
