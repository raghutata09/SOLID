package model;

import enums.ProductType;
import exceptions.ProductNotFoundException;
import utils.Util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Product {

    private String id;
    private String name;
    private ProductType productType;
    private Map<String, List<ProductVariation>> prodVarMap;


    public Product(String name, ProductType productType) {
        this.id = Util.generateId("product");
        this.name = name;
        this.productType = productType;
        this.prodVarMap = new ConcurrentHashMap<>();
    }

    public List<ProductVariation> getProductList(String prodId) {
        try {
            if (prodVarMap.containsKey(prodId)) {
                return prodVarMap.get(prodId);
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + prodId);
            }
        } catch (ProductNotFoundException e) {
            System.err.println("Custom Exception Caught: " + e.getMessage());
            // Tu chahe toh rethrow kar, warna empty list return kar
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Unexpected error while fetching product variations for prodId: " + prodId);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addnewProductVar(ProductVariation productVariation){
        String prodId = productVariation.productId;
        prodVarMap.putIfAbsent(prodId, Collections.synchronizedList(new CopyOnWriteArrayList<>()));
        List<ProductVariation> productVariations = prodVarMap.get(prodId);
        productVariations.add(productVariation);
        prodVarMap.put(prodId,productVariations);
        Util.printMsg("Successfully added the product");
    }

    public ProductVariation getProduct(String varId, String prodId) {
        try {
            List<ProductVariation> productVariations = getProductList(prodId);
            for (ProductVariation variation : productVariations) {
                if (variation.id.equals(varId)) {
                    Util.printMsg("Product Variation found: " + variation);
                    return variation;
                }
            }
            throw new ProductNotFoundException("Product Variation not found with ID: " + varId);
        } catch (ProductNotFoundException e) {
            System.err.println("Custom Exception Caught: " + e.getMessage());
            return null; // or handle as needed
        } catch (Exception e) {
            System.err.println("Unexpected error while fetching product variation with ID: " + varId);
            e.printStackTrace();
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Map<String, List<ProductVariation>> getProdVarMap() {
        return prodVarMap;
    }
}
