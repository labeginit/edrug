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

    public String deliveryMethod;

    public String paymentMethod;

    public List<OrderLine> getCart() {
        return cart;
    }

    public String getDeliveryMethod() { return deliveryMethod;}

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setCart(List<OrderLine> cart) {
        this.cart = cart;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
