package com.example.esummerce.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class CartDto {
    String name;
    String seller;
    String price;

    public CartDto(String name,String seller,String price){
        this.name=name;
        this.price=price;
        this.seller=seller;
    }

    public CartDto(JSONObject json) throws JSONException {
        this.name=json.getString("name");
        this.price=json.getString("price");
        this.seller=json.getString("producedBy");
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSeller() {
        return seller;
    }
}
