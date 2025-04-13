package Practice;

import java.util.*;

public class VideoCatalog {

    private List<Movies> moviesList = new ArrayList<>();
    private Map<String, List<EntertainmentObserver>> observerMap = new HashMap<>();

    public void addObserver(String contentName, EntertainmentObserver observer) {
        observerMap.computeIfAbsent(contentName, k -> new ArrayList<>()).add(observer);
    }

    public void removeObserver(String contentName, EntertainmentObserver observer) {
        if (observerMap.containsKey(contentName)) {
            observerMap.get(contentName).remove(observer);
        }
    }

    public void addMovie(Movies movie) {
        moviesList.add(movie);
        System.out.println("New movie/webseries added: " + movie.movieName);
        notifyObservers(movie);
    }

    private void notifyObservers(Movies movie) {
        List<EntertainmentObserver> interestedObservers = observerMap.get(movie.movieName);
        if (interestedObservers != null) {
            for (EntertainmentObserver observer : interestedObservers) {
                observer.update(movie);
            }
        } else {
            System.out.println("No subscribers for " + movie.movieName);
        }
    }
}
