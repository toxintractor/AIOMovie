package com.bentaher.aiomovie;

import java.io.Serializable;

/**
 * Created by Youssef on 24/01/2018.
 */

public class Bol implements Serializable {

    //De data voor onze datastructuur.
    public String title;
    public String type;
    public String price;
    public String shopLink;
    public String imageLink;

    public Bol(String title, String type, String price, String shopLink, String imageLink){

        this.title = title;
        this.type = type;
        this.price = price;
        this.shopLink = shopLink;
        this.imageLink = imageLink;
    }

    public String getTitle(){
        return title;
    }
    public String getType(){
        return type;
    }
    public String getPrice(){
        return price;
    }
    public String getShopLink(){
        return shopLink;
    }
    public String getImageLink(){
        return imageLink;
    }

}
