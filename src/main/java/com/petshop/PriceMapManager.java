package com.petshop;

import java.util.HashMap;
import java.util.Iterator;


public class PriceMapManager  {

    HashMap<String, Double> productPrices = new HashMap<>();
    
    public String addPrice(String product, double price) throws NullPointerException{
        productPrices.put(product, price);
        return product +" "+ productPrices.get(product).toString();

    }

    public boolean removeProduct(String product){
        return productPrices.remove(product).isNaN() ;
    }

    public Double findPrice(String product){

        Iterator<String> keys = productPrices.keySet().iterator();

        if (productPrices.isEmpty()){return null;}

        do {
            String key = keys.next();
            if(key.equals(product)) {return productPrices.get(key);}
        } while (keys.hasNext());

        return null;
    }
}
