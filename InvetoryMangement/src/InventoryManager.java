import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryManager {

    private static InventoryManager inventoryManagerInstance;

    private List<Inventory> inventoryList;

    private ProductFactory productFactory;

    private NotificationSystem notificationSystem;

    private InventoryManager(){
        inventoryList = new CopyOnWriteArrayList<>();
        productFactory = new ProductFactory();
        notificationSystem = new EmailNotificationSystem(); // or any other implementation
    }

    public static InventoryManager getInstance(){
        if(inventoryManagerInstance==null){
            inventoryManagerInstance = new InventoryManager();
        }
        return inventoryManagerInstance;
    }

    public void addInventory(Inventory inventory){
        inventoryList.add(inventory);
    }

    public void checkAndReplenish(String productSku) {
        for (Inventory inventory : inventoryList) {
            Product product = inventory.getProductBySku(productSku);
            if (product != null && product.getQty() < product.getThreshold()) {
                int replenishQty = product.getThreshold() - product.getQty();
                System.out.println("Replenishing " + replenishQty + " units of " + product.getName() + " in Inventory: " + inventory.getName());
                inventory.addStock(product, replenishQty);
                notificationSystem.notify(inventory.getName(),"Replenished " + replenishQty + " units of " + product.getName() + " in Inventory: " + inventory.getName());
            } else {
                System.out.println("No replenishment needed for " + productSku + " in Inventory: " + inventory.getName());
            }
        }
    }
}
