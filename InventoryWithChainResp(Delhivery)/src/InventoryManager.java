public class InventoryManager {

    private static InventoryManager inventoryManager;
    private InventoryManager(){

    }

    public static InventoryManager getInstance(){
        if(inventoryManager==null){
            return new InventoryManager();
        }
        return  inventoryManager;
    }
}
