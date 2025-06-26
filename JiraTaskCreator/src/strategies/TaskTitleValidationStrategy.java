package strategies;


public class TaskTitleValidationStrategy implements ValidationStrategy {
    @Override
    public boolean isValid(String input) {
        return input != null && input.trim().length() >= 3 && input.trim().length() <= 100;
    }

    @Override
    public String getErrorMessage() {
        return "Task title must be 3-100 characters long";
    }
} 