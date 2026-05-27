package com.pluralsight.models;

import java.util.ArrayList;

public class Sandwich implements OrderItem {
    private String breadType;
    private int size;
    private boolean isToasted;
    private ArrayList<Topping> toppings;
    private double price;

    // remember no derived properties as parameters
    public Sandwich(String breadType, boolean isToasted, int size) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = isToasted;
        //multiple toppings = store in an array
        this.toppings = new ArrayList<>();
        this.price = basePrice(size);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public double getPrice() {
        return price;
    }

    //
    private double basePrice(int size) {
        return switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 0.0;
        };
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        switch (topping.getType()) {
            case "meat":
                price += (size == 4) ? 1.00 : (size == 8) ? 2.00 : 3.00;
                if (topping.isExtra()) {
                    price += (size == 4) ? 0.50 : (size == 8) ? 1.00 : 1.50;
                }
                break;
            case "cheese":
                price += (size == 4) ? 0.75 : (size == 8) ? 1.50 : 2.25;
                if (topping.isExtra()) {
                    price += (size == 4) ? 0.30 : (size == 8) ? 0.60 : 0.90;
                }
                break;
            default:
                break;

        }
    }
}
