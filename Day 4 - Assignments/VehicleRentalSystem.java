import java.util.Scanner;

abstract class Vehicle {
    protected String vehicleID;
    protected String model;
    protected double rentalPricePerDay;

    public Vehicle(String vehicleID, String model, double rentalPricePerDay) {
        this.vehicleID = vehicleID;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public abstract double calculateRentalCost(int rentalDays);

    public String getVehicleID() {
        return vehicleID;
    }

    public String getModel() {
        return model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
}

class Car extends Vehicle {

    public Car(String vehicleID, String model, double rentalPricePerDay) {
        super(vehicleID, model, rentalPricePerDay);
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * rentalPricePerDay;
    }
}

class Bike extends Vehicle {

    public Bike(String vehicleID, String model, double rentalPricePerDay) {
        super(vehicleID, model, rentalPricePerDay);
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * rentalPricePerDay;
    }
}

class RentalCustomer {
    private String customerID;
    private String name;
    private String contactInfo;

    public RentalCustomer(String customerID, String name, String contactInfo) {
        this.customerID = customerID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class Rental {
    private RentalCustomer customer;
    private Vehicle vehicle;
    private int rentalDays;

    public Rental(RentalCustomer customer, Vehicle vehicle, int rentalDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
    }

    public void rentVehicle() {
        System.out.println("Vehicle rented: " + vehicle.getModel());
        System.out.println("Customer: " + customer.getName());
        System.out.println("Rental Duration: " + rentalDays + " days");
        System.out.println("Total Rental Cost: $" + vehicle.calculateRentalCost(rentalDays));
    }

    public void returnVehicle() {
        System.out.println("Vehicle returned: " + vehicle.getModel());
        System.out.println("Customer: " + customer.getName());
        System.out.println("Rental Duration: " + rentalDays + " days");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter vehicle type (car/bike): ");
        String vehicleType = sc.nextLine();
        System.out.print("Enter vehicle ID: ");
        String vehicleID = sc.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = sc.nextLine();
        System.out.print("Enter rental price per day: ");
        double rentalPricePerDay = sc.nextDouble();
        sc.nextLine(); 

        Vehicle vehicle;
        if (vehicleType.equalsIgnoreCase("car")) {
            vehicle = new Car(vehicleID, model, rentalPricePerDay);
        } else {
            vehicle = new Bike(vehicleID, model, rentalPricePerDay);
        }

        System.out.print("Enter customer ID: ");
        String customerID = sc.nextLine();
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter customer contact info: ");
        String contactInfo = sc.nextLine();
        System.out.print("Enter rental days: ");
        int rentalDays = sc.nextInt();

        RentalCustomer customer = new RentalCustomer(customerID, name, contactInfo);
        Rental rental = new Rental(customer, vehicle, rentalDays);

        rental.rentVehicle();
        rental.returnVehicle();

        sc.close();
    }
}
