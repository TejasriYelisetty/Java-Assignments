import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Employee class - Base class for all employee types
abstract class Employee {
    private final String name;
    private final int employeeId;
    private double basicSalary;

    // Constructor
    public Employee(String name, int employeeId, double basicSalary) {
        this.name = name;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    // Setter method
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    // Abstract methods for calculating salary and bonus, which will be implemented in subclasses
    public abstract double calculateSalary();
    public abstract double calculateBonus();
}

// PermanentEmployee class (inherits from Employee)
class PermanentEmployee extends Employee {

    private static final double BONUS_PERCENTAGE = 0.2; // 20% bonus

    public PermanentEmployee(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    // Implement method to calculate salary (basic + bonus)
    @Override
    public double calculateSalary() {
        return getBasicSalary() + calculateBonus();
    }

    // Implement method to calculate bonus (20% of basic salary)
    @Override
    public double calculateBonus() {
        return getBasicSalary() * BONUS_PERCENTAGE;
    }
}

// ContractualEmployee class (inherits from Employee)
class ContractualEmployee extends Employee {

    private static final double BONUS_PERCENTAGE = 0.0; // No bonus for contractual employees

    public ContractualEmployee(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    // Implement method to calculate salary (basic only, no bonus)
    @Override
    public double calculateSalary() {
        return getBasicSalary() + calculateBonus();
    }

    // Implement method to calculate bonus (0% bonus for contractual employees)
    @Override
    public double calculateBonus() {
        return getBasicSalary() * BONUS_PERCENTAGE;
    }
}

// Payroll class - responsible for calculating salary, bonuses, and generating reports
class Payroll {

    // Method to calculate the total salary for an employee (basic salary + bonus)
    public static double calculateSalary(Employee employee) {
        return employee.calculateSalary();
    }

    // Method to calculate the bonus for an employee
    public static double calculateBonus(Employee employee) {
        return employee.calculateBonus();
    }

    // Method to generate payroll reports for a department
    public static void generateReport(List<Employee> employees) {
        System.out.println("Employee Payroll Report");
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeId() + ", Name: " + employee.getName());
            System.out.println("Basic Salary: " + employee.getBasicSalary());
            System.out.println("Salary: " + calculateSalary(employee));
            System.out.println("Bonus: " + calculateBonus(employee));
            System.out.println("----------------------------");
        }
    }
}

// Department class - to manage employees and generate payroll report
class Department {
    private final List<Employee> employees;

    // Constructor
    public Department() {
        this.employees = new ArrayList<>();
    }

    // Method to add an employee to the department
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Method to get the list of employees in the department
    public List<Employee> getEmployees() {
        return employees;
    }

    // Method to generate a payroll report for the department
    public void generatePayrollReport() {
        Payroll.generateReport(employees);
    }
}

// Main class - Entry point for the program
public class EmployeePayrollSystem {
    public static void main(String[] args) {

        // Using try-with-resources to ensure Scanner is properly closed
        try (Scanner scanner = new Scanner(System.in)) {
            // Create a department
            Department department = new Department();

            // Loop to add employees
            while (true) {
                System.out.println("\nEnter Employee Details:");

                System.out.print("Enter Employee Type (1 for Permanent, 2 for Contractual, 0 to Exit): ");
                int employeeType = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (employeeType == 0) {
                    break; // Exit the loop
                }

                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Employee ID: ");
                int employeeId = scanner.nextInt();

                System.out.print("Enter Basic Salary: ");
                double basicSalary = scanner.nextDouble();
                scanner.nextLine();  // Consume newline

                // Create the employee based on the input type
                Employee employee = null;
                if (employeeType == 1) {
                    employee = new PermanentEmployee(name, employeeId, basicSalary);
                } else if (employeeType == 2) {
                    employee = new ContractualEmployee(name, employeeId, basicSalary);
                }

                if (employee != null) {
                    department.addEmployee(employee);
                    System.out.println("Employee added successfully.");
                } else {
                    System.out.println("Invalid employee type. Please try again.");
                }
            }

            // Generate payroll report for the department
            department.generatePayrollReport();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
