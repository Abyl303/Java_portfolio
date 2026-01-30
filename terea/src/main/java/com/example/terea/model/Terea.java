package com.example.terea.model;

import jakarta.persistence.*;
@Entity
@Table(name = "tereas")
public class Terea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String flavor;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean available;


    public Terea() {}

    public Terea(String productName, String flavor, double price, int quantity, boolean available) {
        setProductName(productName);
        setFlavor(flavor);
        setPrice(price);
        setQuantity(quantity);
        setAvailable(available);
    }

    public Integer getId() { return id; }
    public String getProductName() { return productName; }
    public String getFlavor() { return flavor; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isAvailable() { return available; }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name can't be empty!");
        }
        this.productName = productName;
    }

    public void setFlavor(String flavor) {
        if (flavor == null || flavor.trim().isEmpty()) {
            throw new IllegalArgumentException("Flavor name can't be empty!");
        }
        this.flavor = flavor;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ". Product name: " + productName +
                ". Flavor: " + flavor +
                ". Price: " + price +
                ". Quantity: " + quantity +
                ". Available: " + available;
    }
}
