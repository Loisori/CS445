//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Categories;
import java.util.List;

public interface CategoryServiceimpl {
    void SaveCategory(Categories category);

    boolean ExistCategory(String category);

    List<Categories> getAllCategories();

    Categories getCategory(Long categoryId);

    void deleteCategory(Long id);
     Categories findByID(Long id);
}
