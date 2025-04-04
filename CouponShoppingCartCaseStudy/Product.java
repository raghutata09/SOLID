package CouponShoppingCartCaseStudy;

public abstract class Product {
    String name;
    double originalPrice;
    ProductType productType;

    Product(){}

    Product(String name, double originalPrice, ProductType productType){
        this.name=name;
        this.originalPrice=originalPrice;
        this.productType=productType;
    }

    public abstract double getPrice();

    o=

    public ProductType getProductType() {
        return productType;
    }
}
