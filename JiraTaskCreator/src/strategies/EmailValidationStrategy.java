package strategies;

import java.util.regex.Pattern;

public class EmailValidationStrategy implements ValidationStrategy {
    // Only accepts phonepe.com
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@phonepe\\.com$"
    );

    @Override
    public boolean isValid(String input) {
        return input != null && EMAIL_PATTERN.matcher(input).matches();
    }

    @Override
    public String getErrorMessage() {
        return "Email must end with @phonepe.com";
    }
} 