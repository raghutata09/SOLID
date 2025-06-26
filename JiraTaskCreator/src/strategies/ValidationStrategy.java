package strategies;

public interface ValidationStrategy {
    boolean isValid(String input);
    String getErrorMessage();
} 