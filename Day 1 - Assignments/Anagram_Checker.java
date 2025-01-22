import java.util.Scanner;

public class Anagram_Checker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first string: ");
            String str1 = scanner.nextLine();
            
            System.out.print("Enter second string: ");
            String str2 = scanner.nextLine();
            
            if (str1.length() != str2.length()) {
                System.out.println("Not anagrams");
            } else {
                int[] count = new int[256];
                for (int i = 0; i < str1.length(); i++) {
                    count[str1.charAt(i)]++;
                    count[str2.charAt(i)]--;
                }
                boolean isAnagram = true;
                for (int i = 0; i < 256; i++) {
                    if (count[i] != 0) {
                        isAnagram = false;
                        break;
                    }
                }
                if (isAnagram) {
                    System.out.println("Anagrams");
                } else {
                    System.out.println("Not anagrams");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
