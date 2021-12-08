package com.example.esummerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esummerce.R;
import com.example.esummerce.dto.CartDto;
import com.example.esummerce.dto.ProductDto;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolderCart> {

    List<CartDto> list;

    public AdapterCart(List<CartDto> list){
        this.list=list;
    }

    @NonNull
    @Override
    public AdapterCart.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,null,false);
        return new ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolderCart holder, int position) {
        holder.asignarDatos(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder{

        TextView name,seller,quantity,price;

        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            seller = itemView.findViewById(R.id.seller);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
        }

        public void asignarDatos(CartDto product) {
            name.setText(product.getName());
            seller.setText(product.getSeller());
            price.setText(product.getPrice());
        }
    }
}
