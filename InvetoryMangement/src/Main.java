public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of InventoryManager
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // Create and add Inventorys
        Inventory Inventory1 = new Inventory("1","Inventory 1","BLR");
        Inventory Inventory2 = new Inventory("2","Inventory 2","CHE");
        inventoryManager.addInventory(Inventory1);
        inventoryManager.addInventory(Inventory2);

        // Create products using ProductFactory
        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct(
                ProductType.ELECTRONICS, "SKU123", "Laptop", 1000, 50, 25);
        Product tShirt = productFactory.createProduct(
                ProductType.CLOTHES, "SKU456", "T-Shirt", 20, 200, 100);

        // Add products to Inventorys
        Inventory1.addProduct(laptop, 15);
        Inventory1.addProduct(tShirt, 20);


        inventoryManager.checkAndReplenish("SKU123");
        System.out.println("Available quantity of Laptop in Inventory1: " + Inventory1.getAvailableQuantity("SKU123"));
        Inventory1.addStock(laptop, -26);
        System.out.println("Available quantity of Laptop in Inventory1: " + Inventory1.getAvailableQuantity("SKU123"));
        inventoryManager.checkAndReplenish("SKU123");

    }
}