package com.hyscaler.Online_Learning_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyscaler.Online_Learning_Platform.entity.Category;
import com.hyscaler.Online_Learning_Platform.exception.ResourceNotFound;
import com.hyscaler.Online_Learning_Platform.repository.CategoryRepo;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFound());
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category != null) {
            category.setName(updatedCategory.getName());
            category.setCourses(updatedCategory.getCourses());
            return categoryRepo.save(category);
        }
        return category;
    }
}
