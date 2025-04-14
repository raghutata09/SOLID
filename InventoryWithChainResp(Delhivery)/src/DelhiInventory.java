import java.util.HashMap;
import java.util.Map;

public class DelhiInventory extends Inventory {

    private Map<String, Integer> stock = new HashMap<>();

    public DelhiInventory(Inventory nextInventory) {
        super(nextInventory);
        stock.put("iPhone", 3);
        stock.put("Macbook", 0);
    }

    @Override
    public boolean isQtyPresent(String productName, int quantity) {
        int available = stock.getOrDefault(productName, 0);
        if (available >= quantity) {
            System.out.println("✅ Found in Delhi: " + available + " units");
            return true;
        } else {
            System.out.println("❌ Delhi doesn't have enough. Passing to next...");
            return nextInventory != null && nextInventory.isQtyPresent(productName, quantity);
        }
    }
}
