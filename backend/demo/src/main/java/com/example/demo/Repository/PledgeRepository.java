package com.example.demo.Repository;

import com.example.demo.Entity.pledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PledgeRepository extends JpaRepository<pledge, Long> {


}
