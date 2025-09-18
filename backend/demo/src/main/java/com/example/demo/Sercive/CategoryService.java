package com.example.demo.Sercive;

import com.example.demo.Entity.Categories;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Sercive.impl.CategoryServiceimpl;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceimpl {
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Categories getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean ExistCategory(String category) {
        return categoryRepository.existsByName(category);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void SaveCategory(Categories category) {
        categoryRepository.save(category);
    }
}
