package CouponShoppingCartCaseStudy;

import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends CouponDecorator{

    Product product ;
    int discountPercentage;
    ProductType productType;
    static List<ProductType> eligibleTypes = new ArrayList<>();
    static {
        eligibleTypes.add(ProductType.FURNITURE_GODOS);
        eligibleTypes.add(ProductType.ELECTRONIC_GOODS);
    }


    TypeCouponDecorator(Product product, int percent, ProductType productType){
        this.product=product;
        this.discountPercentage=percent;
        this.productType=productType;
    }

    @Override
    public double getPrice() {


        double price = product.getPrice();
        if(eligibleTypes.contains(productType)){
            return price- (price*discountPercentage)/100;
        }
        return price;
    }
}
