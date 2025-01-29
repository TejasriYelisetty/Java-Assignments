import java.util.*;
import java.util.stream.Collectors;

public class EmployeeOperations {
    public static void main(String[] args) {
        // Sample employee list
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", 6000, "HR"),
            new Employee(2, "Bob", 7000, "IT"),
            new Employee(3, "Charlie", 4000, "Finance"),
            new Employee(4, "David", 8000, "IT"),
            new Employee(5, "Eve", 3000, "HR")
        );

        // 1. Find Employee with Highest Salary
        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(emp -> System.out.println("Highest Salary: " + emp));

        // 2. Filter Employees with Salary Greater Than 5000
        List<Employee> highEarners = employees.stream()
                .filter(emp -> emp.getSalary() > 5000)
                .collect(Collectors.toList());
        System.out.println("Employees with salary > 5000: " + highEarners);

        // 3. Get Employee Names and Salaries
        List<String> employeeNamesAndSalaries = employees.stream()
                .map(emp -> emp.getName() + " (Salary: " + emp.getSalary() + ")")
                .collect(Collectors.toList());
        System.out.println("Employee Names and Salaries: " + employeeNamesAndSalaries);

        // 4. Count Employees
        long employeeCount = employees.stream().count();
        System.out.println("Total Employees: " + employeeCount);

        // 5. Group Employees by Department
        Map<String, List<Employee>> groupedByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Employees Grouped by Department: " + groupedByDepartment);

        // 6. Calculate Average Salary
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average Salary: " + averageSalary);

        // 7. Sort Employees by Salary
        List<Employee> sortedBySalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());
        System.out.println("Employees Sorted by Salary: " + sortedBySalary);

        // 8. Find Employee with Second Highest Salary
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst()
                .ifPresent(emp -> System.out.println("Second Highest Salary: " + emp));

        // 9. Partition Employees by Salary Threshold
        Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 5000));
        System.out.println("Partitioned by Salary > 5000: " + partitionedBySalary);

        // 10. Find Employee with Longest Name
        employees.stream()
                .max(Comparator.comparingInt(emp -> emp.getName().length()))
                .ifPresent(emp -> System.out.println("Employee with Longest Name: " + emp));

        // 11. Group Employees by Department and Calculate Average Salary
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Average Salary by Department: " + avgSalaryByDept);

        // 12. Find Top 3 Highest Paid Employees
        List<Employee> top3HighestPaid = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 Highest Paid Employees: " + top3HighestPaid);

        // 13. Calculate Total Salary of All Employees Using Reduce
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Salary of All Employees: " + totalSalary);
    }
}
