package com.example.demo.Repository;

import com.example.demo.Entity.TransactionHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Modifying
    @Transactional
    @Query("SELECT t FROM TransactionHistory t WHERE t.user.userId = :userId")
    List<TransactionHistory> findByUserId(@Param("userId") Long userId);
}