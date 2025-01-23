import java.io.*;
import java.util.*;

class Book implements Serializable {
    private final int bookId;
    private String title;
    private String author;
    private int quantity;

    public Book(int bookId, String title, String author, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

class Member implements Serializable {
    private final int memberId;
    private String name;
    private String email;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }
}

class Transaction implements Serializable {
    private final int transactionId;
    private final Book book;
    private final Member member;
    private final String transactionType;
    private final Date date;

    public Transaction(int transactionId, Book book, Member member, String transactionType, Date date) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.transactionType = transactionType;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", book=" + book.getTitle() +
                ", member=" + member.getName() +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                '}';
    }
}

public class LibraryManagementSystem {
    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE = "members.dat";
    private static final String TRANSACTIONS_FILE = "transactions.dat";

    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadDataFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Update Book");
            System.out.println("4. Update Member");
            System.out.println("5. Delete Book");
            System.out.println("6. Delete Member");
            System.out.println("7. View Books");
            System.out.println("8. View Members");
            System.out.println("9. Issue Book");
            System.out.println("10. Return Book");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> addMember(scanner);
                case 3 -> updateBook(scanner);
                case 4 -> updateMember(scanner);
                case 5 -> deleteBook(scanner);
                case 6 -> deleteMember(scanner);
                case 7 -> viewBooks();
                case 8 -> viewMembers();
                case 9 -> issueBook(scanner);
                case 10 -> returnBook(scanner);
                case 11 -> {
                    saveDataToFile();
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }

    @SuppressWarnings("unchecked")
    public static void loadDataFromFile() {
        try {
            FileInputStream fileIn;
            ObjectInputStream in;

            fileIn = new FileInputStream(BOOKS_FILE);
            in = new ObjectInputStream(fileIn);
            books = (ArrayList<Book>) in.readObject();
            in.close();

            fileIn = new FileInputStream(MEMBERS_FILE);
            in = new ObjectInputStream(fileIn);
            members = (ArrayList<Member>) in.readObject();
            in.close();

            fileIn = new FileInputStream(TRANSACTIONS_FILE);
            in = new ObjectInputStream(fileIn);
            transactions = (ArrayList<Transaction>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh.");
        }
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOut;
            ObjectOutputStream out;

            fileOut = new FileOutputStream(BOOKS_FILE);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(books);
            out.close();

            fileOut = new FileOutputStream(MEMBERS_FILE);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(members);
            out.close();

            fileOut = new FileOutputStream(TRANSACTIONS_FILE);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(transactions);
            out.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static Member findMemberById(int memberId) {
        return members.stream().filter(member -> member.getMemberId() == memberId).findFirst().orElse(null);
    }

    private static Book findBookById(int bookId) {
        return books.stream().filter(book -> book.getBookId() == bookId).findFirst().orElse(null);
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        Book book = new Book(bookId, title, author, quantity);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    private static void addMember(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Member member = new Member(memberId, name, email);
        members.add(member);
        System.out.println("Member added successfully.");
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        Member member = findMemberById(memberId);

        if (member != null) {
            System.out.print("Enter book ID: ");
            int bookId = scanner.nextInt();
            Book book = findBookById(bookId);

            if (book != null && book.getQuantity() > 0) {
                member.addBook(book);
                book.setQuantity(book.getQuantity() - 1);
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book not available.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        Member member = findMemberById(memberId);

        if (member != null) {
            System.out.print("Enter book ID: ");
            int bookId = scanner.nextInt();
            Book book = findBookById(bookId);

            if (book != null) {
                member.returnBook(book);
                book.setQuantity(book.getQuantity() + 1);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void viewBooks() {
        books.forEach(System.out::println);
    }

    private static void viewMembers() {
        members.forEach(System.out::println);
    }

    public static void updateBook(Scanner scanner) {
        System.out.print("Enter the Book ID you want to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book bookToUpdate = findBookById(bookId);

        if (bookToUpdate != null) {
            System.out.print("Enter the new title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter the new author: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter the new quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();

            bookToUpdate.setTitle(newTitle);
            bookToUpdate.setAuthor(newAuthor);
            bookToUpdate.setQuantity(newQuantity);

            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void updateMember(Scanner scanner) {
        System.out.print("Enter the Member ID you want to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member memberToUpdate = findMemberById(memberId);

        if (memberToUpdate != null) {
            System.out.print("Enter the new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter the new email: ");
            String newEmail = scanner.nextLine();

            memberToUpdate.setName(newName);
            memberToUpdate.setEmail(newEmail);

            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter the Book ID you want to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book bookToDelete = findBookById(bookId);

        if (bookToDelete != null) {
            books.remove(bookToDelete);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteMember(Scanner scanner) {
        System.out.print("Enter the Member ID you want to delete: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member memberToDelete = findMemberById(memberId);

        if (memberToDelete != null) {
            members.remove(memberToDelete);
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }
}
