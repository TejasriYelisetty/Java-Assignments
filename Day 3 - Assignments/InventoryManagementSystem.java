import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

interface Supplier {
    void addProduct(InventoryProduct product);
}

interface Inventory {
    void addProduct(InventoryProduct product);
    void updateProduct(InventoryProduct product, int quantity);
    void removeProduct(InventoryProduct product);
    void checkLowStock();
}

class InventoryProduct {
    private String name;
    private double price;
    private int stock;

    public InventoryProduct(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    public boolean isLowStock() {
        return this.stock < 5;
    }

    @Override
    public String toString() {
        return name + " | Price: " + price + " | Stock: " + stock;
    }
}

class InventoryManager implements Supplier, Inventory {
    private List<InventoryProduct> products;

    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(InventoryProduct product) {
        products.add(product);
        System.out.println("Product added: " + product);
    }

    @Override
    public void updateProduct(InventoryProduct product, int quantity) {
        if (products.contains(product)) {
            product.updateStock(quantity);
            System.out.println("Updated product stock: " + product);
        } else {
            System.out.println("Product not found in inventory!");
        }
    }

    @Override
    public void removeProduct(InventoryProduct product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Product removed: " + product);
        } else {
            System.out.println("Product not found in inventory!");
        }
    }

    @Override
    public void checkLowStock() {
        System.out.println("Low Stock Products:");
        for (InventoryProduct product : products) {
            if (product.isLowStock()) {
                System.out.println(product);
            }
        }
    }

    // Getter for products list to access it outside the class
    public List<InventoryProduct> getProducts() {
        return products;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();

        while (true) {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Stock");
            System.out.println("3. Remove Product");
            System.out.println("4. Check Low Stock Products");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    InventoryProduct newProduct = new InventoryProduct(name, price, stock);
                    inventory.addProduct(newProduct);
                    break;

                case 2:
                    System.out.print("Enter product name to update stock: ");
                    String updateName = scanner.nextLine();
                    InventoryProduct updateProduct = null;
                    for (InventoryProduct product : inventory.getProducts()) {
                        if (product.getName().equalsIgnoreCase(updateName)) {
                            updateProduct = product;
                            break;
                        }
                    }
                    if (updateProduct != null) {
                        System.out.print("Enter quantity to update (positive to add, negative to subtract): ");
                        int quantity = scanner.nextInt();
                        inventory.updateProduct(updateProduct, quantity);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = scanner.nextLine();
                    InventoryProduct removeProduct = null;
                    for (InventoryProduct product : inventory.getProducts()) {
                        if (product.getName().equalsIgnoreCase(removeName)) {
                            removeProduct = product;
                            break;
                        }
                    }
                    if (removeProduct != null) {
                        inventory.removeProduct(removeProduct);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 4:
                    inventory.checkLowStock();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
