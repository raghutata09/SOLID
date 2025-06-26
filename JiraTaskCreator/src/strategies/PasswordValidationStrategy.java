package strategies;

import utils.PasswordUtil;

public class PasswordValidationStrategy implements ValidationStrategy {
    @Override
    public boolean isValid(String input) {
        return PasswordUtil.isPasswordStrong(input);
    }

    @Override
    public String getErrorMessage() {
        return "Password must be at least 8 characters long and contain uppercase, lowercase, digit, and special character";
    }
} 