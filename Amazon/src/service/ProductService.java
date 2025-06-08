package service;

import enums.ProductType;
import model.Product;
import model.ProductVariation;
import utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductService {

    private static ProductService productService;

    private Map<String, Product> productMap;
    private ProductService(){
        this.productMap = new ConcurrentHashMap<>();
    }

    public static synchronized ProductService getInstance(){
        if(productService==null){
            productService = new ProductService();
        }
        return productService;
    }

    public void addProduct(Product product){
        productMap.put(product.getId(),product);
    }

    public void addSubsidaryPRoducts(ProductVariation productVariation){
        Product product = productMap.getOrDefault(productVariation.getProductId(),null);
        if(product!=null){
            product.addnewProductVar(productVariation);
        }
        else {
            Util.printMsg("Wrong Prodcut Id entered");
        }
    }

    public Product getProductById(String productId){
        return productMap.getOrDefault(productId,null);
    }

    public void getProductByCategory(ProductType productType){
        List<Product> res = new ArrayList<>();
       for(String pid: productMap.keySet()){
           Product product = productMap.get(pid);
           if(product.getProductType().equals(productType)){
               res.add(product);
           }
       }
       if(res.isEmpty()){
           Util.printMsg("No products found for the category: " + productType);
           return;
       }
       for(Product product : res){
           Util.printMsg("Product ID: " + product.getId() + ", Name: " + product.getName());
           List<ProductVariation> variations = product.getProductList(product.getId());
           if(variations.isEmpty()){
               Util.printMsg("No variations found for product: " + product.getName());
           } else {
               for(ProductVariation variation : variations){
                   Util.printMsg("Variation ID: " + variation.getId()  + ", Quantity: " + variation.getQty());
               }
           }
       }
    }
}
