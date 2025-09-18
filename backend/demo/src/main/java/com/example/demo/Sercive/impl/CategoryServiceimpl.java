package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Categories;

import java.util.List;


public interface CategoryServiceimpl {
    public void SaveCategory(Categories category);

    public boolean ExistCategory(String category);

    public List<Categories> getAllCategories();

    public Categories getCategory(Long categoryId);

    public void deleteCategory(Long id);


}
