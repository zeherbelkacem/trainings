package com.fms.trainings.service;

import com.fms.trainings.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public Category getCategoryByName(String name);

    public Page<Category> getCategoriesByPages(Pageable pageable);

    public void deleteCategory(Long id);
}
