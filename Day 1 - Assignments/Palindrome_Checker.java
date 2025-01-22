import java.util.Scanner;

public class Palindrome_Checker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();

            input = input.replaceAll("\\s", "").toLowerCase();

            int left = 0;
            int right = input.length() - 1;

            boolean isPalindrome = true;
            while (left < right) {
                if (input.charAt(left) != input.charAt(right)) {
                    isPalindrome = false;
                    break;
                }
                left++;
                right--;
            }

            if (isPalindrome) {
                System.out.println("The string is a palindrome.");
            } else {
                System.out.println("The string is not a palindrome.");
            }
        }
    }
}
