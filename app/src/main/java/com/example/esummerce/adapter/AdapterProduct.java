package com.example.esummerce.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esummerce.R;
import com.example.esummerce.dto.ProductDto;
import com.example.esummerce.network.storage.SharedPreferencesStorage;
import com.example.esummerce.network.storage.Storage;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolderProduct> {

    List<ProductDto> list;
    private Storage storage;

    public AdapterProduct(List<ProductDto> list,Storage storage){
        this.storage=storage;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null,false);
        return new AdapterProduct.ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct holder, int position) {
        holder.asignarProducto(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {

        TextView name,seller,price;
        Button buttonBuy;
        String id;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            buttonBuy = itemView.findViewById(R.id.buttonBuy);
            name = itemView.findViewById(R.id.name);
            seller = itemView.findViewById(R.id.seller);
            price = itemView.findViewById(R.id.price);
            buttonBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storage.saveCart(id);
                }
            });
        }

        public void asignarProducto(ProductDto product){
            id = product.getId();
            name.setText(product.getName());
            seller.setText(product.getProducedBy());
            price.setText(product.getPrice());
        }
    }

}
