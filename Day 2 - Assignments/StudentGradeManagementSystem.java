import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private final String name;
    private final int studentId;
    private final ArrayList<Grade> grades;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        double totalPoints = 0;
        for (Grade grade : grades) {
            totalPoints += grade.getGradePoint();
        }
        return grades.isEmpty() ? 0 : totalPoints / grades.size();
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name;
    }
}

class Course {
    private final String courseName;
    private final int courseId;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Course Name: " + courseName;
    }
}

class Grade {
    private final Course course;
    private final double gradePoint;

    public Grade(Course course, double gradePoint) {
        this.course = course;
        this.gradePoint = gradePoint;
    }

    public Course getCourse() {
        return course;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    @Override
    public String toString() {
        return course.getCourseName() + " - Grade: " + gradePoint;
    }
}

public class StudentGradeManagementSystem {
    private final static ArrayList<Student> students = new ArrayList<>();
    private final static ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        courses.add(new Course(1, "Mathematics"));
        courses.add(new Course(2, "Physics"));
        courses.add(new Course(3, "Chemistry"));

        int choice;
        do {
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade to Student");
            System.out.println("4. Calculate GPA for a Student");
            System.out.println("5. Display All Students and their GPA");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> addCourse(scanner);
                case 3 -> assignGrade(scanner);
                case 4 -> calculateGPA(scanner);
                case 5 -> displayStudentsGPA();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid Student ID.");
            scanner.next();
        }
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentId, name);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid Course ID.");
            scanner.next();
        }
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        Course course = new Course(courseId, courseName);
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    private static void assignGrade(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid Student ID.");
            scanner.next();
        }
        int studentId = scanner.nextInt();
        Student student = findStudentById(studentId);

        if (student != null) {
            System.out.print("Enter Course ID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid Course ID.");
                scanner.next();
            }
            int courseId = scanner.nextInt();
            Course course = findCourseById(courseId);

            if (course != null) {
                System.out.print("Enter Grade (0.0 - 4.0): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Please enter a valid grade (numeric value between 0.0 and 4.0).");
                    scanner.next();
                }
                double gradePoint = scanner.nextDouble();

                Grade grade = new Grade(course, gradePoint);
                student.addGrade(grade);
                System.out.println("Grade assigned successfully.");
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void calculateGPA(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid Student ID.");
            scanner.next();
        }
        int studentId = scanner.nextInt();
        Student student = findStudentById(studentId);

        if (student != null) {
            double gpa = student.calculateGPA();
            System.out.println("Student " + student.getName() + "'s GPA is: " + gpa);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayStudentsGPA() {
        for (Student student : students) {
            double gpa = student.calculateGPA();
            System.out.println(student.getName() + " - GPA: " + gpa);
        }
    }

    private static Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
}
