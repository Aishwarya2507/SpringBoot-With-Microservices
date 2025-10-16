package com.SpringBoot.api_category_service.service;


import com.SpringBoot.api_category_service.model.Category;
import com.SpringBoot.api_category_service.payload.ApiResponse;

import java.util.List;

public interface  CategoryService {

    // Create/Save New Category:
    ApiResponse<Category> createCategory(Category category);

    // Get Category By ID:
    ApiResponse<Category> getCategoryById(Long id);

    // Get All Category:
    ApiResponse<List<Category>> getAllCategory();

    // Update Category By ID:
    ApiResponse<Category> updateCategory(Long id, Category category);

    // Delete Category By ID:
    ApiResponse<String> deleteCategory(Long id);

    // Get Product By Name:
    ApiResponse<List<Category>> getCategoryByName(String findName);

    
}



