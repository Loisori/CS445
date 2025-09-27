//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Projects;
import com.example.demo.Repository.ProjectsRepository;
import com.example.demo.Sercive.impl.ProjectsServiceimpl;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class ProjectsService implements ProjectsServiceimpl {


    private final ProjectsRepository projectsRepository;
    private final PledgeService pledgeService;

    @Override
    public List<Projects> getProjectFail(LocalDateTime now, Projects.statusprojects status) {
        return projectsRepository.faidprojects(now, status);
    }

    public void checkcontractterm() {
        LocalDateTime newtime = LocalDateTime.now();
        List<Projects> projects = getProjectFail(newtime, Projects.statusprojects.PENDING);
        for (Projects project : projects) {
            project.setStatus(Projects.statusprojects.FAILED);
            projectsRepository.save(project);
            pledgeService.refundamout(project.getProjectId());
        }
    }

    @Override
    public List<Projects> getMyProjects(Long id) {
        return projectsRepository.findByCreatorUserId(id);
    }


    public void updateIDcategory(Long id) {
        this.projectsRepository.updateProjectsCategoryToNewCategory(id);
    }

    public void deleteIDcategory(Long id) {
        this.projectsRepository.deleteByCategoryId(id);
    }

    public List<Projects> GetAllProjects() {
        return this.projectsRepository.findAll();
    }

    public Projects GetidProject(Long id) {
        return (Projects) this.projectsRepository.findById(id).orElse(null);
    }

    public void SaveProject(Projects project) {
        this.projectsRepository.save(project);
    }

    public void DeleteProject(Long id) {
        this.projectsRepository.deleteById(id);
    }

    public List<Projects> SearchProject(String title) {
        return this.projectsRepository.findByTitleContainingIgnoreCase(title);
    }

}
