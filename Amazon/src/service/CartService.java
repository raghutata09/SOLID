package service;

import model.Cart;
import model.Product;
import model.ProductItem;
import model.ProductVariation;
import utils.Util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CartService {

    private static CartService cartService;
    private UserService userService;

    private Map<String, Cart> myCart;

    private CartService(){
        this.userService = UserService.getInstance();
        this.myCart = new ConcurrentHashMap<>();
    }

    public static synchronized CartService getInstance(){
        if(cartService==null){
            cartService = new CartService();
        }
        return cartService;
    }

    public void addToCart(String userId, ProductVariation productVariation){
        if(!userService.userExists(userId)){
            Util.printMsg("User does not exist, please register first.");
            return;
        }
        Cart cart = myCart.getOrDefault(userId,new Cart());
        ProductItem productItem = new ProductItem(productVariation.getId(), productVariation);
        cart.addToCart(productItem);
        myCart.put(userId, cart);
    }

//    public void increaseCartNo(String userId, ProductVariation productVariation){
//        List<ProductVariation> cartLst = myCart.getOrDefault(userId, new CopyOnWriteArrayList<>());
//        for(ProductVariation pv : cartLst){
//            if(pv.equals(productVariation)){
//                pv.setQty(pv.getQty()+1);
//                break;
//            }
//        }
//    }

//    public void decreaseCartNo(String userId, ProductVariation productVariation){
//        List<ProductVariation> cartLst = myCart.getOrDefault(userId, new CopyOnWriteArrayList<>());
//        ProductVariation productVariation1 =null;
//        for(ProductVariation pv : cartLst){
//            if(pv.equals(productVariation) && pv.getQty()>=1){
//                pv.setQty(pv.getQty()-1);
//                productVariation1 = pv;
//                break;
//            }
//        }
//        if(productVariation1!=null && productVariation1.getQty()==0){
//            cartLst.remove(productVariation1);
//        }
//    }

    public void watchCart(String userId){
        List<ProductItem> plst = myCart.get(userId).getProductList();
        if(plst!=null){
            for(ProductItem productItem : plst){
                Util.printMsg("Product ID: " + productItem.getId() +
                        ", Variation ID: " + productItem.getProductVariation().getId() +
                        ", Quantity: " + productItem.getQty());
            }
        }
    }

}
