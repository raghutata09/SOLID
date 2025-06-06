public class ElectronicProduct extends Product{

    private String brand;
    private int warrantyPeriod;

    ElectronicProduct(String sku, String name, int price, int quantity, int threshold){
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQty(quantity);
        setProductType(ProductType.ELECTRONICS);
        setThreshold(threshold);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
