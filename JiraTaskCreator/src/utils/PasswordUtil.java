package utils;

/**
Utility for password.
 In actual, we will use a proper hashing algo like SHA-256 or MD5
 **/
public class PasswordUtil {

    // Use hashing algo here
    public static String hashPassword(String password) {
        return password;
    }
    
    //Verify password
    public static boolean verifyPassword(String password, String storedPassword) {
        return password.equals(storedPassword);
    }
    
    //validate password for first time users.
    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
        
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
} 