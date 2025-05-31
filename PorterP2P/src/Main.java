import enums.ItemType;
import manager.OrderManager;
import model.Customer;
import model.Driver;
import model.Order;
import Notification.EmailAndSmsNotificationService;
import Notification.NotificationService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // STEP 1: Setup
        NotificationService notificationService = new EmailAndSmsNotificationService();
        OrderManager orderManager = OrderManager.getInstance(notificationService);

        // STEP 2: Show Supported item types
        System.out.println("Supported item types:");
        for (ItemType type : ItemType.values()) {
            System.out.println(" - " + type);
        }

        // STEP 3: Register customers
        Customer c1 = new Customer("RaghuC", "7011293135");
        Customer c2 = new Customer("VidhiC", "82112942135");
        orderManager.registerCustomer(c1);
        orderManager.registerCustomer(c2);

        // STEP 4: Register drivers
        Driver d1 = new Driver("AmanD", "6011293131");
        Driver d2 = new Driver("EshanD", "72112463136");
        orderManager.registerDriver(d1);
        orderManager.registerDriver(d2);

        // STEP 5: Place 2 orders (drivers available)
        System.out.println("Placing Order 1 (should assign driver immediately)");
        Order o1 = orderManager.placeOrder(c1, "Whitefield", "BannergatthaRoad", ItemType.ELECTRONICS, 2.5);

        System.out.println("Placing Order 2 (should assign 2nd driver)");
        Order o2 = orderManager.placeOrder(c2, "Electronic City", "MG Road", ItemType.FILES, 1.0);

        TimeUnit.SECONDS.sleep(1);
        System.out.println("----------");

        // STEP 6: Place more orders than available drivers
        System.out.println("Placing Order 3 (should go to queue)");
        Order o3 = orderManager.placeOrder(c1, "Jp Nagar", "Jayanagar", ItemType.CLOTHING, 0.5);

        System.out.println("Placing Order 4 (should go to queue)");
        Order o4 = orderManager.placeOrder(c2, "VijayaBankLayout", "HSR Layout", ItemType.FILES, 3.0);

        // STEP 7: Cancel Order 4 before pickup
        System.out.println("Cancelling Order 4 (before pickup)");
        orderManager.cancelOrder(o4.getOrderId());

        // STEP 8: Try cancelling after delivery
        System.out.println("Driver picks up Order 1");
        orderManager.pickUpOrder(d1.getId(), o1.getOrderId());

        System.out.println("Delivering Order 1");
        orderManager.deliverOrder(d2.getId(), o1.getOrderId());
        orderManager.deliverOrder(d1.getId(), o1.getOrderId());
        System.out.println("Order 1 delivered successfully - "+o1.getStatus());
        System.out.println();
        System.out.println();

        System.out.println("Trying to cancel Order 1 after delivery (should fail)");
        orderManager.cancelOrder(o1.getOrderId());

        // STEP 9: Assign pending orders again (Order 3 should get assigned now)
        System.out.println("Assigning pending orders (Order 3 should now be assigned)");
        orderManager.assignDriversToPendingOrders();

        // STEP 10: Try picking up a cancelled order
        System.out.println("Trying to pick up cancelled Order 4 (should fail)");
        orderManager.pickUpOrder(d1.getId(), o4.getOrderId());

        // STEP 11: Check driver status
        Driver driver = orderManager.getDriverByName("EshanD");
        System.out.println("Driver " + driver.getName() + " status: " + (driver.isAvailable() ? "Available" : "Busy"));

        // STEP 12: Show final order status summary
        System.out.println("Final Order Status:");
        for (Order order : List.of(o1, o2, o3, o4)) {
            System.out.println(" - " + order.getOrderId() + " (" + order.getCustomer().getName() + "): " + order.getStatus());
        }
    }

    // 💡 Note: Location matching based on GPS can be added in future for nearest-driver logic
}
