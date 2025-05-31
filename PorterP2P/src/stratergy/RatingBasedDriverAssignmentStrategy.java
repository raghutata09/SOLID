package stratergy;

import model.Driver;
import model.Order;

import java.util.Comparator;
import java.util.List;

public class RatingBasedDriverAssignmentStrategy implements DriverAssignmentStrategy {
    @Override
    public Driver assignDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .filter(Driver::isAvailable)
                .max(Comparator.comparingDouble(Driver::getAverageRating))
                .orElse(null);
    }
}
