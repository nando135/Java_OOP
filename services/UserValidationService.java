package javanexuspots.services;
import java.util.List;
import javanexuspots.models.User;
public class UserValidationService {
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 20;
    
    public static void validateUserInput(
            String existingUserId,
            String userId,
            String name,
            String role,
            String username, 
            String password,
            String email,
            List<User> users) throws IllegalArgumentException {
        
        // perform validation
        if (userId.isEmpty() || name.isEmpty() || role.isEmpty() || 
            username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }
        
        // check for invalid characters
        if (userId.contains(",") || name.contains(",") || role.contains(",") || 
            username.contains(",") || password.contains(",") || email.contains(",")) {
            throw new IllegalArgumentException("Fields cannot contain commas (,).");
        }
        
        // check duplicate User ID
        for (User user : users) {
            if (!user.getUserID().equals(existingUserId) && user.getUserID().equals(userId)) {
                throw new IllegalArgumentException("User ID has already been used.");
            }
        }
        
        // check username length
        if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            throw new IllegalArgumentException("Username must be between "+MIN_USERNAME_LENGTH+" and "+MAX_USERNAME_LENGTH+" characters");
        }
        
        // check duplicate usernames
        for (User user : users) {
            if (!user.getUserID().equals(existingUserId) && user.getUsername().equalsIgnoreCase(username)) {
                throw new IllegalArgumentException("Username already exists.");
            }
        }
        
        // invalid email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email must contain @ symbol");
        }
        
        // check for duplicate email
        for (User user : users) {
            if (!user.getUserID().equals(existingUserId) && user.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException("Email already exists.");
            }
        }
    }
    
    private static boolean isValidEmail(String email) {
        return email.contains("@");
    }
}