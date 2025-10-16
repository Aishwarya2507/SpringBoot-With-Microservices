package com.SpringBoot.api_category_service.service;



import com.SpringBoot.api_category_service.model.Category;
import com.SpringBoot.api_category_service.payload.ApiResponse;
import com.SpringBoot.api_category_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    // Create/Save New Category:
    @Override
    public ApiResponse<Category> createCategory(Category category){
        Category saved = categoryRepository.save(category);
        return new ApiResponse<>(true, "Category created successfully", saved);
    }


    // Get Category By ID:
    @Override
    public ApiResponse<Category> getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        return new ApiResponse<>(true, "Category fetched Successfully!", category);
    }


    // Get All Category:
    @Override
    public ApiResponse<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        return new ApiResponse<>(true ,"All Category fetched Successfully!", categoryList);
    }


    // Update Category By ID:
    @Override
    public ApiResponse<Category> updateCategory(Long id, Category category){
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        existing.setName(category.getName());
        Category updated = categoryRepository.save(existing);
        return new ApiResponse<>(true, "Category Updated Successfully!", updated);
    }


    // Delete Category By ID:
    public ApiResponse deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            return new ApiResponse(true, "Category deleted successfully!", null);
        } else {
            return new ApiResponse(false, "Category not found with id: " + id, null);
        }
    }



    // Get Category By Name:
    @Override
    public ApiResponse<List<Category>> getCategoryByName(String findName) {
        // Fetch all categories and filter by name (case-insensitive, partial match)
        List<Category> categories = categoryRepository.findAll()
                .stream()
                .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(findName.toLowerCase()))
                .collect(Collectors.toList());

        return new ApiResponse<>(true, "Category filtered by name successfully", categories);
    }


}
