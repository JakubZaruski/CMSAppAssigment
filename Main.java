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
    
