package Practice;


public class Movies {
    String id;
    String movieName;
    String description;
    int duration;

    public Movies(String id, String movieName, String description, int duration) {
        this.id = id;
        this.movieName = movieName;
        this.description = description;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return movieName + " (" + duration + " mins)";
    }
}