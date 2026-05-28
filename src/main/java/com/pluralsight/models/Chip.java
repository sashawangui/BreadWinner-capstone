package com.pluralsight.models;

public class Chip implements OrderItem{
    private String type;
    private double price;

    public Chip(String type) {
        // dealing with empty spaces
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException ("Chip type cannot be null or empty");
        }
        this.type = type;

        // price is pre-determined and immutable,
        // so I decided to just hardcode it in here
        this.price = 1.50;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    //solid figure makes setter unnecessary

    @Override
    public String createReceipt() {
        return String.format("CHIPS - %s - $%.2f%n", type, price);
    }

}
