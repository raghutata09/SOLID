package Practice;

public class Main {
    public static void main(String[] args) {
        VideoCatalog catalog = new VideoCatalog();

        // Users A and B
        MovieObserver userA = new MovieObserver("User A");
        MovieObserver userB = new MovieObserver("User B");

        // Subscriptions
        catalog.addObserver("Dhoom", userA);       // A subscribes to Dhoom
        catalog.addObserver("Paatal Lok", userB);  // B subscribes to Paatal Lok

        // Add Movies and test notifications
        catalog.addMovie(new Movies("1", "Dhoom", "Action", 120));
        catalog.addMovie(new Movies("2", "Paatal Lok", "Thriller", 45));
        catalog.addMovie(new Movies("3", "Krrish", "Superhero", 150));
    }
}
