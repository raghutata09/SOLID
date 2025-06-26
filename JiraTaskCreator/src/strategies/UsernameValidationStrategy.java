package strategies;

import java.util.regex.Pattern;


public class UsernameValidationStrategy implements ValidationStrategy {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");

    @Override
    public boolean isValid(String input) {
        return input != null && USERNAME_PATTERN.matcher(input).matches();
    }

    @Override
    public String getErrorMessage() {
        return "Username must be 3-20 characters long and contain only letters, numbers, and underscores";
    }
} 