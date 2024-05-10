package com.example.hibernateexample.service;

import com.example.hibernateexample.entity.Projects;
import com.example.hibernateexample.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectsRepository projectsRepository;

    @Autowired
    public ProjectService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Projects createProject(Projects project) {
        return projectsRepository.save(project);
    }
}
