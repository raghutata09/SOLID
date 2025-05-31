package manager;

import Notification.NotificationService;
import stratergy.DriverAssignmentStrategy;
import enums.ItemType;
import enums.OrderStatus;
import model.Customer;
import model.Driver;
import model.Order;
import stratergy.FIFODriverAssignmentStrategy;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class OrderManager {
    private static volatile OrderManager instance;
    private final Map<String, Customer> allCustomers = new ConcurrentHashMap<>();
    private final Map<String, List<Order>> customerOrders = new ConcurrentHashMap<>();
    private final Map<String, Order> allOrders = new ConcurrentHashMap<>();
    private final Map<String, Driver> allDrivers = new ConcurrentHashMap<>();
    private final BlockingQueue<Order> pendingOrders = new LinkedBlockingQueue<>(3); // bounded queue

    private final NotificationService notificationService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private DriverAssignmentStrategy driverAssignmentStrategy;

    private OrderManager(NotificationService service) {
        this.notificationService = service;
        this.driverAssignmentStrategy = new FIFODriverAssignmentStrategy(); // Default strategy
        startOrderExpiryChecker();
    }

    public static OrderManager getInstance(NotificationService service) {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager(service);
                }
            }
        }
        return instance;
    }

    public void registerCustomer(Customer customer) {
        allCustomers.put(customer.getId(), customer);
        customerOrders.put(customer.getId(), new CopyOnWriteArrayList<>());
        System.out.println(customer.getName() + " registered successfully.");
    }

    public void registerDriver(Driver driver) {
        allDrivers.put(driver.getId(), driver);
        System.out.println(driver.getName() + " registered successfully.");
    }

    public Driver getDriverByName(String name) {
        return allDrivers.values().stream()
                .filter(driver -> driver.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public Order placeOrder(Customer customer, String src, String dest, ItemType type, double weight) {

        // We can check if wig
        if (!allCustomers.containsKey(customer.getId())) {
            System.out.println("Customer not registered: " + customer.getName());
            return null;
        }

        Order order = new Order(customer, src, dest, type, weight);
        allOrders.put(order.getOrderId(), order);
        customerOrders.computeIfAbsent(customer.getId(), k -> new CopyOnWriteArrayList<>()).add(order);

        System.out.println("Order placed: " + order.getOrderId());
        tryAssignDriver(order);
        return order;
    }

    public void cancelOrder(String orderId) {
        Order order = allOrders.get(orderId);
        if (order == null || order.getStatus() == OrderStatus.PICKED_UP || order.getStatus() == OrderStatus.DELIVERED) {
            System.out.println("Order cannot be cancelled: " + orderId);
            return;
        }

        order.setStatus(OrderStatus.CANCELLED);
        if (order.getAssignedDriver() != null) {
            order.getAssignedDriver().setAvailable(true);
        }
        notificationService.notifyUser(order.getCustomer().getId(), "Your order was cancelled.");
        System.out.println("Order cancelled: " + orderId);
    }

    public void pickUpOrder(String driverId, String orderId) {
        Driver driver = allDrivers.get(driverId);
        Order order = allOrders.get(orderId);
        if (driver != null && order != null &&
                order.getAssignedDriver() == driver &&
                order.getStatus() == OrderStatus.ASSIGNED) {
            order.setStatus(OrderStatus.PICKED_UP);
            driver.setAvailable(false); // Now mark unavailable here
            notificationService.notifyUser(order.getCustomer().getId(), "Your order has been picked up!");
            System.out.println("Order picked up: " + orderId);
        } else {
            System.out.println("Order cannot be picked up: " + orderId);
        }
    }

    public void deliverOrder(String driverId, String orderId) {
        Driver driver = allDrivers.get(driverId);
        Order order = allOrders.get(orderId);
        if (driver != null && order != null && order.getAssignedDriver() == driver && order.getStatus() == OrderStatus.PICKED_UP) {
            order.setStatus(OrderStatus.DELIVERED);
            driver.setAvailable(true);
            driver.addCompletedOrder(order);
            notificationService.notifyUser(order.getCustomer().getId(), "Your order has been delivered!");
            System.out.println("Order delivered: " + orderId);
        } else {
            System.out.println("Order cannot be delivered: " + orderId);
        }
    }

    // This can be used to rate driver in future so that we can improve the driver assignment strategy
    public void rateDriver(String orderId, double rating) {
        Order order = allOrders.get(orderId);
        if (order == null || order.getStatus() != OrderStatus.DELIVERED) {
            System.out.println("Cannot rate. Order is not delivered or doesn't exist: " + orderId);
            return;
        }

        Driver driver = order.getAssignedDriver();
        if (driver != null) {
            driver.addRating(rating);
            System.out.println("Rating added. Driver " + driver.getName() + " new avg rating: " + driver.getAverageRating());
        } else {
            System.out.println("No driver assigned for order: " + orderId);
        }
    }

    private void tryAssignDriver(Order order) {
        if (order.getStatus() == OrderStatus.CANCELLED) return;

        Driver availableDriver = driverAssignmentStrategy.assignDriver(new ArrayList<>(allDrivers.values()));
        if (availableDriver != null) {
            order.assignDriver(availableDriver);
            order.setStatus(OrderStatus.ASSIGNED);
            notificationService.notifyUser(order.getCustomer().getId(), "Driver assigned: " + availableDriver.getName());
            notificationService.notifyUser(availableDriver.getId(), "You have a new order: " + order.getOrderId());
        } else {
            if (pendingOrders.size() < 3) {
                pendingOrders.add(order);
                System.out.println("Order added to queue: " + order.getOrderId());
            } else {
                System.out.println("Queue is full, order could not be queued: " + order.getOrderId());
            }
        }
    }

    public void assignDriversToPendingOrders() {
        Iterator<Order> iterator = pendingOrders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getStatus() == OrderStatus.CREATED) {
                tryAssignDriver(order);
                if (order.getStatus() == OrderStatus.ASSIGNED) {
                    iterator.remove();
                }
            } else if (order.getStatus() == OrderStatus.CANCELLED) {
                iterator.remove(); // Clean cancelled orders from queue
            }
        }
    }

    private void startOrderExpiryChecker() {

        // Schedule a task to check for expired orders every minute
        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            for (Order order : allOrders.values()) {
                if (order.getStatus() == OrderStatus.CREATED &&
                        order.getCreatedAt().plusMinutes(30).isBefore(now)) {
                    order.setStatus(OrderStatus.EXPIRED);
                    System.out.println("Order expired: " + order.getOrderId());
                    notificationService.notifyUser(order.getCustomer().getId(), "Order expired due to timeout.");
                }
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    public Map<String, Driver> getAllDrivers() {
        return allDrivers;
    }

    public Map<String, Customer> getAllCustomers() {
        return allCustomers;
    }

    public Map<String, Order> getAllOrders() {
        return allOrders;
    }

    public Map<String, List<Order>> getCustomerOrders() {
        return customerOrders;
    }

    //In future, we can add more strategies
    public void  setDriverAssignmentStrategy(DriverAssignmentStrategy strategy) {
        this.driverAssignmentStrategy = strategy;
    }
}
