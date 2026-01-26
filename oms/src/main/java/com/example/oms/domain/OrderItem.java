package com.example.oms.domain;

public class OrderItem {
    private final int id;
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(int id, String productName, double price, int quantity) {
        this.id = id;
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
    }

    public int getId() { return id; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name can't be empty!");
        }
        this.productName = productName.trim();
    }

    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0!");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0!");
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
