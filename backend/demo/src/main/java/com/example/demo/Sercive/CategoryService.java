//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Categories;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Sercive.impl.CategoryServiceimpl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceimpl {
    private final CategoryRepository categoryRepository;

    @Override
    public Categories findByID(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }


    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public Categories getCategory(Long categoryId) {
        return (Categories) this.categoryRepository.findById(categoryId).orElse(null);
    }

    public List<Categories> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public boolean ExistCategory(String category) {
        return this.categoryRepository.existsByName(category);
    }

    public void SaveCategory(Categories category) {
        this.categoryRepository.save(category);
    }
}
