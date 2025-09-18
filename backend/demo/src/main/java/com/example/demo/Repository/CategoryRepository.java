package com.example.demo.Repository;

import com.example.demo.Entity.Categories;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    public boolean existsByName(String name);

}
