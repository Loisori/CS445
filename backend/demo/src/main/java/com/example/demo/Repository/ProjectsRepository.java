package com.example.demo.Repository;

import com.example.demo.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectsRepository  extends JpaRepository<Projects, Long> {
    public List<Projects> findByTitleContainingIgnoreCase(String title);

}
