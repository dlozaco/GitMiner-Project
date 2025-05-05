package aiss.githubminer.controller;

import aiss.githubminer.model.Project;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{owner}/{repo}")
    public Project getProject(@PathVariable(value = "owner") String owner,
                              @PathVariable(value = "repo") String repo){
        return projectService.getProject(owner, repo);
    }
}
