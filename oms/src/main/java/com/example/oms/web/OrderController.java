package com.example.oms.web;

import com.example.oms.domain.Order;
import com.example.oms.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody CreateOrderRequest req) {
        return service.createOrder(req.customerName(), req.customerEmail(), req.createdAt());
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id) {
        Order o = service.findOrderById(id);
        if (o == null) throw new IllegalArgumentException("Order not found");
        return o;
    }

    @DeleteMapping("/orders/{id}")
    public boolean deleteOrder(@PathVariable int id) {
        return service.removeOrderById(id);
    }

    @PostMapping("/orders/{id}/items")
    public boolean addItem(@PathVariable int id, @RequestBody AddItemRequest req) {
        return service.addItemToOrder(id, req.productName(), req.price(), req.quantity());
    }

    @DeleteMapping("/orders/{id}/items/{itemId}")
    public boolean deleteItem(@PathVariable int id, @PathVariable int itemId) {
        return service.removeItemFromOrder(id, itemId);
    }

    @PutMapping("/orders/{id}/status")
    public boolean changeStatus(@PathVariable int id, @RequestBody ChangeStatusRequest req) {
        return service.changeOrderStatus(id, req.newStatus());
    }

    @GetMapping("/orders/search/by-status")
    public List<Order> byStatus(@RequestParam String status) {
        return service.getOrdersByStatus(status);
    }

    @GetMapping("/orders/search/by-email")
    public List<Order> byEmail(@RequestParam String email) {
        return service.getOrdersByCustomerEmail(email);
    }

    @GetMapping("/orders/revenue")
    public double revenue() {
        return service.getTotalRevenue();
    }

    public record CreateOrderRequest(String customerName, String customerEmail, String createdAt) {}
    public record AddItemRequest(String productName, double price, int quantity) {}
    public record ChangeStatusRequest(String newStatus) {}
}
