package com.SpringBoot.api_product_service.service;


import com.SpringBoot.api_product_service.model.Product;
import com.SpringBoot.api_product_service.payload.ApiResponse;

import java.util.List;

public interface ProductService {

    // Create/Save New Product:
    ApiResponse<Product> createProduct(Product product);

    // Get Product By ID:
    ApiResponse<Product> getProdById(Long id);

    // Get All Products:
    ApiResponse<List<Product>> getAllProducts();

    // Update Product By ID:
    ApiResponse<Product> updateProduct(Long id, Product product);

    // Delete Product By ID:
    ApiResponse<String> deleteProduct(Long id);

    // Get Product By Price:
    ApiResponse<List<Product>> getProductByPrice(double minPrice);


}
