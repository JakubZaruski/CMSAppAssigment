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
 * Represents an Office user in the system, extending the basic functionalities of a User.
 * This class is responsible for generating various reports and managing office-specific credentials.
 */
public class Office extends User {
    /**
     * Constructs a new Office instance with specified username and password, and sets the role to OFFICE.
     *
     * @param username the username of the office user
     * @param password the password for the office user
     */
    public Office(String username, String password) {
        super(username, password, UserRole.OFFICE);
    }
/**
     * Generates a report summarizing course data. This method is a placeholder.
     */    
    public void generateCourseReport() {
        // Placeholder for course report generation logic
        System.out.println("Generating Course Report...");
        
        System.out.println("Course Report generated successfully.");
    }
/**
     * Generates a report summarizing student data. This method is a placeholder.
     */
    public void generateStudentReport() {
        // Placeholder for student report generation logic
        System.out.println("Generating Student Report...");
        
        System.out.println("Student Report generated successfully.");
    }
/**
     * Generates a report summarizing lecturer data. This method is a placeholder.
     */
    public void generateLecturerReport() {
        // Placeholder for lecturer report generation logic
        System.out.println("Generating Lecturer Report...");
        
        System.out.println("Lecturer Report generated successfully.");
    }
/**
     * Allows changing the credentials of an Office instance. 
     *
     * @deprecated This method is deprecated. Use {@link #changeOwnCredentials(String, String)} instead.
     * @param office The office instance to update credentials for.
     */
    @Deprecated
public static void changeOwnCredentials(Office office) {
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();

        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        office.changeOwnCredentials(newUsername, newPassword);
        System.out.println("Own credentials changed successfully");
    }
    void generateReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

/**
     * Updates the credentials for this Office user.
     *
     * @param newUsername the new username to set
     * @param newPassword the new password to set
     */
    void changeOwnCredentials(String newUsername, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void changeCredentials() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class scanner {
/**
         * Reads the next line of input from the console.
         * 
         * @return A string containing the line read from the console.
         */
        private static String nextLine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public scanner() {
        }
    }
}
