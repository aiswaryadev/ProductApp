package com.example.productsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.productsapp.Repo.ProductRepo;
import com.example.productsapp.model.Product;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private final ProductRepo repository;

    public ProductViewModel() {
        repository = new ProductRepo();
    }

    public LiveData<List<Product>> getProducts(int limit, int skip) {
        return repository.getProducts(limit, skip);
    }
}
