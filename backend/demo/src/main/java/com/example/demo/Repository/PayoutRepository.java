//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Repository;

import com.example.demo.Entity.Payout;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Payout c WHERE c.project.id = :projectId")
    void deleteByProjectId(Long projectId);

    Boolean existsByProject_ProjectId(Long projectId);
}
