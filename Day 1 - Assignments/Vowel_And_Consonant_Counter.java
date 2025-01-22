import java.util.Scanner;

public class Vowel_And_Consonant_Counter {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();

            int vowelCount = 0;
            int consonantCount = 0;

            input = input.toLowerCase();

            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);

                switch (ch) {
                    case 'a', 'e', 'i', 'o', 'u' -> vowelCount++;
                    default -> {
                        if ((ch >= 'a' && ch <= 'z')) {
                            consonantCount++;
                        }
                    }
                }
            }

            System.out.println("Number of vowels: " + vowelCount);
            System.out.println("Number of consonants: " + consonantCount);
        }
    }
}
