package javanexuspots.models;

public class User {
    private final String userID;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final String role;

    public User(String userID, String name, String role, String username, String password, String email) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}