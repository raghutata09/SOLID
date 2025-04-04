package CouponShoppingCartCaseStudy;

public class Main {
    public static void main(String[] args) {
        Product item1 = new Item1("FAN",1000,ProductType.ELECTRONIC_GOODS);
        Product item2 = new Item2("SOFA",2000,ProductType.FURNITURE_GODOS);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(item1);
        shoppingCart.addToCart(item2);

        System.out.println("Total price is = "+shoppingCart.getTotalPrice());
    }
}
