public class ClothingProduct extends Product{
    private String size;
    private String color;

    ClothingProduct(String sku, String name, int price, int quantity, int threshold){
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQty(quantity);
        setProductType(ProductType.ELECTRONICS);
        setThreshold(threshold);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
