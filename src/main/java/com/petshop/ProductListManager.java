package com.petshop;

import java.util.ArrayList;


public class ProductListManager {


    private ArrayList<String> products = new ArrayList<String>();

    protected boolean addProduct(String productName){

        for (int i = 0; i < products.size(); i++) {
            if (productName.equals(products.get(i))){
                return false;
            }
        }
        products.addLast(productName);
        return true;
    }

    protected boolean removeProduct(String productName){

        for (int i = 0; i < products.size(); i++) {
            if (productName.equals(products.get(i))){
                products.remove(productName);
                return true;
            }

        }
        return false;
    }

    protected int findProduct(String productName){

        for (int i = 0; i < products.size(); i++) {
            if (productName.equals(products.get(i))){
                return i;
            }
        }
        return -1;
    }



}
