package com.wiprotraining.Calculator;

import java.util.Scanner;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    public static void main(String[] args) {
        CalculatorTest test = new CalculatorTest();
        test.runCalculatorTest();
    }

    void runCalculatorTest() {
        Scanner scanner = new Scanner(System.in);

        // Loop to keep asking the user until they choose to exit
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Division by Zero Test");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            // Perform the operation based on user input
            if (choice == 1) {
                performAddition(scanner);
            } else if (choice == 2) {
                performSubtraction(scanner);
            } else if (choice == 3) {
                performMultiplication(scanner);
            } else if (choice == 4) {
                performDivision(scanner);
            } else if (choice == 5) {
                performDivisionByZero(scanner);
            } else if (choice == 6) {
                System.out.println("Exiting the program.");
                break;  // Exit the loop and end the program
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    void performAddition(Scanner scanner) {
        System.out.print("Enter first number for addition: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number for addition: ");
        int b = scanner.nextInt();

        int result = calculator.add(a, b);
        System.out.println("Result: " + a + " + " + b + " = " + result);
    }

    void performSubtraction(Scanner scanner) {
        System.out.print("Enter first number for subtraction: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number for subtraction: ");
        int b = scanner.nextInt();

        int result = calculator.subtract(a, b);
        System.out.println("Result: " + a + " - " + b + " = " + result);
    }

    void performMultiplication(Scanner scanner) {
        System.out.print("Enter first number for multiplication: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number for multiplication: ");
        int b = scanner.nextInt();

        int result = calculator.multiply(a, b);
        System.out.println("Result: " + a + " * " + b + " = " + result);
    }

    void performDivision(Scanner scanner) {
        System.out.print("Enter first number for division: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number for division: ");
        int b = scanner.nextInt();

        if (b != 0) {
            double result = calculator.divide(a, b);
            System.out.println("Result: " + a + " / " + b + " = " + result);
        } else {
            System.out.println("Error: Cannot divide by zero.");
        }
    }

    void performDivisionByZero(Scanner scanner) {
        System.out.print("Enter first number for division by zero test: ");
        int a = scanner.nextInt();
        System.out.print("Enter second number for division by zero test: ");
        int b = scanner.nextInt();

        try {
            System.out.println("Testing division by zero: " + a + " / " + b);
            calculator.divide(a, b); // This should throw an ArithmeticException if b is 0
        } catch (ArithmeticException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
