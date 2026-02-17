package com.petshop;

import java.util.LinkedList;

public class OrderQueueManager {

    LinkedList<Integer> orderQueue = new LinkedList<>();

    public boolean addOrder(int order){
        int i = 0;
        while (i < orderQueue.size()){
            if (orderQueue.get(i) == order){
                return false;
            }
            i++;
        }
       orderQueue.add(order);
        return true;
    }

    public boolean removeOrder(Integer order){
        int i = 0;
        while (i < orderQueue.size()){
            if (orderQueue.get(i).equals(order) ){
                orderQueue.remove(order);
                return true;
            }
            i++;
        }
        return false;
    }

    protected int findProduct(int order) {

        int i = 0;
        while (i < orderQueue.size()) {
            if (orderQueue.get(i) == order) {
                return i;
            }
            i++;
        }
        return -1;
    }

}
