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

public class Admin extends User {
    private static final List<User> userList = new ArrayList<>();

    public Admin(String username, String password) {
        super(username, password, UserRole.ADMIN);
    }

    // Method to add a new user
    public void addUser(User user) {
        userList.add(user); // Add the user to the list
    }

    // Method to modify an existing user
    public void modifyUser(String username, String newPassword, UserRole newRole) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                user.setRole(newRole);
                break;
            }
        }
    }

    // Method to delete an existing user
    public void deleteUser(String username) {
        userList.removeIf(user -> user.getUsername().equals(username));
    }

    // Method to change own username and password
    public void changeOwnCredentials(String newUsername, String newPassword) {
        setUsername(newUsername);
        setPassword(newPassword);
    }

    // Setter method for username
    void setUsername(String newUsername) {
        this.username = newUsername;
    }

    // Setter method for password
    @Override
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
