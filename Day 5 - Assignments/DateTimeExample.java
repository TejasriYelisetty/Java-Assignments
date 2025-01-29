import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExample {
    public static void main(String[] args) {
        // Get the current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        System.out.println("Current Date: " + currentDate);
        System.out.println("Current Time: " + currentTime);
        System.out.println("Current Date and Time: " + currentDateTime);

        // Formatting the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Formatted Date and Time: " + formattedDateTime);

        // Adding 10 days to the current date
        LocalDate futureDate = currentDate.plusDays(10);
        System.out.println("Date after 10 days: " + futureDate);

        // Subtracting 5 hours from the current time
        LocalTime pastTime = currentTime.minusHours(5);
        System.out.println("Time 5 hours ago: " + pastTime);

        // Calculating the difference between two dates
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Days between " + startDate + " and " + endDate + ": " + daysBetween);

        // Using ZonedDateTime for time zones
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Current Date and Time in New York: " + zonedDateTime);

        // Parsing a date from a string
        String dateStr = "15-02-2025";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(dateStr, dateFormatter);
        System.out.println("Parsed Date: " + parsedDate);
    }
}
