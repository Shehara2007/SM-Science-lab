package lk.ijse.sciencelab.model;

public class ForgotPasswordmodel {

    // Simulated method to check if user exists and send reset link
    public boolean sendResetLink(String usernameOrEmail) {
        if (usernameOrEmail == null || usernameOrEmail.isEmpty()) {
            return false;
        }

        // Example user check - replace with real DB lookup
        if (usernameOrEmail.equalsIgnoreCase("user1") || usernameOrEmail.equalsIgnoreCase("user@example.com")) {
            System.out.println("Reset link sent to " + usernameOrEmail);
            return true;
        }

        return false; // User not found
    }
}
