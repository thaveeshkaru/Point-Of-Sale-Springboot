package com.ijse.posproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posproject.entity.Category;
import com.ijse.posproject.service.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")   
    public ResponseEntity<?> createCategory(@RequestBody Category category){

        try {
            Category createCategory = categoryService.createCategory(category);
            return ResponseEntity.status(201).body(createCategory);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory() {

        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.status(201).body(categories); 

    }

    @DeleteMapping("categories/{categoryID}")
    public void deleteItem(@PathVariable Long categoryID){
        categoryService.deleteCategory(categoryID);
    }

    @PutMapping("/categories/{categoryID}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryID, @RequestBody Category category) {
        try {
            Category updatedCategory = new Category();
            updatedCategory.setCategoryName(category.getCategoryName());
            Category updateCategory = categoryService.updateCategory(categoryID,updatedCategory);
            return ResponseEntity.status(201).body(updateCategory);
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);

        }
        
        
    }
    

}
