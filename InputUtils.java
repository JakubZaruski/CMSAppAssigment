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

/**
 * The InputUtils class contains utility methods for handling user inputs and actions within the CMS application.
 * It facilitates user interaction for adding, modifying, and deleting users, among other functionalities.
 */
public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
 * Prompts the admin to add a new user by entering the user's details.
 * Validates the input before adding the user.
 *
 * @param admin The admin performing the operation.
 * @param validator to check inputs.
 */
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
    
    /**
     * Allows the admin to modify an existing user's details after validation.
     *
     * @param admin The admin performing the operation.
     * @param validator to check the new password's validity.
     */
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
    /**
     * Enables the admin to delete a user from the system.
     *
     * @param admin The admin requesting the user deletion.
     */
    public static void deleteUser(Admin admin) {
        System.out.println("Enter username of user to delete:");
        String username = scanner.nextLine();
        admin.deleteUser(username);
        System.out.println("User deleted successfully");
    }
    /**
     * Allows admins to change their own credentials after validation.
     *
     * @param admin The admin changing their credentials.
     * @param validator to check the new password's validity.
     */
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
    /**
     * Presents office users with options to generate reports or manage their credentials.
     *
     * @param office The office user interacting with the system.
     */
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
    /**
     * Provides lecturer users with options to generate reports or manage their credentials.
     *
     * @param lecturer The lecturer user interacting with the system.
     */
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
    /**
 * Prompts the user for an integer input and returns the value.
 * Repeatedly prompts the user until a valid integer is entered.
 *
 * @param message the message to display to the user prompting for input
 * @return the integer value entered by the user
 */
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
    /**
     * DataValidator class for validating user inputs.
     */
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
    
    // Placeholder for missing class/interface declarations
   
    private static class User {

        private User(String username, String password, UserRole role) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    private static class Admin {

        private void changeOwnCredentials(String newUsername, String newPassword) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void deleteUser(String username) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void modifyUser(String username, String newPassword, UserRole newRole) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void addUser(User newUser) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    private static enum UserRole {}
    private static class Office {

        private void generateReports() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void changeOwnCredentials(String newUsername, String newPassword) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    private static class Lecturer {

        private void generateLecturerReport() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void changeOwnCredentials(String newUsername, String newPassword) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
