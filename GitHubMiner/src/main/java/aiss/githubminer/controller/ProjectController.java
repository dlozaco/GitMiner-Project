package aiss.githubminer.controller;

import aiss.githubminer.model.Project;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    public Project getProject(String owner, String repo){
        return projectService.getProject(owner, repo);
    }
}
