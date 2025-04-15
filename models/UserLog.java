package javanexuspots.models;

import java.time.LocalDateTime;

public class UserLog {
    private final String username;
    private final String action;
    private final String adminUsername;
    private final LocalDateTime timestamp;

    public UserLog(String username, String action, String adminUsername, LocalDateTime timestamp) {
        this.username = username;
        this.action = action;
        this.adminUsername = adminUsername;
        this.timestamp = timestamp;
    }

    public String getUsername() { return username; }
    public String getAction() { return action; }
    public String getAdminUsername() { return adminUsername; }
    public LocalDateTime getTimestamp() { return timestamp; }
}