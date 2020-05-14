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

    public List<OrderLine> getCart() {
        return cart;
    }
}
