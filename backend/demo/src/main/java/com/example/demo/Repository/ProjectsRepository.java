//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Repository;

import com.example.demo.Entity.Projects;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT p FROM Projects p WHERE p.creator.userId = :userId")
    List<Projects> findByCreatorUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Projects p WHERE p.categories.categoryId = :id")
    void deleteByCategoryId(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Projects p SET p.categories = (SELECT c FROM Categories c WHERE c.categoryId = 1) WHERE p.categories.categoryId = :categoryId")
    void updateProjectsCategoryToNewCategory(Long categoryId);

    @Modifying
    @Transactional
    @Query("SELECT p  from Projects p Where p.endDate<:now AND p.status=:status")
    List<Projects> faidprojects(LocalDateTime now, Projects.statusprojects status);
}
