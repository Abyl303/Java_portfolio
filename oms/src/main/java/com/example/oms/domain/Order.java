package com.example.oms.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private String customerName;
    private String customerEmail;
    private String status;   // NEW, PAID, SHIPPED, CANCELLED
    private String createdAt;
    private double totalAmount;

    private final List<OrderItem> items = new ArrayList<>();

    public Order(int id, String customerName, String customerEmail, String status, String createdAt) {
        this.id = id;
        setCustomerName(customerName);
        setCustomerEmail(customerEmail);
        setStatus(status);
        setCreatedAt(createdAt);
        recalculateTotal();
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public double getTotalAmount() { return totalAmount; }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty())
            throw new IllegalArgumentException("Customer name can't be empty!");
        this.customerName = customerName.trim();
    }

    public void setCustomerEmail(String customerEmail) {
        if (customerEmail == null || customerEmail.trim().isEmpty())
            throw new IllegalArgumentException("Customer email can't be empty!");
        if (!customerEmail.contains("@"))
            throw new IllegalArgumentException("Customer email must contain '@'!");
        this.customerEmail = customerEmail.trim();
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty())
            throw new IllegalArgumentException("Status can't be empty!");
        String s = status.trim().toUpperCase();
        if (!s.equals("NEW") && !s.equals("PAID") && !s.equals("SHIPPED") && !s.equals("CANCELLED"))
            throw new IllegalArgumentException("Invalid status!");
        this.status = s;
    }

    public void setCreatedAt(String createdAt) {
        if (createdAt == null || createdAt.trim().isEmpty())
            throw new IllegalArgumentException("CreatedAt can't be empty!");
        this.createdAt = createdAt.trim();
    }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("Item can't be null!");
        items.add(item);
        recalculateTotal();
    }

    public boolean removeItemById(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == itemId) {
                items.remove(i);
                recalculateTotal();
                return true;
            }
        }
        return false;
    }

    public void recalculateTotal() {
        double sum = 0;
        for (OrderItem it : items) sum += it.getTotalPrice();
        this.totalAmount = sum;
    }
}
