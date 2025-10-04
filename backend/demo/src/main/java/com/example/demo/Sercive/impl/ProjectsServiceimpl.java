//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Projects;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectsServiceimpl {
    List<Projects> GetAllProjects();

    Projects GetidProject(Long id);

    void SaveProject(Projects project);

    void DeleteProject(Long id);

    List<Projects> SearchProject(String title);

    void deleteIDcategory(Long id);

    void updateIDcategory(Long id);

    List<Projects> getMyProjects(Long id);

    List<Projects> getProjectFail(LocalDateTime now,Projects.statusprojects status);
}
