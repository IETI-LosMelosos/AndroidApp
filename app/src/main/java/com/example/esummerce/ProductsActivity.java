package com.example.esummerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.esummerce.adapter.AdapterCart;
import com.example.esummerce.adapter.AdapterProduct;
import com.example.esummerce.dto.ProductDto;
import com.example.esummerce.network.storage.SharedPreferencesStorage;
import com.example.esummerce.network.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView recycler;
    String url = "https://esumerce.herokuapp.com/v1/product";
    private Storage storage;
    Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        cart = findViewById(R.id.button3);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("SHARED_preferences", Context.MODE_PRIVATE);
        storage = new SharedPreferencesStorage(sharedPreferences);

        recycler = findViewById(R.id.recicler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray json = new JSONArray(response);
                    List<ProductDto> list = new ArrayList<>();
                    for(int i=0;i<json.length();i++){
                        list.add(new ProductDto(json.getJSONObject(i)));
                    }
                    AdapterProduct adapterCart = new AdapterProduct(list,storage);
                    recycler.setAdapter(adapterCart);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("er","er",error);
            }
        });

        Volley.newRequestQueue(this).add(getRequest);
    }
}