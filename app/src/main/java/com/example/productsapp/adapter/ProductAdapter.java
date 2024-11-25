package com.example.productsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.productsapp.R;
import com.example.productsapp.model.Product;

import java.util.List;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//    private final List<Product> productList;
//    private final OnItemClickListener listener;
//    private final Context context;

    private final List<Product> productList;
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    // Updated constructor to accept Context
    public ProductAdapter(List<Product> productList, OnItemClickListener listener) {
//        this.context = context;
//        this.productList = productList;
//        this.listener = listener;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context) // Use the passed Context
//                .inflate(R.layout.item_product, parent, false);
//        return new ProductViewHolder(view);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product, listener);
        //        Product product = productList.get(position);
//        holder.productName.setText(product.getTitle());
//        holder.productPrice.setText(String.format("Price: ₹" + product.getPrice()));

        // Use Glide with the passed Context
//        Glide.with(context)
//                .load(product.getThumbnail()) // Load the image from URL
//                .into(holder.productImage);

//        holder.itemView.setOnClickListener(v -> listener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public void addProduct(List<Product> newUsers) {
        int start = productList.size();
        productList.addAll(newUsers);
        notifyItemRangeInserted(start, newUsers.size());
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
        }

        public void bind(Product product, OnItemClickListener listener) {
            productName.setText(product.getTitle());
            productPrice.setText("Price: " + product.getPrice());
            Glide.with(itemView.getContext()).load(product.getThumbnail()).into(productImage);
            itemView.setOnClickListener(v -> listener.onItemClick(product));
        }
    }

}
