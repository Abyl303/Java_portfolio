package com.example.oms.service;

import com.example.oms.domain.Order;
import com.example.oms.domain.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;
    private int nextItemId = 1;

    public Order createOrder(String customerName, String customerEmail, String createdAt) {
        int id = nextOrderId++;
        Order order = new Order(id, customerName, customerEmail, "NEW", createdAt);
        orders.add(order);
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Order findOrderById(int orderId) {
        for (Order o : orders) {
            if (o.getId() == orderId) return o;
        }
        return null;
    }

    public boolean removeOrderById(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderId) {
                orders.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addItemToOrder(int orderId, String productName, double price, int quantity) {
        Order order = findOrderById(orderId);
        if (order == null) return false;

        int itemId = nextItemId++;
        OrderItem item = new OrderItem(itemId, productName, price, quantity);
        order.addItem(item);
        return true;
    }

    public boolean removeItemFromOrder(int orderId, int itemId) {
        Order order = findOrderById(orderId);
        if (order == null) return false;
        return order.removeItemById(itemId);
    }

    public boolean changeOrderStatus(int orderId, String newStatus) {
        Order order = findOrderById(orderId);
        if (order == null || newStatus == null) return false;

        String target = newStatus.trim().toUpperCase();
        String current = order.getStatus();

        // правила переходов (как в ТЗ)
        boolean allowed =
                (current.equals("NEW") && (target.equals("PAID") || target.equals("CANCELLED"))) ||
                        (current.equals("PAID") && (target.equals("SHIPPED") || target.equals("CANCELLED")));

        if (!allowed) return false;

        order.setStatus(target);
        return true;
    }

    public List<Order> getOrdersByStatus(String status) {
        List<Order> result = new ArrayList<>();
        if (status == null) return result;

        String s = status.trim().toUpperCase();
        for (Order o : orders) {
            if (o.getStatus().equals(s)) result.add(o);
        }
        return result;
    }

    public List<Order> getOrdersByCustomerEmail(String email) {
        List<Order> result = new ArrayList<>();
        if (email == null) return result;

        String e = email.trim();
        for (Order o : orders) {
            if (o.getCustomerEmail().equalsIgnoreCase(e)) result.add(o);
        }
        return result;
    }

    public double getTotalRevenue() {
        double sum = 0;
        for (Order o : orders) {
            if (o.getStatus().equals("PAID") || o.getStatus().equals("SHIPPED")) {
                sum += o.getTotalAmount();
            }
        }
        return sum;
    }
}
