package aiss.gitlabminer.controller;

import aiss.gitlabminer.model.Project;
import aiss.gitlabminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(defaultValue = "5") Integer nCommits, @RequestParam(defaultValue = "5") Integer nIssues) throws Exception {
        Project project = projectService.getProject(owner, name);
        if (project != null) {
            return project;
        } else {
            throw new Exception("ERROR RETRIEVING PROJECT");
        }
    }

    @PostMapping("/{owner}/{name}")
    public Project getAndPostToGitMiner(@PathVariable String owner, @PathVariable String name,
                              @RequestParam(defaultValue = "5") Integer nCommits, @RequestParam(defaultValue = "5") Integer nIssues) throws Exception {
        Project project = projectService.getAndPostToGitMiner(owner, name);
        if (project != null) {
            return project;
        } else {
            throw new Exception("ERROR SENDING PROJECT TO GITMINER");
        }
    }
}
