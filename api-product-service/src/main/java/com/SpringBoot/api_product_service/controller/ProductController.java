package com.SpringBoot.api_product_service.controller;


import com.SpringBoot.api_product_service.model.Product;
import com.SpringBoot.api_product_service.payload.ApiResponse;
import com.SpringBoot.api_product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productService")
public class ProductController {

    @Autowired
    private ProductService productService;


    // Create/Save New Product:
    @PostMapping
    public ApiResponse<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    // Get Product by ID
    @GetMapping("/{id}")
    public ApiResponse<Product> getProductById(@PathVariable Long id) {
        return productService.getProdById(id);
    }


    // Get All Products
    @GetMapping
    public ApiResponse<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }


    // Update Product
    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }


    // Delete Product
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }


    // Get Products By Price Filter
    @PostMapping("/price")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByPricePost(
            @RequestBody Map<String, Object> request) {

        Object minObj = request.get("minPrice");
        if (minObj == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Parameter 'minPrice' is required", null));
        }

        double minPrice = Double.parseDouble(minObj.toString()); // safe conversion

        ApiResponse<List<Product>> response = productService.getProductByPrice(minPrice);

        if (response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No products found with price >= " + minPrice, null));
        }

        return ResponseEntity.ok(response);
    }


}
