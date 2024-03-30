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
}