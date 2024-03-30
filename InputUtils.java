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
    