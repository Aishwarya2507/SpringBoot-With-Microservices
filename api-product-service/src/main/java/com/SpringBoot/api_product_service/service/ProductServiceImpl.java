package com.SpringBoot.api_product_service.service;


import com.SpringBoot.api_product_service.model.Product;
import com.SpringBoot.api_product_service.payload.ApiResponse;
import com.SpringBoot.api_product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    // Create/Save New Product:
    @Override
    public ApiResponse<Product> createProduct(Product product) {
        Product saved = productRepository.save(product);
        return new ApiResponse<Product>(true, "Product created successfully", saved);
    }


    // Get Product By ID:
    @Override
    public ApiResponse<Product> getProdById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return new ApiResponse<>(true, "Product fetched successfully!", product);
    }


    // Get All Products:
    @Override
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ApiResponse<>(true, "All products fetched successfully!", products);
    }


    // Update Product By ID:
    @Override
    public ApiResponse<Product> updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());

        Product updated = productRepository.save(existing);
        return new ApiResponse<>(true, "Product updated successfully!", updated);
    }


    // Delete Product By ID:
    public ApiResponse deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        // Optional : Java 8 features : Avoid Null Pointer Exception

        if (product.isPresent()) {
            productRepository.deleteById(id);
            return new ApiResponse(true, "Product deleted successfully!", null);
        } else {
            return new ApiResponse(false, "Product not found with id: " + id, null);
        }
    }


    // Get Product By Price:
    @Override
    public ApiResponse<List<Product>> getProductByPrice(double minPrice) {
        List<Product> products = productRepository.findByPriceGreaterThanEqual(minPrice);
        return new ApiResponse<>(true, "Products filtered by price successfully", products);
    }


}
