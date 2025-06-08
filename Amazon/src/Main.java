import enums.ProductType;
import model.ClothesProduct;
import model.Product;
import model.ProductVariation;
import model.User;
import service.CartService;
import service.ProductService;
import service.UserService;
import utils.Util;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        CartService cartService = CartService.getInstance();
        ProductService productService = ProductService.getInstance();

        Product p1 = new Product("Souled Store Shirt", ProductType.CLOTHES);
        Product p2 = new Product("Souled Store Pant", ProductType.CLOTHES);

        ProductVariation pv1 = new ClothesProduct("1", p1.getId(), "M", 10, "Red");
        ProductVariation pv2 = new ClothesProduct("2", p1.getId(), "L", 10, "Red");
        ProductVariation pv3 = new ClothesProduct("3", p1.getId(), "L", 10, "Black");

        ProductVariation pv4 = new ClothesProduct("4", p2.getId(), "M", 5, "Red");
        ProductVariation pv5 = new ClothesProduct("5", p2.getId(), "S", 4, "Red");
        ProductVariation pv6 = new ClothesProduct("6", p2.getId(), "XL", 2, "Black");


        User u1 = userService.registerUser("Raghu","1234","abc@gmail.com");

        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addSubsidaryPRoducts(pv1);
        productService.addSubsidaryPRoducts(pv2);
        productService.addSubsidaryPRoducts(pv3);
        productService.addSubsidaryPRoducts(pv4);
        productService.addSubsidaryPRoducts(pv5);
        productService.addSubsidaryPRoducts(pv6);

        productService.getProductByCategory(ProductType.CLOTHES);

        // Adding products to cart
        Util.printMsg("Adding products to cart...");
        cartService.addToCart(u1.getId(), pv1);
        cartService.watchCart(u1.getId());


    }
}