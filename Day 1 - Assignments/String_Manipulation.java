import java.util.LinkedHashMap;
import java.util.Scanner;

public class String_Manipulation {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();
            
            input = input.toLowerCase();
            
            LinkedHashMap<Character, Integer> frequencyMap = new LinkedHashMap<>();
            
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                
                if (Character.isLetter(ch)) {
                    frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
                }
            }
            
            for (char ch : frequencyMap.keySet()) {
                System.out.println(ch + ": " + frequencyMap.get(ch));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
