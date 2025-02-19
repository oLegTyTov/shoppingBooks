package com.example.shoppingbooks.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.CategoryExistException;
import com.example.exceptions.CategoryNotExistException;
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

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryExistException();
        }
        categoryRepository.save(category);
    }
    @Transactional
    public void deleteCategory(String nameCategory)
    {
        if (!categoryRepository.existsByName(nameCategory)) {
            throw new CategoryNotExistException();
        }
        categoryRepository.deleteByName(nameCategory);
    }
}
