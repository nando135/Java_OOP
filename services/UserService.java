package javanexuspots.services;

import javanexuspots.models.User;
import javanexuspots.services.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractService<User> {
    private static final String FILE_PATH = "src/javanexuspots/databases/users.txt";

    public UserService() {
        super(FILE_PATH); // Pass the file path to AbstractService
    }

    @Override
    public List<User> loadData() {
        List<String> lines = readFile();
        System.out.println("Read " + lines.size() + " lines from file");
        List<User> users = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                User user = new User(
                    parts[0].trim(),
                    parts[1].trim(), 
                    parts[2].trim(),  
                    parts[3].trim(),  
                    parts[4].trim(), 
                    parts[5].trim()  
                );
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void saveData(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User user : users) {
            lines.add(String.join(",",
                    user.getUserID(),
                    user.getName(),
                    user.getRole(), 
                    user.getUsername(), 
                    user.getPassword(),
                    user.getEmail()));
        }
        writeFile(lines);
    }

    public void addUser(User user) {
        List<User> users = loadData();

        for (User existingUser : users) {
            if (existingUser.getUserID().equals(user.getUserID())) {
                throw new IllegalArgumentException("User ID already exists: " + user.getUserID());
            }
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists: " + user.getUsername());
            }
            if (existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new IllegalArgumentException("Email already exists: " + user.getEmail());
            }
        }

        users.add(user);
        saveData(users);
    }

    public void updateUser(User updatedUser) {
        List<User> users = loadData();
        boolean userFound = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(updatedUser.getUserID())) {
                users.set(i, updatedUser);
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            throw new IllegalArgumentException("User with ID " + updatedUser.getUserID() + " does not exist.");
        }

        saveData(users);
    }

    public void deleteUser(String userID) {
        List<User> users = loadData();
        boolean removed = users.removeIf(user -> user.getUserID().equals(userID));

        if (!removed) {
            throw new IllegalArgumentException("User with ID " + userID + " does not exist.");
        }

        saveData(users);
    }

    public List<User> getAllUsers() {
        return loadData();
    }

    public User getUserById(String userID) {
        List<User> users = loadData();
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
    
    public String generateNextUserId() {
        List<User> users = loadData();
        int maxId = 0;

        for (User user : users) {
            try {
                String idPart = user.getUserID().replaceAll("[^\\d]", ""); // Extract numeric part
                int numericId = Integer.parseInt(idPart);
                if (numericId > maxId) {
                    maxId = numericId;
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid user ID: " + user.getUserID());
            }
        }

        return "U" + String.format("%03d", maxId + 1); // Generate next ID in "UXXX" format
    }
    
    public List<Object[]> getAllUsersForTable() {
        List<User> users = loadData(); // Load users from file
        List<Object[]> userRows = new ArrayList<>();

        for (User user : users) {
            String roleName = mapRoles(user.getRole());

            userRows.add(new Object[]{
                user.getUserID(),
                user.getName(),
                roleName,
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
            });
        }

        return userRows;
    }

    private String mapRoles(String role) {
        switch (role) {
            case "ADM":
                return "Administrator";
            case "SM":
                return "Sales Manager";
            case "FM":
                return "Finance Manager";
            case "IM":
                return "Inventory Manager";
            case "PM":
                return "Purchase Manager";
            default:
                return "Unknown Role";
        }
    }
}
