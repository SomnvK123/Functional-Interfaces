package Data;

import Entity.*;
import java.time.LocalDate;
import java.util.*;

public class SampleData {
        public static List<Product> getProducts() {
                return Arrays.asList(
                                new Product("P001", "Laptop", 1500.0, 10, "High performance laptop"),
                                new Product("P002", "Smartphone", 800.0, 20, "Latest model smartphone"),
                                new Product("P003", "Tablet", 500.0, 15, "Portable tablet"),
                                new Product("P004", "Headphones", 200.0, 30, "Noise-cancelling headphones"),
                                new Product("P005", "Smartwatch", 300.0, 25, "Fitness tracking smartwatch"),
                                new Product("P006", "Camera", 1200.0, 5, "High-resolution camera"),
                                new Product("P007", "Monitor", 400.0, 12, "4K Ultra HD monitor"),
                                new Product("P008", "Keyboard", 100.0, 50, "Mechanical keyboard"),
                                new Product("P009", "Mouse", 50.0, 40, "Wireless mouse"),
                                new Product("P010", "Printer", 250.0, 8, "All-in-one printer"));
        }

        public static List<Customer> getCustomers() {
                return Arrays.asList(
                                new Customer("C001", "John Doe", "YV5yP@example.com", "123-456-7890"),
                                new Customer("C002", "Jane Smith", "gHk6s@example.com", "987-654-3210"),
                                new Customer("C003", "Alice Johnson", "Bb4bI@example.com", "555-123-4567"),
                                new Customer("C004", "Bob Williams", "k3v6U@example.com", "111-222-3333"),
                                new Customer("C005", "Eva Brown", "Bb4bI@example.com", "555-123-4567"));
        }

        public static List<Order> generateOrders(List<Product> products, List<Customer> customers) {
                Map<String, Product> productMap = new HashMap<>();
                for (Product product : products) {
                        productMap.put(product.getProductID(), product);
                }
                List<Order> orders = new ArrayList<>();
                orders.add(new Order("O001", customers.get(0), List.of(
                                new OrderItem(productMap.get("P001"), 1),
                                new OrderItem(productMap.get("P002"), 2)),
                                LocalDate.of(2023, 10, 1)));
                orders.add(new Order("O002", customers.get(1), List.of(
                                new OrderItem(productMap.get("P003"), 1),
                                new OrderItem(productMap.get("P004"), 1)),
                                LocalDate.of(2023, 10, 2)));

                orders.add(new Order("O003", customers.get(2), List.of(
                                new OrderItem(productMap.get("P005"), 1),
                                new OrderItem(productMap.get("P006"), 1)),
                                LocalDate.of(2023, 10, 3)));

                orders.add(new Order("O004", customers.get(3), List.of(
                                new OrderItem(productMap.get("P007"), 1),
                                new OrderItem(productMap.get("P008"), 1)),
                                LocalDate.of(2023, 10, 4)));

                orders.add(new Order("O005", customers.get(4), List.of(
                                new OrderItem(productMap.get("P009"), 1),
                                new OrderItem(productMap.get("P010"), 1)),
                                LocalDate.of(2023, 10, 5)));

                orders.add(new Order("O006", customers.get(0), List.of(
                                new OrderItem(productMap.get("P001"), 1),
                                new OrderItem(productMap.get("P002"), 2)),
                                LocalDate.of(2023, 10, 6)));

                orders.add(new Order("O007", customers.get(1), List.of(
                                new OrderItem(productMap.get("P003"), 1),
                                new OrderItem(productMap.get("P004"), 1)),
                                LocalDate.of(2023, 10, 7)));

                orders.add(new Order("O008", customers.get(2), List.of(
                                new OrderItem(productMap.get("P005"), 1),
                                new OrderItem(productMap.get("P006"), 1)),
                                LocalDate.of(2023, 10, 8)));

                orders.add(new Order("O009", customers.get(3), List.of(
                                new OrderItem(productMap.get("P007"), 1),
                                new OrderItem(productMap.get("P008"), 1)),
                                LocalDate.of(2023, 10, 9)));

                orders.add(new Order("O010", customers.get(4), List.of(
                                new OrderItem(productMap.get("P009"), 1),
                                new OrderItem(productMap.get("P010"), 1)),
                                LocalDate.of(2023, 10, 10)));

                orders.add(new Order("O011", customers.get(0), List.of(
                                new OrderItem(productMap.get("P001"), 1),
                                new OrderItem(productMap.get("P002"), 2)),
                                LocalDate.of(2023, 10, 11)));

                orders.add(new Order("O012", customers.get(1), List.of(
                                new OrderItem(productMap.get("P003"), 1),
                                new OrderItem(productMap.get("P004"), 1)),
                                LocalDate.of(2023, 10, 12)));

                orders.add(new Order("O013", customers.get(2), List.of(
                                new OrderItem(productMap.get("P005"), 1),
                                new OrderItem(productMap.get("P006"), 1)),
                                LocalDate.of(2023, 10, 13)));

                orders.add(new Order("O014", customers.get(3), List.of(
                                new OrderItem(productMap.get("P007"), 1),
                                new OrderItem(productMap.get("P008"), 1)),
                                LocalDate.of(2023, 10, 14)));

                orders.add(new Order("O015", customers.get(4), List.of(
                                new OrderItem(productMap.get("P009"), 1),
                                new OrderItem(productMap.get("P010"), 1)),
                                LocalDate.of(2023, 9, 15)));

                orders.add(new Order("O016", customers.get(0), List.of(
                                new OrderItem(productMap.get("P001"), 1),
                                new OrderItem(productMap.get("P002"), 2)),
                                LocalDate.of(2023, 11, 16)));

                orders.add(new Order("O017", customers.get(1), List.of(
                                new OrderItem(productMap.get("P003"), 1),
                                new OrderItem(productMap.get("P004"), 1)),
                                LocalDate.of(2023, 8, 17)));

                orders.add(new Order("O018", customers.get(2), List.of(
                                new OrderItem(productMap.get("P005"), 1),
                                new OrderItem(productMap.get("P006"), 1)),
                                LocalDate.of(2023, 11, 18)));

                orders.add(new Order("O019", customers.get(3), List.of(
                                new OrderItem(productMap.get("P007"), 1),
                                new OrderItem(productMap.get("P008"), 1)),
                                LocalDate.of(2023, 12, 19)));

                orders.add(new Order("O020", customers.get(4), List.of(
                                new OrderItem(productMap.get("P009"), 1),
                                new OrderItem(productMap.get("P010"), 1)),
                                LocalDate.of(2023, 11, 20)));

                return orders;
        }
}
