import java.util.Scanner;

class Room {
    protected int roomNumber;
    protected String roomType;
    protected boolean isAvailable;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public void bookRoom() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Room " + roomNumber + " has been booked.");
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }

    public void checkOut() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Checked out from Room " + roomNumber + ".");
        } else {
            System.out.println("Room " + roomNumber + " is already available.");
        }
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Room Type: " + roomType + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber) {
        super(roomNumber, "Standard");
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) {
        super(roomNumber, "Deluxe");
    }
}

class HotelCustomer {
    private String name;
    private int customerId;

    public HotelCustomer(String name, int customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }
}

class HotelBookingSystem {
    private Room[] rooms;

    public HotelBookingSystem(int numRooms) {
        rooms = new Room[numRooms];
        for (int i = 0; i < 5; i++) {
            rooms[i] = new StandardRoom(i + 1);
        }
        for (int i = 5; i < numRooms; i++) {
            rooms[i] = new DeluxeRoom(i + 1);
        }
    }

    public void checkAvailability() {
        int standardCount = 0;
        int deluxeCount = 0;

        for (Room room : rooms) {
            if (room.roomType.equals("Standard") && room.checkAvailability()) {
                standardCount++;
            } else if (room.roomType.equals("Deluxe") && room.checkAvailability()) {
                deluxeCount++;
            }
        }

        System.out.println("Available Standard Rooms: " + standardCount);
        System.out.println("Available Deluxe Rooms: " + deluxeCount);
    }

    public void bookRoom(HotelCustomer customer, int roomNumber) {
        if (roomNumber < 1 || roomNumber > 10) {
            System.out.println("Invalid room number. Please select a room between 1-10.");
            return;
        }
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                room.bookRoom();
                return;
            }
        }
        System.out.println("Room number " + roomNumber + " not found.");
    }

    public void checkOut(int roomNumber) {
        if (roomNumber < 1 || roomNumber > 10) {
            System.out.println("Invalid room number. Please select a room between 1-10.");
            return;
        }
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                room.checkOut();
                return;
            }
        }
        System.out.println("Room number " + roomNumber + " not found.");
    }
}

public class HotelRoomBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelBookingSystem bookingSystem = new HotelBookingSystem(10);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your customer ID: ");
        int customerId = scanner.nextInt();

        HotelCustomer customer = new HotelCustomer(name, customerId);

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Book a room");
            System.out.println("2. Check out a room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                break; // Exit the loop if the user chooses option 3
            }

            if (choice == 1) {
                // Book a room
                System.out.println("\nAvailable rooms:");
                bookingSystem.checkAvailability();

                System.out.print("Enter the room number you want to book (1-5 for Standard, 6-10 for Deluxe): ");
                int roomNumber = scanner.nextInt();
                bookingSystem.bookRoom(customer, roomNumber);

                System.out.println("\nAvailable rooms after booking:");
                bookingSystem.checkAvailability();
            } else if (choice == 2) {
                // Check out a room
                System.out.println("\nAvailable rooms:");
                bookingSystem.checkAvailability();

                System.out.print("Enter the room number you want to check out from (1-5 for Standard, 6-10 for Deluxe): ");
                int roomNumber = scanner.nextInt();
                bookingSystem.checkOut(roomNumber);

                System.out.println("\nAvailable rooms after check-out:");
                bookingSystem.checkAvailability();
            } else {
                System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
