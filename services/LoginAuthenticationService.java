package javanexuspots.services;
import java.util.*;
import javanexuspots.models.User;
import javanexuspots.services.UserService;

public class LoginAuthenticationService {
    private final UserService userService;

    public LoginAuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public String validateUser(String username, String password) {
        List<User> users = userService.getAllUsers(); // get users
        System.out.println("Found " + users.size() + " users in database");

        for (User user : users) {
            System.out.println("Checking user - Username: " + user.getUsername() 
                              + ", Password: " + user.getPassword());

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        return null;
    }    
}