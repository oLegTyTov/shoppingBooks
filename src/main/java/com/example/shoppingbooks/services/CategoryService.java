package com.example.shoppingbooks.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shoppingbooks.entities.Category;
import com.example.shoppingbooks.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    public List<Category> getAllCategories()
    {
    return categoryRepository.findAll();
    }
}
