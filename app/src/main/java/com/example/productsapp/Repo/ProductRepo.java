package com.example.productsapp.Repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.productsapp.connection.ApiClient;
import com.example.productsapp.connection.ApiInterface;
import com.example.productsapp.model.Product;
import com.example.productsapp.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepo {
    private final ApiInterface apiInterface;

    public ProductRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<List<Product>> getProducts(int limit, int skip) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();

        apiInterface.getProducts(limit, skip).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsLiveData.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productsLiveData.setValue(null);
            }
        });
        return productsLiveData;
    }
}
