package com.example.hibernateexample.web.rest;

import com.example.hibernateexample.entity.Projects;
import com.example.hibernateexample.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ProjectsController {
    private final ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("project/post")
    public ResponseEntity<Projects> postProjects(@RequestBody Projects projects) {
        var result = projectService.createProject(projects);
        return ResponseEntity.ok(result);
    }
}
