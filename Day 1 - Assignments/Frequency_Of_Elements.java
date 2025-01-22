import java.util.Scanner;

public class Frequency_Of_Elements {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();

            int[] array = new int[size];
            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Frequency of each element:");
            for (int i = 0; i < size; i++) {
                int count = 1;
                if (array[i] != Integer.MIN_VALUE) {
                    for (int j = i + 1; j < size; j++) {
                        if (array[i] == array[j]) {
                            count++;
                            array[j] = Integer.MIN_VALUE; // Mark as counted
                        }
                    }
                    System.out.println(array[i] + " appears " + count + " times");
                }
            }
        }
    }
}
