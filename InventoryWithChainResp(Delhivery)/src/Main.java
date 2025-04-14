public class Main {
    public static void main(String[] args) {

        // Build the chain: Local -> Central
        Inventory centralInventory = new CentralInventory();
        Inventory localInventory = StateFactory.getInventoryHandler("Delhi", centralInventory);

        // Sample product to check
        String product = "iPhone";
        int quantity = 2;

        System.out.println("🔍 Checking availability for: " + product + ", qty: " + quantity);
        boolean available = localInventory.isQtyPresent(product, quantity);

        if (available) {
            System.out.println("✅ Product available. Proceed to checkout.");
        } else {
            System.out.println("❌ Product not available in any inventory.");
        }
    }
}
