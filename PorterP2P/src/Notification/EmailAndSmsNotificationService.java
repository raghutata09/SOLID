package Notification;

public class EmailAndSmsNotificationService implements NotificationService {

    @Override
    public void notifyUser(String name, String message) {
        System.out.println("Notification to " + name + ": " + message);
    }
}
