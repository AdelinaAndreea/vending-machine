package com.sda;

import java.util.ArrayList;
import java.util.List;

public class Tray {

    private List<Product> products;


    public Tray(){
        this.products=new ArrayList<>();
    }


    public void addProduct(Product product){
        products.add(product);
    }

    public boolean isEmply(){
        return products.isEmpty();

//        if(products.isEmpty()){   acelasi lucru
//            return true;
//        }else{
//            return false;
//        }
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Tray{" +
                "products=" + products +
                '}';
    }
}
