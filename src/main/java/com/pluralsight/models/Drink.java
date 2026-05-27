package com.pluralsight.models;

public class Drink implements OrderItem {
    private String flavor;
    private String size;
    private double price;

    public Drink(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
        this.price = calculateDrinkPrice(size);
    }

    private double calculateDrinkPrice(String size) {
        return switch (size) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    public String getFlavor() {return flavor;}
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {this.size = size;}

    public double getPrice() { return price;}
}
