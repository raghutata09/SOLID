public class StateFactory {

    public static Inventory getInventoryHandler(String cityName, Inventory next) {
        if (cityName.equalsIgnoreCase("Delhi")) {
            return new DelhiInventory(next);
        } else if (cityName.equalsIgnoreCase("Chennai")) {
            return new ChennaiInventory(next);
        }
        return next; // fallback
    }
}
