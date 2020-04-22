package model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Medicine> cartList;

    public ShoppingCart() {
        cartList = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        medicine.setQuantity(medicine.getQuantity() + 1);
        cartList.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        medicine.setQuantity(0);
        cartList.remove(medicine);
    }

    public double getTotalCost() {
        double totalAmount = 0;

        for (Medicine medicine : cartList) {
            totalAmount = +(medicine.getPrice() * medicine.getQuantity());
        }
        return totalAmount;
    }
}
