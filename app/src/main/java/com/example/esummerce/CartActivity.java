package com.example.esummerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.esummerce.adapter.AdapterCart;
import com.example.esummerce.adapter.AdapterProduct;
import com.example.esummerce.dto.CartDto;
import com.example.esummerce.dto.ProductDto;
import com.example.esummerce.network.storage.SharedPreferencesStorage;
import com.example.esummerce.network.storage.Storage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<CartDto> list=new ArrayList<>();
    Button clean,pay;
    Storage storage;
    String url = "https://esumerce.herokuapp.com/v1/product/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SHARED_preferences", Context.MODE_PRIVATE);
        storage = new SharedPreferencesStorage(sharedPreferences);
        recycler = findViewById(R.id.recicler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        clean = findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storage.clearCart();
                Intent intent = new Intent(CartActivity.this,ProductsActivity.class);
                startActivity(intent);
            }
        });
        String cartS = storage.getCart();

        pay = findViewById(R.id.button2);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,BillingActivity.class);
                startActivity(intent);
            }
        });

        if(cartS!=null) {
            List<String> idsList = new ArrayList<String>(Arrays.asList(cartS.replace("[", "").replace("]", "")
                    .replace(" ", "").split(",")));
            for (String i : idsList) {
                StringRequest getRequest = new StringRequest(Request.Method.GET, url + i, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list.add(new CartDto(json));
                            AdapterCart adapterCart = new AdapterCart(list);
                            recycler.setAdapter(adapterCart);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("er", "er", error);
                    }
                });

                Volley.newRequestQueue(this).add(getRequest);
            }
        }
    }
}