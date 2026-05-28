package com.pluralsight.models;
import java.math.BigDecimal;
import java.util.ArrayList;


public class Order {
    private ArrayList<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {items.add(item);}
    public ArrayList<OrderItem> getItems() {return items;}

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(OrderItem::getPrice)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }
}
