
public abstract class Product {
    private String id;
    private String sku;
    private String name;
    private int price;
    private int threshold;
    private ProductType productType;
    private int qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty= qty;
    }

    public void addQty(int qty) {
        this.qty+= qty;
    }



}
