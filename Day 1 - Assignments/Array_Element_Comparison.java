import java.util.Scanner;

public class Array_Element_Comparison {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the size of the arrays: ");
            int size = scanner.nextInt();

            int[] array1 = new int[size];
            int[] array2 = new int[size];

            System.out.println("Enter the elements of the first array:");
            for (int i = 0; i < size; i++) {
                array1[i] = scanner.nextInt();
            }

            System.out.println("Enter the elements of the second array:");
            for (int i = 0; i < size; i++) {
                array2[i] = scanner.nextInt();
            }

            System.out.println("Comparing elements of the two arrays:");
            for (int i = 0; i < size; i++) {
                if (array1[i] == array2[i]) {
                    System.out.println("Element " + array1[i] + " is equal to " + array2[i]);
                } else if (array1[i] > array2[i]) {
                    System.out.println("Element " + array1[i] + " is greater than " + array2[i]);
                } else {
                    System.out.println("Element " + array1[i] + " is lesser than " + array2[i]);
                }
            }
        }
    }
}
