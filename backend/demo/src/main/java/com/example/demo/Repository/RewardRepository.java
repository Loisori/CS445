package com.example.demo.Repository;

import com.example.demo.Entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    public Reward findBytitle(String name);


    public Boolean existsBytitle(String title);
}
