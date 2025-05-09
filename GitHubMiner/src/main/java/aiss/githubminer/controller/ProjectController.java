package aiss.githubminer.controller;

import aiss.githubminer.parsedmodel.ParsedProject;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{owner}/{repo}")
    public ParsedProject getProject(@PathVariable(value = "owner") String owner,
                                    @PathVariable(value = "repo") String repo){
        return projectService.getProject(owner, repo);
    }
}
