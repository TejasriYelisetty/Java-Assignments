import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

class Patient {
    private String name;
    private int age;
    private String id;

    public Patient(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient[Name=" + name + ", Age=" + age + ", ID=" + id + "]";
    }
}

class Doctor {
    private String name;
    private String specialization;
    private String id;

    public Doctor(String name, String specialization, String id) {
        this.name = name;
        this.specialization = specialization;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor[Name=" + name + ", Specialization=" + specialization + ", ID=" + id + "]";
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDate appointmentDate;

    public Appointment(Patient patient, Doctor doctor, LocalDate appointmentDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment[Patient=" + patient.getName() + ", Doctor=" + doctor.getName() + 
               ", Date=" + appointmentDate + "]";
    }
}

public class HospitalManagementSystem {

    private static Map<String, Doctor> doctors = new HashMap<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int patientCounter = 1;

    public static void main(String[] args) {
        boolean exit = false;

        doctors.put("D1", new Doctor("Dr. Smith", "Cardiologist", "D1"));
        doctors.put("D2", new Doctor("Dr. Johnson", "Dermatologist", "D2"));

        while (!exit) {
            try {
                System.out.println("\nHospital Management System");
                System.out.println("1. Schedule Appointment");
                System.out.println("2. View Appointments");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        scheduleAppointment();
                        break;
                    case 2:
                        viewAppointments();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void scheduleAppointment() {
        try {
            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Patient Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            String patientId = "P" + patientCounter++;
            Patient patient = new Patient(name, age, patientId);

            System.out.println("Patient created: " + patient);

            System.out.print("Enter Doctor ID (e.g., D1): ");
            String doctorId = sc.nextLine();
            Doctor doctor = doctors.get(doctorId);

            if (doctor == null) {
                throw new IllegalArgumentException("Doctor not found!");
            }

            System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
            String dateStr = sc.nextLine();
            LocalDate appointmentDate = validateDate(dateStr);

            Appointment appointment = new Appointment(patient, doctor, appointmentDate);
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully for " + patient.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format or invalid date! Please use yyyy-mm-dd and ensure the date exists.");
        } catch (Exception e) {
            System.out.println("An error occurred while scheduling the appointment: " + e.getMessage());
        }
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    private static LocalDate validateDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            LocalDate today = LocalDate.now();
            if (date.isBefore(today)) {
                throw new DateTimeParseException("The appointment date cannot be in the past.", dateStr, 0);
            }

            return date;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date: " + dateStr, dateStr, 0);
        }
    }
}
