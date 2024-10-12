package com.ijse.posproject.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.Category;

@Service
public interface CategoryService {

    Category getCategoryById(Long categoryId);
    Category createCategory(Category category);
    List<Category> getAllCategory();
    void deleteCategory(Long categoryId);
    Category updateCategory(Long categoryId, Category category);
}
