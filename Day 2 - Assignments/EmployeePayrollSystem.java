import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private final String name;
    private final int employeeId;
    private double basicSalary;

    public Employee(String name, int employeeId, double basicSalary) {
        this.name = name;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public abstract double calculateSalary();
    public abstract double calculateBonus();
}

class PermanentEmployee extends Employee {
    private static final double BONUS_PERCENTAGE = 0.2;

    public PermanentEmployee(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    public double calculateSalary() {
        return getBasicSalary() + calculateBonus();
    }

    @Override
    public double calculateBonus() {
        return getBasicSalary() * BONUS_PERCENTAGE;
    }
}

class ContractualEmployee extends Employee {
    private static final double BONUS_PERCENTAGE = 0.0;

    public ContractualEmployee(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    public double calculateSalary() {
        return getBasicSalary() + calculateBonus();
    }

    @Override
    public double calculateBonus() {
        return getBasicSalary() * BONUS_PERCENTAGE;
    }
}

class Payroll {
    public static double calculateSalary(Employee employee) {
        return employee.calculateSalary();
    }

    public static double calculateBonus(Employee employee) {
        return employee.calculateBonus();
    }

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

class Department {
    private final List<Employee> employees;

    public Department() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void generatePayrollReport() {
        Payroll.generateReport(employees);
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Department department = new Department();

            while (true) {
                System.out.println("\nEnter Employee Details:");

                System.out.print("Enter Employee Type (1 for Permanent, 2 for Contractual, 0 to Exit): ");
                int employeeType = scanner.nextInt();
                scanner.nextLine();

                if (employeeType == 0) {
                    break;
                }

                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Employee ID: ");
                int employeeId = scanner.nextInt();

                System.out.print("Enter Basic Salary: ");
                double basicSalary = scanner.nextDouble();
                scanner.nextLine();

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

            department.generatePayrollReport();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
