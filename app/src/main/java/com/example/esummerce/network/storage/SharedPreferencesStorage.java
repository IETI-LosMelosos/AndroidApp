package com.example.esummerce.network.storage;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreferencesStorage implements Storage{

    private final String TOKEN_KEY = "Token_key";
    private final String CAR_KEY = "cart_key";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesStorage(SharedPreferences sharedPreferences){
        this.sharedPreferences=sharedPreferences;
    }

    @Override
    public void saveToken(String token) {
        sharedPreferences.edit().putString(TOKEN_KEY,token).apply();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY,"");
    }

    @Override
    public void saveCart(String product) {
        String products = getCart();
        List<String> productsList;
        if(products==null){
            productsList = new ArrayList<>();
        }else{
            productsList = new ArrayList<>(Arrays.asList(products.replace("[","").replace("]","").replace(" ","").split(",")));
        }
        productsList.add(product);
        sharedPreferences.edit().putString(CAR_KEY,productsList.toString()).apply();
    }

    @Override
    public String getCart() {
        return sharedPreferences.getString(CAR_KEY,null);
    }

    @Override
    public void clearCart() {
        sharedPreferences.edit().remove(CAR_KEY).apply();
    }


}
