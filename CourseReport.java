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

public class CourseReport {
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
                outputToTxtFile("CourseReport");
                break;
            case 2:
                outputToCsvFile("CourseReport");
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

    private static String getCourseReportAsString() {
        StringBuilder report = new StringBuilder();
        // Append the report header
        report.append("Course Report:\n");
        report.append("Module | Program | Enrolled Students | Lecturer | Room\n");

        // SQL query to fetch the course report information
        String query = "SELECT c.name AS module, p.program_name, COUNT(e.student_id) AS enrolled_students, "
                + "l.name AS lecturer, c.room "
                + "FROM Courses c "
                + "JOIN Programs p ON c.program_id = p.program_id "
                + "JOIN Enrollments e ON c.course_id = e.course_id "
                + "JOIN Lecturers l ON c.lecturer_id = l.lecturer_id "
                + "GROUP BY c.name, p.program_name, l.name, c.room";

        // Append data for each course
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String module = rs.getString("module");
                String program = rs.getString("program_name");
                int enrolledStudents = rs.getInt("enrolled_students");
                String lecturer = rs.getString("lecturer");
                String room = rs.getString("room").equalsIgnoreCase("Online") ? "Online" : rs.getString("room");

                report.append(module).append(" | ").append(program).append(" | ").append(enrolledStudents)
                        .append(" | ").append(lecturer).append(" | ").append(room).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report.toString();
    }

    private static String getCourseReportAsStringForCsv() {
        StringBuilder report = new StringBuilder();
        // Append the header for CSV
        report.append("Module, Program, Enrolled Students, Lecturer, Room\n");

        // SQL query to fetch the course report information
        String query = "SELECT c.name AS module, p.program_name, COUNT(e.student_id) AS enrolled_students, "
                + "l.name AS lecturer, c.room "
                + "FROM Courses c "
                + "JOIN Programs p ON c.program_id = p.program_id "
                + "JOIN Enrollments e ON c.course_id = e.course_id "
                + "JOIN Lecturers l ON c.lecturer_id = l.lecturer_id "
                + "GROUP BY c.name, p.program_name, l.name, c.room";

        // Append data for each course
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String module = rs.getString("module");
                String program = rs.getString("program_name");
                int enrolledStudents = rs.getInt("enrolled_students");
                String lecturer = rs.getString("lecturer");
                String room = rs.getString("room").equalsIgnoreCase("Online") ? "Online" : rs.getString("room");

                report.append(module).append(",").append(program).append(",").append(enrolledStudents)
                        .append(",").append(lecturer).append(",").append(room).append("\n");
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
        String content = getCourseReportAsString(); // Get report data as string
        // Implementation to write report to a txt file
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(content);
            System.out.println("Course report written to " + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputToCsvFile(String fileName) {
        String content = getCourseReportAsStringForCsv(); // Get report data as CSV string
        // Implementation to write report to a csv file
        try (FileWriter writer = new FileWriter(fileName + ".csv")) {
            writer.write(content);
            System.out.println("Course report written to " + fileName + ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputToConsole() {
        String content = getCourseReportAsString(); // Get report data as string
        // Implementation to output report to the console
        System.out.println(content);
    }
}

