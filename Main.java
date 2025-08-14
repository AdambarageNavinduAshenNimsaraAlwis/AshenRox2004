package mainapp;

import java.util.Scanner;
import models.Student;
import services.EnrollmentManager;

public class Main {
    private static final int MAX_STUDENTS = 100;
    private static Student[] students = new Student[MAX_STUDENTS];
    private static int studentCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Student Management System Started!\n");
        
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-4): ");
            
            // Handle input more robustly
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number between 1-4");
                scanner.next(); // discard invalid input
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline
            
            switch (choice) {
                case 1:
                    addStudentDetails();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1-4");
            }
        }
        
        System.out.println("\nProgram exited. Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Exit");
    }

    private static void addStudentDetails() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Error: Maximum student capacity reached!");
            return;
        }

        System.out.println("\n--- Add New Student ---");
        
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        students[studentCount] = new Student(id, name);
        studentCount++;
        
        System.out.println("Success! Student added:");
        System.out.println(students[studentCount-1]);
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        
        if (studentCount == 0) {
            System.out.println("No students in system.");
            return;
        }
        
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i+1) + ". " + students[i]);
        }
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();
        
        Student found = EnrollmentManager.searchStudentById(students, studentCount, id);
        
        if (found != null) {
            System.out.println("Student found: " + found);
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }
}