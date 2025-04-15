package javanexuspots.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javanexuspots.models.User;

public class UserLogService {
    private static final String FILE_PATH = "src/javanexuspots/databases/user_logs.txt";

    public enum UserAction {
        USER_CREATED("Registered User"),
        USER_DELETED("Deleted User"),
        USER_EDITED("Edited User");

        private final String description;

        UserAction(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public void writeUserActionLog(String user, String admin, UserAction action, String details) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String logEntry = String.format("%s,%s,%s,%s%n", 
            user,
            admin,
            action + (details.isEmpty() ? "" : ": " + details),
            timestamp
        );

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(logEntry);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to log file: " + e.getMessage());
        }
    }

    public String getEditDetails(User originalUser, User updatedUser) { // logs the changed fields by admin
        List<String> changedFields = new ArrayList<>();
        
        if (!originalUser.getName().equals(updatedUser.getName())) {
            changedFields.add("name");
        }
        if (!originalUser.getUsername().equals(updatedUser.getUsername())) {
            changedFields.add("username");
        }
        if (!originalUser.getEmail().equals(updatedUser.getEmail())) {
            changedFields.add("email");
        }
        if (!originalUser.getPassword().equals(updatedUser.getPassword())) {
            changedFields.add("password");
        }
        if (!originalUser.getRole().equals(updatedUser.getRole())) {
            changedFields.add("role from " + originalUser.getRole() + " to " + updatedUser.getRole());
        }

        return String.join(" | ", changedFields);
    }
}