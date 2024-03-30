/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */
public class Lecturer extends User {
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

    private static class scanner {

        private static String nextLine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public scanner() {
        }
    }
}
