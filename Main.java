
import Data.SampleData;
import Entity.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Product> products = SampleData.getProducts();
        List<Customer> customers = SampleData.getCustomers();
        List<Order> orders = SampleData.generateOrders(products, customers);

        printAllOrders(orders);
        System.out.println("==============================================\n");
        getTop3ProductsByRevenue(orders);
        System.out.println("==============================================\n");
        getTotalRevenueByCustomer(orders);
        System.out.println("==============================================\n");
        getMaxOrderPerCustomer(orders);
        System.out.println("==============================================\n");
        getTotalRevenueByMonth(orders);
        System.out.println("==============================================\n");
        getMaxCustomerInTime(orders,
                Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(2023, 10, 30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println("==============================================\n");
    }

    // 3 sp doanh thu cao nhat
    public static void getTop3ProductsByRevenue(List<Order> orders) {
        // Map<Product, Double> product = orders.stream().flatMap(order ->
        // order.getItems().stream())
        // .collect(Collectors.groupingBy(OrderItem::getProduct,
        // Collectors.summingDouble(OrderItem::getTotal)));

        // System.out.println("Top 3 products with the highest revenue:");
        // product.entrySet().stream().sorted(Map.Entry.<Product,
        // Double>comparingByValue().reversed()).limit(3)
        // .forEach(e -> {
        // Product p = e.getKey();
        // double revenue = e.getValue();
        // System.out.printf("Product: %s, Revenue: %.2f\n", p.getProductName(),
        // revenue);
        // });

        Function<Order, Stream<OrderItem>> getItems = order -> order.getItems().stream(); // Lấy danh sách sản phẩm
                                                                                          // trong đơn hàng
        Function<OrderItem, Product> getProduct = OrderItem::getProduct; // Lấy sản phẩm từ OrderItem
        ToDoubleFunction<OrderItem> getTotal = OrderItem::getTotal; // Lấy tổng giá trị của sản phẩm

        Comparator<Map.Entry<Product, Double>> comparator = Map.Entry.<Product, Double>comparingByValue().reversed();
        Consumer<Map.Entry<Product, Double>> printProduct = e -> { // In thông tin sản phẩm
            Product p = e.getKey();
            double revenue = e.getValue();
            System.out.printf("Product: %s, Revenue: %.2f\n", p.getProductName(), revenue);
        };

        System.out.println("Top 3 products with the highest revenue:");
        orders.stream()
                .flatMap(getItems) // Lấy danh sách sản phẩm từ tất cả đơn hàng
                .collect(Collectors.groupingBy(getProduct, Collectors.summingDouble(getTotal))) // Nhóm theo sản phẩm và
                                                                                                // tính tổng doanh thu
                .entrySet().stream() // Chuyển đổi thành Stream<Map.Entry<Product, Double>>
                .sorted(comparator) // Sắp xếp theo doanh thu giảm dần
                .limit(3)
                .forEach(printProduct);
    }

    // Tính tổng doanh thu theo từng khách hàng
    public static void getTotalRevenueByCustomer(List<Order> orders) {
        // Map<Customer, Double> customer =
        // orders.stream().collect(Collectors.groupingBy(Order::getCustomer,
        // Collectors.summingDouble(o ->
        // o.getItems().stream().mapToDouble(OrderItem::getTotal).sum())));

        // System.out.println("Total revenue by customer:");
        // customer.forEach((c, revenue) -> {
        // System.out.printf("Customer: %s, Revenue: %.2f\n", c.getName(), revenue);
        // });

        Function<Order, Customer> getCustomer = Order::getCustomer; // Lấy khách hàng từ đơn hàng
        ToDoubleFunction<Order> getTotal = order -> order.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
        Consumer<Map.Entry<Customer, Double>> printCustomer = e -> {
            Customer c = e.getKey();
            double revenue = e.getValue();
            System.out.printf("Customer: %s, Revenue: %.2f\n", c.getName(), revenue);
        };

        System.out.println("Total revenue by customer:");
        orders.stream()
                .collect(Collectors.groupingBy(getCustomer, Collectors.summingDouble(getTotal)))
                .entrySet().stream()
                .forEach(printCustomer);
    }

    // Tìm đơn hàng có giá trị lớn nhất của mỗi khách hàng
    public static void getMaxOrderPerCustomer(List<Order> orders) {
        // Map<Customer, Order> order = orders.stream().collect(
        // Collectors.groupingBy(
        // Order::getCustomer,
        // Collectors.collectingAndThen(
        // Collectors.maxBy(Comparator.comparingDouble(
        // o -> o.getItems().stream().mapToDouble(OrderItem::getTotal).sum())),
        // Optional::get)));

        // System.out.println("Max order per customer:");
        // order.forEach((c, o) -> {
        // System.out.printf("Customer: %s, Order Product: %s, Total: %.2f\n",
        // c.getName(),
        // o.getItems().get(0).getProduct().getProductName(),
        // o.getItems().stream().mapToDouble(OrderItem::getTotal).sum());
        // });

        Function<Order, Customer> getCustomer = Order::getCustomer; // Lấy khách hàng từ đơn hàng
        ToDoubleFunction<Order> getTotal = order -> order.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
        Comparator<Order> comparator = Comparator.comparingDouble(getTotal).reversed();
        Consumer<Map.Entry<Customer, Optional<Order>>> printOrder = e -> {
            Customer c = e.getKey();
            Optional<Order> o = e.getValue();
            if (o.isPresent()) {
                Order order = o.get();
                double total = getTotal.applyAsDouble(order);
                System.out.printf("Customer: %s, Order Product: %s, Total: %.2f\n", c.getName(),
                        order.getItems().get(0).getProduct().getProductName(), total);
            } else {
                System.out.printf("Customer: %s, No orders found.\n", c.getName());
            }
        };

        System.out.println("Max order per customer:");
        orders.stream()
                .collect(Collectors.groupingBy(getCustomer, Collectors.maxBy(comparator)))
                .entrySet().stream()
                .forEach(printOrder);
    }

    // Phân loại đơn hàng theo tháng và tính tổng doanh thu mỗi tháng
    public static void getTotalRevenueByMonth(List<Order> orders) {
        // Map<YearMonth, Double> order = orders.stream()
        // .collect(Collectors.groupingBy(o -> YearMonth.from(o.getOrderDate()),
        // Collectors.summingDouble(o ->
        // o.getItems().stream().mapToDouble(OrderItem::getTotal).sum())));

        // System.out.println("Total revenue by month:");
        // order.forEach((m, revenue) -> {
        // System.out.printf("Month: %s, Revenue: %.2f\n", m, revenue);
        // });

        Function<Order, YearMonth> getMonth = order -> YearMonth.from(order.getOrderDate()); // Lấy tháng từ đơn hàng
        ToDoubleFunction<Order> getTotal = order -> order.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
        Consumer<Map.Entry<YearMonth, Double>> printMonth = e -> {
            YearMonth m = e.getKey();
            double revenue = e.getValue();
            System.out.printf("Month: %s, Revenue: %.2f\n", m, revenue);
        };

        System.out.println("Total revenue by month:");
        orders.stream()
                .collect(Collectors.groupingBy(getMonth, Collectors.summingDouble(getTotal)))
                .entrySet().stream()
                .forEach(printMonth);
    }

    // Tìm khách hàng chi tiêu nhiều nhất trong một khoảng thời gian
    public static void getMaxCustomerInTime(List<Order> orders, Date startDate, Date endDate) {
        Predicate<Order> date = order -> {
            LocalDate orderDate = order.getOrderDate();
            return !orderDate.isBefore(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) &&
                    !orderDate.isAfter(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        };

        // Map<Customer, Double> customerSpending = orders.stream()
        // .filter(date)
        // .collect(Collectors.groupingBy(Order::getCustomer,
        // Collectors.summingDouble(o ->
        // o.getItems().stream().mapToDouble(OrderItem::getTotal).sum())));
        Function<Order, Customer> getCustomers = Order::getCustomer; // Lấy khách hàng từ đơn hàng
        ToDoubleFunction<Order> getTotal = order -> order.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
        Comparator<Map.Entry<Customer, Double>> comparator = Map.Entry.<Customer, Double>comparingByValue();

        System.out.println("Max customer in time:");
        orders.stream()
                .filter(date)
                .collect(Collectors.groupingBy(getCustomers,
                        Collectors.summingDouble(getTotal)))
                .entrySet().stream()
                .max(comparator)
                .ifPresent(e -> System.out.printf("Customer: %s, Total Spending: %.2f\n",
                        e.getKey().getName(),
                        e.getValue()));

        // Optional<Map.Entry<Customer, Double>> maxCustomer =
        // customerSpending.entrySet().stream()
        // .max(Map.Entry.comparingByValue());

        // if (maxCustomer.isPresent()) {
        // Map.Entry<Customer, Double> entry = maxCustomer.get();
        // System.out.printf("Customer: %s, Total Spending: %.2f\n",
        // entry.getKey().getName(), entry.getValue());
        // } else {
        // System.out.println("No customers found in the given time range.");
        // }
    }

    // In danh sách đơn hàng de xem cho de
    public static void printAllOrders(List<Order> orders) {
        System.out.println("=========== ORDER LIST ===========");

        for (Order order : orders) {
            System.out.printf("Order ID    : %s\n", order.getOrderID());
            System.out.printf("Customer    : %s (%s | %s)\n",
                    order.getCustomer().getName(),
                    order.getCustomer().getEmail(),
                    order.getCustomer().getPhone());
            System.out.printf("Order Date  : %s\n", order.getOrderDate());
            System.out.println("Products:");

            System.out.printf("%-10s %-20s %10s\n", "Product ID", "Product Name", "Price");
            System.out.println("----------------------------------------------");

            for (OrderItem p : order.getItems()) {
                System.out.printf("%-10s %-20s %10.2f\n",
                        p.getProduct().getProductID(), p.getProduct().getProductName(), p.getProduct().getPrice());
                System.out.printf("Quantity    : %d\n", p.getQuantity());
            }

            System.out.println("----------------------------------------------");
            System.out.printf("ORDER TOTAL : %.2f\n", order.getTotal());
            System.out.println("==============================================\n");
        }
    }

}
