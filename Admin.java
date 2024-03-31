/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmsapp.CMSApp;
/**
 *
 * @author jakubzaruski
 */


import com.cmsapp.users.User;
import com.cmsapp.users.UserRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Admin extends User {
    private final List<User> userList;
    
    
    /**
 * Creates an Admin user with specified username, password, and role.
 * that this admin can manage.
 *
 * @param username   username
 * @param password  password
 * @param role role within the application, typically UserRole.ADMIN
 */
public Admin(String username, String password, UserRole role) {
    super(username, password, role);
    this.userList = new ArrayList<>();
}

/**
 * Modifies the credentials of an existing user identified by username.
 * If the user is found, updates their password and role; otherwise, prints a message.
 *
 * @param username the username of the user whose details are to be modified
 * @param newPassword the new password for the user
 * @param newRole the new role for the user
 */
public void modifyUser(String username, String newPassword, UserRole newRole) {
    Optional<User> foundUser = userList.stream()
                                       .filter(user -> user.getUsername().equals(username))
                                       .findFirst();
    
    if (foundUser.isPresent()) {
        User user = foundUser.get();
        user.setPassword(newPassword);
        user.setRole(newRole);
        System.out.println("User modified successfully.");
    } else {
        System.out.println("User not found.");
    }
}

/**
 * Adds a new user to the CMS application if the user does not already exist.
 * 
 * @param user the User object to be added
 */
public void addUser(User user) {
    boolean exists = userList.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
    if (!exists) {
        userList.add(user);
        System.out.println("User added successfully.");
    } else {
        System.out.println("User already exists.");
    }
}

/**
 * Deletes a user from the CMS application based on the username.
 * If the user is found and deleted, prints a success message; otherwise, prints a message.
 *
 * @param username the username of the user to be deleted
 */
public void deleteUser(String username) {
    boolean removed = userList.removeIf(u -> u.getUsername().equals(username));
    if (removed) {
        System.out.println("User deleted successfully.");
    } else {
        System.out.println("User not found.");
    }
}

/**
 * Changes the admin own username and password.
 * 
 * @param newUsername the new username to be set for the admin
 * @param newPassword the new password to be set for the admin
 */
public void changeOwnCredentials(String newUsername, String newPassword) {
    setUsername(newUsername);
    setPassword(newPassword);
    System.out.println("Credentials updated successfully.");
}
}