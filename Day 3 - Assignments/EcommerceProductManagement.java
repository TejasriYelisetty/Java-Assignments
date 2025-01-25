import java.io.*;
import java.util.*;

public class EcommerceProductManagement {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			ECommercePlatform platform = new ECommercePlatform();
			
			System.out.print("Enter number of products to add to catalog: ");
			int numProducts = scanner.nextInt();
			scanner.nextLine();
			
			for (int i = 0; i < numProducts; i++) {
			    System.out.print("Enter product name: ");
			    String productName = scanner.nextLine();
			    System.out.print("Enter product price: ");
			    double price = scanner.nextDouble();
			    scanner.nextLine();
			    platform.addProductToCatalog(productName, price);
			}
			
			System.out.print("Enter customer name: ");
			String customerName = scanner.nextLine();
			System.out.print("Enter customer email: ");
			String customerEmail = scanner.nextLine();
			CustomerOrder customer = new CustomerOrder(customerName, customerEmail);  // Updated to CustomerOrder
			
			Order order = platform.createOrder(customer);
			
			System.out.println("Available products:");
			platform.displayProductCatalog();
			
			String addMoreProducts = "yes";
			while (addMoreProducts.equalsIgnoreCase("yes")) {
			    System.out.print("Enter product name to add to the order: ");
			    String productName = scanner.nextLine();
			    Product product = platform.getProductFromCatalog(productName);
			    if (product != null) {
			        order.addProduct(product);
			        System.out.println("Product added to the order.");
			    } else {
			        System.out.println("Product not found.");
			    }
			    
			    System.out.print("Add another product? (yes/no): ");
			    addMoreProducts = scanner.nextLine();
			}
			
			System.out.println(order);
			platform.saveOrderHistoryToFile();
		}
        System.out.println("Order history saved to order_history.txt");
    }
}

class Product {
    private String productName;
    private double price;
    
    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public double getPrice() {
        return price;
    }
}

class Order {
    private CustomerOrder customer;
    private List<Product> products;
    private double totalCost;
    
    public Order(CustomerOrder customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalCost = 0.0;
    }
    
    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public CustomerOrder getCustomer() {
        return customer;
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order for Customer: ").append(customer.getName()).append("\n");
        orderDetails.append("Products:\n");
        for (Product product : products) {
            orderDetails.append(product.getProductName()).append(" - $").append(product.getPrice()).append("\n");
        }
        orderDetails.append("Total Cost: $").append(totalCost).append("\n");
        return orderDetails.toString();
    }
}

class CustomerOrder {
    private String name;
    private String email;
    
    public CustomerOrder(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}

class ECommercePlatform {
    private List<Product> productCatalog;
    private List<Order> orders;
    
    public ECommercePlatform() {
        productCatalog = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public void addProductToCatalog(String productName, double price) {
        Product product = new Product(productName, price);
        productCatalog.add(product);
    }
    
    public void removeProductFromCatalog(String productName) {
        productCatalog.removeIf(product -> product.getProductName().equals(productName));
    }
    
    public Product getProductFromCatalog(String productName) {
        for (Product product : productCatalog) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
    
    public Order createOrder(CustomerOrder customer) {
        Order order = new Order(customer);
        orders.add(order);
        return order;
    }
    
    public void saveOrderHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.write("\n---------------------\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void displayProductCatalog() {
        System.out.println("Product Catalog:");
        for (Product product : productCatalog) {
            System.out.println(product.getProductName() + " - $" + product.getPrice());
        }
    }
}
