public class ProductFactory {

    public Product createProduct(ProductType productType, String sku, String name, int price, int qty, int threshold){
        if(productType.equals(ProductType.CLOTHES)){
            return new ClothingProduct(sku,name,price,qty,threshold);
        }else {
            return new ElectronicProduct(sku,name,price,qty,threshold);
        }
    }
}
