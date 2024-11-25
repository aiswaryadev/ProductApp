package com.example.productsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productsapp.R;
import com.example.productsapp.adapter.ProductAdapter;
import com.example.productsapp.connection.ApiClient;
import com.example.productsapp.connection.ApiInterface;
import com.example.productsapp.model.Product;
import com.example.productsapp.model.ProductResponse;
import com.example.productsapp.viewmodel.ProductViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel viewModel;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList, product -> {
            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            intent.putExtra("product", (Serializable) product);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        fetchProducts();
    }
    private void fetchProducts() {
        viewModel.getProducts(10, 0).observe(this, users -> {
            if (users != null) {
                productList.clear();
                productList.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }

  //change0
}