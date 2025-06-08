package model;

public abstract class ProductVariation {

    String id;
    String productId;
    int qty;

    public ProductVariation(String id, String productId) {
        this.id = id;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
