package stratergy;

import model.Driver;
import model.Order;

import java.util.List;

public interface DriverAssignmentStrategy {
    Driver assignDriver(List<Driver> availableDrivers);
}