package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Medicine> cartList;
    private int size = 0;

    public ShoppingCart() {
        cartList = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        medicine.setQuantity(1);
        cartList.add(medicine);
        size++;
    }

    public boolean removeMedicine(Medicine medicine) {
        medicine.setQuantity(0);
        size--;
        return cartList.remove(medicine);
    }

    public double getTotalCost() {
        double totalAmount = 0;

        for (Medicine medicine : cartList) {
            totalAmount = +(medicine.getPrice() * medicine.getQuantity());
        }
        return totalAmount;
    }

    public Medicine getMedicine(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size "
                    + index);
        }
        return (Medicine) cartList.get(index);
    }

    public int size(){
        return size;
    }
}
