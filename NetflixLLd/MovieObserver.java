package Practice;

public class MovieObserver implements EntertainmentObserver {

    private String userName;

    public MovieObserver(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(Movies movie) {
        System.out.println(userName + " got notified: " + movie.movieName + " is now available!");
    }
}
