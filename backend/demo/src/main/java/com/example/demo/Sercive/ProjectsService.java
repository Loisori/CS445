package com.example.demo.Sercive;

import com.example.demo.Entity.Projects;
import com.example.demo.Repository.ProjectsRepository;
import com.example.demo.Sercive.impl.ProjectsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService implements ProjectsServiceimpl {
    @Autowired
    private ProjectsRepository projectsRepository;

    @Override
    public List<Projects> GetAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public Projects GetidProject(Long id) {
        return projectsRepository.findById(id).orElse(null);
    }

    @Override
    public void SaveProject(Projects project) {
        projectsRepository.save(project);

    }

    @Override
    public void DeleteProject(Long id) {
        projectsRepository.deleteById(id);

    }

    @Override
    public List<Projects> SearchProject(String title) {
        return projectsRepository.findByTitleContainingIgnoreCase(title);
    }
}
