//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Repository;

import com.example.demo.Entity.pledge;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PledgeRepository extends JpaRepository<pledge, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM pledge c WHERE c.project.id = :projectId")
    void deleteByProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query("SELECT p FROM pledge p where p.project.projectId=:projects_id")
    List<pledge> findByProjects_Id(Long projects_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM pledge  c where c.reward.id=:id")
    void deleteByRewardId(Long id);
}
