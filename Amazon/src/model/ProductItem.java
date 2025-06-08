package model;

public class ProductItem {
    String id;
    ProductVariation productVariation;
    int qty;

    public ProductItem(String id, ProductVariation productVariation) {
        this.id = id;
        this.productVariation = productVariation;
        this.qty =1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
