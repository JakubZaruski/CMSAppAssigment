/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final  Scanner scanner = new Scanner(System.in);
    private static final  List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMessage();

        // User authentication
        User user = login();

        // Proceed based on user role
        if (user != null) {
            switch (user.getRole()) {
                case ADMIN:
                    handleAdmin((Admin) user);
                    break;
                case OFFICE:
                    handleOffice((Office) user);
                    break;
                case LECTURER:
                    handleLecturer((Lecturer) user);
                    break;
                default:
                    System.out.println("Invalid user role.");
            }
        } else {
            System.out.println("Login failed. Exiting the application.");
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the CMSApp!");
    }

    private static User login() {
        System.out.println("Please login");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check against predefined admin credentials
        if (username.equals("admin") && password.equals("java")) {
            return new Admin(username, password);
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
private static void handleAdmin(Admin admin) {
        System.out.println("Welcome, Admin!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add User");
            System.out.println("2. Modify User");
            System.out.println("3. Delete User");
            System.out.println("4. Change Own Credentials");
            System.out.println("5. Logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addUser(admin);
                    break;
                case 2:
                    modifyUser(admin);
                    break;
                case 3:
                    deleteUser(admin);
                    break;
                case 4:
                    changeOwnCredentials(admin);
                    break;
                case 5:
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addUser(Admin admin) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter role (ADMIN, OFFICE, LECTURER):");
        String roleStr = scanner.nextLine().toUpperCase();
        UserRole role = UserRole.valueOf(roleStr);

        User newUser = new User(username, password, role);
        userList.add(newUser); // Add user to userList
        admin.addUser(newUser); // Add user to admin's userList
        System.out.println("User added successfully.");
    }
    
    private static void modifyUser(Admin admin) {
        System.out.println("Enter username of user to modify:");
        String username = scanner.nextLine();

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        System.out.println("Enter new role (ADMIN, OFFICE, LECTURER):");
        String roleStr = scanner.nextLine().toUpperCase();
        UserRole newRole = UserRole.valueOf(roleStr);

        admin.modifyUser(username, newPassword, newRole);
        System.out.println("User modified successfully");
    }
    
    private static void deleteUser(Admin admin) {
        System.out.println("Enter username of user to delete:");
        String username = scanner.nextLine();

        admin.deleteUser(username);
        System.out.println("User deleted successfully");
    }

    public static void changeOwnCredentials(Admin admin) {
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        admin.changeOwnCredentials(newUsername, newPassword);
        System.out.println("Own credentials changed successfully");
    }
    
    private static void handleOffice(Office office) {
        System.out.println("Welcome, Office!");
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Generate Course Report");
            System.out.println("2. Generate Student Report");
            System.out.println("3. Generate Lecturer Report");
            System.out.println("4. Change Own Credentials");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    office.generateCourseReport(); // Assuming you implement this in Office
                    break;
                case 2:
                    office.generateStudentReport(); // Assuming you implement this in Office
                    break;
                case 3:
                    office.generateLecturerReport(); // Assuming you implement this in Office
                    break;
                case 4:
                    office.changeCredentials(); // Change this method's visibility if necessary
                    break;
                case 5:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void handleLecturer(Lecturer lecturer) {
        System.out.println("Welcome, Lecturer!");
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Generate Lecturer Report");
            System.out.println("2. Change Own Credentials");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    lecturer.generateLecturerReport(); // Ensure this method exists in Lecturer
                    break;
                case 2:
                    lecturer.changeCredentials(); // Change this method's visibility if necessary
                    break;
                case 3:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
    
