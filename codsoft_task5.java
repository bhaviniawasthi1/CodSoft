//TASK 5- STUDENT MANAGEMENT SYSTEM

import java.io.*;
import java.util.*;

// Class representing a single student
class Student implements Serializable {

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters
    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Display student info
    public void display() {
        System.out.println("Name: " + name +
                " | Roll No: " + rollNumber +
                " | Grade: " + grade);
    }
}


// Main management system class
public class codsoft_task5 {

    private List<Student> students;
    private final String FILE_NAME = "students.dat";
    private Scanner scanner;

    public codsoft_task5() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadFromFile(); // load existing data if available
    }

    // Add new student
    private void addStudent() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter roll number: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid roll number.");
            scanner.nextLine();
            return;
        }
        int roll = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();

        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty.");
            return;
        }

        // Check duplicate roll number
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Student with this roll number already exists.");
                return;
            }
        }

        students.add(new Student(name, roll, grade));
        System.out.println("Student added successfully.");
    }

    // Remove student
    private void removeStudent() {

        System.out.print("Enter roll number to remove: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == roll) {
                iterator.remove();
                System.out.println("Student removed.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Search student
    private void searchStudent() {

        System.out.print("Enter roll number to search: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Student found:");
                s.display();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Edit student details
    private void editStudent() {

        System.out.print("Enter roll number to edit: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {

                System.out.print("Enter new name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Enter new grade: ");
                String grade = scanner.nextLine().trim();

                if (!name.isEmpty()) {
                    s.setName(name);
                }

                if (!grade.isEmpty()) {
                    s.setGrade(grade);
                }

                System.out.println("Student updated successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Display all students
    private void displayAll() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.display();
        }
    }

    // Save data to file
    private void saveToFile() {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            oos.writeObject(students);

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load data from file
    private void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            students = (List<Student>) ois.readObject();

        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
    }

    // Menu-driven interface
    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    editStudent();
                    break;
                case 5:
                    displayAll();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    // Entry point
    public static void main(String[] args) {
        codsoft_task5 system = new codsoft_task5();
        system.start();
    }
}