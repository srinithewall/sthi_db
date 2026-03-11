package com.sthi.service;

import com.sthi.model.Category;
import com.sthi.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo CategoryRepo;

    public List<Category> getAllCategories() {
        return CategoryRepo.findAllByOrderByNameAsc();
    }

    public Optional<Category> getCategoryById(Long id) {
        return CategoryRepo.findById(id);
    }

    public Category saveCategory(Category category) {
        return CategoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        CategoryRepo.deleteById(id);
    }
}
