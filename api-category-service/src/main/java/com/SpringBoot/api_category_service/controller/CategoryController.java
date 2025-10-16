package com.SpringBoot.api_category_service.controller;


import com.SpringBoot.api_category_service.model.Category;
import com.SpringBoot.api_category_service.payload.ApiResponse;
import com.SpringBoot.api_category_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categoryService")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // Create/Save New Category:
    @PostMapping
    public ApiResponse<Category> saveCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }


    // Get Category By ID:
    @GetMapping("/{id}")
    public ApiResponse<Category> getById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }


    // Get All Category:
    @GetMapping
    public List<ApiResponse<List<Category>>> getAllList(){
        return Collections.singletonList(categoryService.getAllCategory());
    }


    // Update Category By ID:
    @PutMapping("/{id}")
    public ApiResponse<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        return  categoryService.updateCategory(id, category);
    }


    // Delete Category By ID:
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCategoryById(@PathVariable Long id) {
        ApiResponse<?> response = categoryService.deleteCategory(id);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Get Product By Name:
    @PostMapping("/searchName")
    public ResponseEntity<ApiResponse<List<Category>>> searchCategoryByName(
            @RequestBody Map<String, String> request) {

        String findName = request.get("findName"); // extract from JSON body
        if (findName == null || findName.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Parameter 'findName' is required", null));
        }

        ApiResponse<List<Category>> response = categoryService.getCategoryByName(findName);

        if (response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "No categories found with name: " + findName, null));
        }

        return ResponseEntity.ok(response);
    }


}
