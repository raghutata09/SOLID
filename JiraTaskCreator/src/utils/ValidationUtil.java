package utils;

import java.time.LocalDateTime;
import strategies.*;

/**
 * Utility class for input validation.
 * Provides convenience methods that use specific validation strategies.
 */
public class ValidationUtil {
    
    // Generic validation methods
    public static boolean validateEmail(String email) {
        return new EmailValidationStrategy().isValid(email);
    }
    
    public static boolean validateUsername(String username) {
        return new UsernameValidationStrategy().isValid(username);
    }
    
    public static boolean validatePassword(String password) {
        return new PasswordValidationStrategy().isValid(password);
    }
    
    public static boolean validateTaskTitle(String title) {
        return new TaskTitleValidationStrategy().isValid(title);
    }
    
    public static boolean validateDeadline(LocalDateTime deadline) {
        return deadline != null && deadline.isAfter(LocalDateTime.now());
    }
    
    // Null and empty checks
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }
} 