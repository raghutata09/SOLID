public class EmailNotificationSystem implements NotificationSystem{
    @Override
    public void notify(String name, String message) {
        System.out.println("Email Notification: sent to - "+name);
        System.out.println(message);
    }
}
