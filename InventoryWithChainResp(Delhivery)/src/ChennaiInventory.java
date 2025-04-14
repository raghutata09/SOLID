import java.util.HashMap;
import java.util.Map;

public class ChennaiInventory extends Inventory {

    private Map<String, Integer> stock = new HashMap<>();

    public ChennaiInventory(Inventory nextInventory) {
        super(nextInventory);
        stock.put("iPhone", 1);
        stock.put("Macbook", 1);
    }

    @Override
    public boolean isQtyPresent(String productName, int quantity) {
        int available = stock.getOrDefault(productName, 0);
        if (available >= quantity) {
            System.out.println("✅ Found in Chennai: " + available + " units");
            return true;
        } else {
            System.out.println("❌ Chennai doesn't have enough. Passing to next...");
            return nextInventory != null && nextInventory.isQtyPresent(productName, quantity);
        }
    }
}
