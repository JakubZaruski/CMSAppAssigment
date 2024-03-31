/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;

import com.cmsapp.users.User;
import com.cmsapp.users.UserRole;

/**
 *
 * @author jakubzaruski
 */
/**
 * Represents a Lecturer, extending the User class with specific functionalities for lecturers.
 * This class allows for the generation of lecturer reports and the ability to change own credentials.
 */
public class Lecturer extends User {
    /**
     * Constructs a new Lecturer instance with specified username, password, and assigns the role of LECTURER.
     * 
     * @param username the username of the lecturer
     * @param password the password for the lecturer
     */
    public Lecturer(String username, String password) {
        super(username, password, UserRole.LECTURER);
    }
    
    public void generateLecturerReport() {
        // Placeholder for lecturer report generation logic
        System.out.println("Generating Lecturer Report...");
        // Implement the logic to generate the lecturer report
        // Fetch and format lecturer and module data for the report.
        System.out.println("Lecturer Report generated successfully.");
    }


public static void changeOwnCredentials(Office office) {
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        office.changeOwnCredentials(newUsername, newPassword);
        System.out.println("Own credentials changed successfully");
    }
/**
     * Changes the credentials (username and password) of the given Office instance.
     * 
     * @param office The office instance whose credentials are to be changed.
     */
    void changeCredentials() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/**
     * Scanner utility class for reading input from the console.
     */
    private static class scanner {
/**
         * Reads the next line of text from the console.
         * 
         * @return The line of text entered by the user.
         */
        private static String nextLine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public scanner() {
        }
    }
}
