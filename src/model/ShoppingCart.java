package model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Medicine> cartList;
    private double totalAmount;

    public ShoppingCart() {
        cartList = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        cartList.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        cartList.remove(medicine);
    }

    public void getTotalCost() {
        totalAmount = 0;

        for(Medicine medicine: cartList) {
            totalAmount =+ medicine.getPrice();
        }
    }
}
