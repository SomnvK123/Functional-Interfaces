package Entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String orderID;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDate orderDate;

    public Order(String orderID, Customer customer, List<OrderItem> items, LocalDate orderDate) {
        this.orderID = orderID;
        this.customer = customer;
        this.items = items != null ? items : List.of();
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customer=" + customer +
                ", items=" + items +
                ", orderDate=" + orderDate +
                '}';
    }
}
