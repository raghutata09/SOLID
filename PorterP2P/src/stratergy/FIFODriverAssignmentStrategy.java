package stratergy;

import enums.OrderStatus;
import manager.OrderManager;
import model.Driver;

import java.util.List;

public class FIFODriverAssignmentStrategy implements DriverAssignmentStrategy {
    @Override
    public Driver assignDriver(List<Driver> availableDrivers) {
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable() && !hasAssignedOrder(driver)) return driver;
        }
        return null;
    }

    private boolean hasAssignedOrder(Driver driver) {
        return OrderManager.getInstance(null).getAllOrders().values().stream()
                .anyMatch(order ->
                        order.getAssignedDriver() != null &&
                                order.getAssignedDriver().equals(driver) &&
                                order.getStatus() == OrderStatus.ASSIGNED);
    }
}
