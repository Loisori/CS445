package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Projects;

import java.util.List;
import java.util.Optional;

public interface ProjectsServiceimpl {
    public List<Projects> GetAllProjects();

    public Projects GetidProject(Long id);

    public void SaveProject(Projects project);

    public void DeleteProject(Long id);

    public List<Projects> SearchProject(String title);
}
