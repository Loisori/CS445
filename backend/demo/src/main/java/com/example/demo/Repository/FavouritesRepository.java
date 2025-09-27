//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Repository;

import com.example.demo.Entity.Favourites;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Favourites c WHERE c.project.id = :projectId")
    void deleteByProjectId(Long projectId);
    List<Favourites> findByUser_UserId(Long userId);
}
