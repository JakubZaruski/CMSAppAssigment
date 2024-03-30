/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LecturerReport {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcomeMessage();

        // Provide options to output the report in different formats
        System.out.println("\nChoose an option:");
        System.out.println("1. Output to TXT file");
        System.out.println("2. Output to CSV file");
        System.out.println("3. Output to Console");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                outputToTxtFile("LecturerReport");
                break;
            case 2:
                outputToCsvFile("LecturerReport");
                break;
            case 3:
                outputToConsole();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the CMSApp!");
    }

    private static String getLecturerReportAsString() {
        StringBuilder report = new StringBuilder();
        // Append the report header
        report.append("Lecturer Report:\n");
        report.append("Lecturer Name | Title | Expertise | Modules Taught | Student Count\n");

        // SQL query to fetch the lecturer report information
        String sql = "SELECT l.name AS lecturer_name, l.title AS lecturer_title, l.expertise, " +
                     "       GROUP_CONCAT(DISTINCT c.name) AS modules_taught, " +
                     "       COUNT(DISTINCT e.student_id) AS student_count " +
                     "FROM Lecturers l " +
                     "JOIN Courses c ON l.lecturer_id = c.lecturer_id " +
                     "LEFT JOIN Enrollments e ON c.course_id = e.course_id " +
                     "GROUP BY l.name, l.title, l.expertise";

        // Append data for each lecturer
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String lecturerName = rs.getString("lecturer_name");
                String lecturerTitle = rs.getString("lecturer_title");
                String expertise = rs.getString("expertise");
                String modulesTaught = rs.getString("modules_taught");
                int studentCount = rs.getInt("student_count");

                report.append(lecturerName).append(" | ").append(lecturerTitle).append(" | ").append(expertise)
                        .append(" | ").append(modulesTaught).append(" | ").append(studentCount).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report.toString();
    }

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is registered
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDatabase?useSSL=false", "root", "planbskate1");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    private static void outputToTxtFile(String fileName) {
        String content = getLecturerReportAsString(); // Get report data as string
        // Implementation to write report to a txt file
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(content);
            System.out.println("Lecturer report written to " + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputToCsvFile(String fileName) {
        String content = getLecturerReportAsString(); // Get report data as string
        // Implementation to write report to a csv file
        try (FileWriter writer = new FileWriter(fileName + ".csv")) {
            writer.write(content);
            System.out.println("Lecturer report written to " + fileName + ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputToConsole() {
        String content = getLecturerReportAsString(); // Get report data as string
        // Implementation to output report to the console
        System.out.println(content);
    }
}
