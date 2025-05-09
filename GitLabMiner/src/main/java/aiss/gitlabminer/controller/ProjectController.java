package aiss.gitlabminer.controller;

import aiss.gitlabminer.exception.ProjectNotFoundException;
import aiss.gitlabminer.model.Project;
import aiss.gitlabminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gitlab")
//POST gitlab/{owner}/{name}[?nCommits=5&nIssues=5]

public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{owner}/{name}")
    public Project getProject(@PathVariable String owner, @PathVariable String name,
                        @RequestParam(defaultValue = "5") Integer nCommits, @RequestParam(defaultValue = "5") Integer nIssues) throws ProjectNotFoundException {
        Project project = projectService.getProject(owner, name);
        if (project != null) {
            return project;
        } else {
            throw new ProjectNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{owner}/{name}")
    public Project postToGitMiner(@PathVariable String owner, @PathVariable String name,
                        @RequestParam(defaultValue = "5") Integer nCommits, @RequestParam(defaultValue = "5") Integer nIssues) {
        return projectService.postToGitMiner(owner, name);
    }
}
