/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */
import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addUser(Admin admin, DataValidator validator) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter role (ADMIN, OFFICE, LECTURER):");
        String roleStr = scanner.nextLine().toUpperCase();
        UserRole role = UserRole.valueOf(roleStr);

        if (validator.validate(username) && validator.validate(password)) {
            User newUser = new User(username, password, role);
            admin.addUser(newUser);
            System.out.println("User added successfully.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    
    public static void modifyUser(Admin admin, DataValidator validator) {
        System.out.println("Enter username of user to modify:");
        String username = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        System.out.println("Enter new role (ADMIN, OFFICE, LECTURER):");
        String roleStr = scanner.nextLine().toUpperCase();
        UserRole newRole = UserRole.valueOf(roleStr);

        if (validator.validate(newPassword)) {
            admin.modifyUser(username, newPassword, newRole);
            System.out.println("User modified successfully");
        } else {
            System.out.println("Invalid password.");
        }
    }
    
    public static void deleteUser(Admin admin) {
        System.out.println("Enter username of user to delete:");
        String username = scanner.nextLine();
        admin.deleteUser(username);
        System.out.println("User deleted successfully");
    }

    public static void changeOwnCredentials(Admin admin, DataValidator validator) {
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        if (validator.validate(newPassword)) {
            admin.changeOwnCredentials(newUsername, newPassword);
            System.out.println("Own credentials changed successfully");
        } else {
            System.out.println("Invalid password.");
        }
    }
    
    public static void handleOffice(Office office) {
        System.out.println("Welcome, Office!");
        int choice;
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Generate Reports");
            System.out.println("2. Change Own Credentials");
            System.out.println("3. Logout");
            choice = promptInt("Enter your choice:");

            switch (choice) {
                case 1:
                    office.generateReports();
                    break;
                case 2:
                    System.out.println("Enter new username:");
                    String newUsername = scanner.nextLine();
                    System.out.println("Enter new password:");
                    String newPassword = scanner.nextLine();
                    office.changeOwnCredentials(newUsername, newPassword);
                    System.out.println("Credentials changed successfully.");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
    
    public static void handleLecturer(Lecturer lecturer) {
        System.out.println("Welcome, Lecturer!");
        int choice;
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Generate Lecturer Report");
            System.out.println("2. Change Own Credentials");
            System.out.println("3. Logout");
            choice = promptInt("Enter your choice:");

            switch (choice) {
                case 1:
                    lecturer.generateLecturerReport();
                    break;
                case 2:
                    System.out.println("Enter new username:");
                    String newUsername = scanner.nextLine();
                    System.out.println("Enter new password:");
                    String newPassword = scanner.nextLine();
                    lecturer.changeOwnCredentials(newUsername, newPassword);
                    System.out.println("Credentials changed successfully.");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
    
     private static int promptInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
    
    private static class DataValidator {
        // Constructor
        public DataValidator() {
        }

        // Validate username
        public boolean validateUsername(String username) {
            if (username == null) {
                return false;
            }
            return username.length() >= 4 && username.length() <= 20;
        }

        // Validate password
        public boolean validatePassword(String password) {
            if (password == null) {
                return false;
            }
            return password.length() >= 8;
        }

        // General validation method that can be used for other types of data
        public boolean validate(String data) {
            return validateUsername(data) || validatePassword(data);
        }
    }
    
