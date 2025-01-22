import java.util.Scanner;

public class Subarray_Sum {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();

            int[] array = new int[size];
            System.out.print("Enter the elements of the array: ");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            int maxSum = array[0];
            int currentSum = array[0];
            int start = 0, end = 0, tempStart = 0;

            for (int i = 1; i < size; i++) {
                if (array[i] > currentSum + array[i]) {
                    currentSum = array[i];
                    tempStart = i;
                } else {
                    currentSum = currentSum + array[i];
                }

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    start = tempStart;
                    end = i;
                }
            }

            System.out.println("Maximum sum of contiguous subarray: " + maxSum);
            System.out.print("Subarray with the maximum sum: ");
            for (int i = start; i <= end; i++) {
                System.out.print(array[i] + " ");
            }
        }
    }
}
