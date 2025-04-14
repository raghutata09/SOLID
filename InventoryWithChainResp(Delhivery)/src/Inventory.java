public abstract class Inventory {
    Inventory nextInventory;

    Inventory(Inventory inventory){
        this.nextInventory=inventory;
    }

    public abstract boolean isQtyPresent(String productName, int quantity);

}