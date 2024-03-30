/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */
public class Office extends User {
    public Office(String username, String password) {
        super(username, password, UserRole.OFFICE);
    }
    
    public void generateCourseReport() {
        // Placeholder for course report generation logic
        System.out.println("Generating Course Report...");
        
        System.out.println("Course Report generated successfully.");
    }

    public void generateStudentReport() {
        // Placeholder for student report generation logic
        System.out.println("Generating Student Report...");
        
        System.out.println("Student Report generated successfully.");
    }

    public void generateLecturerReport() {
        // Placeholder for lecturer report generation logic
        System.out.println("Generating Lecturer Report...");
        
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
    void generateReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    void changeOwnCredentials(String newUsername, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class scanner {

        private static String nextLine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public scanner() {
        }
    }
}
