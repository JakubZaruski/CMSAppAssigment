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
/**
 * The StudentReport class is responsible for generating and displaying student reports.
 * It supports outputting the report in several formats including TXT, CSV, or directly to the console.
 */
public class StudentReport {
    private static final Scanner scanner = new Scanner(System.in);
/**
     * The main method offers options to output the student report in different formats.
     * 
     * @param args Command line arguments.
     */
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
                outputToTxtFile("StudentReport");
                break;
            case 2:
                outputToCsvFile("StudentReport");
                break;
            case 3:
                outputToConsole();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
/**
     * Displays a welcome message at the beginning of the report generation process.
     */
    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the CMSApp!");
    }
/**
     * Generates a detailed student report by querying the database and organizing the data.
     * This method formats the report for direct console output but does not display it.
     */
    private static void generateStudentReport() {
        String sql = "SELECT s.name AS student_name, s.student_id, p.program_name, " +
                     "       c.name AS course_name, e.semester, g.grade " +
                     "FROM Students s " +
                     "JOIN Programs p ON s.program_id = p.program_id " +
                     "LEFT JOIN Enrollments e ON s.student_id = e.student_id " +
                     "LEFT JOIN Courses c ON e.course_id = c.course_id " +
                     "LEFT JOIN Grades g ON e.enrollment_id = g.enrollment_id " +
                     "ORDER BY s.student_id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String prevStudentId = "";
            System.out.println("Student Report:\n");

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String programName = rs.getString("program_name");
                String courseName = rs.getString("course_name");
                String semester = rs.getString("semester");
                String grade = rs.getString("grade");

                if (!studentId.equals(prevStudentId)) {
                    if (!prevStudentId.isEmpty()) {
                        System.out.println(); // Print newline before the next student's courses
                    }
                    System.out.println("Student ID: " + studentId);
                    System.out.println("Student Name: " + studentName);
                    System.out.println("Program: " + programName);
                    System.out.println("\nEnrolled Courses:");
                }

                if (courseName != null) {
                    System.out.println("- " + courseName + " (Semester: " + semester + ", Grade: " + grade + ")");
                }

                prevStudentId = studentId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
     * Establishes a connection to the database to retrieve student data.
     * 
     * @return A Connection object for the database.
     * @throws RuntimeException If unable to connect to the database.
     */
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is registered
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDatabase?useSSL=false", "root", "planbskate1");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
/**
     * Outputs the student report to a text file with a given file name.
     * 
     * @param fileName The name of the file to which the report will be written.
     */
    private static void outputToTxtFile(String fileName) {
        generateStudentReport(); // Generates the report

        // Implementation to write report to a txt file
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            // Define the content of the txt file
            String content = "Student Report\n\n";
            // Append the report data to the content
            // You may want to add the data from the generateStudentReport method here
            writer.write(content);
            System.out.println("Student report written to " + fileName + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
     * Outputs the student report to a CSV file with a specified file name.
     * 
     * @param fileName The name of the file to which the report will be written.
     */
    private static void outputToCsvFile(String fileName) {
        generateStudentReport(); // Generates the report

        // Implementation to write report to a csv file
        try (FileWriter writer = new FileWriter(fileName + ".csv")) {
            // Define the content of the csv file
            String content = "Student Name, Student ID, Program Name, Course Name, Semester, Grade\n";
            // Append the report data to the content
            // You may want to add the data from the generateStudentReport method here
            writer.write(content);
            System.out.println("Student report written to " + fileName + ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
     * Displays the generated student report directly on the console.
     */
    private static void outputToConsole() {
        generateStudentReport(); // Generates the report

        // Implementation to output report to the console
        // You may want to call the generateStudentReport method here
    }
}


