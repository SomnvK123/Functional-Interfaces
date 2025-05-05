package Entity;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %10.2f x %d = %.2f",
                product.getProductID(), product.getProductName(), product.getPrice(), quantity, getTotal());
    }
}
