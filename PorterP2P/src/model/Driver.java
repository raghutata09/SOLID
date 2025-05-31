package model;

import utils.IdGeneratorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Driver extends User {
    private boolean isAvailable;
    private final List<Order> completedOrders;
    private final List<Double> ratings;



    /**
     * Constructor for Driver
     * @param name
     * @param phone
     */

    public Driver(String name, String phone) {
        super(IdGeneratorUtil.generateDriverId(),name, phone);
        this.isAvailable = true;
        this.completedOrders = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addCompletedOrder(Order order) {
        this.completedOrders.add(order);
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void addRating(double rating) {
        this.ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        return ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public List<Double> getRatings() {
        return ratings;
    }
}
