import java.util.Scanner;
import java.util.Stack;

public class Valid_Parentheses {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string containing only {}, [], (): ");
            String input = scanner.nextLine();

            System.out.println("The parentheses are " + (isValid(input) ? "valid." : "invalid."));
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            switch (ch) {
                case '{', '[', '(' -> {
                    stack.push(ch);
                }
                case '}' -> {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
                case ']' -> {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
                case ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
                default -> {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
