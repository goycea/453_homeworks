package org.example;

public class ProductModel extends Item {
    private final String name;
    private final int price;
    private int quantity;
    private final String unit;

    public ProductModel(String name, int price, int quantity, String unit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }
    public String getUnit() {
        return unit;}
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
