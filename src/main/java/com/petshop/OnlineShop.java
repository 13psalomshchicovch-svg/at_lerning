package com.petshop;

public class OnlineShop {

    static void main(String[] args) {

        ProductListManager managerProduct = new ProductListManager();
        OrderQueueManager managerOrder = new OrderQueueManager();
        ClientSetManager managerClient = new ClientSetManager();

        PriceMapManager managerPrice = new PriceMapManager();


        //Product
//        System.out.println(managerProduct.addProduct("Milk"));
//        System.out.println(managerProduct.addProduct("Coffe"));
//        System.out.println(managerProduct.addProduct("Milk"));
//        System.out.println(managerProduct.addProduct("Tea"));
//        System.out.println(managerProduct.addProduct("Cookie"));
//        System.out.println(managerProduct.addProduct("Beef"));

//        System.out.println(managerProduct.findProduct("Tea"));
//        System.out.println(managerProduct.findProduct("Dog"));
//        System.out.println(managerProduct.removeProduct("Dog"));
//        System.out.println(managerProduct.findProduct("Tea"));
        //Order
//        System.out.println(managerOrder.addOrder(100));
//        System.out.println(managerOrder.addOrder(200));
//        System.out.println(managerOrder.addOrder(300));
//        System.out.println(managerOrder.addOrder(400));
//        System.out.println(managerOrder.addOrder(100));
//        System.out.println(managerOrder.addOrder(500));
//
//        System.out.println(managerOrder.findProduct(300));
//        System.out.println(managerOrder.removeOrder(300));
//        System.out.println(managerOrder.findProduct(300));

        //Email
//        System.out.println(managerClient.addEmail("1@y.ru"));
//        System.out.println(managerClient.addEmail("2@s.ry"));
//        System.out.println(managerClient.addEmail("2@s.ry"));
//        System.out.println(managerClient.addEmail("4@a.by"));
//        System.out.println(managerClient.addEmail("74@z.ni"));
//        System.out.println(managerClient.addEmail("896@h.oi"));
//
//        System.out.println(managerClient.findEmail("122@s.ry"));
//        System.out.println(managerClient.removeEmail("2@s.ry"));
//        System.out.println(managerClient.findEmail("2@s.ry"));
        //Price

        System.out.println(managerPrice.addPrice("Milk",1.2));
        System.out.println(managerPrice.addPrice("Coffee",2.2));
        System.out.println(managerPrice.addPrice("Milk",3.2));
        System.out.println(managerPrice.addPrice("Bred",4.2));
        System.out.println(managerPrice.addPrice("Tea",5.2));

        System.out.println(managerPrice.findPrice("Coffee"));
        System.out.println(managerPrice.removeProduct("Milk"));
        System.out.println(managerPrice.findPrice("Milk"));



    }
}
