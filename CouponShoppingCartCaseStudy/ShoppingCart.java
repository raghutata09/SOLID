package CouponShoppingCartCaseStudy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> productList;
    ShoppingCart(){
        productList = new ArrayList<>();
    }

    public void addToCart(Product product){
        Product productWithEligibleDiscount =
                new TypeCouponDecorator(
                        new PercentageCouponDecorator(product,10),3, product.getProductType()
                );
        productList.add(productWithEligibleDiscount);

    }

    public int getTotalPrice(){
        int totalPrice=0;
        for(Product product : productList){
            totalPrice+=product.getPrice();
        }
        return totalPrice;
    }

}
