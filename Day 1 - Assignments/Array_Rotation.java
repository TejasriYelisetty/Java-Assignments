import java.util.Scanner;

public class Array_Rotation {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            System.out.print("Enter the elements of the array (separated by space): ");
            String input = scanner.nextLine();  // Read the entire line

            String[] inputArray = input.split(" ");  // Split the input by spaces
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = Integer.parseInt(inputArray[i]);  // Convert to integer and store in array
            }

            System.out.print("Enter the number of rotations: ");
            int rotations = scanner.nextInt();
            rotations %= size;  // To handle rotations larger than the array size

            scanner.nextLine();  // Clear the buffer
            System.out.print("Enter direction (left or right): ");
            String direction = scanner.nextLine();

            switch (direction) {
                case "right" -> rotateRight(array, rotations);
                case "left" -> rotateLeft(array, rotations);
                default -> System.out.println("Invalid direction.");
            }
        }
    }

    public static void rotateRight(int[] array, int rotations) {
        int size = array.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[(i + rotations) % size] = array[i];
        }
        printArray(result);
    }

    public static void rotateLeft(int[] array, int rotations) {
        int size = array.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[(i - rotations + size) % size] = array[i];
        }
        printArray(result);
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
