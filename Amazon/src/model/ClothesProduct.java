package model;

public class ClothesProduct extends ProductVariation{

    String size;
    String color;

    public ClothesProduct(String id, String prodId, String size, int qty, String color) {
        super(id,prodId);
        this.size = size;
        this.qty = qty;
        this.color = color;
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
