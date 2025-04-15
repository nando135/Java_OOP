package javanexuspots.loginPage;

public class LoginSession {
    private static String currentUserSession; 

    // get username of session
    public static String getCurrentUserSession() {
        return currentUserSession;
    }

    // set current user
    public static void setCurrentUserSession(String username) {
        currentUserSession = username;
    }

    // clears the session
    public static void clearSession() {
        currentUserSession = null;
    }
}
