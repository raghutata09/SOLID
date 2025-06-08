package model;

import service.ProductService;
import utils.Util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cart {
    private List<ProductItem> productList;
    public Cart(){
        this.productList = new CopyOnWriteArrayList<>();
    }

    public void addToCart(ProductItem productItem){
        productList.add(productItem);
    }

    public List<ProductItem> getProductList() {
        return productList;
    }
}
