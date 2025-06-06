import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    String id;
    String name;
    String location;
    Map<String, Product> productMap; // SKU -> Product
    Inventory(String id, String name, String location){
        this.id=id;
        this.name = name;
        this.location = location;
        this.productMap = new ConcurrentHashMap<>();
    }

    public synchronized void addProduct(Product product, int qty){
        productMap.put(product.getSku(),product);
    }
    public synchronized void addStock(Product product, int qty){
        if(!productMap.containsKey(product.getSku())){
            System.out.println("Product is not there");
            return;
        }
        Product p = productMap.get(product.getSku());
        p.addQty(qty);
        productMap.put(product.getSku(),p);
    }

    public synchronized void removeProduct(String sku, int qty){
        if(productMap.containsKey(sku)){
            Product product = productMap.get(sku);
            if(product.getQty()>=qty){
                product.setQty(product.getQty() -qty );
            }
            else {
                System.out.println("This operation is not feasible, as qty is less");
            }
        }
    }

    public synchronized int getAvailableQuantity(String sku){
        if(productMap.containsKey(sku)){
            return productMap.get(sku).getQty();
        }
        else {
            return -1;
        }
    }

    public Product getProductBySku(String sku) {
        return productMap.get(sku);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public String getName() {
        return name;
    }
}
