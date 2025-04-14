import java.util.HashMap;
import java.util.Map;

public class CentralInventory extends Inventory {

    private Map<String, Integer> stock = new HashMap<>();

    public CentralInventory() {
        super(null);
        stock.put("iPhone", 100);
        stock.put("Macbook", 20);
    }

    @Override
    public boolean isQtyPresent(String productName, int quantity) {
        int available = stock.getOrDefault(productName, 0);
        if (available >= quantity) {
            System.out.println("✅ Found in Central: " + available + " units");
            return true;
        } else {
            System.out.println("❌ Central doesn't have enough. End of chain.");
            return false;
        }
    }
}
