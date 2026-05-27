package com.pluralsight.models;

public class Topping{

    private String name;
    private String type;
    private boolean addExtra;

    public Topping(String name, String type, boolean addExtra) {
        this.name = name;
        this.type = type;
        this.addExtra = addExtra;
    }

    public boolean isExtra() {
        return addExtra;
    }
    public void setAddExtra(boolean addExtra) {this.addExtra = addExtra;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String category) {
        this.type = category;
    }

}
