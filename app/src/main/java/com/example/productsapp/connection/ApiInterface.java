package com.example.productsapp.connection;

import com.example.productsapp.model.Product;
import com.example.productsapp.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("products")
    Call<ProductResponse> getProducts(@Query("limit") int limit, @Query("skip") int skip);

    @GET("products/{id}")
    Call<Product> getProductDetail(@Path("id") int id);
}
