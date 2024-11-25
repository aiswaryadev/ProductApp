package com.example.productsapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.productsapp.R;
import com.example.productsapp.connection.ApiClient;
import com.example.productsapp.connection.ApiInterface;
import com.example.productsapp.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView productDetails;
    private ImageView productThumbnail;

    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productDetails = findViewById(R.id.productDetails);
        productThumbnail = findViewById(R.id.productThumbnail);

        Product product = (Product) getIntent().getSerializableExtra("product");
        String details = "Name: " + product.getTitle() +
                "\nCategory: " + product.getCategory() +
                "\nBrand: " + product.getBrand() +
                "\nPrice: $" + product.getPrice() +
                "\nDiscount: " + product.getDiscountPercentage() + "%" +
                "\nRating: " + product.getRating() +
                "\nStock: " + product.getStock() +
                "\nWeight: " + product.getWeight() +
                "\nDimensions: " + product.getDimensions() +
                "\nWarranty: " + product.getWarrantyInformation() +
                "\nShipping: " + product.getShippingInformation() +
                "\nAvailability: " + product.getAvailabilityStatus() +
                "\nReturn Policy: " + product.getReturnPolicy() +
                "\nMinimum Order: " + product.getMinimumOrderQuantity();

        productDetails.setText(details);
        productThumbnail.setImageURI(Uri.parse(product.getThumbnail()));
//        loadProductDetails();
//            productId = getIntent().getIntExtra("product", -1);
//            if (productId != -1) {
//                loadProductDetails();
//            }
    }

    private void loadProductDetails() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getProductDetail(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    String details = "Name: " + product.getTitle() +
                            "\nCategory: " + product.getCategory() +
                            "\nBrand: " + product.getBrand() +
                            "\nPrice: $" + product.getPrice() +
                            "\nDiscount: " + product.getDiscountPercentage() + "%" +
                            "\nRating: " + product.getRating() +
                            "\nStock: " + product.getStock() +
                            "\nWeight: " + product.getWeight() +
                            "\nDimensions: " + product.getDimensions() +
                            "\nWarranty: " + product.getWarrantyInformation() +
                            "\nShipping: " + product.getShippingInformation() +
                            "\nAvailability: " + product.getAvailabilityStatus() +
                            "\nReturn Policy: " + product.getReturnPolicy() +
                            "\nMinimum Order: " + product.getMinimumOrderQuantity();

                    productDetails.setText(details);

                    // Load the thumbnail image
                    Glide.with(ProductDetailActivity.this)
                            .load(product.getThumbnail())
                            .into(productThumbnail);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                productDetails.setText("Failed to load product details");
            }
        });
    }
}
