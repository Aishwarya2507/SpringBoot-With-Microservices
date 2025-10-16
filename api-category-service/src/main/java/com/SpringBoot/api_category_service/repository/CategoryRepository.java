package com.SpringBoot.api_category_service.repository;


import com.SpringBoot.api_category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CategoryRepository extends JpaRepository<Category, Long> {
}
