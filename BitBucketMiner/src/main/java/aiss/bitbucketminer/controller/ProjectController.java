package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.parsedModels.ParsedProject;
import aiss.bitbucketminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{owner}/{repo}")
    public ParsedProject getProject(@PathVariable String owner, @PathVariable String repo) {
        return projectService.getProject(owner, repo);
    }
}
